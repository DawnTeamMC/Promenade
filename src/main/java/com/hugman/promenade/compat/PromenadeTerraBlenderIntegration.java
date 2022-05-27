package com.hugman.promenade.compat;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.AutumnBundle;
import com.hugman.promenade.init.CherryBundle;
import com.hugman.promenade.init.GalleryBundle;
import com.hugman.promenade.init.TallerNetherForestBundle;
import com.hugman.promenade.util.BiomeUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;
import terrablender.worldgen.TBSurfaceRuleData;

import java.util.function.Consumer;

public class PromenadeTerraBlenderIntegration implements TerraBlenderApi {
	private static MaterialRules.MaterialRule makeStateRule(Block block) {
		return MaterialRules.block(block.getDefaultState());
	}

	@Override
	public void onTerraBlenderInitialized() {
		Regions.register(new PromenadeOverworldRegion());
		Regions.register(new PromenadeNetherRegion());
		registerOverworldMaterialRules();
	}

	public static void registerOverworldMaterialRules() {
		SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.AFTER_BEDROCK, 1,
				MaterialRules.sequence(
						MaterialRules.condition(
								MaterialRules.biome(BiomeUtil.PUMPKIN_PASTURES_KEY),
								MaterialRules.condition(
										MaterialRules.noiseThreshold(NoiseParametersKeys.CALCITE, -0.0125, 0.0125),
										makeStateRule(Blocks.COARSE_DIRT)
								)
						),
						TBSurfaceRuleData.overworld()
				)
		);
	}

	public static class PromenadeOverworldRegion extends Region {
		public PromenadeOverworldRegion() {
			super(Promenade.MOD_DATA.id("overworld_region"), RegionType.OVERWORLD, 2);
		}

		@Override
		public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
			this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
				if(Promenade.CONFIG.biomes.pumpkin_pastures_weight > 0) {
					builder.replaceBiome(BiomeKeys.PLAINS, AutumnBundle.Biomes.PUMPKIN_PASTURES.getRegistryKey());
				}
				if(Promenade.CONFIG.biomes.cherry_oak_forests_weight > 0) {
					builder.replaceBiome(BiomeKeys.FOREST, CherryBundle.Biomes.PINK_CHERRY_OAK_FOREST.getRegistryKey());
					builder.replaceBiome(BiomeKeys.BIRCH_FOREST, CherryBundle.Biomes.WHITE_CHERRY_OAK_FOREST.getRegistryKey());
				}
			});
		}
	}

	public static class PromenadeNetherRegion extends Region {
		public PromenadeNetherRegion() {
			super(Promenade.MOD_DATA.id("nether_region"), RegionType.NETHER, 5);
		}

		@Override
		public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
			if(Promenade.CONFIG.biomes.tall_nether_forests) {
				this.addBiome(mapper, MultiNoiseUtil.ParameterRange.of(0.3F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), 0.0F, TallerNetherForestBundle.Biomes.TALL_CRIMSON_FOREST.getRegistryKey());
				this.addBiome(mapper, MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.7F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), 0.35F, TallerNetherForestBundle.Biomes.TALL_WARPED_FOREST.getRegistryKey());
			}
			if(Promenade.CONFIG.biomes.nether_galleries) {
				this.addBiome(mapper, MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.2F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), 0.0F, GalleryBundle.Biomes.TRITANOPIAN_GALLERY.getRegistryKey());
				this.addBiome(mapper, MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(-0.2F), MultiNoiseUtil.ParameterRange.of(0.1F), MultiNoiseUtil.ParameterRange.of(0.0F), 0.0F, GalleryBundle.Biomes.ACHROMATOPSIAN_GALLERY.getRegistryKey());
				this.addBiome(mapper, MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(0.0F), MultiNoiseUtil.ParameterRange.of(-0.2F), MultiNoiseUtil.ParameterRange.of(0.0F), 0.0F, GalleryBundle.Biomes.PROTANOPIAN_GALLERY.getRegistryKey());
			}
		}
	}
}