package fr.hugman.promenade.block;

import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

//TODO: codec
public class RootsBlock extends net.minecraft.world.level.block.RootsBlock {
    private final Predicate<BlockState> canPlantOn;

    public RootsBlock(Predicate<BlockState> canPlantOn, Properties settings) {
        super(settings);
        this.canPlantOn = canPlantOn;
    }

    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return this.canPlantOn.test(floor);
    }
}