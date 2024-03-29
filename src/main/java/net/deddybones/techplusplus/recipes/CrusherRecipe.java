package net.deddybones.techplusplus.recipes;

import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class CrusherRecipe extends ModSingleItemRecipe {
    public CrusherRecipe(String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(ModRecipes.CRUSHING_TYPE.get(), ModRecipes.CRUSHING_SERIALIZER.get(), pGroup , pIngredient , pResult);
    }

    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.CRUSHER.get());
    }
}