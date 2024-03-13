package net.deddybones.techplusplus.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties COFFEE_FOOD = new FoodProperties.Builder()
            .alwaysEat().fast()
            .nutrition(1)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100), 0.1f).build();
}
