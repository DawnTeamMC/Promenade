package fr.hugman.promenade.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SnowyBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FreezeTopLayerFeature extends Feature<DefaultFeatureConfig> {
	public FreezeTopLayerFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		var world = context.getWorld();
		var random = context.getRandom();
		var blockPos = context.getOrigin();
		var topMutable = new BlockPos.Mutable();
		var groundMutable = new BlockPos.Mutable();
		var undergroundMutable = new BlockPos.Mutable();

		var snowDepth = ConstantIntProvider.create(3);
		var topSnowLayers = UniformIntProvider.create(2, 3);
		var packedIceDepth = UniformIntProvider.create(2, 3);
		var normalIceDepth = UniformIntProvider.create(2, 3);

		for(int i = 0; i < 16; ++i) {
			for(int j = 0; j < 16; ++j) {
				int x = blockPos.getX() + i;
				int z = blockPos.getZ() + j;

				/* ==================== */
				/* SURFACE GROUND LEVEL */
				/* ==================== */
				topMutable.set(x, world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z), z);
				Biome biome = world.getBiome(topMutable).value();
				groundMutable.set(topMutable).move(Direction.DOWN, 1);

				// Layers of Snow
				if(biome.canSetSnow(world, topMutable)) {
					for(int n = 1; n <= snowDepth.get(random); n++) {
						if(!biome.canSetSnow(world, topMutable)) {
							break;
						}
						world.setBlockState(topMutable, Blocks.SNOW_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);
						topMutable.move(Direction.UP, 1);
					}

					BlockState blockState = world.getBlockState(groundMutable);
					if(blockState.contains(SnowyBlock.SNOWY)) {
						world.setBlockState(groundMutable, blockState.with(SnowyBlock.SNOWY, true), Block.NOTIFY_LISTENERS);
					}
				}

				// Water -> Ice
				if(biome.canSetIce(world, groundMutable, false)) {
					undergroundMutable.set(groundMutable);
					for(int n = 1; n <= packedIceDepth.get(random); n++) {
						if(!biome.canSetIce(world, undergroundMutable, false)) {
							break;
						}
						world.setBlockState(undergroundMutable, Blocks.PACKED_ICE.getDefaultState(), Block.NOTIFY_LISTENERS);
						undergroundMutable.move(Direction.DOWN, 1);
					}
					for(int n = 1; n <= normalIceDepth.get(random); n++) {
						if(!biome.canSetIce(world, undergroundMutable, false)) {
							break;
						}
						world.setBlockState(undergroundMutable, Blocks.ICE.getDefaultState(), Block.NOTIFY_LISTENERS);
						undergroundMutable.move(Direction.DOWN, 1);
					}
				}

				/* ======================= */
				/* SURFACE MOTION BLOCKING */
				/* ======================= */
				topMutable.set(x, world.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z), z);
				groundMutable.set(topMutable).move(Direction.DOWN, 1);
				biome = world.getBiome(topMutable).value(); // just in case I guess?

				// Thin Snow
				if(biome.canSetSnow(world, topMutable)) {
					world.setBlockState(topMutable, Blocks.SNOW.getDefaultState().with(SnowBlock.LAYERS, topSnowLayers.get(random)), Block.NOTIFY_LISTENERS);

					BlockState blockState = world.getBlockState(groundMutable);
					if(blockState.contains(SnowyBlock.SNOWY)) {
						world.setBlockState(groundMutable, blockState.with(SnowyBlock.SNOWY, true), Block.NOTIFY_LISTENERS);
					}
				}
			}
		}
		return true;
	}
}