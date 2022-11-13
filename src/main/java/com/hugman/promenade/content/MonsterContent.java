package com.hugman.promenade.content;

import com.hugman.newdawn.DawnFactory;
import com.hugman.newdawn.RegistryHelper;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.object.entity.LushCreeperEntity;
import com.hugman.promenade.object.entity.SunkenSkeletonEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class MonsterContent {
	public static final EntityType<LushCreeperEntity> LUSH_CREEPER = FabricEntityTypeBuilder.createMob()
			.entityFactory(LushCreeperEntity::new)
			.spawnGroup(SpawnGroup.MONSTER)
			.dimensions(EntityDimensions.fixed(0.6f, 1.7f))
			.trackRangeChunks(8)
			.defaultAttributes(LushCreeperEntity::createCreeperAttributes)
			.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LushCreeperEntity::canSpawn)
			.build();
	public static final Item LUSH_CREEPER_SPAWN_EGG = DawnFactory.spawnEgg(LUSH_CREEPER, 4347181, 4262661);

	public static final EntityType<SunkenSkeletonEntity> SUNKEN_SKELETON = FabricEntityTypeBuilder.createMob()
			.entityFactory(SunkenSkeletonEntity::new)
			.spawnGroup(SpawnGroup.MONSTER)
			.dimensions(EntityDimensions.fixed(0.6F, 1.99F))
			.trackRangeChunks(8)
			.defaultAttributes(SunkenSkeletonEntity::createSunkenSkeletonAttributes)
			.spawnRestriction(SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunkenSkeletonEntity::canSpawn)
			.build();
	public static final Item SUNKEN_SKELETON_SPAWN_EGG = DawnFactory.spawnEgg(SUNKEN_SKELETON, 12233882, 6191682);
	public static final SoundEvent SUNKEN_SKELETON_AMBIENT_SOUND = new SoundEvent(Promenade.id("entity.sunken_skeleton.ambient"));
	public static final SoundEvent SUNKEN_SKELETON_HURT_SOUND = new SoundEvent(Promenade.id("entity.sunken_skeleton.hurt"));
	public static final SoundEvent SUNKEN_SKELETON_DEATH_SOUND = new SoundEvent(Promenade.id("entity.sunken_skeleton.death"));
	public static final SoundEvent SUNKEN_SKELETON_STEP_SOUND = new SoundEvent(Promenade.id("entity.sunken_skeleton.step"));
	public static final SoundEvent SUNKEN_SKELETON_SHOOT_SOUND = new SoundEvent(Promenade.id("entity.sunken_skeleton.shoot"));

	public static void init() {
		RegistryHelper.entity(Promenade.id("lush_creeper"), LUSH_CREEPER);
		RegistryHelper.item(Promenade.id("lush_creeper_spawn_egg"), LUSH_CREEPER_SPAWN_EGG);

		RegistryHelper.entity(Promenade.id("sunken_skeleton"), SUNKEN_SKELETON);
		RegistryHelper.item(Promenade.id("sunken_skeleton_spawn_egg"), SUNKEN_SKELETON_SPAWN_EGG);
		RegistryHelper.sound(SUNKEN_SKELETON_AMBIENT_SOUND);
		RegistryHelper.sound(SUNKEN_SKELETON_HURT_SOUND);
		RegistryHelper.sound(SUNKEN_SKELETON_DEATH_SOUND);
		RegistryHelper.sound(SUNKEN_SKELETON_STEP_SOUND);
		RegistryHelper.sound(SUNKEN_SKELETON_SHOOT_SOUND);

		if(Promenade.CONFIG.monsters.lush_creepers_weight != 0) {
			BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.CREEPER).and(BiomeSelectors.excludeByKey(BiomeKeys.LUSH_CAVES)), SpawnGroup.MONSTER, LUSH_CREEPER, Promenade.CONFIG.monsters.lush_creepers_weight, 2, 3);
			BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), SpawnGroup.MONSTER, LUSH_CREEPER, Promenade.CONFIG.monsters.lush_creepers_weight * 4, 2, 4);
		}
		if(Promenade.CONFIG.monsters.sunken_skeletons_weight != 0) {
			BiomeModifications.addSpawn(biomeSelectionContext -> biomeSelectionContext.hasTag(PromenadeTags.Biomes.SUNKEN_SKELETON_SPAWN), SpawnGroup.MONSTER, SUNKEN_SKELETON, Promenade.CONFIG.monsters.sunken_skeletons_weight, 1, 3);
		}
	}
}
