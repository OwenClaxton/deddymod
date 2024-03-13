package net.deddybones.techplusplus.item;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TechPlusPlus.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("deddymod_tab",
                    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                            .title(Component.translatable("creativetab.deddymod_tab"))
                            .displayItems((itemDisplayParameters, output) -> {

                                output.accept(ModItems.SAPPHIRE.get());
                                output.accept(ModItems.PLASTIMETAL_INGOT.get());
                                output.accept(ModItems.PLASTIMETAL_NUGGET.get());
                                output.accept(ModItems.RAW_PLASTIMETAL.get());

                                output.accept(ModItems.TIN_INGOT.get());
                                output.accept(ModItems.TIN_NUGGET.get());
                                output.accept(ModItems.RAW_TIN.get());

                                output.accept(ModItems.BRONZE_INGOT.get());
                                output.accept(ModItems.BRONZE_NUGGET.get());
                                output.accept(ModItems.RAW_BRONZE.get());

                                output.accept(ModItems.COPPER_NUGGET.get());

                                output.accept(ModItems.GPS_TOOL.get());

                                output.accept(ModItems.COFFEE_FOOD.get());
                                output.accept(ModItems.COFFEE_BEANS.get());

                                output.accept(ModItems.FIBROSIA_SEEDS.get());
                                output.accept(ModItems.PLANT_FIBERS.get());

                                output.accept(ModItems.STICK_BUNDLE.get());
                                output.accept(ModItems.KNAPPED_FLINT.get());
                                output.accept(ModItems.WOODEN_HANDLE.get());

                                output.accept(ModItems.FLINT_KNIFE.get());
                                output.accept(ModItems.WOODEN_SPEAR.get());
                                output.accept(ModItems.STONE_MATTOCK.get());

                                output.accept(ModItems.PLASTIMETAL_SWORD.get());
                                output.accept(ModItems.PLASTIMETAL_PICKAXE.get());
                                output.accept(ModItems.PLASTIMETAL_AXE.get());
                                output.accept(ModItems.PLASTIMETAL_SHOVEL.get());
                                output.accept(ModItems.PLASTIMETAL_HOE.get());

                                output.accept(ModItems.PLASTIMETAL_HELMET.get());
                                output.accept(ModItems.PLASTIMETAL_CHESTPLATE.get());
                                output.accept(ModItems.PLASTIMETAL_LEGGINGS.get());
                                output.accept(ModItems.PLASTIMETAL_BOOTS.get());
                                output.accept(ModItems.PLASTIMETAL_HORSE_ARMOR.get());

                                output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                                output.accept(ModBlocks.PLASTIMETAL_BLOCK.get());

                                output.accept(ModBlocks.SAPPHIRE_ORE.get());
                                output.accept(ModBlocks.PLASTIMETAL_BLOCK.get());
                                output.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());

                                output.accept(ModBlocks.RAW_PLASTIMETAL_BLOCK.get());
                                output.accept(ModBlocks.RUINED_PLASTIMETAL.get());
                                output.accept(ModBlocks.DEEPSLATE_RUINED_PLASTIMETAL.get());

                                output.accept(ModBlocks.PLASTIMETAL_DOOR.get());
                                output.accept(ModBlocks.PLASTIMETAL_TRAPDOOR.get());
                                output.accept(ModBlocks.PLASTIMETAL_BARS.get());

                                output.accept(ModBlocks.GLASS_STAIRS.get());
                                output.accept(ModBlocks.GLASS_SLAB.get());
                                output.accept(ModBlocks.GLASS_FENCE.get());
                                output.accept(ModBlocks.GLASS_FENCE_GATE.get());
                                output.accept(ModBlocks.GLASS_WALL.get());
                                output.accept(ModBlocks.GLASS_DOOR.get());
                                output.accept(ModBlocks.GLASS_TRAPDOOR.get());
                                output.accept(ModBlocks.GLASS_BUTTON.get());
                                output.accept(ModBlocks.GLASS_PRESSURE_PLATE.get());

                                output.accept(ModBlocks.TEST_BLOCK.get());

                                output.accept(ModBlocks.TINY_ROCK_BLOCK.get());
                                output.accept(ModBlocks.TINY_LOG_BLOCK.get());

                                output.accept(TweakedVanillaItems.IRON_AXE.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
