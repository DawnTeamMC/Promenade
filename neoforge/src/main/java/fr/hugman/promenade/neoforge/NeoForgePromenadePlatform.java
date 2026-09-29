package fr.hugman.promenade.neoforge;

import fr.hugman.promenade.platform.CreativeModeTabOutput;
import fr.hugman.promenade.platform.PromenadePlatform;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SequencedSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Most registrations are handed to NeoForge through events fired after the registration phase, so they are kept here
 * until then. Biome additions are applied by {@link PromenadeBiomeModifier}.
 */
public class NeoForgePromenadePlatform implements PromenadePlatform {
    private final List<Consumer<EntityAttributeCreationEvent>> attributes = new ArrayList<>();
    private final List<Consumer<RegisterSpawnPlacementsEvent>> spawnPlacements = new ArrayList<>();
    private final List<Consumer<BlockEntityTypeAddBlocksEvent>> supportedBlocks = new ArrayList<>();
    private final Map<ResourceKey<CreativeModeTab>, List<Consumer<CreativeModeTabOutput>>> creativeModeTabModifiers = new HashMap<>();
    final List<SpawnAddition> spawns = new ArrayList<>();
    final List<FeatureAddition> features = new ArrayList<>();

    void subscribe(IEventBus modBus) {
        modBus.addListener(EntityAttributeCreationEvent.class, event -> this.attributes.forEach(registration -> registration.accept(event)));
        modBus.addListener(RegisterSpawnPlacementsEvent.class, event -> this.spawnPlacements.forEach(registration -> registration.accept(event)));
        modBus.addListener(BlockEntityTypeAddBlocksEvent.class, event -> this.supportedBlocks.forEach(registration -> registration.accept(event)));
        modBus.addListener(BuildCreativeModeTabContentsEvent.class, event -> {
            for (var modifier : this.creativeModeTabModifiers.getOrDefault(event.getTabKey(), List.of())) {
                modifier.accept((anchor, items) -> {
                    insertAfter(event, event.getParentEntries(), CreativeModeTab.TabVisibility.PARENT_TAB_ONLY, anchor, items);
                    insertAfter(event, event.getSearchEntries(), CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY, anchor, items);
                });
            }
        });
    }

    @Override
    public Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public <T> void registerAlias(Registry<T> registry, Identifier oldId, Identifier newId) {
        registry.addAlias(oldId, newId);
    }

    @Override
    public void registerEntityDataSerializer(Identifier id, EntityDataSerializer<?> serializer) {
        Registry.register(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, id, serializer);
    }

    @Override
    public void registerFlammable(Block block, int burnChance, int spreadChance) {
        ((FireBlock) Blocks.FIRE).setFlammable(block, burnChance, spreadChance);
    }

    @Override
    public void addSupportedBlocks(BlockEntityType<?> type, Block... blocks) {
        this.supportedBlocks.add(event -> event.modify(type, blocks));
    }

    @Override
    public CreativeModeTab.Builder creativeModeTabBuilder() {
        return CreativeModeTab.builder();
    }

    @Override
    public void modifyCreativeModeTab(ResourceKey<CreativeModeTab> tab, Consumer<CreativeModeTabOutput> modifier) {
        this.creativeModeTabModifiers.computeIfAbsent(tab, key -> new ArrayList<>()).add(modifier);
    }

    @Override
    public void registerAttributes(EntityType<? extends LivingEntity> type, Supplier<AttributeSupplier.Builder> attributes) {
        this.attributes.add(event -> event.put(type, attributes.get().build()));
    }

    @Override
    public <T extends Mob> void registerSpawnPlacement(EntityType<T> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate) {
        this.spawnPlacements.add(event -> event.register(type, placementType, heightmap, predicate, RegisterSpawnPlacementsEvent.Operation.REPLACE));
    }

    @Override
    public void addSpawn(Predicate<Holder<Biome>> biomes, MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
        this.spawns.add(new SpawnAddition(biomes, category, type, weight, minGroupSize, maxGroupSize));
    }

    @Override
    public void addFeature(Predicate<Holder<Biome>> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature) {
        this.features.add(new FeatureAddition(biomes, step, feature));
    }

    /**
     * Mirrors Fabric's behavior: items are inserted in order after the anchor, or appended if the tab lacks it.
     */
    private static void insertAfter(BuildCreativeModeTabContentsEvent event, SequencedSet<ItemStack> entries, CreativeModeTab.TabVisibility visibility, ItemLike anchor, ItemLike... items) {
        ItemStack previous = new ItemStack(anchor);
        for (ItemLike item : items) {
            ItemStack stack = new ItemStack(item);
            if (entries.contains(stack)) {
                continue;
            }
            if (entries.contains(previous)) {
                event.insertAfter(previous, stack, visibility);
            } else {
                event.accept(stack, visibility);
            }
            previous = stack;
        }
    }

    record SpawnAddition(Predicate<Holder<Biome>> biomes, MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
    }

    record FeatureAddition(Predicate<Holder<Biome>> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature) {
    }
}
