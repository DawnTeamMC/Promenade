package fr.hugman.promenade.item.helper;

import fr.hugman.promenade.item.PromenadeBoatItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ItemFactory {
    /**
     * An item that places a block, but is named after itself rather than after the block.
     */
    public static Function<Item.Properties, Item> uniqueNameBlock(Block block) {
        return settings -> new ItemNameBlockItem(block, settings);
    }

    public static BiFunction<Block, Item.Properties, SignItem> sign(Block wallSignBlock) {
        return (block, settings) -> new SignItem(settings, block, wallSignBlock);
    }

    public static BiFunction<Block, Item.Properties, HangingSignItem> hangingSign(Block wallSignBlock) {
        return (block, settings) -> new HangingSignItem(block, wallSignBlock, settings);
    }

    public static Function<Item.Properties, SpawnEggItem> spawnEgg(EntityType<? extends Mob> entityType, int backgroundColor, int highlightColor) {
        return s -> new SpawnEggItem(entityType, backgroundColor, highlightColor, s);
    }

    public static Function<Item.Properties, PromenadeBoatItem> boat(Supplier<? extends EntityType<? extends Boat>> boatEntity, boolean hasChest) {
        return s -> new PromenadeBoatItem(boatEntity, hasChest, s);
    }
}
