package net.deddybones.techplusplus.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CrusherBlockEntity extends BlockEntity {
    public CrusherBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.CRUSHER.get(), pPos, pState);
    }
}
