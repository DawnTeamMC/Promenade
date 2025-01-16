package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.helper.EntityTypeFactory;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.world.biome.PromenadeBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

import java.util.function.Predicate;

public class PromenadeEntityTypes {
    public static final EntityType<BoatEntity> SAKURA_BOAT = register("sakura_boat", EntityTypeFactory.boat(PromenadeItems.SAKURA_BOAT));
    public static final EntityType<ChestBoatEntity> SAKURA_CHEST_BOAT = register("sakura_chest_boat", EntityTypeFactory.chestBoat(PromenadeItems.SAKURA_CHEST_BOAT));

    public static final EntityType<BoatEntity> MAPLE_BOAT = register("maple_boat", EntityTypeFactory.boat(PromenadeItems.MAPLE_BOAT));
    public static final EntityType<ChestBoatEntity> MAPLE_CHEST_BOAT = register("maple_chest_boat", EntityTypeFactory.chestBoat(PromenadeItems.MAPLE_CHEST_BOAT));

    public static final EntityType<BoatEntity> PALM_BOAT = register("palm_boat", EntityTypeFactory.boat(PromenadeItems.PALM_BOAT));
    public static final EntityType<ChestBoatEntity> PALM_CHEST_BOAT = register("palm_chest_boat", EntityTypeFactory.chestBoat(PromenadeItems.PALM_CHEST_BOAT));

    public static final EntityType<CapybaraEntity> CAPYBARA = register("capybara", FabricEntityType.Builder.createMob(CapybaraEntity::new, SpawnGroup.CREATURE, mob -> mob
                    .defaultAttributes(CapybaraEntity::createCapybaraAttributes)
                    .spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn)
            )
            .dimensions(0.7f, 0.875f)
            .eyeHeight(0.875f));
    public static final EntityType<DuckEntity> DUCK = register("duck", FabricEntityType.Builder.createMob(DuckEntity::new, SpawnGroup.CREATURE, mob -> mob
                    .defaultAttributes(DuckEntity::createDuckAttributes)
                    .spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn)
            )
            .dimensions(0.4F, 0.8F)
            .eyeHeight(1.09375f)
            .maxTrackingRange(10)
            .trackingTickInterval(3));
    public static final EntityType<LushCreeperEntity> LUSH_CREEPER = register("lush_creeper", FabricEntityType.Builder.createMob(LushCreeperEntity::new, SpawnGroup.MONSTER, mob -> mob
                    .defaultAttributes(LushCreeperEntity::createCreeperAttributes)
                    .spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LushCreeperEntity::canSpawn)
            )
            .dimensions(0.6f, 1.7f)
            .maxTrackingRange(8));
    public static final EntityType<SunkenEntity> SUNKEN = register("sunken", FabricEntityType.Builder.createMob(SunkenEntity::new, SpawnGroup.MONSTER, mob -> mob
                    .defaultAttributes(SunkenEntity::createSunkenAttributes)
                    .spawnRestriction(SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunkenEntity::canSpawn)
            )
            .dimensions(0.6F, 1.99F)
            .eyeHeight(1.74F)
            .maxTrackingRange(8));

    private static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> type) {
        var key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Promenade.id(path));
        return Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }

    public static void appendWorldGen() {
        var duckWeight = PromenadeConfig.get().animals().ducksWeight();
        if (duckWeight != 0) {
            Predicate<BiomeSelectionContext> hasFarmAnimals = BiomeSelectors.spawnsOneOf(EntityType.COW).and(BiomeSelectors.spawnsOneOf(EntityType.SHEEP)).and(BiomeSelectors.spawnsOneOf(EntityType.CHICKEN)).and(BiomeSelectors.spawnsOneOf(EntityType.PIG));
            BiomeModifications.addSpawn(hasFarmAnimals, SpawnGroup.CREATURE, PromenadeEntityTypes.DUCK, duckWeight, 4, 4);
        }

        var capybaraWeight = PromenadeConfig.get().animals().capybarasWeight();
        if (capybaraWeight != 0) {
            BiomeModifications.addSpawn(BiomeSelectors.tag(PromenadeBiomeTags.CAPYBARA_SPAWN), SpawnGroup.CREATURE, PromenadeEntityTypes.CAPYBARA, capybaraWeight, 3, 5);
        }

        var lushCreeperWeight = PromenadeConfig.get().monsters().lushCreepersWeight();
        if (lushCreeperWeight != 0) {
            BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.CREEPER).and(BiomeSelectors.excludeByKey(BiomeKeys.LUSH_CAVES)), SpawnGroup.MONSTER, LUSH_CREEPER, lushCreeperWeight, 2, 3);
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), SpawnGroup.MONSTER, LUSH_CREEPER, lushCreeperWeight * 4, 2, 4);
        }

        var sunkensWeight = PromenadeConfig.get().monsters().sunkensWeight();
        if (sunkensWeight != 0) {
            BiomeModifications.addSpawn(biomeSelectionContext -> biomeSelectionContext.hasTag(PromenadeBiomeTags.SUNKEN_SPAWN), SpawnGroup.MONSTER, SUNKEN, sunkensWeight, 1, 3);
        }
    }
}
