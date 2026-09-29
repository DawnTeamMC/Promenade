package fr.hugman.promenade.data.provider;

import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller.AddBiomeMarshaller;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller.ReplaceBiomeMarshaller;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Generates the placement of the mod's biomes. Each biome (or group of biomes) has its own file, so that data packs
 * can override or disable them one by one.
 */
public class PromenadeBiolithBiomePlacementProvider extends FabricCodecDataProvider<BiomePlacementMarshaller> {
	public PromenadeBiolithBiomePlacementProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture, PackOutput.Target.DATA_PACK, "biolith/biome_placement", BiomePlacementMarshaller.CODEC);
	}

	@Override
	public void configure(BiConsumer<Identifier, BiomePlacementMarshaller> provider, HolderLookup.Provider registryLookup) {
		provider.accept(Promenade.id("sakura_groves"), replacements(List.of(
				overworldReplacement(Biomes.FOREST, PromenadeBiomes.BLUSH_SAKURA_GROVE, 0.2D),
				overworldReplacement(Biomes.BIRCH_FOREST, PromenadeBiomes.COTTON_SAKURA_GROVE, 0.2D)
		)));
		provider.accept(Promenade.id("carnelian_treeway"), replacements(List.of(
				overworldReplacement(Biomes.PLAINS, PromenadeBiomes.CARNELIAN_TREEWAY, 0.2D)
		)));
		provider.accept(Promenade.id("glacarian_taiga"), replacements(
				Stream.of(Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.SNOWY_SLOPES, Biomes.JAGGED_PEAKS, Biomes.GROVE)
						.map(target -> overworldReplacement(target, PromenadeBiomes.GLACARIAN_TAIGA, 0.1D))
						.toList()
		));
		provider.accept(Promenade.id("dark_amaranth_forest"), new BiomePlacementMarshaller(
				List.of(new AddBiomeMarshaller(BuiltinDimensionTypes.NETHER, PromenadeBiomes.DARK_AMARANTH_FOREST, Climate.parameters(0.15F, -0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F))),
				List.of(), List.of(), List.of()
		));
	}

	private static ReplaceBiomeMarshaller overworldReplacement(ResourceKey<Biome> target, ResourceKey<Biome> biome, double proportion) {
		return new ReplaceBiomeMarshaller(BuiltinDimensionTypes.OVERWORLD, target, biome, proportion);
	}

	private static BiomePlacementMarshaller replacements(List<ReplaceBiomeMarshaller> replacements) {
		return new BiomePlacementMarshaller(List.of(), List.of(), replacements, List.of());
	}

	@Override
	public @NonNull String getName() {
		return "Biolith Biome Placements";
	}
}
