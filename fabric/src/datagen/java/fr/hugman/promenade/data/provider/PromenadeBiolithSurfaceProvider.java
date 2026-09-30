package fr.hugman.promenade.data.provider;

import com.terraformersmc.biolith.impl.data.SurfaceGenerationMarshaller;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * Generates the mod's surface rules. Biolith 3.0 (for 1.21.1) reads a single {@code biolith/surface_generation.json} per namespace.
 */
public class PromenadeBiolithSurfaceProvider extends FabricCodecDataProvider<SurfaceGenerationMarshaller> {
	public PromenadeBiolithSurfaceProvider(FabricDataOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture, PackOutput.Target.DATA_PACK, "biolith", SurfaceGenerationMarshaller.CODEC);
	}

	@Override
	public void configure(BiConsumer<ResourceLocation, SurfaceGenerationMarshaller> provider, HolderLookup.Provider registryLookup) {
		provider.accept(Promenade.id("surface_generation"), new SurfaceGenerationMarshaller(List.of(
			// Dark Amaranth Forest
			new SurfaceGenerationMarshaller.SurfaceRuleMarshaller(
				BuiltinDimensionTypes.NETHER, ResourceLocation.withDefaultNamespace("rules/nether"), List.of(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
					SurfaceRules.sequence(
							SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0)), SurfaceRules.ifTrue(SurfaceRules.hole(), SurfaceRules.state(Blocks.LAVA.defaultBlockState()))),
							SurfaceRules.ifTrue(SurfaceRules.isBiome(PromenadeBiomes.DARK_AMARANTH_FOREST),
									SurfaceRules.ifTrue(
											SurfaceRules.not(SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.54)),
											SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17), SurfaceRules.state(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK.defaultBlockState())), SurfaceRules.state(PromenadeBlocks.DARK_AMARANTH_NYLIUM.defaultBlockState())))
									)
							)
					)
			))
			)
		)));
	}

	@Override
	public @NotNull String getName() {
		return "Biolith Surface Generation";
	}
}
