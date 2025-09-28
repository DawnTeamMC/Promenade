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
package fr.hugman.promenade.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import fr.hugman.promenade.block.PromenadeBlocks;

public class PromenadeItemGroups {
	public static final ItemGroup PROMENADE = of(PromenadeItemGroupKeys.PROMENADE, FabricItemGroup.builder()
			.displayName(Text.translatable("item_group.promenade.promenade"))
			.icon(() -> new ItemStack(PromenadeBlocks.BLUSH_SAKURA_SAPLING))
			.entries(PromenadeItemGroup::fill)
			.build());

	private static ItemGroup of(RegistryKey<ItemGroup> key, ItemGroup itemGroup) {
		return Registry.register(Registries.ITEM_GROUP, key, itemGroup);
	}
}
