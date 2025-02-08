package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class PromenadeStrippables {
    public static void register() {
        StrippableBlockRegistry.register(PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        StrippableBlockRegistry.register(PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        StrippableBlockRegistry.register(PromenadeBlocks.MAPLE_LOG, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        StrippableBlockRegistry.register(PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        StrippableBlockRegistry.register(PromenadeBlocks.PALM_LOG, PromenadeBlocks.STRIPPED_PALM_LOG);
        StrippableBlockRegistry.register(PromenadeBlocks.PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_WOOD);
        StrippableBlockRegistry.register(PromenadeBlocks.DARK_AMARANTH_STEM, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        StrippableBlockRegistry.register(PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
    }
}
