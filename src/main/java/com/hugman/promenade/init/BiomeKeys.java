package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BiomeCreator;
import com.hugman.promenade.Promenade;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeKeys {
	public static final RegistryKey<Biome> TALL_CRIMSON_FOREST = RegistryKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id("tall_crimson_forest"));
	public static final RegistryKey<Biome> TALL_WARPED_FOREST = RegistryKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id("tall_warped_forest"));
	public static final RegistryKey<Biome> TRITANOPIAN_GALLERY = RegistryKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id("tritanopian_gallery"));
	public static final RegistryKey<Biome> ACHROMATOPSIAN_GALLERY = RegistryKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id("achromatopsian_gallery"));
	public static final RegistryKey<Biome> PROTANOPIAN_GALLERY = RegistryKey.of(Registry.BIOME_KEY, Promenade.MOD_DATA.id("protanopian_gallery"));
}
