package com.hugman.newdawn.builder;

import com.hugman.newdawn.DawnFactory;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class BlockBuilder {
	protected final Block block;

	private BlockBuilder(Block block) {
		this.block = block;
	}

	public static BlockBuilder of(Identifier id, Block block) {
		Registry.register(Registry.BLOCK, id, block);
		return new BlockBuilder(block);
	}

	private ItemBuilder makeItem(BlockItem item) {
		ItemBuilder itemBuilder = DawnFactory.item(Registry.BLOCK.getId(this.block), item);
		if(itemBuilder.get() instanceof BlockItem blockItem) blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		return itemBuilder;
	}

	public BlockBuilder item(Item.Settings settings) {
		makeItem(new BlockItem(this.block, settings));
		return this;
	}

	public BlockBuilder item(ItemGroup group) {
		makeItem(new BlockItem(this.block, new Item.Settings().group(group)));
		return this;
	}

	public BlockBuilder item(Item.Settings settings, Consumer<ItemBuilder> supplier) {
		supplier.accept(makeItem(new BlockItem(this.block, settings)));
		return this;
	}

	public BlockBuilder item(ItemGroup group, Consumer<ItemBuilder> supplier) {
		supplier.accept(makeItem(new BlockItem(this.block, new Item.Settings().group(group))));
		return this;
	}

	/**
	 * @see FireBlock#registerDefaultFlammables() Vanilla flammability values
	 */
	public BlockBuilder flammability(int burn, int spread) {
		if(burn != 0 && spread != 0) {
			FlammableBlockRegistry.getDefaultInstance().add(this.block, burn, spread);
		}
		return this;
	}

	/**
	 * @see AxeItem Vanilla axe stripping values
	 */
	public BlockBuilder strippedFrom(Block strippedFrom) {
		StrippableBlockRegistry.register(strippedFrom, this.block);
		return this;
	}

	public Block get() {
		return this.block;
	}
}