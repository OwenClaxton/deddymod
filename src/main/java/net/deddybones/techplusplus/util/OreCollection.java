package net.deddybones.techplusplus.util;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public enum OreCollection {
    COPPER(     true, true, true),
    TIN(        true, true, true),
    GOLD(       false, true, true),
    BRONZE(     false, true, true),
    IRON(       false, true, true),
    PLASTIMETAL(false, false, true),
    NETHERITE(  false, false, true);

    public static final Map<String, OreCollection> COLLECTION_MAP = Util.make(Maps.newHashMap(),
            (instance) -> {
                instance.put("copper",      OreCollection.COPPER);
                instance.put("tin",         OreCollection.TIN);
                instance.put("gold",        OreCollection.GOLD);
                instance.put("bronze",      OreCollection.BRONZE);
                instance.put("iron",        OreCollection.IRON);
                instance.put("plastimetal", OreCollection.PLASTIMETAL);
                instance.put("netherite",   OreCollection.NETHERITE);
            });

    public static final Map<OreName, Function<OreCollection, ItemLike>> ORE_GETTERS = Map.ofEntries(
            Map.entry(OreName.NUGGET,       OreCollection::getNuggetItem),
            Map.entry(OreName.BILLET,       OreCollection::getBilletItem),
            Map.entry(OreName.RAW_ITEM,     OreCollection::getRawItem),
            Map.entry(OreName.INGOT,        OreCollection::getIngotItem),
            Map.entry(OreName.RAW_BLOCK,    OreCollection::getRawBlock),
            Map.entry(OreName.COOKED_BLOCK, OreCollection::getCookedBlock)
    );

    public enum OreName {
        NUGGET, BILLET, RAW_ITEM, INGOT, RAW_BLOCK, COOKED_BLOCK;
    }

    private @Nullable Item nuggetItem;
    private @Nullable Item billetItem;
    private @Nullable Item rawItem;
    private @Nullable Item ingotItem;
    private @Nullable Block rawBlock;
    private @Nullable Block cookedBlock;
    private final boolean kiln_okay;
    private final boolean furnace_okay;
    private final boolean forge_okay;

    OreCollection() {
        this(null, null, null, null, null, null, true, true, true);
    }

    OreCollection(boolean kiln_okay, boolean furnace_okay, boolean forge_okay) {
        this(null, null, null, null, null, null, kiln_okay, furnace_okay, forge_okay);
    }

    OreCollection(@Nullable Item nuggetItem, @Nullable Item billetItem, @Nullable Item rawItem, @Nullable Item ingotItem,
                  @Nullable Block rawBlock, @Nullable Block cookedBlock) {
        this(nuggetItem, billetItem, rawItem, ingotItem, rawBlock, cookedBlock, true, true, true);
    }

    OreCollection(@Nullable Item nuggetItem, @Nullable Item billetItem, @Nullable Item rawItem, @Nullable Item ingotItem,
                  @Nullable Block rawBlock, @Nullable Block cookedBlock,
                  boolean kiln_okay, boolean furnace_okay, boolean forge_okay ) {
        this.nuggetItem = nuggetItem;
        this.billetItem = billetItem;
        this.rawItem = rawItem;
        this.ingotItem = ingotItem;
        this.rawBlock = rawBlock;
        this.cookedBlock = cookedBlock;
        this.kiln_okay = kiln_okay;
        this.furnace_okay = furnace_okay;
        this.forge_okay = forge_okay;
    }

    public @Nullable OreCollection byGroup(@NotNull String group) {
        return COLLECTION_MAP.getOrDefault(group, null);
    }

    public void setAllItemLikes(@Nullable Item nuggetItem, @Nullable Item billetItem, @Nullable Item rawItem, @Nullable Item ingotItem,
                                @Nullable Block rawBlock, @Nullable Block cookedBlock) {
        this.nuggetItem = nuggetItem;
        this.billetItem = billetItem;
        this.rawItem = rawItem;
        this.ingotItem = ingotItem;
        this.rawBlock = rawBlock;
        this.cookedBlock = cookedBlock;
    }

    public @Nullable Item getNuggetItem() {
        return this.nuggetItem;
    }

    public @Nullable Item getBilletItem() {
        return this.billetItem;
    }

    public @Nullable Item getRawItem() {
        return this.rawItem;
    }

    public @Nullable Item getIngotItem() {
        return this.ingotItem;
    }

    public @Nullable Block getRawBlock() {
        return this.rawBlock;
    }

    public @Nullable Block getCookedBlock() {
        return this.cookedBlock;
    }

    public boolean kilnOK() {
        return this.kiln_okay;
    }

    public boolean furnaceOK() {
        return this.furnace_okay;
    }

    public boolean forgeOK() {
        return this.forge_okay;
    }
}
