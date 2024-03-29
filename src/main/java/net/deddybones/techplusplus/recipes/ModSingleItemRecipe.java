package net.deddybones.techplusplus.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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

    public boolean matches(Container pContainer, @NotNull Level pLevel) {
        return this.ingredient.test(pContainer.getItem(0));
    }

    public abstract @NotNull ItemStack getToastSymbol();

    public @NotNull RecipeType<?> getType() {
        return this.type;
    }

    public @NotNull RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    public @NotNull String getGroup() {
        return this.group;
    }

    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess pAccess) {
        return this.result;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public boolean canCraftInDimensions(int pGridWidth, int pGridHeight) {
        return true;
    }

    public @NotNull ItemStack assemble(@NotNull Container pContainer, @NotNull RegistryAccess pAccess) {
        return this.result.copy();
    }

    public interface Factory<T extends ModSingleItemRecipe> {
        T create(String p_310227_, Ingredient p_313029_, ItemStack p_312409_);
    }

    public static class Serializer<T extends ModSingleItemRecipe> implements RecipeSerializer<T> {

        final ModSingleItemRecipe.Factory<T> factory;
        private final Codec<T> CODEC;

        protected Serializer(ModSingleItemRecipe.Factory<T> pFactory) {
            this.factory = pFactory;
            this.CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                                ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(T::getGroup),
                                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(T::getIngredient),
                                ItemStack.RESULT_CODEC.forGetter(T::getResult)
                        ).apply(instance, pFactory::create));
        }

        public @NotNull Codec<T> codec() {
            return CODEC;
        }

        @Override
        public T fromNetwork(FriendlyByteBuf pFBB) {
            String s = pFBB.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(pFBB);
            ItemStack itemstack = pFBB.readItem();
            return this.factory.create(s, ingredient, itemstack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pFBB, T pRecipe) {
            pFBB.writeUtf(pRecipe.group);
            pRecipe.ingredient.toNetwork(pFBB);
            pFBB.writeItem(pRecipe.result);
        }
    }
}
