package net.deddybones.techplusplus.item.util.unused;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.deddybones.techplusplus.item.custom.ModArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.Ingredient;

import static net.deddybones.techplusplus.TechPlusPlus.MOD_ID;

public record ModArmorMaterial(Map<ModArmorItem.Type, Integer> defense, int enchantmentValue,
                               Holder<SoundEvent> equipSound, Supplier<Ingredient> repairIngredient,
                               List<ModArmorMaterial.Layer> layers, float toughness,
                               float knockbackResistance) implements IArmorMaterial {

    public static final Codec<ModArmorMaterial> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(ModArmorItem.Type.CODEC, Codec.INT).fieldOf("defense")
                    .forGetter(ModArmorMaterial::defense),
            Codec.INT.fieldOf("enchantmentValue")
                    .forGetter(ModArmorMaterial::enchantmentValue),
            SoundEvent.CODEC.fieldOf("equipSound")
                    .forGetter(ModArmorMaterial::equipSound),
            Ingredient.CODEC.fieldOf("repairIngredient")
                    .xmap(ingredient -> ((Supplier<Ingredient>) () -> ingredient), Supplier::get)
                    .forGetter(ModArmorMaterial::repairIngredient),
            Layer.CODEC.listOf().fieldOf("layers")
                    .forGetter(ModArmorMaterial::layers),
            Codec.FLOAT.fieldOf("toughness")
                    .forGetter(ModArmorMaterial::toughness),
            Codec.FLOAT.fieldOf("knockbackResistance")
                    .forGetter(ModArmorMaterial::knockbackResistance)
    ).apply(instance, ModArmorMaterial::new));

    public int getDefense(ModArmorItem.Type pType) {
        return this.defense.getOrDefault(pType, 0);
    }

    public Map<ModArmorItem.Type, Integer> defense() {
        return this.defense;
    }

    public int enchantmentValue() {
        return this.enchantmentValue;
    }

    public Holder<SoundEvent> equipSound() {
        return this.equipSound;
    }

    public Supplier<Ingredient> repairIngredient() {
        return this.repairIngredient;
    }

    public List<ModArmorMaterial.Layer> layers() {
        return this.layers;
    }

    public float toughness() {
        return this.toughness;
    }

    public float knockbackResistance() {
        return this.knockbackResistance;
    }

    public static final class Layer {
        private final ResourceLocation assetName;
        private final String suffix;
        private final boolean dyeable;
        private final ResourceLocation innerTexture;
        private final ResourceLocation outerTexture;

        public static final Codec<Layer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ResourceLocation.CODEC.fieldOf("assetName").forGetter((Layer i) -> i.assetName),
                Codec.STRING.fieldOf("suffix").forGetter(Layer::getSuffix),
                Codec.BOOL.fieldOf("dyeable").forGetter(Layer::dyeable)
        ).apply(instance, Layer::new));

        public Layer(ResourceLocation pAssetName, String pSuffix, boolean pDyeable) {
            this.assetName = pAssetName;
            this.suffix = pSuffix;
            this.dyeable = pDyeable;
            this.innerTexture = this.resolveTexture(true);
            this.outerTexture = this.resolveTexture(false);
        }

        public Layer(ResourceLocation pAssetName) {
            this(pAssetName, "", false);
        }

        private ResourceLocation resolveTexture(boolean pIsInner) {
            return this.assetName.withPath((p_330916_) -> MOD_ID + ":textures/models/armor/"
                    + this.assetName.getPath() + "_layer_"
                    + (pIsInner ? 2 : 1) + this.suffix + ".png");
        }

        public ResourceLocation texture(boolean pWantInner) {
            return pWantInner ? this.innerTexture : this.outerTexture;
        }

        public boolean dyeable() {
            return this.dyeable;
        }

        public String getSuffix() {
            return this.suffix;
        }
    }
}