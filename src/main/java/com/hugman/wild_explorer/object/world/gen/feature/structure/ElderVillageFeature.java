package com.hugman.wild_explorer.object.world.gen.feature.structure;

import com.hugman.wild_explorer.object.world.gen.feature.structure.generator.ElderVillageGenerator;
import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

public class ElderVillageFeature extends StructureFeature<DefaultFeatureConfig> {
	public ElderVillageFeature(Codec<DefaultFeatureConfig> configCodec) {
		super(configCodec);
	}

	private static int getGenerationHeight(int chunkX, int chunkZ, ChunkGenerator chunkGenerator) {
		Random random = new Random(chunkX + chunkZ * 10387313);
		BlockRotation blockRotation = BlockRotation.random(random);
		int i = 5;
		int j = 5;
		if(blockRotation == BlockRotation.CLOCKWISE_90) {
			i = -5;
		}
		else if(blockRotation == BlockRotation.CLOCKWISE_180) {
			i = -5;
			j = -5;
		}
		else if(blockRotation == BlockRotation.COUNTERCLOCKWISE_90) {
			j = -5;
		}

		int k = (chunkX << 4) + 7;
		int l = (chunkZ << 4) + 7;
		int m = chunkGenerator.getHeightInGround(k, l, Heightmap.Type.WORLD_SURFACE_WG);
		int n = chunkGenerator.getHeightInGround(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
		int o = chunkGenerator.getHeightInGround(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
		int p = chunkGenerator.getHeightInGround(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
		return Math.min(Math.min(m, n), Math.min(o, p));
	}

	@Override
	public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
		return Start::new;
	}

	public static class Start extends StructureStart<DefaultFeatureConfig> {
		public Start(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
			super(feature, chunkX, chunkZ, box, references, seed);
		}

		public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig config) {
			int x = chunkX * 16;
			int z = chunkZ * 16;
			int y = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
			BlockRotation rotation = BlockRotation.random(this.random);
			if(y >= 40) {
				BlockPos pos = new BlockPos(x, y, z);
				ElderVillageGenerator.addPieces(manager, pos, rotation, this.children);
				this.setBoundingBoxFromChildren();
			}
		}
	}
}
