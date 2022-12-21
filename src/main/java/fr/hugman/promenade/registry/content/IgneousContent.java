package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.item.ItemGroupHelper;
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
	public static final Block BLUNITE = new Block(DawnBlockSettings.copyOf(Blocks.ANDESITE).item().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).sounds(BlockSoundGroup.TUFF));
	public static final Block BLUNITE_SLAB = DawnFactory.slab(BLUNITE);
	public static final Block BLUNITE_STAIRS = DawnFactory.stairs(BLUNITE);
	public static final Block BLUNITE_WALL = DawnFactory.wall(BLUNITE);

	public static final Block CARBONITE = new Block(DawnBlockSettings.copyOf(Blocks.ANDESITE).item().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.BASALT));
	public static final Block CARBONITE_SLAB = DawnFactory.slab(CARBONITE);
	public static final Block CARBONITE_STAIRS = DawnFactory.stairs(CARBONITE);
	public static final Block CARBONITE_WALL = DawnFactory.wall(CARBONITE);

	public static final Block POLISHED_BLUNITE = new Block(DawnBlockSettings.copyOf(BLUNITE));
	public static final Block POLISHED_BLUNITE_SLAB = DawnFactory.slab(POLISHED_BLUNITE);
	public static final Block POLISHED_BLUNITE_STAIRS = DawnFactory.stairs(POLISHED_BLUNITE);

	public static final Block POLISHED_CARBONITE = new Block(DawnBlockSettings.copyOf(CARBONITE));
	public static final Block POLISHED_CARBONITE_SLAB = DawnFactory.slab(POLISHED_CARBONITE);
	public static final Block POLISHED_CARBONITE_STAIRS = DawnFactory.stairs(POLISHED_CARBONITE);

	public static final RegistryKey<PlacedFeature> ORE_BLUNITE_UPPER = DawnFactory.placedFeature(Promenade.id("ore/blunite/upper"));
	public static final RegistryKey<PlacedFeature> ORE_BLUNITE_LOWER = DawnFactory.placedFeature(Promenade.id("ore/blunite/lower"));
	public static final RegistryKey<PlacedFeature> ORE_CARBONITE_UPPER = DawnFactory.placedFeature(Promenade.id("ore/carbonite/upper"));
	public static final RegistryKey<PlacedFeature> ORE_CARBONITE_LOWER = DawnFactory.placedFeature(Promenade.id("ore/carbonite/lower"));

	public static void init() {
		Registrar.add(Promenade.id("blunite"), BLUNITE);
		Registrar.add(Promenade.id("blunite_slab"), BLUNITE_SLAB);
		Registrar.add(Promenade.id("blunite_stairs"), BLUNITE_STAIRS);
		Registrar.add(Promenade.id("blunite_wall"), BLUNITE_WALL);

		Registrar.add(Promenade.id("carbonite"), CARBONITE);
		Registrar.add(Promenade.id("carbonite_slab"), CARBONITE_SLAB);
		Registrar.add(Promenade.id("carbonite_stairs"), CARBONITE_STAIRS);
		Registrar.add(Promenade.id("carbonite_wall"), CARBONITE_WALL);

		Registrar.add(Promenade.id("polished_blunite"), POLISHED_BLUNITE);
		Registrar.add(Promenade.id("polished_blunite_slab"), POLISHED_BLUNITE_SLAB);
		Registrar.add(Promenade.id("polished_blunite_stairs"), POLISHED_BLUNITE_STAIRS);

		Registrar.add(Promenade.id("polished_carbonite"), POLISHED_CARBONITE);
		Registrar.add(Promenade.id("polished_carbonite_slab"), POLISHED_CARBONITE_SLAB);
		Registrar.add(Promenade.id("polished_carbonite_stairs"), POLISHED_CARBONITE_STAIRS);

		if(Promenade.CONFIG.world_features.igneous_rock_patches) {
			Predicate<BiomeSelectionContext> hasIgneousRocks = c -> c.hasFeature(OreConfiguredFeatures.ORE_ANDESITE) && c.hasFeature(OreConfiguredFeatures.ORE_DIORITE) && c.hasFeature(OreConfiguredFeatures.ORE_GRANITE);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_UPPER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_BLUNITE_LOWER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_CARBONITE_UPPER);
			BiomeModifications.addFeature(hasIgneousRocks, GenerationStep.Feature.UNDERGROUND_ORES, ORE_CARBONITE_LOWER);
		}

		ItemGroupHelper.append(ItemGroups.NATURAL, e -> e.addAfter(Blocks.ANDESITE, BLUNITE, CARBONITE));
		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.POLISHED_ANDESITE_SLAB,
				BLUNITE, BLUNITE_SLAB, BLUNITE_STAIRS, BLUNITE_WALL,
				POLISHED_BLUNITE, POLISHED_BLUNITE_SLAB, POLISHED_BLUNITE_STAIRS,
				CARBONITE, CARBONITE_SLAB, CARBONITE_STAIRS, CARBONITE_WALL,
				POLISHED_CARBONITE, POLISHED_CARBONITE_SLAB, POLISHED_CARBONITE_STAIRS
		));

	}
}
