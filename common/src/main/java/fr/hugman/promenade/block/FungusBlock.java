package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class FungusBlock extends net.minecraft.world.level.block.FungusBlock {

    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;
    private final TagKey<Block> canPlantOn;
    private final TagKey<Block> canGrowOn;

    public FungusBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn, Properties settings) {
        super(settings, featureKey, Blocks.AIR);
        this.featureKey = featureKey;
        this.canPlantOn = canPlantOn;
        this.canGrowOn = canGrowOn;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.below()).is(this.canGrowOn);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(this.canPlantOn);
    }
}
