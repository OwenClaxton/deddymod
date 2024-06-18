package net.deddybones.techplusplus.gui.menu.slots;

import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TagOnlySlot extends Slot {
    protected final TagKey<Item> tag;
    public TagOnlySlot(TagKey<Item> pTag, Container pContainer, int pSlotIndex, int pSlotX, int pSlotY) {
        super(pContainer, pSlotIndex, pSlotX, pSlotY);
        this.tag = pTag;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack pStack) {
        return pStack.is(this.tag);
    }
}
