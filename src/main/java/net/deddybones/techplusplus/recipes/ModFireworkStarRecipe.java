package net.deddybones.techplusplus.recipes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import net.deddybones.techplusplus.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModFireworkStarRecipe extends CustomRecipe {
    private static final Ingredient GUNPOWDER_INGREDIENT = Ingredient.of(Items.GUNPOWDER);
    private static final Ingredient TRAIL_INGREDIENT = Ingredient.of(Items.DIAMOND);
    private static final Ingredient FLICKER_INGREDIENT = Ingredient.of(Items.GLOWSTONE_DUST);
    private static final Ingredient SHAPE_INGREDIENT = Ingredient.of(Items.FIRE_CHARGE, Items.FEATHER,
            ModItems.GOLD_BILLET.get(), Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL, Items.CREEPER_HEAD,
            Items.PLAYER_HEAD, Items.DRAGON_HEAD, Items.ZOMBIE_HEAD, Items.PIGLIN_HEAD);
    private static final Map<Item, FireworkRocketItem.Shape> SHAPE_BY_ITEM = Util.make(Maps.newHashMap(),
            (instance) -> { instance.put(Items.FIRE_CHARGE, FireworkRocketItem.Shape.LARGE_BALL);
                            instance.put(Items.FEATHER, FireworkRocketItem.Shape.BURST);
                            instance.put(ModItems.GOLD_BILLET.get(), FireworkRocketItem.Shape.STAR);
                            instance.put(Items.SKELETON_SKULL, FireworkRocketItem.Shape.CREEPER);
                            instance.put(Items.WITHER_SKELETON_SKULL, FireworkRocketItem.Shape.CREEPER);
                            instance.put(Items.CREEPER_HEAD, FireworkRocketItem.Shape.CREEPER);
                            instance.put(Items.PLAYER_HEAD, FireworkRocketItem.Shape.CREEPER);
                            instance.put(Items.DRAGON_HEAD, FireworkRocketItem.Shape.CREEPER);
                            instance.put(Items.ZOMBIE_HEAD, FireworkRocketItem.Shape.CREEPER);
                            instance.put(Items.PIGLIN_HEAD, FireworkRocketItem.Shape.CREEPER);
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
            } else if (FLICKER_INGREDIENT.test(itemStack)) {
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

    public @NotNull ItemStack assemble(CraftingContainer pContainer, @Nullable RegistryAccess pAccess) {
        ItemStack fireworkItem = new ItemStack(Items.FIREWORK_STAR);
        CompoundTag compoundtag = fireworkItem.getOrCreateTagElement("Explosion");
        FireworkRocketItem.Shape fireworkRocketItem$shape = FireworkRocketItem.Shape.SMALL_BALL;
        List<Integer> dyeList = Lists.newArrayList();
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack fireworkIngredient = pContainer.getItem(i);
            if (fireworkIngredient.isEmpty()) continue;
            if (SHAPE_INGREDIENT.test(fireworkIngredient)) {
                fireworkRocketItem$shape = SHAPE_BY_ITEM.get(fireworkIngredient.getItem());
            } else if (FLICKER_INGREDIENT.test(fireworkIngredient)) {
                compoundtag.putBoolean("Flicker", true);
            } else if (TRAIL_INGREDIENT.test(fireworkIngredient)) {
                compoundtag.putBoolean("Trail", true);
            } else if (fireworkIngredient.getItem() instanceof DyeItem) {
                dyeList.add(((DyeItem) fireworkIngredient.getItem()).getDyeColor().getFireworkColor());
            }
        }
        compoundtag.putIntArray("Colors", dyeList);
        fireworkRocketItem$shape.save(compoundtag);
        return fireworkItem;
    }

    public boolean canCraftInDimensions(int pGridWidth, int pGridHeight) {
        return pGridWidth * pGridHeight >= 2;
    }

    public @NotNull ItemStack getResultItem(@Nullable RegistryAccess pAccess) {
        return new ItemStack(Items.FIREWORK_STAR);
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.FIREWORK_STAR_SERIALIZER.get();
    }
}