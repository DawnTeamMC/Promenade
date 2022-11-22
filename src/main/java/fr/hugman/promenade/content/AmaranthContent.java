package fr.hugman.promenade.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.block.DawnRootsBlock;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.DyliumBlock;
import fr.hugman.promenade.item.ItemGroupHelper;
import fr.hugman.promenade.registry.tag.PromenadeTags;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class AmaranthContent {
	private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
	private static final MapColor WOOD_COLOR = MapColor.DULL_PINK;

	public static RegistryKey<ConfiguredFeature<?, ?>> PLANTED_AMARANTH_FUNGUS = DawnFactory.configuredFeature(Promenade.id("amaranth_fungus/planted"));

	public static Block BLACK_DYLIUM = new DyliumBlock(
			DawnBlockSettings.of(Material.STONE, MapColor.DULL_RED)
					.item()
					.requiresTool()
					.strength(3.0F, 9.0F)
					.sounds(BlockSoundGroup.NYLIUM)
					.ticksRandomly());
	public static Block DARK_AMARANTH_WART_BLOCK = new Block(
			DawnBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL)
					.item()
					.strength(1.0F)
					.sounds(BlockSoundGroup.WART_BLOCK));
	public static Block DARK_AMARANTH_ROOTS = new DawnRootsBlock(state -> state.isIn(PromenadeTags.Blocks.DARK_AMARANTH_ROOTS_PLACEABLE_ON),
			DawnBlockSettings.of(Material.NETHER_SHOOTS, MapColor.CYAN)
					.noCollision()
					.breakInstantly()
					.sounds(BlockSoundGroup.ROOTS)
					.item(new DawnItemSettings().compostingChance(0.65F)));

	public static Block STRIPPED_DARK_AMARANTH_STEM = new PillarBlock(DawnFactory.logSettings(true, WOOD_COLOR));
	public static Block DARK_AMARANTH_STEM = new PillarBlock(DawnFactory.logSettings(true, WOOD_COLOR, BARK_COLOR).stripInto(STRIPPED_DARK_AMARANTH_STEM));
	public static Block STRIPPED_DARK_AMARANTH_HYPHAE = new PillarBlock(DawnFactory.logSettings(true, WOOD_COLOR));
	public static Block DARK_AMARANTH_HYPHAE = new PillarBlock(DawnFactory.logSettings(true, BARK_COLOR).stripInto(STRIPPED_DARK_AMARANTH_HYPHAE));

	public static Block DARK_AMARANTH_PLANKS = DawnFactory.planks(false, WOOD_COLOR);
	public static Block DARK_AMARANTH_STAIRS = DawnFactory.stairs(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_SLAB = DawnFactory.slab(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_FENCE = DawnFactory.fence(true, DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_FENCE_GATE = DawnFactory.fenceGate(true, DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_DOOR = DawnFactory.woodenDoor(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_TRAPDOOR = DawnFactory.woodenTrapdoor(DARK_AMARANTH_PLANKS);
	public static Block DARK_AMARANTH_BUTTON = DawnFactory.woodenButton(true);
	public static Block DARK_AMARANTH_PRESSURE_PLATE = DawnFactory.pressurePlate(DARK_AMARANTH_PLANKS);

	public static Block DARK_AMARANTH_FUNGUS = DawnFactory.fungus(PLANTED_AMARANTH_FUNGUS, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_GROWABLE_ON);
	public static Block POTTED_DARK_AMARANTH_FUNGUS = DawnFactory.potted(DARK_AMARANTH_FUNGUS);

	public static RegistryKey<Biome> DARK_AMARANTH_FOREST = DawnFactory.biome(Promenade.id("dark_amaranth_forest"));
	public static RegistryKey<Biome> TALL_DARK_AMARANTH_FOREST = DawnFactory.biome(Promenade.id("tall_dark_amaranth_forest"));

	public static void init() {
		Registrar.add(Promenade.id("black_dylium"), BLACK_DYLIUM);
		Registrar.add(Promenade.id("dark_amaranth_wart_block"), DARK_AMARANTH_WART_BLOCK);
		Registrar.add(Promenade.id("dark_amaranth_roots"), DARK_AMARANTH_ROOTS);

		Registrar.add(Promenade.id("dark_amaranth_stem"), DARK_AMARANTH_STEM);
		Registrar.add(Promenade.id("stripped_dark_amaranth_stem"), STRIPPED_DARK_AMARANTH_STEM);
		Registrar.add(Promenade.id("dark_amaranth_hyphae"), DARK_AMARANTH_HYPHAE);
		Registrar.add(Promenade.id("stripped_dark_amaranth_hyphae"), STRIPPED_DARK_AMARANTH_HYPHAE);

		Registrar.add(Promenade.id("dark_amaranth_planks"), DARK_AMARANTH_PLANKS);
		Registrar.add(Promenade.id("dark_amaranth_stairs"), DARK_AMARANTH_STAIRS);
		Registrar.add(Promenade.id("dark_amaranth_slab"), DARK_AMARANTH_SLAB);
		Registrar.add(Promenade.id("dark_amaranth_fence"), DARK_AMARANTH_FENCE);
		Registrar.add(Promenade.id("dark_amaranth_fence_gate"), DARK_AMARANTH_FENCE_GATE);
		Registrar.add(Promenade.id("dark_amaranth_door"), DARK_AMARANTH_DOOR);
		Registrar.add(Promenade.id("dark_amaranth_trapdoor"), DARK_AMARANTH_TRAPDOOR);
		Registrar.add(Promenade.id("dark_amaranth_button"), DARK_AMARANTH_BUTTON);
		Registrar.add(Promenade.id("dark_amaranth_pressure_plate"), DARK_AMARANTH_PRESSURE_PLATE);

		Registrar.add(Promenade.id("dark_amaranth_fungus"), DARK_AMARANTH_FUNGUS);
		Registrar.add(Promenade.id("potted_dark_amaranth_fungus"), POTTED_DARK_AMARANTH_FUNGUS);

		if(Promenade.CONFIG.biomes.dark_amaranth_forests_weight > 0) {
			TheEndBiomes.addHighlandsBiome(TALL_DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
			TheEndBiomes.addMidlandsBiome(TALL_DARK_AMARANTH_FOREST, DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
		}

		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> {
			e.addAfter(Blocks.WARPED_BUTTON,
					DARK_AMARANTH_STEM,
					DARK_AMARANTH_HYPHAE,
					STRIPPED_DARK_AMARANTH_STEM,
					STRIPPED_DARK_AMARANTH_HYPHAE,
					DARK_AMARANTH_PLANKS,
					DARK_AMARANTH_STAIRS,
					DARK_AMARANTH_SLAB,
					DARK_AMARANTH_FENCE,
					DARK_AMARANTH_FENCE_GATE,
					DARK_AMARANTH_DOOR,
					DARK_AMARANTH_TRAPDOOR,
					DARK_AMARANTH_PRESSURE_PLATE,
					DARK_AMARANTH_BUTTON);
		});
		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.WARPED_NYLIUM, BLACK_DYLIUM);
			e.addAfter(Blocks.WARPED_STEM, DARK_AMARANTH_STEM);
			e.addAfter(Blocks.WARPED_WART_BLOCK, DARK_AMARANTH_WART_BLOCK);
			e.addAfter(Blocks.WARPED_FUNGUS, DARK_AMARANTH_FUNGUS);
			e.addAfter(Blocks.WARPED_ROOTS, DARK_AMARANTH_ROOTS);
		});
		// TODO
		// ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.WARPED_HANGING_SIGN, DARK_AMARANTH_SIGN, DARK_AMARANTH_HANGING_SIGN));
	}
}
