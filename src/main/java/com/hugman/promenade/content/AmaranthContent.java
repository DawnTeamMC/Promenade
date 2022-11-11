package com.hugman.promenade.content;

import com.hugman.dawn.api.object.block.DawnRootsBlock;
import com.hugman.newdawn.DawnFactory;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.object.block.DyliumBlock;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class AmaranthContent {
	private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
	private static final MapColor WOOD_COLOR = MapColor.DULL_PINK;
	private static final MapColor PLANKS_COLOR = MapColor.DULL_RED;

	public static final RegistryKey<ConfiguredFeature<?, ?>> PLANTED_AMARANTH_FUNGUS = DawnFactory.configuredFeatureKey(Promenade.id("amaranth_fungus/planted"));

	public static final Block BLACK_DYLIUM = DawnFactory.block(Promenade.id("black_dylium"), new DyliumBlock(FabricBlockSettings.of(Material.STONE, MapColor.DULL_RED).requiresTool().strength(3.0F, 9.0F).sounds(BlockSoundGroup.NYLIUM).ticksRandomly())).item(ItemGroup.BUILDING_BLOCKS).get();
	public static final Block DARK_AMARANTH_WART_BLOCK = DawnFactory.block(Promenade.id("dark_amaranth_wart_block"), FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK)).item(ItemGroup.BUILDING_BLOCKS).get();
	public static final Block DARK_AMARANTH_ROOTS = DawnFactory.block(Promenade.id("dark_amaranth_roots"), new DawnRootsBlock(FabricBlockSettings.of(Material.NETHER_SHOOTS, MapColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS), state -> state.isIn(PromenadeTags.Blocks.DARK_AMARANTH_ROOTS_PLACEABLE_ON))).item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.65F)).get();

	public static final Block DARK_AMARANTH_STEM = DawnFactory.log(Promenade.id("dark_amaranth_stem"), WOOD_COLOR, BARK_COLOR, true).get();
	public static final Block STRIPPED_DARK_AMARANTH_STEM = DawnFactory.log(Promenade.id("stripped_dark_amaranth_stem"), WOOD_COLOR, true).strippedFrom(DARK_AMARANTH_STEM).get();
	public static final Block DARK_AMARANTH_HYPHAE = DawnFactory.log(Promenade.id("dark_amaranth_hyphae"), BARK_COLOR, true).get();
	public static final Block STRIPPED_DARK_AMARANTH_HYPHAE = DawnFactory.log(Promenade.id("stripped_dark_amaranth_hyphae"), WOOD_COLOR, true).strippedFrom(DARK_AMARANTH_HYPHAE).get();

	public static final Block DARK_AMARANTH_PLANKS = DawnFactory.planks(Promenade.id("dark_amaranth_planks"), WOOD_COLOR, true).get();
	public static final Block DARK_AMARANTH_STAIRS = DawnFactory.stairs(Promenade.id("dark_amaranth_stairs"), DARK_AMARANTH_PLANKS, true).get();
	public static final Block DARK_AMARANTH_SLAB = DawnFactory.slab(Promenade.id("dark_amaranth_slab"), DARK_AMARANTH_PLANKS, true).get();
	public static final Block DARK_AMARANTH_TRAPDOOR = DawnFactory.trapdoor(Promenade.id("dark_amaranth_trapdoor"), DARK_AMARANTH_PLANKS, true).get();
	public static final Block DARK_AMARANTH_PRESSURE_PLATE = DawnFactory.pressurePlate(Promenade.id("dark_amaranth_pressure_plate"), DARK_AMARANTH_PLANKS, true).get();
	public static final Block DARK_AMARANTH_BUTTON = DawnFactory.woodenButton(Promenade.id("dark_amaranth_button"), true).get();
	public static final Block DARK_AMARANTH_FENCE = DawnFactory.fence(Promenade.id("dark_amaranth_fence"), DARK_AMARANTH_PLANKS, true).get();
	public static final Block DARK_AMARANTH_FENCE_GATE = DawnFactory.fenceGate(Promenade.id("dark_amaranth_fence_gate"), DARK_AMARANTH_PLANKS, true).get();
	public static final Block DARK_AMARANTH_DOOR = DawnFactory.door(Promenade.id("dark_amaranth_door"), DARK_AMARANTH_PLANKS).get();

	public static final Block DARK_AMARANTH_FUNGUS = DawnFactory.fungus(Promenade.id("dark_amaranth_fungus"), PLANTED_AMARANTH_FUNGUS, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_GROWABLE_ON).get();
	public static final Block POTTED_DARK_AMARANTH_FUNGUS = DawnFactory.potted(DARK_AMARANTH_FUNGUS).get();

	public static final RegistryKey<Biome> DARK_AMARANTH_FOREST = DawnFactory.biomeKey(Promenade.id("dark_amaranth_forest"));
	public static final RegistryKey<Biome> TALL_DARK_AMARANTH_FOREST = DawnFactory.biomeKey(Promenade.id("tall_dark_amaranth_forest"));

	public static void init() {
		if(Promenade.CONFIG.biomes.dark_amaranth_forests_weight > 0) {
			TheEndBiomes.addHighlandsBiome(TALL_DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
			TheEndBiomes.addMidlandsBiome(TALL_DARK_AMARANTH_FOREST, DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
		}
	}
}
