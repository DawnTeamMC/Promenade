package fr.hugman.promenade.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.DuckEntity;
import fr.hugman.promenade.item.ItemGroupHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.Heightmap;

import java.util.function.Predicate;

public class AnimalContent {
	public static final EntityType<DuckEntity> DUCK = FabricEntityTypeBuilder.createMob()
			.entityFactory(DuckEntity::new)
			.spawnGroup(SpawnGroup.CREATURE)
			.dimensions(EntityDimensions.fixed(0.4F, 0.8F))
			.trackRangeChunks(10)
			.trackedUpdateRate(3)
			.defaultAttributes(DuckEntity::createDuckAttributes)
			.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn)
			.build();
	public static final Item DUCK_SPAWN_EGG = DawnFactory.spawnEgg(DUCK, 10592673, 15904341);
	public static final SoundEvent DUCK_AMBIENT_SOUND = new SoundEvent(Promenade.id("entity.duck.ambient"));
	public static final SoundEvent DUCK_HURT_SOUND = new SoundEvent(Promenade.id("entity.duck.hurt"));
	public static final SoundEvent DUCK_DEATH_SOUND = new SoundEvent(Promenade.id("entity.duck.death"));
	public static final SoundEvent DUCK_STEP_SOUND = new SoundEvent(Promenade.id("entity.duck.step"));

	public static final Item DUCK_FOOD = new Item(new DawnItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.3F).meat().build()));
	public static final Item COOKED_DUCK_FOOD = new Item(new DawnItemSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(0.6F).meat().build()));

	public static void init() {
		Registrar.add(Promenade.id("duck"), DUCK);
		Registrar.add(Promenade.id("duck_spawn_egg"), DUCK_SPAWN_EGG);
		Registrar.add(DUCK_AMBIENT_SOUND);
		Registrar.add(DUCK_HURT_SOUND);
		Registrar.add(DUCK_DEATH_SOUND);
		Registrar.add(DUCK_STEP_SOUND);

		Registrar.add(Promenade.id("duck"), DUCK_FOOD);
		Registrar.add(Promenade.id("cooked_duck"), COOKED_DUCK_FOOD);

		if(Promenade.CONFIG.animals.ducks_weight != 0) {
			Predicate<BiomeSelectionContext> hasFarmAnimals = BiomeSelectors.spawnsOneOf(EntityType.COW).and(BiomeSelectors.spawnsOneOf(EntityType.SHEEP)).and(BiomeSelectors.spawnsOneOf(EntityType.CHICKEN)).and(BiomeSelectors.spawnsOneOf(EntityType.PIG));
			BiomeModifications.addSpawn(hasFarmAnimals, SpawnGroup.CREATURE, DUCK, 10, 4, 4);
		}

		ItemGroupHelper.appendSpawnEgg(DUCK_SPAWN_EGG);
		ItemGroupHelper.append(ItemGroups.FOOD_AND_DRINK, e -> e.addAfter(Items.COOKED_CHICKEN, DUCK_FOOD, COOKED_DUCK_FOOD));
	}
}