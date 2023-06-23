package fr.hugman.promenade.registry.content;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.item.ItemGroupHelper;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.entity.LushCreeperEntity;
import fr.hugman.promenade.entity.SunkenSkeletonEntity;
import fr.hugman.promenade.registry.tag.PromenadeBiomeTags;
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
    public static final SoundEvent SUNKEN_SKELETON_AMBIENT_SOUND = SoundEvent.of(Promenade.id("entity.sunken_skeleton.ambient"));
    public static final SoundEvent SUNKEN_SKELETON_HURT_SOUND = SoundEvent.of(Promenade.id("entity.sunken_skeleton.hurt"));
    public static final SoundEvent SUNKEN_SKELETON_DEATH_SOUND = SoundEvent.of(Promenade.id("entity.sunken_skeleton.death"));
    public static final SoundEvent SUNKEN_SKELETON_STEP_SOUND = SoundEvent.of(Promenade.id("entity.sunken_skeleton.step"));
    public static final SoundEvent SUNKEN_SKELETON_SHOOT_SOUND = SoundEvent.of(Promenade.id("entity.sunken_skeleton.shoot"));

    public static void register(Registrar r) {
        r.add(("lush_creeper"), LUSH_CREEPER);
        r.add(("lush_creeper_spawn_egg"), LUSH_CREEPER_SPAWN_EGG);

        r.add(("sunken_skeleton"), SUNKEN_SKELETON);
        r.add(("sunken_skeleton_spawn_egg"), SUNKEN_SKELETON_SPAWN_EGG);
        Registrar.add(SUNKEN_SKELETON_AMBIENT_SOUND);
        Registrar.add(SUNKEN_SKELETON_HURT_SOUND);
        Registrar.add(SUNKEN_SKELETON_DEATH_SOUND);
        Registrar.add(SUNKEN_SKELETON_STEP_SOUND);
        Registrar.add(SUNKEN_SKELETON_SHOOT_SOUND);

        if (Promenade.CONFIG.monsters.lush_creepers_weight != 0) {
            BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.CREEPER).and(BiomeSelectors.excludeByKey(BiomeKeys.LUSH_CAVES)), SpawnGroup.MONSTER, LUSH_CREEPER, Promenade.CONFIG.monsters.lush_creepers_weight, 2, 3);
            BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), SpawnGroup.MONSTER, LUSH_CREEPER, Promenade.CONFIG.monsters.lush_creepers_weight * 4, 2, 4);
        }
        if (Promenade.CONFIG.monsters.sunken_skeletons_weight != 0) {
            BiomeModifications.addSpawn(biomeSelectionContext -> biomeSelectionContext.hasTag(PromenadeBiomeTags.SUNKEN_SKELETON_SPAWN), SpawnGroup.MONSTER, SUNKEN_SKELETON, Promenade.CONFIG.monsters.sunken_skeletons_weight, 1, 3);
        }

        ItemGroupHelper.appendSpawnEgg(LUSH_CREEPER_SPAWN_EGG);
        ItemGroupHelper.appendSpawnEgg(SUNKEN_SKELETON_SPAWN_EGG);
    }
}
