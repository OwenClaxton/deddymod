package net.deddybones.deddymod.datagen;

import net.deddybones.deddymod.DeddyMod;
import net.deddybones.deddymod.item.ModItems;
import net.deddybones.deddymod.loot.ModAddItemModifier;
import net.deddybones.deddymod.loot.ModAddSuspiciousSandItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, DeddyMod.MOD_ID);
    }

    @Override
    protected void start() {
//        add("plastimetal_nugget_from_grass", new ModAddItemModifier(new LootItemCondition[]{
//                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
//                LootItemRandomChanceCondition.randomChance(1f).build(),
//        }, ModItems.PLASTIMETAL_NUGGET.get()));

        add("plastimetal_nugget_from_zombie", new ModAddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()
        }, ModItems.PLASTIMETAL_NUGGET.get()));

        add("plastimetal_ingot_from_mineshaft_chest", new ModAddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()
        }, ModItems.PLASTIMETAL_INGOT.get()));

        add("plastimetal_ingot_from_suspicious_sand", new ModAddSuspiciousSandItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()
        }, ModItems.PLASTIMETAL_INGOT.get()));


    }
}
