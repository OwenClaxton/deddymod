package net.deddybones.techplusplus.block.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.deddybones.techplusplus.block.custom.SmelteryBlock;
import net.deddybones.techplusplus.gui.menu.SmelteryMenu;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.deddybones.techplusplus.recipes.SmelteryRecipe;
import net.deddybones.techplusplus.recipes.inputs.SmelteryInput;
import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.isFuel;

@SuppressWarnings("SequencedCollectionMethodCanBeUsed")
public class SmelteryBlockEntity extends BaseContainerBlockEntity
        implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
    public static final int SLOT_FUEL = 0;
    public static final int SLOTS_MATERIALS_START = SLOT_FUEL + 1; // 1
    public static final int SLOTS_MATERIALS_END = SLOTS_MATERIALS_START + 9; // <10
    public static final int SLOT_MOLD = SLOTS_MATERIALS_END; // 10
    public static final int SLOT_RESULT = SLOT_MOLD + 1; // 11, 12th index
    public static final int SLOT_COUNT = SLOT_RESULT - SLOT_FUEL + 1;
    public static final int GRID_WIDTH = SLOT_COUNT;
    public static final int GRID_HEIGHT = 1;
    private static final int[] SLOTS_FOR_UP = IntStream.range(SLOTS_MATERIALS_START, SLOTS_MATERIALS_END).toArray();
    private static final int[] SLOTS_FOR_DOWN = new int[]{SLOT_RESULT};
    private static final int[] SLOTS_FOR_NORTH = new int[]{SLOT_FUEL};
    private static final int[] SLOTS_FOR_ELSE = new int[]{SLOT_MOLD};
    public static final int DATA_BURN_TIME = 0;
    public static final int DATA_BURN_DURATION = DATA_BURN_TIME + 1;
    public static final int DATA_SMELTING_PROGRESS = DATA_BURN_DURATION + 1;
    public static final int DATA_SMELTING_TOTAL_TIME = DATA_SMELTING_PROGRESS + 1;
    public static final int NUM_DATA_VALUES = DATA_SMELTING_TOTAL_TIME + 1;
    public static final int SMELT_TIME_DEFAULT = 100;
    public static final int BURN_COOL_SPEED = 2;
    int burnTime;
    int burnDuration;
    int smeltingProgress;
    int smeltingTotalTime;
    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<SmelteryInput, SmelteryRecipe> quickRecipeCheck;
    LazyOptional<? extends IItemHandler>[] handlers = makeHandlers();

    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int pDataIndex) {
            return switch (pDataIndex) {
                case DATA_BURN_TIME -> SmelteryBlockEntity.this.burnTime;
                case DATA_BURN_DURATION -> SmelteryBlockEntity.this.burnDuration;
                case DATA_SMELTING_PROGRESS -> SmelteryBlockEntity.this.smeltingProgress;
                case DATA_SMELTING_TOTAL_TIME -> SmelteryBlockEntity.this.smeltingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int pDataIndex, int pDataValue) {
            switch (pDataIndex) {
                case DATA_BURN_TIME:
                    SmelteryBlockEntity.this.burnTime = pDataValue;
                    break;
                case DATA_BURN_DURATION:
                    SmelteryBlockEntity.this.burnDuration = pDataValue;
                    break;
                case DATA_SMELTING_PROGRESS:
                    SmelteryBlockEntity.this.smeltingProgress = pDataValue;
                    break;
                case DATA_SMELTING_TOTAL_TIME:
                    SmelteryBlockEntity.this.smeltingTotalTime = pDataValue;
                    break;
            }
        }

        @Override
        public int getCount() {
            return NUM_DATA_VALUES;
        }
    };

    public SmelteryBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.SMELTERY.get(), pPos, pState);
        this.quickRecipeCheck = RecipeManager.createCheck(ModRecipes.SMELTERY_TYPE.get());
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag pTag,
                                  @NotNull HolderLookup.Provider pProvider) {
        super.loadAdditional(pTag, pProvider);
        this.items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items, pProvider);
        this.burnTime = pTag.getInt("BurnTime");
        this.burnDuration = pTag.getInt("BurnDuration"); // avoid infinite fuel by storing the value.
        this.smeltingProgress = pTag.getInt("SmeltingProgress");
        this.smeltingTotalTime = pTag.getInt("SmeltingTotalTime");
        CompoundTag compoundTag = pTag.getCompound("RecipesUsed");
        for (String s : compoundTag.getAllKeys()) {
            this.recipesUsed.put(ResourceLocation.parse(s), compoundTag.getInt(s));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag,
                                  @NotNull HolderLookup.Provider pProvider) {
        super.saveAdditional(pTag, pProvider);
        pTag.putInt("BurnTime", this.burnTime);
        pTag.putInt("BurnDuration", this.burnDuration); // avoid infinite fuel by storing the value.
        pTag.putInt("SmeltingProgress", this.smeltingProgress);
        pTag.putInt("SmeltingTotalTime", this.smeltingTotalTime);
        ContainerHelper.saveAllItems(pTag, this.items, pProvider);
        CompoundTag compoundTag = new CompoundTag();
        this.recipesUsed.forEach(
                (ResourceLocation resourceLocation, Integer recipeInt)
                        -> compoundTag.putInt(resourceLocation.toString(), recipeInt));
        pTag.put("RecipesUsed", compoundTag);
    }

    public boolean isSmelting() {
        return this.smeltingProgress > 0;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public static int getBurnDuration(ItemStack pStack) {
        //noinspection UnstableApiUsage
        return pStack.isEmpty() ? 0 : net.minecraftforge.common.ForgeHooks.getBurnTime(pStack, null);

    }

    public static boolean hasInputs(NonNullList<ItemStack> pItems) {
        if (pItems.size() < SLOT_COUNT) return false;
        boolean hasMold = !pItems.get(SLOT_MOLD).isEmpty();
        boolean hasMaterials = false;
        for (int slotIndex = SLOTS_MATERIALS_START; slotIndex <= SLOTS_MATERIALS_END; slotIndex++) {
            if (pItems.get(slotIndex).isEmpty()) {
                hasMaterials = true;
                break;
            }
        }
        return hasMold && hasMaterials;
    }

    @SuppressWarnings("unused")
    public boolean hasInputs() {
        return hasInputs(this.items);
    }

    public static boolean isSmelting(ContainerData dataAccess) {
        return dataAccess.get(DATA_SMELTING_PROGRESS) > 0;
    }

    public static boolean isBurning(ContainerData dataAccess) {
        return dataAccess.get(DATA_BURN_TIME) > 0;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState,
                                  SmelteryBlockEntity pEntity) {
        boolean wasBurning = pEntity.isBurning();
        boolean stateChange = false;
        if (pEntity.isBurning()) {
            pEntity.burnTime--;
        }

        ItemStack fuelStack = pEntity.items.get(SLOT_FUEL);
        boolean hasMaterials = hasInputs(pEntity.items);
        boolean fuelNotEmpty = !fuelStack.isEmpty();
        if (wasBurning || (fuelNotEmpty && hasMaterials)) {
            RecipeHolder<SmelteryRecipe> recipeHolder = pEntity.quickRecipeCheck
                    .getRecipeFor(pEntity.asInput(), pLevel)
                    .orElse(null);

            int maxStackSize = pEntity.getMaxStackSize();
            if (!pEntity.isBurning() && pEntity.canSmelt(pLevel.registryAccess(), recipeHolder, pEntity.items, maxStackSize)) {
                pEntity.burnTime = getBurnDuration(fuelStack);
                pEntity.burnDuration = pEntity.burnTime;
                if (pEntity.isBurning()) {
                    stateChange = true;
                    if (fuelStack.hasCraftingRemainingItem()) {
                        pEntity.items.set(SLOT_FUEL, fuelStack.getCraftingRemainingItem());
                    } else if (fuelNotEmpty) {
                        fuelStack.shrink(1);
                        if (fuelStack.isEmpty()) {
                            pEntity.items.set(SLOT_FUEL, fuelStack.getCraftingRemainingItem());
                        }
                    }
                }
            }
            if (pEntity.isBurning() && pEntity.canSmelt(pLevel.registryAccess(), recipeHolder, pEntity.items, maxStackSize)) {
                pEntity.smeltingProgress++;
                if (pEntity.smeltingProgress == pEntity.smeltingTotalTime) {
                    pEntity.smeltingProgress = 0;
                    pEntity.smeltingTotalTime = getTotalSmeltingTime(pLevel, pEntity);
                    if (pEntity.smelt(pLevel.registryAccess(), recipeHolder, pEntity.items, maxStackSize)) {
                        pEntity.setRecipeUsed(recipeHolder);
                    }
                    stateChange = true;
                }
            } else {
                pEntity.smeltingProgress = 0;
            }
        } else if (!pEntity.isBurning() && pEntity.smeltingProgress > 0) {
            pEntity.smeltingProgress = Mth.clamp(pEntity.smeltingProgress - BURN_COOL_SPEED, 0, pEntity.smeltingTotalTime);
        }

        if (wasBurning != pEntity.isBurning()) {
            stateChange = true;
            pState = pState.setValue(SmelteryBlock.SMELTING, pEntity.isBurning());
            pLevel.setBlock(pPos, pState, 3);
        }

        if (stateChange) {
            setChanged(pLevel, pPos, pState);
        }
    }

//    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, SmelteryBlockEntity pEntity) {
//        if (pEntity.hasInputs()) {
//            RecipeHolder<SmelteryRecipe> recipeHolder = pEntity.quickRecipeCheck
//                    .getRecipeFor(pEntity, pLevel)
//                    .orElse(null);
//            int maxStackSize = pEntity.getMaxStackSize();
//            if (pEntity.canSmelt(pLevel.registryAccess(), recipeHolder, pEntity.items, maxStackSize)) {
//                pEntity.smeltingProgress++; // If we were not, we are now crushing
//                if (pEntity.smeltingProgress == pEntity.smeltingTotalTime) {
//                    pEntity.smeltingProgress = 0; // we have finished this item
//                    pEntity.smeltingTotalTime = getTotalSmeltingTime(pLevel, pEntity); // prepare for next item
//                    if (pEntity.smelt(pLevel.registryAccess(), recipeHolder, pEntity.items, maxStackSize)) {
//                        pEntity.setRecipeUsed(recipeHolder);
//                    }
//                }
//            } else { // if we can't crush the item
//                pEntity.smeltingProgress = 0;
//            }
//        } else { // if the input is empty
//            pEntity.smeltingProgress = 0;
//        }
//        pState = pState.setValue(SmelteryBlock.SMELTING, pEntity.isSmelting());
//        pLevel.setBlock(pPos, pState, 3);
//        setChanged(pLevel, pPos, pState);
//    }

    private boolean canSmelt(RegistryAccess pAccess,
                             @Nullable RecipeHolder<SmelteryRecipe> pRecipeHolder,
                             NonNullList<ItemStack> pItems, int pMaxStackSize) {
        if (!hasInputs(pItems) || pRecipeHolder == null) // no item to crush or no recipe
            return false;
        ItemStack recipeResult = pRecipeHolder.value().assemble(this.asInput(), pAccess);
        if (recipeResult.isEmpty()) // no valid recipe
            return false;
        ItemStack existingResultStack = pItems.get(SLOT_RESULT);
        if (existingResultStack.isEmpty()) // no stack to clash with
            return true;
        if (!ItemStack.isSameItemSameComponents(existingResultStack, recipeResult)) // stack clashes
            return false;
        // Make sure we obey stack maximum sizes:
        boolean countLessThanEntityMax = (existingResultStack.getCount() + recipeResult.getCount())
                <= pMaxStackSize;
        boolean countLessThanResultMax = (existingResultStack.getCount() + recipeResult.getCount())
                <= existingResultStack.getMaxStackSize();
        return countLessThanEntityMax && countLessThanResultMax;
    }

    private boolean smelt(RegistryAccess pAccess,
                          @Nullable RecipeHolder<SmelteryRecipe> pRecipeHolder,
                          NonNullList<ItemStack> pItems, int pMaxStackSize) {
        if (pRecipeHolder == null || !canSmelt(pAccess, pRecipeHolder, pItems, pMaxStackSize))
            return false;
        ItemStack recipeStack = pRecipeHolder.value().assemble(this.asInput(), pAccess);
        ItemStack resultStack = pItems.get(SLOT_RESULT);
        if (resultStack.isEmpty()) {
            pItems.set(SLOT_RESULT, recipeStack.copy());
        } else if (ItemStack.isSameItemSameComponents(resultStack, recipeStack)) {
            resultStack.grow(recipeStack.getCount());
        }
        for (int slotIndex = SLOTS_MATERIALS_START; slotIndex < SLOTS_MATERIALS_END; slotIndex++)
            pItems.get(slotIndex).shrink(1);
        return true;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return SmelteryBlock.CONTAINER_TITLE;
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory) {
        return new SmelteryMenu(pContainerId, pInventory, this, this.dataAccess);
    }

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    private static int getTotalSmeltingTime(Level pLevel, SmelteryBlockEntity pEntity) {
        return pEntity.quickRecipeCheck.getRecipeFor(pEntity.asInput(), pLevel)
                .map((RecipeHolder<SmelteryRecipe> pRecipe)
                    -> pRecipe.value().getSmeltTime())
                .orElse(SMELT_TIME_DEFAULT);
    }

    public static SmelteryInput asInput(List<ItemStack> pItems) {
        return new SmelteryInput(pItems.subList(SLOTS_MATERIALS_START, SLOTS_MATERIALS_END),
                                 pItems.get(SLOT_MOLD));
    }

    public static SmelteryInput asInput(Container pContainer) {
        int i = pContainer.getContainerSize();
        if (i < SLOT_COUNT) {
            throw new IllegalArgumentException("Container size " + i + " is smaller than expected " + SLOT_COUNT);
        }
        return asInput(IntStream.range(0, pContainer.getContainerSize())
                                .mapToObj(pContainer::getItem)
                                .collect(Collectors.toList()));
    }

    public SmelteryInput asInput() {
        return asInput(this.items);
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> pItems) {
        this.items = pItems;
    }

    public static boolean indexIsMaterial(int pSlotIndex) {
        return (pSlotIndex >= SLOTS_MATERIALS_START) && (pSlotIndex < SLOTS_MATERIALS_END);
    }

    @Override
    public void setItem(int pSlotIndex, ItemStack pStack) {
        ItemStack currentStack = this.items.get(pSlotIndex);
        boolean doesNotMatter = !pStack.isEmpty() &&
                ItemStack.isSameItemSameComponents(currentStack, pStack);
        this.items.set(pSlotIndex, pStack);
        pStack.limitSize(this.getMaxStackSize(pStack));
        if (indexIsMaterial(pSlotIndex) && !doesNotMatter) {
            this.smeltingTotalTime = getTotalSmeltingTime(this.level, this);
            this.smeltingProgress = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean canPlaceItem(int pSlotIndex, @NotNull ItemStack pStack) {
        return switch (pSlotIndex) {
            case SLOT_RESULT -> false;
            case SLOT_MOLD -> pStack.is(ModTags.Items.IS_A_MOLD);
            case SLOT_FUEL -> isFuel(pStack);
            default -> true;
        };
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction pDirection) {
        return switch(pDirection) {
            case DOWN -> SLOTS_FOR_DOWN;
            case UP -> SLOTS_FOR_UP;
            case NORTH -> SLOTS_FOR_NORTH;
            default -> SLOTS_FOR_ELSE;
        };
    }

    @Override
    public boolean canPlaceItemThroughFace(int pSlotIndex,
                                           @NotNull ItemStack pStack,
                                           @Nullable Direction p_58338_) {
        // for hopper input
        return this.canPlaceItem(pSlotIndex, pStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int pSlotIndex, @NotNull ItemStack pStack,
                                          @NotNull Direction pDirection) {
        // for hopper output
        return (pDirection == Direction.DOWN) && (pSlotIndex == SLOT_RESULT);
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> pHolder) {
        if (pHolder != null) {
            ResourceLocation resLoc = pHolder.id();
            this.recipesUsed.addTo(resLoc, 1);
        }
    }

    @Override
    @Nullable
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void awardUsedRecipes(@NotNull Player pPlayer, @NotNull List<ItemStack> pStack) {
    }

    @SuppressWarnings("unused")
    public void awardUsedRecipes(ServerPlayer pPlayer) {
        List<RecipeHolder<?>> recipeHolders =
                this.getRecipesToAward(pPlayer.serverLevel());
        pPlayer.awardRecipes(recipeHolders);
        for (RecipeHolder<?> recipeHolder : recipeHolders) {
            if (recipeHolder != null) {
                pPlayer.triggerRecipeCrafted(recipeHolder, this.items);
            }
        }
        this.recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAward(ServerLevel pLevel) {
        List<RecipeHolder<?>> recipeHolders = Lists.newArrayList();
        for (Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            pLevel.getRecipeManager().byKey(entry.getKey()).ifPresent(recipeHolders::add);
        }
        return recipeHolders;
    }

    @Override
    public void fillStackedContents(@NotNull StackedContents pContents) {
        for (ItemStack itemStack : this.items) {
            pContents.accountStack(itemStack);
        }
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability,
                                                      @Nullable Direction facing) {
        if (capability == ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
            return switch (facing) {
                // indices as per handlers construction:
                case UP -> handlers[0].cast();
                case DOWN -> handlers[1].cast();
                case NORTH -> handlers[2].cast();
                default -> handlers[3].cast();
            };
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) {
            handler.invalidate();
        }
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = makeHandlers();
    }

    private LazyOptional<IItemHandlerModifiable>[] makeHandlers() {
        return SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH, Direction.WEST);
    }
}
