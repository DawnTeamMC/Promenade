package com.hugman.promenade.util;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.PlantPileBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;

public final class BlockBuilders {
	public static final BlockCreator.Builder LEAF_PILE = new BlockCreator.Builder().provider(PlantPileBlock::new).settings(FabricBlockSettings.of(Material.LEAVES).strength(0.1F).ticksRandomly().sounds(BlockSoundGroup.GRASS).noCollision().nonOpaque()).itemGroup(ItemGroup.DECORATIONS).flammability(30, 60).compostingChance(0.3f).render(BlockCreator.Render.CUTOUT_MIPPED);
	public static final BlockCreator.Builder PLANT_PILE = new BlockCreator.Builder().provider(PlantPileBlock::new).settings(FabricBlockSettings.of(Material.PLANT).breakInstantly().sounds(BlockSoundGroup.GRASS).noCollision()).itemGroup(ItemGroup.DECORATIONS).compostingChance(0.65f).flammability(60, 100).render(BlockCreator.Render.CUTOUT_MIPPED);
}
