package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.EntityCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.dawn.api.creator.SoundCreator;
import com.hugman.wild_explorer.object.entity.DuckEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;

public class WEEntities extends WEPack {
	public static final EntityType<DuckEntity> DUCK = register(new EntityCreator.Builder<>("duck", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DuckEntity::new).dimensions(EntityDimensions.fixed(0.4F, 0.8F)).trackRangeChunks(10).trackedUpdateRate(3).build()).attributes(DuckEntity.createDuckAttributes()));
	public static final Item DUCK_SPAWN_EGG = register(new ItemCreator.Builder("duck_spawn_egg", new SpawnEggItem(WEEntities.DUCK, 10592673, 15904341, new Item.Settings().group(ItemGroup.MISC))));
	public static final SoundEvent DUCK_AMBIENT_SOUND = register(new SoundCreator.Builder("entity.duck.ambient"));
	public static final SoundEvent DUCK_HURT_SOUND = register(new SoundCreator.Builder("entity.duck.hurt"));
	public static final SoundEvent DUCK_DEATH_SOUND = register(new SoundCreator.Builder("entity.duck.death"));
	public static final SoundEvent DUCK_STEP_SOUND = register(new SoundCreator.Builder("entity.duck.step"));

	public static void init() {
	}
}
