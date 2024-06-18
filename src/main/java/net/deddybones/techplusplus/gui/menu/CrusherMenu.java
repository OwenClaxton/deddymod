package net.deddybones.techplusplus.gui.menu;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.entity.CrusherBlockEntity;
import net.deddybones.techplusplus.gui.menu.slots.CrusherResultSlot;
import net.deddybones.techplusplus.gui.menu.util.ModAbstractRecipeBookMenu;
import net.deddybones.techplusplus.gui.ModMenuTypes;
import net.deddybones.techplusplus.recipes.CrusherRecipe;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CrusherMenu extends ModAbstractRecipeBookMenu<SingleRecipeInput, CrusherRecipe> {
   public static final int SLOT_INPUT = CrusherBlockEntity.SLOT_INPUT;
   public static final int SLOT_RESULT = CrusherBlockEntity.SLOT_RESULT;
   public static final int SLOT_COUNT = CrusherBlockEntity.SLOT_COUNT;
   public static final int GRID_WIDTH = CrusherBlockEntity.GRID_WIDTH;
   public static final int GRID_HEIGHT = CrusherBlockEntity.GRID_HEIGHT;
   public static final int NUM_DATA_VALUES = CrusherBlockEntity.NUM_DATA_VALUES;
   private static final int INV_SLOT_START = SLOT_COUNT; // inclusive
   private static final int INV_SLOT_END = INV_SLOT_START + (3*9); // not inclusive
   private static final int USE_ROW_SLOT_START = INV_SLOT_END; // inclusive
   private static final int USE_ROW_SLOT_END = USE_ROW_SLOT_START + 9; // not inclusive
   public final int INPUT_SLOT_PX = 54;
   public static final int INPUT_SLOT_PY = 33;
   public final int RESULT_SLOT_PX = 106;
   public static final int RESULT_SLOT_PY = 33;
   private final Container container;
   private final ContainerData data;
   protected final Level level;
   private final RecipeBookType recipeBookType;


   public CrusherMenu(int pContainerId, Inventory pInventory) {
      this(pContainerId, pInventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(NUM_DATA_VALUES));
   }

   public CrusherMenu(int pContainerId, Inventory pInventory, Container pContainer, ContainerData pContData) {

      super(ModMenuTypes.CRUSHER.get(), pContainerId);
      this.recipeBookType = TechPlusPlus.CRUSHER_RECIPE_BOOK_TYPE;
      checkContainerSize(pContainer, 2);
      checkContainerDataCount(pContData, 2);
      this.container = pContainer;
      this.data = pContData;
      this.level = pInventory.player.level();
      this.addSlot(new Slot(pContainer, SLOT_INPUT, INPUT_SLOT_PX, INPUT_SLOT_PY));
      this.addSlot(new CrusherResultSlot(pInventory.player, pContainer, SLOT_RESULT, RESULT_SLOT_PX, RESULT_SLOT_PY));

      addPlayerInventory(pInventory);
      addPlayerHotbar(pInventory);

      this.addDataSlots(this.data);
   }

   public boolean isCrushing() {
      return CrusherBlockEntity.isCrushing(this.data);
   }

   public float getCrushingProgress() {
      int i = this.data.get(CrusherBlockEntity.DATA_CRUSHING_PROGRESS);
      int j = this.data.get(CrusherBlockEntity.DATA_CRUSHING_TOTAL_TIME);
      return j != 0 && i != 0 ? Mth.clamp((float)i / (float)j, 0.0F, 1.0F) : 0.0F;
   }

   @Override
   public void fillCraftSlotsStackedContents(@NotNull StackedContents pStackedContents) {
      if (this.container instanceof StackedContentsCompatible stackedContentsCompatible)
         stackedContentsCompatible.fillStackedContents(pStackedContents);

   }

   @Override
   public void clearCraftingContent() {
      this.getSlot(SLOT_INPUT).set(ItemStack.EMPTY);
      this.getSlot(SLOT_RESULT).set(ItemStack.EMPTY);
   }

   @Override
   public boolean recipeMatches(RecipeHolder pRecipeHolder) {
      return pRecipeHolder.value().matches(new SingleRecipeInput(this.container.getItem(SLOT_INPUT)), this.level);
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
      if (pSlotIndex == SLOT_RESULT) { // if we shift-click the result:
         // check we can move into inventory:
         if (!this.moveItemStackTo(slotStack, INV_SLOT_START, USE_ROW_SLOT_END, true))
            return ItemStack.EMPTY; // failed
         // success:
         quickMovedSlot.onQuickCraft(slotStack, outStack); // perform logic on slot quick move (see: CrusherResultSlot)
      } else if (pSlotIndex != SLOT_INPUT) { // if we shift-click anywhere in the inventory:
         // first try move into input:
         if (CrusherBlockEntity.stackCanBeCrushed(this.level, slotStack)) {
            if (!this.moveItemStackTo(slotStack, SLOT_INPUT, SLOT_INPUT+1, false)) {
               return ItemStack.EMPTY;
            }
         // instead, try move from hotbar to inventory or vice versa:
         } else if (pSlotIndex >= INV_SLOT_START && pSlotIndex < INV_SLOT_END) {
            if (!this.moveItemStackTo(slotStack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
               return ItemStack.EMPTY;
            }
         } else if (pSlotIndex >= USE_ROW_SLOT_START && pSlotIndex < USE_ROW_SLOT_END
                 && !this.moveItemStackTo(slotStack, INV_SLOT_START, INV_SLOT_END, false)) {
            return ItemStack.EMPTY;
         }
      // if from any non-result data slot:
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
      return pSlotIndex == SLOT_INPUT;
   }
}