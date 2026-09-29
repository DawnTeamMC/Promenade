package fr.hugman.promenade.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public final class ItemSettings {
    public static Item.Properties max1() {
        return new Item.Properties().stacksTo(1);
    }

    /**
     * Settings for a sign of a flammable wood type.
     */
    public static Item.Properties sign() {
        return max16().signText().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE);
    }

    /**
     * Settings for a hanging sign of a flammable wood type.
     */
    public static Item.Properties hangingSign() {
        return max16().signText().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS);
    }

    /**
     * Settings for a sign of a wood type that does not burn, such as the nether ones.
     */
    public static Item.Properties nonFlammableSign() {
        return max16().signText();
    }

    /**
     * Settings for a boat of a flammable wood type.
     */
    public static Item.Properties boat() {
        return max1().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS);
    }

    public static Item.Properties max16() {
        return new Item.Properties().stacksTo(16);
    }

    public static Item.Properties stackableDrink(FoodProperties foodComponent, Consumable consumableComponent) {
        return new Item.Properties()
                .craftRemainder(Items.GLASS_BOTTLE)
                .food(foodComponent, consumableComponent)
                .usingConvertsTo(Items.GLASS_BOTTLE)
                .stacksTo(16);
    }
}
