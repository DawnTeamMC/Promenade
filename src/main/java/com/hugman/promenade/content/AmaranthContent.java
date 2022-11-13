package com.hugman.promenade.content;

import com.hugman.dawn.api.object.block.DawnRootsBlock;
import com.hugman.newdawn.DawnBlockSettings;
import com.hugman.newdawn.DawnFactory;
import com.hugman.newdawn.DawnItemSettings;
import com.hugman.newdawn.RegistryHelper;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.object.block.DyliumBlock;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class AmaranthContent {
	private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
	private static final MapColor WOOD_COLOR = MapColor.DULL_PINK;

	public static RegistryKey<ConfiguredFeature<?, ?>> PLANTED_AMARANTH_FUNGUS = DawnFactory.configuredFeatureKey(Promenade.id("amaranth_fungus/planted"));

	public static Block BLACK_DYLIUM = new DyliumBlock(
			DawnBlockSettings.of(Material.STONE, MapColor.DULL_RED)
					.item(ItemGroup.BUILDING_BLOCKS)
					.requiresTool()
					.strength(3.0F, 9.0F)
					.sounds(BlockSoundGroup.NYLIUM)
					.ticksRandomly());
	public static Block DARK_AMARANTH_WART_BLOCK = new Block(
			DawnBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL)
					.item(ItemGroup.BUILDING_BLOCKS)
					.strength(1.0F)
					.sounds(BlockSoundGroup.WART_BLOCK));
	public static Block DARK_AMARANTH_ROOTS = new DawnRootsBlock(
			DawnBlockSettings.of(Material.NETHER_SHOOTS, MapColor.CYAN)
					.noCollision()
					.breakInstantly()
					.sounds(BlockSoundGroup.ROOTS)
					.item(DawnItemSettings.of(ItemGroup.DECORATIONS).compostingChance(0.65F)),
			state -> state.isIn(PromenadeTags.Blocks.DARK_AMARANTH_ROOTS_PLACEABLE_ON));

	public static Block STRIPPED_DARK_AMARANTH_STEM = new PillarBlock(DawnFactory.logSettings(true, WOOD_COLOR));
	public static Block DARK_AMARANTH_STEM = new PillarBlock(DawnFactory.logSettings(true, WOOD_COLOR, BARK_COLOR).stripInto(STRIPPED_DARK_AMARANTH_STEM));
	public static Block STRIPPED_DARK_AMARANTH_HYPHAE = new PillarBlock(DawnFactory.logSettings(true, WOOD_COLOR));
	public static Block DARK_AMARANTH_HYPHAE = new PillarBlock(DawnFactory.logSettings(true, BARK_COLOR).stripInto(STRIPPED_DARK_AMARANTH_HYPHAE));

	public static Block DARK_AMARANTH_PLANKS = DawnFactory.planks(false, WOOD_COLOR);
	public static Block DARK_AMARANTH_STAIRS = DawnFactory.stairs(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_SLAB = DawnFactory.slab(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_FENCE = DawnFactory.fence(true, DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_FENCE_GATE = DawnFactory.fenceGate(true, DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_DOOR = DawnFactory.door(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_TRAPDOOR = DawnFactory.trapdoor(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_BUTTON = DawnFactory.woodenButton(true);
	public static Block DARK_AMARANTH_PRESSURE_PLATE = DawnFactory.pressurePlate(DARK_AMARANTH_PLANKS);

	public static Block DARK_AMARANTH_FUNGUS = DawnFactory.fungus(PLANTED_AMARANTH_FUNGUS, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_GROWABLE_ON);
	public static Block POTTED_DARK_AMARANTH_FUNGUS = DawnFactory.potted(DARK_AMARANTH_FUNGUS);

	public static RegistryKey<Biome> DARK_AMARANTH_FOREST = DawnFactory.biomeKey(Promenade.id("dark_amaranth_forest"));
	public static RegistryKey<Biome> TALL_DARK_AMARANTH_FOREST = DawnFactory.biomeKey(Promenade.id("tall_dark_amaranth_forest"));

	public static void init() {
		RegistryHelper.block(Promenade.id("black_dylium"), BLACK_DYLIUM);
		RegistryHelper.block(Promenade.id("dark_amaranth_wart_block"), DARK_AMARANTH_WART_BLOCK);
		RegistryHelper.block(Promenade.id("dark_amaranth_roots"), DARK_AMARANTH_ROOTS);

		RegistryHelper.block(Promenade.id("dark_amaranth_stem"), DARK_AMARANTH_STEM);
		RegistryHelper.block(Promenade.id("stripped_dark_amaranth_stem"), STRIPPED_DARK_AMARANTH_STEM);
		RegistryHelper.block(Promenade.id("dark_amaranth_hyphae"), DARK_AMARANTH_HYPHAE);
		RegistryHelper.block(Promenade.id("stripped_dark_amaranth_hyphae"), STRIPPED_DARK_AMARANTH_HYPHAE);

		RegistryHelper.block(Promenade.id("dark_amaranth_planks"), DARK_AMARANTH_PLANKS);
		RegistryHelper.block(Promenade.id("dark_amaranth_stairs"), DARK_AMARANTH_STAIRS);
		RegistryHelper.block(Promenade.id("dark_amaranth_slab"), DARK_AMARANTH_SLAB);
		RegistryHelper.block(Promenade.id("dark_amaranth_fence"), DARK_AMARANTH_FENCE);
		RegistryHelper.block(Promenade.id("dark_amaranth_fence_gate"), DARK_AMARANTH_FENCE_GATE);
		RegistryHelper.block(Promenade.id("dark_amaranth_door"), DARK_AMARANTH_DOOR);
		RegistryHelper.block(Promenade.id("dark_amaranth_trapdoor"), DARK_AMARANTH_TRAPDOOR);
		RegistryHelper.block(Promenade.id("dark_amaranth_button"), DARK_AMARANTH_BUTTON);
		RegistryHelper.block(Promenade.id("dark_amaranth_pressure_plate"), DARK_AMARANTH_PRESSURE_PLATE);

		RegistryHelper.block(Promenade.id("dark_amaranth_fungus"), DARK_AMARANTH_FUNGUS);
		RegistryHelper.block(Promenade.id("potted_dark_amaranth_fungus"), POTTED_DARK_AMARANTH_FUNGUS);

		if(Promenade.CONFIG.biomes.dark_amaranth_forests_weight > 0) {
			TheEndBiomes.addHighlandsBiome(TALL_DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
			TheEndBiomes.addMidlandsBiome(TALL_DARK_AMARANTH_FOREST, DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
		}
	}
}
