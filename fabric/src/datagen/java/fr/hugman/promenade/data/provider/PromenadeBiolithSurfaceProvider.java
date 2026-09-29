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
import net.minecraft.data.worldgen.material.VanillaMaterialConditions;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.material.MaterialRules;
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
		var conditions = registryLookup.lookupOrThrow(Registries.MATERIAL_CONDITION);
		var onFloor = MaterialRules.getCondition(conditions, VanillaMaterialConditions.ON_FLOOR);
		provider.accept(Promenade.id("dark_amaranth_forest"), new SurfaceGenerationMarshaller(List.of(
			new SurfaceGenerationMarshaller.SurfaceRuleMarshaller(
				BuiltinDimensionTypes.NETHER, Identifier.withDefaultNamespace("rules/nether"), List.of(MaterialRules.ifTrue(onFloor,
					MaterialRules.sequence(
							MaterialRules.ifTrue(MaterialRules.not(MaterialRules.yBlockCheck(VerticalAnchor.absolute(32), 0)), MaterialRules.ifTrue(MaterialRules.hole(), MaterialRules.state(Blocks.LAVA.defaultBlockState()))),
							MaterialRules.ifTrue(MaterialRules.isBiome(biomes, PromenadeBiomes.DARK_AMARANTH_FOREST),
									MaterialRules.ifTrue(
											MaterialRules.not(MaterialRules.noiseCondition2d(Noises.NETHERRACK, 0.54)),
											MaterialRules.ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(31), 0), MaterialRules.sequence(MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.NETHER_WART, 1.17), MaterialRules.state(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK.defaultBlockState())), MaterialRules.state(PromenadeBlocks.DARK_AMARANTH_NYLIUM.defaultBlockState())))
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