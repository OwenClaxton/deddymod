package net.deddybones.techplusplus.datagen;

import com.google.common.collect.ImmutableList;
import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.item.TweakedVanillaItems;
import net.deddybones.techplusplus.recipes.*;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.deddybones.techplusplus.datagen.custom.ModHelper.getFromExt;
import static net.deddybones.techplusplus.recipes.ModShapelessRecipeBuilder.shapeless;
import static net.deddybones.techplusplus.util.ItemLikeNumbered.NIng;

@SuppressWarnings({"SameParameterValue", "unused"})
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES = ImmutableList.of(ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
    public static final ImmutableList<ItemLike> PLASTIMETAL_BILLET_SMELTABLES = ImmutableList.of(ModItems.PLASTIMETAL_NUGGET.get(), ModBlocks.RUINED_PLASTIMETAL.get(), ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get());
    public static final ImmutableList<ItemLike> PLASTIMETAL_INGOT_SMELTABLES = ImmutableList.of(ModItems.RAW_PLASTIMETAL.get());
    public static final Ingredient PLASTIMETAL_RECYCLABLES = Ingredient.of(ModItems.PLASTIMETAL_PICKAXE.get(), ModItems.PLASTIMETAL_SHOVEL.get(), ModItems.PLASTIMETAL_AXE.get(), ModItems.PLASTIMETAL_HOE.get(), ModItems.PLASTIMETAL_SWORD.get(), ModItems.PLASTIMETAL_HELMET.get(), ModItems.PLASTIMETAL_CHESTPLATE.get(), ModItems.PLASTIMETAL_LEGGINGS.get(), ModItems.PLASTIMETAL_BOOTS.get(), ModItems.PLASTIMETAL_HORSE_ARMOR.get());
    public static final Ingredient IRON_RECYCLABLES = Ingredient.of(Items.IRON_PICKAXE, Items.IRON_SHOVEL, TweakedVanillaItems.IRON_AXE.get(), Items.IRON_HOE, Items.IRON_SWORD, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR, Blocks.IRON_BARS, Blocks.IRON_DOOR, Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pOutput) {
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, Items.CLAY_BALL, RecipeCategory.BUILDING_BLOCKS, Blocks.CLAY);
        shapeless(pOutput, RecipeCategory.MISC, ModItems.CLAY_CHUNK.get(), 1, List.of(NIng(Items.CLAY_BALL, 3))).save();
        shapeless(pOutput, RecipeCategory.MISC, ModItems.LARGE_CLAY_CHUNK.get(), 1, List.of(NIng(Items.CLAY_BALL, 6))).save();
        shapeless(pOutput, RecipeCategory.MISC, Items.CLAY_BALL, 3, List.of(NIng(ModItems.CLAY_CHUNK.get())), getFromExt(ModItems.CLAY_CHUNK.get())).save();
        shapeless(pOutput, RecipeCategory.MISC, Items.CLAY_BALL, 6, List.of(NIng(ModItems.LARGE_CLAY_CHUNK.get())), getFromExt(ModItems.LARGE_CLAY_CHUNK.get())).save();

        oreKiln(pOutput, ImmutableList.of(ModItems.COPPER_NUGGET.get()), RecipeCategory.MISC, ModItems.COPPER_BILLET.get(), 1.0F, 200, "copper");
        oreKiln(pOutput, ImmutableList.of(ModItems.TIN_NUGGET.get()), RecipeCategory.MISC, ModItems.TIN_BILLET.get(), 1.0F, 200, "tin");
        oreSmelting(pOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 1.0F, 200, "sapphire");
        oreBlasting(pOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 1.0F, 100, "sapphire");
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAPPHIRE_BLOCK.get());

        oreSmelting(pOutput, PLASTIMETAL_BILLET_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_BILLET.get(), 0.1F, 200, "plastimetal");
        oreBlasting(pOutput, PLASTIMETAL_BILLET_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_BILLET.get(), 0.1F, 100, "plastimetal");
        oreSmelting(pOutput, PLASTIMETAL_INGOT_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get(), 1.0F, 200, "plastimetal");
        oreBlasting(pOutput, PLASTIMETAL_INGOT_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get(), 1.0F, 100, "plastimetal");
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.PLASTIMETAL_NUGGET.get(), RecipeCategory.MISC, ModItems.RAW_PLASTIMETAL.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.PLASTIMETAL_BILLET.get(), RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.RAW_PLASTIMETAL.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PLASTIMETAL_BLOCK.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.PLASTIMETAL_BLOCK.get());

        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.TIN_NUGGET.get(), RecipeCategory.MISC, ModItems.RAW_TIN.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.TIN_BILLET.get(), RecipeCategory.MISC, ModItems.TIN_INGOT.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.RAW_TIN.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_TIN_BLOCK.get());

        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.BRONZE_NUGGET.get(), RecipeCategory.MISC, ModItems.RAW_BRONZE.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.BRONZE_BILLET.get(), RecipeCategory.MISC, ModItems.BRONZE_INGOT.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.BRONZE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRONZE_BLOCK.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.RAW_BRONZE.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_BRONZE_BLOCK.get());

        shapeless(pOutput, RecipeCategory.MISC, ModItems.RAW_BRONZE.get(), 1, List.of(NIng(ModItems.COPPER_BILLET.get(), 7), NIng(ModItems.TIN_BILLET.get(), 2))).save();
        shapeless(pOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_BRONZE_BLOCK.get(), 1, List.of(NIng(Items.COPPER_INGOT, 7), NIng(ModItems.TIN_INGOT.get(), 2))).save();

        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, Items.STICK, RecipeCategory.MISC, ModItems.STICK_BUNDLE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PLASTIMETAL_BARS.get(), 16).define('#', ModItems.PLASTIMETAL_INGOT.get()).pattern("###").pattern("###").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        doorBuilder(ModBlocks.PLASTIMETAL_DOOR.get(), Ingredient.of(ModItems.PLASTIMETAL_INGOT.get())).unlockedBy(getHasName(ModItems.PLASTIMETAL_INGOT.get()), has(Items.IRON_INGOT)).save(pOutput);
        twoByTwoPacker(pOutput, RecipeCategory.REDSTONE, ModBlocks.PLASTIMETAL_TRAPDOOR.get(), ModItems.PLASTIMETAL_INGOT.get());

        recycleIntoSomething(pOutput, PLASTIMETAL_RECYCLABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_BILLET.get(), 0.1F);
        recycleIntoSomething(pOutput, IRON_RECYCLABLES, RecipeCategory.MISC, ModItems.IRON_BILLET.get(), 0.1F);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PLASTIMETAL_BOOTS.get()).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("X X").pattern("X X").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PLASTIMETAL_LEGGINGS.get()).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("XXX").pattern("X X").pattern("X X").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PLASTIMETAL_CHESTPLATE.get()).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("X X").pattern("XXX").pattern("XXX").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PLASTIMETAL_HELMET.get()).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("XXX").pattern("X X").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLASTIMETAL_HORSE_ARMOR.get()).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("X X").pattern("XXX").pattern("X X").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PLASTIMETAL_AXE.get()).define('#', Items.STICK).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("XX").pattern("X#").pattern(" #").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PLASTIMETAL_HOE.get()).define('#', Items.STICK).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("XX").pattern(" #").pattern(" #").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PLASTIMETAL_PICKAXE.get()).define('#', Items.STICK).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PLASTIMETAL_SHOVEL.get()).define('#', Items.STICK).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("X").pattern("#").pattern("#").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PLASTIMETAL_SWORD.get()).define('#', Items.STICK).define('X', ModItems.PLASTIMETAL_INGOT.get()).pattern("X").pattern("X").pattern("#").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);

        shapeless(pOutput, RecipeCategory.MISC, ModItems.KNAPPED_FLINT.get(), 2, List.of(NIng(Items.FLINT), NIng(ModBlocks.TINY_ROCK_BLOCK.get()))).save();
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.ARROW, 3).define('#', Items.STICK).define('X', ModItems.KNAPPED_FLINT.get()).define('Y', Items.FEATHER).pattern("X").pattern("#").pattern("Y").unlockedBy("has_feather", has(Items.FEATHER)).unlockedBy("has_stick", has(Items.STICK)).unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get())).save(pOutput, TechPlusPlus.MOD_ID + ":arrow");

        List<ItemLike> CarverList = List.of(ModItems.FLINT_KNIFE.get(), ModItems.PLASTIMETAL_AXE.get(), Items.IRON_AXE);

        itemListAndItemLike(pOutput, RecipeCategory.MISC, ModItems.WOODEN_HANDLE.get(), ModBlocks.TINY_LOG_BLOCK.get(), CarverList);
        itemListAndItemLike(pOutput, RecipeCategory.MISC, ModItems.WOODEN_SPEAR.get(), ModItems.WOODEN_HANDLE.get(), CarverList);

        fourThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModBlocks.TINY_ROCK_BLOCK.get(), RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLESTONE);

        shapeless(pOutput, RecipeCategory.TOOLS, ModItems.FLINT_KNIFE.get(), 1, List.of(NIng(ModItems.KNAPPED_FLINT.get()), NIng(Items.STICK))).save();
        shapeless(pOutput, RecipeCategory.TOOLS, ModItems.STONE_MATTOCK.get(), 1, List.of(NIng(ModBlocks.TINY_ROCK_BLOCK.get()), NIng(ModItems.WOODEN_HANDLE.get()), NIng(Items.STRING))).save();


//        for (String tool : new String[]{"shovel", "sword", "pickaxe", "hoe", "axe"}) {
//            removeRecipe(pOutput, "wooden_" + tool);
//        }
//        removeRecipe(pOutput, "arrow");

        // gold nuggets used in firework star, glistering melon slice, and golden carrot recipes -> swap to gold billet:
        SpecialRecipeBuilder.special(ModFireworkStarRecipe::new).save(pOutput,  TechPlusPlus.MOD_ID + ":" + getItemName(Items.FIREWORK_STAR));
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, Items.GOLDEN_CARROT).define('#', ModItems.GOLD_BILLET.get()).define('X', Items.CARROT).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_gold_nugget", has(Items.GOLD_NUGGET)).save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(Items.GOLDEN_CARROT));
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, Items.GLISTERING_MELON_SLICE).define('#', ModItems.GOLD_BILLET.get()).define('X', Items.MELON_SLICE).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_melon", has(Items.MELON_SLICE)).save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(Items.GLISTERING_MELON_SLICE));

        // Clay Molder Recipes
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_SWORD_BLADE.get(),        ModItems.CLAY_CHUNK.get());         // 2xI : WEAPON: swords
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_AXE_BLADE.get(),          ModItems.CLAY_CHUNK.get());         // 2xI : WEAPON: axes
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_CROSSBOW_PARTS.get(),     ModItems.CLAY_CHUNK.get());         // 1xI : WEAPON: crossbow
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_ARROW_HEADS.get(),        ModItems.CLAY_CHUNK.get());         // 2xI : WEAPON: arrows
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_PICKAXE_HEAD.get(),       ModItems.CLAY_CHUNK.get());         // 2xI : TOOL: pickaxe
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_SHOVEL_HEAD.get(),        ModItems.CLAY_CHUNK.get());         // 2xI : TOOL: shovel
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_HOE_BLADE.get(),          ModItems.CLAY_CHUNK.get());         // 2xI : TOOL: hoe
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_SHEAR_BLADE.get(),        ModItems.CLAY_CHUNK.get());         // 2xI : TOOL: shears
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_BUCKET.get(),             ModItems.CLAY_CHUNK.get());         // 2xI : TOOL: bucket
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_STEEL_STRIKER.get(),      ModItems.CLAY_CHUNK.get());         // 1xB : TOOL: flint & steel
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_HELMET_PARTS.get(),       ModItems.LARGE_CLAY_CHUNK.get());   // 2xI : ARMOR: helmets
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_CHESTPLATE_PARTS.get(),   ModItems.LARGE_CLAY_CHUNK.get());   // 4xI : ARMOR: chestplate
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_LEGGINGS_PARTS.get(),     ModItems.LARGE_CLAY_CHUNK.get());   // 4xI : ARMOR: leggings
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_BOOTS_PARTS.get(),        ModItems.LARGE_CLAY_CHUNK.get());   // 2xI : ARMOR: boots
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_HORSE_ARMOR_PARTS.get(),  Blocks.CLAY);                       // 9xI : ARMOR: horse armour
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_TRIM_PARTS.get(),         ModItems.CLAY_CHUNK.get());         // 1xI : ARMOR: trims
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_SHIELD_PARTS.get(),       ModItems.CLAY_CHUNK.get());         // 1xI : shield
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_PLATE.get(),              ModItems.CLAY_CHUNK.get());         // 1xI : pressure plates, minecarts, armor, blocks, armor
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_ROD.get(),                ModItems.CLAY_CHUNK.get());         // 1xI : for tools, weapons, bar blocks
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_FASTENERS.get(),          ModItems.CLAY_CHUNK.get());         // 4xB : for armor, bar blocks, blocks,
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_MECHANISM_PIECES.get(),   ModItems.CLAY_CHUNK.get());         // 1xI : clock, compass, tripwire hook, metal (trap)doors
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_WHEELS.get(),             ModItems.CLAY_CHUNK.get());         // 2xB : minecart
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_SAW_BLADE.get(),          ModItems.CLAY_CHUNK.get());         // 1xI : saw bench, stonecutter
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_CHAIN_LINKS.get(),        ModItems.CLAY_CHUNK.get());         //
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_PISTON_PARTS.get(),       Blocks.CLAY);                       // 1xI : pistons
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_APPLE_SHELL.get(),        ModItems.CLAY_CHUNK.get());         // 1xI : golden apple (recipe uses ingots)
//        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_RAIL.get(),               Blocks.CLAY);                       // 3xI : rail, powered/detector/activator rail

        // Crusher Recipes
        crusherResult(pOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLESTONE, Blocks.STONE);
        crusherResult(pOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE);
        crusherResult(pOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.SAND, Blocks.SANDSTONE, 4);
        crusherResult(pOutput, RecipeCategory.BUILDING_BLOCKS, Blocks.RED_SAND, Blocks.RED_SANDSTONE, 4);
        crusherResult(pOutput, RecipeCategory.MISC, Items.AMETHYST_SHARD, Blocks.AMETHYST_BLOCK, 4);
        crusherResult(pOutput, RecipeCategory.MISC, Items.QUARTZ, Blocks.QUARTZ_BLOCK, 4);
        crusherResult(pOutput, RecipeCategory.MISC, Items.GLOWSTONE_DUST, Blocks.GLOWSTONE, 4);
        crusherResult(pOutput, RecipeCategory.MISC, ModBlocks.TINY_ROCK_BLOCK.get(), Blocks.COBBLESTONE, 4);
        crusherResult(pOutput, RecipeCategory.MISC, Items.CLAY_BALL, Blocks.CLAY, 4);
    }

    protected static void moldingResult(RecipeOutput pOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pIngredient) {
        moldingResult(pOutput, pCategory, pResult, pIngredient, 1);
    }

    protected static void moldingResult(RecipeOutput pOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pIngredient, int resultCount) {
        ModSingleItemRecipeBuilder.molding(Ingredient.of(pIngredient), pCategory, pResult, resultCount)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pOutput,   TechPlusPlus.MOD_ID + ":" + getConversionRecipeName(pResult, pIngredient) + "_molding");
    }

    protected static void crusherResult(RecipeOutput pOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pIngredient) {
        crusherResult(pOutput, pCategory, pResult, pIngredient, 1);
    }

    protected static void crusherResult(RecipeOutput pOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pIngredient, int resultCount) {
        ModSingleItemRecipeBuilder.crushing(Ingredient.of(pIngredient), pCategory, pResult, resultCount)
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pOutput,   TechPlusPlus.MOD_ID + ":" + getConversionRecipeName(pResult, pIngredient) + "_crushing");
    }

    protected static void recycleIntoSomething(@NotNull RecipeOutput pOutput, @NotNull Ingredient itemsForRecycling, @NotNull RecipeCategory recipeCategory,
                                               @NotNull ItemLike recipeResult, float experience, String modid) {
        ModSimpleCookingRecipeBuilder smeltingBuilder = ModSimpleCookingRecipeBuilder.smelting(itemsForRecycling, recipeCategory, recipeResult, experience, 200);
        ModSimpleCookingRecipeBuilder blastingBuilder = ModSimpleCookingRecipeBuilder.blasting(itemsForRecycling, recipeCategory, recipeResult, experience, 100);

        for (ItemStack itemStackToRecycle : itemsForRecycling.getItems()) {
            Item itemToRecycle = itemStackToRecycle.getItem();
            smeltingBuilder.unlockedBy(getHasName(itemToRecycle), has(itemToRecycle));
            blastingBuilder.unlockedBy(getHasName(itemToRecycle), has(itemToRecycle));
        }

        smeltingBuilder.save(pOutput, modid + ":" + getSmeltingRecipeName(recipeResult));
        blastingBuilder.save(pOutput, modid + ":" + getBlastingRecipeName(recipeResult));
    }

    protected static void recycleIntoSomething(@NotNull RecipeOutput pOutput, @NotNull Ingredient itemsForRecycling,
                                               @NotNull RecipeCategory recipeCategory,
                                               @NotNull ItemLike recipeResult, float experience) {
        recycleIntoSomething(pOutput, itemsForRecycling, recipeCategory, recipeResult, experience, TechPlusPlus.MOD_ID);
    }

    protected static void itemListAndItemLike(  @NotNull RecipeOutput pOutput, @NotNull RecipeCategory recipeCategory,
                                                @NotNull ItemLike recipeResult, @NotNull ItemLike recipeIngredient,
                                                List<ItemLike> itemList) {
        for (ItemLike ingredientItem : itemList) {
            shapeless(pOutput, recipeCategory, recipeResult, 1, List.of(NIng(recipeIngredient), NIng(ingredientItem)))
                    .extension("_from_" + getItemName(recipeIngredient) + "_and_" + getItemName(ingredientItem))
                    .save();
        }
    }

    protected static void oreSmelting(@NotNull RecipeOutput pOutput, List<ItemLike> acceptableItems, @NotNull RecipeCategory recipeCategory,
                                      @NotNull ItemLike smeltOutput, float experienceAmount, int smeltTime, @NotNull String group) {
        oreCooking(pOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, acceptableItems, recipeCategory, smeltOutput, experienceAmount, smeltTime, group, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull RecipeOutput pOutput, List<ItemLike> acceptableItems, @NotNull RecipeCategory recipeCategory,
                                      @NotNull ItemLike blastOutput, float experienceAmount, int blastTime, @NotNull String group) {
        oreCooking(pOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, acceptableItems, recipeCategory, blastOutput, experienceAmount, blastTime, group, "_from_blasting");
    }

    protected static void oreKiln(@NotNull RecipeOutput pOutput, List<ItemLike> acceptableItems, @NotNull RecipeCategory recipeCategory,
                                  @NotNull ItemLike kilnOutput, float experienceAmount, int kilnTime, @NotNull String group) {
        oreCooking(pOutput, ModRecipes.KILN_SERIALIZER.get(), KilnRecipe::new, acceptableItems, recipeCategory, kilnOutput, experienceAmount, kilnTime, group, "_from_kiln");
    }

    private static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pOutput, RecipeSerializer<T> recipeSerializer,
                                                                     AbstractCookingRecipe.Factory<T> cookingRecipe, List<ItemLike> acceptableItems,
                                                                     RecipeCategory recipeCategory, ItemLike cookingOutput,
                                                                     float experienceAmount, int cookingTime, String group, String methodString) {
        for(ItemLike itemlike : acceptableItems) {
            ModSimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), recipeCategory, cookingOutput, experienceAmount, cookingTime, recipeSerializer, cookingRecipe)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(cookingOutput) + methodString + "_" + getItemName(itemlike));
        }

    }

    protected static void removeRecipe(@NotNull RecipeOutput pOutput, @NotNull String recipeName) {
        new EmptyRecipeBuilder().save(pOutput, recipeName);
    }

    protected static void fourThingsStorageRecipes(@NotNull RecipeOutput pOutput,
                                                   @NotNull RecipeCategory singleRecipeCategory, @NotNull ItemLike singleItem,
                                                   @NotNull RecipeCategory storageRecipeCategory, @NotNull ItemLike storageItem) {
        ShapedRecipeBuilder.shaped(storageRecipeCategory, storageItem)
                .define('#', singleItem)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(singleItem), has(singleItem))
                .save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(storageItem) + getFromExt(singleItem));

        shapeless(pOutput, singleRecipeCategory, singleItem, 4, List.of(NIng(storageItem)), getFromExt(storageItem)).save();
    }

    protected static void nineThingsStorageRecipes(@NotNull RecipeOutput pOutput,
                                                  @NotNull RecipeCategory singleRecipeCategory, @NotNull ItemLike singleItem,
                                                  @NotNull RecipeCategory storageRecipeCategory, @NotNull ItemLike storageItem) {
        ShapedRecipeBuilder.shaped(storageRecipeCategory, storageItem)
                .define('#', singleItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(singleItem), has(singleItem))
                .save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(storageItem) + getFromExt(singleItem));

        shapeless(pOutput, singleRecipeCategory, singleItem, 9, List.of(NIng(storageItem)), getFromExt(storageItem)).save();
    }
}
