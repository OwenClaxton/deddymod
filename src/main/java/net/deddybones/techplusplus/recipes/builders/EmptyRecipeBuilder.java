package net.deddybones.techplusplus.recipes.builders;

import net.deddybones.techplusplus.recipes.EmptyRecipe;
import net.minecraft.advancements.*;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptyRecipeBuilder implements RecipeBuilder {

    public EmptyRecipeBuilder() {
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String p_176496_, @NotNull Criterion<?> p_297505_) {
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String p_176495_) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return ItemStack.EMPTY.getItem();
    }

    @Override
    public void save(RecipeOutput pOutput, @NotNull ResourceLocation pResourceLocation) {
        pOutput.accept(pResourceLocation, new EmptyRecipe(), null);
    }
}
