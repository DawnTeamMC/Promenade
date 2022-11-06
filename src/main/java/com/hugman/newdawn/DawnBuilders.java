package com.hugman.newdawn;

import com.hugman.newdawn.builder.BlockBuilder;
import com.hugman.promenade.object.block.PileBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public final class DawnBuilders {
	/*==========================*/
	/*   OVERWORLD WOOD STUFF   */
	/*==========================*/
	public static BlockBuilder planks(Identifier id, MapColor color) {
		return BlockBuilder.of(id, new Block(FabricBlockSettings.of(Material.WOOD, color)
						.strength(2.0f, 3.0f)
						.sounds(BlockSoundGroup.WOOD)))
				.item(ItemGroup.BUILDING_BLOCKS)
				.flammability(5, 20);
	}

	public static AbstractBlock.Settings logSettings(MapColor woodColor, MapColor barkColor) {
		return FabricBlockSettings.of(Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor)
				.strength(2.0F)
				.sounds(BlockSoundGroup.WOOD);
	}

	public static AbstractBlock.Settings logSettings(MapColor color) {
		return FabricBlockSettings.of(Material.WOOD, color)
				.strength(2.0F)
				.sounds(BlockSoundGroup.WOOD);
	}

	public static BlockBuilder log(Identifier id, MapColor insideColor, MapColor barkColor) {
		return DawnBuilders.log(id, new PillarBlock(DawnBuilders.logSettings(insideColor, barkColor)));
	}

	public static BlockBuilder log(Identifier id, MapColor color) {
		return DawnBuilders.log(id, new PillarBlock(DawnBuilders.logSettings(color)));
	}

	public static BlockBuilder log(Identifier id, Block block) {
		return BlockBuilder.of(id, block)
				.item(ItemGroup.BUILDING_BLOCKS)
				.flammability(5, 5);
	}

	public static BlockBuilder stairs(Identifier id, Block planks) {
		return BlockBuilder.of(id, new StairsBlock(planks.getDefaultState(), FabricBlockSettings.copy(planks)))
				.item(ItemGroup.BUILDING_BLOCKS)
				.flammability(5, 20);
	}

	public static BlockBuilder slab(Identifier id, Block planks) {
		return BlockBuilder.of(id, new SlabBlock(FabricBlockSettings.copy(planks)))
				.item(ItemGroup.BUILDING_BLOCKS)
				.flammability(5, 20);
	}

	public static BlockBuilder trapdoor(Identifier id, Block planks) {
		return BlockBuilder.of(id, new TrapdoorBlock(FabricBlockSettings.copy(planks)
						.strength(3.0f)
						.nonOpaque()
						.allowsSpawning((state, world, pos, type) -> false))) // Blocks::never
				.item(ItemGroup.REDSTONE)
				.flammability(5, 20);
	}

	public static BlockBuilder pressurePlate(Identifier id, Block planks) {
		return BlockBuilder.of(id, new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(planks)
						.strength(0.5f)
						.noCollision()
						.sounds(BlockSoundGroup.WOOD)))
				.item(ItemGroup.REDSTONE)
				.flammability(5, 20);
	}

	public static BlockBuilder button(Identifier id) {
		return BlockBuilder.of(id, new WoodenButtonBlock(FabricBlockSettings.of(Material.DECORATION)
						.strength(0.5f)
						.noCollision()
						.sounds(BlockSoundGroup.WOOD)))
				.item(ItemGroup.REDSTONE)
				.flammability(5, 20);
	}

	public static BlockBuilder fence(Identifier id, Block planks) {
		return BlockBuilder.of(id, new FenceBlock(FabricBlockSettings.copy(planks)))
				.item(ItemGroup.DECORATIONS, b -> b.fuelTime(300))
				.flammability(5, 20);
	}

	public static BlockBuilder fenceGate(Identifier id, Block planks) {
		return BlockBuilder.of(id, new FenceGateBlock(FabricBlockSettings.copy(planks)))
				.item(ItemGroup.REDSTONE, b -> b.fuelTime(300))
				.flammability(5, 20);
	}

	public static BlockBuilder door(Identifier id, Block planks) {
		return BlockBuilder.of(id, new DoorBlock(FabricBlockSettings.copy(planks)
						.strength(3.0f)
						.nonOpaque()))
				.item(ItemGroup.REDSTONE);
	}

	public static BlockBuilder sapling(Identifier id, SaplingGenerator generator) {
		return BlockBuilder.of(id, new SaplingBlock(generator, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).breakInstantly().noCollision().ticksRandomly()))
				.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.3f));
	}

	public static BlockBuilder potted(Identifier id, Block content) {
		return BlockBuilder.of(id, new FlowerPotBlock(content, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque().luminance(content.getDefaultState().getLuminance())));
	}

	public static BlockBuilder potted(Block content) {
		Identifier contentId = Registry.BLOCK.getId(content);
		return potted(new Identifier(contentId.getNamespace(), "potted_" + contentId.getPath()), content);
	}

	public static BlockBuilder leaves(Identifier id) {
		return leaves(id, BlockSoundGroup.GRASS);
	}

	public static BlockBuilder leaves(Identifier id, BlockSoundGroup soundGroup) {
		return BlockBuilder.of(id, new LeavesBlock(FabricBlockSettings.of(Material.LEAVES)
						.strength(0.2f)
						.ticksRandomly()
						.sounds(soundGroup)
						.nonOpaque()
						.allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
						.suffocates((state, world, pos) -> false)
						.blockVision((state, world, pos) -> false)))
				.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.3f))
				.flammability(30, 60);
	}

	public static BlockBuilder leafPile(Identifier id) {
		return leafPile(id, BlockSoundGroup.GRASS);
	}

	public static BlockBuilder leafPile(Identifier id, BlockSoundGroup soundGroup) {
		return BlockBuilder.of(id, new PileBlock(FabricBlockSettings.of(Material.PLANT)
						.strength(0.1f)
						.ticksRandomly()
						.sounds(soundGroup)
						.noCollision()
						.nonOpaque()))
				.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.3f))
				.flammability(30, 60);
	}
}
