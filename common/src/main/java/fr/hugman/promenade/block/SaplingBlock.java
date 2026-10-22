package fr.hugman.promenade.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A sapling that can only be planted on the blocks of a tag.
 */
public class SaplingBlock extends net.minecraft.world.level.block.SaplingBlock {

    private final TagKey<Block> placeableOn;

    public SaplingBlock(AbstractTreeGrower saplingGenerator, TagKey<Block> placeableOn, Properties settings) {
        super(saplingGenerator, settings);
        this.placeableOn = placeableOn;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(this.placeableOn);
    }
}
