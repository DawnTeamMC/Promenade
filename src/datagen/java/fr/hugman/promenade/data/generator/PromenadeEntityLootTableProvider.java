package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.TagEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class PromenadeEntityLootTableProvider extends SimpleFabricLootTableProvider {
    private final RegistryWrapper.WrapperLookup registries;

    public PromenadeEntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ENTITY);
        this.registries = registryLookup.join();
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> output) {
        RegistryEntryLookup<EntityType<?>> entities = this.registries.getOrThrow(RegistryKeys.ENTITY_TYPE);

        output.accept(
                PromenadeEntityTypes.DUCK.getLootTableKey().orElseThrow(),
                LootTable.builder()
                        .pool(LootPool.builder()
                                .with(ItemEntry.builder(Items.FEATHER)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                        )
                        .pool(LootPool.builder()
                                .with(ItemEntry.builder(PromenadeItems.DUCK)
                                        .apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition()))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                        )
        );

        output.accept(
                PromenadeEntityTypes.LUSH_CREEPER.getLootTableKey().orElseThrow(),
                LootTable.builder()
                        .pool(LootPool.builder()
                                .with(ItemEntry.builder(Items.GUNPOWDER)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                        )
                        .pool(LootPool.builder()
                                .with(ItemEntry.builder(Items.BONE_MEAL).weight(2).quality(2)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                                .with(ItemEntry.builder(Items.MOSS_BLOCK).weight(1).quality(5)
                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registries, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                        )
                        .pool(LootPool.builder()
                                .with(TagEntry.expandBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS))
                                .conditionally(
                                        EntityPropertiesLootCondition.builder(
                                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.create().type(entities, EntityTypeTags.SKELETONS)
                                        )
                                )
                        )
        );

        //TODO: Sunken loot
    }

    protected final AnyOfLootCondition.Builder createSmeltLootCondition() {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return AnyOfLootCondition.builder(
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true))
                ),
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.DIRECT_ATTACKER,
                        EntityPredicate.Builder.create()
                                .equipment(
                                        EntityEquipmentPredicate.Builder.create()
                                                .mainhand(
                                                        ItemPredicate.Builder.create()
                                                                .subPredicate(
                                                                        ItemSubPredicateTypes.ENCHANTMENTS,
                                                                        EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY)))
                                                                )
                                                )
                                )
                )
        );
    }
}