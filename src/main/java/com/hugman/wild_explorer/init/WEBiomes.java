package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.config.WEConfig;
import com.hugman.wild_explorer.object.world.gen.WEBiomeCreator;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class WEBiomes extends WEPack {
	public static final WEConfig.BiomesCategory CONFIG = WildExplorer.CONFIG.biomes;
	public static final RegistryKey<Biome> PUMPKIN_PASTURES = register(new BiomeCreator.Builder("pumpkin_pastures", WEBiomeCreator.createPumpkinPastures()));
	public static final RegistryKey<Biome> PINK_CHERRY_OAK_FOREST = register(new BiomeCreator.Builder("pink_cherry_oak_forest", WEBiomeCreator.createCherryOakForest(true)));
	public static final RegistryKey<Biome> WHITE_CHERRY_OAK_FOREST = register(new BiomeCreator.Builder("white_cherry_oak_forest", WEBiomeCreator.createCherryOakForest(false)));
	public static final RegistryKey<Biome> TALL_CRIMSON_FOREST = register(new BiomeCreator.Builder("tall_crimson_forest", WEBiomeCreator.createTallCrimsonForest()));
	public static final RegistryKey<Biome> TALL_WARPED_FOREST = register(new BiomeCreator.Builder("tall_warped_forest", WEBiomeCreator.createTallWarpedForest()));
	public static final RegistryKey<Biome> TRITANOPIAN_GALLERY = register(new BiomeCreator.Builder("tritanopian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.composeTritanopianGalleryGenerationSettings())));
	public static final RegistryKey<Biome> ACHROMATOPSIAN_GALLERY = register(new BiomeCreator.Builder("achromatopsian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.composeAchromatopsianGalleryGenerationSettings())));
	public static final RegistryKey<Biome> PROTANOPIAN_GALLERY = register(new BiomeCreator.Builder("protanopian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.composeProtanopianGalleryGenerationSettings())));
	public static final RegistryKey<Biome> DARK_AMARANTH_FOREST = register(new BiomeCreator.Builder("dark_amaranth_forest", WEBiomeCreator.createDarkAmaranthForest(false)));
	public static final RegistryKey<Biome> TALL_DARK_AMARANTH_FOREST = register(new BiomeCreator.Builder("tall_dark_amaranth_forest", WEBiomeCreator.createDarkAmaranthForest(true)));

	public static void init() {
	}

	public static void addToGen() {
		if(CONFIG.pumpkin_pastures) OverworldBiomes.addContinentalBiome(PUMPKIN_PASTURES, OverworldClimate.COOL, 1D);
		if(CONFIG.pink_cherry_oak_forest) OverworldBiomes.addContinentalBiome(PINK_CHERRY_OAK_FOREST, OverworldClimate.COOL, 1D);
		if(CONFIG.white_cherry_oak_forest) OverworldBiomes.addContinentalBiome(WHITE_CHERRY_OAK_FOREST, OverworldClimate.COOL, 1D);
		if(CONFIG.tall_crimson_forest) NetherBiomes.addNetherBiome(TALL_CRIMSON_FOREST, new Biome.MixedNoisePoint(0.4F, 0.0F, 0.1F, 0.0F, 0.0F));
		if(CONFIG.tall_warped_forest) NetherBiomes.addNetherBiome(TALL_WARPED_FOREST, new Biome.MixedNoisePoint(0.0F, 0.5F, 0.1F, 0.0F, 0.375F));
		if(CONFIG.tritanopian_gallery) NetherBiomes.addNetherBiome(TRITANOPIAN_GALLERY, new Biome.MixedNoisePoint(0.05F, 0.025F, 0.0F, 0.0F, 0.05F));
		if(CONFIG.achromatopsian_gallery) NetherBiomes.addNetherBiome(ACHROMATOPSIAN_GALLERY, new Biome.MixedNoisePoint(0.1F, 0.05F, 0.0F, 0.0F, 0.025F));
		if(CONFIG.protanopian_gallery) NetherBiomes.addNetherBiome(PROTANOPIAN_GALLERY, new Biome.MixedNoisePoint(0.025F, 0.1F, 0.0F, 0.0F, 0.05F));
		if(CONFIG.dark_amaranth_forest) {
			TheEndBiomes.addHighlandsBiome(TALL_DARK_AMARANTH_FOREST, 1.0D);
			TheEndBiomes.addMidlandsBiome(TALL_DARK_AMARANTH_FOREST, DARK_AMARANTH_FOREST, 1.0D);
		}
	}
}
