package net.deddybones.techplusplus.item.util;

import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.util.ModTags;
import net.deddybones.techplusplus.util.TierNumerics;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier PRIMITIVE = new ForgeTier(TierNumerics.PRIMITIVE.getNumUses(),
                    TierNumerics.PRIMITIVE.getBaseAttackSpeedBonus(), TierNumerics.PRIMITIVE.getBaseAttackDamageBonus(),
                    TierNumerics.PRIMITIVE.getToolEnchantmentValue(), ModTags.Blocks.NEEDS_PRIMITIVE_TOOL,
                    () -> Ingredient.of(ModItems.PLASTIMETAL_INGOT.get()), ModTags.Blocks.NEEDS_COPPER_OR_TIN_TOOL);

    public static final Tier COPPER = new ForgeTier(TierNumerics.COPPER.getNumUses(),
                    TierNumerics.COPPER.getBaseAttackSpeedBonus(), TierNumerics.COPPER.getBaseAttackDamageBonus(),
                    TierNumerics.COPPER.getToolEnchantmentValue(), ModTags.Blocks.NEEDS_COPPER_OR_TIN_TOOL,
                    () -> Ingredient.of(Items.COPPER_INGOT), ModTags.Blocks.NEEDS_BRONZE_TOOL);

    public static final Tier TIN = new ForgeTier(TierNumerics.TIN.getNumUses(),
                    TierNumerics.TIN.getBaseAttackSpeedBonus(), TierNumerics.TIN.getBaseAttackDamageBonus(),
                    TierNumerics.TIN.getToolEnchantmentValue(), ModTags.Blocks.NEEDS_COPPER_OR_TIN_TOOL,
                    () -> Ingredient.of(ModItems.TIN_INGOT.get()), ModTags.Blocks.NEEDS_BRONZE_TOOL);

    public static final Tier BRONZE = new ForgeTier(TierNumerics.BRONZE.getNumUses(),
                    TierNumerics.BRONZE.getBaseAttackSpeedBonus(), TierNumerics.BRONZE.getBaseAttackDamageBonus(),
                    TierNumerics.BRONZE.getToolEnchantmentValue(), ModTags.Blocks.NEEDS_BRONZE_TOOL,
                    () -> Ingredient.of(ModItems.BRONZE_INGOT.get()), BlockTags.NEEDS_IRON_TOOL);

    public static final Tier PLASTIMETAL = new ForgeTier(TierNumerics.PLASTIMETAL.getNumUses(),
                    TierNumerics.PLASTIMETAL.getBaseAttackSpeedBonus(), TierNumerics.PLASTIMETAL.getBaseAttackDamageBonus(),
                    TierNumerics.PLASTIMETAL.getToolEnchantmentValue(), ModTags.Blocks.NEEDS_PLASTIMETAL_TOOL,
                    () -> Ingredient.of(ModItems.PLASTIMETAL_INGOT.get()), ModTags.Blocks.NEEDS_GOD_TOOL);
}