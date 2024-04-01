package net.deddybones.techplusplus.screen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.recipes.ClayMolderRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ClayMolderScreen extends AbstractContainerScreen<ClayMolderMenu> {
   private static final ResourceLocation SCROLLER_SPRITE = new ResourceLocation(TechPlusPlus.MOD_ID + ":container/clay_molder/scroller");
   private static final ResourceLocation SCROLLER_DISABLED_SPRITE = new ResourceLocation(TechPlusPlus.MOD_ID + ":container/clay_molder/scroller_disabled");
   private static final ResourceLocation RECIPE_SELECTED_SPRITE = new ResourceLocation(TechPlusPlus.MOD_ID + ":container/clay_molder/recipe_selected");
   private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = new ResourceLocation(TechPlusPlus.MOD_ID + ":container/clay_molder/recipe_highlighted");
   private static final ResourceLocation RECIPE_SPRITE = new ResourceLocation(TechPlusPlus.MOD_ID + ":container/clay_molder/recipe");
   private static final ResourceLocation BG_LOCATION = new ResourceLocation(TechPlusPlus.MOD_ID + ":textures/gui/container/clay_molder.png");
   private static final int SCROLLER_WIDTH = 12;
   private static final int SCROLLER_HEIGHT = 15;
   private static final int SCROLLER_X = 119;
   private static final int SCROLLER_LENGTH = 41;
   private static final int RECIPES_COLUMNS = 4;
   private static final int RECIPES_ROWS = 3;
   private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
   private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
   private static final int SCROLLER_FULL_HEIGHT = 54;
   private static final int RECIPES_X = 52;
   private static final int RECIPES_Y = 14;
   private float scrollOffs;
   private boolean scrolling;
   private int startIndex;
   private boolean displayRecipes;

   public ClayMolderScreen(ClayMolderMenu pMenu, Inventory pInventory, Component pComp) {
      super(pMenu, pInventory, pComp);
      pMenu.registerUpdateListener(this::containerChanged);
      --this.titleLabelY;
   }

   public void render(@NotNull GuiGraphics pGui, int mouseX, int mouseY, float partialTick) {
      super.render(pGui, mouseX, mouseY, partialTick);
      this.renderTooltip(pGui, mouseX, mouseY);
   }

   protected void renderBg(GuiGraphics pGui, float p_282453_, int p_282940_, int p_282328_) {
      pGui.blit(BG_LOCATION, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
      int k = (int)(((float) SCROLLER_LENGTH) * this.scrollOffs);
      ResourceLocation scrollerSprite = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
      pGui.blitSprite(scrollerSprite, this.leftPos + SCROLLER_X, this.topPos + SCROLLER_HEIGHT + k, SCROLLER_WIDTH, SCROLLER_HEIGHT);
      int l = this.leftPos + RECIPES_X;
      int i1 = this.topPos + RECIPES_Y;
      int j1 = this.startIndex + SCROLLER_WIDTH;
      this.renderButtons(pGui, p_282940_, p_282328_, l, i1, j1);
      this.renderRecipes(pGui, l, i1, j1);
   }

   protected void renderTooltip(@NotNull GuiGraphics pGui, int mouseX, int mouseY) {
      super.renderTooltip(pGui, mouseX, mouseY);
      if (this.displayRecipes) {
         int i = this.leftPos + RECIPES_X;
         int j = this.topPos + RECIPES_Y;
         int k = this.startIndex + SCROLLER_WIDTH;
         List<RecipeHolder<ClayMolderRecipe>> list = this.menu.getRecipes();

         for(int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); ++l) {
            int i1 = l - this.startIndex;
            int j1 = i + i1 % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int k1 = j + i1 / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            if (mouseX >= j1 && mouseX < j1 + RECIPES_IMAGE_SIZE_WIDTH && mouseY >= k1 && mouseY < k1 + RECIPES_IMAGE_SIZE_HEIGHT) {
                assert this.minecraft != null;
                assert this.minecraft.level != null;
                pGui.renderTooltip(this.font, list.get(l).value().getResultItem(this.minecraft.level.registryAccess()),
                       mouseX, mouseY);
            }
         }
      }
   }

   private void renderButtons(GuiGraphics pGui, int p_282136_, int p_282147_, int p_281987_, int p_281276_, int p_282688_) {
      for(int i = this.startIndex; i < p_282688_ && i < this.menu.getNumRecipes(); ++i) {
         int j = i - this.startIndex;
         int k = p_281987_ + j % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
         int l = j / RECIPES_COLUMNS;
         int i1 = p_281276_ + l * RECIPES_IMAGE_SIZE_HEIGHT + 2;
         ResourceLocation resourcelocation;
         if (i == this.menu.getSelectedRecipeIndex()) {
            resourcelocation = RECIPE_SELECTED_SPRITE;
         } else if (p_282136_ >= k && p_282147_ >= i1 && p_282136_ < k + RECIPES_IMAGE_SIZE_WIDTH && p_282147_ < i1 + RECIPES_IMAGE_SIZE_HEIGHT) {
            resourcelocation = RECIPE_HIGHLIGHTED_SPRITE;
         } else {
            resourcelocation = RECIPE_SPRITE;
         }
         pGui.blitSprite(resourcelocation, k, i1 - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
      }
   }

   private void renderRecipes(GuiGraphics p_281999_, int p_282658_, int p_282563_, int p_283352_) {
      List<RecipeHolder<ClayMolderRecipe>> list = this.menu.getRecipes();

      for(int i = this.startIndex; i < p_283352_ && i < this.menu.getNumRecipes(); ++i) {
         int j = i - this.startIndex;
         int k = p_282658_ + j % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
         int l = j / RECIPES_COLUMNS;
         int i1 = p_282563_ + l * RECIPES_IMAGE_SIZE_HEIGHT + 2;
          assert this.minecraft != null;
          assert this.minecraft.level != null;
          p_281999_.renderItem(list.get(i).value().getResultItem(this.minecraft.level.registryAccess()), k, i1);
      }
   }

   public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
      this.scrolling = false;
      if (this.displayRecipes) {
         int i = this.leftPos + RECIPES_X;
         int j = this.topPos + RECIPES_Y;
         int k = this.startIndex + SCROLLER_WIDTH;

         for(int l = this.startIndex; l < k; ++l) {
            int i1 = l - this.startIndex;
            double d0 = mouseX - (double)(i + i1 % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH);
            double d1 = mouseY - (double)(j + i1 / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT);
            if (d0 >= 0.0D && d1 >= 0.0D && d0 < (double) RECIPES_IMAGE_SIZE_WIDTH && d1 < (double) RECIPES_IMAGE_SIZE_HEIGHT) {
                assert this.minecraft != null;
                assert this.minecraft.player != null;
                if (this.menu.clickMenuButton(this.minecraft.player, l)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    assert this.minecraft.gameMode != null;
                    this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
                    return true;
                }
            }
         }

         i = this.leftPos + 119;
         j = this.topPos + 9;
         if (mouseX >= (double)i && mouseX < (double)(i + SCROLLER_WIDTH) && mouseY >= (double)j && mouseY < (double)(j + SCROLLER_FULL_HEIGHT)) {
            this.scrolling = true;
         }
      }
      return super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public boolean mouseDragged(double p_99322_, double p_99323_, int p_99324_, double p_99325_, double p_99326_) {
      if (this.scrolling && this.isScrollBarActive()) {
         int i = this.topPos + RECIPES_Y;
         int j = i + SCROLLER_FULL_HEIGHT;
         this.scrollOffs = ((float)p_99323_ - (float)i - (((float) SCROLLER_HEIGHT)/2.0F)) / ((float)(j - i) - (float) SCROLLER_HEIGHT);
         this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
         this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5D) * RECIPES_COLUMNS;
         return true;
      } else {
         return super.mouseDragged(p_99322_, p_99323_, p_99324_, p_99325_, p_99326_);
      }
   }

   public boolean mouseScrolled(double p_99314_, double p_99315_, double p_99316_, double p_297300_) {
      if (this.isScrollBarActive()) {
         int i = this.getOffscreenRows();
         float f = (float)p_297300_ / (float)i;
         this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
         this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5D) * RECIPES_COLUMNS;
      }

      return true;
   }

   private boolean isScrollBarActive() {
      return this.displayRecipes && this.menu.getNumRecipes() > (RECIPES_ROWS * RECIPES_COLUMNS);
   }

   protected int getOffscreenRows() {
      return (this.menu.getNumRecipes() + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS - RECIPES_ROWS;
   }

   private void containerChanged() {
      this.displayRecipes = this.menu.hasInputItem();
      if (!this.displayRecipes) {
         this.scrollOffs = 0.0F;
         this.startIndex = 0;
      }
   }
}