package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.Promenade;
import java.util.Comparator;
import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.component.CustomData;
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

        // Paintings (mirrors vanilla's CreativeModeTabs#generatePresetPaintings)
        RegistryOps<Tag> ops = displayContext.holders().createSerializationContext(NbtOps.INSTANCE);
        displayContext.holders()
                .lookup(Registries.PAINTING_VARIANT)
                .ifPresent(registryWrapper -> registryWrapper.listElements()
                        .filter(PromenadeItemGroup::isPromenade)
                        .sorted(PAINTING_VARIANT_COMPARATOR)
                        .forEach(
                                paintingVariantEntry -> {
                                    CustomData entityData = CustomData.EMPTY
                                            .update(ops, Painting.VARIANT_MAP_CODEC, paintingVariantEntry)
                                            .getOrThrow()
                                            .update(nbt -> nbt.putString("id", "minecraft:painting"));
                                    ItemStack itemStack = new ItemStack(Items.PAINTING);
                                    itemStack.set(DataComponents.ENTITY_DATA, entityData);
                                    entries.accept(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                                }
                        )
                );
    }

    private static boolean isPromenade(Holder<?> entry) {
        return isPromenade(entry.unwrapKey().orElseThrow());
    }


    private static boolean isPromenade(ResourceKey<?> key) {
        return key.location().getNamespace().equals(Promenade.MOD_ID);
    }

    // FROM Vanilla ItemGroups

    private static final Comparator<Holder<PaintingVariant>> PAINTING_VARIANT_COMPARATOR = Comparator.comparing(
            Holder::value, Comparator.comparingInt(PaintingVariant::area).thenComparing(PaintingVariant::width)
    );
}
