package net.deddybones.techplusplus.item;

import net.deddybones.techplusplus.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TweakedVanillaItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    public static final RegistryObject<Item> IRON_AXE = ITEMS.register("iron_axe",
            () -> new CarverItem(Tiers.IRON, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
