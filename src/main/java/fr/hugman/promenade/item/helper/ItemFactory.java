/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
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
		return s -> new SpawnEggItem(s.spawnEgg(entityType));
	}

	public static Function<Item.Settings, BoatItem> boat(EntityType<? extends AbstractBoatEntity> boatEntity) {
		return s -> new BoatItem(boatEntity, s);
	}
}
