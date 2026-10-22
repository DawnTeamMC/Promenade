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

    // Wolves eat meat by this property in 1.20.1, as there is no meat tag
    public static final FoodProperties RAW_DUCK = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();
    public static final FoodProperties COOKED_DUCK = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).meat().build();

    public static FoodProperties simple(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build();
    }
}
