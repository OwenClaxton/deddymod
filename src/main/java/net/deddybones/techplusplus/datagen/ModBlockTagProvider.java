package net.deddybones.techplusplus.datagen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TechPlusPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {

        this.tag(ModTags.Blocks.TINY_LOG_PLACEABLE_ON)
                .addTag(Tags.Blocks.GRAVEL)
                .addTag(BlockTags.TERRACOTTA)
                .addTag(BlockTags.DIRT);

        this.tag(ModTags.Blocks.TINY_ROCK_PLACEABLE_ON)
                .addTag(BlockTags.STONE_ORE_REPLACEABLES)
                .addTag(BlockTags.SAND)
                .addTag(Tags.Blocks.ORES)
                .addTag(ModTags.Blocks.TINY_LOG_PLACEABLE_ON);

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.GLASS_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.GLASS_FENCE_GATE.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.GLASS_WALL.get());

        this.tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON)
                .add(ModBlocks.TINY_ROCK_BLOCK.get())
                .add(ModBlocks.TINY_LOG_BLOCK.get());

        this.tag(ModTags.Blocks.PLASTIMETAL_ORES)
                .add(ModBlocks.RUINED_PLASTIMETAL.get())
                .add(ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get());

        this.tag(ModTags.Blocks.SAPPHIRE_ORES)
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());

        this.tag(ModTags.Blocks.TIN_ORES)
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get());

        this.tag(ModTags.Blocks.NETHER_ORES)
                .add(Blocks.NETHER_QUARTZ_ORE)
                .add(Blocks.NETHER_GOLD_ORE)
                .add(Blocks.ANCIENT_DEBRIS);

        this.tag(ModTags.Blocks.ORE_BLOCKS)
                .addTag(BlockTags.COAL_ORES)
                .addTag(BlockTags.IRON_ORES)
                .addTag(BlockTags.COPPER_ORES)
                .addTag(BlockTags.GOLD_ORES)
                .addTag(BlockTags.LAPIS_ORES)
                .addTag(BlockTags.REDSTONE_ORES)
                .addTag(BlockTags.EMERALD_ORES)
                .addTag(BlockTags.DIAMOND_ORES)
                .addTag(ModTags.Blocks.PLASTIMETAL_ORES)
                .addTag(ModTags.Blocks.SAPPHIRE_ORES)
                .addTag(ModTags.Blocks.TIN_ORES)
                .addTag(ModTags.Blocks.NETHER_ORES);

        this.tag(ModTags.Blocks.CONCRETE_BLOCKS)
                .add(Blocks.WHITE_CONCRETE)
                .add(Blocks.LIGHT_GRAY_CONCRETE)
                .add(Blocks.GRAY_CONCRETE)
                .add(Blocks.BLACK_CONCRETE)
                .add(Blocks.BROWN_CONCRETE)
                .add(Blocks.RED_CONCRETE)
                .add(Blocks.ORANGE_CONCRETE)
                .add(Blocks.LIME_CONCRETE)
                .add(Blocks.YELLOW_CONCRETE)
                .add(Blocks.GREEN_CONCRETE)
                .add(Blocks.CYAN_CONCRETE)
                .add(Blocks.LIGHT_BLUE_CONCRETE)
                .add(Blocks.BLUE_CONCRETE)
                .add(Blocks.PURPLE_CONCRETE)
                .add(Blocks.MAGENTA_CONCRETE)
                .add(Blocks.PINK_CONCRETE);

        this.tag(ModTags.Blocks.STONE_FAMILY)
                .add(Blocks.STONE)
                .add(Blocks.STONE_STAIRS)
                .add(Blocks.STONE_SLAB)
                .add(Blocks.STONE_PRESSURE_PLATE)
                .add(Blocks.STONE_BUTTON)
                .add(Blocks.INFESTED_STONE);

        this.tag(ModTags.Blocks.SMOOTH_STONE_FAMILY)
                .add(Blocks.SMOOTH_STONE)
                .add(Blocks.SMOOTH_STONE_SLAB);

        this.tag(ModTags.Blocks.STONE_BRICK_FAMILY)
                .add(Blocks.STONE_BRICKS)
                .add(Blocks.STONE_BRICK_STAIRS)
                .add(Blocks.STONE_BRICK_SLAB)
                .add(Blocks.STONE_BRICK_WALL)
                .add(Blocks.CHISELED_STONE_BRICKS)
                .add(Blocks.MOSSY_STONE_BRICK_STAIRS)
                .add(Blocks.MOSSY_STONE_BRICK_SLAB)
                .add(Blocks.MOSSY_STONE_BRICK_WALL)
                .add(Blocks.INFESTED_STONE_BRICKS)
                .add(Blocks.INFESTED_CHISELED_STONE_BRICKS)
                .add(Blocks.INFESTED_MOSSY_STONE_BRICKS)
                .add(Blocks.INFESTED_CRACKED_STONE_BRICKS);

        this.tag(ModTags.Blocks.COBBLESTONE_FAMILY)
                .add(Blocks.COBBLESTONE)
                .add(Blocks.COBBLESTONE_STAIRS)
                .add(Blocks.COBBLESTONE_SLAB)
                .add(Blocks.COBBLESTONE_WALL)
                .add(Blocks.INFESTED_COBBLESTONE)
                .add(Blocks.MOSSY_COBBLESTONE)
                .add(Blocks.MOSSY_COBBLESTONE_STAIRS)
                .add(Blocks.MOSSY_COBBLESTONE_SLAB)
                .add(Blocks.MOSSY_COBBLESTONE_WALL);

        this.tag(ModTags.Blocks.GRANITE_FAMILY)
                .add(Blocks.GRANITE)
                .add(Blocks.GRANITE_STAIRS)
                .add(Blocks.GRANITE_SLAB)
                .add(Blocks.GRANITE_WALL)
                .add(Blocks.POLISHED_GRANITE)
                .add(Blocks.POLISHED_GRANITE_STAIRS)
                .add(Blocks.POLISHED_GRANITE_SLAB);

        this.tag(ModTags.Blocks.ANDESITE_FAMILY)
                .add(Blocks.ANDESITE)
                .add(Blocks.ANDESITE_STAIRS)
                .add(Blocks.ANDESITE_SLAB)
                .add(Blocks.ANDESITE_WALL)
                .add(Blocks.POLISHED_ANDESITE)
                .add(Blocks.POLISHED_ANDESITE_STAIRS)
                .add(Blocks.POLISHED_ANDESITE_SLAB);

        this.tag(ModTags.Blocks.DIORITE_FAMILY)
                .add(Blocks.DIORITE)
                .add(Blocks.DIORITE_STAIRS)
                .add(Blocks.DIORITE_SLAB)
                .add(Blocks.DIORITE_WALL)
                .add(Blocks.POLISHED_DIORITE)
                .add(Blocks.POLISHED_DIORITE_STAIRS)
                .add(Blocks.POLISHED_DIORITE_SLAB);

        this.tag(ModTags.Blocks.BAUXITE_FAMILY)
                .add(ModBlocks.BAUXITE.get())
                .add(ModBlocks.POLISHED_BAUXITE.get());

        this.tag(ModTags.Blocks.DEEPSLATE_FAMILY)
                .add(Blocks.DEEPSLATE)
                .add(Blocks.INFESTED_DEEPSLATE);

        this.tag(ModTags.Blocks.COBBLED_DEEPSLATE_FAMILY)
                .add(Blocks.COBBLED_DEEPSLATE)
                .add(Blocks.COBBLED_DEEPSLATE_STAIRS)
                .add(Blocks.COBBLED_DEEPSLATE_SLAB)
                .add(Blocks.COBBLED_DEEPSLATE_WALL);

        this.tag(ModTags.Blocks.DEEPSLATE_BRICK_FAMILY)
                .add(Blocks.DEEPSLATE_BRICKS)
                .add(Blocks.CRACKED_DEEPSLATE_BRICKS)
                .add(Blocks.DEEPSLATE_BRICK_STAIRS)
                .add(Blocks.DEEPSLATE_BRICK_SLAB)
                .add(Blocks.DEEPSLATE_BRICK_WALL);

        this.tag(ModTags.Blocks.DEEPSLATE_TILE_FAMILY)
                .add(Blocks.DEEPSLATE_TILES)
                .add(Blocks.CRACKED_DEEPSLATE_TILES)
                .add(Blocks.DEEPSLATE_TILE_STAIRS)
                .add(Blocks.DEEPSLATE_TILE_SLAB)
                .add(Blocks.DEEPSLATE_TILE_WALL);

        this.tag(ModTags.Blocks.POLISHED_DEEPSLATE_FAMILY)
                .add(Blocks.POLISHED_DEEPSLATE)
                .add(Blocks.POLISHED_DEEPSLATE_STAIRS)
                .add(Blocks.POLISHED_DEEPSLATE_SLAB)
                .add(Blocks.POLISHED_DEEPSLATE_WALL);

        this.tag(ModTags.Blocks.ALL_DEEPSLATE)
                .addTag(ModTags.Blocks.DEEPSLATE_FAMILY)
                .addTag(ModTags.Blocks.COBBLED_DEEPSLATE_FAMILY)
                .addTag(ModTags.Blocks.DEEPSLATE_BRICK_FAMILY)
                .addTag(ModTags.Blocks.DEEPSLATE_TILE_FAMILY)
                .addTag(ModTags.Blocks.POLISHED_DEEPSLATE_FAMILY)
                .add(Blocks.CHISELED_DEEPSLATE)
                .add(Blocks.REINFORCED_DEEPSLATE);

        this.tag(ModTags.Blocks.BASALT_FAMILY)
                .add(Blocks.BASALT)
                .add(Blocks.POLISHED_BASALT)
                .add(Blocks.SMOOTH_BASALT);

        this.tag(ModTags.Blocks.NETHER_BRICK_FAMILY)
                .add(Blocks.NETHER_BRICKS)
                .add(Blocks.NETHER_BRICK_SLAB)
                .add(Blocks.NETHER_BRICK_STAIRS)
                .add(Blocks.NETHER_BRICK_WALL)
                .add(Blocks.CRACKED_NETHER_BRICKS)
                .add(Blocks.CHISELED_NETHER_BRICKS)
                .add(Blocks.NETHER_BRICK_FENCE)
                .add(Blocks.RED_NETHER_BRICKS)
                .add(Blocks.RED_NETHER_BRICK_SLAB)
                .add(Blocks.RED_NETHER_BRICK_STAIRS)
                .add(Blocks.RED_NETHER_BRICK_WALL);

        this.tag(ModTags.Blocks.NETHERRACK_FAMILY)
                .add(Blocks.NETHERRACK)
                .addTag(BlockTags.NYLIUM);

        this.tag(ModTags.Blocks.PURPUR_FAMILY)
                .add(Blocks.PURPUR_BLOCK)
                .add(Blocks.PURPUR_STAIRS)
                .add(Blocks.PURPUR_SLAB)
                .add(Blocks.PURPUR_PILLAR);

        this.tag(ModTags.Blocks.END_STONE_FAMILY)
                .add(Blocks.END_STONE)
                .add(Blocks.END_STONE_BRICKS)
                .add(Blocks.END_STONE_BRICK_STAIRS)
                .add(Blocks.END_STONE_BRICK_SLAB)
                .add(Blocks.END_STONE_BRICK_WALL);

        this.tag(ModTags.Blocks.BLACKSTONE_FAMILY)
                .add(Blocks.BLACKSTONE)
                .add(Blocks.GILDED_BLACKSTONE)
                .add(Blocks.BLACKSTONE_STAIRS)
                .add(Blocks.BLACKSTONE_SLAB)
                .add(Blocks.BLACKSTONE_WALL);

        this.tag(ModTags.Blocks.POLISHED_BLACKSTONE_FAMILY)
                .add(Blocks.CHISELED_POLISHED_BLACKSTONE)
                .add(Blocks.POLISHED_BLACKSTONE)
                .add(Blocks.POLISHED_BLACKSTONE_STAIRS)
                .add(Blocks.POLISHED_BLACKSTONE_SLAB)
                .add(Blocks.POLISHED_BLACKSTONE_WALL)
                .add(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE)
                .add(Blocks.POLISHED_BLACKSTONE_BUTTON);

        this.tag(ModTags.Blocks.POLISHED_BLACKSTONE_BRICK_FAMILY)
                .add(Blocks.POLISHED_BLACKSTONE_BRICKS)
                .add(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
                .add(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .add(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB)
                .add(Blocks.POLISHED_BLACKSTONE_BRICK_WALL);

        this.tag(ModTags.Blocks.ALL_BLACKSTONE)
                .addTag(ModTags.Blocks.BLACKSTONE_FAMILY)
                .addTag(ModTags.Blocks.POLISHED_BLACKSTONE_FAMILY)
                .addTag(ModTags.Blocks.POLISHED_BLACKSTONE_BRICK_FAMILY);

        this.tag(ModTags.Blocks.PLAIN_GLASS_FAMILY)
                .add(Blocks.GLASS)
                .add(ModBlocks.GLASS_SLAB.get())
                .add(ModBlocks.GLASS_STAIRS.get())
                .add(ModBlocks.GLASS_WALL.get())
                .add(ModBlocks.GLASS_FENCE.get())
                .add(ModBlocks.GLASS_FENCE_GATE.get())
                .add(ModBlocks.GLASS_DOOR.get())
                .add(ModBlocks.GLASS_TRAPDOOR.get())
                .add(ModBlocks.GLASS_PRESSURE_PLATE.get())
                .add(ModBlocks.GLASS_BUTTON.get());

        this.tag(ModTags.Blocks.GLASS_BLOCKS)
                .add(Blocks.GLASS)
                .add(Blocks.WHITE_STAINED_GLASS)
                .add(Blocks.LIGHT_GRAY_STAINED_GLASS)
                .add(Blocks.GRAY_STAINED_GLASS)
                .add(Blocks.BLACK_STAINED_GLASS)
                .add(Blocks.BROWN_STAINED_GLASS)
                .add(Blocks.RED_STAINED_GLASS)
                .add(Blocks.ORANGE_STAINED_GLASS)
                .add(Blocks.LIME_STAINED_GLASS)
                .add(Blocks.YELLOW_STAINED_GLASS)
                .add(Blocks.GREEN_STAINED_GLASS)
                .add(Blocks.CYAN_STAINED_GLASS)
                .add(Blocks.LIGHT_BLUE_STAINED_GLASS)
                .add(Blocks.BLUE_STAINED_GLASS)
                .add(Blocks.PURPLE_STAINED_GLASS)
                .add(Blocks.MAGENTA_STAINED_GLASS)
                .add(Blocks.PINK_STAINED_GLASS);

        this.tag(ModTags.Blocks.GLASS_PANES)
                .add(Blocks.GLASS_PANE)
                .add(Blocks.WHITE_STAINED_GLASS_PANE)
                .add(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE)
                .add(Blocks.GRAY_STAINED_GLASS_PANE)
                .add(Blocks.BLACK_STAINED_GLASS_PANE)
                .add(Blocks.BROWN_STAINED_GLASS_PANE)
                .add(Blocks.RED_STAINED_GLASS_PANE)
                .add(Blocks.ORANGE_STAINED_GLASS_PANE)
                .add(Blocks.LIME_STAINED_GLASS_PANE)
                .add(Blocks.YELLOW_STAINED_GLASS_PANE)
                .add(Blocks.GREEN_STAINED_GLASS_PANE)
                .add(Blocks.CYAN_STAINED_GLASS_PANE)
                .add(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE)
                .add(Blocks.BLUE_STAINED_GLASS_PANE)
                .add(Blocks.PURPLE_STAINED_GLASS_PANE)
                .add(Blocks.MAGENTA_STAINED_GLASS_PANE)
                .add(Blocks.PINK_STAINED_GLASS_PANE);

        this.tag(ModTags.Blocks.ALL_GLASS)
                .addTag(ModTags.Blocks.PLAIN_GLASS_FAMILY)
                .addTag(ModTags.Blocks.GLASS_BLOCKS)
                .addTag(ModTags.Blocks.GLASS_PANES);

        this.tag(ModTags.Blocks.QUARTZ_FAMILY)
                .add(Blocks.QUARTZ_BLOCK)
                .add(Blocks.QUARTZ_PILLAR)
                .add(Blocks.CHISELED_QUARTZ_BLOCK)
                .add(Blocks.QUARTZ_BRICKS)
                .add(Blocks.QUARTZ_SLAB)
                .add(Blocks.QUARTZ_STAIRS)
                .add(Blocks.SMOOTH_QUARTZ)
                .add(Blocks.SMOOTH_QUARTZ_SLAB)
                .add(Blocks.SMOOTH_QUARTZ_STAIRS);

        this.tag(ModTags.Blocks.TUFF_FAMILY)
                .add(Blocks.TUFF);

        this.tag(ModTags.Blocks.BRICK_FAMILY)
                .add(Blocks.BRICKS)
                .add(Blocks.BRICK_STAIRS)
                .add(Blocks.BRICK_SLAB)
                .add(Blocks.BRICK_WALL);

        this.tag(ModTags.Blocks.MUD_FAMILY)
                .add(Blocks.PACKED_MUD)
                .add(Blocks.MUD_BRICKS)
                .add(Blocks.MUD_BRICK_STAIRS)
                .add(Blocks.MUD_BRICK_SLAB)
                .add(Blocks.MUD_BRICK_WALL);

        this.tag(ModTags.Blocks.STORAGE_RAW_ORE_BLOCKS)
                .add(Blocks.RAW_IRON_BLOCK)
                .add(Blocks.RAW_COPPER_BLOCK)
                .add(Blocks.RAW_GOLD_BLOCK)
                .add(ModBlocks.RAW_PLASTIMETAL_BLOCK.get())
                .add(ModBlocks.RAW_TIN_BLOCK.get())
                .add(ModBlocks.RAW_BRONZE_BLOCK.get());

        this.tag(ModTags.Blocks.STORAGE_ORE_BLOCKS)
                .add(Blocks.COAL_BLOCK)
                .add(Blocks.IRON_BLOCK)
                .add(Blocks.COPPER_BLOCK)
                .add(Blocks.GOLD_BLOCK)
                .add(Blocks.LAPIS_BLOCK)
                .add(Blocks.REDSTONE_BLOCK)
                .add(Blocks.EMERALD_BLOCK)
                .add(Blocks.DIAMOND_BLOCK)
                .add(Blocks.NETHERITE_BLOCK)
                .add(ModBlocks.PLASTIMETAL_BLOCK.get())
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.BRONZE_BLOCK.get());

        this.tag(ModTags.Blocks.CRAFTED_COPPER_BUILDING_BLOCKS)
                .add(Blocks.COPPER_BLOCK) // also in STORAGE_ORE_BLOCKS
                .add(Blocks.CUT_COPPER)
                .add(Blocks.CUT_COPPER_STAIRS)
                .add(Blocks.CUT_COPPER_SLAB)
                .add(Blocks.EXPOSED_COPPER)
                .add(Blocks.EXPOSED_CUT_COPPER)
                .add(Blocks.EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WEATHERED_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.WEATHERED_CUT_COPPER_SLAB)
                .add(Blocks.OXIDIZED_COPPER)
                .add(Blocks.OXIDIZED_CUT_COPPER)
                .add(Blocks.OXIDIZED_CUT_COPPER_STAIRS)
                .add(Blocks.OXIDIZED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_COPPER_BLOCK)
                .add(Blocks.WAXED_CUT_COPPER)
                .add(Blocks.WAXED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_EXPOSED_COPPER)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_WEATHERED_COPPER)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_OXIDIZED_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB);

        this.tag(ModTags.Blocks.METAL_BUILDING_BLOCKS)
                .addTag(ModTags.Blocks.CRAFTED_COPPER_BUILDING_BLOCKS)
                .add(Blocks.IRON_BARS)
                .add(Blocks.IRON_DOOR)
                .add(Blocks.IRON_TRAPDOOR)
                .add(Blocks.CHAIN)
                .add(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .add(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .add(ModBlocks.PLASTIMETAL_DOOR.get())
                .add(ModBlocks.PLASTIMETAL_TRAPDOOR.get())
                .add(ModBlocks.PLASTIMETAL_BARS.get());

        this.tag(ModTags.Blocks.SANDSTONE_FAMILY)
                .add(Blocks.SANDSTONE)
                .add(Blocks.SANDSTONE_STAIRS)
                .add(Blocks.SANDSTONE_SLAB)
                .add(Blocks.SANDSTONE_WALL)
                .add(Blocks.CHISELED_SANDSTONE)
                .add(Blocks.SMOOTH_SANDSTONE)
                .add(Blocks.SMOOTH_SANDSTONE_STAIRS)
                .add(Blocks.SMOOTH_SANDSTONE_SLAB)
                .add(Blocks.CUT_SANDSTONE)
                .add(Blocks.CUT_SANDSTONE_SLAB);

        this.tag(ModTags.Blocks.RED_SANDSTONE_FAMILY)
                .add(Blocks.RED_SANDSTONE)
                .add(Blocks.RED_SANDSTONE_STAIRS)
                .add(Blocks.RED_SANDSTONE_SLAB)
                .add(Blocks.RED_SANDSTONE_WALL)
                .add(Blocks.CHISELED_RED_SANDSTONE)
                .add(Blocks.SMOOTH_RED_SANDSTONE)
                .add(Blocks.SMOOTH_RED_SANDSTONE_STAIRS)
                .add(Blocks.SMOOTH_RED_SANDSTONE_SLAB)
                .add(Blocks.CUT_RED_SANDSTONE)
                .add(Blocks.CUT_RED_SANDSTONE_SLAB);

        this.tag(ModTags.Blocks.PRISMARINE_FAMILY)
                .add(Blocks.PRISMARINE)
                .add(Blocks.PRISMARINE_STAIRS)
                .add(Blocks.PRISMARINE_SLAB)
                .add(Blocks.PRISMARINE_WALL)
                .add(Blocks.PRISMARINE_BRICKS)
                .add(Blocks.PRISMARINE_BRICK_STAIRS)
                .add(Blocks.PRISMARINE_BRICK_SLAB)
                .add(Blocks.DARK_PRISMARINE)
                .add(Blocks.DARK_PRISMARINE_STAIRS)
                .add(Blocks.DARK_PRISMARINE_SLAB);

        this.tag(ModTags.Blocks.AMETHYST_FAMILY)
                .add(Blocks.AMETHYST_BLOCK)
                .add(Blocks.BUDDING_AMETHYST)
                .add(Blocks.AMETHYST_CLUSTER)
                .add(Blocks.LARGE_AMETHYST_BUD)
                .add(Blocks.MEDIUM_AMETHYST_BUD)
                .add(Blocks.SMALL_AMETHYST_BUD);

        this.tag(ModTags.Blocks.TERRACOTTA_FAMILY)
                .addTag(BlockTags.TERRACOTTA)
                .add(Blocks.WHITE_GLAZED_TERRACOTTA)
                .add(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA)
                .add(Blocks.GRAY_GLAZED_TERRACOTTA)
                .add(Blocks.BLACK_GLAZED_TERRACOTTA)
                .add(Blocks.BROWN_GLAZED_TERRACOTTA)
                .add(Blocks.RED_GLAZED_TERRACOTTA)
                .add(Blocks.ORANGE_GLAZED_TERRACOTTA)
                .add(Blocks.LIME_GLAZED_TERRACOTTA)
                .add(Blocks.YELLOW_GLAZED_TERRACOTTA)
                .add(Blocks.GREEN_GLAZED_TERRACOTTA)
                .add(Blocks.CYAN_GLAZED_TERRACOTTA)
                .add(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .add(Blocks.BLUE_GLAZED_TERRACOTTA)
                .add(Blocks.PURPLE_GLAZED_TERRACOTTA)
                .add(Blocks.MAGENTA_GLAZED_TERRACOTTA)
                .add(Blocks.PINK_GLAZED_TERRACOTTA);

        this.tag(ModTags.Blocks.DRIPSTONE_FAMILY)
                .add(Blocks.DRIPSTONE_BLOCK)
                .add(Blocks.POINTED_DRIPSTONE);

        this.tag(ModTags.Blocks.OBSIDIAN_FAMILY)
                .add(Blocks.OBSIDIAN)
                .add(Blocks.CRYING_OBSIDIAN);

        this.tag(ModTags.Blocks.CORAL_FAMILY)
                .add(Blocks.TUBE_CORAL_BLOCK)
                .add(Blocks.BRAIN_CORAL_BLOCK)
                .add(Blocks.BUBBLE_CORAL_BLOCK)
                .add(Blocks.FIRE_CORAL_BLOCK)
                .add(Blocks.HORN_CORAL_BLOCK)
                .add(Blocks.TUBE_CORAL)
                .add(Blocks.BRAIN_CORAL)
                .add(Blocks.BUBBLE_CORAL)
                .add(Blocks.FIRE_CORAL)
                .add(Blocks.HORN_CORAL)
                .add(Blocks.TUBE_CORAL_FAN)
                .add(Blocks.BRAIN_CORAL_FAN)
                .add(Blocks.BUBBLE_CORAL_FAN)
                .add(Blocks.FIRE_CORAL_FAN)
                .add(Blocks.HORN_CORAL_FAN)
                .add(Blocks.TUBE_CORAL_WALL_FAN)
                .add(Blocks.BRAIN_CORAL_WALL_FAN)
                .add(Blocks.BUBBLE_CORAL_WALL_FAN)
                .add(Blocks.FIRE_CORAL_WALL_FAN)
                .add(Blocks.HORN_CORAL_WALL_FAN)
                .add(Blocks.DEAD_TUBE_CORAL_BLOCK)
                .add(Blocks.DEAD_BRAIN_CORAL_BLOCK)
                .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK)
                .add(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .add(Blocks.DEAD_HORN_CORAL_BLOCK)
                .add(Blocks.DEAD_TUBE_CORAL)
                .add(Blocks.DEAD_BRAIN_CORAL)
                .add(Blocks.DEAD_BUBBLE_CORAL)
                .add(Blocks.DEAD_FIRE_CORAL)
                .add(Blocks.DEAD_HORN_CORAL)
                .add(Blocks.DEAD_TUBE_CORAL_FAN)
                .add(Blocks.DEAD_BRAIN_CORAL_FAN)
                .add(Blocks.DEAD_BUBBLE_CORAL_FAN)
                .add(Blocks.DEAD_FIRE_CORAL_FAN)
                .add(Blocks.DEAD_HORN_CORAL_FAN)
                .add(Blocks.DEAD_TUBE_CORAL_WALL_FAN)
                .add(Blocks.DEAD_BRAIN_CORAL_WALL_FAN)
                .add(Blocks.DEAD_BUBBLE_CORAL_WALL_FAN)
                .add(Blocks.DEAD_FIRE_CORAL_WALL_FAN)
                .add(Blocks.DEAD_HORN_CORAL_WALL_FAN);

        this.tag(ModTags.Blocks.STONE_BLOCKS)
                .add(Blocks.CALCITE)
                .addTag(ModTags.Blocks.CONCRETE_BLOCKS)
                .addTag(ModTags.Blocks.STONE_FAMILY)
                .addTag(ModTags.Blocks.SMOOTH_STONE_FAMILY)
                .addTag(ModTags.Blocks.STONE_BRICK_FAMILY)
                .addTag(ModTags.Blocks.COBBLESTONE_FAMILY)
                .addTag(ModTags.Blocks.GRANITE_FAMILY)
                .addTag(ModTags.Blocks.ANDESITE_FAMILY)
                .addTag(ModTags.Blocks.DIORITE_FAMILY)
                .addTag(ModTags.Blocks.BAUXITE_FAMILY)
                .addTag(ModTags.Blocks.ALL_DEEPSLATE)
                .addTag(ModTags.Blocks.BASALT_FAMILY)
                .addTag(ModTags.Blocks.NETHER_BRICK_FAMILY)
                .addTag(ModTags.Blocks.NETHERRACK_FAMILY)
                .addTag(ModTags.Blocks.PURPUR_FAMILY)
                .addTag(ModTags.Blocks.END_STONE_FAMILY)
                .addTag(ModTags.Blocks.ALL_BLACKSTONE)
                .addTag(ModTags.Blocks.QUARTZ_FAMILY)
                .addTag(ModTags.Blocks.STORAGE_RAW_ORE_BLOCKS)
                .addTag(ModTags.Blocks.STORAGE_ORE_BLOCKS)
                .addTag(ModTags.Blocks.AMETHYST_FAMILY)
                .addTag(ModTags.Blocks.TUFF_FAMILY)
                .addTag(ModTags.Blocks.BRICK_FAMILY)
                .addTag(ModTags.Blocks.MUD_FAMILY)
                .addTag(ModTags.Blocks.SANDSTONE_FAMILY)
                .addTag(ModTags.Blocks.RED_SANDSTONE_FAMILY)
                .addTag(ModTags.Blocks.PRISMARINE_FAMILY)
                .addTag(ModTags.Blocks.TERRACOTTA_FAMILY)
                .addTag(ModTags.Blocks.DRIPSTONE_FAMILY)
                .addTag(ModTags.Blocks.OBSIDIAN_FAMILY)
                .addTag(ModTags.Blocks.CORAL_FAMILY);

        this.tag(ModTags.Blocks.FROGLIGHT_FAMILY)
                .add(Blocks.OCHRE_FROGLIGHT)
                .add(Blocks.VERDANT_FROGLIGHT)
                .add(Blocks.PEARLESCENT_FROGLIGHT);

        this.tag(ModTags.Blocks.ICE_FAMILY)
                .add(Blocks.ICE)
                .add(Blocks.BLUE_ICE)
                .add(Blocks.FROSTED_ICE)
                .add(Blocks.PACKED_ICE);

        this.tag(ModTags.Blocks.OAK_WOOD_FAMILY)
                .addTag(BlockTags.OAK_LOGS)
                .add(      Blocks.OAK_PLANKS)
                .add(      Blocks.OAK_STAIRS)
                .add(      Blocks.OAK_SLAB)
                .add(      Blocks.OAK_FENCE)
                .add(      Blocks.OAK_FENCE_GATE)
                .add(      Blocks.OAK_DOOR)
                .add(      Blocks.OAK_TRAPDOOR)
                .add(      Blocks.OAK_PRESSURE_PLATE)
                .add(      Blocks.OAK_BUTTON)
                .add(      Blocks.OAK_SIGN)
                .add(      Blocks.OAK_HANGING_SIGN)
                .add(      Blocks.OAK_WALL_SIGN)
                .add(      Blocks.OAK_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.SPRUCE_WOOD_FAMILY)
                .addTag(BlockTags.SPRUCE_LOGS)
                .add(      Blocks.SPRUCE_PLANKS)
                .add(      Blocks.SPRUCE_STAIRS)
                .add(      Blocks.SPRUCE_SLAB)
                .add(      Blocks.SPRUCE_FENCE)
                .add(      Blocks.SPRUCE_FENCE_GATE)
                .add(      Blocks.SPRUCE_DOOR)
                .add(      Blocks.SPRUCE_TRAPDOOR)
                .add(      Blocks.SPRUCE_PRESSURE_PLATE)
                .add(      Blocks.SPRUCE_BUTTON)
                .add(      Blocks.SPRUCE_SIGN)
                .add(      Blocks.SPRUCE_HANGING_SIGN)
                .add(      Blocks.SPRUCE_WALL_SIGN)
                .add(      Blocks.SPRUCE_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.BIRCH_WOOD_FAMILY)
                .addTag(BlockTags.BIRCH_LOGS)
                .add(      Blocks.BIRCH_PLANKS)
                .add(      Blocks.BIRCH_STAIRS)
                .add(      Blocks.BIRCH_SLAB)
                .add(      Blocks.BIRCH_FENCE)
                .add(      Blocks.BIRCH_FENCE_GATE)
                .add(      Blocks.BIRCH_DOOR)
                .add(      Blocks.BIRCH_TRAPDOOR)
                .add(      Blocks.BIRCH_PRESSURE_PLATE)
                .add(      Blocks.BIRCH_BUTTON)
                .add(      Blocks.BIRCH_SIGN)
                .add(      Blocks.BIRCH_HANGING_SIGN)
                .add(      Blocks.BIRCH_WALL_SIGN)
                .add(      Blocks.BIRCH_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.JUNGLE_WOOD_FAMILY)
                .addTag(BlockTags.JUNGLE_LOGS)
                .add(      Blocks.JUNGLE_PLANKS)
                .add(      Blocks.JUNGLE_STAIRS)
                .add(      Blocks.JUNGLE_SLAB)
                .add(      Blocks.JUNGLE_FENCE)
                .add(      Blocks.JUNGLE_FENCE_GATE)
                .add(      Blocks.JUNGLE_DOOR)
                .add(      Blocks.JUNGLE_TRAPDOOR)
                .add(      Blocks.JUNGLE_PRESSURE_PLATE)
                .add(      Blocks.JUNGLE_BUTTON)
                .add(      Blocks.JUNGLE_SIGN)
                .add(      Blocks.JUNGLE_HANGING_SIGN)
                .add(      Blocks.JUNGLE_WALL_SIGN)
                .add(      Blocks.JUNGLE_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.ACACIA_WOOD_FAMILY)
                .addTag(BlockTags.ACACIA_LOGS)
                .add(      Blocks.ACACIA_PLANKS)
                .add(      Blocks.ACACIA_STAIRS)
                .add(      Blocks.ACACIA_SLAB)
                .add(      Blocks.ACACIA_FENCE)
                .add(      Blocks.ACACIA_FENCE_GATE)
                .add(      Blocks.ACACIA_DOOR)
                .add(      Blocks.ACACIA_TRAPDOOR)
                .add(      Blocks.ACACIA_PRESSURE_PLATE)
                .add(      Blocks.ACACIA_BUTTON)
                .add(      Blocks.ACACIA_SIGN)
                .add(      Blocks.ACACIA_HANGING_SIGN)
                .add(      Blocks.ACACIA_WALL_SIGN)
                .add(      Blocks.ACACIA_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.DARK_OAK_WOOD_FAMILY)
                .addTag(BlockTags.DARK_OAK_LOGS)
                .add(      Blocks.DARK_OAK_PLANKS)
                .add(      Blocks.DARK_OAK_STAIRS)
                .add(      Blocks.DARK_OAK_SLAB)
                .add(      Blocks.DARK_OAK_FENCE)
                .add(      Blocks.DARK_OAK_FENCE_GATE)
                .add(      Blocks.DARK_OAK_DOOR)
                .add(      Blocks.DARK_OAK_TRAPDOOR)
                .add(      Blocks.DARK_OAK_PRESSURE_PLATE)
                .add(      Blocks.DARK_OAK_BUTTON)
                .add(      Blocks.DARK_OAK_SIGN)
                .add(      Blocks.DARK_OAK_HANGING_SIGN)
                .add(      Blocks.DARK_OAK_WALL_SIGN)
                .add(      Blocks.DARK_OAK_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.MANGROVE_WOOD_FAMILY)
                .addTag(BlockTags.MANGROVE_LOGS)
                .add(      Blocks.MANGROVE_PLANKS)
                .add(      Blocks.MANGROVE_STAIRS)
                .add(      Blocks.MANGROVE_SLAB)
                .add(      Blocks.MANGROVE_FENCE)
                .add(      Blocks.MANGROVE_FENCE_GATE)
                .add(      Blocks.MANGROVE_DOOR)
                .add(      Blocks.MANGROVE_TRAPDOOR)
                .add(      Blocks.MANGROVE_PRESSURE_PLATE)
                .add(      Blocks.MANGROVE_BUTTON)
                .add(      Blocks.MANGROVE_SIGN)
                .add(      Blocks.MANGROVE_HANGING_SIGN)
                .add(      Blocks.MANGROVE_WALL_SIGN)
                .add(      Blocks.MANGROVE_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.CHERRY_WOOD_FAMILY)
                .addTag(BlockTags.CHERRY_LOGS)
                .add(      Blocks.CHERRY_PLANKS)
                .add(      Blocks.CHERRY_STAIRS)
                .add(      Blocks.CHERRY_SLAB)
                .add(      Blocks.CHERRY_FENCE)
                .add(      Blocks.CHERRY_FENCE_GATE)
                .add(      Blocks.CHERRY_DOOR)
                .add(      Blocks.CHERRY_TRAPDOOR)
                .add(      Blocks.CHERRY_PRESSURE_PLATE)
                .add(      Blocks.CHERRY_BUTTON)
                .add(      Blocks.CHERRY_SIGN)
                .add(      Blocks.CHERRY_HANGING_SIGN)
                .add(      Blocks.CHERRY_WALL_SIGN)
                .add(      Blocks.CHERRY_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.CRIMSON_STEM_FAMILY)
                .addTag(BlockTags.CRIMSON_STEMS)
                .add(      Blocks.CRIMSON_PLANKS)
                .add(      Blocks.CRIMSON_STAIRS)
                .add(      Blocks.CRIMSON_SLAB)
                .add(      Blocks.CRIMSON_FENCE)
                .add(      Blocks.CRIMSON_FENCE_GATE)
                .add(      Blocks.CRIMSON_DOOR)
                .add(      Blocks.CRIMSON_TRAPDOOR)
                .add(      Blocks.CRIMSON_PRESSURE_PLATE)
                .add(      Blocks.CRIMSON_BUTTON)
                .add(      Blocks.CRIMSON_SIGN)
                .add(      Blocks.CRIMSON_HANGING_SIGN)
                .add(      Blocks.CRIMSON_WALL_SIGN)
                .add(      Blocks.CRIMSON_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.WARPED_STEM_FAMILY)
                .addTag(BlockTags.WARPED_STEMS)
                .add(      Blocks.WARPED_PLANKS)
                .add(      Blocks.WARPED_STAIRS)
                .add(      Blocks.WARPED_SLAB)
                .add(      Blocks.WARPED_FENCE)
                .add(      Blocks.WARPED_FENCE_GATE)
                .add(      Blocks.WARPED_DOOR)
                .add(      Blocks.WARPED_TRAPDOOR)
                .add(      Blocks.WARPED_PRESSURE_PLATE)
                .add(      Blocks.WARPED_BUTTON)
                .add(      Blocks.WARPED_SIGN)
                .add(      Blocks.WARPED_HANGING_SIGN)
                .add(      Blocks.WARPED_WALL_SIGN)
                .add(      Blocks.WARPED_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.BAMBOO_WOOD_FAMILY)
                .addTag(BlockTags.BAMBOO_BLOCKS)
                .add(      Blocks.BAMBOO_MOSAIC)
                .add(      Blocks.BAMBOO_MOSAIC_STAIRS)
                .add(      Blocks.BAMBOO_MOSAIC_SLAB)
                .add(      Blocks.BAMBOO_PLANKS)
                .add(      Blocks.BAMBOO_STAIRS)
                .add(      Blocks.BAMBOO_SLAB)
                .add(      Blocks.BAMBOO_FENCE)
                .add(      Blocks.BAMBOO_FENCE_GATE)
                .add(      Blocks.BAMBOO_DOOR)
                .add(      Blocks.BAMBOO_TRAPDOOR)
                .add(      Blocks.BAMBOO_PRESSURE_PLATE)
                .add(      Blocks.BAMBOO_BUTTON)
                .add(      Blocks.BAMBOO_SIGN)
                .add(      Blocks.BAMBOO_HANGING_SIGN)
                .add(      Blocks.BAMBOO_WALL_SIGN)
                .add(      Blocks.BAMBOO_WALL_HANGING_SIGN);

        this.tag(ModTags.Blocks.ALL_WOOD_FAMILIES)
                .addTag(ModTags.Blocks.OAK_WOOD_FAMILY)
                .addTag(ModTags.Blocks.SPRUCE_WOOD_FAMILY)
                .addTag(ModTags.Blocks.BIRCH_WOOD_FAMILY)
                .addTag(ModTags.Blocks.JUNGLE_WOOD_FAMILY)
                .addTag(ModTags.Blocks.ACACIA_WOOD_FAMILY)
                .addTag(ModTags.Blocks.DARK_OAK_WOOD_FAMILY)
                .addTag(ModTags.Blocks.MANGROVE_WOOD_FAMILY)
                .addTag(ModTags.Blocks.CHERRY_WOOD_FAMILY)
                .addTag(ModTags.Blocks.BAMBOO_WOOD_FAMILY)
                .addTag(ModTags.Blocks.CRIMSON_STEM_FAMILY)
                .addTag(ModTags.Blocks.WARPED_STEM_FAMILY);

        this.tag(ModTags.Blocks.HURTS)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Blocks.SEA_LANTERN)
                .add(Blocks.SPAWNER)
                .add(Blocks.MAGMA_BLOCK)
                .add(Blocks.BONE_BLOCK)
                .add(Blocks.GLOWSTONE)
                .add(Blocks.MUDDY_MANGROVE_ROOTS)
                .addTag(ModTags.Blocks.ALL_GLASS)
                .addTag(ModTags.Blocks.ICE_FAMILY)
                .addTag(ModTags.Blocks.ORE_BLOCKS)
                .addTag(ModTags.Blocks.STONE_BLOCKS);

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(Blocks.MANGROVE_ROOTS)
                .addTag(ModTags.Blocks.ALL_WOOD_FAMILIES);

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(   ModBlocks.SAPPHIRE_BLOCK.get(),
                        ModBlocks.PLASTIMETAL_BLOCK.get(),
                        ModBlocks.SAPPHIRE_ORE.get(),
                        ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                        ModBlocks.RUINED_PLASTIMETAL.get(),
                        ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get(),
                        ModBlocks.RAW_PLASTIMETAL_BLOCK.get(),
                        ModBlocks.PLASTIMETAL_DOOR.get(),
                        ModBlocks.PLASTIMETAL_TRAPDOOR.get(),
                        ModBlocks.PLASTIMETAL_BARS.get()
                );

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(Blocks.OAK_LOG)
                .addTag(BlockTags.LOGS)
                .addTag(BlockTags.PLANKS);

    }
}
