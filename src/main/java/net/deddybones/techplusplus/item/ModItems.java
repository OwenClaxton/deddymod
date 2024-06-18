package net.deddybones.techplusplus.item;

import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.custom.*;
import net.deddybones.techplusplus.item.util.ModToolTiers;
import net.deddybones.techplusplus.util.TierCollection;
import net.deddybones.techplusplus.util.TierNumerics;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.deddybones.techplusplus.util.EquipmentCollection.EquipmentName;

import static net.deddybones.techplusplus.TechPlusPlus.MOD_ID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = simpleItem("sapphire");

    public static final RegistryObject<Item> RAW_PLASTIMETAL = simpleItem("raw_plastimetal");
    public static final RegistryObject<Item> RAW_NETHERITE = simpleItem("raw_netherite");
    public static final RegistryObject<Item> RAW_TIN = simpleItem("raw_tin");
    public static final RegistryObject<Item> RAW_BRONZE = simpleItem("raw_bronze");

    public static final RegistryObject<Item> PLASTIMETAL_NUGGET = simpleItem("plastimetal_nugget");
    public static final RegistryObject<Item> NETHERITE_NUGGET = simpleItem("netherite_nugget");
    public static final RegistryObject<Item> COPPER_NUGGET = simpleItem("copper_nugget");
    public static final RegistryObject<Item> TIN_NUGGET = simpleItem("tin_nugget");
    public static final RegistryObject<Item> BRONZE_NUGGET = simpleItem("bronze_nugget");

    public static final RegistryObject<Item> PLASTIMETAL_INGOT = simpleItem("plastimetal_ingot");
    public static final RegistryObject<Item> TIN_INGOT = simpleItem("tin_ingot");
    public static final RegistryObject<Item> BRONZE_INGOT = simpleItem("bronze_ingot");

    public static final RegistryObject<Item> IRON_BILLET = simpleItem("iron_billet");
    public static final RegistryObject<Item> PLASTIMETAL_BILLET = simpleItem("plastimetal_billet");
    public static final RegistryObject<Item> GOLD_BILLET = simpleItem("gold_billet");
    public static final RegistryObject<Item> NETHERITE_BILLET = simpleItem("netherite_billet");
    public static final RegistryObject<Item> COPPER_BILLET = simpleItem("copper_billet");
    public static final RegistryObject<Item> TIN_BILLET = simpleItem("tin_billet");
    public static final RegistryObject<Item> BRONZE_BILLET = simpleItem("bronze_billet");

    public static final RegistryObject<Item> IRON_ROD = simpleItem("iron_rod");
    public static final RegistryObject<Item> PLASTIMETAL_ROD = simpleItem("plastimetal_rod");
    public static final RegistryObject<Item> GOLD_ROD = simpleItem("gold_rod");
    public static final RegistryObject<Item> NETHERITE_ROD = simpleItem("netherite_rod");
    public static final RegistryObject<Item> COPPER_ROD = simpleItem("copper_rod");
    public static final RegistryObject<Item> TIN_ROD = simpleItem("tin_rod");
    public static final RegistryObject<Item> BRONZE_ROD = simpleItem("bronze_rod");

    public static final RegistryObject<Item> IRON_DISK = simpleItem("iron_disk");
    public static final RegistryObject<Item> PLASTIMETAL_DISK = simpleItem("plastimetal_disk");
    public static final RegistryObject<Item> GOLD_DISK = simpleItem("gold_disk");
    public static final RegistryObject<Item> NETHERITE_DISK = simpleItem("netherite_disk");
    public static final RegistryObject<Item> COPPER_DISK = simpleItem("copper_disk");
    public static final RegistryObject<Item> TIN_DISK = simpleItem("tin_disk");
    public static final RegistryObject<Item> BRONZE_DISK = simpleItem("bronze_disk");

    public static final RegistryObject<Item> FLAT_IRON_PANEL = simpleItem("flat_iron_panel");
    public static final RegistryObject<Item> FLAT_PLASTIMETAL_PANEL = simpleItem("flat_plastimetal_panel");
    public static final RegistryObject<Item> FLAT_GOLD_PANEL = simpleItem("flat_gold_panel");
    public static final RegistryObject<Item> FLAT_NETHERITE_PANEL = simpleItem("flat_netherite_panel");
    public static final RegistryObject<Item> FLAT_COPPER_PANEL = simpleItem("flat_copper_panel");
    public static final RegistryObject<Item> FLAT_TIN_PANEL = simpleItem("flat_tin_panel");
    public static final RegistryObject<Item> FLAT_BRONZE_PANEL = simpleItem("flat_bronze_panel");

    public static final RegistryObject<Item> ROUND_IRON_PANEL = simpleItem("round_iron_panel");
    public static final RegistryObject<Item> ROUND_PLASTIMETAL_PANEL = simpleItem("round_plastimetal_panel");
    public static final RegistryObject<Item> ROUND_GOLD_PANEL = simpleItem("round_gold_panel");
    public static final RegistryObject<Item> ROUND_NETHERITE_PANEL = simpleItem("round_netherite_panel");
    public static final RegistryObject<Item> ROUND_COPPER_PANEL = simpleItem("round_copper_panel");
    public static final RegistryObject<Item> ROUND_TIN_PANEL = simpleItem("round_tin_panel");
    public static final RegistryObject<Item> ROUND_BRONZE_PANEL = simpleItem("round_bronze_panel");

    public static final RegistryObject<Item> GPS_TOOL = ITEMS.register("gps_tool",
            () -> new GPSItem(new Item.Properties().durability(200)));

    public static final RegistryObject<Item> COFFEE_FOOD = ITEMS.register("coffee_food",
            () -> new Item(new Item.Properties().food(ModFoods.COFFEE_FOOD)));
    public static final RegistryObject<Item> COFFEE_BEANS = ITEMS.register("coffee_beans",
            () -> new ItemNameBlockItem(ModBlocks.COFFEE_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> FIBROSIA_SEEDS = ITEMS.register("fibrosia_seeds",
            () -> new ItemNameBlockItem(ModBlocks.FIBROSIA_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> PLANT_FIBERS = simpleItem("plant_fibers");

    public static final RegistryObject<Item> STICK_BUNDLE = ITEMS.register("stick_bundle",
            () -> new FuelItem(new Item.Properties(), 1000));
    public static final RegistryObject<Item> KNAPPED_FLINT = simpleItem("knapped_flint");
    public static final RegistryObject<Item> WOODEN_HANDLE = ITEMS.register("wooden_handle",
            () -> new CarvedFuelItem(new Item.Properties(), 200));
    public static final RegistryObject<Item> BLADE = simpleItem("blade");
    public static final RegistryObject<Item> FASTENERS = simpleItem("fasteners");
    public static final RegistryObject<Item> MECHANISM_PIECES = simpleItem("mechanism_pieces");

    public static final RegistryObject<Item> CLAY_CHUNK = simpleItem("clay_chunk");
    public static final RegistryObject<Item> LARGE_CLAY_CHUNK = simpleItem("large_clay_chunk");

    public static final RegistryObject<Item> WOODEN_SPEAR = ITEMS.register("wooden_spear",
            () -> new SpearItem(new Item.Properties().durability(50).attributes(SpearItem.createAttributes()).component(DataComponents.TOOL, SpearItem.createToolProperties())));
    public static final RegistryObject<Item> FLINT_KNIFE = ITEMS.register("flint_knife",
            () -> new CarverItem(ModToolTiers.PRIMITIVE, new Item.Properties().durability(20)));
    public static final RegistryObject<Item> STONE_MATTOCK = ITEMS.register("stone_mattock",
            () -> new SwordItem(ModToolTiers.PRIMITIVE, new Item.Properties()));

    public static final RegistryObject<Item> PLASTIMETAL_SWORD       = sword(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_PICKAXE     = pickaxe(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_AXE         = axe(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_SHOVEL      = shovel(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_HOE         = hoe(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_HELMET      = helmet(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_CHESTPLATE  = chestplate(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_LEGGINGS    = leggings(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_BOOTS       = boots(TierCollection.PLA);
    public static final RegistryObject<Item> PLASTIMETAL_HORSE_ARMOR = horseArmor(TierCollection.PLA);

    public static final RegistryObject<Item> COPPER_SWORD       = sword(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_PICKAXE     = pickaxe(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_AXE         = axe(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_SHOVEL      = shovel(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_HOE         = hoe(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_HELMET      = helmet(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_CHESTPLATE  = chestplate(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_LEGGINGS    = leggings(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_BOOTS       = boots(TierCollection.COP);
    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = horseArmor(TierCollection.COP);

    public static final RegistryObject<Item> TIN_SWORD          = sword(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_PICKAXE        = pickaxe(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_AXE            = axe(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_SHOVEL         = shovel(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_HOE            = hoe(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_HELMET         = helmet(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_CHESTPLATE     = chestplate(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_LEGGINGS       = leggings(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_BOOTS          = boots(TierCollection.TIN);
    public static final RegistryObject<Item> TIN_HORSE_ARMOR    = horseArmor(TierCollection.TIN);

    public static final RegistryObject<Item> BRONZE_SWORD       = sword(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_PICKAXE     = pickaxe(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_AXE         = axe(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_SHOVEL      = shovel(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_HOE         = hoe(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_HELMET      = helmet(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_CHESTPLATE  = chestplate(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_LEGGINGS    = leggings(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_BOOTS       = boots(TierCollection.BRO);
    public static final RegistryObject<Item> BRONZE_HORSE_ARMOR = horseArmor(TierCollection.BRO);

    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = horseArmor(TierCollection.NET);

    public static final RegistryObject<Item> IRON_SWORD_PARTS  = simpleItem("iron_sword_parts");
    public static final RegistryObject<Item> IRON_PICKAXE_PART = simpleItem("iron_pickaxe_part");
    public static final RegistryObject<Item> IRON_AXE_PART     = simpleItem("iron_axe_part");
    public static final RegistryObject<Item> IRON_SHOVEL_PART  = simpleItem("iron_shovel_part");
    public static final RegistryObject<Item> IRON_HOE_PART     = simpleItem("iron_hoe_part");

    public static final RegistryObject<Item> PLASTIMETAL_SWORD_PARTS  = simpleItem("plastimetal_sword_parts");
    public static final RegistryObject<Item> PLASTIMETAL_PICKAXE_PART = simpleItem("plastimetal_pickaxe_part");
    public static final RegistryObject<Item> PLASTIMETAL_AXE_PART     = simpleItem("plastimetal_axe_part");
    public static final RegistryObject<Item> PLASTIMETAL_SHOVEL_PART  = simpleItem("plastimetal_shovel_part");
    public static final RegistryObject<Item> PLASTIMETAL_HOE_PART     = simpleItem("plastimetal_hoe_part");

    public static final RegistryObject<Item> GOLDEN_SWORD_PARTS  = simpleItem("golden_sword_parts");
    public static final RegistryObject<Item> GOLDEN_PICKAXE_PART = simpleItem("golden_pickaxe_part");
    public static final RegistryObject<Item> GOLDEN_AXE_PART     = simpleItem("golden_axe_part");
    public static final RegistryObject<Item> GOLDEN_SHOVEL_PART  = simpleItem("golden_shovel_part");
    public static final RegistryObject<Item> GOLDEN_HOE_PART     = simpleItem("golden_hoe_part");

    public static final RegistryObject<Item> NETHERITE_SWORD_PARTS  = simpleItem("netherite_sword_parts");
    public static final RegistryObject<Item> NETHERITE_PICKAXE_PART = simpleItem("netherite_pickaxe_part");
    public static final RegistryObject<Item> NETHERITE_AXE_PART     = simpleItem("netherite_axe_part");
    public static final RegistryObject<Item> NETHERITE_SHOVEL_PART  = simpleItem("netherite_shovel_part");
    public static final RegistryObject<Item> NETHERITE_HOE_PART     = simpleItem("netherite_hoe_part");

    public static final RegistryObject<Item> COPPER_SWORD_PARTS  = simpleItem("copper_sword_parts");
    public static final RegistryObject<Item> COPPER_PICKAXE_PART = simpleItem("copper_pickaxe_part");
    public static final RegistryObject<Item> COPPER_AXE_PART     = simpleItem("copper_axe_part");
    public static final RegistryObject<Item> COPPER_SHOVEL_PART  = simpleItem("copper_shovel_part");
    public static final RegistryObject<Item> COPPER_HOE_PART     = simpleItem("copper_hoe_part");

    public static final RegistryObject<Item> TIN_SWORD_PARTS  = simpleItem("tin_sword_parts");
    public static final RegistryObject<Item> TIN_PICKAXE_PART = simpleItem("tin_pickaxe_part");
    public static final RegistryObject<Item> TIN_AXE_PART     = simpleItem("tin_axe_part");
    public static final RegistryObject<Item> TIN_SHOVEL_PART  = simpleItem("tin_shovel_part");
    public static final RegistryObject<Item> TIN_HOE_PART     = simpleItem("tin_hoe_part");

    public static final RegistryObject<Item> BRONZE_SWORD_PARTS  = simpleItem("bronze_sword_parts");
    public static final RegistryObject<Item> BRONZE_PICKAXE_PART = simpleItem("bronze_pickaxe_part");
    public static final RegistryObject<Item> BRONZE_AXE_PART     = simpleItem("bronze_axe_part");
    public static final RegistryObject<Item> BRONZE_SHOVEL_PART  = simpleItem("bronze_shovel_part");
    public static final RegistryObject<Item> BRONZE_HOE_PART     = simpleItem("bronze_hoe_part");

    public static final RegistryObject<Item> MOLD_SWORD_PARTS       = simpleItem("mold_sword_parts");
    public static final RegistryObject<Item> MOLD_PICKAXE_PART      = simpleItem("mold_pickaxe_part");
    public static final RegistryObject<Item> MOLD_AXE_PART          = simpleItem("mold_axe_part");
    public static final RegistryObject<Item> MOLD_SHOVEL_PART       = simpleItem("mold_shovel_part");
    public static final RegistryObject<Item> MOLD_HOE_PART          = simpleItem("mold_hoe_part");
    public static final RegistryObject<Item> MOLD_BLADE             = simpleItem("mold_blade");
    public static final RegistryObject<Item> MOLD_FASTENERS         = simpleItem("mold_fasteners");
    public static final RegistryObject<Item> MOLD_MECHANISM_PIECES  = simpleItem("mold_mechanism_pieces");
    public static final RegistryObject<Item> MOLD_ROD               = simpleItem("mold_rod");
    public static final RegistryObject<Item> MOLD_DISK              = simpleItem("mold_disk");
    public static final RegistryObject<Item> MOLD_FLAT_PANEL        = simpleItem("mold_flat_panel");
    public static final RegistryObject<Item> MOLD_ROUND_PANEL       = simpleItem("mold_round_panel");
    public static final RegistryObject<Item> MOLD_ARROW_HEADS       = simpleItem("mold_arrow_heads");
    public static final RegistryObject<Item> MOLD_BEAM              = simpleItem("mold_beam");
    public static final RegistryObject<Item> MOLD_BODY              = simpleItem("mold_body");

    public static RegistryObject<Item> sword(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_sword",
                () -> new SwordItem(pTierColl.getTier(),
                        new Item.Properties().attributes(SwordItem.createAttributes(
                            pTierColl.getTier(),
                            (int) tierNumerics.getAttackDamageBonus(EquipmentName.SWORD),
                            tierNumerics.getAttackSpeedBonus(EquipmentName.SWORD)))
                ));
    }
    public static RegistryObject<Item> pickaxe(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_pickaxe",
                () -> new PickaxeItem(pTierColl.getTier(),
                        new Item.Properties().attributes(PickaxeItem.createAttributes(
                                pTierColl.getTier(),
                                (int) tierNumerics.getAttackDamageBonus(EquipmentName.PICKAXE),
                                tierNumerics.getAttackSpeedBonus(EquipmentName.PICKAXE)))
                ));
    }
    public static RegistryObject<Item> axe(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_axe",
                () -> new AxeItem(pTierColl.getTier(),
                        new Item.Properties().attributes(AxeItem.createAttributes(
                                pTierColl.getTier(),
                                (int) tierNumerics.getAttackDamageBonus(EquipmentName.AXE),
                                tierNumerics.getAttackSpeedBonus(EquipmentName.AXE)))
                ));
    }
    public static RegistryObject<Item> shovel(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_shovel",
                () -> new ShovelItem(pTierColl.getTier(),
                        new Item.Properties().attributes(ShovelItem.createAttributes(
                                pTierColl.getTier(),
                                (int) tierNumerics.getAttackDamageBonus(EquipmentName.SHOVEL),
                                tierNumerics.getAttackSpeedBonus(EquipmentName.SHOVEL)))
                ));
    }
    public static RegistryObject<Item> hoe(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_hoe",
                () -> new HoeItem(pTierColl.getTier(),
                        new Item.Properties().attributes(HoeItem.createAttributes(
                                pTierColl.getTier(),
                                (int) tierNumerics.getAttackDamageBonus(EquipmentName.HOE),
                                tierNumerics.getAttackSpeedBonus(EquipmentName.HOE)))
                ));
    }
    public static RegistryObject<Item> helmet(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_helmet",
                () -> new ModArmorItem(pTierColl.getMaterial(), ArmorItem.Type.HELMET,
                        new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(tierNumerics.getDurabilityMultiplier()))
                ));
    }
    public static RegistryObject<Item> chestplate(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_chestplate",
                () -> new ModArmorItem(pTierColl.getMaterial(), ArmorItem.Type.CHESTPLATE,
                        new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(tierNumerics.getDurabilityMultiplier()))
                ));
    }
    public static RegistryObject<Item> leggings(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_leggings",
                () -> new ModArmorItem(pTierColl.getMaterial(), ArmorItem.Type.LEGGINGS,
                        new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(tierNumerics.getDurabilityMultiplier()))
                ));
    }
    public static RegistryObject<Item> boots(TierCollection pTierColl) {
        TierNumerics tierNumerics = TierNumerics.NUMERICS_MAP.get(pTierColl.getGroup());
        return ITEMS.register(pTierColl.getGroup() + "_boots",
                () -> new ModArmorItem(pTierColl.getMaterial(), ArmorItem.Type.BOOTS,
                        new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(tierNumerics.getDurabilityMultiplier()))
                ));
    }
    public static RegistryObject<Item> horseArmor(TierCollection pTierColl) {
        return ITEMS.register(pTierColl.getGroup() + "_horse_armor",
                () -> new ModAnimalArmorItem(
                    pTierColl.getMaterial(), ModAnimalArmorItem.BodyType.EQUESTRIAN, false,
                    new Item.Properties().stacksTo(1)
                ));
    }
    public static RegistryObject<Item> simpleItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
