package net.deddybones.techplusplus.item.util;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.util.TierCollection;
import net.deddybones.techplusplus.util.TierNumerics;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMORS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, TechPlusPlus.MOD_ID);

    public static final RegistryObject<ArmorMaterial> PLASTIMETAL;
    public static final RegistryObject<ArmorMaterial> BRONZE;
    public static final RegistryObject<ArmorMaterial> COPPER;
    public static final RegistryObject<ArmorMaterial> TIN;

    public ModArmorMaterials() {
    }

    static {
        PLASTIMETAL = registerArmor(TierCollection.PLA);
        BRONZE = registerArmor(TierCollection.BRO);
        COPPER = registerArmor(TierCollection.COP);
        TIN = registerArmor(TierCollection.TIN);
    }

    private static RegistryObject<ArmorMaterial> registerArmor(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        EnumMap<ArmorItem.Type, Integer> pEnumMap = Util.make(new EnumMap<>(ArmorItem.Type.class), enumMap -> {
            enumMap.put(ArmorItem.Type.BOOTS, tierNumerics.getBootsProtection());
            enumMap.put(ArmorItem.Type.LEGGINGS, tierNumerics.getLeggingsProtection());
            enumMap.put(ArmorItem.Type.CHESTPLATE, tierNumerics.getChestplateProtection());
            enumMap.put(ArmorItem.Type.HELMET, tierNumerics.getHelmetProtection());
            enumMap.put(ArmorItem.Type.BODY, tierNumerics.getBodyProtection());
        });
        String pGroup = pTierColl.getGroup();
        List<ArmorMaterial.Layer> pLayerList = List.of(new ModArmorLayer(new ResourceLocation(pGroup)));
        return registerArmor(pGroup, pEnumMap, tierNumerics.getArmorEnchantmentValue(),
                        SoundEvents.ARMOR_EQUIP_IRON, pTierColl.getRepairIngredient(), pLayerList,
                        tierNumerics.getToughness(), tierNumerics.getKnockbackResistance());
    }
    @SuppressWarnings("SameParameterValue")
    private static RegistryObject<ArmorMaterial> registerArmor( String pGroup, EnumMap<ArmorItem.Type, Integer> pEnumMap,
                                                                int pEnchantmentValue, Holder<SoundEvent> pEquipSound,
                                                                Supplier<Ingredient> pRepairIngredient,
                                                                List<ArmorMaterial.Layer> pLayerList, float pToughness,
                                                                float pKnockbackResistance) {
        return ARMORS.register(pGroup,
            () -> new ArmorMaterial(pEnumMap, pEnchantmentValue, pEquipSound, pRepairIngredient,
                    pLayerList, pToughness, pKnockbackResistance));

    }

    public static void register(IEventBus eventBus) {
        ARMORS.register(eventBus);
    }
}
