package net.deddybones.techplusplus.block.custom;

import com.mojang.serialization.MapCodec;
import javax.annotation.Nullable;
import net.deddybones.techplusplus.block.entity.KilnBlockEntity;
import net.deddybones.techplusplus.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class KilnBlock extends AbstractFurnaceBlock {
   public static final MapCodec<KilnBlock> CODEC = simpleCodec(KilnBlock::new);

   public @NotNull MapCodec<KilnBlock> codec() {
      return CODEC;
   }

   public KilnBlock(BlockBehaviour.Properties pProperties) {
      super(pProperties);
   }

   public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
      return new KilnBlockEntity(pPos, pState);
   }

   @Nullable
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState,
                                                                 @NotNull BlockEntityType<T> pBlockEntityType) {
      return createFurnaceTicker(pLevel, pBlockEntityType, ModBlockEntities.KILN.get());
   }

   protected void openContainer(Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer) {
      BlockEntity blockentity = pLevel.getBlockEntity(pPos);
      if (blockentity instanceof KilnBlockEntity) {
         pPlayer.openMenu((MenuProvider)blockentity);
      }

   }

   public void animateTick(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos,
                           @NotNull RandomSource pRand) {
      if (pState.getValue(LIT)) {
         double d0 = (double) pPos.getX() + 0.5D;
         double d1 = (double) pPos.getY();
         double d2 = (double) pPos.getZ() + 0.5D;
         if (pRand.nextDouble() < 0.1D) {
            pLevel.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
         }
         Direction direction = pState.getValue(FACING);
         Direction.Axis direction$axis = direction.getAxis();
         double d4 = pRand.nextDouble() * 0.6D - 0.3D;
         double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
         double d6 = pRand.nextDouble() * 6.0D / 16.0D;
         double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
         pLevel.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
         pLevel.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
      }
   }
}
