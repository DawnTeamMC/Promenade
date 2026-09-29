package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.Promenade;
import java.util.Comparator;
import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackLinkedSet;
import net.minecraft.world.item.Items;

public final class PromenadeItemGroup {
    public static void fill(CreativeModeTab.ItemDisplayParameters displayContext, CreativeModeTab.Output entries) {
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndComponentsSet();

        for (CreativeModeTab itemGroup : BuiltInRegistries.CREATIVE_MODE_TAB) {
            if (itemGroup.getType() != CreativeModeTab.Type.SEARCH) {
                for (var stack : itemGroup.getSearchTabDisplayItems()) {
                    if (isPromenade(BuiltInRegistries.ITEM.wrapAsHolder(stack.getItem()))) {
                        set.add(stack);
                    }
                }
            }
        }

        entries.acceptAll(set);

        // Vanilla Entity Variants
        //TODO: add spawn eggs

        // Paintings
        displayContext.holders()
                .lookup(Registries.PAINTING_VARIANT)
                .ifPresent(registryWrapper -> registryWrapper.listElements()
                        .filter(PromenadeItemGroup::isPromenade)
                        .sorted(PAINTING_VARIANT_COMPARATOR)
                        .forEach(
                                paintingVariantEntry -> {
                                    ItemStack itemStack = new ItemStack(Items.PAINTING);
                                    itemStack.set(DataComponents.PAINTING_VARIANT, paintingVariantEntry);
                                    entries.accept(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                                }
                        )
                );
    }

    private static boolean isPromenade(Holder<?> entry) {
        return isPromenade(entry.unwrapKey().orElseThrow());
    }


    private static boolean isPromenade(ResourceKey<?> key) {
        return key.identifier().getNamespace().equals(Promenade.MOD_ID);
    }

    // FROM Vanilla ItemGroups

    private static final Comparator<Holder<PaintingVariant>> PAINTING_VARIANT_COMPARATOR = Comparator.comparing(
            Holder::value, Comparator.comparingInt(PaintingVariant::area).thenComparing(PaintingVariant::width)
    );
}
