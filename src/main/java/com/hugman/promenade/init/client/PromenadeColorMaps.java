package com.hugman.promenade.init.client;

import com.hugman.promenade.init.AutumnBundle;
import com.hugman.promenade.init.CherryBundle;
import com.hugman.promenade.init.PalmBundle;
import com.hugman.promenade.init.VanillaPilesBundle;
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
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor(), PalmBundle.PALM_WOOD.getLeaves(), PalmBundle.PALM_LEAF_PILE, VanillaPilesBundle.OAK_LEAF_PILE, VanillaPilesBundle.JUNGLE_LEAF_PILE, VanillaPilesBundle.ACACIA_LEAF_PILE, VanillaPilesBundle.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getSpruceColor(), VanillaPilesBundle.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> FoliageColors.getBirchColor(), VanillaPilesBundle.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15232304, AutumnBundle.AUTUMN_OAK_LEAVES, AutumnBundle.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 16759362, AutumnBundle.AUTUMN_BIRCH_LEAVES, AutumnBundle.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15768259, CherryBundle.PINK_CHERRY_OAK_LEAVES, CherryBundle.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> 15786729, CherryBundle.WHITE_CHERRY_OAK_LEAVES, CherryBundle.WHITE_CHERRY_OAK_LEAF_PILE);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((item, layer) -> GrassColors.getColor(0.5D, 1.0D), PalmBundle.PALM_WOOD.getLeaves(), PalmBundle.PALM_LEAF_PILE, VanillaPilesBundle.OAK_LEAF_PILE, VanillaPilesBundle.SPRUCE_LEAF_PILE, VanillaPilesBundle.BIRCH_LEAF_PILE, VanillaPilesBundle.JUNGLE_LEAF_PILE, VanillaPilesBundle.ACACIA_LEAF_PILE, VanillaPilesBundle.DARK_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15232304, AutumnBundle.AUTUMN_OAK_LEAVES, AutumnBundle.AUTUMN_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 16759362, AutumnBundle.AUTUMN_BIRCH_LEAVES, AutumnBundle.AUTUMN_BIRCH_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15768259, CherryBundle.PINK_CHERRY_OAK_LEAVES, CherryBundle.PINK_CHERRY_OAK_LEAF_PILE);
		ColorProviderRegistry.ITEM.register((item, layer) -> 15786729, CherryBundle.WHITE_CHERRY_OAK_LEAVES, CherryBundle.WHITE_CHERRY_OAK_LEAF_PILE);
	}
}
