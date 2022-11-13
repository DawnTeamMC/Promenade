package com.hugman.newdawn;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegistryHelper {
	public static void block(Identifier id, Block block) {
		Registry.register(Registry.BLOCK, id, block);
	}

	public static void block(Identifier id, AbstractBlock.Settings settings) {
		Registry.register(Registry.BLOCK, id, new Block(settings));
	}

	public static void item(Identifier id, Item item) {
		Registry.register(Registry.ITEM, id, item);
	}

	public static void item(Identifier id, Item.Settings settings) {
		Registry.register(Registry.ITEM, id, new Item(settings));
	}

	public static void item(Identifier id, ItemGroup group) {
		Registry.register(Registry.ITEM, id, new Item(new Item.Settings().group(group)));
	}

	public static void sound(SoundEvent soundEvent) {
		Registry.register(Registry.SOUND_EVENT, soundEvent.getId(), soundEvent);
	}

	public static void particle(Identifier id, DefaultParticleType particleType) {
		Registry.register(Registry.PARTICLE_TYPE, id, particleType);
	}

	public static <T extends Entity> void entity(Identifier id, EntityType<T> type) {
		Registry.register(Registry.ENTITY_TYPE, id, type);
	}
}
