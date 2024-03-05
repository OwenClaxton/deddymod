package net.deddybones.deddymod.datagen;

import net.deddybones.deddymod.DeddyMod;
import net.deddybones.deddymod.block.ModBlocks;
import net.deddybones.deddymod.item.ModItems;
import net.deddybones.deddymod.item.TweakedVanillaItems;
import net.deddybones.deddymod.util.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, DeddyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PLASTIMETAL_HELMET.get(),
                        ModItems.PLASTIMETAL_CHESTPLATE.get(),
                        ModItems.PLASTIMETAL_LEGGINGS.get(),
                        ModItems.PLASTIMETAL_BOOTS.get());

        this.tag(ItemTags.AXES)
                .add(ModItems.PLASTIMETAL_AXE.get())
                .add(TweakedVanillaItems.IRON_AXE.get());

        this.tag(ItemTags.PICKAXES)
                .add(ModItems.PLASTIMETAL_PICKAXE.get());

        this.tag(ItemTags.SWORDS)
                .add(ModItems.PLASTIMETAL_SWORD.get());

        this.tag(ItemTags.SHOVELS)
                .add(ModItems.PLASTIMETAL_SHOVEL.get());

        this.tag(ItemTags.HOES)
                .add(ModItems.PLASTIMETAL_HOE.get());

        this.tag(ModTags.Items.CAN_CARVE)
                .addTag(ItemTags.AXES)
                .add(ModItems.FLINT_KNIFE.get());

        this.tag(ModTags.Items.CARVED_ITEM)
                .add(ModItems.WOODEN_HANDLE.get())
                .add(ModItems.WOODEN_SPEAR.get());

        this.tag(ModTags.Items.CARVABLE_ITEM)
                .add(ModItems.WOODEN_HANDLE.get())
                .add(ModBlocks.TINY_LOG_BLOCK.get().asItem());

    }
}
