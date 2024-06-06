package net.deddybones.techplusplus.block.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.deddybones.techplusplus.block.custom.CrusherBlock;
import net.deddybones.techplusplus.gui.menu.CrusherMenu;
import net.deddybones.techplusplus.recipes.CrusherRecipe;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
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
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("SequencedCollectionMethodCanBeUsed")
public class CrusherBlockEntity extends BaseContainerBlockEntity
        implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_RESULT = 1;
    public static final int SLOT_COUNT = 2;
    public static final int GRID_WIDTH = 1;
    public static final int GRID_HEIGHT = 1;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{1};
    public static final int DATA_CRUSHING_PROGRESS = 0;
    public static final int DATA_CRUSHING_TOTAL_TIME = 1;
    public static final int NUM_DATA_VALUES = 2;
    public static final int CRUSHING_DURATION_STANDARD = 50;
    int crushingProgress;
    int crushingTotalTime;
    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed =
            new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<Container, CrusherRecipe> quickRecipeCheck;
    LazyOptional<? extends IItemHandler>[] handlers =
            SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int pDataIndex) {
            return switch (pDataIndex) {
                case DATA_CRUSHING_PROGRESS -> CrusherBlockEntity.this.crushingProgress;
                case DATA_CRUSHING_TOTAL_TIME -> CrusherBlockEntity.this.crushingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int pDataIndex, int pDataValue) {
            switch (pDataIndex) {
                case DATA_CRUSHING_PROGRESS:
                    CrusherBlockEntity.this.crushingProgress = pDataValue;
                    break;
                case DATA_CRUSHING_TOTAL_TIME:
                    CrusherBlockEntity.this.crushingTotalTime = pDataValue;
            }
        }

        @Override
        public int getCount() {
            return NUM_DATA_VALUES;
        }
    };

    public CrusherBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.CRUSHER.get(), pPos, pState);
        this.quickRecipeCheck = RecipeManager.createCheck(ModRecipes.CRUSHING_TYPE.get());
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag pTag,
                                  @NotNull HolderLookup.Provider pProvider) {
        super.loadAdditional(pTag, pProvider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items, pProvider);
        this.crushingProgress = pTag.getInt("CrushingTime");
        this.crushingTotalTime = pTag.getInt("CrushingTotalTime");
        CompoundTag compoundTag = pTag.getCompound("RecipesUsed");
        for (String s : compoundTag.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundTag.getInt(s));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag,
                                  @NotNull HolderLookup.Provider pProvider) {
        super.saveAdditional(pTag, pProvider);
        pTag.putInt("CrushingTime", this.crushingProgress);
        pTag.putInt("CrushingTotalTime", this.crushingTotalTime);
        ContainerHelper.saveAllItems(pTag, this.items, pProvider);
        CompoundTag compoundTag = new CompoundTag();
        this.recipesUsed.forEach(
                (ResourceLocation resLoc, Integer recipeInt)
                        -> compoundTag.putInt(resLoc.toString(), recipeInt));
        pTag.put("RecipesUsed", compoundTag);
    }

    public boolean isCrushing() {
        return this.crushingProgress > 0;
    }

    public static boolean isCrushing(ContainerData dataAccess) {
        return dataAccess.get(DATA_CRUSHING_PROGRESS) > 0;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, CrusherBlockEntity pEntity) {
        boolean inputIsNotEmpty = !pEntity.items.get(SLOT_INPUT).isEmpty(); // input
        if (inputIsNotEmpty) {
            RecipeHolder<CrusherRecipe> recipeHolder = pEntity.quickRecipeCheck
                                                .getRecipeFor(pEntity, pLevel)
                                                .orElse(null);
            int maxStackSize = pEntity.getMaxStackSize();
            if (pEntity.canCrush(pLevel.registryAccess(), recipeHolder, pEntity.items, maxStackSize)) {
                pEntity.crushingProgress++; // If we were not, we are now crushing
                if (pEntity.crushingProgress == pEntity.crushingTotalTime) {
                    pEntity.crushingProgress = 0; // we have finished this item
                    pEntity.crushingTotalTime = getTotalCrushingTime(pLevel, pEntity); // prepare for next item
                    if (pEntity.crush(pLevel.registryAccess(), recipeHolder, pEntity.items, maxStackSize)) {
                        pEntity.setRecipeUsed(recipeHolder);
                    }
                }
            } else { // if we can't crush the item
                pEntity.crushingProgress = 0;
            }
        } else { // if the input is empty
            pEntity.crushingProgress = 0;
        }
        pState = pState.setValue(CrusherBlock.CRUSHING, pEntity.isCrushing());
        pLevel.setBlock(pPos, pState, 3);
        setChanged(pLevel, pPos, pState);
    }

    public static boolean stackCanBeCrushed(Level pLevel, ItemStack pStack) {
        return pLevel.getRecipeManager()
                .getRecipeFor(ModRecipes.CRUSHING_TYPE.get(), new SimpleContainer(pStack), pLevel)
                .isPresent();
    }

    private boolean canCrush(RegistryAccess pAccess,
                            @Nullable RecipeHolder<CrusherRecipe> pRecipeHolder,
                            NonNullList<ItemStack> pItems, int pMaxStackSize) {
        if (pItems.get(SLOT_INPUT).isEmpty() || pRecipeHolder == null) // no item to crush or no recipe
            return false;
        ItemStack recipeResult = pRecipeHolder.value().assemble(this, pAccess);
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

    private boolean crush(RegistryAccess pAccess,
                         @Nullable RecipeHolder<CrusherRecipe> pRecipeHolder,
                         NonNullList<ItemStack> pItems, int pMaxStackSize) {
        if (pRecipeHolder == null || !canCrush(pAccess, pRecipeHolder, pItems, pMaxStackSize))
            return false;
        ItemStack inputStack = pItems.get(SLOT_INPUT);
        ItemStack recipeStack = pRecipeHolder.value().assemble(this, pAccess);
        ItemStack resultStack = pItems.get(SLOT_RESULT);
        if (resultStack.isEmpty()) {
            pItems.set(SLOT_RESULT, recipeStack.copy());
        } else if (ItemStack.isSameItemSameComponents(resultStack, recipeStack)) {
            resultStack.grow(recipeStack.getCount());
        }
        inputStack.shrink(1);
        return true;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.techplusplus.crusher");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int pContainerId,
                                                        @NotNull Inventory pInventory) {
        return new CrusherMenu(pContainerId, pInventory, this, this.dataAccess);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    private static int getTotalCrushingTime(Level pLevel, CrusherBlockEntity pEntity) {
        return pEntity.quickRecipeCheck.getRecipeFor(pEntity, pLevel)
                .map((RecipeHolder<CrusherRecipe> pRecipe)
                    -> CRUSHING_DURATION_STANDARD)
                .orElse(CRUSHING_DURATION_STANDARD);
        // Currently, everything has the same crushing time. But, we could modify the
        // crushing recipe to have unique crushing times:
        //      .map((RecipeHolder<CrusherRecipe> pRecipe)
        //          -> pRecipe.value().getCrushingTime())
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> pItems) {
        this.items = pItems;
    }

    @Override
    public void setItem(int pSlotIndex, ItemStack pStack) {
        ItemStack currentStack = this.items.get(pSlotIndex);
        boolean doesNotMatter = !pStack.isEmpty() &&
                ItemStack.isSameItemSameComponents(currentStack, pStack);
        this.items.set(pSlotIndex, pStack);
        pStack.limitSize(this.getMaxStackSize(pStack));
        if (pSlotIndex == SLOT_INPUT && !doesNotMatter) {
            this.crushingTotalTime = getTotalCrushingTime(this.level, this);
            this.crushingProgress = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean canPlaceItem(int pSlotIndex, @NotNull ItemStack pStack) {
        return pSlotIndex == SLOT_INPUT;
    }

    @Override
    public @NotNull ModelData getModelData() {
        return super.getModelData();
    }

    @Override
    public boolean hasCustomOutlineRendering(Player player) {
        return super.hasCustomOutlineRendering(player);
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction pDirection) {
        if (pDirection == Direction.UP) {
            return SLOTS_FOR_UP;
        } else if (pDirection == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        }
        return new int[0];
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
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> pCap,
                                                      @Nullable Direction pSide) {
        if (pCap == ForgeCapabilities.ITEM_HANDLER && pSide != null && !this.remove) {
            if (pSide == Direction.UP) return handlers[SLOT_INPUT].cast();
            else if (pSide == Direction.DOWN) return handlers[SLOT_RESULT].cast();
        }
        return super.getCapability(pCap, pSide);
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
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }
}
