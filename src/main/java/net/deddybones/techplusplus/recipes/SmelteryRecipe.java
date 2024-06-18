package net.deddybones.techplusplus.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.block.entity.SmelteryBlockEntity;
import net.deddybones.techplusplus.recipes.inputs.SmelteryInput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SmelteryRecipe implements Recipe<SmelteryInput> {
    protected final String group;
    protected final ItemStack result;
    protected final NonNullList<Ingredient> materialsIng;
    protected final Ingredient mold;
    protected final int smeltTime;
    public static final int MAX_INGREDIENTS_WIDTH = 3;
    public static final int MAX_INGREDIENTS_HEIGHT = 3;
    public static final int MAX_INGREDIENTS = MAX_INGREDIENTS_WIDTH * MAX_INGREDIENTS_HEIGHT;

    public SmelteryRecipe(String pGroup, ItemStack pResult,
                          NonNullList<Ingredient> pMaterialsIng,
                          Ingredient pMold, int pSmeltTime) {
        this.group = pGroup;
        this.result = pResult;
        this.materialsIng = pMaterialsIng;
        this.mold = pMold;
        this.smeltTime = pSmeltTime;
    }

    @Override
    public boolean matches(@NotNull SmelteryInput pInput, @NotNull Level pLevel) {
        return this.mold.test(pInput.getMold())
                && smelteryMaterialsMatch(pInput.getMaterials(), this.materialsIng);
    }

    public static boolean smelteryMaterialsMatch(List<ItemStack> pMaterials, NonNullList<Ingredient> pMaterialsIng) {
        ArrayList<ItemStack> containerIngredients = new ArrayList<>();
        int numContainerIngredients = 0;
        for (int slotIndex = 0;
                slotIndex < SmelteryBlockEntity.SLOTS_MATERIALS_END - SmelteryBlockEntity.SLOTS_MATERIALS_START;
                slotIndex++) {
            ItemStack slotItem = pMaterials.get(slotIndex);
            if (!slotItem.isEmpty()) {
                numContainerIngredients++;
                containerIngredients.add(slotItem);
            }
        }
        return numContainerIngredients == pMaterialsIng.size()
                && RecipeMatcher.findMatches(containerIngredients, pMaterialsIng) != null;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SmelteryInput pInput, @NotNull HolderLookup.Provider pProvider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int gridWidth, int gridHeight) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull HolderLookup.Provider pProvider) {
        return this.result;
    }

    @Override
    public @NotNull String getGroup() {
        return this.group;
    }

    public @NotNull ItemStack getResult() {
        return this.result;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.materialsIng;
    }

    public @NotNull Ingredient getMold() {
        return this.mold;
    }

    public int getSmeltTime() {
        return this.smeltTime;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.SMELTERY.get());
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.SMELTERY_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.SMELTERY_TYPE.get();
    }

    @Override
    public boolean isIncomplete() {
        // check if ingredients via super, else check mold and result (unnecessary but why not).
        return Recipe.super.isIncomplete() || this.getMold().isEmpty() || this.getResult().isEmpty();
    }

    public interface Factory<T extends SmelteryRecipe> {
        T create(String group, ItemStack result,
                 NonNullList<Ingredient> ingredients,
                 Ingredient mold, int smeltTime);
    }

    public static class SmelterySerializer<T extends SmelteryRecipe> implements RecipeSerializer<T> {
        private final SmelteryRecipe.Factory<T> factory;
        private final MapCodec<T> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

        public SmelterySerializer(SmelteryRecipe.Factory<T> factory, int smeltTime) {
            this.factory = factory;
            this.codec = RecordCodecBuilder.mapCodec(
                            instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(SmelteryRecipe::getGroup),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(SmelteryRecipe::getResult),
                                Ingredient.CODEC_NONEMPTY
                                    .listOf().fieldOf("ingredients").flatXmap(
                                        ingList -> {
                                            Ingredient[] ingArr = ingList.stream()
                                                                        .filter(ingredient -> !ingredient.isEmpty())
                                                                        .toArray(Ingredient[]::new);
                                            if (ingArr.length == 0) {
                                                return DataResult.error(() -> "No ingredients for smeltery recipe");
                                            } else {
                                                return ingArr.length > MAX_INGREDIENTS
                                                ? DataResult.error(() -> "Too many ingredients for smeltery recipe")
                                                : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingArr));
                                            }
                                        }, DataResult::success)
                                    .forGetter(SmelteryRecipe::getIngredients),
                                Ingredient.CODEC_NONEMPTY.fieldOf("mold").forGetter(SmelteryRecipe::getMold),
                                Codec.INT.fieldOf("smeltTime").orElse(smeltTime).forGetter(SmelteryRecipe::getSmeltTime)
                            ).apply(instance, factory::create));
            this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
        }

        @Override
        public @NotNull MapCodec<T> codec() {
            return this.codec;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
            return this.streamCodec;
        }

        private T fromNetwork(RegistryFriendlyByteBuf regFBB) {
            String group = regFBB.readUtf();
            ItemStack result = ItemStack.STREAM_CODEC.decode(regFBB);
            int numIngredients = regFBB.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);
            ingredients.replaceAll(ingredient -> Ingredient.CONTENTS_STREAM_CODEC.decode(regFBB));
            Ingredient mold = Ingredient.CONTENTS_STREAM_CODEC.decode(regFBB);
            int smeltTime = regFBB.readVarInt();
            return this.factory.create(group, result, ingredients, mold, smeltTime);
        }

        private void toNetwork(RegistryFriendlyByteBuf regFBB, T pRecipe) {
            regFBB.writeUtf(pRecipe.getGroup());
            ItemStack.STREAM_CODEC.encode(regFBB, pRecipe.getResult());
            regFBB.writeVarInt(pRecipe.getIngredients().size());
            for (Ingredient ingredient : pRecipe.getIngredients())
                Ingredient.CONTENTS_STREAM_CODEC.encode(regFBB, ingredient);
            Ingredient.CONTENTS_STREAM_CODEC.encode(regFBB, pRecipe.getMold());
            regFBB.writeVarInt(pRecipe.getSmeltTime());
        }

        public SmelteryRecipe create(String pGroup, ItemStack pResult,
                                     NonNullList<Ingredient> pIngredients,
                                     Ingredient pMold, int smeltTime) {
            return this.factory.create(pGroup, pResult, pIngredients, pMold, smeltTime);
        }
    }
}
