package net.deddybones.techplusplus.item.util.unused;

import net.deddybones.techplusplus.item.custom.ModArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AdaptorArmorMaterial implements IArmorMaterial {
    private final ArmorMaterial armorMaterial;
    private final List<ModArmorMaterial.Layer> armorLayers;

    public AdaptorArmorMaterial(Holder<ArmorMaterial> pArmorMaterial) {
        this.armorMaterial = pArmorMaterial.get();
        this.armorLayers = this.armorMaterial.layers().stream().map(
                (ArmorMaterial.Layer i) -> new ModArmorMaterial.Layer(
                        new ResourceLocation(pArmorMaterial.getRegisteredName()), i.getSuffix(), i.dyeable()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public int getDefense(ModArmorItem.Type pType) {
        return 0;
    }

    @Override
    public Map<ModArmorItem.Type, Integer> defense() {
        return null;
    }

    @Override
    public int enchantmentValue() {
        return armorMaterial.enchantmentValue();
    }

    @Override
    public Holder<SoundEvent> equipSound() {
        return armorMaterial.equipSound();
    }

    @Override
    public Supplier<Ingredient> repairIngredient() {
        return armorMaterial.repairIngredient();
    }

    @Override
    public List<ModArmorMaterial.Layer> layers() {
        return armorLayers;
    }

    @Override
    public float toughness() {
        return armorMaterial.toughness();
    }

    @Override
    public float knockbackResistance() {
        return armorMaterial.knockbackResistance();
    }
}
