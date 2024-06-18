package net.deddybones.techplusplus.recipes.inputs;

import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SmelteryInput implements RecipeInput {
    private final List<ItemStack> materials;
    private final ItemStack mold;
    private final StackedContents stackedContents = new StackedContents();
    private final int materialCount;

    public SmelteryInput(List<ItemStack> pMaterials, ItemStack pMold) {
        this.materials = pMaterials;
        this.mold = pMold;
        int pMaterialCount = 0;
        for (ItemStack itemStack : pMaterials) {
            if (!itemStack.isEmpty()) {
                pMaterialCount++;
                this.stackedContents.accountStack(itemStack, 1);
            }
        }
        this.materialCount = pMaterialCount;
    }

    @Override
    public @NotNull ItemStack getItem(int pSlotIndex) {
        if (pSlotIndex == this.materials.size()) {
            return this.mold;
        }
        return this.materials.get(pSlotIndex);
    }

    @Override
    public int size() {
        return this.materials.size() + 1;
    }

    public List<ItemStack> getMaterials() {
        return this.materials;
    }

    public ItemStack getMold() {
        return this.mold;
    }

    @SuppressWarnings("unused")
    public StackedContents getStackedContents() {
        return this.stackedContents;
    }

    @Override
    public boolean equals(Object pObject) {
        if (pObject == this) {
            return true;
        } else {
            return pObject instanceof SmelteryInput smelteryInput
                && this.materialCount == smelteryInput.materialCount
                && ItemStack.matches(this.mold, smelteryInput.mold)
                && ItemStack.listMatches(this.materials, smelteryInput.materials);
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.materialCount == 0) && this.mold.isEmpty();
    }
}
