package fr.hugman.promenade.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.util.Collections;
import java.util.function.Predicate;

public final class ItemGroupHelper {
	public static void append(ItemGroup group, ItemGroupEvents.ModifyEntries modifier) {
		ItemGroupEvents.modifyEntriesEvent(group).register(modifier);
	}

	public static void appendSpawnEgg(Item spawnEgg) {
		String path = Registries.ITEM.getId(spawnEgg).getPath();

		Predicate<ItemStack> predicate = stack1 -> {
			String path1 = Registries.ITEM.getId(stack1.getItem()).getPath();
			for(ItemStack stack2 : ItemGroups.SPAWN_EGGS.getDisplayStacks()) {
				String path2 = Registries.ITEM.getId(stack2.getItem()).getPath();
				if(path1.matches(".*_spawn_egg") && path2.matches(".*_spawn_egg")) {
					// check if path is lexicographically between path1 and path2
					if(path.compareTo(path1) > 0 && path.compareTo(path2) < 0) {
						return true;
					}
				}
			}
			return false;
		};
		append(ItemGroups.SPAWN_EGGS, e -> e.addAfter(predicate, Collections.singleton(new ItemStack(spawnEgg)), ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS));
	}
}
