package net.deddybones.deddymod.block.custom;

import net.deddybones.deddymod.block.ModBlocks;
import net.deddybones.deddymod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class RockGoodieBlock extends GoodieBlock {
    public static final VoxelShape SHAPE = Block.box(5,0,5, 11, 1, 11);
    public RockGoodieBlock(Properties p_51021_) {
        super(p_51021_);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(@NotNull BlockState pBlockState, @NotNull BlockGetter pBlockGetter,
                                        @NotNull BlockPos pBlockPos, @NotNull CollisionContext pColContext) {
        Vec3 vec3 = pBlockState.getOffset(pBlockGetter, pBlockPos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

}
