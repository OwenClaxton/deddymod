package net.deddybones.techplusplus.gui.screen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.gui.menu.KilnMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnMenu> {
    private static final ResourceLocation LIT_PROGRESS_SPRITE   = new ResourceLocation( "minecraft:container/furnace/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE  = new ResourceLocation( "minecraft:container/furnace/burn_progress");
    private static final ResourceLocation TEXTURE               = new ResourceLocation(TechPlusPlus.MOD_ID + ":textures/gui/container/kiln.png");

    public KilnScreen(KilnMenu pMenu, Inventory pInventory, Component pComponent) {
        super(pMenu, new SmeltingRecipeBookComponent(), pInventory, pComponent, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }

}