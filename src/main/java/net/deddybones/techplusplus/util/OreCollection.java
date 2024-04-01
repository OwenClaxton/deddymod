package net.deddybones.techplusplus.util;

import com.google.common.collect.Maps;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

@SuppressWarnings("unused")
public enum OreCollection implements net.minecraftforge.common.IExtensibleEnum {
    COPPER(ModItems.COPPER_NUGGET.get(), ModItems.COPPER_BILLET.get(), Items.RAW_COPPER, Items.COPPER_INGOT, Blocks.RAW_COPPER_BLOCK, Blocks.COPPER_BLOCK),
    TIN(ModItems.TIN_NUGGET.get(), ModItems.TIN_BILLET.get(), ModItems.RAW_TIN.get(), ModItems.TIN_INGOT.get(), ModBlocks.RAW_TIN_BLOCK.get(), ModBlocks.TIN_BLOCK.get()),
    GOLD(Items.GOLD_NUGGET, ModItems.GOLD_BILLET.get(), Items.RAW_GOLD, Items.GOLD_INGOT, Blocks.RAW_GOLD_BLOCK, Blocks.GOLD_BLOCK),
    BRONZE(ModItems.BRONZE_NUGGET.get(), ModItems.BRONZE_BILLET.get(), ModItems.RAW_BRONZE.get(), ModItems.BRONZE_INGOT.get(), ModBlocks.RAW_BRONZE_BLOCK.get(), ModBlocks.BRONZE_BLOCK.get()),
    IRON(Items.IRON_NUGGET, ModItems.IRON_BILLET.get(), Items.RAW_IRON, Items.IRON_INGOT, Blocks.RAW_IRON_BLOCK, Blocks.IRON_BLOCK),
    PLASTIMETAL(ModItems.PLASTIMETAL_NUGGET.get(), ModItems.PLASTIMETAL_BILLET.get(), ModItems.RAW_PLASTIMETAL.get(), ModItems.PLASTIMETAL_INGOT.get(), ModBlocks.RAW_PLASTIMETAL_BLOCK.get(), ModBlocks.PLASTIMETAL_BLOCK.get()),
//    NETHERITE(ModItems.NETHERITE_NUGGET.get(), ModItems.NETHERITE_BILLET.get(), ModItems.RAW_NETHERITE.get(), Items.NETHERITE_INGOT, ModBlocks.RAW_NETHERITE_BLOCK.get(), Blocks.NETHERITE_BLOCK);
    NETHERITE(null, ModItems.NETHERITE_BILLET.get(), null, Items.NETHERITE_INGOT, null, Blocks.NETHERITE_BLOCK);

    private static final Map<String, OreCollection> COLLECTION_MAP = Util.make(Maps.newHashMap(),
            (instance) -> {
                instance.put("copper",      OreCollection.COPPER);
                instance.put("tin",         OreCollection.TIN);
                instance.put("gold",        OreCollection.GOLD);
                instance.put("bronze",      OreCollection.BRONZE);
                instance.put("iron",        OreCollection.IRON);
                instance.put("plastimetal", OreCollection.PLASTIMETAL);
                instance.put("netherite",   OreCollection.NETHERITE);
            });

    private final @Nullable Item nuggetItem;
    private final @Nullable Item billetItem;
    private final @Nullable Item rawItem;
    private final @Nullable Item ingotItem;
    private final @Nullable Block rawBlock;
    private final @Nullable Block cookedBlock;

    OreCollection(@Nullable Item nuggetItem, @Nullable Item billetItem, @Nullable Item rawItem, @Nullable Item ingotItem, @Nullable Block rawBlock, @Nullable Block cookedBlock) {
        this.nuggetItem = nuggetItem;
        this.billetItem = billetItem;
        this.rawItem = rawItem;
        this.ingotItem = ingotItem;
        this.rawBlock = rawBlock;
        this.cookedBlock = cookedBlock;
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
}
