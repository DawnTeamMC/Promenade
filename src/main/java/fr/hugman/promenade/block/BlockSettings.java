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
package fr.hugman.promenade.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;

public final class BlockSettings {
	public static AbstractBlock.Settings rock() {
		return AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(1.5F, 6.0f);
	}

	public static AbstractBlock.Settings rock(MapColor mapColor, BlockSoundGroup sounds) {
		return rock().mapColor(mapColor).sounds(sounds);
	}

	public static AbstractBlock.Settings planks(MapColor color, BlockSoundGroup sounds, boolean flammable) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.create()
				.mapColor(color)
				.instrument(NoteBlockInstrument.BASS)
				.strength(2.0f, 3.0f)
				.sounds(sounds);
		if (flammable)
			settings.burnable();
		return settings;
	}
}
