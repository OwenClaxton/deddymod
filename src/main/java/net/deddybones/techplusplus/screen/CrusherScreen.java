package net.deddybones.techplusplus.screen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrusherScreen extends AbstractContainerScreen<CrusherMenu> {
   private static final ResourceLocation BG_LOCATION = new ResourceLocation(TechPlusPlus.MOD_ID + ":textures/gui/crusher.png");

   public CrusherScreen(CrusherMenu pMenu, Inventory pInventory, Component pComp) {
      super(pMenu, pInventory, pComp);
      --this.titleLabelY;
   }

   protected void renderBg(GuiGraphics pGui, float p_282453_, int p_282940_, int p_282328_) {
      int i = this.leftPos;
      int j = this.topPos;
      pGui.blit(BG_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
   }
}