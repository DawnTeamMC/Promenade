package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.Promenade;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

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
        //TODO: add wolf spawn eggs

        // Paintings
        displayContext.lookup()
                .getOptional(RegistryKeys.PAINTING_VARIANT)
                .ifPresent(
                        registryWrapper -> addPaintings(
                                entries,
                                displayContext.lookup(),
                                registryWrapper,
                                PromenadeItemGroup::isPromenade,
                                ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS
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

    private static void addPaintings(
            ItemGroup.Entries entries,
            RegistryWrapper.WrapperLookup registries,
            RegistryWrapper.Impl<PaintingVariant> registryWrapper,
            Predicate<RegistryEntry<PaintingVariant>> filter,
            ItemGroup.StackVisibility stackVisibility
    ) {
        RegistryOps<NbtElement> registryOps = registries.getOps(NbtOps.INSTANCE);
        registryWrapper.streamEntries()
                .filter(filter)
                .sorted(PAINTING_VARIANT_COMPARATOR)
                .forEach(
                        paintingVariantEntry -> {
                            NbtComponent nbtComponent = NbtComponent.DEFAULT
                                    .with(registryOps, PaintingEntity.VARIANT_MAP_CODEC, paintingVariantEntry)
                                    .getOrThrow()
                                    .apply(nbt -> nbt.putString("id", "minecraft:painting"));
                            ItemStack itemStack = new ItemStack(Items.PAINTING);
                            itemStack.set(DataComponentTypes.ENTITY_DATA, nbtComponent);
                            entries.add(itemStack, stackVisibility);
                        }
                );
    }
}
