package net.deddybones.techplusplus.util.forge;

import net.deddybones.techplusplus.util.EquipmentCollection.EquipmentName;
import net.deddybones.techplusplus.util.OreCollection.OreName;

@SuppressWarnings("unused")
public enum RecyclingYield {
    PICKAXE(    EquipmentName.PICKAXE,     OreName.INGOT, 3),
    AXE(        EquipmentName.AXE,         OreName.INGOT, 3),
    HOE(        EquipmentName.HOE,         OreName.INGOT, 2),
    SWORD(      EquipmentName.SWORD,       OreName.INGOT, 2),
    SHOVEL(     EquipmentName.SHOVEL,      OreName.INGOT, 1),
    HELMET(     EquipmentName.HELMET,      OreName.INGOT, 5),
    CHESTPLATE( EquipmentName.CHESTPLATE,  OreName.INGOT, 8),
    LEGGINGS(   EquipmentName.LEGGINGS,    OreName.INGOT, 7),
    BOOTS(      EquipmentName.BOOTS,       OreName.INGOT, 4),
    HORSE_ARMOR(EquipmentName.HORSE_ARMOR, OreName.INGOT, 9);

    private final EquipmentName equipmentName;
    private final OreName oreName;
    private final int oreNum;

    RecyclingYield(EquipmentName equipmentName, OreName oreName) {
        this(equipmentName, oreName, 1);
    }

    RecyclingYield(EquipmentName equipmentName, OreName oreName, int oreNum) {
        this.equipmentName = equipmentName;
        this.oreName = oreName;
        this.oreNum = oreNum;
    }

    public int getOreNum() {
        return oreNum;
    }

    public OreName getOreName() {
        return oreName;
    }

    public EquipmentName getEquipmentName() {
        return equipmentName;
    }
}
