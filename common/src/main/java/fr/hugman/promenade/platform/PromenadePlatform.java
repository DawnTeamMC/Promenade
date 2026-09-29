package fr.hugman.promenade.platform;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.nio.file.Path;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Everything the shared code needs from the mod loader.
 * <p>
 * Each loader project provides one implementation, declared in {@code META-INF/services}.
 * Registrations that loaders deliver through their own events (renderers, model layers, colors, particles, dynamic
 * registries) are not in here: they take a registrar instead, that the loader passes when it's ready.
 */
public interface PromenadePlatform {
    PromenadePlatform INSTANCE = ServiceLoader.load(PromenadePlatform.class, PromenadePlatform.class.getClassLoader())
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No Promenade platform implementation found"));

    Path configDir();

    /* ============== */
    /*   REGISTRIES   */
    /* ============== */

    /**
     * Makes {@code oldId} resolve to {@code newId}, so that renamed content is not lost in existing worlds.
     */
    <T> void registerAlias(Registry<T> registry, Identifier oldId, Identifier newId);

    void registerEntityDataSerializer(Identifier id, EntityDataSerializer<?> serializer);

    /* ========== */
    /*   BLOCKS   */
    /* ========== */

    void registerFlammable(Block block, int burnChance, int spreadChance);

    void addSupportedBlocks(BlockEntityType<?> type, Block... blocks);

    /* ================= */
    /*   CREATIVE TABS   */
    /* ================= */

    /**
     * @return a builder for a creative tab that the loader positions by itself
     */
    CreativeModeTab.Builder creativeModeTabBuilder();

    void modifyCreativeModeTab(ResourceKey<CreativeModeTab> tab, Consumer<CreativeModeTabOutput> modifier);

    /* ============ */
    /*   ENTITIES   */
    /* ============ */

    /**
     * @param attributes called once the loader is ready to build the attributes, as they may depend on other mods' registrations
     */
    void registerAttributes(EntityType<? extends LivingEntity> type, Supplier<AttributeSupplier.Builder> attributes);

    <T extends Mob> void registerSpawnPlacement(EntityType<T> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate);

    /* ==================== */
    /*   WORLD GENERATION   */
    /* ==================== */

    void addSpawn(Predicate<Holder<Biome>> biomes, MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize);

    void addFeature(Predicate<Holder<Biome>> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature);
}
