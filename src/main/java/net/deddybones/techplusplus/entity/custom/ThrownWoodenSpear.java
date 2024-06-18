package net.deddybones.techplusplus.entity.custom;

import javax.annotation.Nullable;

import net.deddybones.techplusplus.item.ModItems;
import net.deddybones.techplusplus.entity.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ThrownWoodenSpear extends AbstractArrow {
    private boolean dealtDamage;

    public ThrownWoodenSpear(EntityType<? extends ThrownWoodenSpear> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownWoodenSpear(Level pLevel, LivingEntity pEntity, ItemStack pStack) {
        super(ModEntities.THROWN_WOODEN_SPEAR_ENTITY_TYPE.get(), pEntity, pLevel, pStack, null);
    }

    public ThrownWoodenSpear(Level pLevel, double pX, double pY, double pZ, ItemStack pStack) {
        super(ModEntities.THROWN_WOODEN_SPEAR_ENTITY_TYPE.get(), pX, pY, pZ, pLevel, pStack, null);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder pSEDBuilder) {
        super.defineSynchedData(pSEDBuilder);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        super.tick();
    }

    @Nullable
    @Override
    protected EntityHitResult findHitEntity(@NotNull Vec3 p_37575_, @NotNull Vec3 p_37576_) {
        return this.dealtDamage ? null : super.findHitEntity(p_37575_, p_37576_);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37573_) {
        Entity entity = p_37573_.getEntity();
        float f = 8.0F;
        Entity entityOwner = this.getOwner();
        DamageSource damageSource = this.damageSources().trident(this, entityOwner == null ? this : entityOwner);
        if (this.level() instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, Objects.requireNonNull(this.getWeaponItem()),
                                                entity, damageSource, f);
        }
        this.dealtDamage = true;
        if (entity.hurt(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (this.level() instanceof ServerLevel serverlevel) {
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel, entity, damageSource, this.getWeaponItem());
            }

            if (entity instanceof LivingEntity livingEntity) {
                this.doKnockback(livingEntity, damageSource);
                this.doPostHurtEffects(livingEntity);
            }
        }
        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    @Override
    protected boolean tryPickup(@NotNull Player p_150196_) {
        return super.tryPickup(p_150196_) || this.isNoPhysics() && p_150196_.getInventory().add(this.getPickupItem());
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.WOODEN_SPEAR.get());
    }

    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    public void playerTouch(@NotNull Player pPlayer) {
        super.playerTouch(pPlayer);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pTag) {
        super.readAdditionalSaveData(pTag);
        this.dealtDamage = pTag.getBoolean("DealtDamage");
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pTag) {
        super.addAdditionalSaveData(pTag);
        pTag.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != AbstractArrow.Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    protected float getWaterInertia() {
        return 0.99F;
    }

    @Override
    public boolean shouldRender(double p_37588_, double p_37589_, double p_37590_) {
        return true;
    }
}
