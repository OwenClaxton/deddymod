package net.deddybones.deddymod.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TransparentStairBlock extends StairBlock {
    public static final MapCodec<TransparentStairBlock> CODEC = RecordCodecBuilder.mapCodec((p_309295_) -> {
        return p_309295_.group(BlockState.CODEC.fieldOf("base_state").forGetter((p_309296_) -> {
            return p_309296_.baseState;
        }), propertiesCodec()).apply(p_309295_, TransparentStairBlock::new);
    });

    public MapCodec<? extends TransparentStairBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean hidesNeighborFace(BlockGetter level, BlockPos pos, BlockState state, BlockState neighborState, Direction dir) {
        return neighborState.is(this) || super.hidesNeighborFace(level, pos, state, neighborState, dir);
    }
    public TransparentStairBlock(BlockState p_56862_, Properties p_56863_) {
        super(p_56862_, p_56863_);
    }

    public float getShadeBrightness(BlockState p_312407_, BlockGetter p_310193_, BlockPos p_311965_) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState p_312717_, BlockGetter p_312877_, BlockPos p_312899_) {
        return true;
    }
}
