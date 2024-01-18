package fr.hugman.promenade.block;

import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.DawnBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class PromenadeBlocks {
    //TODO: move all blocks to this class in next major version
    public static final Block SPARKLER = new SparklerBlock(DawnBlockSettings.copyOf(Blocks.QUARTZ_BLOCK).item());

    public static void register(Registrar r) {
        r.add("sparkler", SPARKLER);
    }
}
