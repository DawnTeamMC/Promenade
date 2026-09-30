package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class PromenadeStrippables {
    /**
     * Gives each strippable block, with the block it turns into once stripped.
     * Fabric registers them at runtime, while NeoForge reads them from a data map generated from this list.
     * Stripped maple logs may drip syrup, which they decide themselves once placed.
     */
    public static void register(BiConsumer<Block, Block> registrar) {
        registrar.accept(PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        registrar.accept(PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        registrar.accept(PromenadeBlocks.MAPLE_LOG, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        registrar.accept(PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        registrar.accept(PromenadeBlocks.PALM_LOG, PromenadeBlocks.STRIPPED_PALM_LOG);
        registrar.accept(PromenadeBlocks.PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_WOOD);
        registrar.accept(PromenadeBlocks.DARK_AMARANTH_STEM, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        registrar.accept(PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
    }
}
