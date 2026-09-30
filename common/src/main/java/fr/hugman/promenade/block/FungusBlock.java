package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class FungusBlock extends net.minecraft.world.level.block.FungusBlock {
    public static final MapCodec<FungusBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.CONFIGURED_FEATURE).fieldOf("feature").forGetter(block -> block.featureKey),
            TagKey.codec(Registries.BLOCK).fieldOf("placeable_on").forGetter(block -> block.canPlantOn),
            TagKey.codec(Registries.BLOCK).fieldOf("growable_on").forGetter(block -> block.canGrowOn),
            propertiesCodec()
    ).apply(instance, FungusBlock::new));

    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;
    private final TagKey<Block> canPlantOn;
    private final TagKey<Block> canGrowOn;

    public FungusBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn, Properties settings) {
        super(featureKey, Blocks.AIR, settings);
        this.featureKey = featureKey;
        this.canPlantOn = canPlantOn;
        this.canGrowOn = canGrowOn;
    }

    // Vanilla's codec() returns its exact class
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public MapCodec<net.minecraft.world.level.block.FungusBlock> codec() {
        return (MapCodec) CODEC;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.below()).is(this.canGrowOn);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(this.canPlantOn);
    }
}
