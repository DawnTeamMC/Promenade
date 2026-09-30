package fr.hugman.promenade.component;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class PromenadeFoodComponents {
    public static final FoodProperties MAPLE_SYRUP_BOTTLE = simple(6, 0.1F);

    public static final FoodProperties BLUEBERRIES = simple(2, 0.1F);

    public static final FoodProperties BANANA = simple(4, 0.3F);
    public static final FoodProperties APRICOT = simple(4, 0.3F);
    public static final FoodProperties MANGO = simple(4, 0.3F);

    public static final FoodProperties RAW_DUCK = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).build();
    public static final FoodProperties COOKED_DUCK = simple(6, 0.6F);

    public static FoodProperties simple(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build();
    }
}
