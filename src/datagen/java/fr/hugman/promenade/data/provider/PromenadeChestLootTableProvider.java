/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.loot.PromenadeLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class PromenadeChestLootTableProvider extends SimpleFabricLootTableProvider {
	public PromenadeChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup, LootContextTypes.CHEST);
	}

	@Override
	public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> output) {
		output.accept(PromenadeLootTables.WITCH_HUT_CHEST,
				LootTable.builder().pool(LootPool.builder()
						.rolls(UniformLootNumberProvider.create(4, 7))
						.with(ItemEntry.builder(Items.GLASS_BOTTLE)
								.weight(5)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
						)
						.with(ItemEntry.builder(Items.MAGMA_CREAM).weight(2))
						.with(ItemEntry.builder(Items.MELON_SLICE)
								.weight(6)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
						)
						.with(ItemEntry.builder(Items.GLISTERING_MELON_SLICE).weight(2))
						.with(ItemEntry.builder(Items.CARROT)
								.weight(10)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4)))
						)
						.with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(2))
						.with(ItemEntry.builder(Items.APPLE)
								.weight(10)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 5)))
						)
						.with(ItemEntry.builder(Items.GOLDEN_APPLE))
						.with(ItemEntry.builder(Items.RABBIT_FOOT)
								.weight(3)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
						)
						.with(ItemEntry.builder(Items.SPIDER_EYE)
								.weight(6)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
						)
						.with(ItemEntry.builder(Items.EMERALD)
								.weight(4)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4)))
						)
				)
		);
	}
}