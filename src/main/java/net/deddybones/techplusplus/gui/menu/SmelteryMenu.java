package net.deddybones.techplusplus.gui.menu;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.entity.SmelteryBlockEntity;
import net.deddybones.techplusplus.gui.ModMenuTypes;
import net.deddybones.techplusplus.gui.menu.slots.SmelteryResultSlot;
import net.deddybones.techplusplus.gui.menu.slots.TagOnlySlot;
import net.deddybones.techplusplus.gui.menu.util.ModAbstractRecipeBookMenu;
import net.deddybones.techplusplus.recipes.SmelteryRecipe;
import net.deddybones.techplusplus.recipes.inputs.SmelteryInput;
import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.isFuel;

public class SmelteryMenu extends ModAbstractRecipeBookMenu<SmelteryInput, SmelteryRecipe> {
   public static final int SLOT_FUEL = SmelteryBlockEntity.SLOT_FUEL;
   public static final int SLOTS_MATERIALS_START = SmelteryBlockEntity.SLOTS_MATERIALS_START;
   public static final int SLOTS_MATERIALS_END = SmelteryBlockEntity.SLOTS_MATERIALS_END;
   public static final int SLOT_RESULT = SmelteryBlockEntity.SLOT_RESULT;
   public static final int SLOT_MOLD = SmelteryBlockEntity.SLOT_MOLD;
   public static final int SLOT_COUNT = SmelteryBlockEntity.SLOT_COUNT;
   public static final int NUM_DATA_VALUES = SmelteryBlockEntity.NUM_DATA_VALUES;
   public static final int GRID_WIDTH = SmelteryBlockEntity.GRID_WIDTH;
   public static final int GRID_HEIGHT = SmelteryBlockEntity.GRID_HEIGHT;
   private static final int INV_SLOT_START = SLOT_COUNT; // inclusive
   private static final int INV_SLOT_END = INV_SLOT_START + (3*9); // not inclusive
   private static final int USE_ROW_SLOT_START = INV_SLOT_END; // inclusive
   private static final int USE_ROW_SLOT_END = USE_ROW_SLOT_START + 9; // not inclusive
   public final int MATERIALS_FIRST_SLOT_PX = 43;
   public static final int MATERIALS_FIRST_SLOT_PY = 18;
   public final int RESULT_SLOT_PX = 139;
   public static final int RESULT_SLOT_PY = 35;
   public final int MOLD_SLOT_PX = 109;
   public static final int MOLD_SLOT_PY = 54;
   public final int FUEL_SLOT_PX = 19;
   public static final int FUEL_SLOT_PY = 54;
   private final Container container;
   private final ContainerData data;
   protected final Level level;
   private final RecipeBookType recipeBookType;

   public SmelteryMenu(int pContainerId, Inventory pInventory) {
      this(pContainerId, pInventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(NUM_DATA_VALUES));
   }

   public SmelteryMenu(int pContainerId, Inventory pInventory, Container pContainer, ContainerData pContData) {
      super(ModMenuTypes.SMELTERY.get(), pContainerId);
      this.recipeBookType = TechPlusPlus.SMELTERY_RECIPE_BOOK_TYPE;
      checkContainerSize(pContainer, SLOT_COUNT);
      checkContainerDataCount(pContData, NUM_DATA_VALUES);
      this.container = pContainer;
      this.data = pContData;
      this.level = pInventory.player.level();
      this.addSlot(new Slot(pContainer, SLOT_FUEL, FUEL_SLOT_PX, FUEL_SLOT_PY));
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            this.addSlot(new Slot(pContainer, SLOTS_MATERIALS_START + j + i * 3,
                    MATERIALS_FIRST_SLOT_PX + j * 18,
                    MATERIALS_FIRST_SLOT_PY + i * 18));
         }
      }
      this.addSlot(new TagOnlySlot(ModTags.Items.IS_A_MOLD, pContainer, SLOT_MOLD, MOLD_SLOT_PX, MOLD_SLOT_PY));
      this.addSlot(new SmelteryResultSlot(pInventory.player, pContainer, SLOT_RESULT, RESULT_SLOT_PX, RESULT_SLOT_PY));

      addPlayerInventory(pInventory);
      addPlayerHotbar(pInventory);

      this.addDataSlots(this.data);
   }

   public boolean isSmelting() {
      return SmelteryBlockEntity.isSmelting(this.data);
   }

   public boolean isBurning() {
      return SmelteryBlockEntity.isBurning(this.data);
   }

   public float getSmeltingProgress() {
      int i = this.data.get(SmelteryBlockEntity.DATA_SMELTING_PROGRESS);
      int j = this.data.get(SmelteryBlockEntity.DATA_SMELTING_TOTAL_TIME);
      return j != 0 && i != 0 ? Mth.clamp((float)i / (float)j, 0.0F, 1.0F) : 0.0F;
   }

   public float getBurnProgress() {
      int i = this.data.get(SmelteryBlockEntity.DATA_BURN_TIME);
      int j = this.data.get(SmelteryBlockEntity.DATA_BURN_DURATION);
      if (j == 0) {
         j = 200;
      }
      return Mth.clamp((float) i / (float) j, 0.0F, 1.0F);
   }

   @Override
   public void fillCraftSlotsStackedContents(@NotNull StackedContents pStackedContents) {
      if (this.container instanceof StackedContentsCompatible stackedContentsCompatible)
         stackedContentsCompatible.fillStackedContents(pStackedContents);

   }

   @Override
   public void clearCraftingContent() {
      for (int slotIndex = SLOTS_MATERIALS_START; slotIndex < SLOTS_MATERIALS_END; slotIndex++)
         this.getSlot(slotIndex).set(ItemStack.EMPTY);
      this.getSlot(SLOT_RESULT).set(ItemStack.EMPTY);
      this.getSlot(SLOT_MOLD).set(ItemStack.EMPTY);
   }

   @Override
   public boolean recipeMatches(RecipeHolder<SmelteryRecipe> pRecipeHolder) {
      return pRecipeHolder.value().matches(SmelteryBlockEntity.asInput(this.container), this.level);
   }

   @Override
   public int getResultSlotIndex() {
      return SLOT_RESULT;
   }

   @Override
   public int getGridWidth() {
      return GRID_WIDTH;
   }

   @Override
   public int getGridHeight() {
      return GRID_HEIGHT;
   }

   @Override
   public int getSize() {
      return SLOT_COUNT;
   }

   @Override
   public boolean stillValid(@NotNull Player pPlayer) {
      return this.container.stillValid(pPlayer);
   }

   @Override
   public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pSlotIndex) {
      Slot quickMovedSlot = this.slots.get(pSlotIndex);
      if (!quickMovedSlot.hasItem())
         return ItemStack.EMPTY;
      ItemStack slotStack = quickMovedSlot.getItem();
      ItemStack outStack = slotStack.copy();
      // if we shift-click any smeltery non-material slot:
      if (pSlotIndex == SLOT_RESULT || pSlotIndex == SLOT_MOLD || pSlotIndex == SLOT_FUEL) {
         // check we can move into inventory:
         if (!this.moveItemStackTo(slotStack, INV_SLOT_START, USE_ROW_SLOT_END, true))
            return ItemStack.EMPTY; // failed
         // success:
         quickMovedSlot.onQuickCraft(slotStack, outStack); // perform logic on slot quick move (see: SmelteryResultSlot)
      } else if (!SmelteryBlockEntity.indexIsMaterial(pSlotIndex)) { // if we shift-click anywhere in the inventory:
         // first try move into fuel slot:
         if (isFuel(slotStack)) {
            if (!this.moveItemStackTo(slotStack, SLOT_FUEL, SLOT_FUEL + 1, false)) {
               return ItemStack.EMPTY;
            }
         } else if (slotStack.is(ModTags.Items.IS_A_MOLD)) {
            if (!this.moveItemStackTo(slotStack, SLOT_MOLD, SLOT_MOLD + 1, false)) {
               return ItemStack.EMPTY;
            }
         } else if (!this.moveItemStackTo(slotStack, SLOTS_MATERIALS_START, SLOTS_MATERIALS_END, false)) {
            return ItemStack.EMPTY;
         // instead, try move from hotbar to inventory or vice versa:
         } else if (pSlotIndex >= INV_SLOT_START && pSlotIndex < INV_SLOT_END) {
            if (!this.moveItemStackTo(slotStack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
               return ItemStack.EMPTY;
            }
         } else if (pSlotIndex >= USE_ROW_SLOT_START && pSlotIndex < USE_ROW_SLOT_END
                 && !this.moveItemStackTo(slotStack, INV_SLOT_START, INV_SLOT_END, false)) {
            return ItemStack.EMPTY;
         }
      // if from any material data slot:
      } else if (!this.moveItemStackTo(slotStack, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
         return ItemStack.EMPTY;
      }

      if (slotStack.isEmpty()) {
         // ff the stack has completely moved out of the slot, set slot to empty
         quickMovedSlot.setByPlayer(ItemStack.EMPTY);
      } else {
         // otherwise, notify the slot that the stack count has changed
         quickMovedSlot.setChanged();
      }
      if (slotStack.getCount() == outStack.getCount()) { // no change:
         return ItemStack.EMPTY;
      }
      // Execute logic on what to do post move with the remaining stack
      quickMovedSlot.onTake(pPlayer, slotStack);
      return outStack;
   }

   @Override
   public @NotNull RecipeBookType getRecipeBookType() {
      return recipeBookType;
   }

   @Override
   public boolean shouldMoveToInventory(int pSlotIndex) {
      return SmelteryBlockEntity.indexIsMaterial(pSlotIndex);
   }
}