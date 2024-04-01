package fr.hugman.promenade.item;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.boat.PromenadeBoatTypeKeys;
import fr.hugman.promenade.sign.PromenadeSigns;
import fr.hugman.promenade.registry.content.SakuraContent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class PromenadeItems {
    public static final Item MAPLE_SIGN = register(PromenadeItemKeys.MAPLE_SIGN, PromenadeSigns.MAPLE.signItem());
    public static final Item MAPLE_HANGING_SIGN = register(PromenadeItemKeys.MAPLE_HANGING_SIGN, PromenadeSigns.MAPLE.hangingSignItem());
    public static final Item MAPLE_BOAT = registerBoat(PromenadeItemKeys.MAPLE_BOAT, PromenadeBoatTypeKeys.MAPLE, false);
    public static final Item MAPLE_CHEST_BOAT = registerBoat(PromenadeItemKeys.MAPLE_CHEST_BOAT, PromenadeBoatTypeKeys.MAPLE, true);
    public static final Item MAPLE_SYRUP_BOTTLE = register(PromenadeItemKeys.MAPLE_SYRUP_BOTTLE, new HoneyBottleItem(new Item.Settings()
            .maxCount(16)
            .food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
            .recipeRemainder(Items.GLASS_BOTTLE)));

    private static <O extends Item> O register(RegistryKey<Item> key, O item) {
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * @deprecated Use {@link #register(RegistryKey, Item)} instead
     */
    @Deprecated
    private static <O extends Item> O register(String path, O item) {
        return Registry.register(Registries.ITEM, Promenade.id(path), item);
    }

    private static Item registerBoat(RegistryKey<Item> key, RegistryKey<TerraformBoatType> boatKey, boolean chest) {
        return registerBoat(key, boatKey, chest, new Item.Settings().maxCount(1));
    }

    private static Item registerBoat(RegistryKey<Item> key, RegistryKey<TerraformBoatType> boatKey, boolean chest, Item.Settings settings) {
        Item item = register(key, new TerraformBoatItem(boatKey, chest, settings));
        TerraformBoatItemHelper.registerBoatDispenserBehavior(item, boatKey, chest);
        return item;
    }

    public static void appendItemGroups() {
        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(SakuraContent.SAKURA_SIGNS.hangingSign(), MAPLE_SIGN, MAPLE_HANGING_SIGN));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(SakuraContent.SAKURA_BOAT_TYPE.getChestItem(), MAPLE_BOAT, MAPLE_CHEST_BOAT));
        ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.HONEY_BOTTLE, MAPLE_SYRUP_BOTTLE));
    }
}
