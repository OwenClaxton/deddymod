package net.deddybones.techplusplus.recipes;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.util.crafting.EmptyRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TechPlusPlus.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TechPlusPlus.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> EMPTY_SERIALIZER = RECIPE_SERIALIZERS.register("empty", EmptyRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<?>> EMPTY_TYPE = RECIPE_TYPES.register("empty", () -> new RecipeType<>() {});


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
