package net.deddybones.deddymod.datagen;

import net.deddybones.deddymod.DeddyMod;
import net.deddybones.deddymod.block.ModBlocks;
import net.deddybones.deddymod.item.ModItems;
import net.deddybones.deddymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DeddyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
//        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
//                .add(ModBlocks.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);

//        this.tag(BlockTags.IMPERMEABLE).add(ModBlocks.TEST_BLOCK.get());

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

    }
}
