package fr.hugman.promenade.item.helper;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class ItemFactory {
    public static Function<Item.Settings, Item> uniqueNameBlock(Block block) {
        return settings -> new BlockItem(block, settings.useItemPrefixedTranslationKey());
    }

    public static BiFunction<Block, Item.Settings, SignItem> sign(Block wallSignBlock) {
        return (block, settings) -> new SignItem(block, wallSignBlock, settings);
    }

    public static BiFunction<Block, Item.Settings, HangingSignItem> hangingSign(Block wallSignBlock) {
        return (block, settings) -> new HangingSignItem(block, wallSignBlock, settings);
    }

    public static Function<Item.Settings, SpawnEggItem> spawnEgg(EntityType<? extends MobEntity> entityType) {
        return s -> new SpawnEggItem(entityType, s);
    }

    public static Function<Item.Settings, BoatItem> boat(EntityType<? extends AbstractBoatEntity> boatEntity) {
        return s -> new BoatItem(boatEntity, s);
    }
}
