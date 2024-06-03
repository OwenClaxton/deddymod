package net.deddybones.techplusplus.item.util.unused;

import net.deddybones.techplusplus.item.custom.ModArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public abstract interface IArmorMaterial {
    public abstract int getDefense(ModArmorItem.Type pType);

    public abstract Map<ModArmorItem.Type, Integer> defense();

    public abstract int enchantmentValue();

    public abstract Holder<SoundEvent> equipSound();

    public abstract Supplier<Ingredient> repairIngredient();

    public abstract List<ModArmorMaterial.Layer> layers();

    public abstract float toughness();

    public abstract float knockbackResistance();
}
