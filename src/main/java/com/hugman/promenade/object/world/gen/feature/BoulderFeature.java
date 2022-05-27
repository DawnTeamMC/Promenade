package com.hugman.promenade.object.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class BoulderFeature extends Feature<BoulderFeatureConfig> {
	public BoulderFeature(Codec<BoulderFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(FeatureContext<BoulderFeatureConfig> context) {
		Random random = context.getRandom();
		BoulderFeatureConfig config = context.getConfig();
		StructureWorldAccess world = context.getWorld();
		int radius = config.radius.get(random);
		BlockPos pos = context.getOrigin();
		for(; pos.getY() > world.getBottomY() + radius; pos = pos.down()) {
			if(!world.isAir(pos)) {
				if(config.replaceableBlocks.contains(world.getBlockState(pos))) {
					break;
				}
			}
		}
		if(pos.getY() <= world.getBottomY() + radius) {
			return false;
		}
		else {
			for(int i = 0; i < 3; ++i) {
				int j = random.nextInt(radius);
				int k = random.nextInt(radius);
				int l = random.nextInt(radius);
				float f = (float) (j + k + l) * 0.333F + 0.5F;
				for(BlockPos pos2 : BlockPos.iterate(pos.add(-j, -k, -l), pos.add(j, k, l))) {
					if(pos2.getSquaredDistance(pos) <= (double) (f * f)) {
						world.setBlockState(pos2, config.stateProvider.getBlockState(random, pos), 4);
					}
				}
				pos = pos.add(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
			}
			return true;
		}
	}
}
