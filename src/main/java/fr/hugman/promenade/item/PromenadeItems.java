package fr.hugman.promenade.item;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.boat.PromenadeBoatTypeKeys;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class PromenadeItems {
    public static final Item SAKURA_SIGN = register(PromenadeItemKeys.SAKURA_SIGN, new SignItem(new Item.Settings().maxCount(16), PromenadeBlocks.SAKURA_SIGN, PromenadeBlocks.SAKURA_WALL_SIGN));
    public static final Item SAKURA_HANGING_SIGN = register(PromenadeItemKeys.SAKURA_HANGING_SIGN, new HangingSignItem(PromenadeBlocks.SAKURA_HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item SAKURA_BOAT = registerBoat(PromenadeItemKeys.SAKURA_BOAT, PromenadeBoatTypeKeys.SAKURA, false);
    public static final Item SAKURA_CHEST_BOAT = registerBoat(PromenadeItemKeys.SAKURA_CHEST_BOAT, PromenadeBoatTypeKeys.SAKURA, true);

    public static final Item MAPLE_SIGN = register(PromenadeItemKeys.MAPLE_SIGN, new SignItem(new Item.Settings().maxCount(16), PromenadeBlocks.MAPLE_SIGN, PromenadeBlocks.MAPLE_WALL_SIGN));
    public static final Item MAPLE_HANGING_SIGN = register(PromenadeItemKeys.MAPLE_HANGING_SIGN, new HangingSignItem(PromenadeBlocks.MAPLE_HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item MAPLE_BOAT = registerBoat(PromenadeItemKeys.MAPLE_BOAT, PromenadeBoatTypeKeys.MAPLE, false);
    public static final Item MAPLE_CHEST_BOAT = registerBoat(PromenadeItemKeys.MAPLE_CHEST_BOAT, PromenadeBoatTypeKeys.MAPLE, true);
    public static final Item MAPLE_SYRUP_BOTTLE = register(PromenadeItemKeys.MAPLE_SYRUP_BOTTLE, new HoneyBottleItem(new Item.Settings()
            .maxCount(16)
            .food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
            .recipeRemainder(Items.GLASS_BOTTLE)));

    private static <O extends Item> O register(RegistryKey<Item> key, O item) {
        return Registry.register(Registries.ITEM, key, item);
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
        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.BIRCH_SIGN, SAKURA_SIGN, SAKURA_HANGING_SIGN));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.BIRCH_CHEST_BOAT, SAKURA_BOAT, SAKURA_CHEST_BOAT));

        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(MAPLE_HANGING_SIGN, MAPLE_SIGN, MAPLE_HANGING_SIGN));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(SAKURA_CHEST_BOAT, MAPLE_BOAT, MAPLE_CHEST_BOAT));
        ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.HONEY_BOTTLE, MAPLE_SYRUP_BOTTLE));
    }
}
