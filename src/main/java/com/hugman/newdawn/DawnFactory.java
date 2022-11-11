package com.hugman.newdawn;

import com.hugman.dawn.api.object.block.DawnFungusBlock;
import com.hugman.dawn.api.object.block.DawnSaplingBlock;
import com.hugman.newdawn.builder.BlockBuilder;
import com.hugman.newdawn.builder.ItemBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
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
	/*   BLOCK DIRECTS   */
	/*===================*/
	public static BlockBuilder block(Identifier id, Block block) {
		return BlockBuilder.of(id, block);
	}

	public static BlockBuilder block(Identifier id, Block.Settings settings) {
		return BlockBuilder.of(id, new Block(settings));
	}

	/*==================*/
	/*   ITEM DIRECTS   */
	/*==================*/

	public static ItemBuilder item(Identifier id, Item item) {
		return ItemBuilder.of(id, item);
	}

	public static ItemBuilder item(Identifier id, Item.Settings settings) {
		return item(id, new Item(settings));
	}

	public static ItemBuilder item(Identifier id, ItemGroup group) {
		return item(id, new Item.Settings().group(group));
	}

	/*===================*/
	/*   OTHER DIRECTS   */
	/*===================*/

	public static SoundEvent sound(Identifier id) {
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}

	public static DefaultParticleType particle(Identifier id) {
		return Registry.register(Registry.PARTICLE_TYPE, id, FabricParticleTypes.simple());
	}

	public static <T extends Entity> EntityType<T> entity(Identifier id, EntityType<T> type) {
		return Registry.register(Registry.ENTITY_TYPE, id, type);
	}

	public static RegistryKey<Biome> biomeKey(Identifier id) {
		return RegistryKey.of(Registry.BIOME_KEY, id);
	}

	public static RegistryKey<PlacedFeature> placedFeatureKey(Identifier id) {
		return RegistryKey.of(Registry.PLACED_FEATURE_KEY, id);
	}

	public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey(Identifier id) {
		return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, id);
	}

	/*==========================*/
	/*   WOOD STUFF   */
	/*==========================*/

	public static BlockBuilder planks(Identifier id, MapColor color, boolean isNether) {
		BlockBuilder builder = block(id, new Block(FabricBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, color)
				.strength(2.0f, 3.0f)
				.sounds(BlockSoundGroup.WOOD))) // todo: this has changed in 1.19.3 for nether
				.item(ItemGroup.BUILDING_BLOCKS);
		if(isNether) builder.flammability(5, 20);
		return builder;
	}

	public static AbstractBlock.Settings logSettings(MapColor woodColor, MapColor barkColor, boolean isNether) {
		return FabricBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? woodColor : barkColor)
				.strength(2.0F)
				.sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD);
	}

	public static AbstractBlock.Settings logSettings(MapColor color, boolean isNether) {
		return FabricBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, color)
				.strength(2.0F)
				.sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD);
	}

	public static BlockBuilder log(Identifier id, MapColor insideColor, MapColor barkColor, boolean isNether) {
		return DawnFactory.log(id, new PillarBlock(DawnFactory.logSettings(insideColor, barkColor, isNether)), isNether);
	}

	public static BlockBuilder log(Identifier id, MapColor color, boolean isNether) {
		return DawnFactory.log(id, new PillarBlock(DawnFactory.logSettings(color, isNether)), isNether);
	}

	public static BlockBuilder log(Identifier id, Block block, boolean isNether) {
		BlockBuilder builder = block(id, block).item(ItemGroup.BUILDING_BLOCKS);
		if(isNether) builder.flammability(5, 5);
		return builder;
	}

	public static BlockBuilder stairs(Identifier id, Block planks, boolean isNether) {
		BlockBuilder builder = block(id, new StairsBlock(planks.getDefaultState(), FabricBlockSettings.copy(planks))).item(ItemGroup.BUILDING_BLOCKS);
		if (isNether) builder.flammability(5, 20);
		return builder;
	}

	public static BlockBuilder slab(Identifier id, Block planks, boolean isNether) {
		BlockBuilder builder = block(id, new SlabBlock(FabricBlockSettings.copy(planks))).item(ItemGroup.BUILDING_BLOCKS);
		if(isNether) builder.flammability(5, 20);
		return builder;
	}

	public static BlockBuilder trapdoor(Identifier id, Block planks, boolean isNether) {
		BlockBuilder builder = block(id, new TrapdoorBlock(FabricBlockSettings.copy(planks)
				.strength(3.0f)
				.nonOpaque()
				.allowsSpawning((state, world, pos, type) -> false))) // Blocks::never
				.item(ItemGroup.REDSTONE);
		if(isNether) builder.flammability(5, 20);
		return builder;
	}

	public static BlockBuilder pressurePlate(Identifier id, Block planks, boolean isNether) {
		BlockBuilder builder = block(id, new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(planks)
				.strength(0.5f)
				.noCollision()))
				.item(ItemGroup.REDSTONE);
		if(isNether) builder.flammability(5, 20);
		return builder;
	}

	public static BlockBuilder woodenButton(Identifier id, boolean isNether) {
		return block(id, new WoodenButtonBlock(FabricBlockSettings.of(Material.DECORATION)
				.strength(0.5f)
				.noCollision()
				.sounds(BlockSoundGroup.WOOD))) // todo: this has changed in 1.19.3 for nether
				.item(ItemGroup.REDSTONE);
	}

	public static BlockBuilder fence(Identifier id, Block planks, boolean isNether) {
		BlockBuilder builder = block(id, new FenceBlock(FabricBlockSettings.copy(planks)))
				.item(ItemGroup.DECORATIONS, b -> b.fuelTime(isNether ? 0 : 300));
		if(isNether) builder.flammability(5, 20);
		return builder;
	}

	public static BlockBuilder fenceGate(Identifier id, Block planks, boolean isNether) {
		BlockBuilder builder = block(id, new FenceGateBlock(FabricBlockSettings.copy(planks)))
				.item(ItemGroup.REDSTONE, b -> b.fuelTime(isNether ? 0 : 300));
		if(isNether) builder.flammability(5, 20);
		return builder;
	}

	public static BlockBuilder door(Identifier id, Block planks) {
		return block(id, new DoorBlock(FabricBlockSettings.copy(planks)
				.strength(3.0f)
				.nonOpaque()))
				.item(ItemGroup.REDSTONE);
	}

	public static BlockBuilder sapling(Identifier id, SaplingGenerator generator) {
		return block(id, new SaplingBlock(generator, FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).breakInstantly().noCollision().ticksRandomly()))
				.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.3f));
	}

	public static BlockBuilder sapling(Identifier id, SaplingGenerator generator, Predicate<BlockState> saplingSoilPredicate) {
		return block(id, new DawnSaplingBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).breakInstantly().noCollision().ticksRandomly(), generator, saplingSoilPredicate))
				.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.3f));
	}

	public static BlockBuilder fungus(Identifier id, RegistryKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn) {
		return block(id, new DawnFungusBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.FUNGUS).breakInstantly().noCollision(), featureKey, canPlantOn, canGrowOn))
				.item(ItemGroup.DECORATIONS, b -> b.compostingChance(0.65f));
	}

	public static BlockBuilder potted(Identifier id, Block content) {
		return block(id, new FlowerPotBlock(content, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque().luminance(content.getDefaultState().getLuminance())));
	}

	public static BlockBuilder potted(Block content) {
		Identifier contentId = Registry.BLOCK.getId(content);
		return potted(new Identifier(contentId.getNamespace(), "potted_" + contentId.getPath()), content);
	}

	public static BlockBuilder leaves(Identifier id) {
		return leaves(id, BlockSoundGroup.GRASS);
	}

	public static BlockBuilder leaves(Identifier id, BlockSoundGroup soundGroup) {
		return block(id, new LeavesBlock(FabricBlockSettings.of(Material.LEAVES)
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

	public static ItemBuilder spawnEgg(EntityType<? extends MobEntity> entity, int primaryColor, int secondaryColor) {
		Identifier entityId = Registry.ENTITY_TYPE.getId(entity);
		return item(new Identifier(entityId.getNamespace(), entityId.getPath() + "_spawn_egg"), new SpawnEggItem(entity, primaryColor, secondaryColor, new Item.Settings().group(ItemGroup.MISC)));
	}
}
