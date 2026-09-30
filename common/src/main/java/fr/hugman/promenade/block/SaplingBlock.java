package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A sapling that can only be planted on the blocks of a tag.
 */
public class SaplingBlock extends net.minecraft.world.level.block.SaplingBlock {
    public static final MapCodec<SaplingBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            TreeGrower.CODEC.fieldOf("tree").forGetter(block -> block.treeGrower),
            TagKey.codec(Registries.BLOCK).fieldOf("placeable_on").forGetter(block -> block.placeableOn),
            propertiesCodec()
    ).apply(instance, SaplingBlock::new));

    private final TagKey<Block> placeableOn;

    public SaplingBlock(TreeGrower saplingGenerator, TagKey<Block> placeableOn, Properties settings) {
        super(saplingGenerator, settings);
        this.placeableOn = placeableOn;
    }

    @Override
    public MapCodec<? extends SaplingBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(this.placeableOn);
    }
}
