package net.deddybones.techplusplus.item;

import net.deddybones.techplusplus.TechPlusPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HorseArmorItem;
import org.jetbrains.annotations.NotNull;

public class ModHorseArmorItem extends HorseArmorItem {
    private static final String TEX_FOLDER = TechPlusPlus.MOD_ID + ":textures/entity/horse/";
    private final int protection;
    private final ResourceLocation texture;

    public ModHorseArmorItem(int pProtection, String pTexture, Properties pProperties) {
        this(pProtection, new ResourceLocation(TechPlusPlus.MOD_ID + ":textures/entity/horse/armor/horse_armor_" + pTexture + ".png"), pProperties);
    }

    public ModHorseArmorItem(int pProtection, ResourceLocation pTexture, Properties pProperties) {
        super(pProtection, pTexture, pProperties);
        this.protection = pProtection;
        this.texture = pTexture;
    }

    @Override
    public @NotNull ResourceLocation getTexture() {
        return texture;
    }

    public int getProtection() {
        return this.protection;
    }
}
