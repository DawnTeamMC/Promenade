package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class IgneousContent {
	public static final Block ASPHALT = new Block(DawnBlockSettings.copyOf(Blocks.ANDESITE).item().mapColor(MapColor.DEEPSLATE_GRAY).sounds(BlockSoundGroup.BASALT));
	public static final Block ASPHALT_SLAB = DawnFactory.slab(ASPHALT);
	public static final Block ASPHALT_STAIRS = DawnFactory.stairs(ASPHALT);
	public static final Block ASPHALT_WALL = DawnFactory.wall(ASPHALT);

	public static final Block POLISHED_ASPHALT = new Block(DawnBlockSettings.copyOf(ASPHALT));
	public static final Block POLISHED_ASPHALT_SLAB = DawnFactory.slab(POLISHED_ASPHALT);
	public static final Block POLISHED_ASPHALT_STAIRS = DawnFactory.stairs(POLISHED_ASPHALT);

	public static final Block BLUNITE = new Block(DawnBlockSettings.copyOf(Blocks.ANDESITE).item().mapColor(MapColor.TERRACOTTA_CYAN).sounds(BlockSoundGroup.TUFF));
	public static final Block BLUNITE_SLAB = DawnFactory.slab(BLUNITE);
	public static final Block BLUNITE_STAIRS = DawnFactory.stairs(BLUNITE);
	public static final Block BLUNITE_WALL = DawnFactory.wall(BLUNITE);

	public static final Block POLISHED_BLUNITE = new Block(DawnBlockSettings.copyOf(BLUNITE));
	public static final Block POLISHED_BLUNITE_SLAB = DawnFactory.slab(POLISHED_BLUNITE);
	public static final Block POLISHED_BLUNITE_STAIRS = DawnFactory.stairs(POLISHED_BLUNITE);

	public static final RegistryKey<PlacedFeature> ORE_BLUNITE_UPPER = DawnFactory.placedFeature(Promenade.id("ore/blunite/upper"));
	public static final RegistryKey<PlacedFeature> ORE_BLUNITE_LOWER = DawnFactory.placedFeature(Promenade.id("ore/blunite/lower"));
	public static final RegistryKey<PlacedFeature> ORE_ASPHALT_UPPER = DawnFactory.placedFeature(Promenade.id("ore/asphalt/upper"));
	public static final RegistryKey<PlacedFeature> ORE_ASPHALT_LOWER = DawnFactory.placedFeature(Promenade.id("ore/asphalt/lower"));

	public static void register(Registrar r) {
		r.add(("asphalt"), ASPHALT);
		r.add(("asphalt_slab"), ASPHALT_SLAB);
		r.add(("asphalt_stairs"), ASPHALT_STAIRS);
		r.add(("asphalt_wall"), ASPHALT_WALL);

		r.add(("polished_asphalt"), POLISHED_ASPHALT);
		r.add(("polished_asphalt_slab"), POLISHED_ASPHALT_SLAB);
		r.add(("polished_asphalt_stairs"), POLISHED_ASPHALT_STAIRS);

		r.add(("blunite"), BLUNITE);
		r.add(("blunite_slab"), BLUNITE_SLAB);
		r.add(("blunite_stairs"), BLUNITE_STAIRS);
		r.add(("blunite_wall"), BLUNITE_WALL);

		r.add(("polished_blunite"), POLISHED_BLUNITE);
		r.add(("polished_blunite_slab"), POLISHED_BLUNITE_SLAB);
		r.add(("polished_blunite_stairs"), POLISHED_BLUNITE_STAIRS);


		if(Promenade.CONFIG.world_features.igneous_rock_patches) {
			Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasFeature(OreConfiguredFeatures.ORE_ANDESITE) && c.hasFeature(OreConfiguredFeatures.ORE_DIORITE) && c.hasFeature(OreConfiguredFeatures.ORE_GRANITE);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_UPPER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_LOWER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_ASPHALT_UPPER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_ASPHALT_LOWER);
		}

		ItemGroupHelper.append(ItemGroups.NATURAL, e -> e.addAfter(Blocks.ANDESITE, BLUNITE, ASPHALT));
		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.POLISHED_ANDESITE_SLAB,
				BLUNITE, BLUNITE_SLAB, BLUNITE_STAIRS, BLUNITE_WALL,
				POLISHED_BLUNITE, POLISHED_BLUNITE_SLAB, POLISHED_BLUNITE_STAIRS,
				ASPHALT, ASPHALT_SLAB, ASPHALT_STAIRS, ASPHALT_WALL,
				POLISHED_ASPHALT, POLISHED_ASPHALT_SLAB, POLISHED_ASPHALT_STAIRS
		));

	}
}
