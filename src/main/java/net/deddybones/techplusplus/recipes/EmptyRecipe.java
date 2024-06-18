package net.deddybones.techplusplus.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptyRecipe implements Recipe<RecipeInput> {
    protected final String type; // Necessary to populate the serializer

    // Necessary to populate the serializer
    public EmptyRecipe(String type) {
        this.type = type;
    }

    public EmptyRecipe() {
        this.type = "empty";
    }

    public @NotNull RecipeType<?> getType() {
        return ModRecipes.EMPTY_TYPE.get();
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.EMPTY_SERIALIZER.get();
    }

    public @NotNull ItemStack getResultItem(@Nullable HolderLookup.Provider pHLP) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean matches(@NotNull RecipeInput pRecipeInput, @NotNull Level pLevel) {
        return false;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RecipeInput pRecipeInput, @NotNull HolderLookup.Provider pProvider) {
        return ItemStack.EMPTY;
    }

    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }

    public interface Factory<T extends EmptyRecipe> {
        T create(String type);
    }

    public static class Serializer<T extends EmptyRecipe> implements RecipeSerializer<T> {
        final EmptyRecipe.Factory<T> factory;
        private final MapCodec<T> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

        protected Serializer(EmptyRecipe.Factory<T> pFactory) {
            this.factory = pFactory;
            this.codec = RecordCodecBuilder.mapCodec(
                    p_327217_ -> p_327217_.group(
                                    Codec.STRING.optionalFieldOf("type", "")
                                            .forGetter(emptyRecipeInstance -> emptyRecipeInstance.type))
                            .apply(p_327217_, pFactory::create)
            );
            this.streamCodec = StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    emptyRecipeInstance -> emptyRecipeInstance.type,
                    pFactory::create
            );
        }

        public @NotNull MapCodec<T> codec() {
            return this.codec;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
            return this.streamCodec;
        }
    }
}
