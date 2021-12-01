package com.hugman.promenade.compat.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.BlockBundle;
import com.hugman.promenade.init.PromenadeBundle;
import com.hugman.promenade.init.world.feature.OreBundle;
import io.github.haykam821.columns.block.ColumnBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class ColumnsBundle extends PromenadeBundle {
	public static final Block BLUNITE_COLUMN = add(new BlockCreator.Builder("blunite_column", ColumnBlock::new, FabricBlockSettings.copyOf(OreBundle.BLUNITE.getBlock(DefaultBlockTemplates.CUBE))).itemGroup(ItemGroup.DECORATIONS).build());
	public static final Block CARBONITE_COLUMN = add(new BlockCreator.Builder("carbonite_column", ColumnBlock::new, FabricBlockSettings.copyOf(OreBundle.CARBONITE.getBlock(DefaultBlockTemplates.CUBE))).itemGroup(ItemGroup.DECORATIONS).build());

	public static void init() {
		Promenade.LOGGER.info("Initialized Columns compatibility");
	}
}
