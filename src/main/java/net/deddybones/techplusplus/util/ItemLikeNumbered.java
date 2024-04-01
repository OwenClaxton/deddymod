package net.deddybones.techplusplus.util;

import net.minecraft.world.level.ItemLike;

@SuppressWarnings("unused")
public class ItemLikeNumbered {
    private final ItemLike itemLike;
    private final int number;

    public ItemLikeNumbered(ItemLike itemLike) {
        this(itemLike, 1);
    }

    public ItemLikeNumbered(ItemLike itemLike, int number) {
        this.itemLike = itemLike;
        this.number = number;
    }

    public static ItemLikeNumbered NIng(ItemLike ingredient) {
        return new ItemLikeNumbered(ingredient, 1);
    }

    public static ItemLikeNumbered NIng(ItemLike ingredient, int number) {
        return new ItemLikeNumbered(ingredient, number);
    }

    public ItemLike getItemLike() {
        return this.itemLike;
    }

    public int getNumber() {
        return this.number;
    }
}
