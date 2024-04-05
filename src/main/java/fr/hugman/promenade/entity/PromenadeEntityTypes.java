package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.world.biome.PromenadeBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

import java.util.function.Predicate;

public class PromenadeEntityTypes {
    public static final EntityType<CapybaraEntity> CAPYBARA = of("capybara", FabricEntityTypeBuilder.createMob()
            .entityFactory(CapybaraEntity::new)
            .spawnGroup(SpawnGroup.CREATURE)
            .dimensions(EntityDimensions.changing(0.7f, 0.875f).withEyeHeight(0.875f))
            .defaultAttributes(CapybaraEntity::createCapybaraAttributes)
            .spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn));
    public static final EntityType<DuckEntity> DUCK = of("duck", FabricEntityTypeBuilder.createMob()
            .entityFactory(DuckEntity::new)
            .spawnGroup(SpawnGroup.CREATURE)
            .dimensions(EntityDimensions.changing(0.4F, 0.8F).withEyeHeight(1.09375f))
            .trackRangeChunks(10)
            .trackedUpdateRate(3)
            .defaultAttributes(DuckEntity::createDuckAttributes)
            .spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn));
    public static final EntityType<LushCreeperEntity> LUSH_CREEPER = of("lush_creeper", FabricEntityTypeBuilder.createMob()
            .entityFactory(LushCreeperEntity::new)
            .spawnGroup(SpawnGroup.MONSTER)
            .dimensions(EntityDimensions.changing(0.6f, 1.7f))
            .trackRangeChunks(8)
            .defaultAttributes(LushCreeperEntity::createCreeperAttributes)
            .spawnRestriction(SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LushCreeperEntity::canSpawn));
    public static final EntityType<SunkenSkeletonEntity> SUNKEN_SKELETON = of("sunken_skeleton", FabricEntityTypeBuilder.createMob()
            .entityFactory(SunkenSkeletonEntity::new)
            .spawnGroup(SpawnGroup.MONSTER)
            .dimensions(EntityDimensions.changing(0.6F, 1.99F).withEyeHeight(1.74F))
            .trackRangeChunks(8)
            .defaultAttributes(SunkenSkeletonEntity::createSunkenSkeletonAttributes)
            .spawnRestriction(SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SunkenSkeletonEntity::canSpawn));

    private static <T extends Entity> EntityType<T> of(String path, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, Promenade.id(path), builder.build());
    }

    public static void appendWorldGen() {
        if (Promenade.CONFIG.animals.ducks_weight != 0) {
            Predicate<BiomeSelectionContext> hasFarmAnimals = BiomeSelectors.spawnsOneOf(EntityType.COW).and(BiomeSelectors.spawnsOneOf(EntityType.SHEEP)).and(BiomeSelectors.spawnsOneOf(EntityType.CHICKEN)).and(BiomeSelectors.spawnsOneOf(EntityType.PIG));
            BiomeModifications.addSpawn(hasFarmAnimals, SpawnGroup.CREATURE, PromenadeEntityTypes.DUCK, Promenade.CONFIG.animals.ducks_weight, 4, 4);
        }
        if (Promenade.CONFIG.animals.capybaras_weight != 0) {
            BiomeModifications.addSpawn(BiomeSelectors.tag(PromenadeBiomeTags.CAPYBARA_SPAWN), SpawnGroup.CREATURE, PromenadeEntityTypes.CAPYBARA, Promenade.CONFIG.animals.capybaras_weight, 3, 5);
        }
        if (Promenade.CONFIG.monsters.lush_creepers_weight != 0) {
            BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.CREEPER).and(BiomeSelectors.excludeByKey(BiomeKeys.LUSH_CAVES)), SpawnGroup.MONSTER, LUSH_CREEPER, Promenade.CONFIG.monsters.lush_creepers_weight, 2, 3);
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), SpawnGroup.MONSTER, LUSH_CREEPER, Promenade.CONFIG.monsters.lush_creepers_weight * 4, 2, 4);
        }
        if (Promenade.CONFIG.monsters.sunken_skeletons_weight != 0) {
            BiomeModifications.addSpawn(biomeSelectionContext -> biomeSelectionContext.hasTag(PromenadeBiomeTags.SUNKEN_SKELETON_SPAWN), SpawnGroup.MONSTER, SUNKEN_SKELETON, Promenade.CONFIG.monsters.sunken_skeletons_weight, 1, 3);
        }
    }
}
