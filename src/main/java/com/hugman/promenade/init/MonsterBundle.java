package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.EntityCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.dawn.api.creator.SoundCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.config.PromenadeConfig;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.object.entity.DuckEntity;
import com.hugman.promenade.object.entity.SunkenSkeletonEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
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
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.OceanConfiguredFeatures;

import java.util.function.Predicate;

public class MonsterBundle extends PromenadeBundle {
	public static final EntityType<SunkenSkeletonEntity> SUNKEN_SKELETON = add(new EntityCreator<>("sunken_skeleton", FabricEntityTypeBuilder.createMob()
			.entityFactory(SunkenSkeletonEntity::new)
			.spawnGroup(SpawnGroup.MONSTER)
			.dimensions(EntityDimensions.fixed(0.6F, 1.99F))
			.trackRangeChunks(8)
			.defaultAttributes(SunkenSkeletonEntity::createSunkenSkeletonAttributes)
			.spawnRestriction(SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunkenSkeletonEntity::canSpawn).build()));
	public static final Item SUNKEN_SKELETON_SPAWN_EGG = add(new ItemCreator.Builder("sunken_skeleton_spawn_egg", settings -> new SpawnEggItem(MonsterBundle.SUNKEN_SKELETON, 12233882, 6191682, settings), new Item.Settings().group(ItemGroup.MISC)).build());
	public static final SoundCreator SUNKEN_SKELETON_AMBIENT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.ambient"));
	public static final SoundCreator SUNKEN_SKELETON_HURT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.hurt"));
	public static final SoundCreator SUNKEN_SKELETON_DEATH_SOUND = creator(new SoundCreator("entity.sunken_skeleton.death"));
	public static final SoundCreator SUNKEN_SKELETON_STEP_SOUND = creator(new SoundCreator("entity.sunken_skeleton.step"));
	public static final SoundCreator SUNKEN_SKELETON_SHOOT_SOUND = creator(new SoundCreator("entity.sunken_skeleton.shoot"));

	public static void addToGen() {
		if(Promenade.CONFIG.creatures.sunken_skeletons) {
			BiomeModifications.addSpawn(biomeSelectionContext -> biomeSelectionContext.hasBuiltInFeature(OceanConfiguredFeatures.WARM_OCEAN_VEGETATION), SpawnGroup.MONSTER, SUNKEN_SKELETON, 20, 1, 3);
		}
	}
}
