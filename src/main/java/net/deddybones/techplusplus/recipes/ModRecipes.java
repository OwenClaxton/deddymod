package net.deddybones.techplusplus.recipes;

import net.deddybones.techplusplus.TechPlusPlus;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TechPlusPlus.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TechPlusPlus.MOD_ID);

    public static final RegistryObject<RecipeSerializer<EmptyRecipe>> EMPTY_SERIALIZER      = RECIPE_SERIALIZERS.register("empty", EmptyRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<CrusherRecipe>> CRUSHING_SERIALIZER = RECIPE_SERIALIZERS.register("crushing", () -> new ModSingleItemRecipe.Serializer<>(CrusherRecipe::new));

    public static final RegistryObject<RecipeType<EmptyRecipe>> EMPTY_TYPE      = registerType("empty");
    public static final RegistryObject<RecipeType<CrusherRecipe>> CRUSHING_TYPE = registerType("crushing");

    static <T extends Recipe<?>> RegistryObject<RecipeType<T>> registerType(final String pId) {
        return RECIPE_TYPES.register(pId, () -> new RecipeType<>() {
            public String toString() {
                return pId;
            }
        });
    }

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
