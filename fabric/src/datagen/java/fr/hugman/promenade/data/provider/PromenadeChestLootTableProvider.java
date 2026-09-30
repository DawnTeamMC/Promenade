package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.loot.PromenadeLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class PromenadeChestLootTableProvider extends SimpleFabricLootTableProvider {
    public PromenadeChestLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(PromenadeLootTables.WITCH_HUT_CHEST,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(4, 7))
                        .add(LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                .setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        )
                        .add(LootItem.lootTableItem(Items.MAGMA_CREAM).setWeight(2))
                        .add(LootItem.lootTableItem(Items.MELON_SLICE)
                                .setWeight(6)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                        )
                        .add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).setWeight(2))
                        .add(LootItem.lootTableItem(Items.CARROT)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                        )
                        .add(LootItem.lootTableItem(Items.GOLDEN_CARROT).setWeight(2))
                        .add(LootItem.lootTableItem(Items.APPLE)
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))
                        )
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
                        .add(LootItem.lootTableItem(Items.RABBIT_FOOT)
                                .setWeight(3)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                        )
                        .add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                .setWeight(6)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                        )
                        .add(LootItem.lootTableItem(Items.EMERALD)
                                .setWeight(4)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                        )
                )
        );
    }
}