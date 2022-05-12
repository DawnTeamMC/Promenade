package com.hugman.promenade.init;

import com.hugman.promenade.Promenade;
import com.hugman.promenade.util.WorldGenUtil;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public class GalleryBundle extends PromenadeBundle
{
	public static final RegistryKey<Biome> TRITANOPIAN_GALLERY = WorldGenUtil.biomeKey("tritanopian_gallery");
	public static final RegistryKey<Biome> ACHROMATOPSIAN_GALLERY = WorldGenUtil.biomeKey("achromatopsian_gallery");
	public static final RegistryKey<Biome> PROTANOPIAN_GALLERY = WorldGenUtil.biomeKey("protanopian_gallery");

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.nether_galleries) {
			NetherBiomes.addNetherBiome(TRITANOPIAN_GALLERY, MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F));
			NetherBiomes.addNetherBiome(ACHROMATOPSIAN_GALLERY, MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, -0.4F, 0.0F, 0.1F, 0.0F));
			NetherBiomes.addNetherBiome(PROTANOPIAN_GALLERY, MultiNoiseUtil.createNoiseHypercube(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2F, 0.0F));
		}
	}
}