package net.deddybones.techplusplus.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class LogGoodieBlock extends GoodieBlock {

    public static final VoxelShape SHAPE_NS = Block.box(7,0,2, 9, 2, 14);
    public static final VoxelShape SHAPE_EW = Block.box(2,0,7, 14, 2, 9);

    public LogGoodieBlock(Properties p_51021_) {
        super(p_51021_);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pBlockState, @NotNull BlockGetter pBlockGetter,
                                        @NotNull BlockPos pBlockPos, @NotNull CollisionContext pColContext) {
        Vec3 vec3 = pBlockState.getOffset(pBlockGetter, pBlockPos);
        return (pBlockState.getValue(LEFT_RIGHT) ? SHAPE_EW : SHAPE_NS).move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 300;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 300;
    }
}
