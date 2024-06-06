package net.deddybones.techplusplus;

import com.google.common.collect.ImmutableList;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.block.TweakedVanillaBlocks;
import net.deddybones.techplusplus.block.entity.ModBlockEntities;
import net.deddybones.techplusplus.entity.ModEntities;
import net.deddybones.techplusplus.item.util.ModArmorMaterials;
import net.deddybones.techplusplus.util.ModCreativeModeTabs;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.item.TweakedVanillaItems;
import net.deddybones.techplusplus.loot.ModLootModifiers;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.deddybones.techplusplus.gui.ModMenuTypes;
import net.deddybones.techplusplus.worldgen.ModWorldGen;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TechPlusPlus.MOD_ID)
public class TechPlusPlus {
    public static final String MOD_ID = "techplusplus";

    public static final RecipeBookType KILN_RECIPE_BOOK_TYPE = RecipeBookType.create("KILN");
    public static final RecipeBookCategories KILN_SEARCH = RecipeBookCategories.create("KILN_SEARCH", new ItemStack(Items.COMPASS));
    public static final RecipeBookCategories KILN_FOOD = RecipeBookCategories.create("KILN_FOOD", new ItemStack(Items.PORKCHOP));
    public static final RecipeBookCategories KILN_MISC = RecipeBookCategories.create("KILN_MISC", new ItemStack(Items.IRON_NUGGET));
    public static final List<RecipeBookCategories> KILN_CATEGORIES = ImmutableList.of(KILN_SEARCH, KILN_FOOD, KILN_MISC);
    public static final RecipeBookType CRUSHER_RECIPE_BOOK_TYPE = RecipeBookType.create("CRUSHER");
    public static final RecipeBookCategories CRUSHER_SEARCH = RecipeBookCategories.create("CRUSHER_SEARCH", new ItemStack(Items.COMPASS));
    public static final RecipeBookCategories CRUSHER_MISC = RecipeBookCategories.create("CRUSHER_MISC", new ItemStack(Blocks.GRAVEL));
    public static final List<RecipeBookCategories> CRUSHER_CATEGORIES = ImmutableList.of(CRUSHER_SEARCH, CRUSHER_MISC);
//    public static final ResourceKey<Registry<ModArmorMaterial>> MOD_ARMOR_MATERIAL = ResourceKey.createRegistryKey(new ResourceLocation(MOD_ID, "mod_armor_material"));

    public TechPlusPlus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

//        modEventBus.addListener(this::addCustomRegistries);
        ModArmorMaterials.register(modEventBus);

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
//        modEventBus.addListener(this::addCreative);
    }

//    private void addCustomRegistries(DataPackRegistryEvent.NewRegistry event) {
//        event.dataPackRegistry(MOD_ARMOR_MATERIAL, ModArmorMaterial.CODEC);
//    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->
        {
            ItemProperties.register(ModItems.WOODEN_SPEAR.get(),
                    new ResourceLocation(TechPlusPlus.MOD_ID, "throwing"), (stack, level, living, id) -> {
                        return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
                    });
        });
    }

//    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
//            event.accept(ModItems.SAPPHIRE);
//        }
//    }
}
