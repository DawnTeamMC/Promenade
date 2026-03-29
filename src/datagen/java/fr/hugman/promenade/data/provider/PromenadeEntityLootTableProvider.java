package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.loot.PromenadeLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class PromenadeEntityLootTableProvider extends SimpleFabricLootTableSubProvider {
    private final HolderLookup.Provider registries;

    public PromenadeEntityLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.ENTITY);
        this.registries = registryLookup.join();
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        final var entities = this.registries.lookupOrThrow(Registries.ENTITY_TYPE);
        final var fluids = this.registries.lookupOrThrow(Registries.FLUID);

        output.accept(PromenadeEntityTypes.CAPYBARA.getDefaultLootTable().orElseThrow(), LootTable.lootTable());
        output.accept(
                PromenadeEntityTypes.DUCK.getDefaultLootTable().orElseThrow(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.FEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(PromenadeItems.DUCK)
                                        .apply(SmeltItemFunction.smelted().when(this.createSmeltLootCondition()))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
        );

        output.accept(
                PromenadeEntityTypes.LUSH_CREEPER.getDefaultLootTable().orElseThrow(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(2).setQuality(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                                .add(LootItem.lootTableItem(Items.MOSS_BLOCK).setWeight(1).setQuality(5)
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS))
                                .when(
                                        LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(entities, EntityTypeTags.SKELETONS)
                                        )
                                )
                        )
        );

        output.accept(PromenadeLootTables.TUBE_SUNKEN, sunken(fluids, Items.TUBE_CORAL, Items.DEAD_TUBE_CORAL));
        output.accept(PromenadeLootTables.BRAIN_SUNKEN, sunken(fluids, Items.BRAIN_CORAL, Items.DEAD_BRAIN_CORAL));
        output.accept(PromenadeLootTables.BUBBLE_SUNKEN, sunken(fluids, Items.BUBBLE_CORAL, Items.DEAD_BUBBLE_CORAL));
        output.accept(PromenadeLootTables.FIRE_SUNKEN, sunken(fluids, Items.FIRE_CORAL, Items.DEAD_FIRE_CORAL));
        output.accept(PromenadeLootTables.HORN_SUNKEN, sunken(fluids, Items.HORN_CORAL, Items.DEAD_HORN_CORAL));
    }

    protected final AnyOfCondition.Builder createSmeltLootCondition() {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
                ),
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.DIRECT_ATTACKER,
                        EntityPredicate.Builder.entity()
                                .equipment(
                                        EntityEquipmentPredicate.Builder.equipment()
                                                .mainhand(
                                                        ItemPredicate.Builder.item()
                                                                .withComponents(
                                                                        DataComponentMatchers.Builder.components()
                                                                                .partial(
                                                                                        DataComponentPredicates.ENCHANTMENTS,
                                                                                        EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY)))
                                                                                )
                                                                                .build()
                                                                )
                                                )
                                )
                )
        );
    }

    protected final LootTable.Builder sunken(HolderGetter<Fluid> fluids, Item coral, Item deadCoral) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.ARROW)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.BONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .add(
                                LootItem.lootTableItem(coral)
                                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setFluid(FluidPredicate.Builder.fluid().of(fluids.getOrThrow(FluidTags.WATER)))))
                                        .otherwise(LootItem.lootTableItem(deadCoral))
                        )
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                );
    }
}