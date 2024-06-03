package net.deddybones.techplusplus.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ModSingleItemRecipe implements Recipe<Container> {
    protected final Ingredient ingredient;
    protected final ItemStack result;
    private final RecipeType<?> type;
    private final RecipeSerializer<?> serializer;
    protected final String group;

    public ModSingleItemRecipe(RecipeType<?> pType, RecipeSerializer<?> pSerializer, String pGroup, Ingredient pIngredient, ItemStack pResult) {
        this.type           = pType;
        this.serializer     = pSerializer;
        this.group          = pGroup;
        this.ingredient     = pIngredient;
        this.result         = pResult;
    }

    public @NotNull RecipeType<?> getType() {
        return this.type;
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    public @NotNull String getGroup() {
        return this.group;
    }

    public @NotNull ItemStack getResultItem(@Nullable HolderLookup.Provider pHLP) {
        return this.result;
    }

    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public boolean canCraftInDimensions(int pGridWidth, int pGridHeight) {
        return true;
    }

    public @NotNull ItemStack assemble(@NotNull Container pContainer, @Nullable HolderLookup.Provider pHLP) {
        return this.result.copy();
    }

    public boolean matches(Container pContainer, @Nullable Level pLevel) {
        return this.ingredient.test(pContainer.getItem(0));
    }

    public ItemStack getResult() {
        return this.result;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public abstract @NotNull ItemStack getToastSymbol();

    public interface Factory<T extends ModSingleItemRecipe> {
        T create(String pString, Ingredient pIngredient, ItemStack pItemStack);
    }

    public static class Serializer<T extends ModSingleItemRecipe> implements RecipeSerializer<T> {
        final ModSingleItemRecipe.Factory<T> factory;
        private final MapCodec<T> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

        protected Serializer(ModSingleItemRecipe.Factory<T> p_311205_) {
            this.factory = p_311205_;
            this.codec = RecordCodecBuilder.mapCodec(
                    p_327217_ -> p_327217_.group(
                                    Codec.STRING.optionalFieldOf("group", "").forGetter(p_298324_ -> p_298324_.group),
                                    Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(p_299566_ -> p_299566_.ingredient),
                                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_301692_ -> p_301692_.result)
                            )
                            .apply(p_327217_, p_311205_::create)
            );
            this.streamCodec = StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    p_327219_ -> p_327219_.group,
                    Ingredient.CONTENTS_STREAM_CODEC,
                    p_327218_ -> p_327218_.ingredient,
                    ItemStack.STREAM_CODEC,
                    p_327215_ -> p_327215_.result,
                    p_311205_::create
            );
        }

        @Override
        public @NotNull MapCodec<T> codec() {
            return this.codec;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
            return this.streamCodec;
        }
    }
}
