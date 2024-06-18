package net.deddybones.techplusplus.loot;

import com.mojang.serialization.MapCodec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.deddybones.techplusplus.TechPlusPlus.MOD_ID;

@SuppressWarnings("unused")
public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MOD_ID);

    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("add_item", ModAddItemModifier.CODEC);

    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_SUSPICIOUS_SAND_ITEM =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("add_suspicious_sand_item", ModAddSuspiciousSandItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }

}
