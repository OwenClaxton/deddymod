package net.deddybones.techplusplus.event;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
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

@Mod.EventBusSubscriber(modid = TechPlusPlus.MOD_ID)
public class ModEvents {

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

//    @SubscribeEvent
//    public void onOpenContainer(PlayerContainerEvent.Open event) {
//    }
//
//    @SubscribeEvent
//    public void onCloseContainer(PlayerContainerEvent.Close event) {
//    }
//
//    @SubscribeEvent
//    public void onLoggingIn(PlayerEvent.PlayerLoggedInEvent event) {
//    }
//
//    @SubscribeEvent
//    public void onLoggingOut(PlayerEvent.PlayerLoggedOutEvent event) {
//    }

//    @SubscribeEvent
//    public void pickupItem(EntityItemPickupEvent event) {
//    }

    @SubscribeEvent
    public void onLeftClickingBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntity().isCreative()) return;
        Player player = event.getEntity();
        BlockState blockTarget = event.getLevel().getBlockState(event.getPos());
        boolean blockHurts      = blockTarget.is(ModTags.Blocks.HURTS);
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

    public boolean isRepairRecipe(Player player, Level level) {
        CraftingContainer craftingSlots;
        if (player.containerMenu.getClass() == InventoryMenu.class) {
            InventoryMenu iMenu = (InventoryMenu) player.containerMenu;
            craftingSlots = iMenu.getCraftSlots();
        } else {
            CraftingMenu cMenu = (CraftingMenu) player.containerMenu;
            NonNullList<ItemStack> craftingSlotContents = cMenu.slots.subList(1, 10).stream().map(Slot::getItem)
                    .collect(Collectors.toCollection(NonNullList::create));
            craftingSlots = new TransientCraftingContainer(cMenu, 3, 3, craftingSlotContents);
        }
        List<RecipeHolder<CraftingRecipe>> recipeList = level.getRecipeManager().getRecipesFor(RecipeType.CRAFTING, craftingSlots, level);
        return recipeList.get(0).id().toString().equals("minecraft:repair_item");
    }

    @SubscribeEvent
    public void itemCrafted(PlayerEvent.ItemCraftedEvent event) {
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
                        thisItem.hurt(1, player.getRandom(), player instanceof ServerPlayer ? (ServerPlayer) player : null);
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
