package net.deddybones.techplusplus.block.custom;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.NotNull;

public class GoodieBlock extends Block {
    @SuppressWarnings("unused")
    public static final MapCodec<GoodieBlock> CODEC = simpleCodec(GoodieBlock::new);
    public static final BooleanProperty LEFT_RIGHT = BlockStateProperties.CONDITIONAL;

    public GoodieBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LEFT_RIGHT, Boolean.FALSE));
    }

    @Override
    public @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pStateDefinition) {
        pStateDefinition.add(LEFT_RIGHT);
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState()
                .setValue(LEFT_RIGHT, blockPlaceContext.getHorizontalDirection().getAxis() == Direction.Axis.X);
    }

    @SuppressWarnings("deprecation")
    public @NotNull FluidState getFluidState(@NotNull BlockState pBlockState) {
        return super.getFluidState(pBlockState);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState updateShape(@NotNull BlockState pBlockState, @NotNull Direction pDirection, @NotNull BlockState p_51034_,
                                           @NotNull LevelAccessor pLevelAccessor, @NotNull BlockPos pBlockPos, @NotNull BlockPos p_51037_) {
        return pDirection == Direction.DOWN && !this.canSurvive(pBlockState, pLevelAccessor, pBlockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pBlockState, pDirection, p_51034_, pLevelAccessor, pBlockPos, p_51037_);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull RenderShape getRenderShape(@NotNull BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    public boolean propagatesSkylightDown(BlockState p_51039_, @NotNull BlockGetter p_51040_, @NotNull BlockPos p_51041_) {
        return p_51039_.getFluidState().isEmpty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(@NotNull BlockState p_51023_, @NotNull BlockGetter p_51024_, @NotNull BlockPos p_51025_, @NotNull PathComputationType p_51026_) {
        return p_51026_ == PathComputationType.AIR && !this.hasCollision || super.isPathfindable(p_51023_, p_51024_, p_51025_, p_51026_);
    }

    @Override
    public boolean canDropFromExplosion(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion) {
        return true;
    }

    @Override
    public boolean isPossibleToRespawnInThis(@NotNull BlockState p_279289_) {
        return true;
    }

    @SuppressWarnings("unused")
    public boolean mayPlaceOn(BlockState p_51042_, @NotNull BlockGetter p_51043_, @NotNull BlockPos p_51044_) {
        return p_51042_.isFaceSturdy(p_51043_, p_51044_, Direction.UP);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(@NotNull BlockState p_51028_, @NotNull LevelReader p_51029_, BlockPos p_51030_) {
        BlockPos blockpos = p_51030_.below();
        return this.mayPlaceOn(p_51029_.getBlockState(blockpos), p_51029_, blockpos);
    }

    @Override
    public boolean canEntityDestroy(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return true;
    }
}
