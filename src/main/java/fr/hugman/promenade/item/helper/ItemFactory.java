package fr.hugman.promenade.item.helper;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class ItemFactory {
    public static Function<Item.Properties, Item> uniqueNameBlock(Block block) {
        return settings -> new BlockItem(block, settings.useItemDescriptionPrefix());
    }

    public static BiFunction<Block, Item.Properties, SignItem> sign(Block wallSignBlock) {
        return (block, settings) -> new SignItem(block, wallSignBlock, settings);
    }

    public static BiFunction<Block, Item.Properties, HangingSignItem> hangingSign(Block wallSignBlock) {
        return (block, settings) -> new HangingSignItem(block, wallSignBlock, settings);
    }

    public static Function<Item.Properties, SpawnEggItem> spawnEgg(EntityType<? extends Mob> entityType) {
        return s -> new SpawnEggItem(s.spawnEgg(entityType));
    }

    public static Function<Item.Properties, BoatItem> boat(EntityType<? extends AbstractBoat> boatEntity) {
        return s -> new BoatItem(boatEntity, s);
    }
}
