package fr.hugman.promenade.sound;

import net.minecraft.world.level.block.SoundType;

public final class PromenadeBlockSounds {
    public static final SoundType MAPLE_WOOD = SoundType.WOOD;
    public static final SoundType SAKURA_WOOD = SoundType.CHERRY_WOOD;
    public static final SoundType PALM_WOOD = SoundType.WOOD;
    public static final SoundType AMARANTH_WOOD = SoundType.NETHER_WOOD;

    public static final SoundType SNOWY_LEAVES = new SoundType(1.0f, 1.0f,
            PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_BREAK,
            PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_STEP,
            PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_PLACE,
            PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_HIT,
            PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_FALL
    );

    public static final SoundType SNOWY_AZALEA_LEAVES = new SoundType(1.0f, 1.0f,
            PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_BREAK,
            PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_STEP,
            PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_PLACE,
            PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_HIT,
            PromenadeSoundEvents.BLOCK_SNOWY_AZALEA_LEAVES_FALL
    );

    public static final SoundType SNOWY_CHERRY_LEAVES = new SoundType(1.0f, 1.0f,
            PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_BREAK,
            PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_STEP,
            PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_PLACE,
            PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_HIT,
            PromenadeSoundEvents.BLOCK_SNOWY_CHERRY_LEAVES_FALL
    );

}
