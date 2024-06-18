package net.deddybones.techplusplus.event;

import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

import static net.deddybones.techplusplus.TechPlusPlus.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ModEvents {

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {

    }

//    @SubscribeEvent
//    public static void onOpenContainer(PlayerContainerEvent.Open event) {
//    }
//
//    @SubscribeEvent
//    public static void onCloseContainer(PlayerContainerEvent.Close event) {
//    }
//
//    @SubscribeEvent
//    public static void onLoggingIn(PlayerEvent.PlayerLoggedInEvent event) {
//    }
//
//    @SubscribeEvent
//    public static void onLoggingOut(PlayerEvent.PlayerLoggedOutEvent event) {
//    }

//    @SubscribeEvent
//    public static void pickupItem(EntityItemPickupEvent event) {
//    }

    @SubscribeEvent
    public static void onLeftClickingBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntity().isCreative()) return;
        Player player = event.getEntity();
        BlockState blockTarget = event.getLevel().getBlockState(event.getPos());
        boolean blockIsSoft     = blockTarget.is(ModTags.Blocks.SOFT_BLOCKS_OVERRIDE);
        boolean blockHurts      = blockTarget.is(ModTags.Blocks.HURTS) & !blockIsSoft;
        boolean usingTool       = event.getItemStack().is(ModTags.Items.IS_A_TOOL);
        boolean shouldCancel    = false;
        if (!usingTool) {
            if (blockHurts) shouldCancel = true;
            if (!event.getLevel().isClientSide() && blockHurts) player.hurt(event.getLevel().damageSources().generic(), 1);
        } else {
            boolean hasCorrectTool  = player.hasCorrectToolForDrops(blockTarget);
            if (!hasCorrectTool) shouldCancel = true;
        }
        event.setCanceled(shouldCancel);
    }

    public static boolean isRepairRecipe(Player player, Level level) {
        CraftingInput craftingInput;
        if (player.containerMenu.getClass() == InventoryMenu.class) {
            InventoryMenu iMenu = (InventoryMenu) player.containerMenu;
            craftingInput = iMenu.getCraftSlots().asCraftInput();
        } else {
            CraftingMenu cMenu = (CraftingMenu) player.containerMenu;
            NonNullList<ItemStack> craftingSlotContents = cMenu.slots.subList(1, 10).stream().map(Slot::getItem)
                    .collect(Collectors.toCollection(NonNullList::create));
            craftingInput = new TransientCraftingContainer(cMenu, 3, 3, craftingSlotContents).asCraftInput();
        }
        List<RecipeHolder<CraftingRecipe>> recipeList = level.getRecipeManager().getRecipesFor(RecipeType.CRAFTING, craftingInput, level);
        return recipeList.getFirst().id().toString().equals("minecraft:repair_item");
    }

    @SubscribeEvent
    public static void itemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Level level = event.getEntity().level();
        Player player = event.getEntity();
        if (!level.isClientSide()) {

            int startInd    = 1;
            int endInd      = (player.containerMenu.getClass() == InventoryMenu.class) ? 4 : 9;

            ItemStack craftingOutput = event.getCrafting();
            if (craftingOutput.is(ModTags.Items.CARVED_ITEM)) { // Check if we're performing a carving:
                for (int i = startInd; i <= endInd; i++) {
                    ItemStack thisItem = player.containerMenu.slots.get(i).getItem();
                    if (thisItem.is(ModTags.Items.CAN_CARVE)) {
                        thisItem.hurtAndBreak(1, (ServerLevel) player.level(),
                                player instanceof ServerPlayer ? (ServerPlayer) player : null,
                                (item) -> thisItem.setCount(0)); // TODO check.
                        return;
                    }
                }
            } else if (craftingOutput.is(ModTags.Items.CAN_CARVE)) { // Check if we're crafting a carver:
                if (! isRepairRecipe(player, level)) return;
                // past this point for repair recipes; we need to manually purge the input tools
                // because CarverItem will remain by default
                int toolsFound = 0;
                for (int i = startInd; i <= endInd; i++) {
                    ItemStack thisItem = player.containerMenu.slots.get(i).getItem();
                    if (thisItem.is(craftingOutput.getItem())) {
                        player.containerMenu.slots.get(i).set(ItemStack.EMPTY);
                        toolsFound++;
                        if (toolsFound > 1) return;
                    }
                }
            }
        }
    }
}
