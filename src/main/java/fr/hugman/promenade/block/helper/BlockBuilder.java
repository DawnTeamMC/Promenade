package fr.hugman.promenade.block.helper;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlockBuilder {
    private static final Function<AbstractBlock.Settings, Block> DEFAULT_FACTORY = Block::new;
    private static final Supplier<Item.Settings> DEFAULT_ITEM_SETTINGS = () -> new Item.Settings().useBlockPrefixedTranslationKey();

    private Function<AbstractBlock.Settings, Block> factory = DEFAULT_FACTORY;
    private AbstractBlock.Settings settings;
    private int flameBurn = 0;
    private int flameSpread = 0;
    private Block stripsInto = null;

    private Item.Settings itemSettings = DEFAULT_ITEM_SETTINGS.get();
    private int fuelTime = 0;
    private float compostingChance = 0.0F;

    public BlockBuilder(Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        this.factory = factory;
        this.settings = settings;
    }

    public BlockBuilder(AbstractBlock.Settings settings) {
        this.settings = settings;
    }

    public BlockBuilder(Block block) {
        // retrieves the block's values
        this.settings = AbstractBlock.Settings.copyShallow(block);

        //TODO: copy flame burn and spread, strip into, and item settings
        // Crash if the block is not registered
    }

    public BlockBuilder factory(Function<AbstractBlock.Settings, Block> factory) {
        this.factory = factory;
        return this;
    }

    public BlockBuilder settings(AbstractBlock.Settings settings) {
        this.settings = settings;
        return this;
    }

    public BlockBuilder settings(Function<AbstractBlock.Settings, AbstractBlock.Settings> settingsConsumer) {
        this.settings = settingsConsumer.apply(this.settings);
        return this;
    }

    public BlockBuilder burnable(int burnTime, int spreadTime) {
        this.flameBurn = burnTime;
        this.flameSpread = spreadTime;
        return this;
    }

    public BlockBuilder stripsInto(Block block) {
        this.stripsInto = block;
        return this;
    }

    public BlockBuilder itemSettings(Item.Settings settings) {
        this.itemSettings = settings;
        return this;
    }

    public BlockBuilder itemSettings(Function<Item.Settings, Item.Settings> settings) {
        this.itemSettings = settings.apply(this.itemSettings);
        return this;
    }

    public BlockBuilder noItem() {
        this.itemSettings = null;
        return this;
    }

    public BlockBuilder fuelTime(int fuelTime) {
        this.fuelTime = fuelTime;
        return this;
    }

    public BlockBuilder compostingChance(float compostingChance) {
        this.compostingChance = compostingChance;
        return this;
    }

    public Block register(RegistryKey<Block> key) {
        if(this.factory == null) {
            throw new IllegalStateException("Cannot register block: factory is not set!");
        }
        var block = this.factory.apply(this.settings.registryKey(key));
        Registry.register(Registries.BLOCK, key, block);
        if(this.itemSettings instanceof Item.Settings) {
            var itemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, key.getValue());
            Registry.register(Registries.ITEM, itemRegistryKey, new BlockItem(block, this.itemSettings.registryKey(itemRegistryKey)));
        }
        //TODO: register burnable, compostable, and strip into...
        return block;
    }
}
