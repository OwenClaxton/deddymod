package net.deddybones.deddymod.worldgen;

import net.deddybones.deddymod.DeddyMod;
import net.deddybones.deddymod.worldgen.features.TinyLogFeature;
import net.deddybones.deddymod.worldgen.features.TinyRockFeature;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModWorldGen {
    public static final DeferredRegister<BiomeModifier> BIOME_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, DeddyMod.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.Keys.FEATURES, DeddyMod.MOD_ID);

    public static final RegistryObject<Feature<SimpleBlockConfiguration>> TINY_ROCK_FEATURE = registerFeature("tiny_rock_feature",
            () -> new TinyRockFeature(SimpleBlockConfiguration.CODEC));

    public static final RegistryObject<Feature<SimpleBlockConfiguration>> TINY_LOG_FEATURE = registerFeature("tiny_log_feature",
            () -> new TinyLogFeature(SimpleBlockConfiguration.CODEC));

    private static <T extends Feature<?>>RegistryObject<T> registerFeature(String name, Supplier<T> pFeature) {
        return FEATURES.register(name, pFeature);
    }

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
        FEATURES.register(eventBus);
    }
}
