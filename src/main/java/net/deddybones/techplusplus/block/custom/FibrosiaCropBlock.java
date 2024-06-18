package net.deddybones.techplusplus.block.custom;

import net.deddybones.techplusplus.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class FibrosiaCropBlock extends ModCropBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    public static final RegistryObject<Item> SEED_ITEM = ModItems.FIBROSIA_SEEDS;

    public FibrosiaCropBlock(Properties p_52247_) {
        super(p_52247_);
    }

    @Override
    public @NotNull ItemLike getBaseSeedId() {
        return SEED_ITEM.get();
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
