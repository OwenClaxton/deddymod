package net.deddybones.deddymod.item;

import net.deddybones.deddymod.DeddyMod;
import net.deddybones.deddymod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers{

    public static final Tier PRIMITIVE = TierSortingRegistry.registerTier(
            new ForgeTier(0, 50, 1f, 0.5f, 0,
                          ModTags.Blocks.NEEDS_PRIMITIVE_TOOL, () -> Ingredient.of(ModItems.PLASTIMETAL_INGOT.get())),
            new ResourceLocation(DeddyMod.MOD_ID, "primitive"), List.of(), List.of());

    public static final Tier COPPER = TierSortingRegistry.registerTier(
            new ForgeTier(0, 100, 2f, 1f, 5,
                    ModTags.Blocks.NEEDS_COPPERTIN_TOOL, () -> Ingredient.of(Items.COPPER_INGOT)),
            new ResourceLocation(DeddyMod.MOD_ID, "copper"), List.of(ModToolTiers.PRIMITIVE), List.of());

    public static final Tier TIN = TierSortingRegistry.registerTier(
            new ForgeTier(0, 100, 2f, 1f, 5,
                    ModTags.Blocks.NEEDS_COPPERTIN_TOOL, () -> Ingredient.of(ModItems.TIN_INGOT.get())),
            new ResourceLocation(DeddyMod.MOD_ID, "tin"), List.of(ModToolTiers.PRIMITIVE), List.of());

    public static final Tier BRONZE = TierSortingRegistry.registerTier(
            new ForgeTier(0, 200, 3f, 1.5f, 8,
                    ModTags.Blocks.NEEDS_BRONZE_TOOL, () -> Ingredient.of(ModItems.BRONZE_INGOT.get())),
            new ResourceLocation(DeddyMod.MOD_ID, "bronze"), List.of(ModToolTiers.COPPER, ModToolTiers.TIN), List.of(Tiers.IRON));
    public static final Tier PLASTIMETAL = TierSortingRegistry.registerTier(
            new ForgeTier(2, 500, 7f, 2.5f, 14,
                    ModTags.Blocks.NEEDS_PLASTIMETAL_TOOL, () -> Ingredient.of(ModItems.PLASTIMETAL_INGOT.get())),
            new ResourceLocation(DeddyMod.MOD_ID, "plastimetal"), List.of(Tiers.IRON), List.of());


}