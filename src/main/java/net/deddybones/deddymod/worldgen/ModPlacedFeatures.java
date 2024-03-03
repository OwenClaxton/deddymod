package net.deddybones.deddymod.worldgen;

import net.deddybones.deddymod.DeddyMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
//import net.minecraft.data.worldgen.features.OreFeatures;
//import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.worldSurfaceSquaredWithCount;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_SMALL_PLACED_KEY = registerKey("sapphire_ore_small_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_MEDIUM_PLACED_KEY = registerKey("sapphire_ore_medium_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_LARGE_PLACED_KEY = registerKey("sapphire_ore_large_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_BURIED_PLACED_KEY = registerKey("sapphire_ore_buried_placed");

    public static final ResourceKey<PlacedFeature> PLASTIMETAL_ORE_SMALL_PLACED_KEY = registerKey("plastimetal_ore_small_placed");
    public static final ResourceKey<PlacedFeature> PLASTIMETAL_ORE_LARGE_PLACED_KEY = registerKey("plastimetal_ore_large_placed");
    public static final ResourceKey<PlacedFeature> PLASTIMETAL_ORE_BURIED_PLACED_KEY = registerKey("plastimetal_ore_buried_placed");

    public static final ResourceKey<PlacedFeature> TINY_ROCKS_PLACED_KEY = registerKey("tiny_rocks_placed");
    public static final ResourceKey<PlacedFeature> TINY_LOGS_PLACED_KEY = registerKey("tiny_logs_placed");

    public static final ResourceKey<PlacedFeature> BAUXITE_ORE_UPPER_PLACED_KEY = registerKey("bauxite_ore_upper_placed");
    public static final ResourceKey<PlacedFeature> BAUXITE_ORE_LOWER_PLACED_KEY = registerKey("bauxite_ore_lower_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {

        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_SMALL_HOLDER    = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_SMALL_KEY);
        Holder<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_MEDIUM_HOLDER   = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_MEDIUM_KEY);
        Holder<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_LARGE_HOLDER    = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_LARGE_KEY);
        Holder<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_BURIED_HOLDER   = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_BURIED_KEY);

        Holder<ConfiguredFeature<?, ?>> PLASTIMETAL_ORE_SMALL_HOLDER    = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PLASTIMETAL_ORE_SMALL_KEY);
        Holder<ConfiguredFeature<?, ?>> PLASTIMETAL_ORE_LARGE_HOLDER    = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PLASTIMETAL_ORE_LARGE_KEY);
        Holder<ConfiguredFeature<?, ?>> PLASTIMETAL_ORE_BURIED_HOLDER   = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PLASTIMETAL_ORE_BURIED_KEY);

        Holder<ConfiguredFeature<?, ?>> TINY_ROCKS_HOLDER   = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TINY_ROCKS_KEY);
        Holder<ConfiguredFeature<?, ?>> TINY_LOGS_HOLDER    = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TINY_LOGS_KEY);

        Holder<ConfiguredFeature<?, ?>> BAUXITE_ORE_HOLDER  = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_BAUXITE_ORE_KEY);


        register(context, SAPPHIRE_ORE_SMALL_PLACED_KEY,  SAPPHIRE_ORE_SMALL_HOLDER,
                commonOrePlacement(3, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, SAPPHIRE_ORE_MEDIUM_PLACED_KEY, SAPPHIRE_ORE_MEDIUM_HOLDER,
                commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),     VerticalAnchor.absolute(-4))));
        register(context, SAPPHIRE_ORE_LARGE_PLACED_KEY,  SAPPHIRE_ORE_LARGE_HOLDER,
                rareOrePlacement(4,   HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, SAPPHIRE_ORE_BURIED_PLACED_KEY, SAPPHIRE_ORE_BURIED_HOLDER,
                commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, PLASTIMETAL_ORE_SMALL_PLACED_KEY, PLASTIMETAL_ORE_SMALL_HOLDER,
                commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(48), VerticalAnchor.top())));
        register(context, PLASTIMETAL_ORE_LARGE_PLACED_KEY, PLASTIMETAL_ORE_LARGE_HOLDER,
                rareOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));
        register(context, PLASTIMETAL_ORE_BURIED_PLACED_KEY, PLASTIMETAL_ORE_BURIED_HOLDER,
                rareOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));

        register(context, TINY_ROCKS_PLACED_KEY, TINY_ROCKS_HOLDER, worldSurfaceSquaredWithCount(2));
        register(context, TINY_LOGS_PLACED_KEY, TINY_LOGS_HOLDER, worldSurfaceSquaredWithCount(1));

        register(context, BAUXITE_ORE_UPPER_PLACED_KEY, BAUXITE_ORE_HOLDER,
                commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(48), VerticalAnchor.absolute(128))));
        register(context, BAUXITE_ORE_LOWER_PLACED_KEY, BAUXITE_ORE_HOLDER,
                rareOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(48))));

    }
    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(DeddyMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?,?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier rarityModifier, PlacementModifier shapeModifier) {
        return List.of(rarityModifier, InSquarePlacement.spread(), shapeModifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int veinsPerChunk, PlacementModifier shapeModifier) {
        return orePlacement(CountPlacement.of(veinsPerChunk), shapeModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int veinsPerChunk, PlacementModifier shapeModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(veinsPerChunk), shapeModifier);
    }
}
