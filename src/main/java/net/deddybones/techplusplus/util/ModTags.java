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
