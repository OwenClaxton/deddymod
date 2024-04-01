package net.deddybones.techplusplus.screen;

import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.recipes.ClayMolderRecipe;
import net.deddybones.techplusplus.recipes.ModRecipes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;

public class ClayMolderMenu extends ModAbstractOneInputOneOutputContainerMenu<ClayMolderRecipe, ClayMolderMenu> {

   public ClayMolderMenu(int pContainerId, Inventory pInventory) {
      this(pContainerId, pInventory, ContainerLevelAccess.NULL);
   }

   public ClayMolderMenu(int pContainerId, Inventory pInventory, final ContainerLevelAccess pAccess) {
      super(ModBlocks.CLAY_MOLDER.get(), ModRecipes.MOLD_TYPE.get(), ModMenuTypes.CLAY_MOLDER.get(),
              pContainerId, pInventory, pAccess, true);
   }
}