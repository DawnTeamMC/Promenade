package com.hugman.promenade.compat;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.AutumnBundle;
import com.hugman.promenade.init.CherryBundle;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import terrablender.api.BiomeProvider;
import terrablender.api.BiomeProviders;
import terrablender.api.TerraBlenderApi;
import terrablender.worldgen.TBClimate;

import java.util.function.Consumer;

public class PromenadeTerraBlenderIntegration implements TerraBlenderApi {
	@Override
	public void onTerraBlenderInitialized() {
		BiomeProviders.register(new PBiomeProvider());
	}

	public static class PBiomeProvider extends BiomeProvider {
		public PBiomeProvider() {
			super(Promenade.MOD_DATA.id("biome_provider"), 2);
		}

		@Override
		public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, RegistryKey<Biome>>> mapper) {
			this.addModifiedVanillaOverworldBiomes(mapper, builder ->{
				builder.replaceBiome(BiomeKeys.PLAINS,		 AutumnBundle.Biomes.PUMPKIN_PASTURES		.getRegistryKey());
				builder.replaceBiome(BiomeKeys.FOREST,		 CherryBundle.Biomes.PINK_CHERRY_OAK_FOREST	.getRegistryKey());
				builder.replaceBiome(BiomeKeys.BIRCH_FOREST, CherryBundle.Biomes.WHITE_CHERRY_OAK_FOREST.getRegistryKey());
			});
		}
	}
}