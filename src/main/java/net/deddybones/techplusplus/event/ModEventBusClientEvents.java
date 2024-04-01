package net.deddybones.techplusplus.event;

import com.google.common.collect.ImmutableList;
import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.entity.ModEntities;
import net.deddybones.techplusplus.entity.client.ModModelLayers;
import net.deddybones.techplusplus.entity.client.ThrownWoodenSpearModel;
import net.deddybones.techplusplus.entity.client.ThrownWoodenSpearRenderer;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.deddybones.techplusplus.screen.ClayMolderScreen;
import net.deddybones.techplusplus.screen.CrusherScreen;
import net.deddybones.techplusplus.screen.KilnScreen;
import net.deddybones.techplusplus.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TechPlusPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.THROWN_WOODEN_SPEAR_ENTITY_TYPE.get(), ThrownWoodenSpearRenderer::new);

        MenuScreens.register(ModMenuTypes.CRUSHER.get(), CrusherScreen::new);
        MenuScreens.register(ModMenuTypes.KILN.get(), KilnScreen::new);
        MenuScreens.register(ModMenuTypes.CLAY_MOLDER.get(), ClayMolderScreen::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.THROWN_WOODEN_SPEAR_LAYER, ThrownWoodenSpearModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRecipeBookCategoriesEvent(RegisterRecipeBookCategoriesEvent event) {
        event.registerBookCategories(TechPlusPlus.KILN_RECIPE_BOOK_TYPE, TechPlusPlus.KILN_CATEGORIES);
        event.registerAggregateCategory(TechPlusPlus.KILN_SEARCH, ImmutableList.of(TechPlusPlus.KILN_SEARCH, TechPlusPlus.KILN_FOOD, TechPlusPlus.KILN_MISC));
        event.registerRecipeCategoryFinder(ModRecipes.KILN_TYPE.get(), recipe -> {
            AbstractCookingRecipe abstractCookingRecipe = (AbstractCookingRecipe) recipe;
            CookingBookCategory cookingBookCategory = abstractCookingRecipe.category();
            return cookingBookCategory == CookingBookCategory.MISC ? TechPlusPlus.KILN_MISC : TechPlusPlus.KILN_FOOD;
        });
    }

}
