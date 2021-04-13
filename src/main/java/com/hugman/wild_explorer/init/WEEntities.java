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

public class WEEntities extends WEBundle {
	public static final EntityType<DuckEntity> DUCK = add(new EntityCreator<>("duck", FabricEntityTypeBuilder.createLiving().entityFactory(DuckEntity::new).spawnGroup(SpawnGroup.CREATURE).dimensions(EntityDimensions.fixed(0.4F, 0.8F)).trackRangeChunks(10).trackedUpdateRate(3).defaultAttributes(DuckEntity::createDuckAttributes).build()));
	public static final Item DUCK_SPAWN_EGG = add(new ItemCreator.Builder("duck_spawn_egg", settings -> new SpawnEggItem(WEEntities.DUCK, 10592673, 15904341, settings), new Item.Settings().group(ItemGroup.MISC)).build());
	public static final SoundCreator DUCK_AMBIENT_SOUND = creator(new SoundCreator("entity.duck.ambient"));
	public static final SoundCreator DUCK_HURT_SOUND = creator(new SoundCreator("entity.duck.hurt"));
	public static final SoundCreator DUCK_DEATH_SOUND = creator(new SoundCreator("entity.duck.death"));
	public static final SoundCreator DUCK_STEP_SOUND = creator(new SoundCreator("entity.duck.step"));

	public static void init() {
	}
}
