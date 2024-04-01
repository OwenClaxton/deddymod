package net.deddybones.techplusplus.util;

import com.google.common.collect.Maps;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public enum OreCollection {
    COPPER(ModItems.COPPER_NUGGET.get(), ModItems.COPPER_BILLET.get(), Items.RAW_COPPER, Items.COPPER_INGOT, Blocks.RAW_COPPER_BLOCK, Blocks.COPPER_BLOCK, true, true, true),
    TIN(ModItems.TIN_NUGGET.get(), ModItems.TIN_BILLET.get(), ModItems.RAW_TIN.get(), ModItems.TIN_INGOT.get(), ModBlocks.RAW_TIN_BLOCK.get(), ModBlocks.TIN_BLOCK.get(), true, true, true),
    GOLD(Items.GOLD_NUGGET, ModItems.GOLD_BILLET.get(), Items.RAW_GOLD, Items.GOLD_INGOT, Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK, false, true, true),
    BRONZE(ModItems.BRONZE_NUGGET.get(), ModItems.BRONZE_BILLET.get(), ModItems.RAW_BRONZE.get(), ModItems.BRONZE_INGOT.get(), ModBlocks.RAW_BRONZE_BLOCK.get(), ModBlocks.BRONZE_BLOCK.get(), false, true, true),
    IRON(Items.IRON_NUGGET, ModItems.IRON_BILLET.get(), Items.RAW_IRON, Items.IRON_INGOT, Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK, false, true, true),
    PLASTIMETAL(ModItems.PLASTIMETAL_NUGGET.get(), ModItems.PLASTIMETAL_BILLET.get(), ModItems.RAW_PLASTIMETAL.get(), ModItems.PLASTIMETAL_INGOT.get(), ModBlocks.RAW_PLASTIMETAL_BLOCK.get(), ModBlocks.PLASTIMETAL_BLOCK.get(), false, false, true),
//    NETHERITE(ModItems.NETHERITE_NUGGET.get(), ModItems.NETHERITE_BILLET.get(), ModItems.RAW_NETHERITE.get(), Items.NETHERITE_INGOT, ModBlocks.RAW_NETHERITE_BLOCK.get(), Blocks.NETHERITE_BLOCK);
    NETHERITE(null, ModItems.NETHERITE_BILLET.get(), null, Items.NETHERITE_INGOT, null, Blocks.NETHERITE_BLOCK, false, false, true);

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

    private final @Nullable Item nuggetItem;
    private final @Nullable Item billetItem;
    private final @Nullable Item rawItem;
    private final @Nullable Item ingotItem;
    private final @Nullable Block rawBlock;
    private final @Nullable Block cookedBlock;
    private final boolean kiln_okay;
    private final boolean furnace_okay;
    private final boolean forge_okay;

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
