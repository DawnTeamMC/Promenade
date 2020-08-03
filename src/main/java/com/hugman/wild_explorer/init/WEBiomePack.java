package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.wild_explorer.util.WEBiomeCreator;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.world.biome.Biome;

public class WEBiomePack extends WEPack {
	public static final Biome PUMPKIN_PASTURES = register(new BiomeCreator.Builder("pumpkin_pastures", WEBiomeCreator.createPumpkinPastures()).addToOverworldContinental(OverworldClimate.TEMPERATE, 1D, true));
	public static final Biome PINK_CHERRY_OAK_FOREST = register(new BiomeCreator.Builder("pink_cherry_oak_forest", WEBiomeCreator.createCherryOakForest(true)).addToOverworldContinental(OverworldClimate.COOL, 1D, true));
	public static final Biome WHITE_CHERRY_OAK_FOREST = register(new BiomeCreator.Builder("white_cherry_oak_forest", WEBiomeCreator.createCherryOakForest(false)).addToOverworldContinental(OverworldClimate.COOL, 1D, true));
	public static final Biome TALL_CRIMSON_FOREST = register(new BiomeCreator.Builder("tall_crimson_forest", WEBiomeCreator.createTallCrimsonForest()).addToNether(0.4F, 0.0F, 0.1F, 0.0F, 0.0F));
	public static final Biome TALL_WARPED_FOREST = register(new BiomeCreator.Builder("tall_warped_forest", WEBiomeCreator.createTallWarpedForest()).addToNether(0.0F, 0.5F, 0.1F, 0.0F, 0.375F));
	public static final Biome TRITANOPIAN_GALLERY = register(new BiomeCreator.Builder("tritanopian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.createTritanopianGalleryGenerationSettings())).addToNether(0.05F, 0.025F, 0.0F, 0.0F, 0.05F));
	public static final Biome ACHROMATOPSIAN_GALLERY = register(new BiomeCreator.Builder("achromatopsian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.createAchromatopsianGalleryGenerationSettings())).addToNether(0.1F, 0.05F, 0.0F, 0.0F, 0.025F));
	public static final Biome PROTANOPIAN_GALLERY = register(new BiomeCreator.Builder("protanopian_gallery", WEBiomeCreator.createGallery(WEBiomeCreator.createProtanopianGalleryGenerationSettings())).addToNether(0.025F, 0.1F, 0.0F, 0.0F, 0.05F));
	//public static final Biome DARK_AMARANTH_FOREST = register("dark_amaranth_forest", new DarkAmaranthForest());
	//public static final Biome TALL_DARK_AMARANTH_FOREST = register("tall_dark_amaranth_forest", new TallDarkAmaranthForest());
}
