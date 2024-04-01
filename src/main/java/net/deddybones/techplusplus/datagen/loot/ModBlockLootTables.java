package net.deddybones.techplusplus.datagen.loot;

import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.block.custom.CoffeePlantBlock;
import net.deddybones.techplusplus.block.custom.FibrosiaCropBlock;
import net.deddybones.techplusplus.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    private final List<Block> VANILLA_BLOCKS_CHANGED = List.of(Blocks.CLAY);

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(Blocks.CLAY, block -> createSingleItemTableWithSilkTouch(block, Items.CLAY_BALL, ConstantValue.exactly(9.0F)));

        this.dropSelf(ModBlocks.BAUXITE.get());
        this.dropSelf(ModBlocks.POLISHED_BAUXITE.get());

        this.dropSelf(ModBlocks.RAW_PLASTIMETAL_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_TIN_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_BRONZE_BLOCK.get());

        this.dropSelf(ModBlocks.PLASTIMETAL_BLOCK.get());
        this.dropSelf(ModBlocks.TIN_BLOCK.get());
        this.dropSelf(ModBlocks.BRONZE_BLOCK.get());
        this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());

        this.add(ModBlocks.TIN_ORE.get(), block -> createOreDrop(block, ModItems.RAW_TIN.get()));
        this.add(ModBlocks.DEEPSLATE_TIN_ORE.get(), block -> createOreDrop(block, ModItems.RAW_TIN.get()));

        this.add(ModBlocks.SAPPHIRE_ORE.get(), block -> createOreDrop(block, ModItems.SAPPHIRE.get()));
        this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), block -> createOreDrop(block, ModItems.SAPPHIRE.get()));

        this.add(ModBlocks.RUINED_PLASTIMETAL.get(), block -> createOreDrop(block, ModItems.RAW_PLASTIMETAL.get()));
        this.add(ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get(), block -> createOreDrop(block, ModItems.RAW_PLASTIMETAL.get()));

        this.dropWhenSilkTouch(ModBlocks.GLASS_STAIRS.get());
        this.dropWhenSilkTouch(ModBlocks.GLASS_BUTTON.get());
        this.dropWhenSilkTouch(ModBlocks.GLASS_PRESSURE_PLATE.get());
        this.dropWhenSilkTouch(ModBlocks.GLASS_TRAPDOOR.get());
        this.dropWhenSilkTouch(ModBlocks.GLASS_FENCE.get());
        this.dropWhenSilkTouch(ModBlocks.GLASS_FENCE_GATE.get());
        this.dropWhenSilkTouch(ModBlocks.GLASS_WALL.get());

        this.dropWhenSilkTouch(ModBlocks.TEST_BLOCK.get());

        this.add(ModBlocks.GLASS_SLAB.get(), this::createSilkTouchSlabItemTable);
        this.add(ModBlocks.GLASS_DOOR.get(), this::createSilkTouchDoorTable);

        this.add(ModBlocks.PLASTIMETAL_DOOR.get(), this::createDoorTable);
        this.dropSelf(ModBlocks.PLASTIMETAL_TRAPDOOR.get());
        this.dropSelf(ModBlocks.PLASTIMETAL_BARS.get());

        this.dropSelf(ModBlocks.TINY_ROCK_BLOCK.get());
        this.dropSelf(ModBlocks.TINY_LOG_BLOCK.get());

        this.add(ModBlocks.COFFEE_CROP.get(),
                createCropDrop(ModBlocks.COFFEE_CROP.get(), ModItems.COFFEE_BEANS.get(),
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(ModBlocks.COFFEE_CROP.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(CoffeePlantBlock.AGE, CoffeePlantBlock.MAX_AGE))));
        this.add(ModBlocks.FIBROSIA_CROP.get(),
                createCropDrops(ModBlocks.FIBROSIA_CROP.get(), ModItems.PLANT_FIBERS.get(), ModItems.FIBROSIA_SEEDS.get(),
                        LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(ModBlocks.FIBROSIA_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(FibrosiaCropBlock.AGE, FibrosiaCropBlock.MAX_AGE))));

        this.dropSelf(ModBlocks.CRUSHER.get());
        this.dropSelf(ModBlocks.KILN.get());
        this.dropSelf(ModBlocks.CLAY_MOLDER.get());
    }

    protected LootTable.Builder createCropDrop(Block pBlock, Item pProduce, LootItemCondition.Builder pCondition) {
        return this.applyExplosionDecay(pBlock,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(pProduce).when(pCondition).otherwise(LootItem.lootTableItem(pProduce))))
                        .withPool(LootPool.lootPool()
                                .when(pCondition).add(LootItem.lootTableItem(pProduce)
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));
    }

    protected LootTable.Builder createSilkTouchSlabItemTable(Block p_251313_) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(HAS_SILK_TOUCH).setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(p_251313_, LootItem.lootTableItem(p_251313_)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(p_251313_)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    protected LootTable.Builder createSilkTouchDoorTable(Block pBlock) {
        return LootTable.lootTable()
                .withPool(this.applyExplosionCondition(pBlock, LootPool.lootPool()
                        .when(HAS_SILK_TOUCH).setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(pBlock)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(pBlock)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER))))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        ArrayList<Block> iterableBlocks = new ArrayList<>();
        ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(iterableBlocks::add);
        iterableBlocks.addAll(VANILLA_BLOCKS_CHANGED);

        return iterableBlocks;
    }
}
