package net.deddybones.techplusplus.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

import static net.deddybones.techplusplus.datagen.util.ModHelper.modLoc;

public class ThrownWoodenSpearModel<T extends Entity> extends EntityModel<T> {
//    public static final ResourceLocation TEXTURE = modLoc("textures/entity/thrown_wooden_spear.png");
    private final ModelPart thrown_wooden_spear_model;

    public ThrownWoodenSpearModel(ModelPart root) {
        this.thrown_wooden_spear_model = root.getChild("thrown_wooden_spear_model");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        //PartDefinition thrown_wooden_spear_model_part =
        partdefinition.addOrReplaceChild("thrown_wooden_spear_model",
                CubeListBuilder.create().texOffs(0, 6)
                        .addBox(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F),
                PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(@NotNull T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_,
                          float p_102623_) {

    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int p_103113_,
                               int pOverlayCoords, int pColor) {
        thrown_wooden_spear_model.render(poseStack, vertexConsumer, p_103113_, pOverlayCoords, pColor);
    }
}