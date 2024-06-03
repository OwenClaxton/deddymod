package net.deddybones.techplusplus.block.entity;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TechPlusPlus.MOD_ID);

    public static final RegistryObject<BlockEntityType<KilnBlockEntity>> KILN =
            BLOCK_ENTITIES.register("kiln", () ->
                    BlockEntityType.Builder.of(KilnBlockEntity::new,
                            ModBlocks.KILN.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrusherBlockEntity>> CRUSHER =
            BLOCK_ENTITIES.register("crusher", () ->
                    BlockEntityType.Builder.of(CrusherBlockEntity::new,
                            ModBlocks.CRUSHER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ClayMolderBlockEntity>> CLAY_MOLDER =
            BLOCK_ENTITIES.register("clay_molder", () ->
                    BlockEntityType.Builder.of(ClayMolderBlockEntity::new,
                            ModBlocks.CLAY_MOLDER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}