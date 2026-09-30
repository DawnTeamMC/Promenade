package fr.hugman.promenade.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * The keys of a block and of its item. Newer versions of Minecraft ship this class; 1.21.1 does not.
 */
public record BlockItemId(ResourceKey<Block> block, ResourceKey<Item> item) {
    public static BlockItemId create(ResourceLocation blockId, ResourceLocation itemId) {
        return new BlockItemId(ResourceKey.create(Registries.BLOCK, blockId), ResourceKey.create(Registries.ITEM, itemId));
    }
}
