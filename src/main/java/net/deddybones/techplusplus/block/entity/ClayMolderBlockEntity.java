package net.deddybones.techplusplus.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ClayMolderBlockEntity extends BlockEntity {
    public ClayMolderBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.CLAY_MOLDER.get(), pPos, pState);
    }
}
