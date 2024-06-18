package net.deddybones.techplusplus.item.custom;

import javax.annotation.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.deddybones.techplusplus.datagen.util.ModHelper.modLoc;

public class ModAnimalArmorItem extends AnimalArmorItem {
    public final ResourceLocation modTextureLocation;
    public final ResourceLocation modOverlayTextureLocation;

    public ModAnimalArmorItem(Holder<ArmorMaterial> pMaterial, AnimalArmorItem.BodyType pBodyType,
                              boolean pHasOverlay, Item.Properties pProperties) {
        super(pMaterial, pBodyType, pHasOverlay, pProperties);
        this.modTextureLocation = modLoc(super.getTexture().getPath());
        if (pHasOverlay) {
            this.modOverlayTextureLocation = modLoc(Objects.requireNonNull(super.getOverlayTexture()).getPath());
        } else {
            this.modOverlayTextureLocation = null;
        }
    }

    @Override
    public @NotNull ResourceLocation getTexture() {
        return modTextureLocation;
    }

    @Override
    public @Nullable ResourceLocation getOverlayTexture() {
        return modOverlayTextureLocation;
    }
}