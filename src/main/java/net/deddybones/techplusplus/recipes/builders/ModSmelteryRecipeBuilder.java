package net.deddybones.techplusplus.recipes.builders;

import net.deddybones.techplusplus.datagen.util.ModHelper;
import net.deddybones.techplusplus.recipes.SmelteryRecipe;
import net.deddybones.techplusplus.util.ObjectNumbered;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static net.deddybones.techplusplus.datagen.util.ModHelper.*;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ModSmelteryRecipeBuilder implements RecipeBuilder {
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private boolean isFromMinecraft = false;
    public RecipeOutput recipeOutput;

    private final Item result;
    private final int resultCount;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Ingredient mold;
    private @Nullable String group;
    private String extension; // such as _from_ore_type
    private int smeltTime;

    public ModSmelteryRecipeBuilder(RecipeOutput recipeOutput, ItemLike pResult, int pResultCount,
                                    Ingredient pMold, int pSmeltTime, String extension) {
        this.recipeOutput = recipeOutput;
        this.result = pResult.asItem();
        this.resultCount = pResultCount;
        this.group = getItemName(this.result);
        this.mold = pMold;
        this.extension = extension;
        this.smeltTime = pSmeltTime;
    }

    public static ModSmelteryRecipeBuilder smeltery(RecipeOutput recipeOutput, ItemLike pResult,
                                                    Ingredient pMold, int pSmeltTime) {
        // didn't specify pResultCount and pExtension
        return new ModSmelteryRecipeBuilder(recipeOutput, pResult, 1, pMold, pSmeltTime, "");
    }

    public static ModSmelteryRecipeBuilder smeltery(RecipeOutput recipeOutput, ItemLike pResult,
                                                    int pResultCount, Ingredient pMold, int pSmeltTime) {
        // didn't specify pExtension
        return new ModSmelteryRecipeBuilder(recipeOutput, pResult, pResultCount, pMold, pSmeltTime, "");
    }

    public static ModSmelteryRecipeBuilder smeltery(RecipeOutput recipeOutput, ItemLike pResult,
                                                    Ingredient pMold, int pSmeltTime, String extension) {
        // didn't specify pResultCount
        return new ModSmelteryRecipeBuilder(recipeOutput, pResult, 1, pMold, pSmeltTime, extension);
    }

    public static ModSmelteryRecipeBuilder smeltery(RecipeOutput recipeOutput, ItemLike pResult,
                                                    int pResultCount, Ingredient pMold, int pSmeltTime,
                                                    String pExtension) {
        // specified all
        return new ModSmelteryRecipeBuilder(recipeOutput, pResult, pResultCount, pMold, pSmeltTime, pExtension);
    }

    public ModSmelteryRecipeBuilder requires(List<ObjectNumbered<ItemLike>> listItemLikeNumbered) {
        for (ObjectNumbered<ItemLike> itemLikeNumbered : listItemLikeNumbered) {
            this.requires(itemLikeNumbered);
        }
        return this;
    }

    public ModSmelteryRecipeBuilder requires(ObjectNumbered<ItemLike> itemLikeNumbered) {
        return this.requires(Ingredient.of(itemLikeNumbered.getObject()), itemLikeNumbered.getCount())
                .unlockedBy(getItemName(itemLikeNumbered.getObject()), has(itemLikeNumbered.getObject()));
    }

    public ModSmelteryRecipeBuilder requires(ItemLike pIngredient) {
        return this.requires(Ingredient.of(pIngredient), 1);
    }

    public ModSmelteryRecipeBuilder requires(ItemLike pIngredient, int pNumNeeded) {
        return this.requires(Ingredient.of(pIngredient), pNumNeeded);
    }

    public ModSmelteryRecipeBuilder requires(Ingredient pIngredient) {
        return this.requires(pIngredient, 1);
    }

    public ModSmelteryRecipeBuilder requires(Ingredient pIngredient, int pNumNeeded) {
        for (int i = 0; i < pNumNeeded; ++i) {
            this.ingredients.add(pIngredient);
        }
        return this;
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

    public @NotNull ModSmelteryRecipeBuilder unlockedBy(@NotNull String pUnlockString, @NotNull Criterion<?> pCriteria) {
        this.criteria.put(pUnlockString, pCriteria);
        return this;
    }

    public @NotNull ModSmelteryRecipeBuilder group(@Nullable String pGroup) {
        this.group = pGroup;
        return this;
    }

    public @NotNull ModSmelteryRecipeBuilder extension(@NotNull String pExtension) {
        this.extension = pExtension;
        return this;
    }

    public @NotNull ModSmelteryRecipeBuilder smeltTime(int pSmeltTime) {
        this.smeltTime = pSmeltTime;
        return this;
    }

    public @NotNull Item getResult() {
        return this.result;
    }

    public void save() {
        Function<String, ResourceLocation> locFunc = this.isFromMinecraft ? ModHelper::resLoc : ModHelper::modLoc;
        this.save(this.recipeOutput, locFunc.apply(getItemName(this.getResult()) + extension));
    }

    public void save(RecipeOutput pOutput, @NotNull ResourceLocation pResLoc) {
        this.ensureValid(pResLoc);

        Advancement.Builder advancement$builder = pOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pResLoc))
                .rewards(AdvancementRewards.Builder.recipe(pResLoc))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);

        SmelteryRecipe shapelessRecipe = new SmelteryRecipe(
                Objects.requireNonNullElse(this.group, ""),
                new ItemStack(this.result, this.resultCount),
                this.ingredients,
                this.mold,
                this.smeltTime);

        AdvancementHolder advancementHolder = advancement$builder.build(pResLoc.withPrefix("recipes/smeltery/"));

        pOutput.accept(pResLoc, shapelessRecipe, advancementHolder);
    }

    private void ensureValid(ResourceLocation pResLoc) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pResLoc);
        }
    }
}
