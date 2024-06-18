package net.deddybones.techplusplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.deddybones.techplusplus.block.entity.SmelteryBlockEntity;
import net.deddybones.techplusplus.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SmelteryBlock extends BaseEntityBlock {
    public static final MapCodec<SmelteryBlock> CODEC = simpleCodec(SmelteryBlock::new);
    public static final Component CONTAINER_TITLE = Component.translatable("container.techplusplus.smeltery");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty SMELTING = BooleanProperty.create("smelting");
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D,
                                                        16.0D, 16.0D, 16.0D);

    @Override
    public @NotNull MapCodec<SmelteryBlock> codec() {
        return CODEC;
    }

    public SmelteryBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(SMELTING, Boolean.FALSE));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new SmelteryBlockEntity(pPos, pState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING,
                pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void onRemove(BlockState pState, @NotNull Level pLevel,
                            @NotNull BlockPos pPos, BlockState pOtherState,
                            boolean p_48717_) {
        if (!pState.is(pOtherState.getBlock())) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof SmelteryBlockEntity smelteryBlockEntity) {
                if (pLevel instanceof ServerLevel serverLevel) {
                    Containers.dropContents(pLevel, pPos, smelteryBlockEntity);
                    smelteryBlockEntity.getRecipesToAward(serverLevel);
                }
                super.onRemove(pState, pLevel, pPos, pOtherState, p_48717_);
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            } else {
                super.onRemove(pState, pLevel, pPos, pOtherState, p_48717_);
            }
        }
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
    public @NotNull VoxelShape getShape(@NotNull BlockState pState,
                                        @NotNull BlockGetter pGetter,
                                        @NotNull BlockPos pPos,
                                        @NotNull CollisionContext pCollision) {
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
        pBuilder.add(FACING, SMELTING);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel,
                                                                  @NotNull BlockState pState,
                                                                  @NotNull BlockEntityType<T> pType) {
        return pLevel.isClientSide ? null : createTickerHelper(pType, ModBlockEntities.SMELTERY.get(),
                SmelteryBlockEntity::serverTick);
    }

    @Override
    public void animateTick(BlockState pState, @NotNull Level pLevel,
                            @NotNull BlockPos pPos,
                            @NotNull RandomSource pRandom) {
        if (pState.getValue(SMELTING)) {
            double pX = (double) pPos.getX() + 0.5;
            double pY = (double) pPos.getY() + 1.0;
            double pZ = (double) pPos.getZ() + 0.5;
            if (pRandom.nextDouble() < 0.2)
                pLevel.playLocalSound(pX, pY, pZ, SoundEvents.FURNACE_FIRE_CRACKLE,
                        SoundSource.BLOCKS, 1.0F, 1.0F, false);
            for (int i = 0; i < 3; i++) {
                double x_offset = (0.5 * pRandom.nextDouble()) - 0.25;
                double y_offset = pRandom.nextDouble() * 6.0 / 16.0;
                double z_offset = (0.5 * pRandom.nextDouble()) - 0.25;
                pLevel.addParticle(ParticleTypes.WHITE_SMOKE,
                        pX + x_offset, pY + y_offset, pZ + z_offset,
                        0.0, 0.0, 0.0);
            }
        }
    }
}