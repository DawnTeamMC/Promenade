package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
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

    //TODO: move this to another class
    public StarryLeavesBlock(Settings settings) {
        this(PromenadeBlockKeys.STAR_BITS, settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (world.getDimension().hasFixedTime() || world.isNight()) {
            if (random.nextInt(100) == 0) {
                var block = world.getRegistryManager().getOrThrow(RegistryKeys.BLOCK).getOptionalValue(this.starBits);
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
