package net.deddybones.techplusplus.block.entity;

import com.mojang.datafixers.types.Type;
import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.deddybones.techplusplus.TechPlusPlus.MOD_ID;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

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

    public static final RegistryObject<BlockEntityType<SmelteryBlockEntity>> SMELTERY =
            BLOCK_ENTITIES.register("smeltery", () ->
                    BlockEntityType.Builder.of(SmelteryBlockEntity::new,
                            ModBlocks.SMELTERY.get()).build(null));

//    public static <I extends BlockEntityType<?>> RegistryObject<I> register(
//            final String name, final Supplier<? extends I> sup) {
//        return BLOCK_ENTITIES.register(name, sup);
//    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}