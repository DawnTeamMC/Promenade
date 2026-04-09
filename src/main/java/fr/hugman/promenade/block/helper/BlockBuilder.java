package fr.hugman.promenade.block.helper;

import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockBuilder {
    private static final Function<BlockBehaviour.Properties, Block> DEFAULT_FACTORY = Block::new;
    private static final Supplier<Item.Properties> DEFAULT_ITEM_SETTINGS = () -> new Item.Properties().useBlockDescriptionPrefix();

    private Function<BlockBehaviour.Properties, Block> factory = DEFAULT_FACTORY;
    private BlockBehaviour.Properties settings;

    private Item.Properties itemSettings = DEFAULT_ITEM_SETTINGS.get();

    public BlockBuilder(Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        this.factory = factory;
        this.settings = settings;
    }

    public BlockBuilder(BlockBehaviour.Properties settings) {
        this.settings = settings;
    }

    public BlockBuilder(Block block) {
        // retrieves the block's values
        this.settings = BlockBehaviour.Properties.ofLegacyCopy(block);
    }

    public BlockBuilder factory(Function<BlockBehaviour.Properties, Block> factory) {
        this.factory = factory;
        return this;
    }

    public BlockBuilder settings(BlockBehaviour.Properties settings) {
        this.settings = settings;
        return this;
    }

    public BlockBuilder settings(Function<BlockBehaviour.Properties, BlockBehaviour.Properties> settingsConsumer) {
        this.settings = settingsConsumer.apply(this.settings);
        return this;
    }

    public BlockBuilder itemSettings(Item.Properties settings) {
        this.itemSettings = settings;
        return this;
    }

    public BlockBuilder itemSettings(Function<Item.Properties, Item.Properties> settings) {
        this.itemSettings = settings.apply(this.itemSettings);
        return this;
    }

    public BlockBuilder noItem() {
        this.itemSettings = null;
        return this;
    }

    public Block register(ResourceKey<Block> key) {
        if (this.factory == null) {
            throw new IllegalStateException("Cannot register block: factory is not set!");
        }
        var block = this.factory.apply(this.settings.setId(key));
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        if (this.itemSettings instanceof Item.Properties) {
            var itemRegistryKey = ResourceKey.create(Registries.ITEM, key.identifier());
            Registry.register(BuiltInRegistries.ITEM, itemRegistryKey, new BlockItem(block, this.itemSettings.setId(itemRegistryKey)));
        }
        return block;
    }
}
