package fr.hugman.promenade.data.provider;
import fr.hugman.promenade.entity.PromenadeEntityTypes;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.loot.PromenadeLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.FluidPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.function.BiConsumer;

public class PromenadeEntityLootTableProvider extends SimpleFabricLootTableProvider {
    public PromenadeEntityLootTableProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.ENTITY);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> output) {
        output.accept(PromenadeEntityTypes.CAPYBARA.getDefaultLootTable(), LootTable.lootTable());
        output.accept(
                PromenadeEntityTypes.DUCK.getDefaultLootTable(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.FEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(PromenadeItems.DUCK)
                                        .apply(SmeltItemFunction.smelted().when(this.createSmeltLootCondition()))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
        );

        output.accept(
                PromenadeEntityTypes.LUSH_CREEPER.getDefaultLootTable(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(Items.BONE_MEAL).setWeight(2).setQuality(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                                .add(LootItem.lootTableItem(Items.MOSS_BLOCK).setWeight(1).setQuality(5)
                                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                )
                        )
                        .withPool(LootPool.lootPool()
                                .add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS))
                                .when(
                                        LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)
                                        )
                                )
                        )
        );

        output.accept(PromenadeLootTables.TUBE_SUNKEN, sunken(Items.TUBE_CORAL, Items.DEAD_TUBE_CORAL));
        output.accept(PromenadeLootTables.BRAIN_SUNKEN, sunken(Items.BRAIN_CORAL, Items.DEAD_BRAIN_CORAL));
        output.accept(PromenadeLootTables.BUBBLE_SUNKEN, sunken(Items.BUBBLE_CORAL, Items.DEAD_BUBBLE_CORAL));
        output.accept(PromenadeLootTables.FIRE_SUNKEN, sunken(Items.FIRE_CORAL, Items.DEAD_FIRE_CORAL));
        output.accept(PromenadeLootTables.HORN_SUNKEN, sunken(Items.HORN_CORAL, Items.DEAD_HORN_CORAL));
    }

    /**
     * Mobs killed while on fire drop cooked meat. In 1.20.1, Fire Aspect sets them on fire, as there is no smelting enchantment tag.
     */
    protected final LootItemCondition.Builder createSmeltLootCondition() {
        return LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build())
        );
    }

    protected final LootTable.Builder sunken(Item coral, Item deadCoral) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.ARROW)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.BONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .add(
                                LootItem.lootTableItem(coral)
                                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setFluid(FluidPredicate.Builder.fluid().of(FluidTags.WATER).build())))
                                        .otherwise(LootItem.lootTableItem(deadCoral))
                        )
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                );
    }
}