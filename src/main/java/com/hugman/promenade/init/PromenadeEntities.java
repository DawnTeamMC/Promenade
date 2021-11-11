package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.EntityCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.dawn.api.creator.SoundCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.world.PromenadeConfiguredFeatures;
import com.hugman.promenade.object.entity.DuckEntity;
import com.hugman.promenade.object.entity.SunkenSkeletonEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

import java.util.function.Predicate;

public class PromenadeEntities extends PromenadeBundle {
	public static final EntityType<DuckEntity> DUCK = add(new EntityCreator<>("duck", FabricEntityTypeBuilder.createLiving().entityFactory(DuckEntity::new).spawnGroup(SpawnGroup.CREATURE).dimensions(EntityDimensions.fixed(0.4F, 0.8F)).trackRangeChunks(10).trackedUpdateRate(3).defaultAttributes(DuckEntity::createDuckAttributes).build()));
	public static final Item DUCK_SPAWN_EGG = add(new ItemCreator.Builder("duck_spawn_egg", settings -> new SpawnEggItem(PromenadeEntities.DUCK, 10592673, 15904341, settings), new Item.Settings().group(ItemGroup.MISC)).build());
	public static final SoundCreator DUCK_AMBIENT_SOUND = creator(new SoundCreator("entity.duck.ambient"));
	public static final SoundCreator DUCK_HURT_SOUND = creator(new SoundCreator("entity.duck.hurt"));
	public static final SoundCreator DUCK_DEATH_SOUND = creator(new SoundCreator("entity.duck.death"));
	public static final SoundCreator DUCK_STEP_SOUND = creator(new SoundCreator("entity.duck.step"));

	public static final EntityType<SunkenSkeletonEntity> SUNKEN_SKELETON = add(new EntityCreator<>("sunken_skeleton", FabricEntityTypeBuilder.createMob().entityFactory(SunkenSkeletonEntity::new).spawnGroup(SpawnGroup.MONSTER).dimensions(EntityDimensions.fixed(0.6F, 1.99F)).trackRangeChunks(8).defaultAttributes(SunkenSkeletonEntity::createSunkenSkeletonAttributes).build()));
	public static final Item SUNKEN_SKELETON_SPAWN_EGG = add(new ItemCreator.Builder("sunken_skeleton_spawn_egg", settings -> new SpawnEggItem(PromenadeEntities.SUNKEN_SKELETON, 10592673, 15904341, settings), new Item.Settings().group(ItemGroup.MISC)).build());
	public static final SoundCreator SUNKEN_SKELETON_AMBIENT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.ambient"));
	public static final SoundCreator SUNKEN_SKELETON_HURT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.hurt"));
	public static final SoundCreator SUNKEN_SKELETON_DEATH_SOUND = creator(new SoundCreator("entity.sunken_skeleton.death"));
	public static final SoundCreator SUNKEN_SKELETON_STEP_SOUND = creator(new SoundCreator("entity.sunken_skeleton.step"));
	public static final SoundCreator SUNKEN_SKELETON_SHOOT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.shoot"));

	private static final PromenadeConfig.CreaturesCategory CREATURES_CONFIG = Promenade.CONFIG.creatures;

	public static void init() {
	}

	public static void addToGen() {
		if(CREATURES_CONFIG.ducks) {
			Predicate<BiomeSelectionContext> hasFarmAnimals = BiomeSelectors.spawnsOneOf(EntityType.COW).and(BiomeSelectors.spawnsOneOf(EntityType.SHEEP)).and(BiomeSelectors.spawnsOneOf(EntityType.CHICKEN)).and(BiomeSelectors.spawnsOneOf(EntityType.PIG));
			BiomeModifications.addSpawn(hasFarmAnimals, SpawnGroup.CREATURE, PromenadeEntities.DUCK, 10, 4, 4);
		}
	}
}
