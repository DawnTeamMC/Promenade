package com.hugman.promenade.init;

import com.hugman.dawn.api.creator.EntityCreator;
import com.hugman.dawn.api.creator.ItemCreator;
import com.hugman.dawn.api.creator.SoundCreator;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.object.entity.DuckEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.Heightmap;

import java.util.function.Predicate;

public class AnimalBundle extends PromenadeBundle {
	public static final EntityType<DuckEntity> DUCK = add(new EntityCreator<>("duck", FabricEntityTypeBuilder.createMob()
			.entityFactory(DuckEntity::new)
			.spawnGroup(SpawnGroup.CREATURE)
			.dimensions(EntityDimensions.fixed(0.4F, 0.8F))
			.trackRangeChunks(10)
			.trackedUpdateRate(3)
			.defaultAttributes(DuckEntity::createDuckAttributes)
			.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn)
			.build()));
	public static final Item DUCK_SPAWN_EGG = add(new ItemCreator.Builder("duck_spawn_egg", settings -> new SpawnEggItem(AnimalBundle.DUCK, 10592673, 15904341, settings), new Item.Settings().group(ItemGroup.MISC)).build());
	public static final SoundCreator DUCK_AMBIENT_SOUND = creator(new SoundCreator("entity.duck.ambient"));
	public static final SoundCreator DUCK_HURT_SOUND = creator(new SoundCreator("entity.duck.hurt"));
	public static final SoundCreator DUCK_DEATH_SOUND = creator(new SoundCreator("entity.duck.death"));
	public static final SoundCreator DUCK_STEP_SOUND = creator(new SoundCreator("entity.duck.step"));

	public static void addToGen() {
		if(Promenade.CONFIG.creatures.ducks) {
			Predicate<BiomeSelectionContext> hasFarmAnimals = BiomeSelectors.spawnsOneOf(EntityType.COW).and(BiomeSelectors.spawnsOneOf(EntityType.SHEEP)).and(BiomeSelectors.spawnsOneOf(EntityType.CHICKEN)).and(BiomeSelectors.spawnsOneOf(EntityType.PIG));
			BiomeModifications.addSpawn(hasFarmAnimals, SpawnGroup.CREATURE, DUCK, 10, 4, 4);
		}
	}
}
