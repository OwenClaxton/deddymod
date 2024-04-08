package net.deddybones.techplusplus.util;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TechPlusPlus.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TECHPLUSPLUS_TAB =
            CREATIVE_MODE_TABS.register("techplusplus_tab",
                    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                            .title(Component.translatable("creativetab.techplusplus_tab"))
                            .displayItems((itemDisplayParameters, output) -> {

                                ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
                                ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);

                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
