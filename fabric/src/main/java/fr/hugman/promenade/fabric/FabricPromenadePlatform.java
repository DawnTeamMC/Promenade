package fr.hugman.promenade.fabric;

import fr.hugman.promenade.platform.CreativeModeTabOutput;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FabricPromenadePlatform implements PromenadePlatform {
    @Override
    public Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public <T> void registerAlias(Registry<T> registry, ResourceLocation oldId, ResourceLocation newId) {
        registry.addAlias(oldId, newId);
    }

    @Override
    public void registerEntityDataSerializer(ResourceLocation id, EntityDataSerializer<?> serializer) {
        // Fabric API has no registry for these on 1.21.1: they are synced by the order they are registered in
        EntityDataSerializers.registerSerializer(serializer);
    }

    @Override
    public void registerFlammable(Block block, int burnChance, int spreadChance) {
        FlammableBlockRegistry.getDefaultInstance().add(block, burnChance, spreadChance);
    }

    @Override
    public void addSupportedBlocks(BlockEntityType<?> type, Block... blocks) {
        for (Block block : blocks) {
            type.addSupportedBlock(block);
        }
    }

    @Override
    public CreativeModeTab.Builder creativeModeTabBuilder() {
        return FabricItemGroup.builder();
    }

    @Override
    public void modifyCreativeModeTab(ResourceKey<CreativeModeTab> tab, Consumer<CreativeModeTabOutput> modifier) {
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> modifier.accept(entries::addAfter));
    }

    @Override
    public void registerAttributes(EntityType<? extends LivingEntity> type, Supplier<AttributeSupplier.Builder> attributes) {
        FabricDefaultAttributeRegistry.register(type, attributes.get());
    }

    @Override
    public <T extends Mob> void registerSpawnPlacement(EntityType<T> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate) {
        SpawnPlacements.register(type, placementType, heightmap, predicate);
    }

    @Override
    public void addSpawn(Predicate<Holder<Biome>> biomes, MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
        BiomeModifications.addSpawn(context -> biomes.test(context.getBiomeRegistryEntry()), category, type, weight, minGroupSize, maxGroupSize);
    }

    @Override
    public void addFeature(Predicate<Holder<Biome>> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature) {
        BiomeModifications.addFeature(context -> biomes.test(context.getBiomeRegistryEntry()), step, feature);
    }
}
