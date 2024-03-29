package net.deddybones.techplusplus.screen;

import com.google.common.collect.Lists;
import java.util.List;

import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.recipes.CrusherRecipe;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CrusherMenu extends AbstractContainerMenu {
   public static final int INPUT_SLOT = 0;
   public static final int RESULT_SLOT = 1;
   private static final int INV_SLOT_START = 2;
   private static final int INV_SLOT_END = 29;
   private static final int USE_ROW_SLOT_START = INV_SLOT_END;
   private static final int USE_ROW_SLOT_END = 38;
   private final ContainerLevelAccess access;
   private final Level level;
   private List<RecipeHolder<CrusherRecipe>> recipes = Lists.newArrayList();
   private ItemStack input = ItemStack.EMPTY;
   long lastSoundTime;
   final Slot inputSlot;
   final Slot resultSlot;
   Runnable slotUpdateListener = () -> {};
   public static final int INPUT_SLOT_PX = 54;
   public static final int INPUT_SLOT_PY = 33;
   public static final int RESULT_SLOT_PX = 106;
   public static final int RESULT_SLOT_PY = 33;
   public final Container inputContainer = new SimpleContainer(1) {
      public void setChanged() {
         super.setChanged();
         CrusherMenu.this.slotsChanged(this);
         CrusherMenu.this.slotUpdateListener.run();
      }
   };
   final ResultContainer resultContainer = new ResultContainer();

   public CrusherMenu(int pContainerId, Inventory pInventory) {
      this(pContainerId, pInventory, ContainerLevelAccess.NULL);
   }

   public CrusherMenu(int pContainerId, Inventory pInventory, final ContainerLevelAccess pAccess) {
      super(ModMenuTypes.CRUSHER_MENU.get(), pContainerId);
      this.access = pAccess;
      this.level = pInventory.player.level();
      this.inputSlot = this.addSlot(new Slot(this.inputContainer, INPUT_SLOT, INPUT_SLOT_PX, INPUT_SLOT_PY));
      this.resultSlot = this.addSlot(new Slot(this.resultContainer, RESULT_SLOT, RESULT_SLOT_PX, RESULT_SLOT_PY) {
         public boolean mayPlace(@NotNull ItemStack pStack) {
            return false;
         }

         public void onTake(@NotNull Player pPlayer, @NotNull ItemStack pStack) {
             pStack.onCraftedBy(pPlayer.level(), pPlayer, pStack.getCount());
            CrusherMenu.this.resultContainer.awardUsedRecipes(pPlayer, this.getRelevantItems());
            ItemStack itemstack = CrusherMenu.this.inputSlot.remove(RESULT_SLOT);
            if (!itemstack.isEmpty()) {
               CrusherMenu.this.setupResultSlot();
            }

             pAccess.execute((pLevel, pPos) -> {
               long l = pLevel.getGameTime();
               if (CrusherMenu.this.lastSoundTime != l) {
                   pLevel.playSound(null, pPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                  CrusherMenu.this.lastSoundTime = l;
               }

            });
            super.onTake(pPlayer, pStack);
         }

         private List<ItemStack> getRelevantItems() {
            return List.of(CrusherMenu.this.inputSlot.getItem());
         }
      });

      addPlayerInventory(pInventory);
      addPlayerHotbar(pInventory);
   }

   private void addPlayerInventory(Inventory pInventory) {
      for (int i = 0; i < 3; ++i) {
         for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(pInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
         }
      }
   }

   private void addPlayerHotbar(Inventory pInventory) {
      for (int i = 0; i < 9; ++i) {
         this.addSlot(new Slot(pInventory, i, 8 + i * 18, 142));
      }
   }

   public List<RecipeHolder<CrusherRecipe>> getRecipes() {
      return this.recipes;
   }

   public boolean stillValid(@NotNull Player pPlayer) {
      return stillValid(this.access, pPlayer, ModBlocks.CRUSHER.get());
   }

   public void slotsChanged(@NotNull Container pContainer) {
      ItemStack itemstack = this.inputSlot.getItem();
      if (!itemstack.is(this.input.getItem())) {
         this.input = itemstack.copy();
         this.updateRecipes(pContainer, itemstack);
         this.setupResultSlot();
      }

   }

   private void updateRecipes(Container pContainer, ItemStack pStack) {
      this.recipes.clear();
      if (!pStack.isEmpty()) {
         this.recipes = this.level.getRecipeManager().getRecipesFor(ModRecipes.CRUSHING_TYPE.get(), pContainer, this.level);
      }
      this.resultSlot.set(ItemStack.EMPTY);
   }

   void setupResultSlot() {
      if (!this.recipes.isEmpty()) {
         RecipeHolder<CrusherRecipe> recipeholder = this.recipes.get(0);
         ItemStack itemstack = recipeholder.value().assemble(this.inputContainer, this.level.registryAccess());
         if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
            this.resultContainer.setRecipeUsed(recipeholder);
            this.resultSlot.set(itemstack);
         } else {
            this.resultSlot.set(ItemStack.EMPTY);
         }
      } else {
         this.resultSlot.set(ItemStack.EMPTY);
      }

      this.broadcastChanges();
   }

   public @NotNull MenuType<?> getType() {
      return ModMenuTypes.CRUSHER_MENU.get();
   }

   public boolean canTakeItemForPickAll(@NotNull ItemStack pStack, Slot pSlot) {
      return pSlot.container != this.resultContainer && super.canTakeItemForPickAll(pStack, pSlot);
   }

   public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pSlotNum) {
      Slot slot = this.slots.get(pSlotNum);
      if (!slot.hasItem()) return ItemStack.EMPTY;
      ItemStack slotStack = slot.getItem();
      ItemStack slotStackOut = slotStack.copy();

      if (pSlotNum == RESULT_SLOT) {
         slotStack.getItem().onCraftedBy(slotStack, pPlayer.level(), pPlayer);
         if (!this.moveItemStackTo(slotStack, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
            return ItemStack.EMPTY;
         }
         slot.onQuickCraft(slotStack, slotStackOut);

      } else if (pSlotNum == INPUT_SLOT) {
         if (!this.moveItemStackTo(slotStack, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
            return ItemStack.EMPTY;
         }

      } else if (this.level.getRecipeManager().getRecipeFor(ModRecipes.CRUSHING_TYPE.get(), new SimpleContainer(slotStack), this.level).isPresent()) {
         if (!this.moveItemStackTo(slotStack, INPUT_SLOT, RESULT_SLOT, false)) {
            return ItemStack.EMPTY;
         }
      } else if (pSlotNum >= INV_SLOT_START && pSlotNum < USE_ROW_SLOT_START) {
         if (!this.moveItemStackTo(slotStack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
            return ItemStack.EMPTY;
         }
      } else if (pSlotNum >= USE_ROW_SLOT_START && pSlotNum < USE_ROW_SLOT_END) {
         if (!this.moveItemStackTo(slotStack, INV_SLOT_START, USE_ROW_SLOT_START, false)) {
            return ItemStack.EMPTY;
         }
      }

      if (slotStack.isEmpty()) {
         slot.setByPlayer(ItemStack.EMPTY);
      }

      slot.setChanged();
      if (slotStack.getCount() == slotStackOut.getCount()) {
         return ItemStack.EMPTY;
      }

      slot.onTake(pPlayer, slotStack);
      this.broadcastChanges();

      return slotStackOut;
   }

   public void removed(@NotNull Player pPlayer) {
      super.removed(pPlayer);
      this.resultContainer.removeItemNoUpdate(1); // this integer is meaningless. Why mojang, Why.
      this.access.execute((pLevel, pPos) -> this.clearContainer(pPlayer, this.inputContainer));
   }
}