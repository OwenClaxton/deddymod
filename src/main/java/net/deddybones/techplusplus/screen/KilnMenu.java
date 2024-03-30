package net.deddybones.techplusplus.screen;

import net.deddybones.techplusplus.recipes.ModRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;


import static net.deddybones.techplusplus.TechPlusPlus.*;

public class KilnMenu extends AbstractFurnaceMenu {

    public KilnMenu(int pContainerId, Inventory pInventory) {
        super(ModMenuTypes.KILN.get(), ModRecipes.KILN_TYPE.get(), KILN_RECIPE_BOOK_TYPE, pContainerId, pInventory);
    }

    public KilnMenu(int pContainerId, Inventory pInventory, Container pKilnBlockEntity, ContainerData pDataAccess) {
        super(ModMenuTypes.KILN.get(), ModRecipes.KILN_TYPE.get(), KILN_RECIPE_BOOK_TYPE, pContainerId, pInventory, pKilnBlockEntity, pDataAccess);
    }

}