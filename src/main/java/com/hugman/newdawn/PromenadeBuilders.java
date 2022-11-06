package com.hugman.newdawn;

import com.hugman.newdawn.builder.BlockBuilder;
import com.hugman.promenade.object.block.CarpetedGrassBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class PromenadeBuilders {
	public static BlockBuilder carpetedGrassBlock(Identifier id) {
		return BlockBuilder.of(id, new CarpetedGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)
						.ticksRandomly()
						.strength(0.6F)
						.sounds(BlockSoundGroup.GRASS)))
				.item(ItemGroup.BUILDING_BLOCKS, b -> b.compostingChance(0.3f));
	}
}
