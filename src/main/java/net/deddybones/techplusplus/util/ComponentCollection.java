package net.deddybones.techplusplus.util;

import net.deddybones.techplusplus.util.EquipmentCollection.EquipmentName;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public enum ComponentCollection {
    COPPER(),
    TIN(),
    GOLD(),
    BRONZE(),
    IRON(),
    PLASTIMETAL(),
    NETHERITE();

    public static final Map<String, ComponentCollection> COMPONENT_MAP = Util.make(Maps.newHashMap(),
            (instance) -> {
                instance.put("copper",      ComponentCollection.COPPER);
                instance.put("tin",         ComponentCollection.TIN);
                instance.put("gold",        ComponentCollection.GOLD);
                instance.put("bronze",      ComponentCollection.BRONZE);
                instance.put("iron",        ComponentCollection.IRON);
                instance.put("plastimetal", ComponentCollection.PLASTIMETAL);
                instance.put("netherite",   ComponentCollection.NETHERITE);
            });

    public static final Map<ComponentName, Function<ComponentCollection, ItemLike>> ORE_GETTERS = Map.ofEntries(
            Map.entry(ComponentName.ROD,          ComponentCollection::getRodItem),
            Map.entry(ComponentName.FLAT_PANEL,   ComponentCollection::getFlatPanelItem),
            Map.entry(ComponentName.ROUND_PANEL,  ComponentCollection::getRoundPanelItem),
            Map.entry(ComponentName.DISK,         ComponentCollection::getDiskItem),
            Map.entry(ComponentName.PICKAXE_PART, ComponentCollection::getPickaxePartItem),
            Map.entry(ComponentName.AXE_PART,     ComponentCollection::getAxePartItem),
            Map.entry(ComponentName.SWORD_PARTS,  ComponentCollection::getSwordPartsItem),
            Map.entry(ComponentName.HOE_PART,     ComponentCollection::getHoePartItem),
            Map.entry(ComponentName.SHOVEL_PART,  ComponentCollection::getShovelPartItem)
    );

    public enum ComponentName {
        ROD, FLAT_PANEL, ROUND_PANEL, DISK, PICKAXE_PART, AXE_PART, SWORD_PARTS, HOE_PART, SHOVEL_PART;
    }

    private @Nullable ItemLike rodItem;
    private @Nullable ItemLike flatPanelItem;
    private @Nullable ItemLike roundPanelItem;
    private @Nullable ItemLike diskItem;
    private @Nullable ItemLike pickaxePartItem;
    private @Nullable ItemLike axePartItem;
    private @Nullable ItemLike swordPartsItem;
    private @Nullable ItemLike hoePartItem;
    private @Nullable ItemLike shovelPartItem;

    ComponentCollection() {
        this(null, null, null, null, null, null, null, null, null);
    }

    ComponentCollection(@Nullable ItemLike rodItem, @Nullable ItemLike flatPanelItem, @Nullable ItemLike roundPanelItem,
                        @Nullable ItemLike diskItem, @Nullable ItemLike pickaxePartItem, @Nullable ItemLike axePartItem,
                        @Nullable ItemLike swordPartsItem, @Nullable ItemLike hoePartItem, @Nullable ItemLike shovelPartItem) {
        this.rodItem         = rodItem;
        this.flatPanelItem   = flatPanelItem;
        this.roundPanelItem  = roundPanelItem;
        this.diskItem        = diskItem;
        this.pickaxePartItem = pickaxePartItem;
        this.axePartItem     = axePartItem;
        this.swordPartsItem  = swordPartsItem;
        this.hoePartItem     = hoePartItem;
        this.shovelPartItem  = shovelPartItem;
    }

    public @Nullable ComponentCollection byGroup(@NotNull String group) {
        return COMPONENT_MAP.getOrDefault(group, null);
    }

    public void setAllItemLikes(@Nullable ItemLike rodItem, @Nullable ItemLike flatPanelItem, @Nullable ItemLike roundPanelItem,
                                @Nullable ItemLike diskItem, @Nullable ItemLike pickaxePartItem, @Nullable ItemLike axePartItem,
                                @Nullable ItemLike swordPartsItem, @Nullable ItemLike hoePartItem, @Nullable ItemLike shovelPartItem) {
        this.rodItem         = rodItem;
        this.flatPanelItem   = flatPanelItem;
        this.roundPanelItem  = roundPanelItem;
        this.diskItem        = diskItem;
        this.pickaxePartItem = pickaxePartItem;
        this.axePartItem     = axePartItem;
        this.swordPartsItem  = swordPartsItem;
        this.hoePartItem     = hoePartItem;
        this.shovelPartItem  = shovelPartItem;
    }

    public @Nullable ItemLike getToolPartItem(EquipmentName toolName) {
        return switch(toolName) {
            case SWORD -> this.getSwordPartsItem();
            case SHOVEL -> this.getShovelPartItem();
            case PICKAXE -> this.getPickaxePartItem();
            case AXE -> this.getAxePartItem();
            case HOE -> this.getHoePartItem();
            default -> null;
        };
    }

    public void setRodItem(@Nullable ItemLike pItem) {
        this.rodItem = pItem;
    }

    public @Nullable ItemLike getRodItem() {
        return this.rodItem;
    }

    public void setFlatPanelItem(@Nullable ItemLike pItem) {
        this.flatPanelItem = pItem;
    }

    public @Nullable ItemLike getFlatPanelItem() {
        return this.flatPanelItem;
    }

    public void setRoundPanelItem(@Nullable ItemLike pItem) {
        this.roundPanelItem = pItem;
    }

    public @Nullable ItemLike getRoundPanelItem() {
        return this.roundPanelItem;
    }

    public void setDiskItem(@Nullable ItemLike pItem) {
        this.diskItem = pItem;
    }

    public @Nullable ItemLike getDiskItem() {
        return this.diskItem;
    }

    public void setPickaxePartItem(@Nullable ItemLike pItem) {
        this.pickaxePartItem = pItem;
    }

    public @Nullable ItemLike getPickaxePartItem() {
        return this.pickaxePartItem;
    }

    public void setAxePartItem(@Nullable ItemLike pItem) {
        this.axePartItem = pItem;
    }

    public @Nullable ItemLike getAxePartItem() {
        return this.axePartItem;
    }

    public void setSwordPartsItem(@Nullable ItemLike pItem) {
        this.swordPartsItem = pItem;
    }

    public @Nullable ItemLike getSwordPartsItem() {
        return this.swordPartsItem;
    }

    public void setHoePartItem(@Nullable ItemLike pItem) {
        this.hoePartItem = pItem;
    }

    public @Nullable ItemLike getHoePartItem() {
        return this.hoePartItem;
    }

    public void setShovelPartItem(@Nullable ItemLike pItem) {
        this.shovelPartItem = pItem;
    }

    public @Nullable ItemLike getShovelPartItem() {
        return this.shovelPartItem;
    }
}

