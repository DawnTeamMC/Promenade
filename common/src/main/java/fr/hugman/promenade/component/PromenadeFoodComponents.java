package fr.hugman.promenade.component;

import net.minecraft.world.food.FoodProperties;

public class PromenadeFoodComponents {
    public static final FoodProperties MAPLE_SYRUP_BOTTLE = alwaysEdible(6, 0.1F);

    public static final FoodProperties BLUEBERRIES = simple(2, 0.1F);

    public static final FoodProperties BANANA = simple(4, 0.3F);
    public static final FoodProperties APRICOT = simple(4, 0.3F);
    public static final FoodProperties MANGO = simple(4, 0.3F);

    public static final FoodProperties RAW_DUCK = simple(2, 0.3F);
    public static final FoodProperties COOKED_DUCK = simple(6, 0.6F);

    public static FoodProperties simple(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build();
    }

    public static FoodProperties alwaysEdible(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build();
    }
}
