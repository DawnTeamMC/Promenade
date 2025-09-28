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
package fr.hugman.promenade.block.type;

import net.minecraft.block.WoodType;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;

import fr.hugman.promenade.Promenade;

public class PromenadeWoodTypes {
	public static final WoodType SAKURA = WoodTypeBuilder.copyOf(WoodType.CHERRY).register(Promenade.id("sakura"), PromenadeBlockSetTypes.SAKURA);
	public static final WoodType MAPLE = WoodTypeBuilder.copyOf(WoodType.OAK).register(Promenade.id("maple"), PromenadeBlockSetTypes.MAPLE);
	public static final WoodType PALM = WoodTypeBuilder.copyOf(WoodType.OAK).register(Promenade.id("palm"), PromenadeBlockSetTypes.PALM);
	public static final WoodType DARK_AMARANTH = WoodTypeBuilder.copyOf(WoodType.CRIMSON).register(Promenade.id("dark_amaranth"), PromenadeBlockSetTypes.DARK_AMARANTH);
}
