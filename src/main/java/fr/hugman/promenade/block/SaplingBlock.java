package fr.hugman.promenade.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;

//TODO: codec
public class SaplingBlock extends net.minecraft.block.SaplingBlock {
    private final Predicate<BlockState> predicate;

    public SaplingBlock(SaplingGenerator saplingGenerator, Predicate<BlockState> predicate, Settings settings) {
        super(saplingGenerator, settings);
        this.predicate = predicate;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        if (predicate != null) return predicate.test(floor);
        return super.canPlantOnTop(floor, world, pos);
    }
}