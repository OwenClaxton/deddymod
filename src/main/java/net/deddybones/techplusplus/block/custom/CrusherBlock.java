package net.deddybones.techplusplus.block.custom;

import com.mojang.serialization.MapCodec;
import javax.annotation.Nullable;

import net.deddybones.techplusplus.gui.menu.CrusherMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CrusherBlock extends BaseEntityBlock {
    public static final MapCodec<CrusherBlock> CODEC = simpleCodec(CrusherBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.techplusplus.crusher");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D,
                                                        16.0D, 16.0D, 16.0D);

    @Override
    public @NotNull MapCodec<CrusherBlock> codec() {
        return CODEC;
    }

    public CrusherBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState pState, Level pLevel,
                                                        @NotNull BlockPos pPos, @NotNull Player pPlayer,
                                                        @NotNull BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            return InteractionResult.CONSUME;
        }
    }

    @Override
    @Nullable
    public MenuProvider getMenuProvider(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos) {
        return new SimpleMenuProvider(
                (pContainerId, pInventory, pPlayer) -> new CrusherMenu(
                        pContainerId, pInventory, ContainerLevelAccess.create(pLevel, pPos)
                ), CONTAINER_TITLE);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pGetter,
                                        @NotNull BlockPos pPos, @NotNull CollisionContext pCollision) {
        return SHAPE;
    }

    @Override
    public boolean useShapeForLightOcclusion(@NotNull BlockState pState) {
        return true;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull BlockState rotate(BlockState pState, Rotation pTransform) {
        return pState.setValue(FACING, pTransform.rotate(pState.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState mirror(BlockState pState, Mirror pTransform) {
        return pState.rotate(pTransform.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

//    @Override
//    @SuppressWarnings("deprecation")
//    public boolean isPathfindable(@NotNull BlockState pState, @NotNull BlockGetter pGetter, @NotNull BlockPos pPos,
//                                  @NotNull PathComputationType pCompute) {
//        return false;
//    }
}