package net.deddybones.techplusplus.worldgen.features;

import com.mojang.serialization.Codec;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.block.custom.GoodieBlock;
import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

import java.util.Random;

public class TinyRockFeature extends Feature<SimpleBlockConfiguration> {
    public TinyRockFeature(Codec<SimpleBlockConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> context)
    {
        final WorldGenLevel level = context.level();
        final BlockPos pos = context.origin();

        final BlockState stateAt = level.getBlockState(pos);
        final BlockState stateDown = level.getBlockState(pos.below());
        if (stateAt.isAir() && stateDown.is(ModTags.Blocks.TINY_ROCK_PLACEABLE_ON)) {
            level.setBlock(pos, ModBlocks.TINY_ROCK_BLOCK.get().defaultBlockState()
                    .setValue(GoodieBlock.LEFT_RIGHT, (new Random()).nextBoolean()), 3);
            return true;
        }
        return false;
    }
}
