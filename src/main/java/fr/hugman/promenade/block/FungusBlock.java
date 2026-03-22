package fr.hugman.promenade.block;

import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class FungusBlock extends net.minecraft.world.level.block.FungusBlock {
    private final Predicate<BlockState> canPlantOn;
    private final Predicate<BlockState> canGrowOn;

    public FungusBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn, Properties settings) {
        this(featureKey, s -> s.is(canPlantOn), s -> s.is(canGrowOn), settings);
    }

    public FungusBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, Predicate<BlockState> canPlantOn, Predicate<BlockState> canGrowOn, Properties settings) {
        super(featureKey, null, settings);
        this.canPlantOn = canPlantOn;
        this.canGrowOn = canGrowOn;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return this.canGrowOn.test(world.getBlockState(pos.below()));
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return this.canPlantOn.test(floor);
    }
}