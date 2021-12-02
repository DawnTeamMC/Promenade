package com.hugman.promenade.util;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.PlantPileBlock;
import com.hugman.dawn.api.util.BlockTemplate;
import net.minecraft.item.ItemGroup;

public final class BlockTemplateUtil {
	public static final BlockTemplate LEAF_PILE = new BlockTemplate(PlantPileBlock::new, "leaf_pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED);
	public static final BlockTemplate PLANT_PILE = new BlockTemplate(PlantPileBlock::new, "pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED);
}
