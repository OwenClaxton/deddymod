package net.deddybones.techplusplus.gui.menu.slots;

import net.deddybones.techplusplus.block.entity.CrusherBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CrusherResultSlot extends Slot {
    private final Player player;
    private int removeCount;

    public CrusherResultSlot(Player pPlayer, Container pContainer, int pSlotIndex, int pSlotX, int pSlotY) {
        super(pContainer, pSlotIndex, pSlotX, pSlotY);
        this.player = pPlayer;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack pStack) {
        return false;
    }

    @Override
    public @NotNull ItemStack remove(int removeAmount) {
        if (this.hasItem()) {
            this.removeCount = this.removeCount + Math.min(removeAmount, this.getItem().getCount());
        }
        return super.remove(removeAmount);
    }

    @Override
    public void onTake(@NotNull Player pPlayer, @NotNull ItemStack pStack) {
        this.checkTakeAchievements(pStack);
        super.onTake(pPlayer, pStack);
    }

    @Override
    protected void onQuickCraft(@NotNull ItemStack pStack, int removeAmount) {
        this.removeCount += removeAmount;
        this.checkTakeAchievements(pStack);
    }

    @Override
    protected void checkTakeAchievements(ItemStack pStack) {
        pStack.onCraftedBy(this.player.level(), this.player, this.removeCount);
        if (this.player instanceof ServerPlayer serverPlayer &&
                this.container instanceof CrusherBlockEntity blockEntity) {
            blockEntity.awardUsedRecipes(serverPlayer);
        }
        this.removeCount = 0;
    }
}
