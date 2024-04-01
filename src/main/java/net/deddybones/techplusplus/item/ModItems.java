package net.deddybones.techplusplus.item;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TechPlusPlus.MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_PLASTIMETAL = ITEMS.register("raw_plastimetal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_NUGGET = ITEMS.register("plastimetal_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_BILLET = ITEMS.register("plastimetal_billet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_INGOT = ITEMS.register("plastimetal_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BILLET = ITEMS.register("copper_billet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_BILLET = ITEMS.register("tin_billet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_BRONZE = ITEMS.register("raw_bronze",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BILLET = ITEMS.register("bronze_billet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_BILLET = ITEMS.register("iron_billet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_BILLET = ITEMS.register("gold_billet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_BILLET = ITEMS.register("netherite_billet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GPS_TOOL = ITEMS.register("gps_tool",
            () -> new GPSItem(new Item.Properties().durability(200)));

    public static final RegistryObject<Item> COFFEE_FOOD = ITEMS.register("coffee_food",
            () -> new Item(new Item.Properties().food(ModFoods.COFFEE_FOOD)));
    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register("coffee_beans",
            () -> new ItemNameBlockItem(ModBlocks.COFFEE_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> FIBROSIA_SEEDS = ITEMS.register("fibrosia_seeds",
            () -> new ItemNameBlockItem(ModBlocks.FIBROSIA_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> PLANT_FIBERS = ITEMS.register("plant_fibers",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STICK_BUNDLE = ITEMS.register("stick_bundle",
            () -> new FuelItem(new Item.Properties(), 1000));
    public static final RegistryObject<Item> KNAPPED_FLINT = ITEMS.register("knapped_flint",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_HANDLE = ITEMS.register("wooden_handle",
            () -> new CarvedFuelItem(new Item.Properties(), 200));
    public static final RegistryObject<Item> CLAY_CHUNK = ITEMS.register("clay_chunk",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_CLAY_CHUNK = ITEMS.register("large_clay_chunk",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> WOODEN_SPEAR = ITEMS.register("wooden_spear",
            () -> new SpearItem(new Item.Properties().durability(50)));
    public static final RegistryObject<Item> FLINT_KNIFE = ITEMS.register("flint_knife",
            () -> new CarverItem(ModToolTiers.PRIMITIVE, 4, 2, new Item.Properties().durability(20)));
    public static final RegistryObject<Item> STONE_MATTOCK = ITEMS.register("stone_mattock",
            () -> new SwordItem(ModToolTiers.PRIMITIVE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> PLASTIMETAL_SWORD = ITEMS.register("plastimetal_sword",
            () -> new SwordItem(ModToolTiers.PLASTIMETAL, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_PICKAXE = ITEMS.register("plastimetal_pickaxe",
            () -> new PickaxeItem(ModToolTiers.PLASTIMETAL, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_AXE = ITEMS.register("plastimetal_axe",
            () -> new AxeItem(ModToolTiers.PLASTIMETAL, 7, 1, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_SHOVEL = ITEMS.register("plastimetal_shovel",
            () -> new ShovelItem(ModToolTiers.PLASTIMETAL, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_HOE = ITEMS.register("plastimetal_hoe",
            () -> new HoeItem(ModToolTiers.PLASTIMETAL, 0, 0, new Item.Properties()));

    public static final RegistryObject<Item> PLASTIMETAL_HELMET = ITEMS.register("plastimetal_helmet",
            () -> new ModArmorItem(ModArmorMaterials.PLASTIMETAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_CHESTPLATE = ITEMS.register("plastimetal_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.PLASTIMETAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_LEGGINGS = ITEMS.register("plastimetal_leggings",
            () -> new ModArmorItem(ModArmorMaterials.PLASTIMETAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_BOOTS = ITEMS.register("plastimetal_boots",
            () -> new ModArmorItem(ModArmorMaterials.PLASTIMETAL, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> PLASTIMETAL_HORSE_ARMOR = ITEMS.register("plastimetal_horse_armor",
            () -> new ModHorseArmorItem(6, "plastimetal", (new Item.Properties()).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
