package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.gen.stateprovider.MapleSyrupStateProvider;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.CopyPropertiesProvider;

public class PromenadeStrippables {
    public static void register() {
        register(PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        register(PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        // maple logs may drip syrup once stripped, so they use a dedicated state provider
        BlockTransformerHelper.registerStripping(PromenadeBlocks.MAPLE_LOG, new MapleSyrupStateProvider());
        register(PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        register(PromenadeBlocks.PALM_LOG, PromenadeBlocks.STRIPPED_PALM_LOG);
        register(PromenadeBlocks.PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_WOOD);
        register(PromenadeBlocks.DARK_AMARANTH_STEM, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        register(PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
    }

    private static void register(Block log, Block strippedLog) {
        BlockTransformerHelper.registerStripping(log, new CopyPropertiesProvider(strippedLog));
    }
}
