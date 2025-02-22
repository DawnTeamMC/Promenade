package fr.hugman.promenade.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Predicate;

public class FungusBlock extends net.minecraft.block.FungusBlock {
    private final Predicate<BlockState> canPlantOn;
    private final Predicate<BlockState> canGrowOn;

    public FungusBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn, Settings settings) {
        this(featureKey, s -> s.isIn(canPlantOn), s -> s.isIn(canGrowOn), settings);
    }

    public FungusBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Predicate<BlockState> canPlantOn, Predicate<BlockState> canGrowOn, Settings settings) {
        super(featureKey, null, settings);
        this.canPlantOn = canPlantOn;
        this.canGrowOn = canGrowOn;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return this.canGrowOn.test(world.getBlockState(pos.down()));
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return this.canPlantOn.test(floor);
    }
}