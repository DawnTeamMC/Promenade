package fr.hugman.promenade.registry;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.gen.stateprovider.MapleSyrupStateProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.CopyPropertiesProvider;

import java.util.function.BiConsumer;

public class PromenadeStrippables {
    /**
     * Gives each strippable block, with the state it turns into once stripped.
     * Fabric registers them at runtime, while NeoForge reads them from a data map generated from this list.
     */
    public static void register(BiConsumer<Block, BlockStateProvider> registrar) {
        register(registrar, PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        register(registrar, PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        // maple logs may drip syrup once stripped, so they use a dedicated state provider
        registrar.accept(PromenadeBlocks.MAPLE_LOG, new MapleSyrupStateProvider());
        register(registrar, PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        register(registrar, PromenadeBlocks.PALM_LOG, PromenadeBlocks.STRIPPED_PALM_LOG);
        register(registrar, PromenadeBlocks.PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_WOOD);
        register(registrar, PromenadeBlocks.DARK_AMARANTH_STEM, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        register(registrar, PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
    }

    private static void register(BiConsumer<Block, BlockStateProvider> registrar, Block log, Block strippedLog) {
        registrar.accept(log, new CopyPropertiesProvider(strippedLog));
    }
}
