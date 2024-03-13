package net.deddybones.techplusplus.util.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptyRecipe implements Recipe<Container> {

    String type;

    public EmptyRecipe() {
        this("empty");
    }

    public EmptyRecipe(String pType) {
        type = pType;
    }

    @Override
    public boolean matches(@NotNull Container p_44002_, @NotNull Level p_44003_) {
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull Container p_44001_, @NotNull RegistryAccess p_267165_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess p_267052_) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.EMPTY_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.EMPTY_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<EmptyRecipe> {
        //https://gist.github.com/Drullkus/1bca3f2d7f048b1fe03be97c28f87910
        public static final Codec<EmptyRecipe> CODEC = RecordCodecBuilder.create(
                (instance) -> instance.group(
                    Codec.STRING.fieldOf("type").forGetter((EmptyRecipe o) -> o.type)
                ).apply(instance, EmptyRecipe::new));

        public @NotNull Codec<EmptyRecipe> codec() {
            return CODEC;
        }

        @Override
        public @Nullable EmptyRecipe fromNetwork(@NotNull FriendlyByteBuf p_44106_) {
            return new EmptyRecipe();
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf p_44101_, @NotNull EmptyRecipe p_44102_) {

        }
    }
}
