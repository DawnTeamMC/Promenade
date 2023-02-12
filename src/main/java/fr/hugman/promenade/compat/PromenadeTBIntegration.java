package fr.hugman.promenade.compat;

import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.content.SakuraContent;
import fr.hugman.promenade.registry.content.GlaglaglaContent;
import fr.hugman.promenade.registry.content.MapleContent;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
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

public class PromenadeTBIntegration implements TerraBlenderApi {
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
								MaterialRules.biome(MapleContent.CARNELIAN_TREEWAY),
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
			super(Promenade.id("overworld"), RegionType.OVERWORLD, 2);
		}

		@Override
		public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
			this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
				if(Promenade.CONFIG.biomes.carnelian_treeway_weight > 0) {
					builder.replaceBiome(BiomeKeys.PLAINS, MapleContent.CARNELIAN_TREEWAY);
				}
				if(Promenade.CONFIG.biomes.sakura_groves_weight > 0) {
					builder.replaceBiome(BiomeKeys.FOREST, SakuraContent.BLUSH_SAKURA_GROVE);
					builder.replaceBiome(BiomeKeys.BIRCH_FOREST, SakuraContent.COTTON_SAKURA_GROVE);
				}
				if(Promenade.CONFIG.biomes.glacarian_taiga > 0) {
					builder.replaceBiome(BiomeKeys.TAIGA, GlaglaglaContent.GLACARIAN_TAIGA);
					builder.replaceBiome(BiomeKeys.SNOWY_TAIGA, GlaglaglaContent.GLACARIAN_TAIGA);
					builder.replaceBiome(BiomeKeys.SNOWY_SLOPES, GlaglaglaContent.GLACARIAN_TAIGA);
					builder.replaceBiome(BiomeKeys.JAGGED_PEAKS, GlaglaglaContent.GLACARIAN_TAIGA);
					builder.replaceBiome(BiomeKeys.GROVE, GlaglaglaContent.GLACARIAN_TAIGA);
				}
			});
		}
	}
}