package fr.hugman.promenade.block;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.dawn.DawnFactory;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

/**
 * Leaves block that can grow stars at night.
 *
 * @author Hugman
 */
public class StarryLeavesBlock extends LeavesBlock {
    public static final MapCodec<StarryLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RegistryKey.createCodec(RegistryKeys.BLOCK).fieldOf("star_bits").forGetter(block -> block.starBits),
            createSettingsCodec())
        .apply(instance, StarryLeavesBlock::new));

    public final RegistryKey<Block> starBits;

    public StarryLeavesBlock(RegistryKey<Block> starBits, Settings settings) {
        super(settings);
        this.starBits = starBits;
    }

    public static StarryLeavesBlock of(Settings settings) {
        return new StarryLeavesBlock(PromenadeBlockKeys.STAR_BITS, settings);
    }

    public static StarryLeavesBlock of(MapColor mapColor, BlockSoundGroup soundGroup) {
        return of(DawnFactory.leavesSettings(mapColor, soundGroup));
    }

    public static StarryLeavesBlock of(MapColor mapColor) {
        return of(mapColor, BlockSoundGroup.GRASS);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (world.isNight()) {
            if (random.nextInt(100) == 0) {
                var block = world.getRegistryManager().get(RegistryKeys.BLOCK).getOrEmpty(this.starBits);
                if (block.isEmpty()) {
                    return;
                }

                Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
                BlockPos blockPos = pos.offset(direction);
                BlockState blockState = world.getBlockState(blockPos);
                if(canGrowIn(blockState)) {
                    world.setBlockState(blockPos, block.get()
                            .getDefaultState()
                            .with(StarBitsBlock.FACING, direction)
                            .with(StarBitsBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER));
                    // TODO: play sound
                }
            }
        } else {
            //TODO: remove neighbor natural star bits
        }
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
