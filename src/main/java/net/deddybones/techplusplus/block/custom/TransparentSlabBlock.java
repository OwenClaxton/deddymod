package net.deddybones.techplusplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jetbrains.annotations.NotNull;

public class TransparentSlabBlock extends SlabBlock {
    public static final MapCodec<TransparentSlabBlock> CODEC = simpleCodec(TransparentSlabBlock::new);

    public TransparentSlabBlock(Properties p_56359_) {
        super(p_56359_);
    }

    public @NotNull MapCodec<? extends TransparentSlabBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean hidesNeighborFace(BlockGetter level, BlockPos pos, BlockState state, BlockState neighborState, Direction dir) {
        return switch (getSlabType(state)) {
            case TOP -> {
                if (dir == Direction.UP) {
                    yield neighborState.is(this) || super.hidesNeighborFace(level, pos, state, neighborState, dir);
                } else if (isHorizontal(dir)) {
                    yield (neighborState.is(this) && neighborState.getValue(TYPE) == SlabType.TOP) && (neighborState.is(this) || super.hidesNeighborFace(level, pos, state, neighborState, dir));
                }
                yield false;
            }
            case BOTTOM -> {
                if (dir == Direction.DOWN) {
                    yield neighborState.is(this) || super.hidesNeighborFace(level, pos, state, neighborState, dir);
                } else if (isHorizontal(dir)) {
                    yield (neighborState.is(this) && neighborState.getValue(TYPE) == SlabType.BOTTOM) && (neighborState.is(this) || super.hidesNeighborFace(level, pos, state, neighborState, dir));
                }
                yield false;
            }
            default -> neighborState.is(this) || super.hidesNeighborFace(level, pos, state, neighborState, dir);
        };
    }

    public boolean isHorizontal(Direction dir) {
        return switch (dir) {
            case UP, DOWN -> false;
            default -> true;
        };
    }

    public SlabType getSlabType(BlockState state) {
        return state.getValue(TYPE);
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
