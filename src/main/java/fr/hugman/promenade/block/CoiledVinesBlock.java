package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoiledVinesBlock extends AbstractFacingPlantStemBlock {
    public static final MapCodec<CoiledVinesBlock> CODEC = simpleCodec(CoiledVinesBlock::new);
    public static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(2.0, 2.0, 2.0, 14.0, 16.0, 14.0), // DOWN
            Block.box(2.0, 0.0, 2.0, 14.0, 14.0, 14.0), // UP
            Block.box(2.0, 2.0, 2.0, 14.0, 14.0, 16.0), // NORTH
            Block.box(2.0, 2.0, 0.0, 14.0, 14.0, 14.0), // SOUTH
            Block.box(2.0, 2.0, 2.0, 16.0, 14.0, 14.0), // WEST
            Block.box(0.0, 2.0, 2.0, 14.0, 14.0, 14.0) // EAST
    };

    public CoiledVinesBlock(Properties settings) {
        super(settings, SHAPES, false, 0.1);
    }

    @Override
    protected MapCodec<CoiledVinesBlock> codec() {
        return CODEC;
    }

    @Override
    protected Block getPlant() {
        return PromenadeBlocks.COILED_VINES_PLANT;
    }

    @Override
    protected int getGrowthLength(RandomSource random) {
        return NetherVines.getBlocksToGrowWhenBonemealed(random);
    }

    @Override
    protected boolean canGrowAt(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }
}
