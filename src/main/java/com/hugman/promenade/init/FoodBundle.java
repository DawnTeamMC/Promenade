package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.newdawn.DawnFactory;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.block.BlueberryBushBlock;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class FoodBundle extends PromenadeBundle
{
	public static final Block BLUEBERRY_BUSH = add(new BlockCreator.Builder("blueberry_bush", BlueberryBushBlock::new, FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH)).flammability(60, 100).render(BlockCreator.Render.CUTOUT).noItem().build());
	public static final Item BLUEBERRIES = add(new ItemCreator.Builder("blueberries", settings -> new AliasedBlockItem(BLUEBERRY_BUSH, settings), new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build())).compostingChance(0.30F).build());
	public static final RegistryKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_COMMON = DawnFactory.placedFeatureKey(Promenade.id("patch/blueberry_bush/common"));
	public static final RegistryKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_RARE = DawnFactory.placedFeatureKey(Promenade.id("patch/blueberry_bush/rare"));

	public static final Item BANANA = add(new ItemCreator.Builder("banana", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build())).compostingChance(0.65F).build());
	public static final Item APRICOT = add(new ItemCreator.Builder("apricot", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build())).compostingChance(0.65F).build());
	public static final Item MANGO = add(new ItemCreator.Builder("mango", Item::new, new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build())).compostingChance(0.65F).build());

	public static void init() {
		if(Promenade.CONFIG.world_features.blueberry_bushes) {
			BiomeModifications.addFeature(c -> c.hasBuiltInPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_COMMON.value()), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_BLUEBERRY_BUSH_COMMON);
			BiomeModifications.addFeature(c -> c.hasBuiltInPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_RARE.value()), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_BLUEBERRY_BUSH_RARE);
		}
	}
}