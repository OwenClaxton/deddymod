package net.deddybones.techplusplus.util;

import net.deddybones.techplusplus.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public enum EquipmentCollection {
//    COPPER(ModItems.COPPER_SWORD.get(), ModItems.COPPER_AXE.get(), ModItems.COPPER_HOE.get(), ModItems.COPPER_PICKAXE.get(), ModItems.COPPER_SHOVEL.get(),
//            ModItems.COPPER_HELMET.get(), ModItems.COPPER_CHESTPLATE.get(), ModItems.COPPER_LEGGINGS.get(), ModItems.COPPER_BOOTS.get(), ModItems.COPPER_HORSE_ARMOR.get()),
    COPPER(),
//    TIN(ModItems.TIN_SWORD.get(), ModItems.TIN_AXE.get(), ModItems.TIN_HOE.get(), ModItems.TIN_PICKAXE.get(), ModItems.TIN_SHOVEL.get(),
//            ModItems.TIN_HELMET.get(), ModItems.TIN_CHESTPLATE.get(), ModItems.TIN_LEGGINGS.get(), ModItems.TIN_BOOTS.get(), ModItems.TIN_HORSE_ARMOR.get()),
    TIN(),
    GOLD(Items.GOLDEN_SWORD, Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL,
            Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR),
//    BRONZE(ModItems.BRONZE_SWORD.get(), ModItems.BRONZE_AXE.get(), ModItems.BRONZE_HOE.get(), ModItems.BRONZE_PICKAXE.get(), ModItems.BRONZE_SHOVEL.get(),
//            ModItems.BRONZE_HELMET.get(), ModItems.BRONZE_CHESTPLATE.get(), ModItems.BRONZE_LEGGINGS.get(), ModItems.BRONZE_BOOTS.get(), ModItems.BRONZE_HORSE_ARMOR.get()),
    BRONZE(),
    IRON(Items.IRON_SWORD, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_PICKAXE, Items.IRON_SHOVEL,
            Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR),
    PLASTIMETAL(ModItems.PLASTIMETAL_SWORD.get(), ModItems.PLASTIMETAL_AXE.get(), ModItems.PLASTIMETAL_HOE.get(), ModItems.PLASTIMETAL_PICKAXE.get(), ModItems.PLASTIMETAL_SHOVEL.get(),
            ModItems.PLASTIMETAL_HELMET.get(), ModItems.PLASTIMETAL_CHESTPLATE.get(), ModItems.PLASTIMETAL_LEGGINGS.get(), ModItems.PLASTIMETAL_BOOTS.get(), ModItems.PLASTIMETAL_HORSE_ARMOR.get()),
    NETHERITE(Items.NETHERITE_SWORD, Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL,
//            Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS, ModItems.NETHERITE_HORSE_ARMOR.get());
            Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS, null);

    private final @Nullable Item pickaxeItem;
    private final @Nullable Item axeItem;
    private final @Nullable Item hoeItem;
    private final @Nullable Item swordItem;
    private final @Nullable Item shovelItem;
    private final @Nullable Item helmetItem;
    private final @Nullable Item chestplateItem;
    private final @Nullable Item leggingsItem;
    private final @Nullable Item bootsItem;
    private final @Nullable Item horseArmorItem;

    private static final Map<EquipmentName, Function<EquipmentCollection, ItemLike>> EQUIPMENT_GETTERS = Map.ofEntries(
            Map.entry(EquipmentName.PICKAXE,     EquipmentCollection::getPickaxeItem),
            Map.entry(EquipmentName.AXE,         EquipmentCollection::getAxeItem),
            Map.entry(EquipmentName.HOE,         EquipmentCollection::getHoeItem),
            Map.entry(EquipmentName.SWORD,       EquipmentCollection::getSwordItem),
            Map.entry(EquipmentName.SHOVEL,      EquipmentCollection::getShovelItem),
            Map.entry(EquipmentName.HELMET,      EquipmentCollection::getHelmetItem),
            Map.entry(EquipmentName.CHESTPLATE,  EquipmentCollection::getChestplateItem),
            Map.entry(EquipmentName.LEGGINGS,    EquipmentCollection::getLeggingsItem),
            Map.entry(EquipmentName.BOOTS,       EquipmentCollection::getBootsItem),
            Map.entry(EquipmentName.HORSE_ARMOR, EquipmentCollection::getHorseArmorItem)
    );

    public enum EquipmentName {
        PICKAXE, AXE, HOE, SWORD, SHOVEL, HELMET, CHESTPLATE, LEGGINGS, BOOTS, HORSE_ARMOR;
    }

    EquipmentCollection() {
        this.pickaxeItem = null;
        this.axeItem = null;
        this.hoeItem = null;
        this.swordItem = null;
        this.shovelItem = null;
        this.helmetItem = null;
        this.chestplateItem = null;
        this.leggingsItem = null;
        this.bootsItem = null;
        this.horseArmorItem = null;
    }

    EquipmentCollection(@Nullable Item swordItem, @Nullable Item axeItem,
                   @Nullable Item hoeItem, @Nullable Item pickaxeItem, @Nullable Item shovelItem,
                   @Nullable Item helmetItem, @Nullable Item chestplateItem, @Nullable Item leggingsItem, @Nullable Item bootsItem,
                   @Nullable Item horseArmorItem) {
        this.pickaxeItem = pickaxeItem;
        this.axeItem = axeItem;
        this.hoeItem = hoeItem;
        this.swordItem = swordItem;
        this.shovelItem = shovelItem;
        this.helmetItem = helmetItem;
        this.chestplateItem = chestplateItem;
        this.leggingsItem = leggingsItem;
        this.bootsItem = bootsItem;
        this.horseArmorItem = horseArmorItem;
    }

    public @Nullable Item getHelmetItem() {
        return this.helmetItem;
    }

    public @Nullable Item getChestplateItem() {
        return this.chestplateItem;
    }

    public @Nullable Item getLeggingsItem() {
        return this.leggingsItem;
    }

    public @Nullable Item getBootsItem() {
        return this.bootsItem;
    }

    public @Nullable Item getSwordItem() {
        return this.swordItem;
    }

    public @Nullable Item getAxeItem() {
        return this.axeItem;
    }

    public @Nullable Item getHoeItem() {
        return this.hoeItem;
    }

    public @Nullable Item getPickaxeItem() {
        return this.pickaxeItem;
    }

    public @Nullable Item getShovelItem() {
        return this.shovelItem;
    }

    public @Nullable Item getHorseArmorItem() {
        return this.horseArmorItem;
    }
}
