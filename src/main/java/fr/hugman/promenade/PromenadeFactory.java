package fr.hugman.promenade;

import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.promenade.block.CarpetedGrassBlock;
import fr.hugman.promenade.block.PileBlock;
import fr.hugman.promenade.block.SnowyLeavesBlock;
import fr.hugman.promenade.registry.content.GlaglaglaContent;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;

public final class PromenadeFactory {
	public static Block leafPile() {
		return leafPile(BlockSoundGroup.GRASS, MapColor.DARK_GREEN);
	}

	public static Block leafPile(MapColor color) {
		return leafPile(BlockSoundGroup.GRASS, color);
	}

	public static Block leafPile(BlockSoundGroup soundGroup) {
		return leafPile(soundGroup, MapColor.DARK_GREEN);
	}

	public static Block leafPile(BlockSoundGroup soundGroup, MapColor color) {
		return new PileBlock(DawnBlockSettings.create()
				.item(new DawnItemSettings().compostingChance(0.3f))
				.mapColor(color)
				.burnable(30, 60)
				.strength(0.1f)
				.ticksRandomly()
				.sounds(soundGroup)
				.noCollision()
				.nonOpaque());
	}

	public static Block carpetedGrassBlock(MapColor color) {
		return new CarpetedGrassBlock(DawnBlockSettings.create()
				.item(new DawnItemSettings().compostingChance(0.3f))
				.mapColor(color)
				.ticksRandomly()
				.strength(0.6F)
				.sounds(BlockSoundGroup.GRASS));
	}

	public static SnowyLeavesBlock snowyLeaves() {
		return snowyLeaves(GlaglaglaContent.SNOWY_LEAVES_SOUNDS);
	}

	public static SnowyLeavesBlock snowyLeaves(BlockSoundGroup soundGroup) {
		return new SnowyLeavesBlock(DawnBlockSettings.create()
				.item(new DawnItemSettings().compostingChance(0.3f))
				.strength(0.2f)
				.ticksRandomly()
				.sounds(soundGroup)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
				.suffocates((state, world, pos) -> false)
				.blockVision((state, world, pos) -> false)
				.burnable(30, 60)
				.pistonBehavior(PistonBehavior.DESTROY)
				.solidBlock((state, world, pos) -> false));
	}
}
