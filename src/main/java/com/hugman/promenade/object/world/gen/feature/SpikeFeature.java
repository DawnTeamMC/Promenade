package com.hugman.promenade.object.world.gen.feature;

import com.hugman.promenade.object.world.gen.feature.config.SpikeFeatureConfig;
import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.filler.RandomSimpleFiller;
import com.terraformersmc.terraform.shapes.impl.filler.SimpleFiller;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.RotateLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terraform.shapes.impl.validator.AirValidator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SpikeFeature extends Feature<SpikeFeatureConfig> {
	public SpikeFeature(Codec<SpikeFeatureConfig> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, SpikeFeatureConfig config) {
		int amount = random.nextInt(3) + 2;
		Shape shape = Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0));
		if(!config.baseBlocks.contains(world.getBlockState(pos.down()).getBlock().getDefaultState())) return false;
		for(int i = 0; i < amount; i++) {
			int height = random.nextInt(config.randomHeight) + config.baseHeight;
			float radius = random.nextFloat() * config.randomRadius + config.baseRadius;
			float ytheta = (random.nextFloat() * config.randomYtheta) + config.baseYtheta;
			float ztheta = (random.nextFloat() * config.randomZtheta) + config.baseZtheta;

			shape = shape.applyLayer(new AddLayer(Shapes
					.ellipticalPyramid(radius, radius, height)
					.applyLayer(new RotateLayer(Quaternion.of(0, ytheta, ztheta, true)))
			));
		}

		shape
				.applyLayer(new RotateLayer(Quaternion.of(0, 0, 0, 1)))
				.applyLayer(new TranslateLayer(Position.of(pos)))
				.validate(AirValidator.of((TestableWorld) world), (validShape) -> {
					validShape = validShape.applyLayer(new TranslateLayer(Position.of(0, config.yOffset, 0)));
					validShape.fill(new SimpleFiller(world, config.state));
					validShape.fill(new RandomSimpleFiller(world, config.decorState, new Random(), config.decorChance));
				});
		return true;
	}
}
