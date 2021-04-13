package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.wild_explorer.WildExplorer;
import com.hugman.wild_explorer.config.WEConfig;
import com.hugman.wild_explorer.object.world.gen.WEBiomeCreator;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.world.biome.Biome;

public class WEBiomes extends WEBundle {
	public static final WEConfig.BiomesCategory CONFIG = WildExplorer.CONFIG.biomes;
	public static final BiomeCreator PUMPKIN_PASTURES = creator(new BiomeCreator("pumpkin_pastures", WEBiomeCreator.createPumpkinPastures()));
	public static final BiomeCreator PINK_CHERRY_OAK_FOREST = creator(new BiomeCreator("pink_cherry_oak_forest", WEBiomeCreator.createCherryOakForest(true)));
	public static final BiomeCreator WHITE_CHERRY_OAK_FOREST = creator(new BiomeCreator("white_cherry_oak_forest", WEBiomeCreator.createCherryOakForest(false)));
	public static final BiomeCreator TALL_CRIMSON_FOREST = creator(new BiomeCreator("tall_crimson_forest", WEBiomeCreator.createTallCrimsonForest()));
	public static final BiomeCreator TALL_WARPED_FOREST = creator(new BiomeCreator("tall_warped_forest", WEBiomeCreator.createTallWarpedForest()));
	public static final BiomeCreator TRITANOPIAN_GALLERY = creator(new BiomeCreator("tritanopian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.composeTritanopianGalleryGenerationSettings())));
	public static final BiomeCreator ACHROMATOPSIAN_GALLERY = creator(new BiomeCreator("achromatopsian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.composeAchromatopsianGalleryGenerationSettings())));
	public static final BiomeCreator PROTANOPIAN_GALLERY = creator(new BiomeCreator("protanopian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.composeProtanopianGalleryGenerationSettings())));
	public static final BiomeCreator DARK_AMARANTH_FOREST = creator(new BiomeCreator("dark_amaranth_forest", WEBiomeCreator.createDarkAmaranthForest(false)));
	public static final BiomeCreator TALL_DARK_AMARANTH_FOREST = creator(new BiomeCreator("tall_dark_amaranth_forest", WEBiomeCreator.createDarkAmaranthForest(true)));

	public static void init() {
	}

	public static void addToGen() {
		if(CONFIG.pumpkin_pastures) OverworldBiomes.addContinentalBiome(PUMPKIN_PASTURES.getRegistryKey(), OverworldClimate.COOL, CONFIG.pumpkin_pastures_weight / 10.0D);
		if(CONFIG.cherry_oak_forests) {
			OverworldBiomes.addContinentalBiome(PINK_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, CONFIG.cherry_oak_forests_weight / 10.0D);
			OverworldBiomes.addContinentalBiome(WHITE_CHERRY_OAK_FOREST.getRegistryKey(), OverworldClimate.COOL, CONFIG.cherry_oak_forests_weight / 10.0D);
		}
		if(CONFIG.tall_nether_forests) {
			NetherBiomes.addNetherBiome(TALL_CRIMSON_FOREST.getRegistryKey(), new Biome.MixedNoisePoint(0.4F, 0.0F, 0.1F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(TALL_WARPED_FOREST.getRegistryKey(), new Biome.MixedNoisePoint(0.0F, 0.5F, 0.1F, 0.0F, 0.375F));
		}
		if(CONFIG.nether_galleries) {
			NetherBiomes.addNetherBiome(TRITANOPIAN_GALLERY.getRegistryKey(), new Biome.MixedNoisePoint(0.05F, 0.025F, 0.0F, 0.0F, 0.05F));
			NetherBiomes.addNetherBiome(ACHROMATOPSIAN_GALLERY.getRegistryKey(), new Biome.MixedNoisePoint(0.1F, 0.05F, 0.0F, 0.0F, 0.025F));
			NetherBiomes.addNetherBiome(PROTANOPIAN_GALLERY.getRegistryKey(), new Biome.MixedNoisePoint(0.025F, 0.1F, 0.0F, 0.0F, 0.05F));
		}
		if(CONFIG.dark_amaranth_forests) {
			TheEndBiomes.addHighlandsBiome(TALL_DARK_AMARANTH_FOREST.getRegistryKey(), CONFIG.dark_amaranth_forests_weight / 10.0D);
			TheEndBiomes.addMidlandsBiome(TALL_DARK_AMARANTH_FOREST.getRegistryKey(), DARK_AMARANTH_FOREST.getRegistryKey(), CONFIG.dark_amaranth_forests_weight / 10.0D);
		}
	}
}
