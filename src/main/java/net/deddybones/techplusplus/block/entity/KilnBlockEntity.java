package net.deddybones.techplusplus.block.entity;

import net.deddybones.techplusplus.recipes.ModRecipes;
import net.deddybones.techplusplus.screen.KilnMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.KILN.get(), pPos, pState, ModRecipes.KILN_TYPE.get());
    }

    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.techplusplus.kiln");
    }

    protected @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory) {
        return new KilnMenu(pContainerId, pInventory, this, this.dataAccess);
    }
}