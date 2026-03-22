package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoiledVinesPlantBlock extends AbstractFacingPlantBlock {
    public static final MapCodec<CoiledVinesPlantBlock> CODEC = simpleCodec(CoiledVinesPlantBlock::new);
    public static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0), // DOWN
            Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0), // UP
            Block.box(2.0, 2.0, 0.0, 14.0, 14.0, 16.0), // NORTH
            Block.box(2.0, 2.0, 0.0, 14.0, 14.0, 16.0), // SOUTH
            Block.box(0.0, 2.0, 2.0, 16.0, 14.0, 14.0), // WEST
            Block.box(0.0, 2.0, 2.0, 16.0, 14.0, 14.0) // EAST
    };

    public CoiledVinesPlantBlock(Properties settings) {
        super(settings, SHAPES, false);
    }

    @Override
    protected MapCodec<CoiledVinesPlantBlock> codec() {
        return CODEC;
    }

    @Override
    protected AbstractFacingPlantStemBlock getStem() {
        return (AbstractFacingPlantStemBlock) PromenadeBlocks.COILED_VINES;
    }
}
