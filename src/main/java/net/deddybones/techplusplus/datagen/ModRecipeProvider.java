package net.deddybones.techplusplus.datagen;

import com.google.common.collect.ImmutableList;
import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.util.crafting.EmptyRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES = ImmutableList.of(ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
    public static final ImmutableList<ItemLike> PLASTIMETAL_NUGGET_SMELTABLES = ImmutableList.of(ModItems.RAW_PLASTIMETAL.get(), ModBlocks.RUINED_PLASTIMETAL.get(), ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get());
    public static final ImmutableList<ItemLike> PLASTIMETAL_SMELTABLES = ImmutableList.of(ModBlocks.RAW_PLASTIMETAL_BLOCK.get());
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pOutput) {
        oreSmelting(pOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 1.0F, 200, "sapphire");
        oreBlasting(pOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 1.0F, 100, "sapphire");

        oreSmelting(pOutput, PLASTIMETAL_NUGGET_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_NUGGET.get(), 0.1F, 200, "plastimetal");
        oreBlasting(pOutput, PLASTIMETAL_NUGGET_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_NUGGET.get(), 0.1F, 100, "plastimetal");
        oreSmelting(pOutput, PLASTIMETAL_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get(), 1.0F, 200, "plastimetal");
        oreBlasting(pOutput, PLASTIMETAL_SMELTABLES, RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get(), 1.0F, 100, "plastimetal");
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.PLASTIMETAL_NUGGET.get(), RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get());

        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAPPHIRE_BLOCK.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.PLASTIMETAL_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.PLASTIMETAL_BLOCK.get());
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.RAW_PLASTIMETAL.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PLASTIMETAL_BLOCK.get());

        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, Items.STICK, RecipeCategory.MISC, ModItems.STICK_BUNDLE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PLASTIMETAL_BARS.get(), 16).define('#', ModItems.PLASTIMETAL_INGOT.get()).pattern("###").pattern("###").unlockedBy("has_plastimetal_ingot", has(ModItems.PLASTIMETAL_INGOT.get())).save(pOutput);
        doorBuilder(ModBlocks.PLASTIMETAL_DOOR.get(), Ingredient.of(ModItems.PLASTIMETAL_INGOT.get())).unlockedBy(getHasName(ModItems.PLASTIMETAL_INGOT.get()), has(Items.IRON_INGOT)).save(pOutput);
        twoByTwoPacker(pOutput, RecipeCategory.REDSTONE, ModBlocks.PLASTIMETAL_TRAPDOOR.get(), ModItems.PLASTIMETAL_INGOT.get());

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.PLASTIMETAL_PICKAXE.get(), ModItems.PLASTIMETAL_SHOVEL.get(), ModItems.PLASTIMETAL_AXE.get(), ModItems.PLASTIMETAL_HOE.get(), ModItems.PLASTIMETAL_SWORD.get(), ModItems.PLASTIMETAL_HELMET.get(), ModItems.PLASTIMETAL_CHESTPLATE.get(), ModItems.PLASTIMETAL_LEGGINGS.get(), ModItems.PLASTIMETAL_BOOTS.get(), ModItems.PLASTIMETAL_HORSE_ARMOR.get()), RecipeCategory.MISC, ModItems.PLASTIMETAL_NUGGET.get(), 0.1F, 200).unlockedBy("has_plastimetal_pickaxe", has(ModItems.PLASTIMETAL_PICKAXE.get())).unlockedBy("has_plastimetal_shovel", has(ModItems.PLASTIMETAL_SHOVEL.get())).unlockedBy("has_plastimetal_axe", has(ModItems.PLASTIMETAL_AXE.get())).unlockedBy("has_plastimetal_hoe", has(ModItems.PLASTIMETAL_HOE.get())).unlockedBy("has_plastimetal_sword", has(ModItems.PLASTIMETAL_SWORD.get())).unlockedBy("has_plastimetal_helmet", has(ModItems.PLASTIMETAL_HELMET.get())).unlockedBy("has_plastimetal_chestplate", has(ModItems.PLASTIMETAL_CHESTPLATE.get())).unlockedBy("has_plastimetal_leggings", has(ModItems.PLASTIMETAL_LEGGINGS.get())).unlockedBy("has_plastimetal_boots", has(ModItems.PLASTIMETAL_BOOTS.get())).unlockedBy("has_plastimetal_horse_armor", has(ModItems.PLASTIMETAL_HORSE_ARMOR.get())).save(pOutput, TechPlusPlus.MOD_ID + ":" + getSmeltingRecipeName(ModItems.PLASTIMETAL_NUGGET.get()));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.PLASTIMETAL_PICKAXE.get(), ModItems.PLASTIMETAL_SHOVEL.get(), ModItems.PLASTIMETAL_AXE.get(), ModItems.PLASTIMETAL_HOE.get(), ModItems.PLASTIMETAL_SWORD.get(), ModItems.PLASTIMETAL_HELMET.get(), ModItems.PLASTIMETAL_CHESTPLATE.get(), ModItems.PLASTIMETAL_LEGGINGS.get(), ModItems.PLASTIMETAL_BOOTS.get(), ModItems.PLASTIMETAL_HORSE_ARMOR.get()), RecipeCategory.MISC, ModItems.PLASTIMETAL_NUGGET.get(), 0.1F, 100).unlockedBy("has_plastimetal_pickaxe", has(ModItems.PLASTIMETAL_PICKAXE.get())).unlockedBy("has_plastimetal_shovel", has(ModItems.PLASTIMETAL_SHOVEL.get())).unlockedBy("has_plastimetal_axe", has(ModItems.PLASTIMETAL_AXE.get())).unlockedBy("has_plastimetal_hoe", has(ModItems.PLASTIMETAL_HOE.get())).unlockedBy("has_plastimetal_sword", has(ModItems.PLASTIMETAL_SWORD.get())).unlockedBy("has_plastimetal_helmet", has(ModItems.PLASTIMETAL_HELMET.get())).unlockedBy("has_plastimetal_chestplate", has(ModItems.PLASTIMETAL_CHESTPLATE.get())).unlockedBy("has_plastimetal_leggings", has(ModItems.PLASTIMETAL_LEGGINGS.get())).unlockedBy("has_plastimetal_boots", has(ModItems.PLASTIMETAL_BOOTS.get())).unlockedBy("has_plastimetal_horse_armor", has(ModItems.PLASTIMETAL_HORSE_ARMOR.get())).save(pOutput, TechPlusPlus.MOD_ID + ":" + getBlastingRecipeName(ModItems.PLASTIMETAL_NUGGET.get()));

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.KNAPPED_FLINT.get(), 2).requires(Items.FLINT).requires(ModBlocks.TINY_ROCK_BLOCK.get()).unlockedBy("has_flint", has(Items.FLINT)).unlockedBy("has_tiny_rock_block", has(ModBlocks.TINY_ROCK_BLOCK.get())).save(pOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.ARROW, 3).define('#', Items.STICK).define('X', ModItems.KNAPPED_FLINT.get()).define('Y', Items.FEATHER).pattern("X").pattern("#").pattern("Y").unlockedBy("has_feather", has(Items.FEATHER)).unlockedBy("has_stick", has(Items.STICK)).unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get())).save(pOutput, TechPlusPlus.MOD_ID + ":arrow");

        List<Item> CarverList = List.of(ModItems.FLINT_KNIFE.get(), ModItems.PLASTIMETAL_AXE.get(), Items.IRON_AXE);

        itemListAndItemLike(pOutput, RecipeCategory.MISC, ModItems.WOODEN_HANDLE.get(), ModBlocks.TINY_LOG_BLOCK.get(), CarverList);
        itemListAndItemLike(pOutput, RecipeCategory.MISC, ModItems.WOODEN_SPEAR.get(), ModItems.WOODEN_HANDLE.get(), CarverList);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.FLINT_KNIFE.get(), 1)
                .requires(ModItems.KNAPPED_FLINT.get()).requires(Items.STICK)
                .unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get())).unlockedBy("has_stick", has(Items.STICK))
                .save(pOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.STONE_MATTOCK.get(), 1)
                .requires(ModBlocks.TINY_ROCK_BLOCK.get()).requires(Items.STRING).requires(ModItems.WOODEN_HANDLE.get())
                .unlockedBy("has_tiny_rock_block", has(ModBlocks.TINY_ROCK_BLOCK.get())).unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wooden_handle", has(ModItems.WOODEN_HANDLE.get()))
                .save(pOutput);

//        for (String tool : new String[]{"shovel", "sword", "pickaxe", "hoe", "axe"}) {
//            removeRecipe(pOutput, "wooden_" + tool);
//        }
//        removeRecipe(pOutput, "arrow");

    }

    protected static void itemListAndItemLike(@NotNull RecipeOutput pOutput, @NotNull RecipeCategory recipeCategory,
                                         @NotNull ItemLike recipeResult, @NotNull ItemLike recipeIngredient, List<Item> itemList) {
        for (Item singleItem : itemList) {
            ShapelessRecipeBuilder.shapeless(recipeCategory, recipeResult, 1)
                    .requires(recipeIngredient).requires(singleItem)
                    .unlockedBy(getHasName(recipeIngredient), has(recipeIngredient))
                    .unlockedBy("has_" + singleItem.toString(), has(singleItem))
                    .save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(recipeResult) + "_from_" + getItemName(recipeIngredient) + "_and_" + getItemName(singleItem));
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

    private static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pOutput, RecipeSerializer<T> recipeSerializer,
                                                                     AbstractCookingRecipe.Factory<T> cookingRecipe, List<ItemLike> acceptableItems,
                                                                     RecipeCategory recipeCategory, ItemLike cookingOutput,
                                                                     float experienceAmount, int cookingTime, String group, String methodString) {
        for(ItemLike itemlike : acceptableItems) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), recipeCategory, cookingOutput, experienceAmount, cookingTime, recipeSerializer, cookingRecipe)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pOutput, TechPlusPlus.MOD_ID + ":" + (cookingOutput) + methodString + "_" + getItemName(itemlike));
        }

    }

    protected static void removeRecipe(@NotNull RecipeOutput pOutput, @NotNull String recipeName) {
        new EmptyRecipeBuilder().save(pOutput, recipeName);
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
                .save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(storageItem) + "_from_" + getItemName(singleItem));

        ShapelessRecipeBuilder.shapeless(singleRecipeCategory, singleItem, 9)
                .requires(storageItem)
                .unlockedBy(getHasName(storageItem), has(storageItem))
                .save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(singleItem) + "_from_" + getItemName(storageItem));
    }
}
