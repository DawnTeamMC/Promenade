package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.helper.EntityTypeFactory;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import java.util.function.Predicate;

public class PromenadeEntityTypes {
    public static final EntityType<Boat> SAKURA_BOAT = register("sakura_boat", EntityTypeFactory.boat(() -> PromenadeItems.SAKURA_BOAT));
    public static final EntityType<ChestBoat> SAKURA_CHEST_BOAT = register("sakura_chest_boat", EntityTypeFactory.chestBoat(() -> PromenadeItems.SAKURA_CHEST_BOAT));

    public static final EntityType<Boat> MAPLE_BOAT = register("maple_boat", EntityTypeFactory.boat(() -> PromenadeItems.MAPLE_BOAT));
    public static final EntityType<ChestBoat> MAPLE_CHEST_BOAT = register("maple_chest_boat", EntityTypeFactory.chestBoat(() -> PromenadeItems.MAPLE_CHEST_BOAT));

    public static final EntityType<Boat> PALM_BOAT = register("palm_boat", EntityTypeFactory.boat(() -> PromenadeItems.PALM_BOAT));
    public static final EntityType<ChestBoat> PALM_CHEST_BOAT = register("palm_chest_boat", EntityTypeFactory.chestBoat(() -> PromenadeItems.PALM_CHEST_BOAT));

    public static final EntityType<Capybara> CAPYBARA = register("capybara", FabricEntityType.Builder.createMob(Capybara::new, MobCategory.CREATURE, mob -> mob
                    .defaultAttributes(Capybara::createCapybaraAttributes)
                    .spawnPlacement(SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules)
            )
            .sized(0.7f, 0.875f)
            .eyeHeight(0.85f));
    public static final EntityType<Duck> DUCK = register("duck", FabricEntityType.Builder.createMob(Duck::new, MobCategory.CREATURE, mob -> mob
                    .defaultAttributes(Duck::createDuckAttributes)
                    .spawnPlacement(SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules)
            )
            .sized(8f /16, 10f /16)
            .eyeHeight(9.75f /16)
            .clientTrackingRange(10)
            .updateInterval(3));
    public static final EntityType<LushCreeper> LUSH_CREEPER = register("lush_creeper", FabricEntityType.Builder.createMob(LushCreeper::new, MobCategory.MONSTER, mob -> mob
                    .defaultAttributes(LushCreeper::createAttributes)
                    .spawnPlacement(SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LushCreeper::canSpawn)
            )
            .sized(0.6f, 1.7f)
            .clientTrackingRange(8)
            .notInPeaceful());
    public static final EntityType<Sunken> SUNKEN = register("sunken", FabricEntityType.Builder.createMob(Sunken::new, MobCategory.MONSTER, mob -> mob
                    .defaultAttributes(Sunken::createSunkenAttributes)
                    .spawnPlacement(SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Sunken::canSpawn)
            )
            .sized(0.6F, 1.99F)
            .eyeHeight(1.74F)
            .clientTrackingRange(8)
            .notInPeaceful());

    private static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> type) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, Promenade.id(path));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }

    public static void appendWorldGen() {
        var duckWeight = PromenadeConfig.get().animals().ducksWeight();
        if (duckWeight != 0) {
            Predicate<BiomeSelectionContext> hasFarmAnimals = BiomeSelectors.spawnsOneOf(EntityType.COW)
                    .and(BiomeSelectors.spawnsOneOf(EntityType.SHEEP))
                    .and(BiomeSelectors.spawnsOneOf(EntityType.CHICKEN))
                    .and(BiomeSelectors.spawnsOneOf(EntityType.PIG));
            BiomeModifications.addSpawn(hasFarmAnimals, MobCategory.CREATURE, PromenadeEntityTypes.DUCK, duckWeight, 4, 4);
        }

        var capybaraWeight = PromenadeConfig.get().animals().capybarasWeight();
        if (capybaraWeight != 0) {
            BiomeModifications.addSpawn(BiomeSelectors.tag(PromenadeBiomeTags.SPAWNS_CAPYBARAS), MobCategory.CREATURE, PromenadeEntityTypes.CAPYBARA, capybaraWeight, 3, 5);
        }

        var lushCreeperWeight = PromenadeConfig.get().monsters().lushCreepersWeight();
        if (lushCreeperWeight != 0) {
            BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.CREEPER).and(BiomeSelectors.excludeByKey(Biomes.LUSH_CAVES)), MobCategory.MONSTER, LUSH_CREEPER, lushCreeperWeight, 2, 3);
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.LUSH_CAVES), MobCategory.MONSTER, LUSH_CREEPER, lushCreeperWeight * 4, 2, 4);
        }

        var sunkensWeight = PromenadeConfig.get().monsters().sunkensWeight();
        if (sunkensWeight != 0) {
            BiomeModifications.addSpawn(biomeSelectionContext -> biomeSelectionContext.hasTag(PromenadeBiomeTags.SPAWNS_SUNKEN), MobCategory.MONSTER, SUNKEN, sunkensWeight, 1, 3);
        }
    }
}
