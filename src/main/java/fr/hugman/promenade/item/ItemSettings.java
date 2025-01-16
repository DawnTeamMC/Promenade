package fr.hugman.promenade.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public final class ItemSettings {
    public static Item.Settings max1() {
        return new Item.Settings().maxCount(1);
    }

    public static Item.Settings max16() {
        return new Item.Settings().maxCount(16);
    }

    public static Item.Settings stackableDrink(FoodComponent foodComponent, ConsumableComponent consumableComponent) {
        return new Item.Settings()
                .recipeRemainder(Items.GLASS_BOTTLE)
                .food(foodComponent, consumableComponent)
                .useRemainder(Items.GLASS_BOTTLE)
                .maxCount(16);
    }
}
