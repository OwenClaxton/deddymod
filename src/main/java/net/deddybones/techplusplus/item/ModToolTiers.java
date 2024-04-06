package net.deddybones.techplusplus.item;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers{

    public static final Tier PRIMITIVE = TierSortingRegistry.registerTier(
            new ForgeTier(0, 50, 1.0f, 0.0f, 0,
                          ModTags.Blocks.NEEDS_PRIMITIVE_TOOL, () -> Ingredient.of(ModItems.PLASTIMETAL_INGOT.get())),
            new ResourceLocation(TechPlusPlus.MOD_ID, "primitive"), List.of(), List.of());

    public static final Tier COPPER = TierSortingRegistry.registerTier(
            new ForgeTier(1, 100, 2.0f, 1.0f, 5,
                    ModTags.Blocks.NEEDS_COPPER_OR_TIN_TOOL, () -> Ingredient.of(Items.COPPER_INGOT)),
            new ResourceLocation(TechPlusPlus.MOD_ID, "copper"), List.of(ModToolTiers.PRIMITIVE), List.of());

    public static final Tier TIN = TierSortingRegistry.registerTier(
            new ForgeTier(1, 100, 2.0f, 1.0f, 5,
                    ModTags.Blocks.NEEDS_COPPER_OR_TIN_TOOL, () -> Ingredient.of(ModItems.TIN_INGOT.get())),
            new ResourceLocation(TechPlusPlus.MOD_ID, "tin"), List.of(ModToolTiers.PRIMITIVE), List.of());

    public static final Tier BRONZE = TierSortingRegistry.registerTier(
            new ForgeTier(2, 200, 5.0f, 1.5f, 8,
                    ModTags.Blocks.NEEDS_BRONZE_TOOL, () -> Ingredient.of(ModItems.BRONZE_INGOT.get())),
            new ResourceLocation(TechPlusPlus.MOD_ID, "bronze"), List.of(ModToolTiers.COPPER, ModToolTiers.TIN), List.of(Tiers.IRON));

//    IRON(2, 250, 6.0F, 2.0F, 14, () -> {
//        return Ingredient.of(Items.IRON_INGOT);
//    }),

    public static final Tier PLASTIMETAL = TierSortingRegistry.registerTier(
            new ForgeTier(4, 500, 8.0f, 2.5f, 14,
                    ModTags.Blocks.NEEDS_PLASTIMETAL_TOOL, () -> Ingredient.of(ModItems.PLASTIMETAL_INGOT.get())),
            new ResourceLocation(TechPlusPlus.MOD_ID, "plastimetal"), List.of(Tiers.IRON), List.of());


}