package fr.hugman.promenade.world.gen.stateprovider;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.block.MapleLogBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.block.StrippedMapleLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

/**
 * Strips a maple log into a stripped maple log, which has a 10% chance of dripping syrup if the log was natural.
 */
public record MapleSyrupStateProvider() implements BlockStateProvider {
    public static final MapCodec<MapleSyrupStateProvider> CODEC = MapCodec.unit(MapleSyrupStateProvider::new);

    @Override
    public MapCodec<MapleSyrupStateProvider> codec() {
        return CODEC;
    }

    @Override
    public BlockState getState(LevelAccessor level, RandomSource random, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        boolean natural = state.hasProperty(MapleLogBlock.NATURAL) && state.getValue(MapleLogBlock.NATURAL);
        return PromenadeBlocks.STRIPPED_MAPLE_LOG.defaultBlockState()
                .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS))
                .setValue(StrippedMapleLogBlock.DRIP, natural && random.nextFloat() < 0.1F);
    }
}
