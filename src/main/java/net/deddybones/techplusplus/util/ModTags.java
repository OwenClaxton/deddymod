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
        public static final TagKey<Block> NEEDS_PRIMITIVE_TOOL = tag("needs_primitive_tool");
        public static final TagKey<Block> NEEDS_COPPERTIN_TOOL = tag("needs_coppertin_tool");
        public static final TagKey<Block> NEEDS_BRONZE_TOOL = tag("needs_bronze_tool");
        public static final TagKey<Block> NEEDS_PLASTIMETAL_TOOL = tag("needs_plastimetal_tool");
        public static final TagKey<Block> HURTS = tag("hurts");

        public static final TagKey<Block> HARD_BLOCKS_OVERRIDE = tag("hard_blocks_override");
        public static final TagKey<Block> SOFT_BLOCKS_OVERRIDE = tag("soft_blocks_override");

        public static final TagKey<Block> TINY_ROCK_PLACEABLE_ON = tag("tiny_rock_placeable_on");
        public static final TagKey<Block> TINY_LOG_PLACEABLE_ON = tag("tiny_log_placeable_on");

        public static final TagKey<Block> PLASTIMETAL_ORES = tag("plastimetal_ores");
        public static final TagKey<Block> SAPPHIRE_ORES = tag("sapphire_ores");
        public static final TagKey<Block> TIN_ORES = tag("tin_ores");
        public static final TagKey<Block> NETHER_ORES = tag("nether_ores");

        public static final TagKey<Block> ORE_BLOCKS = tag("ore_blocks");
        public static final TagKey<Block> STORAGE_RAW_ORE_BLOCKS = tag("storage_ore_blocks");
        public static final TagKey<Block> STORAGE_ORE_BLOCKS = tag("storage_ore_blocks");
        public static final TagKey<Block> CRAFTED_COPPER_BUILDING_BLOCKS = tag("crafted_copper_building_blocks");
        public static final TagKey<Block> METAL_BUILDING_BLOCKS = tag("metal_building_blocks");

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
        public static final TagKey<Block> NETHERRACK_FAMILY = tag("netherrack_family");
        public static final TagKey<Block> DRIPSTONE_FAMILY = tag("dripstone_family");
        public static final TagKey<Block> OBSIDIAN_FAMILY = tag("obsidian_family");
        public static final TagKey<Block> CORAL_FAMILY = tag("coral_family");
        public static final TagKey<Block> PURPUR_FAMILY = tag("purpur_family");
        public static final TagKey<Block> END_STONE_FAMILY = tag("end_stone_family");
        public static final TagKey<Block> BLACKSTONE_FAMILY = tag("blackstone_family");
        public static final TagKey<Block> POLISHED_BLACKSTONE_FAMILY = tag("polished_blackstone_family");
        public static final TagKey<Block> POLISHED_BLACKSTONE_BRICK_FAMILY = tag("polished_blackstone_brick_family");
        public static final TagKey<Block> ALL_BLACKSTONE = tag("all_blackstone");
        public static final TagKey<Block> QUARTZ_FAMILY = tag("quartz_family");
        public static final TagKey<Block> AMETHYST_FAMILY = tag("amethyst_family");
        public static final TagKey<Block> TUFF_FAMILY = tag("tuff_family");
        public static final TagKey<Block> BRICK_FAMILY = tag("brick_family");
        public static final TagKey<Block> MUD_BRICK_FAMILY = tag("mud_brick_family");
        public static final TagKey<Block> SANDSTONE_FAMILY = tag("sandstone_family");
        public static final TagKey<Block> RED_SANDSTONE_FAMILY = tag("red_sandstone_family");
        public static final TagKey<Block> PRISMARINE_FAMILY = tag("prismarine_family");
        public static final TagKey<Block> TERRACOTTA_FAMILY = tag("terracotta_family");
        public static final TagKey<Block> CALCITE_FAMILY = tag("calcite_family");
        public static final TagKey<Block> STONE_BLOCKS = tag("stone_blocks");

        public static final TagKey<Block> PLAIN_GLASS_FAMILY = tag("glass_family");
        public static final TagKey<Block> GLASS_BLOCKS = tag("glass_blocks");
        public static final TagKey<Block> GLASS_PANES = tag("glass_panes");
        public static final TagKey<Block> ALL_GLASS = tag("all_glass");

        public static final TagKey<Block> ICE_FAMILY = tag("ice_family");

        public static final TagKey<Block> OAK_WOOD_CRAFTED_FAMILY = tag("oak_wood_crafted_family");
        public static final TagKey<Block> SPRUCE_WOOD_CRAFTED_FAMILY = tag("spruce_wood_crafted_family");
        public static final TagKey<Block> BIRCH_WOOD_CRAFTED_FAMILY = tag("birch_wood_crafted_family");
        public static final TagKey<Block> JUNGLE_WOOD_CRAFTED_FAMILY = tag("jungle_wood_crafted_family");
        public static final TagKey<Block> ACACIA_WOOD_CRAFTED_FAMILY = tag("acacia_wood_crafted_family");
        public static final TagKey<Block> DARK_OAK_WOOD_CRAFTED_FAMILY = tag("dark_oak_wood_crafted_family");
        public static final TagKey<Block> MANGROVE_WOOD_CRAFTED_FAMILY = tag("mangrove_wood_crafted_family");
        public static final TagKey<Block> CHERRY_WOOD_CRAFTED_FAMILY = tag("cherry_wood_crafted_family");
        public static final TagKey<Block> BAMBOO_WOOD_CRAFTED_FAMILY = tag("bamboo_wood_crafted_family");
        public static final TagKey<Block> CRIMSON_STEM_CRAFTED_FAMILY = tag("crimson_stem_crafted_family");
        public static final TagKey<Block> WARPED_STEM_CRAFTED_FAMILY = tag("warped_stem_crafted_family");
        public static final TagKey<Block> ALL_CRAFTED_WOOD_FAMILIES = tag("all_crafted_wood_families");

        public static final TagKey<Block> OAK_WOOD_FAMILY = tag("oak_wood_family");
        public static final TagKey<Block> SPRUCE_WOOD_FAMILY = tag("spruce_wood_family");
        public static final TagKey<Block> BIRCH_WOOD_FAMILY = tag("birch_wood_family");
        public static final TagKey<Block> JUNGLE_WOOD_FAMILY = tag("jungle_wood_family");
        public static final TagKey<Block> ACACIA_WOOD_FAMILY = tag("acacia_wood_family");
        public static final TagKey<Block> DARK_OAK_WOOD_FAMILY = tag("dark_oak_wood_family");
        public static final TagKey<Block> MANGROVE_WOOD_FAMILY = tag("mangrove_wood_family");
        public static final TagKey<Block> CHERRY_WOOD_FAMILY = tag("cherry_wood_family");
        public static final TagKey<Block> BAMBOO_WOOD_FAMILY = tag("bamboo_wood_family");
        public static final TagKey<Block> CRIMSON_STEM_FAMILY = tag("crimson_stem_family");
        public static final TagKey<Block> WARPED_STEM_FAMILY = tag("warped_stem_family");
        public static final TagKey<Block> ALL_WOOD_FAMILIES = tag("all_wood_families");

        public static final TagKey<Block> ROOTS_FAMILY = tag("roots_family");

        public static final TagKey<Block> FROGLIGHT_FAMILY = tag("froglight_family");
        public static final TagKey<Block> PLACED_FUNGUS_FAMILY = tag("placed_fungus_family");

        public static final TagKey<Block> SOFT_PLANTS = tag("soft_plants");
        public static final TagKey<Block> SOIL_LIKE = tag("soil_like");
        public static final TagKey<Block> HARD_PLANTS = tag("hard_plants");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TechPlusPlus.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> CAN_CARVE = tag("can_carve");
        public static final TagKey<Item> CARVED_ITEM = tag("carved_item");
        public static final TagKey<Item> CARVABLE_ITEM = tag("carvable_item");
        public static final TagKey<Item> BUCKETS = tag("buckets");
        public static final TagKey<Item> IS_A_TOOL = tag("is_a_tool");
        public static final TagKey<Item> CAN_BREAK_BLOCKS = tag("can_break_blocks");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TechPlusPlus.MOD_ID, name));
        }

    }
}
