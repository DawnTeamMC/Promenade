package com.hugman.promenade;

import com.hugman.newdawn.DawnBlockSettings;
import com.hugman.newdawn.DawnItemSettings;
import com.hugman.promenade.object.block.CarpetedGrassBlock;
import com.hugman.promenade.object.block.PileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;

public final class PromenadeFactory {
	public static Block leafPile() {
		return leafPile(BlockSoundGroup.GRASS);
	}

	public static Block leafPile(BlockSoundGroup soundGroup) {
		return new PileBlock(DawnBlockSettings.of(Material.PLANT)
				.item(DawnItemSettings.of(ItemGroup.DECORATIONS).compostingChance(0.3f)).flammability(30, 60)
				.strength(0.1f)
				.ticksRandomly()
				.sounds(soundGroup)
				.noCollision()
				.nonOpaque());
	}

	public static Block carpetedGrassBlock() {
		return new CarpetedGrassBlock(DawnBlockSettings.of(Material.SOLID_ORGANIC)
				.item(DawnItemSettings.of(ItemGroup.BUILDING_BLOCKS).compostingChance(0.3f))
				.ticksRandomly()
				.strength(0.6F)
				.sounds(BlockSoundGroup.GRASS));
	}
}
