package net.deddybones.deddymod.datagen;

import net.deddybones.deddymod.DeddyMod;
import net.deddybones.deddymod.util.forge.DatapackBuiltinEntriesProvider;
import net.deddybones.deddymod.util.forge.RegistrySetBuilder;
import net.deddybones.deddymod.worldgen.ModBiomeModifiers;
import net.deddybones.deddymod.worldgen.ModConfiguredFeatures;
import net.deddybones.deddymod.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(DeddyMod.MOD_ID));
    }
}
