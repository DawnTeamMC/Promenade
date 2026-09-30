package fr.hugman.promenade.entity;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.entity.helper.EntityTypeFactory;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.platform.PromenadePlatform;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import fr.hugman.promenade.world.biome.PromenadeBiomeSelectors;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import java.util.function.Predicate;

public class PromenadeEntityTypes {
    public static final EntityType<PromenadeBoat> SAKURA_BOAT = register("sakura_boat", EntityTypeFactory.boat(() -> PromenadeItems.SAKURA_BOAT, () -> PromenadeBlocks.SAKURA_PLANKS));
    public static final EntityType<PromenadeChestBoat> SAKURA_CHEST_BOAT = register("sakura_chest_boat", EntityTypeFactory.chestBoat(() -> PromenadeItems.SAKURA_CHEST_BOAT, () -> PromenadeBlocks.SAKURA_PLANKS));

    public static final EntityType<PromenadeBoat> MAPLE_BOAT = register("maple_boat", EntityTypeFactory.boat(() -> PromenadeItems.MAPLE_BOAT, () -> PromenadeBlocks.MAPLE_PLANKS));
    public static final EntityType<PromenadeChestBoat> MAPLE_CHEST_BOAT = register("maple_chest_boat", EntityTypeFactory.chestBoat(() -> PromenadeItems.MAPLE_CHEST_BOAT, () -> PromenadeBlocks.MAPLE_PLANKS));

    public static final EntityType<PromenadeBoat> PALM_BOAT = register("palm_boat", EntityTypeFactory.boat(() -> PromenadeItems.PALM_BOAT, () -> PromenadeBlocks.PALM_PLANKS));
    public static final EntityType<PromenadeChestBoat> PALM_CHEST_BOAT = register("palm_chest_boat", EntityTypeFactory.chestBoat(() -> PromenadeItems.PALM_CHEST_BOAT, () -> PromenadeBlocks.PALM_PLANKS));

    public static final EntityType<Capybara> CAPYBARA = register("capybara", EntityType.Builder.of(Capybara::new, MobCategory.CREATURE)
            .sized(0.7f, 0.875f)
            .eyeHeight(0.85f));
    public static final EntityType<Duck> DUCK = register("duck", EntityType.Builder.of(Duck::new, MobCategory.CREATURE)
            .sized(8f /16, 10f /16)
            .eyeHeight(9.75f /16)
            .clientTrackingRange(10)
            .updateInterval(3));

    public static final EntityType<LushCreeper> LUSH_CREEPER = register("lush_creeper", EntityType.Builder.of(LushCreeper::new, MobCategory.MONSTER)
            .sized(0.6f, 1.7f)
            .clientTrackingRange(8));
    public static final EntityType<Sunken> SUNKEN = register("sunken", EntityType.Builder.of(Sunken::new, MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
            .eyeHeight(1.74F)
            .ridingOffset(-0.7F)
            .clientTrackingRange(8));

    private static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> type) {
        var id = Promenade.id(path);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, type.build(id.toString()));
    }

    public static void registerAttributes() {
        var platform = PromenadePlatform.INSTANCE;
        platform.registerAttributes(CAPYBARA, Capybara::createCapybaraAttributes);
        platform.registerAttributes(DUCK, Duck::createDuckAttributes);
        platform.registerAttributes(LUSH_CREEPER, LushCreeper::createAttributes);
        platform.registerAttributes(SUNKEN, Sunken::createSunkenAttributes);
    }

    public static void registerSpawnPlacements() {
        var platform = PromenadePlatform.INSTANCE;
        platform.registerSpawnPlacement(CAPYBARA, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        platform.registerSpawnPlacement(DUCK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        platform.registerSpawnPlacement(LUSH_CREEPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LushCreeper::canSpawn);
        platform.registerSpawnPlacement(SUNKEN, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Sunken::canSpawn);
    }

    public static void appendWorldGen() {
        var platform = PromenadePlatform.INSTANCE;
        var duckWeight = PromenadeConfig.get().animals().ducksWeight();
        if (duckWeight > 0) {
            Predicate<Holder<Biome>> hasFarmAnimals = PromenadeBiomeSelectors.spawns(EntityType.COW)
                    .and(PromenadeBiomeSelectors.spawns(EntityType.SHEEP))
                    .and(PromenadeBiomeSelectors.spawns(EntityType.CHICKEN))
                    .and(PromenadeBiomeSelectors.spawns(EntityType.PIG));
            platform.addSpawn(hasFarmAnimals, MobCategory.CREATURE, PromenadeEntityTypes.DUCK, duckWeight, 4, 4);
        }

        var capybaraWeight = PromenadeConfig.get().animals().capybarasWeight();
        if (capybaraWeight > 0) {
            platform.addSpawn(PromenadeBiomeSelectors.tag(PromenadeBiomeTags.SPAWNS_CAPYBARAS), MobCategory.CREATURE, PromenadeEntityTypes.CAPYBARA, capybaraWeight, 3, 5);
        }

        var lushCreeperWeight = PromenadeConfig.get().monsters().lushCreepersWeight();
        if (lushCreeperWeight > 0) {
            platform.addSpawn(PromenadeBiomeSelectors.spawns(EntityType.CREEPER).and(PromenadeBiomeSelectors.is(Biomes.LUSH_CAVES).negate()), MobCategory.MONSTER, LUSH_CREEPER, lushCreeperWeight, 2, 3);
            platform.addSpawn(PromenadeBiomeSelectors.is(Biomes.LUSH_CAVES), MobCategory.MONSTER, LUSH_CREEPER, lushCreeperWeight * 4, 2, 4);
        }

        var sunkensWeight = PromenadeConfig.get().monsters().sunkensWeight();
        if (sunkensWeight > 0) {
            platform.addSpawn(PromenadeBiomeSelectors.tag(PromenadeBiomeTags.SPAWNS_SUNKEN), MobCategory.MONSTER, SUNKEN, sunkensWeight, 1, 3);
        }
    }
}
