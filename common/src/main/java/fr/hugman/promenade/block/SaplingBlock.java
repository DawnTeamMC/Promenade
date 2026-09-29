package fr.hugman.promenade.block;

import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

//TODO: codec
public class SaplingBlock extends net.minecraft.world.level.block.SaplingBlock {
    private final Predicate<BlockState> predicate;

    public SaplingBlock(TreeGrower saplingGenerator, Predicate<BlockState> predicate, Properties settings) {
        super(saplingGenerator, settings);
        this.predicate = predicate;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        if (predicate != null) return predicate.test(floor);
        return super.mayPlaceOn(floor, world, pos);
    }
}