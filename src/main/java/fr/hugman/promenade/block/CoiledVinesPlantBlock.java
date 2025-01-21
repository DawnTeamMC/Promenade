package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

public class CoiledVinesPlantBlock extends AbstractFacingPlantBlock {
    public static final MapCodec<CoiledVinesPlantBlock> CODEC = createCodec(CoiledVinesPlantBlock::new);
    public static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public CoiledVinesPlantBlock(Settings settings) {
        super(settings, SHAPE, false);
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
