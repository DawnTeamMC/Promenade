package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ConfiguredFeatureCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.dawn.api.creator.PlacedFeatureCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeFoods;
import com.hugman.promenade.object.block.BlueberryBushBlock;
import com.hugman.promenade.util.GenUtil;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class FoodBundle extends PromenadeBundle {
	public static final Block BLUEBERRY_BUSH = add(new BlockCreator.Builder("blueberry_bush", BlueberryBushBlock::new, FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)).flammability(60, 100).render(BlockCreator.Render.CUTOUT).noItem().build());
	public static final Item BLUEBERRIES = add(new ItemCreator.Builder("blueberries", settings -> new AliasedBlockItem(BLUEBERRY_BUSH, settings), new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.BLUEBERRIES)).compostingChance(0.30F).build());

	public static final Item BANANA = add(new ItemCreator.Builder("banana", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.BANANA)).compostingChance(0.65F).build());
	public static final Item APRICOT = add(new ItemCreator.Builder("apricot", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.APRICOT)).compostingChance(0.65F).build());
	public static final Item MANGO = add(new ItemCreator.Builder("mango", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.MANGO)).compostingChance(0.65F).build());
	public static final Item DUCK = add(new ItemCreator.Builder("duck", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.DUCK)).build());
	public static final Item COOKED_DUCK = add(new ItemCreator.Builder("cooked_duck", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(PromenadeFoods.COOKED_DUCK)).build());

	public static class Features {
		public static class Configured {
			public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH = add(new ConfiguredFeatureCreator<>("patch/blueberry_bush", Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(BLUEBERRY_BUSH.getDefaultState().with(BlueberryBushBlock.AGE, 3)))), List.of(Blocks.GRASS_BLOCK)))));
		}

		public static class Placed {
			public static final PlacedFeature PATCH_BLUEBERRY_BUSH_COMMON = add(new PlacedFeatureCreator("patch/blueberry_bush/common", Configured.PATCH_BLUEBERRY_BUSH.withPlacement(RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of())));
			public static final PlacedFeature PATCH_BLUEBERRY_BUSH_RARE = add(new PlacedFeatureCreator("patch/blueberry_bush/rare", Configured.PATCH_BLUEBERRY_BUSH.withPlacement(RarityFilterPlacementModifier.of(384), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of())));
		}
	}

	public static void addToGen() {
		if(Promenade.CONFIG.features.blueberry_bushes) {
			BiomeModifications.addFeature(c -> c.hasBuiltInPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_COMMON), GenerationStep.Feature.VEGETAL_DECORATION, GenUtil.getKey(Features.Placed.PATCH_BLUEBERRY_BUSH_COMMON));
			BiomeModifications.addFeature(c -> c.hasBuiltInPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_RARE), GenerationStep.Feature.VEGETAL_DECORATION, GenUtil.getKey(Features.Placed.PATCH_BLUEBERRY_BUSH_RARE));
		}
	}
}