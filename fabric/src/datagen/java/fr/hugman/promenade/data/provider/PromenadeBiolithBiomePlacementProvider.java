package fr.hugman.promenade.data.provider;

import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller.AddBiomeMarshaller;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller.ReplaceBiomeMarshaller;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Generates the placement of the mod's biomes. Biolith 3.0 (for 1.21.1) reads a single
 * {@code biolith/biome_placement.json} per namespace, which data packs can override to change or disable it.
 */
public class PromenadeBiolithBiomePlacementProvider extends FabricCodecDataProvider<BiomePlacementMarshaller> {
	public PromenadeBiolithBiomePlacementProvider(FabricDataOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture, PackOutput.Target.DATA_PACK, "biolith", BiomePlacementMarshaller.CODEC);
	}

	@Override
	public void configure(BiConsumer<ResourceLocation, BiomePlacementMarshaller> provider, HolderLookup.Provider registryLookup) {
		List<ReplaceBiomeMarshaller> replacements = new ArrayList<>();
		// Sakura Groves
		replacements.add(overworldReplacement(Biomes.FOREST, PromenadeBiomes.BLUSH_SAKURA_GROVE, 0.2D));
		replacements.add(overworldReplacement(Biomes.BIRCH_FOREST, PromenadeBiomes.COTTON_SAKURA_GROVE, 0.2D));
		// Carnelian Treeway
		replacements.add(overworldReplacement(Biomes.PLAINS, PromenadeBiomes.CARNELIAN_TREEWAY, 0.2D));
		// Glacarian Taiga
		Stream.of(Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.SNOWY_SLOPES, Biomes.JAGGED_PEAKS, Biomes.GROVE)
				.map(target -> overworldReplacement(target, PromenadeBiomes.GLACARIAN_TAIGA, 0.1D))
				.forEach(replacements::add);

		List<AddBiomeMarshaller> additions = List.of(
				// Dark Amaranth Forest
				new AddBiomeMarshaller(BuiltinDimensionTypes.NETHER, PromenadeBiomes.DARK_AMARANTH_FOREST, Climate.parameters(0.15F, -0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F))
		);

		provider.accept(Promenade.id("biome_placement"), new BiomePlacementMarshaller(additions, List.of(), replacements, List.of()));
	}

	private static ReplaceBiomeMarshaller overworldReplacement(ResourceKey<Biome> target, ResourceKey<Biome> biome, double proportion) {
		return new ReplaceBiomeMarshaller(BuiltinDimensionTypes.OVERWORLD, target, biome, proportion);
	}

	@Override
	public @NotNull String getName() {
		return "Biolith Biome Placement";
	}
}
