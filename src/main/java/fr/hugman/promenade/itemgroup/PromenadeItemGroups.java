package fr.hugman.promenade.itemgroup;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PromenadeItemGroups {
    public static final CreativeModeTab PROMENADE = of(PromenadeItemGroupKeys.PROMENADE, FabricCreativeModeTab.builder()
            .title(Component.translatable("item_group.promenade.promenade"))
            .icon(() -> new ItemStack(PromenadeBlocks.BLUSH_SAKURA_SAPLING))
            .displayItems(PromenadeItemGroup::fill)
            .build());

    private static CreativeModeTab of(ResourceKey<CreativeModeTab> key, CreativeModeTab itemGroup) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, itemGroup);
    }
}
