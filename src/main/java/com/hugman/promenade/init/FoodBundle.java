package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeFoods;
import com.hugman.promenade.object.block.BlueberryBushBlock;
import com.hugman.promenade.util.PFeatureRegistrer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class FoodBundle extends PromenadeBundle {
	public static final Block BLUEBERRY_BUSH = add(new BlockCreator.Builder("blueberry_bush", BlueberryBushBlock::new, FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)).flammability(60, 100).render(BlockCreator.Render.CUTOUT).noItem().build());
	public static final Item BLUEBERRIES = add(new ItemCreator.Builder("blueberries", settings -> new AliasedBlockItem(BLUEBERRY_BUSH, settings), new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.BLUEBERRIES)).compostingChance(0.30F).build());

	public static final Item BANANA = add(new ItemCreator.Builder("banana", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.BANANA)).compostingChance(0.65F).build());
	public static final Item APRICOT = add(new ItemCreator.Builder("apricot", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.APRICOT)).compostingChance(0.65F).build());
	public static final Item MANGO = add(new ItemCreator.Builder("mango", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.MANGO)).compostingChance(0.65F).build());

	public static class Features {
		public static class Configured {
			public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_BLUEBERRY_BUSH = PFeatureRegistrer.config("patch/blueberry_bush", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(BLUEBERRY_BUSH.getDefaultState().with(BlueberryBushBlock.AGE, 3))), List.of(Blocks.GRASS_BLOCK)));
		}

		public static class Placed {
			public static final RegistryEntry<PlacedFeature> PATCH_BLUEBERRY_BUSH_COMMON = PFeatureRegistrer.place("patch/blueberry_bush/common", Configured.PATCH_BLUEBERRY_BUSH, RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
			public static final RegistryEntry<PlacedFeature> PATCH_BLUEBERRY_BUSH_RARE = PFeatureRegistrer.place("patch/blueberry_bush/rare", Configured.PATCH_BLUEBERRY_BUSH, RarityFilterPlacementModifier.of(384), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
		}
	}

	public static void addToGen() {
		if(Promenade.CONFIG.world_features.blueberry_bushes) {
			BiomeModifications.addFeature(c -> c.hasBuiltInPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_COMMON.value()), GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PATCH_BLUEBERRY_BUSH_COMMON.getKey().orElseThrow());
			BiomeModifications.addFeature(c -> c.hasBuiltInPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_RARE.value()), GenerationStep.Feature.VEGETAL_DECORATION, Features.Placed.PATCH_BLUEBERRY_BUSH_RARE.getKey().orElseThrow());
		}
	}
}