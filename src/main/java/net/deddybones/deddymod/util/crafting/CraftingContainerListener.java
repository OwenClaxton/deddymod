package net.deddybones.deddymod.util.crafting;

import net.deddybones.deddymod.DeddyMod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import org.jetbrains.annotations.NotNull;

public class CraftingContainerListener implements ContainerListener {

    private Player player;

    public CraftingContainerListener(Player pPlayer) {
        this.player = pPlayer;

    }
    @Override
    public void slotChanged(@NotNull AbstractContainerMenu pContainerMenu, int pSlotIndex, @NotNull ItemStack pItemStack) {
        CarveProcessHolder.CarveEventHolder carveEventHolder = DeddyMod.carveProcessHolder.get(this.player.getStringUUID());
        if (carveEventHolder == null) return;
        if ((pContainerMenu.getClass() == InventoryMenu.class) || (pContainerMenu.getClass() == CraftingMenu.class)) {
            if (pSlotIndex == Math.max(carveEventHolder.getCarverIndex(), carveEventHolder.getCarvableIndex())) {//((pSlotIndex >= 1) && (pSlotIndex <= 4)) {
                int OFFSET_BETWEEN_CONTAINER_VIEWS = 1;
                Slot carverSlot     = carveEventHolder.getPlayer().containerMenu.slots.get(carveEventHolder.getCarverIndex() + OFFSET_BETWEEN_CONTAINER_VIEWS);
                Slot carvableSlot   = carveEventHolder.getPlayer().containerMenu.slots.get(carveEventHolder.getCarvableIndex() + OFFSET_BETWEEN_CONTAINER_VIEWS);
                carverSlot.set(carveEventHolder.getCarver().copy());
                carvableSlot.set(carveEventHolder.getCarvable().copy());
                carverSlot.setChanged();
                carvableSlot.setChanged();
                DeddyMod.carveProcessHolder.remove(this.player.getStringUUID());
            }
        }
    }

    @Override
    public void dataChanged(@NotNull AbstractContainerMenu p_150524_, int p_150525_, int p_150526_) {

    }
}
