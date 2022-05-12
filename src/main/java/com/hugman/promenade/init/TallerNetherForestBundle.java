package com.hugman.promenade.init;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public class TallerNetherForestBundle extends PromenadeBundle
{
	public static final RegistryKey<Biome> TALL_CRIMSON_FOREST = WorldGenUtil.biomeKey("tall_crimson_forest");
	public static final RegistryKey<Biome> TALL_WARPED_FOREST = WorldGenUtil.biomeKey("tall_warped_forest");

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.tall_nether_forests) {
			NetherBiomes.addNetherBiome(TALL_CRIMSON_FOREST, MultiNoiseUtil.createNoiseHypercube(0.6F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(TALL_WARPED_FOREST, MultiNoiseUtil.createNoiseHypercube(0.0F, 0.7F, 0.0F, 0.0F, 0.0F, 0.0F, 0.35F));
		}
	}
}