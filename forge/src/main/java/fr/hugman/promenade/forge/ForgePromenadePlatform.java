package fr.hugman.promenade.forge;

import fr.hugman.promenade.platform.CreativeModeTabOutput;
import fr.hugman.promenade.platform.PromenadePlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Most registrations are handed to Forge through events fired after the registration phase, so they are kept here
 * until then. Biome additions are applied by {@link PromenadeBiomeModifier}.
 */
public class ForgePromenadePlatform implements PromenadePlatform {
    private final List<Consumer<EntityAttributeCreationEvent>> attributes = new ArrayList<>();
    private final List<Consumer<SpawnPlacementRegisterEvent>> spawnPlacements = new ArrayList<>();
    private final Map<ResourceKey<CreativeModeTab>, List<Consumer<CreativeModeTabOutput>>> creativeModeTabModifiers = new HashMap<>();
    final List<SpawnAddition> spawns = new ArrayList<>();
    final List<FeatureAddition> features = new ArrayList<>();

    void subscribe(IEventBus modBus) {
        modBus.addListener((EntityAttributeCreationEvent event) -> this.attributes.forEach(registration -> registration.accept(event)));
        modBus.addListener((SpawnPlacementRegisterEvent event) -> this.spawnPlacements.forEach(registration -> registration.accept(event)));
        modBus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            for (var modifier : this.creativeModeTabModifiers.getOrDefault(event.getTabKey(), List.of())) {
                modifier.accept((anchor, items) -> insertAfter(event, anchor, items));
            }
        });
    }

    @Override
    public Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    /**
     * Forge locks vanilla's methods for the registries it manages, but they all stay open until their own registration
     * event, which is when Promenade registers its content.
     */
    @Override
    public <T, V extends T> V register(Registry<T> registry, ResourceKey<T> key, V value) {
        ForgeRegistry<T> forgeRegistry = RegistryManager.ACTIVE.getRegistry(registry.key());
        if (forgeRegistry == null) {
            return Registry.register(registry, key, value);
        }
        forgeRegistry.register(key.location(), value);
        return value;
    }

    @Override
    public <T> void registerAlias(Registry<T> registry, ResourceLocation oldId, ResourceLocation newId) {
        RegistryManager.ACTIVE.getRegistry(registry.key()).addAlias(oldId, newId);
    }

    @Override
    public void registerEntityDataSerializer(ResourceLocation id, EntityDataSerializer<?> serializer) {
        ForgeRegistries.ENTITY_DATA_SERIALIZERS.get().register(id, serializer);
    }

    @Override
    public void registerFlammable(Block block, int burnChance, int spreadChance) {
        ((FireBlock) Blocks.FIRE).setFlammable(block, burnChance, spreadChance);
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
    public <T extends Mob> void registerSpawnPlacement(EntityType<T> type, SpawnPlacements.Type placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate) {
        this.spawnPlacements.add(event -> event.register(type, placementType, heightmap, predicate, SpawnPlacementRegisterEvent.Operation.REPLACE));
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
     * Forge 1.20.1 keeps a single list of entries, whose visibility tells in which tabs they show.
     */
    private static void insertAfter(BuildCreativeModeTabContentsEvent event, ItemLike anchor, ItemLike... items) {
        var entries = event.getEntries();
        ItemStack previous = new ItemStack(anchor);
        for (ItemLike item : items) {
            ItemStack stack = new ItemStack(item);
            if (entries.contains(stack)) {
                continue;
            }
            // Appended at the end of the tab if it lacks the previous item
            entries.putAfter(previous, stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            previous = stack;
        }
    }

    record SpawnAddition(Predicate<Holder<Biome>> biomes, MobCategory category, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
    }

    record FeatureAddition(Predicate<Holder<Biome>> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature> feature) {
    }
}
