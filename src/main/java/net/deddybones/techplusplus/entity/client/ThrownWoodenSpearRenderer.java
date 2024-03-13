package net.deddybones.techplusplus.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.entity.custom.ThrownWoodenSpear;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ThrownWoodenSpearRenderer extends EntityRenderer<ThrownWoodenSpear> {
    public static final ResourceLocation THROWN_WOODEN_SPEAR_LOCATION = new ResourceLocation(TechPlusPlus.MOD_ID, "textures/entity/thrown_wooden_spear.png");
    private final ThrownWoodenSpearModel model;

    public ThrownWoodenSpearRenderer(EntityRendererProvider.Context p_174420_) {
        super(p_174420_);
        this.model = new ThrownWoodenSpearModel(p_174420_.bakeLayer(ModModelLayers.THROWN_WOODEN_SPEAR_LAYER));
    }

    public void render(ThrownWoodenSpear p_116111_, float p_116112_, float p_116113_, PoseStack p_116114_, @NotNull MultiBufferSource p_116115_, int p_116116_) {
        p_116114_.pushPose();
        p_116114_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_116113_, p_116111_.yRotO, p_116111_.getYRot()) - 90.0F));
        p_116114_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_116113_, p_116111_.xRotO, p_116111_.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(p_116115_, this.model.renderType(this.getTextureLocation(p_116111_)), false, false);//p_116111_.isFoil());
        this.model.renderToBuffer(p_116114_, vertexconsumer, p_116116_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        p_116114_.popPose();
        super.render(p_116111_, p_116112_, p_116113_, p_116114_, p_116115_, p_116116_);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrownWoodenSpear p_116109_) {
        return THROWN_WOODEN_SPEAR_LOCATION;
    }
}