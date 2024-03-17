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

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
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

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(Blocks.OAK_LOG)
                .addTag(BlockTags.LOGS)
                .addTag(BlockTags.PLANKS);

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

        this.tag(ModTags.Blocks.STONE_BLOCKS)
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
                .addTag(ModTags.Blocks.PURPUR_FAMILY)
                .addTag(ModTags.Blocks.END_STONE_FAMILY)
                .addTag(ModTags.Blocks.ALL_BLACKSTONE)
                .addTag(ModTags.Blocks.QUARTZ_FAMILY)
                .addTag(ModTags.Blocks.STORAGE_RAW_ORE_BLOCKS)
                .addTag(ModTags.Blocks.STORAGE_ORE_BLOCKS);

        // for plants and things in the world you probably shouldn't use your hands for
        this.tag(ModTags.Blocks.HURTS_SMALL)
                .addTag(BlockTags.LEAVES);

        // for buildables that a tool should be used for
        this.tag(ModTags.Blocks.HURTS_MEDIUM)
                .addTag(BlockTags.PLANKS)
                .addTag(ModTags.Blocks.ALL_GLASS);

        // for things you should definitely use a tool for
        this.tag(ModTags.Blocks.HURTS_LARGE)
                .addTag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.ORE_BLOCKS)
                .addTag(ModTags.Blocks.STONE_BLOCKS);


    }
}
