package fr.hugman.promenade.block;

import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import fr.hugman.dawn.item.ItemGroupHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroups;

public class PromenadeBlocks {
    //TODO: move all blocks to this class in next major version
    public static final Block SPARKLER = new SparklerBlock(SparklerBlock.DEFAULT_BEHAVIORS, DawnBlockSettings.copyOf(Blocks.QUARTZ_BLOCK).item());

    public static void register(Registrar r) {
        r.add("sparkler", SPARKLER);

        appendItemGroups();
    }


    private static void appendItemGroups() {
        ItemGroupHelper.append(ItemGroups.REDSTONE, e -> e.addAfter(Blocks.NOTE_BLOCK, SPARKLER));
    }
}
