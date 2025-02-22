package fr.hugman.promenade.sound;

import net.minecraft.sound.BlockSoundGroup;

public final class PromenadeBlockSounds {
    public static final BlockSoundGroup MAPLE_WOOD = BlockSoundGroup.WOOD;
    public static final BlockSoundGroup SAKURA_WOOD = BlockSoundGroup.CHERRY_WOOD;
    public static final BlockSoundGroup PALM_WOOD = BlockSoundGroup.WOOD;
    public static final BlockSoundGroup AMARANTH_WOOD = BlockSoundGroup.NETHER_WOOD;

    public static final BlockSoundGroup SNOWY_LEAVES = new BlockSoundGroup(1.0f, 1.0f, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_BREAK, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_STEP, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_PLACE, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_HIT, PromenadeSoundEvents.BLOCK_SNOWY_LEAVES_FALL);

}
