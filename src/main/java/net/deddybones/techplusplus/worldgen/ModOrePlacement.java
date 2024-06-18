package net.deddybones.techplusplus.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

@SuppressWarnings("unused")
public class ModOrePlacement {
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
