package fr.hugman.promenade.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;

//TODO: codec
public class RootsBlock extends net.minecraft.block.RootsBlock {
    private final Predicate<BlockState> canPlantOn;

    public RootsBlock(Predicate<BlockState> canPlantOn, Settings settings) {
        super(settings);
        this.canPlantOn = canPlantOn;
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return this.canPlantOn.test(floor);
    }
}