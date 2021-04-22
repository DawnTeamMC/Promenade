package com.hugman.promenade.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class OreContainerBundle extends Bundle {
	private final BlockCreator COAL_ORE, IRON_ORE, GOLD_ORE, COPPER_ORE, LAPIS_ORE, REDSTONE_ORE, EMERALD_ORE, DIAMOND_ORE;

	/**
	 * Creates a creator bundle containing all vanilla ores in a certain block.
	 */
	public OreContainerBundle(String name, AbstractBlock.Settings settings) {
		BlockCreator.Builder builder = new BlockCreator.Builder().settings(settings).itemGroup(ItemGroup.BUILDING_BLOCKS);
		COAL_ORE = put(builder.copy().name(name + "_coal_ore").blockProvider(s -> new OreBlock(s, UniformIntProvider.create(0, 2))).build());
		IRON_ORE = put(builder.copy().name(name + "_iron_ore").blockProvider(OreBlock::new).build());
		GOLD_ORE = put(builder.copy().name(name + "_gold_ore").blockProvider(OreBlock::new).build());
		COPPER_ORE = put(builder.copy().name(name + "_copper_ore").blockProvider(OreBlock::new).build());
		LAPIS_ORE = put(builder.copy().name(name + "_lapis_ore").blockProvider(s -> new OreBlock(s, UniformIntProvider.create(2, 5))).build());
		REDSTONE_ORE = put(builder.copy().name(name + "_redstone_ore").blockProvider(RedstoneOreBlock::new).settings(FabricBlockSettings.copyOf(settings).ticksRandomly().luminance((state) -> (Boolean)state.get(Properties.LIT) ? 9 : 0)).build());
		EMERALD_ORE = put(builder.copy().name(name + "_emerald_ore").blockProvider(s -> new OreBlock(s, UniformIntProvider.create(3, 7))).build());
		DIAMOND_ORE = put(builder.copy().name(name + "_diamond_ore").blockProvider(s -> new OreBlock(s, UniformIntProvider.create(3, 7))).build());
	}

	public Block coal() {
		return COAL_ORE.getValue();
	}

	public Block iron() {
		return IRON_ORE.getValue();
	}

	public Block gold() {
		return GOLD_ORE.getValue();
	}

	public Block copper() {
		return COPPER_ORE.getValue();
	}

	public Block lapis() {
		return LAPIS_ORE.getValue();
	}

	public Block redstone() {
		return REDSTONE_ORE.getValue();
	}

	public Block emerald() {
		return EMERALD_ORE.getValue();
	}

	public Block diamond() {
		return DIAMOND_ORE.getValue();
	}
}
