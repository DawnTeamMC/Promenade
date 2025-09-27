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
package fr.hugman.promenade.entity.helper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class EntityTypeFactory {
	public static EntityType.Builder<BoatEntity> boat(Supplier<Item> item) {
		EntityType.EntityFactory<BoatEntity> factory = (type, world) -> new BoatEntity(type, world, item);
		return EntityType.Builder.create(factory, SpawnGroup.MISC)
				.dropsNothing()
				.dimensions(1.375F, 0.5625F)
				.eyeHeight(0.5625F)
				.maxTrackingRange(10);
	}

	public static EntityType.Builder<ChestBoatEntity> chestBoat(Supplier<Item> item) {
		EntityType.EntityFactory<ChestBoatEntity> factory = (type, world) -> new ChestBoatEntity(type, world, item);
		return EntityType.Builder.create(factory, SpawnGroup.MISC)
				.dropsNothing()
				.dimensions(1.375F, 0.5625F)
				.eyeHeight(0.5625F)
				.maxTrackingRange(10);
	}
}
