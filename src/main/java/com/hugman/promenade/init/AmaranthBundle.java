package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.block.PlantBundle;
import com.hugman.dawn.api.creator.bundle.block.WoodBundle;
import com.hugman.dawn.api.object.block.DawnFungusBlock;
import com.hugman.dawn.api.object.block.DawnRootsBlock;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.object.block.DyliumBlock;
import com.hugman.promenade.util.WorldGenUtil;
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

public class AmaranthBundle extends PromenadeBundle {
	public static final RegistryKey<ConfiguredFeature<?, ?>> PLANTED_AMARANTH_FUNGUS = WorldGenUtil.configuredFeatureKey("amaranth_fungus/planted");

	public static final Block BLACK_DYLIUM = add(new BlockCreator.Builder("black_dylium", DyliumBlock::new, FabricBlockSettings.of(Material.STONE, MapColor.DULL_RED).requiresTool().strength(3.0F, 9.0F).sounds(BlockSoundGroup.NYLIUM).ticksRandomly()).itemGroup(ItemGroup.BUILDING_BLOCKS).build());
	public static final Block DARK_AMARANTH_WART_BLOCK = add(new BlockCreator.Builder("dark_amaranth_wart_block", Block::new, FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK)).itemGroup(ItemGroup.BUILDING_BLOCKS).build());
	public static final Block DARK_AMARANTH_ROOTS = add(new BlockCreator.Builder("dark_amaranth_roots", s -> new DawnRootsBlock(s, state -> state.isIn(PromenadeTags.Blocks.DARK_AMARANTH_ROOTS_PLACEABLE_ON)), FabricBlockSettings.of(Material.NETHER_SHOOTS, MapColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F).build());
	public static final WoodBundle DARK_AMARANTH_WOOD = creator(new WoodBundle.Builder("dark_amaranth", MapColor.LIGHT_GRAY, MapColor.LIGHT_GRAY, MapColor.DARK_DULL_PINK, true).build());
	public static final PlantBundle DARK_AMARANTH_FUNGUS = creator(new PlantBundle(new BlockCreator.Builder("dark_amaranth_fungus", settings -> new DawnFungusBlock(settings, PLANTED_AMARANTH_FUNGUS, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_PLACEABLE_ON, PromenadeTags.Blocks.DARK_AMARANTH_FUNGUS_GROWABLE_ON), DefaultBlockSettings.FUNGUS).compostingChance(0.65F).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED)));

	public static final RegistryKey<Biome> DARK_AMARANTH_FOREST = WorldGenUtil.biomeKey("dark_amaranth_forest");
	public static final RegistryKey<Biome> TALL_DARK_AMARANTH_FOREST = WorldGenUtil.biomeKey("tall_dark_amaranth_forest");

	public static void addToGen() {
		if(Promenade.CONFIG.biomes.dark_amaranth_forests_weight > 0) {
			TheEndBiomes.addHighlandsBiome(TALL_DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
			TheEndBiomes.addMidlandsBiome(TALL_DARK_AMARANTH_FOREST, DARK_AMARANTH_FOREST, Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 10.0D);
		}
	}
}
