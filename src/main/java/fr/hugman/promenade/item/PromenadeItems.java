package fr.hugman.promenade.item;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.boat.PromenadeBoatTypeKeys;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class PromenadeItems {
    public static final Item SAKURA_SIGN = of(PromenadeItemKeys.SAKURA_SIGN, new SignItem(new Item.Settings().maxCount(16), PromenadeBlocks.SAKURA_SIGN, PromenadeBlocks.SAKURA_WALL_SIGN));
    public static final Item SAKURA_HANGING_SIGN = of(PromenadeItemKeys.SAKURA_HANGING_SIGN, new HangingSignItem(PromenadeBlocks.SAKURA_HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item SAKURA_BOAT = ofBoat(PromenadeItemKeys.SAKURA_BOAT, PromenadeBoatTypeKeys.SAKURA, false);
    public static final Item SAKURA_CHEST_BOAT = ofBoat(PromenadeItemKeys.SAKURA_CHEST_BOAT, PromenadeBoatTypeKeys.SAKURA, true);

    public static final Item MAPLE_SIGN = of(PromenadeItemKeys.MAPLE_SIGN, new SignItem(new Item.Settings().maxCount(16), PromenadeBlocks.MAPLE_SIGN, PromenadeBlocks.MAPLE_WALL_SIGN));
    public static final Item MAPLE_HANGING_SIGN = of(PromenadeItemKeys.MAPLE_HANGING_SIGN, new HangingSignItem(PromenadeBlocks.MAPLE_HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item MAPLE_BOAT = ofBoat(PromenadeItemKeys.MAPLE_BOAT, PromenadeBoatTypeKeys.MAPLE, false);
    public static final Item MAPLE_CHEST_BOAT = ofBoat(PromenadeItemKeys.MAPLE_CHEST_BOAT, PromenadeBoatTypeKeys.MAPLE, true);
    public static final Item MAPLE_SYRUP_BOTTLE = of(PromenadeItemKeys.MAPLE_SYRUP_BOTTLE, new HoneyBottleItem(new Item.Settings()
            .maxCount(16)
            .food(new FoodComponent.Builder().hunger(6).saturationModifier(0.1F).build())
            .recipeRemainder(Items.GLASS_BOTTLE)));

    public static final Item PALM_SIGN = of(PromenadeItemKeys.PALM_SIGN, new SignItem(new Item.Settings().maxCount(16), PromenadeBlocks.PALM_SIGN, PromenadeBlocks.PALM_WALL_SIGN));
    public static final Item PALM_HANGING_SIGN = of(PromenadeItemKeys.PALM_HANGING_SIGN, new HangingSignItem(PromenadeBlocks.PALM_HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item PALM_BOAT = ofBoat(PromenadeItemKeys.PALM_BOAT, PromenadeBoatTypeKeys.PALM, false);
    public static final Item PALM_CHEST_BOAT = ofBoat(PromenadeItemKeys.PALM_CHEST_BOAT, PromenadeBoatTypeKeys.PALM, true);

    public static final Item AURORAL_CYPRESS_SIGN = of(PromenadeItemKeys.AURORAL_CYPRESS_SIGN, new SignItem(new Item.Settings().maxCount(16), PromenadeBlocks.AURORAL_CYPRESS_SIGN, PromenadeBlocks.AURORAL_CYPRESS_WALL_SIGN));
    public static final Item AURORAL_CYPRESS_HANGING_SIGN = of(PromenadeItemKeys.AURORAL_CYPRESS_HANGING_SIGN, new HangingSignItem(PromenadeBlocks.AURORAL_CYPRESS_HANGING_SIGN, PromenadeBlocks.AURORAL_CYPRESS_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item AURORAL_CYPRESS_BOAT = ofBoat(PromenadeItemKeys.AURORAL_CYPRESS_BOAT, PromenadeBoatTypeKeys.AURORAL_CYPRESS, false);
    public static final Item AURORAL_CYPRESS_CHEST_BOAT = ofBoat(PromenadeItemKeys.AURORAL_CYPRESS_CHEST_BOAT, PromenadeBoatTypeKeys.AURORAL_CYPRESS, true);

    public static final Item DARK_AMARANTH_SIGN = of(PromenadeItemKeys.DARK_AMARANTH_SIGN, new SignItem(new Item.Settings().maxCount(16), PromenadeBlocks.DARK_AMARANTH_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_SIGN));
    public static final Item DARK_AMARANTH_HANGING_SIGN = of(PromenadeItemKeys.DARK_AMARANTH_HANGING_SIGN, new HangingSignItem(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Item BLUEBERRIES = of(PromenadeItemKeys.BLUEBERRIES, new AliasedBlockItem(PromenadeBlocks.BLUEBERRY_BUSH, new Item.Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build()).compostingChance(0.30F)));

    public static final Item BANANA = of(PromenadeItemKeys.BANANA, new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build()).compostingChance(0.65F)));
    public static final Item APRICOT = of(PromenadeItemKeys.APRICOT, new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build()).compostingChance(0.65F)));
    public static final Item MANGO = of(PromenadeItemKeys.MANGO, new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build()).compostingChance(0.65F)));

    public static final Item DUCK = of(PromenadeItemKeys.DUCK, new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.3F).build())));
    public static final Item COOKED_DUCK = of(PromenadeItemKeys.COOKED_DUCK, new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(0.6F).build())));

    public static final Item CAPYBARA_SPAWN_EGG = of(PromenadeItemKeys.CAPYBARA_SPAWN_EGG, DawnFactory.spawnEgg(PromenadeEntityTypes.CAPYBARA, 0xa0704e, 0x433930)); //TODO: review colors
    public static final Item DUCK_SPAWN_EGG = of(PromenadeItemKeys.DUCK_SPAWN_EGG, DawnFactory.spawnEgg(PromenadeEntityTypes.DUCK, 10592673, 15904341));
    public static final Item LUSH_CREEPER_SPAWN_EGG = of(PromenadeItemKeys.LUSH_CREEPER_SPAWN_EGG, DawnFactory.spawnEgg(PromenadeEntityTypes.LUSH_CREEPER, 4347181, 4262661));
    public static final Item SUNKEN_SPAWN_EGG = of(PromenadeItemKeys.SUNKEN_SPAWN_EGG, DawnFactory.spawnEgg(PromenadeEntityTypes.SUNKEN, 12233882, 6191682));

    private static <O extends Item> O of(RegistryKey<Item> key, O item) {
        return Registry.register(Registries.ITEM, key, item);
    }

    private static Item ofBoat(RegistryKey<Item> key, RegistryKey<TerraformBoatType> boatKey, boolean chest) {
        return ofBoat(key, boatKey, chest, new Item.Settings().maxCount(1));
    }

    private static Item ofBoat(RegistryKey<Item> key, RegistryKey<TerraformBoatType> boatKey, boolean chest, Item.Settings settings) {
        Item item = of(key, new TerraformBoatItem(boatKey, chest, settings));
        TerraformBoatItemHelper.registerBoatDispenserBehavior(item, boatKey, chest);
        return item;
    }

    public static void appendItemGroups() {
        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.BIRCH_HANGING_SIGN, SAKURA_SIGN, SAKURA_HANGING_SIGN));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.BIRCH_CHEST_BOAT, SAKURA_BOAT, SAKURA_CHEST_BOAT));

        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(SAKURA_HANGING_SIGN, MAPLE_SIGN, MAPLE_HANGING_SIGN));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(SAKURA_CHEST_BOAT, MAPLE_BOAT, MAPLE_CHEST_BOAT));
        ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.HONEY_BOTTLE, MAPLE_SYRUP_BOTTLE));

        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.ACACIA_HANGING_SIGN, PALM_SIGN, PALM_HANGING_SIGN));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.ACACIA_CHEST_BOAT, PALM_BOAT, PALM_CHEST_BOAT));

        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.SPRUCE_HANGING_SIGN, AURORAL_CYPRESS_SIGN, AURORAL_CYPRESS_HANGING_SIGN));
        ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.SPRUCE_CHEST_BOAT, AURORAL_CYPRESS_BOAT, AURORAL_CYPRESS_CHEST_BOAT));

        ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.WARPED_HANGING_SIGN, DARK_AMARANTH_SIGN, DARK_AMARANTH_HANGING_SIGN));

        ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> {
            e.addAfter(Items.SWEET_BERRIES, BLUEBERRIES);
            e.addAfter(Items.ENCHANTED_GOLDEN_APPLE, BANANA);
            //TODO apricot and mango
        });

        ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.COOKED_CHICKEN, DUCK, COOKED_DUCK));

        ItemGroupHelper.appendSpawnEgg(CAPYBARA_SPAWN_EGG);
        ItemGroupHelper.appendSpawnEgg(DUCK_SPAWN_EGG);
        ItemGroupHelper.appendSpawnEgg(LUSH_CREEPER_SPAWN_EGG);
        ItemGroupHelper.appendSpawnEgg(SUNKEN_SPAWN_EGG);
    }
}
