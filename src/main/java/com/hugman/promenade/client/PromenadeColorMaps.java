package com.hugman.promenade.client;

import com.hugman.promenade.content.MapleContent;
import com.hugman.promenade.content.PalmContent;
import com.hugman.promenade.content.VanillaPilesContent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.BlockItem;

@Environment(EnvType.CLIENT)
public class PromenadeColorMaps {
	private static final int SAP_MAPLE_COLOR = 10931465;

	public static void registerColors() {
		registerBlockColors();
		registerItemColors();
	}

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor(), PalmContent.PALM_LEAVES, PalmContent.PALM_LEAF_PILE, VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE, VanillaPilesContent.MANGROVE_LEAF_PILE, MapleContent.SAP_MAPLE_LEAVES, MapleContent.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getSpruceColor(), VanillaPilesContent.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getBirchColor(), VanillaPilesContent.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if(world == null || pos == null) {
				return GrassColors.getColor(0.5, 1.0);
			}
			return BiomeColors.getGrassColor(world, pos);
		}, MapleContent.VERMILION_CARPETED_GRASS_BLOCK, MapleContent.FULVOUS_CARPETED_GRASS_BLOCK, MapleContent.MIKADO_CARPETED_GRASS_BLOCK);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((stack, layer) -> GrassColors.getColor(0.5D, 1.0D), PalmContent.PALM_LEAVES, PalmContent.PALM_LEAF_PILE, VanillaPilesContent.OAK_LEAF_PILE, VanillaPilesContent.SPRUCE_LEAF_PILE, VanillaPilesContent.BIRCH_LEAF_PILE, VanillaPilesContent.JUNGLE_LEAF_PILE, VanillaPilesContent.ACACIA_LEAF_PILE, VanillaPilesContent.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> FoliageColors.getMangroveColor(), VanillaPilesContent.MANGROVE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> SAP_MAPLE_COLOR, MapleContent.SAP_MAPLE_LEAVES, MapleContent.SAP_MAPLE_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleContent.VERMILION_CARPETED_GRASS_BLOCK).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleContent.VERMILION_CARPETED_GRASS_BLOCK);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleContent.FULVOUS_CARPETED_GRASS_BLOCK).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleContent.FULVOUS_CARPETED_GRASS_BLOCK);
		ColorProviderRegistry.ITEM.register((stack, layer) -> ColorProviderRegistry.BLOCK.get(MapleContent.MIKADO_CARPETED_GRASS_BLOCK).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, layer), MapleContent.MIKADO_CARPETED_GRASS_BLOCK);
	}
}
