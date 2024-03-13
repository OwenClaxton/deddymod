package net.deddybones.techplusplus.datagen.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class ModHelper {
    public static ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public static String name(Block block) {
        return key(block).getPath();
    }
}
