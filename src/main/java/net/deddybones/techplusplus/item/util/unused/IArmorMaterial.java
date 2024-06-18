package net.deddybones.techplusplus.item.util.unused;

import net.deddybones.techplusplus.item.custom.ModArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface IArmorMaterial {
    int getDefense(ModArmorItem.Type pType);

    Map<ModArmorItem.Type, Integer> defense();

    int enchantmentValue();

    Holder<SoundEvent> equipSound();

    Supplier<Ingredient> repairIngredient();

    List<ModArmorMaterial.Layer> layers();

    float toughness();

    float knockbackResistance();
}
