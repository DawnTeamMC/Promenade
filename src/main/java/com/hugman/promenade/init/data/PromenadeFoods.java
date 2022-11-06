package com.hugman.promenade.init.data;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class PromenadeFoods {
	public static final FoodComponent BLUEBERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build();
	public static final FoodComponent BANANA = new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build();
	public static final FoodComponent APRICOT = new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build();
	public static final FoodComponent MANGO = new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build();
	public static final FoodComponent DUCK = new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.3F).meat().build();
	public static final FoodComponent COOKED_DUCK = new FoodComponent.Builder().hunger(6).saturationModifier(0.6F).meat().build();
}
