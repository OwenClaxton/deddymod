package net.deddybones.techplusplus.gui.screen;

import net.deddybones.techplusplus.gui.menu.SmelteryMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import static net.deddybones.techplusplus.datagen.util.ModHelper.modLoc;

@OnlyIn(Dist.CLIENT)
public class SmelteryScreen extends AbstractContainerScreen<SmelteryMenu> implements RecipeUpdateListener {
   private static final ResourceLocation texture = modLoc("textures/gui/container/smeltery.png");
   private static final ResourceLocation burnProgressSprite = modLoc("container/furnace/burn_progress");
   private static final ResourceLocation smeltProgressSprite = modLoc("container/furnace/cook_progress");
   public final AbstractFurnaceRecipeBookComponent recipeBookComponent = new SmeltingRecipeBookComponent();
   private static final int SMELT_SPRITE_PX = 106;
   private static final int SMELT_SPRITE_PY = 30;
   private static final int BURN_SPRITE_PX = 20;
   private static final int BURN_SPRITE_PY = 37;
   private static final int RECIPE_BOOK_PX = 17;
   private static final int RECIPE_BOOK_PY = 17;
   private boolean widthTooNarrow;

   public SmelteryScreen(SmelteryMenu pMenu, Inventory pInventory, Component pComp) {
      super(pMenu, pInventory, pComp);
      --this.titleLabelY;
   }

   @Override
   public void init() {
      super.init();
      this.widthTooNarrow = this.width < 379;
      assert this.minecraft != null;
      this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
      this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
      this.addRenderableWidget(new ImageButton(this.leftPos + RECIPE_BOOK_PX, this.topPos + RECIPE_BOOK_PY, 20, 18,
         RecipeBookComponent.RECIPE_BUTTON_SPRITES,
         p_308201_ -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            p_308201_.setPosition(this.leftPos + RECIPE_BOOK_PX, this.topPos + RECIPE_BOOK_PY);
         }
      ));
      this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
   }

   @Override
   public void containerTick() {
      super.containerTick();
      this.recipeBookComponent.tick();
   }

   @Override
   public void render(@NotNull GuiGraphics pGui, int mouseX, int mouseY, float partialTick) {
      if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
         this.renderBackground(pGui, mouseX, mouseY, partialTick);
         this.recipeBookComponent.render(pGui, mouseX, mouseY, partialTick);
      } else {
         super.render(pGui, mouseX, mouseY, partialTick);
         this.recipeBookComponent.render(pGui, mouseX, mouseY, partialTick);
         this.recipeBookComponent.renderGhostRecipe(pGui, this.leftPos, this.topPos, true, partialTick);
      }
      this.renderTooltip(pGui, mouseX, mouseY);
      this.recipeBookComponent.renderTooltip(pGui, this.leftPos, this.topPos, mouseX, mouseY);
   }

   @Override
   protected void renderBg(GuiGraphics pGui, float partialTick, int mouseX, int mouseY) {
      int i = this.leftPos;
      int j = this.topPos;
      pGui.blit(texture, i, j, 0, 0, this.imageWidth, this.imageHeight);
      if (this.menu.isBurning()) {
         int l = Mth.ceil(this.menu.getBurnProgress() * 13.0F) + 1;
         pGui.blitSprite(burnProgressSprite, 14, 14, 0, 14 - l, i + BURN_SPRITE_PX, j + BURN_SPRITE_PY + 14 - l, 14, l);
      }
      int j1 = Mth.ceil(this.menu.getSmeltingProgress() * 24.0F);
      pGui.blitSprite(smeltProgressSprite, 24, 16, 0, 0, i + SMELT_SPRITE_PX, j + SMELT_SPRITE_PY, j1, 16);
   }

   @Override
   public boolean mouseClicked(double p_97834_, double p_97835_, int p_97836_) {
      if (this.recipeBookComponent.mouseClicked(p_97834_, p_97835_, p_97836_)) {
         return true;
      } else {
         return this.widthTooNarrow && this.recipeBookComponent.isVisible()
                 || super.mouseClicked(p_97834_, p_97835_, p_97836_);
      }
   }

   @Override
   protected void slotClicked(@NotNull Slot pSlot, int pMouseX, int pMouseY, @NotNull ClickType pClickType) {
      super.slotClicked(pSlot, pMouseX, pMouseY, pClickType);
      this.recipeBookComponent.slotClicked(pSlot);
   }

   @Override
   public boolean keyPressed(int p_97844_, int p_97845_, int p_97846_) {
      return this.recipeBookComponent.keyPressed(p_97844_, p_97845_, p_97846_)
              || super.keyPressed(p_97844_, p_97845_, p_97846_);
   }

   @Override
   protected boolean hasClickedOutside(double p_97838_, double p_97839_, int p_97840_,
                                       int p_97841_, int p_97842_) {
      boolean flag = p_97838_ < (double)p_97840_
              || p_97839_ < (double)p_97841_
              || p_97838_ >= (double)(p_97840_ + this.imageWidth)
              || p_97839_ >= (double)(p_97841_ + this.imageHeight);
      return this.recipeBookComponent.hasClickedOutside(p_97838_, p_97839_, this.leftPos,
              this.topPos, this.imageWidth, this.imageHeight, p_97842_) && flag;
   }

   @Override
   public boolean charTyped(char p_97831_, int p_97832_) {
      return this.recipeBookComponent.charTyped(p_97831_, p_97832_) || super.charTyped(p_97831_, p_97832_);
   }

   @Override
   public void recipesUpdated() {
      this.recipeBookComponent.recipesUpdated();
   }

   @Override
   public @NotNull RecipeBookComponent getRecipeBookComponent() {
      return this.recipeBookComponent;
   }
}