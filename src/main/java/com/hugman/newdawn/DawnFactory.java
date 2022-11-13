package com.hugman.newdawn;

import com.hugman.dawn.api.object.block.DawnFungusBlock;
import com.hugman.dawn.api.object.block.DawnSaplingBlock;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public final class DawnFactory {
	/*===================*/
	/*   REGISTRY KEYS   */
	/*===================*/

	public static RegistryKey<Biome> biomeKey(Identifier id) {
		return RegistryKey.of(Registry.BIOME_KEY, id);
	}

	public static RegistryKey<PlacedFeature> placedFeatureKey(Identifier id) {
		return RegistryKey.of(Registry.PLACED_FEATURE_KEY, id);
	}

	public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey(Identifier id) {
		return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, id);
	}

	/*================*/
	/*   WOOD STUFF   */
	/*================*/

	public static Block planks(boolean isNether, MapColor color) {
		return new Block(DawnFactory.planksSettings(isNether, color));
	}

	public static DawnBlockSettings planksSettings(boolean isNether, MapColor color) {
		DawnBlockSettings settings = DawnBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, color)
				.strength(2.0f, 3.0f)
				.sounds(BlockSoundGroup.WOOD) // todo: this has changed in 1.19.3 for nether
				.item(ItemGroup.BUILDING_BLOCKS);
		if(isNether) settings.flammability(5, 20);
		return settings;
	}

	public static DawnBlockSettings logSettings(boolean isNether, MapColor woodColor, MapColor barkColor) {
		DawnBlockSettings settings = DawnBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor)
				.strength(2.0F)
				.sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD)
				.item(ItemGroup.BUILDING_BLOCKS);
		if(isNether) settings.flammability(5, 5);
		return settings;
	}

	public static DawnBlockSettings logSettings(boolean isNether, MapColor color) {
		DawnBlockSettings settings = DawnBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, color)
				.strength(2.0F)
				.sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD)
				.item(ItemGroup.BUILDING_BLOCKS);
		if(isNether) settings.flammability(5, 5);
		return settings;
	}

	public static Block stairs(Block baseBlock) {
		return new StairsBlock(baseBlock.getDefaultState(), DawnBlockSettings.copy(baseBlock));
	}

	public static Block slab(Block baseBlock) {
		return new SlabBlock(DawnBlockSettings.copy(baseBlock));
	}

	public static Block pressurePlate(Block baseBlock) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock)
				.strength(0.5f)
				.noCollision()
				.item(ItemGroup.REDSTONE);
		return new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, settings);
	}

	public static Block woodenButton(boolean isNether) {
		return new WoodenButtonBlock(DawnBlockSettings.of(Material.DECORATION)
				.strength(0.5f)
				.noCollision()
				.sounds(BlockSoundGroup.WOOD) // todo: this has changed in 1.19.3 for nether
				.item(ItemGroup.REDSTONE));
	}

	public static Block fence(boolean isNether, Block baseBlock) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock).item(DawnItemSettings.of(ItemGroup.DECORATIONS).fuelTime(isNether ? 0 : 300));
		if(isNether) settings.flammability(5, 20);
		return new FenceBlock(settings);
	}

	public static Block fenceGate(boolean isNether, Block baseBlock) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock).item(DawnItemSettings.of(ItemGroup.REDSTONE).fuelTime(isNether ? 0 : 300));
		if(isNether) settings.flammability(5, 20);
		return new FenceGateBlock(settings);
	}

	public static Block trapdoor(Block baseBlock) {
		DawnBlockSettings settings = DawnBlockSettings.copy(baseBlock)
				.strength(3.0f)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> false) // Blocks::never
				.item(ItemGroup.REDSTONE);
		return new TrapdoorBlock(settings);
	}

	public static Block door(Block baseBlock) {
		return new DoorBlock(DawnBlockSettings.copy(baseBlock)
				.strength(3.0f)
				.nonOpaque()
				.item(ItemGroup.REDSTONE));
	}

	public static Block sapling(SaplingGenerator generator) {
		return new SaplingBlock(generator, DawnBlockSettings.of(Material.PLANT)
				.sounds(BlockSoundGroup.GRASS)
				.breakInstantly()
				.noCollision()
				.ticksRandomly()
				.item(DawnItemSettings.of(ItemGroup.DECORATIONS).compostingChance(0.3f)));
	}

	public static Block sapling(SaplingGenerator generator, Predicate<BlockState> saplingSoilPredicate) {
		return new DawnSaplingBlock(DawnBlockSettings.of(Material.PLANT)
				.sounds(BlockSoundGroup.GRASS)
				.breakInstantly()
				.noCollision()
				.ticksRandomly()
				.item(DawnItemSettings.of(ItemGroup.DECORATIONS).compostingChance(0.3f)), generator, saplingSoilPredicate);
	}

	public static Block fungus(RegistryKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn) {
		return new DawnFungusBlock(DawnBlockSettings.of(Material.PLANT)
				.item(DawnItemSettings.of(ItemGroup.DECORATIONS).compostingChance(0.65f))
				.sounds(BlockSoundGroup.FUNGUS)
				.breakInstantly()
				.noCollision(), featureKey, canPlantOn, canGrowOn);
	}

	public static Block potted(Block content) {
		return new FlowerPotBlock(content, DawnBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque().luminance(content.getDefaultState().getLuminance()));
	}

	public static Block leaves() {
		return leaves(BlockSoundGroup.GRASS);
	}

	public static Block leaves(BlockSoundGroup soundGroup) {
		return new LeavesBlock(DawnBlockSettings.of(Material.LEAVES)
				.item(DawnItemSettings.of(ItemGroup.DECORATIONS).compostingChance(0.3f))
				.strength(0.2f)
				.ticksRandomly()
				.sounds(soundGroup)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
				.suffocates((state, world, pos) -> false)
				.blockVision((state, world, pos) -> false)
				.flammability(30, 60));
	}

	/*============*/
	/*   OTHERS   */
	/*============*/

	// TODO: add this to a new Entity Type builder
	public static Item spawnEgg(EntityType<? extends MobEntity> entity, int primaryColor, int secondaryColor) {
		return new SpawnEggItem(entity, primaryColor, secondaryColor, DawnItemSettings.of(ItemGroup.MISC));
	}
}
