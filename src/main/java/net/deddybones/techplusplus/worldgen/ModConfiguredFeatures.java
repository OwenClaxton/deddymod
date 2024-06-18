package net.deddybones.techplusplus.worldgen;

import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

import static net.deddybones.techplusplus.datagen.util.ModHelper.modLoc;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_SMALL_KEY = registerKey("sapphire_ore_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_BURIED_KEY = registerKey("sapphire_ore_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_MEDIUM_KEY = registerKey("sapphire_ore_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE_LARGE_KEY = registerKey("sapphire_ore_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PLASTIMETAL_ORE_SMALL_KEY = registerKey("plastimetal_ore_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PLASTIMETAL_ORE_LARGE_KEY = registerKey("plastimetal_ore_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PLASTIMETAL_ORE_BURIED_KEY = registerKey("plastimetal_ore_buried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_LARGE_KEY = registerKey("tin_ore_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TINY_ROCKS_KEY = registerKey("tiny_rocks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TINY_LOGS_KEY = registerKey("tiny_logs");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BAUXITE_ORE_KEY = registerKey("bauxite_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables      = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables  = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        // RuleTest netherrackReplaceables = new TagMatchTest(Tags.Blocks.NETHERRACK);
        // RuleTest endReplaceables        = new TagMatchTest(Tags.Blocks.END_STONES);

        List<OreConfiguration.TargetBlockState> overworldSapphireOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())
                );

        List<OreConfiguration.TargetBlockState> overworldPlastimetalOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.RUINED_PLASTIMETAL.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldTinOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_SAPPHIRE_ORE_SMALL_KEY,  Feature.ORE, new OreConfiguration(overworldSapphireOres, 4, 0.5F));
        register(context, OVERWORLD_SAPPHIRE_ORE_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 8, 1.0F));
        register(context, OVERWORLD_SAPPHIRE_ORE_MEDIUM_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 8, 0.5F));
        register(context, OVERWORLD_SAPPHIRE_ORE_LARGE_KEY,  Feature.ORE, new OreConfiguration(overworldSapphireOres, 12, 0.7F));

        register(context, OVERWORLD_PLASTIMETAL_ORE_SMALL_KEY,  Feature.ORE, new OreConfiguration(overworldPlastimetalOres, 8, 0.3F));
        register(context, OVERWORLD_PLASTIMETAL_ORE_LARGE_KEY,  Feature.ORE, new OreConfiguration(overworldPlastimetalOres, 24, 0.7F));
        register(context, OVERWORLD_PLASTIMETAL_ORE_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldPlastimetalOres, 30, 1.0F));

        register(context, OVERWORLD_TIN_ORE_KEY,        Feature.ORE, new OreConfiguration(overworldTinOres, 10));
        register(context, OVERWORLD_TIN_ORE_LARGE_KEY,  Feature.ORE, new OreConfiguration(overworldTinOres, 20));

        register(context, OVERWORLD_TINY_ROCKS_KEY, ModWorldGen.TINY_ROCK_FEATURE.get(), singleBlock(ModBlocks.TINY_ROCK_BLOCK.get()));
        register(context, OVERWORLD_TINY_LOGS_KEY, ModWorldGen.TINY_LOG_FEATURE.get(), singleBlock(ModBlocks.TINY_LOG_BLOCK.get()));

        register(context, OVERWORLD_BAUXITE_ORE_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables, ModBlocks.BAUXITE.get().defaultBlockState(), 16));

    }

    private static SimpleBlockConfiguration singleBlock(Block pBlock) {
        return new SimpleBlockConfiguration(BlockStateProvider.simple(pBlock));
    }

    @SuppressWarnings("unused")
    private static RandomPatchConfiguration surfaceClutter(Block pBlock, int pTries) {
        return FeatureUtils.simpleRandomPatchConfiguration(pTries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(pBlock))));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, modLoc(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
