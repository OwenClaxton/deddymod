package net.deddybones.techplusplus.recipes.builders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.util.ObjectNumbered;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import static net.deddybones.techplusplus.datagen.util.ModHelper.*;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ModShapelessRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;
    private String extension;
    private boolean isFromMinecraft = false;
    public RecipeOutput recipeOutput;

    public ModShapelessRecipeBuilder(RecipeOutput recipeOutput, RecipeCategory pCategory, ItemLike pResult, int pResultCount, String extension) {
        this.category = pCategory;
        this.result = pResult.asItem();
        this.count = pResultCount;
        this.extension = extension;
        this.recipeOutput = recipeOutput;
        this.group = getItemName(this.result);
    }

    public static ModShapelessRecipeBuilder shapeless(RecipeOutput recipeOutput, RecipeCategory pCategory, ItemLike pResult) {
        return new ModShapelessRecipeBuilder(recipeOutput, pCategory, pResult, 1, "");
    }

    public static ModShapelessRecipeBuilder shapeless(RecipeOutput recipeOutput, RecipeCategory pCategory, ItemLike pResult, int pResultCount) {
        return new ModShapelessRecipeBuilder(recipeOutput, pCategory, pResult, pResultCount, "");
    }

    public static ModShapelessRecipeBuilder shapeless(RecipeOutput recipeOutput, RecipeCategory pCategory, ItemLike pResult, String extension) {
        return new ModShapelessRecipeBuilder(recipeOutput, pCategory, pResult, 1, extension);
    }

    public static ModShapelessRecipeBuilder shapeless(RecipeOutput recipeOutput, RecipeCategory pCategory, ItemLike pResult, int pResultCount, String extension) {
        return new ModShapelessRecipeBuilder(recipeOutput, pCategory, pResult, pResultCount, extension);
    }

    public static ModShapelessRecipeBuilder shapeless(@NotNull RecipeOutput pOutput, @NotNull RecipeCategory recipeCategory,
                                    @NotNull ItemLike pResult, int numMade, @NotNull List<ObjectNumbered<ItemLike>> ingredientsList) {
        return shapeless(pOutput, recipeCategory, pResult, numMade, ingredientsList, "");
    }

    public static ModShapelessRecipeBuilder shapeless(@NotNull RecipeOutput pOutput, @NotNull RecipeCategory recipeCategory,
                                    @NotNull ItemLike pResult, int numMade, @NotNull List<ObjectNumbered<ItemLike>> ingredientsList,
                                    String extension) {
        return ModShapelessRecipeBuilder.shapeless(pOutput, recipeCategory, pResult, numMade, extension).requires(ingredientsList);
    }

    public ModShapelessRecipeBuilder requires(List<ObjectNumbered<ItemLike>> listItemLikeNumbered) {
        for (ObjectNumbered<ItemLike> itemLikeNumbered : listItemLikeNumbered) {
            this.requires(itemLikeNumbered);
        }
        return this;
    }

    public ModShapelessRecipeBuilder requires(ObjectNumbered<ItemLike> itemLikeNumbered) {
        return this.requires(Ingredient.of(itemLikeNumbered.getObject()), itemLikeNumbered.getCount())
                .unlockedBy(getItemName(itemLikeNumbered.getObject()), has(itemLikeNumbered.getObject()));
    }

    public ModShapelessRecipeBuilder requires(ItemLike pIngredient) {
        return this.requires(Ingredient.of(pIngredient), 1);
    }

    public ModShapelessRecipeBuilder requires(ItemLike pIngredient, int pNumNeeded) {
        return this.requires(Ingredient.of(pIngredient), pNumNeeded);
    }

    public ModShapelessRecipeBuilder requires(Ingredient pIngredient) {
        return this.requires(pIngredient, 1);
    }

    public void fromMod() {
        this.isFromMinecraft = false;
    }

    public void fromMinecraft() {
        this.isFromMinecraft = true;
    }

    public void fromMinecraft(boolean isFromMinecraft) {
        this.isFromMinecraft = isFromMinecraft;
    }

    public ModShapelessRecipeBuilder requires(Ingredient pIngredient, int pNumNeeded) {
        for (int i = 0; i < pNumNeeded; ++i) {
            this.ingredients.add(pIngredient);
        }
        return this;
    }

    public @NotNull ModShapelessRecipeBuilder unlockedBy(@NotNull String pUnlockString, @NotNull Criterion<?> pCriteria) {
        this.criteria.put(pUnlockString, pCriteria);
        return this;
    }

    public @NotNull ModShapelessRecipeBuilder group(@Nullable String pGroup) {
        this.group = pGroup;
        return this;
    }

    public @NotNull ModShapelessRecipeBuilder extension(@NotNull String pExtension) {
        this.extension = pExtension;
        return this;
    }

    public @NotNull Item getResult() {
        return this.result;
    }

    public void save() {
        String resLocPrefix = this.isFromMinecraft ? "minecraft:" : TechPlusPlus.MOD_ID + ":";
        ResourceLocation resLoc = new ResourceLocation(resLocPrefix + getItemName(this.getResult()) + extension);
        this.save(this.recipeOutput, resLoc);
    }

    public void save(RecipeOutput pOutput, @NotNull ResourceLocation pResLoc) {
        this.ensureValid(pResLoc);

        Advancement.Builder advancement$builder = pOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pResLoc))
                .rewards(AdvancementRewards.Builder.recipe(pResLoc))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);

        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(
                Objects.requireNonNullElse(this.group, ""),
                RecipeBuilder.determineBookCategory(this.category),
                new ItemStack(this.result, this.count),
                this.ingredients);

        AdvancementHolder advancementHolder = advancement$builder.build(pResLoc.withPrefix("recipes/" + this.category.getFolderName() + "/"));

        pOutput.accept(pResLoc, shapelessRecipe, advancementHolder);
    }

    private void ensureValid(ResourceLocation pResLoc) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pResLoc);
        }
    }
}
