package fr.hugman.promenade.data.provider;

import com.terraformersmc.biolith.impl.data.SurfaceGenerationMarshaller;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class PromenadeBiolithSurfaceProvider extends FabricCodecDataProvider<SurfaceGenerationMarshaller> {
	public PromenadeBiolithSurfaceProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture, PackOutput.Target.DATA_PACK, "biolith/surface_generation", SurfaceGenerationMarshaller.CODEC);
	}

	@Override
	public void configure(BiConsumer<Identifier, SurfaceGenerationMarshaller> provider, HolderLookup.Provider registryLookup) {
		var biomes = registryLookup.lookupOrThrow(Registries.BIOME);
		provider.accept(Promenade.id("dark_amaranth_forest"), new SurfaceGenerationMarshaller(List.of(
			new SurfaceGenerationMarshaller.SurfaceRuleMarshaller(
				BuiltinDimensionTypes.NETHER, Promenade.id("rules/nether"), List.of(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
					SurfaceRules.sequence(
							SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0)), SurfaceRules.ifTrue(SurfaceRules.hole(), SurfaceRuleData.makeStateRule(Blocks.LAVA))),
							SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, PromenadeBiomes.DARK_AMARANTH_FOREST),
									SurfaceRules.ifTrue(
											SurfaceRules.not(SurfaceRules.noiseCondition2d(Noises.NETHERRACK, 0.54)),
											SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.NETHER_WART, 1.17), SurfaceRuleData.makeStateRule(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK)), SurfaceRuleData.makeStateRule(PromenadeBlocks.DARK_AMARANTH_NYLIUM)))
									)
							)
					)
			))
			)
		)));
	}

	@Override
	public @NonNull String getName() {
		return "";
	}
}