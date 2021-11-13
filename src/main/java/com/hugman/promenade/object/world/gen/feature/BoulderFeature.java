package com.hugman.promenade.object.world.gen.feature;

import com.hugman.promenade.object.world.gen.feature.config.BoulderFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Iterator;
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
		BlockPos pos = context.getOrigin();
		for(; pos.getY() > 3; pos = pos.down()) {
			if(!world.isAir(pos.down())) {
				if(!config.replaceableBlocks.contains(world.getBlockState(pos.down()))) {
					break;
				}
			}
		}
		if(pos.getY() <= 3) {
			return false;
		}
		else {
			for(int i = 0; i < 3; ++i) {
				int j = random.nextInt(2);
				int k = random.nextInt(8);
				int l = random.nextInt(2);
				float f = (float) (j + k + l) * 0.333F + 0.5F;
				Iterator var11 = BlockPos.iterate(pos.add(-j, -k, -l), pos.add(j, k, l)).iterator();
				while(var11.hasNext()) {
					BlockPos blockPos2 = (BlockPos) var11.next();
					if(blockPos2.getSquaredDistance(pos) <= (double) (f * f)) {
						world.setBlockState(blockPos2, config.state, 4);
					}
				}
				pos = pos.add(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
			}
			return true;
		}
	}
}
