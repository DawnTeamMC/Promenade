package fr.hugman.promenade.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;

public final class ItemSettings {
    public static Item.Properties max1() {
        return new Item.Properties().stacksTo(1);
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
