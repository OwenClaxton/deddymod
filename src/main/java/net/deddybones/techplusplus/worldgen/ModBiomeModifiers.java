package net.deddybones.techplusplus.worldgen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SAPPHIRE_ORE_SMALL = registerKey("add_sapphire_ore_small");
    public static final ResourceKey<BiomeModifier> ADD_SAPPHIRE_ORE_MEDIUM = registerKey("add_sapphire_ore_medium");
    public static final ResourceKey<BiomeModifier> ADD_SAPPHIRE_ORE_BURIED = registerKey("add_sapphire_ore_buried");
    public static final ResourceKey<BiomeModifier> ADD_SAPPHIRE_ORE_LARGE = registerKey("add_sapphire_ore_large");

    public static final ResourceKey<BiomeModifier> ADD_PLASTIMETAL_ORE_SMALL = registerKey("add_plastimetal_ore_small");
    public static final ResourceKey<BiomeModifier> ADD_PLASTIMETAL_ORE_BURIED = registerKey("add_plastimetal_ore_buried");
    public static final ResourceKey<BiomeModifier> ADD_PLASTIMETAL_ORE_LARGE = registerKey("add_plastimetal_ore_large");

    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = registerKey("add_tin_ore");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE_LARGE = registerKey("add_tin_ore_large");

    public static final ResourceKey<BiomeModifier> ADD_TINY_ROCKS = registerKey("add_tiny_rocks");
    public static final ResourceKey<BiomeModifier> ADD_TINY_LOGS = registerKey("add_tiny_logs");

    public static final ResourceKey<BiomeModifier> ADD_BAUXITE_ORE_UPPER = registerKey("add_bauxite_ore_upper");
    public static final ResourceKey<BiomeModifier> ADD_BAUXITE_ORE_LOWER = registerKey("add_bauxite_ore_lower");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SAPPHIRE_ORE_SMALL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SAPPHIRE_ORE_SMALL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_SAPPHIRE_ORE_MEDIUM, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SAPPHIRE_ORE_MEDIUM_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_SAPPHIRE_ORE_BURIED, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SAPPHIRE_ORE_BURIED_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_SAPPHIRE_ORE_LARGE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SAPPHIRE_ORE_LARGE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));


        context.register(ADD_PLASTIMETAL_ORE_SMALL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PLASTIMETAL_ORE_SMALL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_PLASTIMETAL_ORE_BURIED, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PLASTIMETAL_ORE_BURIED_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_PLASTIMETAL_ORE_LARGE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PLASTIMETAL_ORE_LARGE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));


        context.register(ADD_TIN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_TIN_ORE_LARGE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_LARGE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));


        context.register(ADD_TINY_ROCKS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TINY_ROCKS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_TINY_LOGS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TINY_LOGS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));


        context.register(ADD_BAUXITE_ORE_UPPER, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BAUXITE_ORE_UPPER_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_BAUXITE_ORE_LOWER, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BAUXITE_ORE_LOWER_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(TechPlusPlus.MOD_ID, name));
    }
}
