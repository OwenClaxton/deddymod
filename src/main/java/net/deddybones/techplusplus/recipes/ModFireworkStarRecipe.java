package net.deddybones.techplusplus.recipes;

import com.google.common.collect.Maps;

import java.util.Map;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.deddybones.techplusplus.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModFireworkStarRecipe extends CustomRecipe {
    private static final Ingredient GUNPOWDER_INGREDIENT = Ingredient.of(Items.GUNPOWDER);
    private static final Ingredient TRAIL_INGREDIENT = Ingredient.of(Items.DIAMOND);
    private static final Ingredient TWINKLE_INGREDIENT = Ingredient.of(Items.GLOWSTONE_DUST);
    private static final Ingredient SHAPE_INGREDIENT = Ingredient.of(Items.FIRE_CHARGE, Items.FEATHER,
            ModItems.GOLD_BILLET.get(), Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL, Items.CREEPER_HEAD,
            Items.PLAYER_HEAD, Items.DRAGON_HEAD, Items.ZOMBIE_HEAD, Items.PIGLIN_HEAD);
    private static final Map<Item, FireworkExplosion.Shape> SHAPE_BY_ITEM = Util.make(Maps.newHashMap(),
            (instance) -> { instance.put(Items.FIRE_CHARGE, FireworkExplosion.Shape.LARGE_BALL);
                            instance.put(Items.FEATHER, FireworkExplosion.Shape.BURST);
                            instance.put(ModItems.GOLD_BILLET.get(), FireworkExplosion.Shape.STAR);
                            instance.put(Items.SKELETON_SKULL, FireworkExplosion.Shape.CREEPER);
                            instance.put(Items.WITHER_SKELETON_SKULL, FireworkExplosion.Shape.CREEPER);
                            instance.put(Items.CREEPER_HEAD, FireworkExplosion.Shape.CREEPER);
                            instance.put(Items.PLAYER_HEAD, FireworkExplosion.Shape.CREEPER);
                            instance.put(Items.DRAGON_HEAD, FireworkExplosion.Shape.CREEPER);
                            instance.put(Items.ZOMBIE_HEAD, FireworkExplosion.Shape.CREEPER);
                            instance.put(Items.PIGLIN_HEAD, FireworkExplosion.Shape.CREEPER);
                        });

    public ModFireworkStarRecipe(CraftingBookCategory pCategory) {
        super(pCategory);
    }

    public boolean matches(CraftingContainer pContainer, @Nullable Level pLevel) {
        boolean GUNPOWDER_FLAG = false;
        boolean DYE_FLAG = false;
        boolean SHAPE_FLAG = false;
        boolean TRAIL_FLAG = false;
        boolean FLICKER_FLAG = false;

        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemStack = pContainer.getItem(i);
            if (itemStack.isEmpty()) continue;
            if (SHAPE_INGREDIENT.test(itemStack)) {
                if (SHAPE_FLAG) return false;
                SHAPE_FLAG = true;
            } else if (TWINKLE_INGREDIENT.test(itemStack)) {
                if (FLICKER_FLAG) return false;
                FLICKER_FLAG = true;
            } else if (TRAIL_INGREDIENT.test(itemStack)) {
                if (TRAIL_FLAG) return false;
                TRAIL_FLAG = true;
            } else if (GUNPOWDER_INGREDIENT.test(itemStack)) {
                if (GUNPOWDER_FLAG) return false;
                GUNPOWDER_FLAG = true;
            } else {
                if (!(itemStack.getItem() instanceof DyeItem)) return false;
                DYE_FLAG = true;
            }
        }
        return GUNPOWDER_FLAG && DYE_FLAG;
    }

    public @NotNull ItemStack assemble(CraftingContainer p_43893_, HolderLookup.@NotNull Provider p_335220_) {
        FireworkExplosion.Shape fireworkexplosion$shape = FireworkExplosion.Shape.SMALL_BALL;
        boolean flag = false;
        boolean flag1 = false;
        IntList intlist = new IntArrayList();

        for (int i = 0; i < p_43893_.getContainerSize(); i++) {
            ItemStack itemstack = p_43893_.getItem(i);
            if (!itemstack.isEmpty()) {
                if (SHAPE_INGREDIENT.test(itemstack)) {
                    fireworkexplosion$shape = SHAPE_BY_ITEM.get(itemstack.getItem());
                } else if (TWINKLE_INGREDIENT.test(itemstack)) {
                    flag = true;
                } else if (TRAIL_INGREDIENT.test(itemstack)) {
                    flag1 = true;
                } else if (itemstack.getItem() instanceof DyeItem) {
                    intlist.add(((DyeItem)itemstack.getItem()).getDyeColor().getFireworkColor());
                }
            }
        }

        ItemStack itemstack1 = new ItemStack(Items.FIREWORK_STAR);
        itemstack1.set(DataComponents.FIREWORK_EXPLOSION,
                new FireworkExplosion(fireworkexplosion$shape, intlist, IntList.of(), flag1, flag));
        return itemstack1;
    }

    @Override
    public boolean canCraftInDimensions(int pGridWidth, int pGridHeight) {
        return pGridWidth * pGridHeight >= 2;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider p_328577_) {
        return new ItemStack(Items.FIREWORK_STAR);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.FIREWORK_STAR_SERIALIZER.get();
    }
}