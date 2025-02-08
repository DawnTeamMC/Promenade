package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

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
