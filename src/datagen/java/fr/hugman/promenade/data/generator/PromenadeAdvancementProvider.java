package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.advancement.criterion.KilledByArrowCriterion;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PromenadeAdvancementProvider extends FabricAdvancementProvider {
    public PromenadeAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        final var entities = wrapperLookup.getOrThrow(RegistryKeys.ENTITY_TYPE);
        final var fluids = wrapperLookup.getOrThrow(RegistryKeys.FLUID);
        final var blocks = wrapperLookup.getOrThrow(RegistryKeys.BLOCK);
        final var items = wrapperLookup.getOrThrow(RegistryKeys.ITEM);

        Advancement.Builder.create()
                .display(
                        PromenadeItems.MAPLE_SYRUP_BOTTLE,
                        Text.translatable("advancements.promenade.husbandry.harvest_maple_syrup.title"),
                        Text.translatable("advancements.promenade.husbandry.harvest_maple_syrup.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .parent(Identifier.of("husbandry/safely_harvest_honey"))
                .criterion("harvest_maple_syrup", createPickMapleSyrup(blocks, items))
                .build(consumer, Promenade.MOD_ID + ":husbandry/harvest_maple_syrup");
        Advancement.Builder.create()
                .display(
                        Items.FIRE_CORAL,
                        Text.translatable("advancements.promenade.adventure.kill_sunken_outside_water.title"),
                        Text.translatable("advancements.promenade.adventure.kill_sunken_outside_water.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .parent(Identifier.of("adventure/whos_the_pillager_now"))
                .criterion("kill_sunken_outside_water", createCrossbowSunkenOutsideWaterFromWater(fluids, entities))
                .rewards(AdvancementRewards.Builder.experience(65))
                .build(consumer, Promenade.MOD_ID + ":adventure/kill_sunken_outside_water");


    }

    public static AdvancementCriterion<KilledByArrowCriterion.Conditions> createCrossbowSunkenOutsideWaterFromWater(RegistryEntryLookup<Fluid> fluids, RegistryEntryLookup<EntityType<?>> entities) {
        return Criteria.KILLED_BY_ARROW.create(
                new KilledByArrowCriterion.Conditions(
                        // player is in water
                        Optional.of(LootContextPredicate.create(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().fluid(FluidPredicate.Builder.create().tag(fluids.getOrThrow(FluidTags.WATER))), new BlockPos(0, 1, 0)).build())),
                        // entity is a sunken and is outside water
                        List.of(LootContextPredicate.create(
                                EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(entities, PromenadeEntityTypes.SUNKEN)).build(),
                                InvertedLootCondition.builder(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.create().location(LocationPredicate.Builder.create().fluid(FluidPredicate.Builder.create().tag(fluids.getOrThrow(FluidTags.WATER))))
                                )).build()
                        )),
                        NumberRange.IntRange.ANY,
                        Optional.empty()
                )
        );
    }


    public static AdvancementCriterion<ItemCriterion.Conditions> createPickMapleSyrup(RegistryEntryLookup<Block> blocks, RegistryEntryLookup<Item> items) {
        return Criteria.ITEM_USED_ON_BLOCK.create(
                new ItemCriterion.Conditions(
                        Optional.empty(),
                        Optional.of(LootContextPredicate.create(
                                LocationCheckLootCondition.builder(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(blocks, PromenadeBlocks.STRIPPED_MAPLE_LOG))).build(),
                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(items, Items.GLASS_BOTTLE)).build()
                        ))
                )
        );
    }
}