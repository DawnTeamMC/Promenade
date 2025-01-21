package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineLogic;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

public class CoiledVinesBlock extends AbstractFacingPlantStemBlock {
    public static final MapCodec<CoiledVinesBlock> CODEC = createCodec(CoiledVinesBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);

    public CoiledVinesBlock(Settings settings) {
        super(settings, SHAPE, false, 0.1);
    }

    @Override
    protected MapCodec<CoiledVinesBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected Block getPlant() {
        return PromenadeBlocks.COILED_VINES_PLANT;
    }

    @Override
    protected int getGrowthLength(Random random) {
        return VineLogic.getGrowthLength(random);
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return VineLogic.isValidForWeepingStem(state);
    }
}
