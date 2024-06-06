package net.deddybones.techplusplus.util;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public enum EquipmentCollection {
    COPPER(),
    TIN(),
    GOLD(),
    BRONZE(),
    IRON(),
    PLASTIMETAL(),
    NETHERITE();

    private @Nullable ItemLike pickaxeItem;
    private @Nullable ItemLike axeItem;
    private @Nullable ItemLike hoeItem;
    private @Nullable ItemLike swordItem;
    private @Nullable ItemLike shovelItem;
    private @Nullable ItemLike helmetItem;
    private @Nullable ItemLike chestplateItem;
    private @Nullable ItemLike leggingsItem;
    private @Nullable ItemLike bootsItem;
    private @Nullable ItemLike horseArmorItem;

    public static final Map<String, EquipmentCollection> COLLECTION_MAP = Util.make(Maps.newHashMap(),
            (instance) -> {
                instance.put("copper",      EquipmentCollection.COPPER);
                instance.put("tin",         EquipmentCollection.TIN);
                instance.put("gold",        EquipmentCollection.GOLD);
                instance.put("bronze",      EquipmentCollection.BRONZE);
                instance.put("iron",        EquipmentCollection.IRON);
                instance.put("plastimetal", EquipmentCollection.PLASTIMETAL);
                instance.put("netherite",   EquipmentCollection.NETHERITE);
            });

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

    public final List<EquipmentName> TOOLS = List.of(EquipmentName.PICKAXE, EquipmentName.AXE, EquipmentName.HOE,
            EquipmentName.SWORD, EquipmentName.SHOVEL);

    public enum EquipmentName {
        PICKAXE, AXE, HOE, SWORD, SHOVEL, HELMET, CHESTPLATE, LEGGINGS, BOOTS, HORSE_ARMOR;
    }

    EquipmentCollection() {
        this.pickaxeItem    = null;
        this.axeItem        = null;
        this.hoeItem        = null;
        this.swordItem      = null;
        this.shovelItem     = null;
        this.helmetItem     = null;
        this.chestplateItem = null;
        this.leggingsItem   = null;
        this.bootsItem      = null;
        this.horseArmorItem = null;
    }

    EquipmentCollection(@Nullable Item swordItem, @Nullable Item axeItem, @Nullable Item hoeItem,
                        @Nullable Item pickaxeItem, @Nullable Item shovelItem, @Nullable Item helmetItem,
                        @Nullable Item chestplateItem, @Nullable Item leggingsItem, @Nullable Item bootsItem,
                        @Nullable Item horseArmorItem) {
        this.pickaxeItem    = pickaxeItem;
        this.axeItem        = axeItem;
        this.hoeItem        = hoeItem;
        this.swordItem      = swordItem;
        this.shovelItem     = shovelItem;
        this.helmetItem     = helmetItem;
        this.chestplateItem = chestplateItem;
        this.leggingsItem   = leggingsItem;
        this.bootsItem      = bootsItem;
        this.horseArmorItem = horseArmorItem;
    }

    public void setAllItemLikes(@Nullable ItemLike swordItem, @Nullable ItemLike axeItem,
                                @Nullable ItemLike hoeItem, @Nullable ItemLike pickaxeItem,
                                @Nullable ItemLike shovelItem, @Nullable ItemLike helmetItem,
                                @Nullable ItemLike chestplateItem, @Nullable ItemLike leggingsItem,
                                @Nullable ItemLike bootsItem, @Nullable ItemLike horseArmorItem) {
        this.pickaxeItem    = pickaxeItem;
        this.axeItem        = axeItem;
        this.hoeItem        = hoeItem;
        this.swordItem      = swordItem;
        this.shovelItem     = shovelItem;
        this.helmetItem     = helmetItem;
        this.chestplateItem = chestplateItem;
        this.leggingsItem   = leggingsItem;
        this.bootsItem      = bootsItem;
        this.horseArmorItem = horseArmorItem;
    }

    public @Nullable ItemLike getArmorItem(EquipmentName armorName) {
        return switch(armorName) {
            case HELMET -> this.getHelmetItem();
            case CHESTPLATE -> this.getChestplateItem();
            case LEGGINGS -> this.getLeggingsItem();
            case BOOTS -> this.getBootsItem();
            case HORSE_ARMOR -> this.getHorseArmorItem();
            default -> null;
        };
    }

    public @Nullable ItemLike getHelmetItem() {
        return this.helmetItem;
    }

    public @Nullable ItemLike getChestplateItem() {
        return this.chestplateItem;
    }

    public @Nullable ItemLike getLeggingsItem() {
        return this.leggingsItem;
    }

    public @Nullable ItemLike getBootsItem() {
        return this.bootsItem;
    }

    public @Nullable ItemLike getToolItem(EquipmentName toolName) {
        return switch(toolName) {
            case SWORD -> this.getSwordItem();
            case SHOVEL -> this.getShovelItem();
            case PICKAXE -> this.getPickaxeItem();
            case AXE -> this.getAxeItem();
            case HOE -> this.getHoeItem();
            default -> null;
        };
    }

    public @Nullable ItemLike getSwordItem() {
        return this.swordItem;
    }

    public @Nullable ItemLike getAxeItem() {
        return this.axeItem;
    }

    public @Nullable ItemLike getHoeItem() {
        return this.hoeItem;
    }

    public @Nullable ItemLike getPickaxeItem() {
        return this.pickaxeItem;
    }

    public @Nullable ItemLike getShovelItem() {
        return this.shovelItem;
    }

    public @Nullable ItemLike getHorseArmorItem() {
        return this.horseArmorItem;
    }
}
