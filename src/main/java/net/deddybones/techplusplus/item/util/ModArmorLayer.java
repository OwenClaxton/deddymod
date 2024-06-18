package net.deddybones.techplusplus.item.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;

import static net.deddybones.techplusplus.datagen.util.ModHelper.modLoc;

public class ModArmorLayer extends ArmorMaterial.Layer {

    public ModArmorLayer(ResourceLocation pAssetName, String pSuffix, boolean pIsDyeable) {
        super(pAssetName, pSuffix, pIsDyeable);
        this.innerTexture = this.resolveTexture(true);
        this.outerTexture = this.resolveTexture(false);
    }

    public ModArmorLayer(ResourceLocation pAssetName) {
        this(pAssetName, "", false);
    }

    private ResourceLocation resolveTexture(boolean pIsInner) {
        return modLoc("textures/models/armor/" + this.assetName.getPath() + "_layer_"
                        + (pIsInner ? 2 : 1) + this.getSuffix() + ".png");
    }
}
