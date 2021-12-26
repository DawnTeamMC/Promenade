package com.hugman.promenade.mixin;

import com.google.common.collect.ImmutableList;
import com.hugman.promenade.util.BiomeUtil;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(MaterialRules.class)
public class MaterialRulesMixin {
	@ModifyVariable(method = "biome(Ljava/util/List;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$BiomeMaterialCondition;", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private static List<RegistryKey<Biome>> promenade$appendBiomes(List<RegistryKey<Biome>> biomes) {
		if(biomes.contains(BiomeKeys.WARPED_FOREST)) return ImmutableList.<RegistryKey<Biome>>builder().addAll(biomes).add(BiomeUtil.TALL_WARPED_FOREST_KEY).build();
		if(biomes.contains(BiomeKeys.CRIMSON_FOREST)) return ImmutableList.<RegistryKey<Biome>>builder().addAll(biomes).add(BiomeUtil.TALL_CRIMSON_FOREST_KEY).build();
		if(biomes.contains(BiomeKeys.NETHER_WASTES)) return ImmutableList.<RegistryKey<Biome>>builder().addAll(biomes).add(BiomeUtil.ACHROMATOPSIAN_GALLERY_KEY).add(BiomeUtil.PROTANOPIAN_GALLERY_KEY).add(BiomeUtil.TRITANOPIAN_GALLERY_KEY).build();
		return biomes;
	}
}
