package net.deddybones.techplusplus.block.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public abstract class ModCropBlock extends CropBlock {

    public ModCropBlock(Properties p_52247_) {
        super(p_52247_);
    }

    public abstract ItemLike getBaseSeedId();

    public abstract IntegerProperty getAgeProperty();

    public abstract int getMaxAge();
}
