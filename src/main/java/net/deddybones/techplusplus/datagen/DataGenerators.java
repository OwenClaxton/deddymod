package net.deddybones.techplusplus.datagen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.util.EquipmentCollection;
import net.deddybones.techplusplus.util.OreCollection;
import net.deddybones.techplusplus.util.ComponentCollection;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TechPlusPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    private static void populateEnums() {
        OreCollection.COPPER.setAllItemLikes(           ModItems.COPPER_NUGGET.get(),      ModItems.COPPER_BILLET.get(),       Items.RAW_COPPER,               Items.COPPER_INGOT,               Blocks.RAW_COPPER_BLOCK,               Blocks.COPPER_BLOCK);
        OreCollection.TIN.setAllItemLikes(              ModItems.TIN_NUGGET.get(),         ModItems.TIN_BILLET.get(),          ModItems.RAW_TIN.get(),         ModItems.TIN_INGOT.get(),         ModBlocks.RAW_TIN_BLOCK.get(),         ModBlocks.TIN_BLOCK.get());
        OreCollection.GOLD.setAllItemLikes(             Items.GOLD_NUGGET,                 ModItems.GOLD_BILLET.get(),         Items.RAW_GOLD,                 Items.GOLD_INGOT,                 Blocks.RAW_GOLD_BLOCK,                 Blocks.GOLD_BLOCK);
        OreCollection.BRONZE.setAllItemLikes(           ModItems.BRONZE_NUGGET.get(),      ModItems.BRONZE_BILLET.get(),       ModItems.RAW_BRONZE.get(),      ModItems.BRONZE_INGOT.get(),      ModBlocks.RAW_BRONZE_BLOCK.get(),      ModBlocks.BRONZE_BLOCK.get());
        OreCollection.IRON.setAllItemLikes(             Items.IRON_NUGGET,                 ModItems.IRON_BILLET.get(),         Items.RAW_IRON,                 Items.IRON_INGOT,                 Blocks.RAW_IRON_BLOCK,                 Blocks.IRON_BLOCK);
        OreCollection.PLASTIMETAL.setAllItemLikes(      ModItems.PLASTIMETAL_NUGGET.get(), ModItems.PLASTIMETAL_BILLET.get(),  ModItems.RAW_PLASTIMETAL.get(), ModItems.PLASTIMETAL_INGOT.get(), ModBlocks.RAW_PLASTIMETAL_BLOCK.get(), ModBlocks.PLASTIMETAL_BLOCK.get());
        OreCollection.NETHERITE.setAllItemLikes(        ModItems.NETHERITE_NUGGET.get(),   ModItems.NETHERITE_BILLET.get(),    ModItems.RAW_NETHERITE.get(),   Items.NETHERITE_INGOT,            ModBlocks.RAW_NETHERITE_BLOCK.get(),   Blocks.NETHERITE_BLOCK);

        EquipmentCollection.COPPER.setAllItemLikes(     ModItems.COPPER_SWORD.get(),      ModItems.COPPER_AXE.get(),      ModItems.COPPER_HOE.get(),      ModItems.COPPER_PICKAXE.get(),      ModItems.COPPER_SHOVEL.get(),      ModItems.COPPER_HELMET.get(),      ModItems.COPPER_CHESTPLATE.get(),      ModItems.COPPER_LEGGINGS.get(),      ModItems.COPPER_BOOTS.get(),      ModItems.COPPER_HORSE_ARMOR.get());
        EquipmentCollection.TIN.setAllItemLikes(        ModItems.TIN_SWORD.get(),         ModItems.TIN_AXE.get(),         ModItems.TIN_HOE.get(),         ModItems.TIN_PICKAXE.get(),         ModItems.TIN_SHOVEL.get(),         ModItems.TIN_HELMET.get(),         ModItems.TIN_CHESTPLATE.get(),         ModItems.TIN_LEGGINGS.get(),         ModItems.TIN_BOOTS.get(),         ModItems.TIN_HORSE_ARMOR.get());
        EquipmentCollection.GOLD.setAllItemLikes(       Items.GOLDEN_SWORD,               Items.GOLDEN_AXE,               Items.GOLDEN_HOE,               Items.GOLDEN_PICKAXE,               Items.GOLDEN_SHOVEL,               Items.GOLDEN_HELMET,               Items.GOLDEN_CHESTPLATE,               Items.GOLDEN_LEGGINGS,               Items.GOLDEN_BOOTS,               Items.GOLDEN_HORSE_ARMOR);
        EquipmentCollection.BRONZE.setAllItemLikes(     ModItems.BRONZE_SWORD.get(),      ModItems.BRONZE_AXE.get(),      ModItems.BRONZE_HOE.get(),      ModItems.BRONZE_PICKAXE.get(),      ModItems.BRONZE_SHOVEL.get(),      ModItems.BRONZE_HELMET.get(),      ModItems.BRONZE_CHESTPLATE.get(),      ModItems.BRONZE_LEGGINGS.get(),      ModItems.BRONZE_BOOTS.get(),      ModItems.BRONZE_HORSE_ARMOR.get());
        EquipmentCollection.IRON.setAllItemLikes(       Items.IRON_SWORD,                 Items.IRON_AXE,                 Items.IRON_HOE,                 Items.IRON_PICKAXE,                 Items.IRON_SHOVEL,                 Items.IRON_HELMET,                 Items.IRON_CHESTPLATE,                 Items.IRON_LEGGINGS,                 Items.IRON_BOOTS,                 Items.IRON_HORSE_ARMOR);
        EquipmentCollection.PLASTIMETAL.setAllItemLikes(ModItems.PLASTIMETAL_SWORD.get(), ModItems.PLASTIMETAL_AXE.get(), ModItems.PLASTIMETAL_HOE.get(), ModItems.PLASTIMETAL_PICKAXE.get(), ModItems.PLASTIMETAL_SHOVEL.get(), ModItems.PLASTIMETAL_HELMET.get(), ModItems.PLASTIMETAL_CHESTPLATE.get(), ModItems.PLASTIMETAL_LEGGINGS.get(), ModItems.PLASTIMETAL_BOOTS.get(), ModItems.PLASTIMETAL_HORSE_ARMOR.get());
        EquipmentCollection.NETHERITE.setAllItemLikes(  Items.NETHERITE_SWORD,            Items.NETHERITE_AXE,            Items.NETHERITE_HOE,            Items.NETHERITE_PICKAXE,            Items.NETHERITE_SHOVEL,            Items.NETHERITE_HELMET,            Items.NETHERITE_CHESTPLATE,            Items.NETHERITE_LEGGINGS,            Items.NETHERITE_BOOTS,            ModItems.NETHERITE_HORSE_ARMOR.get());

        ComponentCollection.COPPER.setAllItemLikes(     ModItems.COPPER_ROD.get(),      ModItems.FLAT_COPPER_PANEL.get(),      ModItems.ROUND_COPPER_PANEL.get(),      ModItems.COPPER_DISK.get(),      ModItems.COPPER_PICKAXE_PART.get(),      ModItems.COPPER_AXE_PART.get(),      ModItems.COPPER_SWORD_PARTS.get(),      ModItems.COPPER_HOE_PART.get(),      ModItems.COPPER_SHOVEL_PART.get());
        ComponentCollection.TIN.setAllItemLikes(        ModItems.TIN_ROD.get(),         ModItems.FLAT_TIN_PANEL.get(),         ModItems.ROUND_TIN_PANEL.get(),         ModItems.TIN_DISK.get(),         ModItems.TIN_PICKAXE_PART.get(),         ModItems.TIN_AXE_PART.get(),         ModItems.TIN_SWORD_PARTS.get(),         ModItems.TIN_HOE_PART.get(),         ModItems.TIN_SHOVEL_PART.get());
        ComponentCollection.GOLD.setAllItemLikes(       ModItems.GOLD_ROD.get(),        ModItems.FLAT_GOLD_PANEL.get(),        ModItems.ROUND_GOLD_PANEL.get(),        ModItems.GOLD_DISK.get(),        ModItems.GOLDEN_PICKAXE_PART.get(),      ModItems.GOLDEN_AXE_PART.get(),      ModItems.GOLDEN_SWORD_PARTS.get(),      ModItems.GOLDEN_HOE_PART.get(),      ModItems.GOLDEN_SHOVEL_PART.get());
        ComponentCollection.BRONZE.setAllItemLikes(     ModItems.BRONZE_ROD.get(),      ModItems.FLAT_BRONZE_PANEL.get(),      ModItems.ROUND_BRONZE_PANEL.get(),      ModItems.BRONZE_DISK.get(),      ModItems.BRONZE_PICKAXE_PART.get(),      ModItems.BRONZE_AXE_PART.get(),      ModItems.BRONZE_SWORD_PARTS.get(),      ModItems.BRONZE_HOE_PART.get(),      ModItems.BRONZE_SHOVEL_PART.get());
        ComponentCollection.IRON.setAllItemLikes(       ModItems.IRON_ROD.get(),        ModItems.FLAT_IRON_PANEL.get(),        ModItems.ROUND_IRON_PANEL.get(),        ModItems.IRON_DISK.get(),        ModItems.IRON_PICKAXE_PART.get(),        ModItems.IRON_AXE_PART.get(),        ModItems.IRON_SWORD_PARTS.get(),        ModItems.IRON_HOE_PART.get(),        ModItems.IRON_SHOVEL_PART.get());
        ComponentCollection.PLASTIMETAL.setAllItemLikes(ModItems.PLASTIMETAL_ROD.get(), ModItems.FLAT_PLASTIMETAL_PANEL.get(), ModItems.ROUND_PLASTIMETAL_PANEL.get(), ModItems.PLASTIMETAL_DISK.get(), ModItems.PLASTIMETAL_PICKAXE_PART.get(), ModItems.PLASTIMETAL_AXE_PART.get(), ModItems.PLASTIMETAL_SWORD_PARTS.get(), ModItems.PLASTIMETAL_HOE_PART.get(), ModItems.PLASTIMETAL_SHOVEL_PART.get());
        ComponentCollection.NETHERITE.setAllItemLikes(  ModItems.NETHERITE_ROD.get(),   ModItems.FLAT_NETHERITE_PANEL.get(),   ModItems.ROUND_NETHERITE_PANEL.get(),   ModItems.NETHERITE_DISK.get(),   ModItems.NETHERITE_PICKAXE_PART.get(),   ModItems.NETHERITE_AXE_PART.get(),   ModItems.NETHERITE_SWORD_PARTS.get(),   ModItems.NETHERITE_HOE_PART.get(),   ModItems.NETHERITE_SHOVEL_PART.get());
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        populateEnums();

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

        ModBlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(),
                new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(),
                new ModItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new ModLootTableProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));

    }
}
