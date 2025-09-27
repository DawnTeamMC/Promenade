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
package fr.hugman.promenade.block.property;

import fr.hugman.promenade.block.MoaiType;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class PromenadeBlockProperties {
	public static final BooleanProperty NATURAL = BooleanProperty.of("natural");
	public static final BooleanProperty DRIP = BooleanProperty.of("drip");
	public static final EnumProperty<MoaiType> MOAI_TYPE = EnumProperty.of("type", MoaiType.class);
	public static final IntProperty DISTANCE_1_14 = IntProperty.of("distance", 1, 14);
}
