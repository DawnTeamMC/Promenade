package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.EntityCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.dawn.api.creator.SoundCreator;
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
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class MonsterBundle extends PromenadeBundle {
	public static final EntityType<LushCreeperEntity> LUSH_CREEPER = add(new EntityCreator<>("lush_creeper", FabricEntityTypeBuilder.createMob()
			.entityFactory(LushCreeperEntity::new)
			.spawnGroup(SpawnGroup.MONSTER)
			.dimensions(EntityDimensions.fixed(0.6f, 1.7f))
			.trackRangeChunks(8)
			.defaultAttributes(LushCreeperEntity::createCreeperAttributes)
			.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LushCreeperEntity::canSpawn)
			.build()));
	public static final Item LUSH_CREEPER_SPAWN_EGG = add(new ItemCreator.Builder("lush_creeper_spawn_egg", settings -> new SpawnEggItem(LUSH_CREEPER, 4347181, 4262661, settings), new Item.Settings().group(ItemGroup.MISC)).build());

	public static final EntityType<SunkenSkeletonEntity> SUNKEN_SKELETON = add(new EntityCreator<>("sunken_skeleton", FabricEntityTypeBuilder.createMob()
			.entityFactory(SunkenSkeletonEntity::new)
			.spawnGroup(SpawnGroup.MONSTER)
			.dimensions(EntityDimensions.fixed(0.6F, 1.99F))
			.trackRangeChunks(8)
			.defaultAttributes(SunkenSkeletonEntity::createSunkenSkeletonAttributes)
			.spawnRestriction(SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunkenSkeletonEntity::canSpawn)
			.build()));
	public static final Item SUNKEN_SKELETON_SPAWN_EGG = add(new ItemCreator.Builder("sunken_skeleton_spawn_egg", settings -> new SpawnEggItem(SUNKEN_SKELETON, 12233882, 6191682, settings), new Item.Settings().group(ItemGroup.MISC)).build());
	public static final SoundCreator SUNKEN_SKELETON_AMBIENT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.ambient"));
	public static final SoundCreator SUNKEN_SKELETON_HURT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.hurt"));
	public static final SoundCreator SUNKEN_SKELETON_DEATH_SOUND = creator(new SoundCreator("entity.sunken_skeleton.death"));
	public static final SoundCreator SUNKEN_SKELETON_STEP_SOUND = creator(new SoundCreator("entity.sunken_skeleton.step"));
	public static final SoundCreator SUNKEN_SKELETON_SHOOT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.shoot"));

	public static void addToGen() {
		if(Promenade.CONFIG.monsters.lush_creepers) {
			BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.CREEPER).and(BiomeSelectors.excludeByKey(BiomeKeys.LUSH_CAVES)), SpawnGroup.MONSTER, LUSH_CREEPER, 15, 2, 3);
			BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), SpawnGroup.MONSTER, LUSH_CREEPER, 90, 2, 4);
		}
		if(Promenade.CONFIG.monsters.sunken_skeletons) {
			BiomeModifications.addSpawn(biomeSelectionContext -> biomeSelectionContext.hasTag(PromenadeTags.Biomes.SUNKEN_SKELETON_SPAWN), SpawnGroup.MONSTER, SUNKEN_SKELETON, 20, 1, 3);
		}
	}
}
