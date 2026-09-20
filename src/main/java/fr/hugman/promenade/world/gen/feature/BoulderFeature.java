package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record BoulderFeature(
        Holder<BlockStateProvider> stateProvider,
        BlockPredicate replaceableBlocks,
        IntProvider radius
) implements Feature {
    public static final MapCodec<BoulderFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("state").forGetter(BoulderFeature::stateProvider),
            BlockPredicate.CODEC.fieldOf("replaceable").forGetter(BoulderFeature::replaceableBlocks),
            IntProviders.codec(1, 64).fieldOf("count").forGetter(BoulderFeature::radius)
    ).apply(instance, BoulderFeature::new));

    @Override
    public MapCodec<BoulderFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        int radius = this.radius.sample(random);
        BlockPos pos = origin;
        for (; pos.getY() > world.getMinY() + radius; pos = pos.below()) {
            if (!world.isEmptyBlock(pos)) {
                if (this.replaceableBlocks.test(world, pos)) {
                    break;
                }
            }
        }
        if (pos.getY() <= world.getMinY() + radius) {
            return false;
        } else {
            for (int i = 0; i < 3; ++i) {
                int j = random.nextInt(radius);
                int k = random.nextInt(radius);
                int l = random.nextInt(radius);
                float f = (float) (j + k + l) * 0.333F + 0.5F;
                for (BlockPos pos2 : BlockPos.betweenClosed(pos.offset(-j, -k, -l), pos.offset(j, k, l))) {
                    if (pos2.distSqr(pos) <= (double) (f * f)) {
                        this.setBlock(world, pos2, this.stateProvider.value().getState(world, random, pos));
                    }
                }
                pos = pos.offset(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
            }
            return true;
        }
    }
}
