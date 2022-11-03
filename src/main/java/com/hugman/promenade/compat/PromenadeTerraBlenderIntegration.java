package com.hugman.promenade.compat;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.MapleBundle;
import com.hugman.promenade.init.CherryBundle;
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
		registerOverworldMaterialRules();
	}

	public static void registerOverworldMaterialRules() {
		SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.AFTER_BEDROCK, 1,
				MaterialRules.sequence(
						MaterialRules.condition(
								MaterialRules.biome(MapleBundle.CARNELIAN_TREEWAY),
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
			super(Promenade.MOD_DATA.id("overworld"), RegionType.OVERWORLD, 2);
		}

		@Override
		public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
			this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
				if(Promenade.CONFIG.biomes.carnelian_treeway_weight > 0) {
					builder.replaceBiome(BiomeKeys.PLAINS, MapleBundle.CARNELIAN_TREEWAY);
				}
				if(Promenade.CONFIG.biomes.cherry_oak_forests_weight > 0) {
					builder.replaceBiome(BiomeKeys.FOREST, CherryBundle.PINK_CHERRY_OAK_FOREST);
					builder.replaceBiome(BiomeKeys.BIRCH_FOREST, CherryBundle.WHITE_CHERRY_OAK_FOREST);
				}
			});
		}
	}
}