package net.deddybones.techplusplus.recipes.builders;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

import net.deddybones.techplusplus.recipes.ClayMolderRecipe;
import net.deddybones.techplusplus.recipes.CrusherRecipe;
import net.deddybones.techplusplus.recipes.ModSingleItemRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class ModSingleItemRecipeBuilder implements RecipeBuilder {
   private final RecipeCategory category;
   private final Item result;
   private final Ingredient ingredient;
   private final int count;
   private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
   @Nullable
   private String group;
   private final ModSingleItemRecipe.Factory<?> factory;

   public ModSingleItemRecipeBuilder(RecipeCategory pCategory, ModSingleItemRecipe.Factory<?> pFactory, Ingredient pIngredient, ItemLike pResult, int resultCount) {
      this.category = pCategory;
      this.factory = pFactory;
      this.result = pResult.asItem();
      this.ingredient = pIngredient;
      this.count = resultCount;
   }

   public static ModSingleItemRecipeBuilder crushing(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult, int resultCount) {
      return new ModSingleItemRecipeBuilder(pCategory, CrusherRecipe::new, pIngredient, pResult, resultCount);
   }

   public static ModSingleItemRecipeBuilder molding(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult, int resultCount) {
      return new ModSingleItemRecipeBuilder(pCategory, ClayMolderRecipe::new, pIngredient, pResult, resultCount);
   }

   public @NotNull ModSingleItemRecipeBuilder unlockedBy(@NotNull String pCriteriaStr, @NotNull Criterion<?> pCriteria) {
      this.criteria.put(pCriteriaStr, pCriteria);
      return this;
   }

   public @NotNull ModSingleItemRecipeBuilder group(@Nullable String pGroup) {
      this.group = pGroup;
      return this;
   }

   public @NotNull Item getResult() {
      return this.result;
   }

   public void save(RecipeOutput pOutput, @NotNull ResourceLocation pLoc) {
      this.ensureValid(pLoc);
      Advancement.Builder advancement$builder = pOutput.advancement()
              .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pLoc))
              .rewards(AdvancementRewards.Builder.recipe(pLoc))
              .requirements(AdvancementRequirements.Strategy.OR);

      this.criteria.forEach(advancement$builder::addCriterion);
      ModSingleItemRecipe ModSingleItemRecipe = this.factory.create(Objects.requireNonNullElse(this.group, ""), this.ingredient, new ItemStack(this.result, this.count));
      pOutput.accept(pLoc, ModSingleItemRecipe, advancement$builder.build(pLoc.withPrefix("recipes/" + this.category.getFolderName() + "/")));
   }

   private void ensureValid(ResourceLocation pLoc) {
      if (this.criteria.isEmpty()) {
         throw new IllegalStateException("No way of obtaining recipe " + pLoc);
      }
   }
}