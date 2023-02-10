package fr.hugman.promenade;

import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.promenade.block.CarpetedGrassBlock;
import fr.hugman.promenade.block.PileBlock;
import fr.hugman.promenade.block.SnowyLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;

public final class PromenadeFactory {
	public static Block leafPile() {
		return leafPile(BlockSoundGroup.GRASS);
	}

	public static Block leafPile(BlockSoundGroup soundGroup) {
		return new PileBlock(DawnBlockSettings.of(Material.PLANT)
				.item(new DawnItemSettings().compostingChance(0.3f)).flammability(30, 60)
				.strength(0.1f)
				.ticksRandomly()
				.sounds(soundGroup)
				.noCollision()
				.nonOpaque());
	}

	public static Block carpetedGrassBlock() {
		return new CarpetedGrassBlock(DawnBlockSettings.of(Material.SOLID_ORGANIC)
				.item(new DawnItemSettings().compostingChance(0.3f))
				.ticksRandomly()
				.strength(0.6F)
				.sounds(BlockSoundGroup.GRASS));
	}

	public static SnowyLeavesBlock snowyLeaves() {
		return snowyLeaves(BlockSoundGroup.GRASS); //TODO: snowy sounds
	}

	public static SnowyLeavesBlock snowyLeaves(BlockSoundGroup soundGroup) {
		return new SnowyLeavesBlock(DawnBlockSettings.of(Material.LEAVES)
				.item(new DawnItemSettings().compostingChance(0.3f))
				.strength(0.2f)
				.ticksRandomly()
				.sounds(soundGroup)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
				.suffocates((state, world, pos) -> false)
				.blockVision((state, world, pos) -> false)
				.flammability(30, 60));
	}
}
