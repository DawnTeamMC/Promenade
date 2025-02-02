package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.shape.VoxelShape;

public class CoiledVinesPlantBlock extends AbstractFacingPlantBlock {
    public static final MapCodec<CoiledVinesPlantBlock> CODEC = createCodec(CoiledVinesPlantBlock::new);
    public static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0), // DOWN
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 16.0, 14.0), // UP
            Block.createCuboidShape(2.0, 2.0, 0.0, 14.0, 14.0, 16.0), // NORTH
            Block.createCuboidShape(2.0, 2.0, 0.0, 14.0, 14.0, 16.0), // SOUTH
            Block.createCuboidShape(0.0, 2.0, 2.0, 16.0, 14.0, 14.0), // WEST
            Block.createCuboidShape(0.0, 2.0, 2.0, 16.0, 14.0, 14.0) // EAST
    };

    public CoiledVinesPlantBlock(Settings settings) {
        super(settings, SHAPES, false);
    }

    @Override
    protected MapCodec<CoiledVinesPlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected AbstractFacingPlantStemBlock getStem() {
        return (AbstractFacingPlantStemBlock) PromenadeBlocks.COILED_VINES;
    }
}
