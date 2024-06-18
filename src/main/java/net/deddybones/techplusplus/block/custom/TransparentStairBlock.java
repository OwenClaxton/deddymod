package net.deddybones.techplusplus.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TransparentStairBlock extends StairBlock {
    public static final MapCodec<TransparentStairBlock> CODEC = RecordCodecBuilder.mapCodec(
            (p_309295_) -> p_309295_.group(
                    BlockState.CODEC.fieldOf("base_state").forGetter((p_309296_) -> p_309296_.baseState),
                    propertiesCodec()
            ).apply(p_309295_, TransparentStairBlock::new));

    public @NotNull MapCodec<? extends TransparentStairBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean hidesNeighborFace(BlockGetter level, BlockPos pos, BlockState state, BlockState neighborState, Direction dir) {
        return neighborState.is(this) || super.hidesNeighborFace(level, pos, state, neighborState, dir);
    }
    public TransparentStairBlock(BlockState p_56862_, Properties p_56863_) {
        super(p_56862_, p_56863_);
    }

    public float getShadeBrightness(@NotNull BlockState state, @NotNull BlockGetter getter,
                                    @NotNull BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(@NotNull BlockState state, @NotNull BlockGetter getter,
                                          @NotNull BlockPos pos) {
        return true;
    }
}
