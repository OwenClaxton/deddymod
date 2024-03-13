package net.deddybones.techplusplus.event;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.entity.client.ModModelLayers;
import net.deddybones.techplusplus.entity.client.ThrownWoodenSpearModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TechPlusPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.THROWN_WOODEN_SPEAR_LAYER, ThrownWoodenSpearModel::createBodyLayer);
    }

}
