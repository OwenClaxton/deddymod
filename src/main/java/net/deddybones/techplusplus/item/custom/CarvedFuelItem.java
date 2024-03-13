package net.deddybones.techplusplus.item.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CarvedFuelItem extends FuelItem {

    public CarvedFuelItem(Properties p_41383_, int burnTime) {
        super(p_41383_, burnTime);
    }

    @Override
    public void onCraftedBy(ItemStack pItemStack, Level pLevel, Player pPlayer) {
        super.onCraftedBy(pItemStack, pLevel, pPlayer);
    }
}
