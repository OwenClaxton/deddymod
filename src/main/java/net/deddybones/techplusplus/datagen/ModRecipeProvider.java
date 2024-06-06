package net.deddybones.techplusplus.datagen;

import com.google.common.collect.ImmutableList;
import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.item.TweakedVanillaItems;
import net.deddybones.techplusplus.recipes.*;
import net.deddybones.techplusplus.recipes.builders.EmptyRecipeBuilder;
import net.deddybones.techplusplus.recipes.builders.ModSimpleCookingRecipeBuilder;
import net.deddybones.techplusplus.recipes.builders.ModSingleItemRecipeBuilder;
import net.deddybones.techplusplus.util.EquipmentCollection.EquipmentName;
import net.deddybones.techplusplus.util.OreCollection;
import net.deddybones.techplusplus.util.TierCollection;
import net.minecraft.core.HolderLookup;
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
import java.util.concurrent.CompletableFuture;

import static net.deddybones.techplusplus.datagen.util.ModHelper.getFromExt;
import static net.deddybones.techplusplus.recipes.builders.ModShapelessRecipeBuilder.shapeless;
import static net.deddybones.techplusplus.util.ObjectNumbered.ObN;

@SuppressWarnings({"SameParameterValue", "unused"})
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES =
            ImmutableList.of(ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
    public static final ImmutableList<ItemLike> PLASTIMETAL_BILLET_SMELTABLES =
            ImmutableList.of(ModItems.PLASTIMETAL_NUGGET.get(), ModBlocks.RUINED_PLASTIMETAL.get(),
                    ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get());
    public static final ImmutableList<ItemLike> PLASTIMETAL_INGOT_SMELTABLES =
            ImmutableList.of(ModItems.RAW_PLASTIMETAL.get());
    public static final Ingredient PLASTIMETAL_RECYCLABLES =
            Ingredient.of(ModItems.PLASTIMETAL_PICKAXE.get(), ModItems.PLASTIMETAL_SHOVEL.get(),
                    ModItems.PLASTIMETAL_AXE.get(), ModItems.PLASTIMETAL_HOE.get(), ModItems.PLASTIMETAL_SWORD.get(),
                    ModItems.PLASTIMETAL_HELMET.get(), ModItems.PLASTIMETAL_CHESTPLATE.get(),
                    ModItems.PLASTIMETAL_LEGGINGS.get(), ModItems.PLASTIMETAL_BOOTS.get(),
                    ModItems.PLASTIMETAL_HORSE_ARMOR.get());
    public static final Ingredient IRON_RECYCLABLES =
            Ingredient.of(Items.IRON_PICKAXE, Items.IRON_SHOVEL, TweakedVanillaItems.IRON_AXE.get(),
                    Items.IRON_HOE, Items.IRON_SWORD, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS,
                    Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR, Blocks.IRON_BARS, Blocks.IRON_DOOR,
                    Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }

    protected void makeClayRecipes(@NotNull RecipeOutput pOutput) {
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, Items.CLAY_BALL,
                RecipeCategory.BUILDING_BLOCKS, Blocks.CLAY);
        shapeless(pOutput, RecipeCategory.MISC, ModItems.CLAY_CHUNK.get(), 1,
                List.of(ObN(Items.CLAY_BALL, 3))).save();
        shapeless(pOutput, RecipeCategory.MISC, ModItems.LARGE_CLAY_CHUNK.get(), 1,
                List.of(ObN(Items.CLAY_BALL, 6))).save();
        shapeless(pOutput, RecipeCategory.MISC, Items.CLAY_BALL, 3,
                List.of(ObN(ModItems.CLAY_CHUNK.get())), getFromExt(ModItems.CLAY_CHUNK.get())).save();
        shapeless(pOutput, RecipeCategory.MISC, Items.CLAY_BALL, 6,
                List.of(ObN(ModItems.LARGE_CLAY_CHUNK.get())), getFromExt(ModItems.LARGE_CLAY_CHUNK.get())).save();
    }

    protected void makeMainMetalRecipes(@NotNull RecipeOutput pOutput) {
        for (OreCollection oreCollection : OreCollection.values()) {
            boolean nuggetNotNull       = oreCollection.getNuggetItem() != null;
            boolean billetNotNull       = oreCollection.getBilletItem() != null;
            boolean rawNotNull          = oreCollection.getRawItem() != null;
            boolean ingotNotNull        = oreCollection.getIngotItem() != null;
            boolean rawBlockNotNull     = oreCollection.getRawBlock() != null;
            boolean cookedBlockNotNull  = oreCollection.getCookedBlock() != null;
            if (nuggetNotNull && rawNotNull) // 9x Nugget -> 1x Raw
                nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, oreCollection.getNuggetItem(),
                        RecipeCategory.MISC, oreCollection.getRawItem());
            if (rawNotNull && rawBlockNotNull) // 9x Raw Item -> 1x Raw Block
                nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, oreCollection.getRawItem(),
                        RecipeCategory.BUILDING_BLOCKS, oreCollection.getRawBlock());
            if (billetNotNull && ingotNotNull) // 9x Billet -> 1x Ingot
                nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, oreCollection.getBilletItem(),
                        RecipeCategory.MISC, oreCollection.getIngotItem());
            if (ingotNotNull && cookedBlockNotNull) // 9x Ingot -> 1x Cooked Block
                nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, oreCollection.getIngotItem(),
                        RecipeCategory.BUILDING_BLOCKS, oreCollection.getCookedBlock());
            if (nuggetNotNull && billetNotNull) {
                if (oreCollection.kilnOK()) {
                    // 1x Nugget -> 1x Billet @ 1x speed
                    oreKiln(pOutput, ImmutableList.of(oreCollection.getNuggetItem()), RecipeCategory.MISC,
                            oreCollection.getBilletItem(), 1.0F, 200, oreCollection.name());
                }
                if (oreCollection.furnaceOK()) {
                    // 1x Raw Item -> 1x Ingot @ 1x speed
                    oreSmelting(pOutput, ImmutableList.of(oreCollection.getRawItem()), RecipeCategory.MISC,
                            oreCollection.getIngotItem(), 1.0F, 200, oreCollection.name());
                    // 1x Nugget -> 1x Billet @ 4x speed
                    oreSmelting(pOutput, ImmutableList.of(oreCollection.getNuggetItem()), RecipeCategory.MISC,
                            oreCollection.getBilletItem(), 1.0F, 50, oreCollection.name());
                }
                if (oreCollection.forgeOK()) {
                    // 1x Raw Block -> 1x Cooked Block @ 1x speed
                    oreBlasting(pOutput, ImmutableList.of(oreCollection.getRawBlock()), RecipeCategory.MISC,
                            oreCollection.getCookedBlock(), 1.0F, 200, oreCollection.name());
                    // 1x Raw Item -> 1x Ingot @ 4x speed
                    oreBlasting(pOutput, ImmutableList.of(oreCollection.getRawItem()), RecipeCategory.MISC,
                            oreCollection.getIngotItem(), 1.0F, 50, oreCollection.name());
                    // 1x Nugget -> 1x Billet @ 8x speed
                    oreBlasting(pOutput, ImmutableList.of(oreCollection.getNuggetItem()), RecipeCategory.MISC,
                            oreCollection.getBilletItem(), 1.0F, 12, oreCollection.name());
                }
            }
        }
    }

    protected void makeAlloyRecipes(@NotNull RecipeOutput pOutput) {
        shapeless(pOutput, RecipeCategory.MISC, ModItems.RAW_BRONZE.get(), 1,
                List.of(ObN(ModItems.COPPER_BILLET.get(), 7), ObN(ModItems.TIN_BILLET.get(), 2))).save();
        shapeless(pOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_BRONZE_BLOCK.get(), 1,
                List.of(ObN(Items.COPPER_INGOT, 7), ObN(ModItems.TIN_INGOT.get(), 2))).save();
    }

    protected void makeToolRecipes(@NotNull RecipeOutput pOutput) {
        for (TierCollection tierCollection : TierCollection.values()) {
            for (EquipmentName tool : EquipmentName.values()) {
                ItemLike toolItemLike = tierCollection.getEquip().getToolItem(tool);
                ItemLike toolPartItemLike = tierCollection.getComp().getToolPartItem(tool);
                if ((toolItemLike != null) && (toolPartItemLike != null)) {
                    shapeless(pOutput, RecipeCategory.TOOLS, toolItemLike, 1,
                            List.of(ObN(toolPartItemLike), ObN(ModItems.WOODEN_HANDLE.get()), ObN(Items.STRING))
                    ).save();
                }
            }
        }
    }

    protected void makeArmorRecipes(@NotNull RecipeOutput pOutput) {
        for (TierCollection tierCollection : TierCollection.values()) {
            for (EquipmentName armor : EquipmentName.values()) {
                ItemLike armorItemLike = tierCollection.getEquip().getArmorItem(armor);
                if (armorItemLike != null) {
//                    shapeless(pOutput, RecipeCategory.TOOLS, toolItemLike, 1,
//                            List.of(ObN(toolPartItemLike), ObN(ModItems.WOODEN_HANDLE.get()), ObN(Items.STRING))
//                    ).save();
                }
            }
        }
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pOutput) {
        makeClayRecipes(pOutput);
        makeMainMetalRecipes(pOutput);
        makeToolRecipes(pOutput);
        makeArmorRecipes(pOutput);
        makeAlloyRecipes(pOutput);

        oreSmelting(pOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 1.0F, 200, "sapphire");
        oreBlasting(pOutput, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 1.0F, 100, "sapphire");
        nineThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAPPHIRE_BLOCK.get());

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

        shapeless(pOutput, RecipeCategory.MISC, ModItems.KNAPPED_FLINT.get(), 2, List.of(ObN(Items.FLINT), ObN(ModBlocks.TINY_ROCK_BLOCK.get()))).save();
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.ARROW, 3).define('#', Items.STICK).define('X', ModItems.KNAPPED_FLINT.get()).define('Y', Items.FEATHER).pattern("X").pattern("#").pattern("Y").unlockedBy("has_feather", has(Items.FEATHER)).unlockedBy("has_stick", has(Items.STICK)).unlockedBy("has_knapped_flint", has(ModItems.KNAPPED_FLINT.get())).save(pOutput, TechPlusPlus.MOD_ID + ":arrow");

        List<ItemLike> CarverList = List.of(ModItems.FLINT_KNIFE.get(), ModItems.PLASTIMETAL_AXE.get(), Items.IRON_AXE);

        itemListAndItemLike(pOutput, RecipeCategory.MISC, ModItems.WOODEN_HANDLE.get(), ModBlocks.TINY_LOG_BLOCK.get(), CarverList);
        itemListAndItemLike(pOutput, RecipeCategory.MISC, ModItems.WOODEN_SPEAR.get(), ModItems.WOODEN_HANDLE.get(), CarverList);

        fourThingsStorageRecipes(pOutput, RecipeCategory.MISC, ModBlocks.TINY_ROCK_BLOCK.get(), RecipeCategory.BUILDING_BLOCKS, Blocks.COBBLESTONE);

        shapeless(pOutput, RecipeCategory.TOOLS, ModItems.FLINT_KNIFE.get(), 1, List.of(ObN(ModItems.KNAPPED_FLINT.get()), ObN(Items.STICK))).save();
        shapeless(pOutput, RecipeCategory.TOOLS, ModItems.STONE_MATTOCK.get(), 1, List.of(ObN(ModBlocks.TINY_ROCK_BLOCK.get()), ObN(ModItems.WOODEN_HANDLE.get()), ObN(Items.STRING))).save();


//        for (String tool : new String[]{"shovel", "sword", "pickaxe", "hoe", "axe"}) {
//            removeRecipe(pOutput, "wooden_" + tool);
//        }
//        removeRecipe(pOutput, "arrow");

        // gold nuggets used in firework star, glistering melon slice, and golden carrot recipes -> swap to gold billet:
        SpecialRecipeBuilder.special(ModFireworkStarRecipe::new).save(pOutput,  TechPlusPlus.MOD_ID + ":" + getItemName(Items.FIREWORK_STAR));
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, Items.GOLDEN_CARROT).define('#', ModItems.GOLD_BILLET.get()).define('X', Items.CARROT).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_gold_nugget", has(Items.GOLD_NUGGET)).save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(Items.GOLDEN_CARROT));
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, Items.GLISTERING_MELON_SLICE).define('#', ModItems.GOLD_BILLET.get()).define('X', Items.MELON_SLICE).pattern("###").pattern("#X#").pattern("###").unlockedBy("has_melon", has(Items.MELON_SLICE)).save(pOutput, TechPlusPlus.MOD_ID + ":" + getItemName(Items.GLISTERING_MELON_SLICE));

        // Clay Molder Recipes
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_SWORD_PARTS.get(),      ModItems.CLAY_CHUNK.get());         // 2xI : WEAPON: swords
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_AXE_PART.get(),         ModItems.CLAY_CHUNK.get());         // 3xI : WEAPON: axes
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_PICKAXE_PART.get(),     ModItems.CLAY_CHUNK.get());         // 3xI : TOOL: pickaxe
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_SHOVEL_PART.get(),      ModItems.CLAY_CHUNK.get());         // 1xI : TOOL: shovel
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_HOE_PART.get(),         ModItems.CLAY_CHUNK.get());         // 2xI : TOOL: hoe
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_ARROW_HEADS.get(),      ModItems.CLAY_CHUNK.get());         // 1xI : WEAPON: arrows
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_BLADE.get(),            ModItems.CLAY_CHUNK.get());         // 1xI : TOOL: shears
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_ROD.get(),              ModItems.CLAY_CHUNK.get());         // 1xI : for tools, weapons, bar blocks
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_FLAT_PANEL.get(),       ModItems.LARGE_CLAY_CHUNK.get());   // 1xI : shield, pressure plates, minecarts, armor, blocks, armor
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_ROUND_PANEL.get(),      ModItems.LARGE_CLAY_CHUNK.get());   // 1xI : bucket, armor, blocks, armor
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_DISK.get(),             ModItems.LARGE_CLAY_CHUNK.get());   // 1xI : minecart (wheels), saw bench, stonecutter
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_FASTENERS.get(),        ModItems.CLAY_CHUNK.get());         // 1xI : for armor, bar blocks, blocks,
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_MECHANISM_PIECES.get(), ModItems.CLAY_CHUNK.get());         // 1xI : piston, crossbow, clock, compass, tripwire hook, metal (trap)doors
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_BODY.get(),             Blocks.CLAY);                       // 1xI : for armor, bar blocks, blocks,
        moldingResult(pOutput, RecipeCategory.MISC, ModItems.MOLD_BEAM.get(),             Blocks.CLAY);                       // 1xI : piston, crossbow, clock, compass, tripwire hook, metal (trap)doors

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
            shapeless(pOutput, recipeCategory, recipeResult, 1, List.of(ObN(recipeIngredient), ObN(ingredientItem)))
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

        shapeless(pOutput, singleRecipeCategory, singleItem, 4, List.of(ObN(storageItem)), getFromExt(storageItem)).save();
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

        shapeless(pOutput, singleRecipeCategory, singleItem, 9, List.of(ObN(storageItem)), getFromExt(storageItem)).save();
    }
}
