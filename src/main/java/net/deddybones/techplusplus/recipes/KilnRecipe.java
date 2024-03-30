package net.deddybones.techplusplus.recipes;

import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class KilnRecipe extends AbstractCookingRecipe {
   public KilnRecipe(String pType, CookingBookCategory pCategory, Ingredient pIngredient,
                     ItemStack pResult, float pExperience, int pCookingTime) {
      super(ModRecipes.KILN_TYPE.get(), pType, pCategory, pIngredient, pResult, pExperience, pCookingTime);
   }

   public @NotNull ItemStack getToastSymbol() {
      return new ItemStack(ModBlocks.KILN.get());
   }

   public @NotNull RecipeSerializer<?> getSerializer() {
      return ModRecipes.KILN_SERIALIZER.get();
   }
}