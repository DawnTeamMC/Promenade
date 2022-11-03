package com.hugman.promenade.init.client;

import com.hugman.promenade.init.MapleBundle;
import com.hugman.promenade.init.PalmBundle;
import com.hugman.promenade.init.VanillaPilesBundle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.BlockItem;

@Environment(EnvType.CLIENT)
public class PromenadeColorMaps {
	private static final int SAP_MAPLE_COLOR = 10931465;
	private static final int CARNELIAN_GROUND_COLOR = 10931465;

	public static void registerColors() {
		registerBlockColors();
		registerItemColors();
	}

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor(), PalmBundle.PALM_WOOD.getLeaves(), PalmBundle.PALM_LEAF_PILE, VanillaPilesBundle.OAK_LEAF_PILE, VanillaPilesBundle.JUNGLE_LEAF_PILE, VanillaPilesBundle.ACACIA_LEAF_PILE, VanillaPilesBundle.DARK_OAK_LEAF_PILE, VanillaPilesBundle.MANGROVE_LEAF_PILE, MapleBundle.SAP_MAPLE_LEAVES, MapleBundle.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getSpruceColor(), VanillaPilesBundle.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getBirchColor(), VanillaPilesBundle.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (world == null || pos == null) {
				return GrassColors.getColor(0.5, 1.0);
			}
			return BiomeColors.getGrassColor(world, pos);
		}, MapleBundle.VERMILION_CARPETED_GRASS_BLOCK, MapleBundle.FULVOUS_CARPETED_GRASS_BLOCK, MapleBundle.MIKADO_CARPETED_GRASS_BLOCK);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((stack, layer) -> GrassColors.getColor(0.5D, 1.0D), PalmBundle.PALM_WOOD.getLeaves(), PalmBundle.PALM_LEAF_PILE, VanillaPilesBundle.OAK_LEAF_PILE, VanillaPilesBundle.SPRUCE_LEAF_PILE, VanillaPilesBundle.BIRCH_LEAF_PILE, VanillaPilesBundle.JUNGLE_LEAF_PILE, VanillaPilesBundle.ACACIA_LEAF_PILE, VanillaPilesBundle.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> FoliageColors.getMangroveColor(), VanillaPilesBundle.MANGROVE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> SAP_MAPLE_COLOR, MapleBundle.SAP_MAPLE_LEAVES, MapleBundle.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleBundle.VERMILION_CARPETED_GRASS_BLOCK).getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleBundle.VERMILION_CARPETED_GRASS_BLOCK);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleBundle.FULVOUS_CARPETED_GRASS_BLOCK).getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleBundle.FULVOUS_CARPETED_GRASS_BLOCK);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleBundle.MIKADO_CARPETED_GRASS_BLOCK).getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleBundle.MIKADO_CARPETED_GRASS_BLOCK);
	}
}
