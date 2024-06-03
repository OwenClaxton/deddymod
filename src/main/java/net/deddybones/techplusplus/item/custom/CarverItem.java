package net.deddybones.techplusplus.item.custom;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class CarverItem extends AxeItem {
    public CarverItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.getDamageValue() < itemStack.getMaxDamage();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return (itemStack.getDamageValue() < itemStack.getMaxDamage())
                ? itemStack.copy() : ItemStack.EMPTY;
    }
}
