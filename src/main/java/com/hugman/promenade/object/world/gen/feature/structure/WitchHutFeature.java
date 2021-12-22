package com.hugman.promenade.object.world.gen.feature.structure;

import com.hugman.promenade.object.world.gen.feature.structure.generator.WitchHutGenerator;
import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class WitchHutFeature extends StructureFeature<DefaultFeatureConfig> {
	public WitchHutFeature(Codec<DefaultFeatureConfig> configCodec) {
		super(configCodec, StructureGeneratorFactory.simple(StructureGeneratorFactory.checkForBiomeOnTop(Heightmap.Type.WORLD_SURFACE_WG), WitchHutFeature::addPieces));
	}

	private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
		BlockPos pos = new BlockPos(context.chunkPos().getStartX(), 90, context.chunkPos().getStartZ());
		WitchHutGenerator.addPieces(context.structureManager(), pos, collector, context.random());
	}
}
