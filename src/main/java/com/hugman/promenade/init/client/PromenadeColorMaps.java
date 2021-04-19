package com.hugman.promenade.init.client;

import com.hugman.promenade.init.PromenadeBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;

@Environment(EnvType.CLIENT)
public class PromenadeColorMaps {
	public static void registerColors() {
		registerBlockColors();
		registerItemColors();
	}

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor(), PromenadeBlocks.PALM_WOOD.getLeaves(), PromenadeBlocks.PALM_LEAF_PILE, PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getSpruceColor(), PromenadeBlocks.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getBirchColor(), PromenadeBlocks.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15232304, PromenadeBlocks.AUTUMN_OAK_LEAVES, PromenadeBlocks.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15645495, PromenadeBlocks.AUTUMN_BIRCH_LEAVES, PromenadeBlocks.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15768259, PromenadeBlocks.PINK_CHERRY_OAK_LEAVES, PromenadeBlocks.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15786729, PromenadeBlocks.WHITE_CHERRY_OAK_LEAVES, PromenadeBlocks.WHITE_CHERRY_OAK_LEAF_PILE);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((item, layer) -> GrassColors.getColor(0.5D, 1.0D), PromenadeBlocks.PALM_WOOD.getLeaves(), PromenadeBlocks.PALM_LEAF_PILE, PromenadeBlocks.OAK_LEAF_PILE, PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeBlocks.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15232304, PromenadeBlocks.AUTUMN_OAK_LEAVES, PromenadeBlocks.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15645495, PromenadeBlocks.AUTUMN_BIRCH_LEAVES, PromenadeBlocks.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15768259, PromenadeBlocks.PINK_CHERRY_OAK_LEAVES, PromenadeBlocks.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15786729, PromenadeBlocks.WHITE_CHERRY_OAK_LEAVES, PromenadeBlocks.WHITE_CHERRY_OAK_LEAF_PILE);
	}
}
