package net.deddybones.techplusplus.datagen.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ModHelper {
    public static ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    public static String name(Block block) {
        return key(block).getPath();
    }

    public static String getItemName(ItemLike pItemLike) {
        return pItemLike.asItem().toString();
    }

    public static String getFromExt(ItemLike pItemLike) {
        return "_from_" + getItemName(pItemLike);
    }

    public static String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(MinMaxBounds.Ints p_176521_, ItemLike p_176522_) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(p_176522_).withCount(p_176521_));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike p_298497_) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(p_298497_));
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(TagKey<Item> p_299059_) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(p_299059_));
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate.Builder... itemPredicateBuilder) {
        return inventoryTrigger(Arrays.stream(itemPredicateBuilder).map(ItemPredicate.Builder::build).toArray(ItemPredicate[]::new));
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate... itemPredicate) {
        return CriteriaTriggers.INVENTORY_CHANGED.createCriterion(
                new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
                        InventoryChangeTrigger.TriggerInstance.Slots.ANY,
                        List.of(itemPredicate)
                )
        );
    }
}
