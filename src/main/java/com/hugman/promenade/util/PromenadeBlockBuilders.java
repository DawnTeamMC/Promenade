package com.hugman.promenade.util;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.promenade.object.block.CarpetedGrassBlock;
import com.hugman.promenade.object.block.PlantPileBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;

public final class PromenadeBlockBuilders
{
	public static BlockCreator.Builder plantPile() {
		return plantPile(null);
	}

	public static BlockCreator.Builder plantPile(String name) {
		return new BlockCreator.Builder()
				.name(name)
				.provider(PlantPileBlock::new)
				.settings(FabricBlockSettings.of(Material.PLANT).breakInstantly().sounds(BlockSoundGroup.GRASS).noCollision().nonOpaque())
				.itemGroup(ItemGroup.DECORATIONS)
				.compostingChance(0.65f)
				.flammability(60, 100)
				.render(BlockCreator.Render.CUTOUT_MIPPED);
	}

	public static BlockCreator.Builder leafPile(String name) {
		return leafPile(name, BlockSoundGroup.GRASS);
	}


	public static BlockCreator.Builder carpetedGrassBlock(String name) {
		return new BlockCreator.Builder()
				.name(name)
				.provider(CarpetedGrassBlock::new)
				.settings(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS))
				.itemGroup(ItemGroup.BUILDING_BLOCKS)
				.compostingChance(0.3f)
				.render(BlockCreator.Render.CUTOUT_MIPPED);
	}

	public static BlockCreator.Builder leafPile(String name, BlockSoundGroup sounds) {
		return pile(name, sounds, 0.1F);
	}

	public static BlockCreator.Builder pile(String name, BlockSoundGroup sounds, float strength) {
		return new BlockCreator.Builder()
				.name(name)
				.provider(PlantPileBlock::new)
				.settings(FabricBlockSettings.of(Material.PLANT).strength(strength).sounds(sounds).noCollision().nonOpaque())
				.itemGroup(ItemGroup.DECORATIONS)
				.flammability(30, 60)
				.compostingChance(0.3f)
				.render(BlockCreator.Render.CUTOUT_MIPPED);
	}
}
