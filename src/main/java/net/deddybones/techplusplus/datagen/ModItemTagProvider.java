package net.deddybones.techplusplus.datagen;

import net.deddybones.techplusplus.TechPlusPlus;
import net.deddybones.techplusplus.block.ModBlocks;
import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.item.TweakedVanillaItems;
import net.deddybones.techplusplus.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, TechPlusPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PLASTIMETAL_HELMET.get())
                .add(ModItems.PLASTIMETAL_CHESTPLATE.get())
                .add(ModItems.PLASTIMETAL_LEGGINGS.get())
                .add(ModItems.PLASTIMETAL_BOOTS.get())
                .add(ModItems.TIN_HELMET.get())
                .add(ModItems.TIN_CHESTPLATE.get())
                .add(ModItems.TIN_LEGGINGS.get())
                .add(ModItems.TIN_BOOTS.get())
                .add(ModItems.COPPER_HELMET.get())
                .add(ModItems.COPPER_CHESTPLATE.get())
                .add(ModItems.COPPER_LEGGINGS.get())
                .add(ModItems.COPPER_BOOTS.get())
                .add(ModItems.BRONZE_HELMET.get())
                .add(ModItems.BRONZE_CHESTPLATE.get())
                .add(ModItems.BRONZE_LEGGINGS.get())
                .add(ModItems.BRONZE_BOOTS.get());

        this.tag(ItemTags.AXES)
                .add(ModItems.PLASTIMETAL_AXE.get())
                .add(TweakedVanillaItems.IRON_AXE.get())
                .add(ModItems.TIN_AXE.get())
                .add(ModItems.COPPER_AXE.get())
                .add(ModItems.BRONZE_AXE.get());

        this.tag(ItemTags.PICKAXES)
                .add(ModItems.PLASTIMETAL_PICKAXE.get())
                .add(ModItems.TIN_PICKAXE.get())
                .add(ModItems.COPPER_PICKAXE.get())
                .add(ModItems.BRONZE_PICKAXE.get());

        this.tag(ItemTags.SWORDS)
                .add(ModItems.PLASTIMETAL_SWORD.get())
                .add(ModItems.TIN_SWORD.get())
                .add(ModItems.COPPER_SWORD.get())
                .add(ModItems.BRONZE_SWORD.get());

        this.tag(ItemTags.SHOVELS)
                .add(ModItems.PLASTIMETAL_SHOVEL.get())
                .add(ModItems.TIN_SHOVEL.get())
                .add(ModItems.COPPER_SHOVEL.get())
                .add(ModItems.BRONZE_SHOVEL.get());

        this.tag(ItemTags.HOES)
                .add(ModItems.PLASTIMETAL_HOE.get())
                .add(ModItems.TIN_HOE.get())
                .add(ModItems.COPPER_HOE.get())
                .add(ModItems.BRONZE_HOE.get());

        this.tag(ModTags.Items.CAN_CARVE)
                .addTag(ItemTags.AXES)
                .add(ModItems.FLINT_KNIFE.get());

        this.tag(ModTags.Items.CARVED_ITEM)
                .add(ModItems.WOODEN_HANDLE.get())
                .add(ModItems.WOODEN_SPEAR.get());

        this.tag(ModTags.Items.CARVABLE_ITEM)
                .add(ModItems.WOODEN_HANDLE.get())
                .add(ModBlocks.TINY_LOG_BLOCK.get().asItem());

        this.tag(ModTags.Items.BUCKETS)
                .add(Items.BUCKET)
                .add(Items.WATER_BUCKET)
                .add(Items.COD_BUCKET)
                .add(Items.SALMON_BUCKET)
                .add(Items.TROPICAL_FISH_BUCKET)
                .add(Items.PUFFERFISH_BUCKET)
                .add(Items.AXOLOTL_BUCKET)
                .add(Items.TADPOLE_BUCKET)
                .add(Items.LAVA_BUCKET)
                .add(Items.POWDER_SNOW_BUCKET)
                .add(Items.MILK_BUCKET);

        this.tag(ModTags.Items.IS_A_TOOL)
                .addTag(ModTags.Items.BUCKETS)
                .add(Items.BOW)
                .add(Items.CROSSBOW)
                .add(Items.FISHING_ROD)
                .add(Items.CARROT_ON_A_STICK)
                .add(Items.WARPED_FUNGUS_ON_A_STICK)
                .add(Items.FLINT_AND_STEEL)
                .add(Items.SPYGLASS)
                .addTag(ModTags.Items.CAN_BREAK_BLOCKS);

        this.tag(ModTags.Items.CAN_BREAK_BLOCKS)
                .addTag(ItemTags.AXES)
                .addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.HOES)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.SWORDS)
                .add(Items.TRIDENT)
                .add(Items.SHEARS)
                .add(ModItems.FLINT_KNIFE.get())
                .add(ModItems.STONE_MATTOCK.get())
                .add(ModItems.WOODEN_SPEAR.get());

    }
}
