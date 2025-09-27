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
package fr.hugman.promenade.sound;

import net.minecraft.sound.BlockSoundGroup;

public final class PromenadeBlockSounds {
	public static final BlockSoundGroup MAPLE_WOOD = BlockSoundGroup.WOOD;
	public static final BlockSoundGroup SAKURA_WOOD = BlockSoundGroup.CHERRY_WOOD;
	public static final BlockSoundGroup PALM_WOOD = BlockSoundGroup.WOOD;
	public static final BlockSoundGroup AMARANTH_WOOD = BlockSoundGroup.NETHER_WOOD;

	public static final BlockSoundGroup SNOWY_LEAVES = new BlockSoundGroup(1.0f, 1.0f,
			PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_BREAK,
			PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_STEP,
			PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_PLACE,
			PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_HIT,
			PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_FALL
	);

	public static final BlockSoundGroup SNOWY_AZALEA_LEAVES = new BlockSoundGroup(1.0f, 1.0f,
			PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_BREAK,
			PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_STEP,
			PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_PLACE,
			PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_HIT,
			PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_FALL
	);

	public static final BlockSoundGroup SNOWY_CHERRY_LEAVES = new BlockSoundGroup(1.0f, 1.0f,
			PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_BREAK,
			PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_STEP,
			PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_PLACE,
			PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_HIT,
			PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_FALL
	);

}
