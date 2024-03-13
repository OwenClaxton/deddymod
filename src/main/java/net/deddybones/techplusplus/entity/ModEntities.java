package net.deddybones.techplusplus.entity;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.entity.custom.ThrownWoodenSpear;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TechPlusPlus.MOD_ID);

    public static final RegistryObject<EntityType<ThrownWoodenSpear>> THROWN_WOODEN_SPEAR_ENTITY_TYPE =
            ENTITY_TYPES.register("thrown_wooden_spear_entity_type",
                    () -> EntityType.Builder.<ThrownWoodenSpear>of(ThrownWoodenSpear::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("thrown_wooden_spear_entity_type"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
