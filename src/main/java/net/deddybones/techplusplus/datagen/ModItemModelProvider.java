package net.deddybones.techplusplus.datagen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.ModItems;
import static net.deddybones.techplusplus.datagen.custom.ModHelper.bName;
import static net.deddybones.techplusplus.datagen.custom.ModHelper.iName;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.LinkedHashMap;

@SuppressWarnings({"UnusedReturnValue","SameParameterValue"})
public class ModItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TechPlusPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.SAPPHIRE.get());

        simpleItem(ModItems.PLASTIMETAL_INGOT.get());
        simpleItem(ModItems.TIN_INGOT.get());
        simpleItem(ModItems.BRONZE_INGOT.get());

        simpleItem(ModItems.PLASTIMETAL_BILLET.get());
        simpleItem(ModItems.TIN_BILLET.get());
        simpleItem(ModItems.COPPER_BILLET.get());
        simpleItem(ModItems.BRONZE_BILLET.get());
        simpleItem(ModItems.GOLD_BILLET.get());
        simpleItem(ModItems.IRON_BILLET.get());
        simpleItem(ModItems.NETHERITE_BILLET.get());

        simpleItem(ModItems.PLASTIMETAL_ROD.get());
        simpleItem(ModItems.TIN_ROD.get());
        simpleItem(ModItems.COPPER_ROD.get());
        simpleItem(ModItems.BRONZE_ROD.get());
        simpleItem(ModItems.GOLD_ROD.get());
        simpleItem(ModItems.IRON_ROD.get());
        simpleItem(ModItems.NETHERITE_ROD.get());

        simpleItem(ModItems.PLASTIMETAL_DISK.get());
        simpleItem(ModItems.TIN_DISK.get());
        simpleItem(ModItems.COPPER_DISK.get());
        simpleItem(ModItems.BRONZE_DISK.get());
        simpleItem(ModItems.GOLD_DISK.get());
        simpleItem(ModItems.IRON_DISK.get());
        simpleItem(ModItems.NETHERITE_DISK.get());

        simpleItem(ModItems.FLAT_PLASTIMETAL_PANEL.get());
        simpleItem(ModItems.FLAT_TIN_PANEL.get());
        simpleItem(ModItems.FLAT_COPPER_PANEL.get());
        simpleItem(ModItems.FLAT_BRONZE_PANEL.get());
        simpleItem(ModItems.FLAT_GOLD_PANEL.get());
        simpleItem(ModItems.FLAT_IRON_PANEL.get());
        simpleItem(ModItems.FLAT_NETHERITE_PANEL.get());

        simpleItem(ModItems.ROUND_PLASTIMETAL_PANEL.get());
        simpleItem(ModItems.ROUND_TIN_PANEL.get());
        simpleItem(ModItems.ROUND_COPPER_PANEL.get());
        simpleItem(ModItems.ROUND_BRONZE_PANEL.get());
        simpleItem(ModItems.ROUND_GOLD_PANEL.get());
        simpleItem(ModItems.ROUND_IRON_PANEL.get());
        simpleItem(ModItems.ROUND_NETHERITE_PANEL.get());

        simpleItem(ModItems.PLASTIMETAL_NUGGET.get());
        simpleItem(ModItems.TIN_NUGGET.get());
        simpleItem(ModItems.COPPER_NUGGET.get());
        simpleItem(ModItems.BRONZE_NUGGET.get());
        simpleItem(ModItems.NETHERITE_NUGGET.get());

        simpleItem(ModItems.RAW_PLASTIMETAL.get());
        simpleItem(ModItems.RAW_TIN.get());
        simpleItem(ModItems.RAW_BRONZE.get());
        simpleItem(ModItems.RAW_NETHERITE.get());

        simpleItem(ModItems.GPS_TOOL.get());

        simpleItem(ModItems.COFFEE_FOOD.get());
        simpleItem(ModItems.COFFEE_BEANS.get());
        simpleItem(ModItems.PLANT_FIBERS.get());
        simpleItem(ModItems.FIBROSIA_SEEDS.get());

        simpleItem(ModItems.STICK_BUNDLE.get());
        simpleItem(ModItems.KNAPPED_FLINT.get());
        simpleItem(ModItems.WOODEN_HANDLE.get());
        simpleItem(ModItems.FASTENERS.get());
        simpleItem(ModItems.BLADE.get());
        simpleItem(ModItems.CLAY_CHUNK.get());
        simpleItem(ModItems.LARGE_CLAY_CHUNK.get());

        evenSimplerBlockItem(ModBlocks.GLASS_STAIRS.get());
        evenSimplerBlockItem(ModBlocks.GLASS_SLAB.get());
        evenSimplerBlockItem(ModBlocks.GLASS_PRESSURE_PLATE.get());
        evenSimplerBlockItem(ModBlocks.GLASS_FENCE_GATE.get());
        fenceItem(ModBlocks.GLASS_FENCE.get(), Blocks.GLASS);
        buttonItem(ModBlocks.GLASS_BUTTON.get(), Blocks.GLASS);
        wallItem(ModBlocks.GLASS_WALL.get(), Blocks.GLASS);
        simpleBlockItem(ModBlocks.GLASS_DOOR.get());
        trapdoorItem(ModBlocks.GLASS_TRAPDOOR.get());

        simpleBlockItem(ModBlocks.PLASTIMETAL_DOOR.get());
        trapdoorItem(   ModBlocks.PLASTIMETAL_TRAPDOOR.get());
        handheldBlock(  ModBlocks.PLASTIMETAL_BARS.get());

        simpleBlockItem(ModBlocks.GOLD_DOOR.get());
        trapdoorItem(   ModBlocks.GOLD_TRAPDOOR.get());
        handheldBlock(  ModBlocks.GOLD_BARS.get());

        simpleBlockItem(ModBlocks.NETHERITE_DOOR.get());
        trapdoorItem(   ModBlocks.NETHERITE_TRAPDOOR.get());
        handheldBlock(  ModBlocks.NETHERITE_BARS.get());

        simpleBlockItem(ModBlocks.TIN_DOOR.get());
        trapdoorItem(   ModBlocks.TIN_TRAPDOOR.get());
        handheldBlock(  ModBlocks.TIN_BARS.get());

        handheldBlock(  ModBlocks.COPPER_BARS.get());

        simpleBlockItem(ModBlocks.BRONZE_DOOR.get());
        trapdoorItem(   ModBlocks.BRONZE_TRAPDOOR.get());
        handheldBlock(  ModBlocks.BRONZE_BARS.get());

        handheldItem(    ModItems.IRON_SWORD_PARTS.get());
        handheldItem(    ModItems.IRON_PICKAXE_PART.get());
        handheldItem(    ModItems.IRON_AXE_PART.get());
        handheldItem(    ModItems.IRON_SHOVEL_PART.get());
        handheldItem(    ModItems.IRON_HOE_PART.get());
        handheldItem(    ModItems.IRON_HOE_PART.get());

        trimmedArmorItem(ModItems.PLASTIMETAL_HELMET.get());
        trimmedArmorItem(ModItems.PLASTIMETAL_CHESTPLATE.get());
        trimmedArmorItem(ModItems.PLASTIMETAL_LEGGINGS.get());
        trimmedArmorItem(ModItems.PLASTIMETAL_BOOTS.get());
        simpleItem(      ModItems.PLASTIMETAL_HORSE_ARMOR.get());
        handheldItem(    ModItems.PLASTIMETAL_SWORD.get());
        handheldItem(    ModItems.PLASTIMETAL_PICKAXE.get());
        handheldItem(    ModItems.PLASTIMETAL_AXE.get());
        handheldItem(    ModItems.PLASTIMETAL_SHOVEL.get());
        handheldItem(    ModItems.PLASTIMETAL_HOE.get());
        handheldItem(    ModItems.PLASTIMETAL_SWORD_PARTS.get());
        handheldItem(    ModItems.PLASTIMETAL_PICKAXE_PART.get());
        handheldItem(    ModItems.PLASTIMETAL_AXE_PART.get());
        handheldItem(    ModItems.PLASTIMETAL_SHOVEL_PART.get());
        handheldItem(    ModItems.PLASTIMETAL_HOE_PART.get());
        handheldItem(    ModItems.PLASTIMETAL_HOE_PART.get());

        handheldItem(    ModItems.GOLDEN_SWORD_PARTS.get());
        handheldItem(    ModItems.GOLDEN_PICKAXE_PART.get());
        handheldItem(    ModItems.GOLDEN_AXE_PART.get());
        handheldItem(    ModItems.GOLDEN_SHOVEL_PART.get());
        handheldItem(    ModItems.GOLDEN_HOE_PART.get());
        handheldItem(    ModItems.GOLDEN_HOE_PART.get());

        simpleItem(      ModItems.NETHERITE_HORSE_ARMOR.get());
        handheldItem(    ModItems.NETHERITE_SWORD_PARTS.get());
        handheldItem(    ModItems.NETHERITE_PICKAXE_PART.get());
        handheldItem(    ModItems.NETHERITE_AXE_PART.get());
        handheldItem(    ModItems.NETHERITE_SHOVEL_PART.get());
        handheldItem(    ModItems.NETHERITE_HOE_PART.get());
        handheldItem(    ModItems.NETHERITE_HOE_PART.get());

        trimmedArmorItem(ModItems.COPPER_HELMET.get());
        trimmedArmorItem(ModItems.COPPER_CHESTPLATE.get());
        trimmedArmorItem(ModItems.COPPER_LEGGINGS.get());
        trimmedArmorItem(ModItems.COPPER_BOOTS.get());
        simpleItem(      ModItems.COPPER_HORSE_ARMOR.get());
        handheldItem(    ModItems.COPPER_SWORD.get());
        handheldItem(    ModItems.COPPER_PICKAXE.get());
        handheldItem(    ModItems.COPPER_AXE.get());
        handheldItem(    ModItems.COPPER_SHOVEL.get());
        handheldItem(    ModItems.COPPER_HOE.get());
        handheldItem(    ModItems.COPPER_SWORD_PARTS.get());
        handheldItem(    ModItems.COPPER_PICKAXE_PART.get());
        handheldItem(    ModItems.COPPER_AXE_PART.get());
        handheldItem(    ModItems.COPPER_SHOVEL_PART.get());
        handheldItem(    ModItems.COPPER_HOE_PART.get());
        handheldItem(    ModItems.COPPER_HOE_PART.get());

        trimmedArmorItem(ModItems.TIN_HELMET.get());
        trimmedArmorItem(ModItems.TIN_CHESTPLATE.get());
        trimmedArmorItem(ModItems.TIN_LEGGINGS.get());
        trimmedArmorItem(ModItems.TIN_BOOTS.get());
        simpleItem(      ModItems.TIN_HORSE_ARMOR.get());
        handheldItem(    ModItems.TIN_SWORD.get());
        handheldItem(    ModItems.TIN_PICKAXE.get());
        handheldItem(    ModItems.TIN_AXE.get());
        handheldItem(    ModItems.TIN_SHOVEL.get());
        handheldItem(    ModItems.TIN_HOE.get());
        handheldItem(    ModItems.TIN_SWORD_PARTS.get());
        handheldItem(    ModItems.TIN_PICKAXE_PART.get());
        handheldItem(    ModItems.TIN_AXE_PART.get());
        handheldItem(    ModItems.TIN_SHOVEL_PART.get());
        handheldItem(    ModItems.TIN_HOE_PART.get());
        handheldItem(    ModItems.TIN_HOE_PART.get());

        trimmedArmorItem(ModItems.BRONZE_HELMET.get());
        trimmedArmorItem(ModItems.BRONZE_CHESTPLATE.get());
        trimmedArmorItem(ModItems.BRONZE_LEGGINGS.get());
        trimmedArmorItem(ModItems.BRONZE_BOOTS.get());
        simpleItem(      ModItems.BRONZE_HORSE_ARMOR.get());
        handheldItem(    ModItems.BRONZE_SWORD.get());
        handheldItem(    ModItems.BRONZE_PICKAXE.get());
        handheldItem(    ModItems.BRONZE_AXE.get());
        handheldItem(    ModItems.BRONZE_SHOVEL.get());
        handheldItem(    ModItems.BRONZE_HOE.get());
        handheldItem(    ModItems.BRONZE_SWORD_PARTS.get());
        handheldItem(    ModItems.BRONZE_PICKAXE_PART.get());
        handheldItem(    ModItems.BRONZE_AXE_PART.get());
        handheldItem(    ModItems.BRONZE_SHOVEL_PART.get());
        handheldItem(    ModItems.BRONZE_HOE_PART.get());

//        handheldItem(ModItems.WOODEN_SPEAR.get()); // We have custom jsons for this
        handheldItem(ModItems.FLINT_KNIFE.get());
        handheldItem(ModItems.STONE_MATTOCK.get());

        simpleBlockItem(ModBlocks.TINY_ROCK_BLOCK.get());
        simpleBlockItem(ModBlocks.TINY_LOG_BLOCK.get());
    }

    private void trimmedArmorItem(Item item) {
        final String MOD_ID = TechPlusPlus.MOD_ID; // Change this to your mod id

        if (item instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {

                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that these textures exist,
                // avoiding an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(iName(armorItem),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + iName(armorItem)));
            });
        }
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(iName(item),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TechPlusPlus.MOD_ID, "item/" + iName(item)));
    }

    public void trapdoorItem(Block block) {
        this.withExistingParent(bName(block),
                modLoc("block/" + bName(block) + "_bottom"));
    }

    public void fenceItem(Block block, Block baseBlock) {
        this.withExistingParent(bName(block), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(TechPlusPlus.MOD_ID, "block/" + bName(baseBlock)));
    }

    public void buttonItem(Block block, Block baseBlock) {
        this.withExistingParent(bName(block), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(TechPlusPlus.MOD_ID, "block/" + bName(baseBlock)));
    }

    public void wallItem(Block block, Block baseBlock) {
        this.withExistingParent(bName(block), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(TechPlusPlus.MOD_ID, "block/" + bName(baseBlock)));
    }

    public void evenSimplerBlockItem(Block block) {
        this.withExistingParent(bName(block),
                new ResourceLocation(TechPlusPlus.MOD_ID, "block/" + bName(block)));
    }
    
    private ItemModelBuilder handheldBlock(Block block) {
        return withExistingParent(bName(block),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(TechPlusPlus.MOD_ID,"block/" + bName(block)));
    }

    private ItemModelBuilder handheldItem(Item item, String pModID) {
        return withExistingParent(iName(item),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(pModID,"item/" + iName(item)));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return handheldItem(item, TechPlusPlus.MOD_ID);
    }

    private ItemModelBuilder simpleBlockItem(Block block) {
        return withExistingParent(bName(block),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TechPlusPlus.MOD_ID,"item/" + bName(block)));
    }
}
