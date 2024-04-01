package net.deddybones.techplusplus.recipes;

import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class ClayMolderRecipe extends ModSingleItemRecipe {
    public ClayMolderRecipe(String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(ModRecipes.MOLD_TYPE.get(), ModRecipes.MOLD_SERIALIZER.get(), pGroup , pIngredient , pResult);
    }

    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.CLAY_MOLDER.get());
    }
}
