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

import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.BlockSetType;

public class PromenadeBlockSetTypes {
	public static final BlockSetType SAKURA = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(Promenade.id("sakura"));
	public static final BlockSetType MAPLE = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Promenade.id("maple"));
	public static final BlockSetType PALM = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Promenade.id("palm"));
	public static final BlockSetType DARK_AMARANTH = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(Promenade.id("dark_amaranth"));
}
