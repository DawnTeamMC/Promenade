package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PromenadeAdvancementProvider extends FabricAdvancementProvider {
    public PromenadeAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> consumer) {
        final var entities = wrapperLookup.lookupOrThrow(Registries.ENTITY_TYPE);
        final var fluids = wrapperLookup.lookupOrThrow(Registries.FLUID);
        final var blocks = wrapperLookup.lookupOrThrow(Registries.BLOCK);
        final var items = wrapperLookup.lookupOrThrow(Registries.ITEM);

        Advancement.Builder.advancement()
                .display(
                        PromenadeItems.MAPLE_SYRUP_BOTTLE,
                        Component.translatable("advancements.promenade.husbandry.harvest_maple_syrup.title"),
                        Component.translatable("advancements.promenade.husbandry.harvest_maple_syrup.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .parent(Identifier.parse("husbandry/safely_harvest_honey"))
                .addCriterion("harvest_maple_syrup", createPickMapleSyrup(blocks, items))
                .save(consumer, Promenade.MOD_ID + ":husbandry/harvest_maple_syrup");
        Advancement.Builder.advancement()
                .display(
                        Items.FIRE_CORAL,
                        Component.translatable("advancements.promenade.adventure.kill_sunken_outside_water.title"),
                        Component.translatable("advancements.promenade.adventure.kill_sunken_outside_water.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .parent(Identifier.parse("adventure/whos_the_pillager_now"))
                .addCriterion("kill_sunken_outside_water", createCrossbowSunkenOutsideWaterFromWater(fluids, entities))
                .rewards(AdvancementRewards.Builder.experience(65))
                .save(consumer, Promenade.MOD_ID + ":adventure/kill_sunken_outside_water");


    }

    public static Criterion<KilledByArrowTrigger.TriggerInstance> createCrossbowSunkenOutsideWaterFromWater(HolderGetter<Fluid> fluids, HolderGetter<EntityType<?>> entities) {
        return CriteriaTriggers.KILLED_BY_ARROW.createCriterion(
                new KilledByArrowTrigger.TriggerInstance(
                        // player is in water
                        Optional.of(ContextAwarePredicate.create(LocationCheck.checkLocation(LocationPredicate.Builder.location().setFluid(FluidPredicate.Builder.fluid().of(fluids.getOrThrow(FluidTags.WATER))), new BlockPos(0, 1, 0)).build())),
                        // entity is a sunken and is outside water
                        List.of(ContextAwarePredicate.create(
                                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(entities, PromenadeEntityTypes.SUNKEN)).build(),
                                InvertedLootItemCondition.invert(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity().located(LocationPredicate.Builder.location().setFluid(FluidPredicate.Builder.fluid().of(fluids.getOrThrow(FluidTags.WATER))))
                                )).build()
                        )),
                        MinMaxBounds.Ints.ANY,
                        Optional.empty()
                )
        );
    }


    public static Criterion<ItemUsedOnLocationTrigger.TriggerInstance> createPickMapleSyrup(HolderGetter<Block> blocks, HolderGetter<Item> items) {
        return CriteriaTriggers.ITEM_USED_ON_BLOCK.createCriterion(
                new ItemUsedOnLocationTrigger.TriggerInstance(
                        Optional.empty(),
                        Optional.of(ContextAwarePredicate.create(
                                LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(blocks, PromenadeBlocks.STRIPPED_MAPLE_LOG))).build(),
                                MatchTool.toolMatches(ItemPredicate.Builder.item().of(items, Items.GLASS_BOTTLE)).build()
                        ))
                )
        );
    }
}