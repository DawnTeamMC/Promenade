package com.hugman.promenade.init.client;

import com.hugman.promenade.init.BlockBundle;
import com.hugman.promenade.init.AutumnBundle;
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
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor(), BlockBundle.PALM_WOOD.getLeaves(), BlockBundle.PALM_LEAF_PILE, BlockBundle.OAK_LEAF_PILE, BlockBundle.JUNGLE_LEAF_PILE, BlockBundle.ACACIA_LEAF_PILE, BlockBundle.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getSpruceColor(), BlockBundle.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getBirchColor(), BlockBundle.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15232304, AutumnBundle.AUTUMN_OAK_LEAVES, AutumnBundle.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 16759362, AutumnBundle.AUTUMN_BIRCH_LEAVES, AutumnBundle.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15768259, BlockBundle.PINK_CHERRY_OAK_LEAVES, BlockBundle.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15786729, BlockBundle.WHITE_CHERRY_OAK_LEAVES, BlockBundle.WHITE_CHERRY_OAK_LEAF_PILE);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((item, layer) -> GrassColors.getColor(0.5D, 1.0D), BlockBundle.PALM_WOOD.getLeaves(), BlockBundle.PALM_LEAF_PILE, BlockBundle.OAK_LEAF_PILE, BlockBundle.SPRUCE_LEAF_PILE, BlockBundle.BIRCH_LEAF_PILE, BlockBundle.JUNGLE_LEAF_PILE, BlockBundle.ACACIA_LEAF_PILE, BlockBundle.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15232304, AutumnBundle.AUTUMN_OAK_LEAVES, AutumnBundle.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 16759362, AutumnBundle.AUTUMN_BIRCH_LEAVES, AutumnBundle.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15768259, BlockBundle.PINK_CHERRY_OAK_LEAVES, BlockBundle.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15786729, BlockBundle.WHITE_CHERRY_OAK_LEAVES, BlockBundle.WHITE_CHERRY_OAK_LEAF_PILE);
	}
}
