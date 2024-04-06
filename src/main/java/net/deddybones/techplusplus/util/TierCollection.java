package net.deddybones.techplusplus.util;

import net.deddybones.techplusplus.item.ModArmorMaterials;
import net.deddybones.techplusplus.item.ModToolTiers;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Tier;

@SuppressWarnings("unused")
public enum TierCollection {
    PLA("plastimetal", ModToolTiers.PLASTIMETAL, ModArmorMaterials.PLASTIMETAL, EquipmentCollection.PLASTIMETAL, OreCollection.PLASTIMETAL ),
    IRO("iron",        Tiers.IRON,               ArmorMaterials.IRON,           EquipmentCollection.IRON,        OreCollection.IRON        ),
    GOL("gold",        Tiers.GOLD,               ArmorMaterials.GOLD,           EquipmentCollection.GOLD,        OreCollection.GOLD        ),
    NET("netherite",   Tiers.NETHERITE,          ArmorMaterials.NETHERITE,      EquipmentCollection.NETHERITE,   OreCollection.NETHERITE   ),
    COP("copper",      ModToolTiers.COPPER,      ModArmorMaterials.COPPER,      EquipmentCollection.COPPER,      OreCollection.COPPER      ),
    TIN("tin",         ModToolTiers.TIN,         ModArmorMaterials.TIN,         EquipmentCollection.TIN,         OreCollection.TIN         ),
    BRO("bronze",      ModToolTiers.BRONZE,      ModArmorMaterials.BRONZE,      EquipmentCollection.BRONZE,      OreCollection.BRONZE      );

    private final String group;
    private final Tier tier;
    private final ArmorMaterial material;
    private final EquipmentCollection equip;
    private final OreCollection ore;

    TierCollection(String pGroup, Tier pTier, ArmorMaterial pMaterial, EquipmentCollection pEquip, OreCollection pOre) {
        this.group = pGroup;
        this.tier = pTier;
        this.material = pMaterial;
        this.equip = pEquip;
        this.ore = pOre;
    }

    public int getAttackDamageExtra(EquipmentCollection.EquipmentName name) {
        return 0;
    }

    public int getAttackSpeedExtra(EquipmentCollection.EquipmentName name) {
        return 0;
    }

    public int getProtectionAmount(EquipmentCollection.EquipmentName name) {
        return 0;
    }

    public OreCollection getOre() {
        return ore;
    }

    public EquipmentCollection getEquip() {
        return equip;
    }

    public ArmorMaterial getMaterial() {
        return material;
    }

    public Tier getTier() {
        return tier;
    }

    public String getGroup() {
        return group;
    }
}
