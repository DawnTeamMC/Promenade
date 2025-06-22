package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.Promenade;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Comparator;
import java.util.Set;

public final class PromenadeItemGroup {
    public static void fill(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
        Set<ItemStack> set = ItemStackSet.create();

        for (ItemGroup itemGroup : Registries.ITEM_GROUP) {
            if (itemGroup.getType() != ItemGroup.Type.SEARCH) {
                for (var stack : itemGroup.getSearchTabStacks()) {
                    if (isPromenade(Registries.ITEM.getEntry(stack.getItem()))) {
                        set.add(stack);
                    }
                }
            }
        }

        entries.addAll(set);

        // Vanilla Entity Variants
        //TODO: add spawn eggs

        // Paintings
        displayContext.lookup()
                .getOptional(RegistryKeys.PAINTING_VARIANT)
                .ifPresent(registryWrapper -> registryWrapper.streamEntries()
                        .filter(PromenadeItemGroup::isPromenade)
                        .sorted(PAINTING_VARIANT_COMPARATOR)
                        .forEach(
                                paintingVariantEntry -> {
                                    ItemStack itemStack = new ItemStack(Items.PAINTING);
                                    itemStack.set(DataComponentTypes.PAINTING_VARIANT, paintingVariantEntry);
                                    entries.add(itemStack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
                                }
                        )
                );
    }

    private static boolean isPromenade(RegistryEntry<?> entry) {
        return isPromenade(entry.getKey().orElseThrow());
    }


    private static boolean isPromenade(RegistryKey<?> key) {
        return key.getValue().getNamespace().equals(Promenade.MOD_ID);
    }

    // FROM Vanilla ItemGroups

    private static final Comparator<RegistryEntry<PaintingVariant>> PAINTING_VARIANT_COMPARATOR = Comparator.comparing(
            RegistryEntry::value, Comparator.comparingInt(PaintingVariant::getArea).thenComparing(PaintingVariant::width)
    );
}
