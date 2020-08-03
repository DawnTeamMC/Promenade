package com.hugman.wild_explorer.init.client;

import com.hugman.wild_explorer.init.WEBlockPack;
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
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor();
		}, WEBlockPack.PALM_WOOD.getLeaves(), WEBlockPack.PALM_WOOD.getLeafPile());
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return 15232304;
		}, WEBlockPack.AUTUMN_OAK_LEAVES.getLeaves(), WEBlockPack.AUTUMN_OAK_LEAVES.getLeafPile());
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return 15645495;
		}, WEBlockPack.AUTUMN_BIRCH_LEAVES.getLeaves(), WEBlockPack.AUTUMN_BIRCH_LEAVES.getLeafPile());
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return 15768259;
		}, WEBlockPack.PINK_CHERRY_OAK_LEAVES.getLeaves(), WEBlockPack.PINK_CHERRY_OAK_LEAVES.getLeafPile());
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return 15786729;
		}, WEBlockPack.WHITE_CHERRY_OAK_LEAVES.getLeaves(), WEBlockPack.WHITE_CHERRY_OAK_LEAVES.getLeafPile());
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((item, layer) ->
		{
			return GrassColors.getColor(0.5D, 1.0D);
		}, WEBlockPack.PALM_WOOD.getLeaves(), WEBlockPack.PALM_WOOD.getLeafPile());
		ColorProviderRegistry.ITEM.register((item, layer) ->
		{
			return 15232304;
		}, WEBlockPack.AUTUMN_OAK_LEAVES.getLeaves(), WEBlockPack.AUTUMN_OAK_LEAVES.getLeafPile());
		ColorProviderRegistry.ITEM.register((item, layer) ->
		{
			return 15645495;
		}, WEBlockPack.AUTUMN_BIRCH_LEAVES.getLeaves(), WEBlockPack.AUTUMN_BIRCH_LEAVES.getLeafPile());
		ColorProviderRegistry.ITEM.register((item, layer) ->
		{
			return 15768259;
		}, WEBlockPack.PINK_CHERRY_OAK_LEAVES.getLeaves(), WEBlockPack.PINK_CHERRY_OAK_LEAVES.getLeafPile());
		ColorProviderRegistry.ITEM.register((item, layer) ->
		{
			return 15786729;
		}, WEBlockPack.WHITE_CHERRY_OAK_LEAVES.getLeaves(), WEBlockPack.WHITE_CHERRY_OAK_LEAVES.getLeafPile());
	}
}
