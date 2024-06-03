package net.deddybones.techplusplus.item.custom;

import net.deddybones.techplusplus.entity.custom.ThrownWoodenSpear;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpearItem extends TridentItem implements ProjectileItem {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final double BASE_DAMAGE = 8.0D;
    public static final double BASE_SPEED = -2.9D;

    public SpearItem(Item.Properties p_43381_) {
        super(p_43381_);
    }

    public static @NotNull ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
            .add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", BASE_DAMAGE, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
            )
            .add(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", BASE_SPEED, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
            ).build();
    }

    public void releaseUsing(ItemStack pItemStack, Level pLevel, LivingEntity pEntity, int p_43397_) {
        if (pEntity instanceof Player player) {
            int i = this.getUseDuration(pItemStack) - p_43397_;
            if (i >= THROW_THRESHOLD_TIME) {
                if (!pLevel.isClientSide) {
                    pItemStack.hurtAndBreak(1, player, player.getMainHandItem().getEquipmentSlot());
                    ThrownWoodenSpear thrownSpear = new ThrownWoodenSpear(pLevel, player, pItemStack);
                    thrownSpear.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                    if (!player.hasInfiniteMaterials()) {
                        thrownSpear.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    pLevel.addFreshEntity(thrownSpear);
                    pLevel.playSound(null, thrownSpear, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.hasInfiniteMaterials()) {
                        player.getInventory().removeItem(pItemStack);
                    }
                }
            }
        }
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public @NotNull Projectile asProjectile(@NotNull Level pLevel, Position pPosition, ItemStack pStack, @Nullable Direction pDirection) {
        ThrownWoodenSpear thrownWoodenSpear = new ThrownWoodenSpear(pLevel, pPosition.x(), pPosition.y(), pPosition.z(), pStack.copyWithCount(1));
        thrownWoodenSpear.pickup = AbstractArrow.Pickup.ALLOWED;
        return thrownWoodenSpear;
    }

}