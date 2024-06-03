package net.deddybones.techplusplus.gui.menu;

import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.recipes.CrusherRecipe;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.deddybones.techplusplus.gui.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;

public class CrusherMenu extends ModAbstractOneInputOneOutputContainerMenu<CrusherRecipe, CrusherMenu> {

   public CrusherMenu(int pContainerId, Inventory pInventory) {
      this(pContainerId, pInventory, ContainerLevelAccess.NULL);
   }

   public CrusherMenu(int pContainerId, Inventory pInventory, final ContainerLevelAccess pAccess) {
      super(ModBlocks.CRUSHER.get(), ModRecipes.CRUSHING_TYPE.get(), ModMenuTypes.CRUSHER.get(),
              pContainerId, pInventory, pAccess, false);
   }
}