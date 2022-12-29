package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.BlueberryBushBlock;
import fr.hugman.promenade.item.ItemGroupHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class FoodContent {
	public static final Block BLUEBERRY_BUSH = new BlueberryBushBlock(
			DawnBlockSettings.of(Material.PLANT)
					.ticksRandomly()
					.noCollision()
					.sounds(BlockSoundGroup.SWEET_BERRY_BUSH)
					.flammability(60, 100));
	public static final Item BLUEBERRIES = new AliasedBlockItem(BLUEBERRY_BUSH, new DawnItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build()).compostingChance(0.30F));
	public static final RegistryKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_COMMON = DawnFactory.placedFeature(Promenade.id("patch/blueberry_bush/common"));
	public static final RegistryKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_RARE = DawnFactory.placedFeature(Promenade.id("patch/blueberry_bush/rare"));

	public static final Item BANANA = new Item(new DawnItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build()).compostingChance(0.65F));
	public static final Item APRICOT = new Item(new DawnItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build()).compostingChance(0.65F));
	public static final Item MANGO = new Item(new DawnItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build()).compostingChance(0.65F));

	public static void init() {
		Registrar.add(Promenade.id("blueberry_bush"), BLUEBERRY_BUSH);
		Registrar.add(Promenade.id("blueberries"), BLUEBERRIES);

		Registrar.add(Promenade.id("banana"), BANANA);
		Registrar.add(Promenade.id("apricot"), APRICOT);
		Registrar.add(Promenade.id("mango"), MANGO);

		if(Promenade.CONFIG.world_features.blueberry_bushes) {
			BiomeModifications.addFeature(c -> c.hasPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_COMMON), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_BLUEBERRY_BUSH_COMMON);
			BiomeModifications.addFeature(c -> c.hasPlacedFeature(VegetationPlacedFeatures.PATCH_BERRY_RARE), GenerationStep.Feature.VEGETAL_DECORATION, PATCH_BLUEBERRY_BUSH_RARE);
		}

		ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> {
			e.addAfter(Items.SWEET_BERRIES, BLUEBERRIES);
			e.addAfter(Items.ENCHANTED_GOLDEN_APPLE, BANANA);
			//TODO apricot and mango
		});
	}
}