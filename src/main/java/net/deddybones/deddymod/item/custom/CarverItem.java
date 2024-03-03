package net.deddybones.deddymod.item.custom;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class CarverItem extends AxeItem {
    public CarverItem(Tier p_40521_, float p_40522_, float p_40523_, Properties p_40524_) {
        super(p_40521_, p_40522_, p_40523_, p_40524_);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.getDamageValue() < itemStack.getMaxDamage();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return (itemStack.getDamageValue() < itemStack.getMaxDamage()) ? itemStack.copy() : ItemStack.EMPTY;
    }
}
