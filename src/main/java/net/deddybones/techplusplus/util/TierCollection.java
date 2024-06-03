package net.deddybones.techplusplus.util;

import net.deddybones.techplusplus.item.util.*;
import net.deddybones.techplusplus.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public enum TierCollection {
    COP("copper",      ModToolTiers.COPPER,      () -> Ingredient.of(Items.COPPER_INGOT),               () -> ModArmorMaterials.COPPER.getHolder().orElse(null),                 EquipmentCollection.COPPER,      OreCollection.COPPER,      ComponentCollection.COPPER      ),
    TIN("tin",         ModToolTiers.TIN,         () -> Ingredient.of(ModItems.TIN_INGOT.get()),         () -> ModArmorMaterials.TIN.getHolder().orElse(null),                    EquipmentCollection.TIN,         OreCollection.TIN,         ComponentCollection.TIN         ),
    BRO("bronze",      ModToolTiers.BRONZE,      () -> Ingredient.of(ModItems.BRONZE_INGOT.get()),      () -> ModArmorMaterials.BRONZE.getHolder().orElse(null),                 EquipmentCollection.BRONZE,      OreCollection.BRONZE,      ComponentCollection.BRONZE      ),
    IRO("iron",        Tiers.IRON,               () -> Ingredient.of(Items.IRON_INGOT),                 () -> ArmorMaterials.IRON,      EquipmentCollection.IRON,        OreCollection.IRON,        ComponentCollection.IRON        ),
    GOL("gold",        Tiers.GOLD,               () -> Ingredient.of(Items.GOLD_INGOT),                 () -> ArmorMaterials.GOLD,      EquipmentCollection.GOLD,        OreCollection.GOLD,        ComponentCollection.GOLD        ),
    NET("netherite",   Tiers.NETHERITE,          () -> Ingredient.of(Items.NETHERITE_INGOT),            () -> ArmorMaterials.NETHERITE, EquipmentCollection.NETHERITE,   OreCollection.NETHERITE,   ComponentCollection.NETHERITE   ),
    PLA("plastimetal", ModToolTiers.PLASTIMETAL, () -> Ingredient.of(ModItems.PLASTIMETAL_INGOT.get()), () -> ModArmorMaterials.PLASTIMETAL.getHolder().orElse(null),            EquipmentCollection.PLASTIMETAL, OreCollection.PLASTIMETAL, ComponentCollection.PLASTIMETAL );

    private final String group;
    private final Tier tier;
    private final Supplier<Ingredient> repairIngredient;
    private final Supplier<Holder<ArmorMaterial>> material;
    private final EquipmentCollection equip;
    private final OreCollection ore;
    private final ComponentCollection comp;

    TierCollection(String pGroup, Tier pTier, Supplier<Ingredient> pRepairIngredient,
                   Supplier<Holder<ArmorMaterial>> pMaterial, EquipmentCollection pEquip,
                   OreCollection pOre, ComponentCollection pComp) {
        this.group              = pGroup;
        this.tier               = pTier;
        this.repairIngredient   = pRepairIngredient;
        this.material           = pMaterial;
        this.equip              = pEquip;
        this.ore                = pOre;
        this.comp               = pComp;
    }

    public String getGroup() {
        return group;
    }

    public Tier getTier() {
        return tier;
    }

    public Supplier<Ingredient> getRepairIngredient() {
        return repairIngredient;
    }

    public Holder<ArmorMaterial> getMaterial() {
        return material.get();
    }

    public EquipmentCollection getEquip() {
        return equip;
    }

    public OreCollection getOre() {
        return ore;
    }

    public ComponentCollection getComp() {
        return comp;
    }
}
