package net.deddybones.techplusplus.gui.menu.util;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class ModAbstractRecipeBookMenu<C extends Container> extends RecipeBookMenu<C> {
    public static final int NUM_INVENTORY_ROWS = 3;
    public static final int INVENTORY_SLOTS_PER_ROW = 9;

    protected ModAbstractRecipeBookMenu(MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    protected void addPlayerInventory(Inventory pInventory) {
        for (int i = 0; i < NUM_INVENTORY_ROWS; ++i) {
            for (int l = 0; l < INVENTORY_SLOTS_PER_ROW; ++l) {
                this.addSlot(new Slot(pInventory, l + i * INVENTORY_SLOTS_PER_ROW + INVENTORY_SLOTS_PER_ROW,
                        8 + l * 18, 84 + i * 18)); // dimension stuff.
            }
        }
    }

    protected void addPlayerHotbar(Inventory pInventory) {
        for (int i = 0; i < INVENTORY_SLOTS_PER_ROW; ++i) {
            this.addSlot(new Slot(pInventory, i,
                    8 + i * 18, 142)); // dimension stuff.
        }
    }

    @Override
    protected boolean moveItemStackTo(@NotNull ItemStack pStack, int startIndexInclusive,
                                      int endIndexExclusive, boolean fromStartToEnd) {
        return super.moveItemStackTo(pStack, startIndexInclusive, endIndexExclusive, fromStartToEnd);
    }
}
