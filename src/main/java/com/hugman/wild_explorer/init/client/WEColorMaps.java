package com.hugman.wild_explorer.init.client;

import com.hugman.wild_explorer.init.WEBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;

@Environment(EnvType.CLIENT)
public class WEColorMaps {
	public static void registerColors() {
		registerBlockColors();
		registerItemColors();
	}

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor(), WEBlocks.PALM_WOOD.getLeaves(), WEBlocks.PALM_LEAF_PILE, WEBlocks.OAK_LEAF_PILE, WEBlocks.JUNGLE_LEAF_PILE, WEBlocks.ACACIA_LEAF_PILE, WEBlocks.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getSpruceColor(), WEBlocks.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getBirchColor(), WEBlocks.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15232304, WEBlocks.AUTUMN_OAK_LEAVES, WEBlocks.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15645495, WEBlocks.AUTUMN_BIRCH_LEAVES, WEBlocks.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15768259, WEBlocks.PINK_CHERRY_OAK_LEAVES, WEBlocks.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15786729, WEBlocks.WHITE_CHERRY_OAK_LEAVES, WEBlocks.WHITE_CHERRY_OAK_LEAF_PILE);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((item, layer) -> GrassColors.getColor(0.5D, 1.0D), WEBlocks.PALM_WOOD.getLeaves(), WEBlocks.PALM_LEAF_PILE, WEBlocks.OAK_LEAF_PILE, WEBlocks.SPRUCE_LEAF_PILE, WEBlocks.BIRCH_LEAF_PILE, WEBlocks.JUNGLE_LEAF_PILE, WEBlocks.ACACIA_LEAF_PILE, WEBlocks.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15232304, WEBlocks.AUTUMN_OAK_LEAVES, WEBlocks.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15645495, WEBlocks.AUTUMN_BIRCH_LEAVES, WEBlocks.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15768259, WEBlocks.PINK_CHERRY_OAK_LEAVES, WEBlocks.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15786729, WEBlocks.WHITE_CHERRY_OAK_LEAVES, WEBlocks.WHITE_CHERRY_OAK_LEAF_PILE);
	}
}
