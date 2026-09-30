package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RootsBlock extends net.minecraft.world.level.block.RootsBlock {
    public static final MapCodec<RootsBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            TagKey.codec(Registries.BLOCK).fieldOf("placeable_on").forGetter(block -> block.canPlantOn),
            propertiesCodec()
    ).apply(instance, RootsBlock::new));

    private final TagKey<Block> canPlantOn;

    public RootsBlock(TagKey<Block> canPlantOn, Properties settings) {
        super(settings);
        this.canPlantOn = canPlantOn;
    }

    // Vanilla's codec() returns its exact class
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public MapCodec<net.minecraft.world.level.block.RootsBlock> codec() {
        return (MapCodec) CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(this.canPlantOn);
    }
}
