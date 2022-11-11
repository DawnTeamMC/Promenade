package com.hugman.promenade;

import com.hugman.newdawn.DawnFactory;
import com.hugman.newdawn.builder.BlockBuilder;
import com.hugman.promenade.object.block.CarpetedGrassBlock;
import com.hugman.promenade.object.block.PileBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class PromenadeFactory {
	public static BlockBuilder leafPile(Identifier id) {
		return leafPile(id, BlockSoundGroup.GRASS);
	}

	public static BlockBuilder leafPile(Identifier id, BlockSoundGroup soundGroup) {
		return DawnFactory.block(id, new PileBlock(FabricBlockSettings.of(Material.PLANT)
						.strength(0.1f)
						.ticksRandomly()
						.sounds(soundGroup)
						.noCollision()
						.nonOpaque()))
				.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.3f))
				.flammability(30, 60);
	}

	public static BlockBuilder carpetedGrassBlock(Identifier id) {
		return DawnFactory.block(id, new CarpetedGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)
						.ticksRandomly()
						.strength(0.6F)
						.sounds(BlockSoundGroup.GRASS)))
				.item(ItemGroup.BUILDING_BLOCKS, b -> b.compostingChance(0.3f));
	}
}
