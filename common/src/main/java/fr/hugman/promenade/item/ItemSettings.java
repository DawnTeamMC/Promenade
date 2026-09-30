package fr.hugman.promenade.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * Burn times need no setting here: on 1.21.1, both loaders give them through item tags (signs, boats...).
 */
public final class ItemSettings {
    public static Item.Properties max1() {
        return new Item.Properties().stacksTo(1);
    }

    /**
     * Settings for a sign of a flammable wood type.
     */
    public static Item.Properties sign() {
        return max16();
    }

    /**
     * Settings for a hanging sign of a flammable wood type.
     */
    public static Item.Properties hangingSign() {
        return max16();
    }

    /**
     * Settings for a sign of a wood type that does not burn, such as the nether ones.
     */
    public static Item.Properties nonFlammableSign() {
        return max16();
    }

    /**
     * Settings for a boat of a flammable wood type.
     */
    public static Item.Properties boat() {
        return max1();
    }

    public static Item.Properties max16() {
        return new Item.Properties().stacksTo(16);
    }

    public static Item.Properties stackableDrink(FoodProperties foodComponent) {
        return new Item.Properties()
                .craftRemainder(Items.GLASS_BOTTLE)
                .food(foodComponent)
                .stacksTo(16);
    }
}
