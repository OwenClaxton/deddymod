package net.deddybones.techplusplus.util;

import net.deddybones.techplusplus.TechPlusPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        // public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
        // e.g. BlockState state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES)

        public static final TagKey<Block> TINY_ROCK_PLACEABLE_ON = tag("tiny_rock_placeable_on");
        public static final TagKey<Block> TINY_LOG_PLACEABLE_ON = tag("tiny_log_placeable_on");
        public static final TagKey<Block> NEEDS_PRIMITIVE_TOOL = tag("needs_primitive_tool");
        public static final TagKey<Block> NEEDS_COPPERTIN_TOOL = tag("needs_coppertin_tool");
        public static final TagKey<Block> NEEDS_BRONZE_TOOL = tag("needs_bronze_tool");
        public static final TagKey<Block> NEEDS_PLASTIMETAL_TOOL = tag("needs_plastimetal_tool");
        public static final TagKey<Block> PLASTIMETAL_ORES = tag("plastimetal_ores");
        public static final TagKey<Block> TIN_ORES = tag("tin_ores");
        public static final TagKey<Block> SAPPHIRE_ORES = tag("sapphire_ores");
        public static final TagKey<Block> NETHER_ORES = tag("nether_ores");
        public static final TagKey<Block> CONCRETE_BLOCKS = tag("concrete_blocks");
        public static final TagKey<Block> STONE_FAMILY = tag("stone_family");
        public static final TagKey<Block> SMOOTH_STONE_FAMILY = tag("smooth_stone_family");
        public static final TagKey<Block> STONE_BRICK_FAMILY = tag("stone_brick_family");
        public static final TagKey<Block> COBBLESTONE_FAMILY = tag("cobblestone_family");
        public static final TagKey<Block> GRANITE_FAMILY = tag("granite_family");
        public static final TagKey<Block> ANDESITE_FAMILY = tag("andesite_family");
        public static final TagKey<Block> DIORITE_FAMILY = tag("diorite_family");
        public static final TagKey<Block> BAUXITE_FAMILY = tag("bauxite_family");
        public static final TagKey<Block> DEEPSLATE_FAMILY = tag("deepslate_family");
        public static final TagKey<Block> COBBLED_DEEPSLATE_FAMILY = tag("cobbled_deepslate_family");
        public static final TagKey<Block> DEEPSLATE_BRICK_FAMILY = tag("deepslate_brick_family");
        public static final TagKey<Block> DEEPSLATE_TILE_FAMILY = tag("deepslate_tile_family");
        public static final TagKey<Block> POLISHED_DEEPSLATE_FAMILY = tag("polished_deepslate_family");
        public static final TagKey<Block> ALL_DEEPSLATE = tag("all_deepslate");
        public static final TagKey<Block> BASALT_FAMILY = tag("basalt_family");
        public static final TagKey<Block> NETHER_BRICK_FAMILY = tag("nether_brick_family");
        public static final TagKey<Block> PURPUR_FAMILY = tag("purpur_family");
        public static final TagKey<Block> BLACKSTONE_FAMILY = tag("blackstone_family");
        public static final TagKey<Block> POLISHED_BLACKSTONE_FAMILY = tag("polished_blackstone_family");
        public static final TagKey<Block> POLISHED_BLACKSTONE_BRICK_FAMILY = tag("polished_blackstone_brick_family");
        public static final TagKey<Block> ALL_BLACKSTONE = tag("all_blackstone");
        public static final TagKey<Block> END_STONE_FAMILY = tag("end_stone_family");
        public static final TagKey<Block> PLAIN_GLASS_FAMILY = tag("glass_family");
        public static final TagKey<Block> GLASS_PANES = tag("glass_panes");
        public static final TagKey<Block> GLASS_BLOCKS = tag("glass_blocks");
        public static final TagKey<Block> ALL_GLASS = tag("all_glass");
        public static final TagKey<Block> QUARTZ_FAMILY = tag("quartz_family");
        public static final TagKey<Block> STORAGE_RAW_ORE_BLOCKS = tag("storage_ore_blocks");
        public static final TagKey<Block> STORAGE_ORE_BLOCKS = tag("storage_ore_blocks");
        public static final TagKey<Block> STONE_BLOCKS = tag("stone_blocks");
        public static final TagKey<Block> ORE_BLOCKS = tag("ore_blocks");
        public static final TagKey<Block> HURTS_SMALL = tag("hurts_small");
        public static final TagKey<Block> HURTS_MEDIUM = tag("hurts_medium");
        public static final TagKey<Block> HURTS_LARGE = tag("hurts_large");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TechPlusPlus.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CAN_CARVE = tag("can_carve");
        public static final TagKey<Item> CARVED_ITEM = tag("carved_item");
        public static final TagKey<Item> CARVABLE_ITEM = tag("carvable_item");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TechPlusPlus.MOD_ID, name));
        }

    }
}
