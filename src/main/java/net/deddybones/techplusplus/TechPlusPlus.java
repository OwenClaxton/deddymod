package net.deddybones.techplusplus;

import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.block.TweakedVanillaBlocks;
import net.deddybones.techplusplus.block.entity.ModBlockEntities;
import net.deddybones.techplusplus.entity.ModEntities;
import net.deddybones.techplusplus.item.ModCreativeModeTabs;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.item.TweakedVanillaItems;
import net.deddybones.techplusplus.loot.ModLootModifiers;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.deddybones.techplusplus.screen.ModMenuTypes;
import net.deddybones.techplusplus.worldgen.ModWorldGen;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TechPlusPlus.MOD_ID)
public class TechPlusPlus {
    public static final String MOD_ID = "techplusplus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final RecipeBookType KILN_RECIPE_BOOK_TYPE = RecipeBookType.create("KILN");
    public static final RecipeBookCategories KILN_SEARCH = RecipeBookCategories.create("KILN_SEARCH", new ItemStack(Items.COMPASS));
    public static final RecipeBookCategories KILN_FOOD = RecipeBookCategories.create("KILN_FOOD", new ItemStack(Items.PORKCHOP));
    public static final RecipeBookCategories KILN_MISC = RecipeBookCategories.create("KILN_MISC", new ItemStack(Items.IRON_NUGGET));
    public static final List<RecipeBookCategories> KILN_CATEGORIES = ImmutableList.of(KILN_SEARCH, KILN_FOOD, KILN_MISC);

    public TechPlusPlus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        TweakedVanillaItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        TweakedVanillaBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModWorldGen.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        ModEntities.register(modEventBus);
        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
//        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TEST_BLOCK.get(), RenderType.cutout());
        event.enqueueWork(() ->
        {
            ItemProperties.register(ModItems.WOODEN_SPEAR.get(),
                    new ResourceLocation(TechPlusPlus.MOD_ID, "throwing"), (stack, level, living, id) -> {
                        return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
                    });
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SAPPHIRE);
        }
    }
}
