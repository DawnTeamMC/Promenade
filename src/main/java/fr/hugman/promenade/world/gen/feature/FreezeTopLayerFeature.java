package fr.hugman.promenade.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FreezeTopLayerFeature extends Feature<NoneFeatureConfiguration> {
    public FreezeTopLayerFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var world = context.level();
        var random = context.random();
        var blockPos = context.origin();
        var topMutable = new BlockPos.MutableBlockPos();
        var groundMutable = new BlockPos.MutableBlockPos();
        var undergroundMutable = new BlockPos.MutableBlockPos();

        var snowDepth = ConstantInt.of(3);
        var topSnowLayers = UniformInt.of(2, 3);
        var packedIceDepth = UniformInt.of(2, 3);
        var normalIceDepth = UniformInt.of(2, 3);

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int x = blockPos.getX() + i;
                int z = blockPos.getZ() + j;

                /* ==================== */
                /* SURFACE GROUND LEVEL */
                /* ==================== */
                topMutable.set(x, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z), z);
                Biome biome = world.getBiome(topMutable).value();
                groundMutable.set(topMutable).move(Direction.DOWN, 1);

                // Layers of Snow
                if (biome.shouldSnow(world, topMutable)) {
                    for (int n = 1; n <= snowDepth.sample(random); n++) {
                        if (!biome.shouldSnow(world, topMutable)) {
                            break;
                        }
                        world.setBlock(topMutable, Blocks.SNOW_BLOCK.defaultBlockState(), Block.UPDATE_CLIENTS);
                        topMutable.move(Direction.UP, 1);
                    }

                    BlockState blockState = world.getBlockState(groundMutable);
                    if (blockState.hasProperty(SnowyDirtBlock.SNOWY)) {
                        world.setBlock(groundMutable, blockState.setValue(SnowyDirtBlock.SNOWY, true), Block.UPDATE_CLIENTS);
                    }
                }

                // Water -> Ice
                if (biome.shouldFreeze(world, groundMutable, false)) {
                    undergroundMutable.set(groundMutable);
                    for (int n = 1; n <= packedIceDepth.sample(random); n++) {
                        if (!biome.shouldFreeze(world, undergroundMutable, false)) {
                            break;
                        }
                        world.setBlock(undergroundMutable, Blocks.PACKED_ICE.defaultBlockState(), Block.UPDATE_CLIENTS);
                        undergroundMutable.move(Direction.DOWN, 1);
                    }
                    for (int n = 1; n <= normalIceDepth.sample(random); n++) {
                        if (!biome.shouldFreeze(world, undergroundMutable, false)) {
                            break;
                        }
                        world.setBlock(undergroundMutable, Blocks.ICE.defaultBlockState(), Block.UPDATE_CLIENTS);
                        undergroundMutable.move(Direction.DOWN, 1);
                    }
                }

                /* ======================= */
                /* SURFACE MOTION BLOCKING */
                /* ======================= */
                topMutable.set(x, world.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z), z);
                groundMutable.set(topMutable).move(Direction.DOWN, 1);
                biome = world.getBiome(topMutable).value(); // just in case I guess?

                // Thin Snow
                if (biome.shouldSnow(world, topMutable)) {
                    world.setBlock(topMutable, Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, topSnowLayers.sample(random)), Block.UPDATE_CLIENTS);

                    BlockState blockState = world.getBlockState(groundMutable);
                    if (blockState.hasProperty(SnowyDirtBlock.SNOWY)) {
                        world.setBlock(groundMutable, blockState.setValue(SnowyDirtBlock.SNOWY, true), Block.UPDATE_CLIENTS);
                    }
                }
            }
        }
        return true;
    }
}