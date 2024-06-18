package net.deddybones.techplusplus.gui.menu.slots;

import net.deddybones.techplusplus.block.entity.SmelteryBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SmelteryResultSlot extends ResultSlot {

    public SmelteryResultSlot(Player pPlayer, Container pContainer, int pSlotIndex, int pSlotX, int pSlotY) {
        super(pPlayer, pContainer, pSlotIndex, pSlotX, pSlotY);
    }

    @Override
    protected void checkTakeAchievements(@NotNull ItemStack pStack) {
        pStack.onCraftedBy(this.player.level(), this.player, this.removeCount);
        if (this.player instanceof ServerPlayer serverPlayer &&
                this.container instanceof SmelteryBlockEntity blockEntity) {
            blockEntity.awardUsedRecipes(serverPlayer);
        }
        this.removeCount = 0;
    }
}
