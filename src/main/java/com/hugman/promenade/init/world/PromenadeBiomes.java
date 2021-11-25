package com.hugman.promenade.init.world;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.PromenadeBundle;
import com.hugman.promenade.object.world.gen.biome.PromenadeBiomeCreator;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public class PromenadeBiomes extends PromenadeBundle {
	public static final BiomeCreator PUMPKIN_PASTURES = creator(new BiomeCreator("pumpkin_pastures", PromenadeBiomeCreator.createPumpkinPastures()));
	public static final BiomeCreator PINK_CHERRY_OAK_FOREST = creator(new BiomeCreator("pink_cherry_oak_forest", PromenadeBiomeCreator.createCherryOakForest(true)));
	public static final BiomeCreator WHITE_CHERRY_OAK_FOREST = creator(new BiomeCreator("white_cherry_oak_forest", PromenadeBiomeCreator.createCherryOakForest(false)));
	public static final BiomeCreator TALL_CRIMSON_FOREST = creator(new BiomeCreator("tall_crimson_forest", PromenadeBiomeCreator.createTallCrimsonForest()));
	public static final BiomeCreator TALL_WARPED_FOREST = creator(new BiomeCreator("tall_warped_forest", PromenadeBiomeCreator.createTallWarpedForest()));
	public static final BiomeCreator TRITANOPIAN_GALLERY = creator(new BiomeCreator("tritanopian_gallery", PromenadeBiomeCreator.createGallery(PromenadeBiomeCreator.composeTritanopianGalleryGenerationSettings())));
	public static final BiomeCreator ACHROMATOPSIAN_GALLERY = creator(new BiomeCreator("achromatopsian_gallery", PromenadeBiomeCreator.createGallery(PromenadeBiomeCreator.composeAchromatopsianGalleryGenerationSettings())));
	public static final BiomeCreator PROTANOPIAN_GALLERY = creator(new BiomeCreator("protanopian_gallery", PromenadeBiomeCreator.createGallery(PromenadeBiomeCreator.composeProtanopianGalleryGenerationSettings())));
	public static final BiomeCreator DARK_AMARANTH_FOREST = creator(new BiomeCreator("dark_amaranth_forest", PromenadeBiomeCreator.createDarkAmaranthForest(false)));
	public static final BiomeCreator TALL_DARK_AMARANTH_FOREST = creator(new BiomeCreator("tall_dark_amaranth_forest", PromenadeBiomeCreator.createDarkAmaranthForest(true)));
	private static final PromenadeConfig.BiomesCategory BIOMES_CONFIG = Promenade.CONFIG.biomes;
	private static final PromenadeConfig.CreaturesCategory CREATURES_CONFIG = Promenade.CONFIG.creatures;

	public static void init() {
	}

	public static void addToGen() {
		/*
		--------------------------------------
		OVERWORLD BIOMES ARE NOT SUPPORTED YET
		--------------------------------------

		if(BIOMES_CONFIG.pumpkin_pastures) OverworldBiomes.addContinentalBiome(PUMPKIN_PASTURES.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.pumpkin_pastures_weight / 10.0D);
		if(BIOMES_CONFIG.cherry_oak_forests) {
			OverworldBiomes.addContinentalBiome(PINK_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D);
			OverworldBiomes.addContinentalBiome(WHITE_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.cherry_oak_forests_weight / 10.0D);
		}

		 */
		if(BIOMES_CONFIG.tall_nether_forests) {
			NetherBiomes.addNetherBiome(TALL_CRIMSON_FOREST.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.4F, 0.0F, 0.8F, 0.0F, 0.4F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(TALL_WARPED_FOREST.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.5F, 0.8F, 0.0F, 0.0F, 0.0F, 0.375F));
		}
		if(BIOMES_CONFIG.nether_galleries) {
			NetherBiomes.addNetherBiome(TRITANOPIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(ACHROMATOPSIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.2F, 0.0F, 0.6F, 0.0F));
			NetherBiomes.addNetherBiome(PROTANOPIAN_GALLERY.getRegistryKey(), MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F, 0.0F));
		}
		if(BIOMES_CONFIG.dark_amaranth_forests) {
			TheEndBiomes.addHighlandsBiome(TALL_DARK_AMARANTH_FOREST.getRegistryKey(), BIOMES_CONFIG.dark_amaranth_forests_weight / 10.0D);
			TheEndBiomes.addMidlandsBiome(TALL_DARK_AMARANTH_FOREST.getRegistryKey(), DARK_AMARANTH_FOREST.getRegistryKey(), BIOMES_CONFIG.dark_amaranth_forests_weight / 10.0D);
		}
	}
}
