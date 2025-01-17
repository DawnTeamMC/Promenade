package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.tag.PromenadeBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class PromenadeBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public PromenadeBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(PromenadeBlockTags.SAKURA_LOGS).add(PromenadeBlocks.SAKURA_LOG, PromenadeBlocks.STRIPPED_SAKURA_LOG, PromenadeBlocks.SAKURA_WOOD, PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        getOrCreateTagBuilder(PromenadeBlockTags.MAPLE_LOGS).add(PromenadeBlocks.MAPLE_LOG, PromenadeBlocks.STRIPPED_MAPLE_LOG, PromenadeBlocks.MAPLE_WOOD, PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        getOrCreateTagBuilder(PromenadeBlockTags.PALM_LOGS).add(PromenadeBlocks.PALM_LOG, PromenadeBlocks.STRIPPED_PALM_LOG, PromenadeBlocks.PALM_WOOD, PromenadeBlocks.STRIPPED_PALM_WOOD);
        getOrCreateTagBuilder(PromenadeBlockTags.DARK_AMARANTH_STEMS).add(PromenadeBlocks.DARK_AMARANTH_STEM, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM, PromenadeBlocks.DARK_AMARANTH_HYPHAE, PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);

        getOrCreateTagBuilder(PromenadeBlockTags.LEAVES).add(
                PromenadeBlocks.VERMILION_MAPLE_LEAVES,
                PromenadeBlocks.FULVOUS_MAPLE_LEAVES,
                PromenadeBlocks.MIKADO_MAPLE_LEAVES,
                PromenadeBlocks.SAP_MAPLE_LEAVES,
                PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS,
                PromenadeBlocks.COTTON_SAKURA_BLOSSOMS,
                PromenadeBlocks.PALM_LEAVES,
                PromenadeBlocks.SNOWY_SPRUCE_LEAVES
        );

        getOrCreateTagBuilder(PromenadeBlockTags.LEAF_PILES).add(
                PromenadeBlocks.OAK_LEAF_PILE,
                PromenadeBlocks.SPRUCE_LEAF_PILE,
                PromenadeBlocks.BIRCH_LEAF_PILE,
                PromenadeBlocks.JUNGLE_LEAF_PILE,
                PromenadeBlocks.ACACIA_LEAF_PILE,
                PromenadeBlocks.CHERRY_LEAF_PILE,
                PromenadeBlocks.DARK_OAK_LEAF_PILE,
                PromenadeBlocks.PALE_OAK_LEAF_PILE,
                PromenadeBlocks.MANGROVE_LEAF_PILE,
                PromenadeBlocks.AZALEA_LEAF_PILE,
                PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE,

                PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE,
                PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE,
                PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE,
                PromenadeBlocks.SAP_MAPLE_LEAF_PILE,
                PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE,
                PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE,
                PromenadeBlocks.PALM_LEAF_PILE
        );

        getOrCreateTagBuilder(PromenadeBlockTags.FLOWER_PILES).add(
                PromenadeBlocks.DANDELION_PILE,
                PromenadeBlocks.POPPY_PILE,
                PromenadeBlocks.BLUE_ORCHID_PILE,
                PromenadeBlocks.ALLIUM_PILE,
                PromenadeBlocks.AZURE_BLUET_PILE,
                PromenadeBlocks.RED_TULIP_PILE,
                PromenadeBlocks.ORANGE_TULIP_PILE,
                PromenadeBlocks.WHITE_TULIP_PILE,
                PromenadeBlocks.PINK_TULIP_PILE,
                PromenadeBlocks.OXEYE_DAISY_PILE,
                PromenadeBlocks.CORNFLOWER_PILE,
                PromenadeBlocks.LILY_OF_THE_VALLEY_PILE,
                PromenadeBlocks.WITHER_ROSE_PILE
        );

        getOrCreateTagBuilder(PromenadeBlockTags.DARK_AMARANTH_FUNGUS_GROWABLE_ON).add(PromenadeBlocks.BLACK_DYLIUM);
        getOrCreateTagBuilder(PromenadeBlockTags.DARK_AMARANTH_FUNGUS_PLACEABLE_ON)
                .addTag(PromenadeBlockTags.DARK_AMARANTH_FUNGUS_GROWABLE_ON)
                .add(Blocks.END_STONE)
                .add(Blocks.MYCELIUM);
        getOrCreateTagBuilder(PromenadeBlockTags.DARK_AMARANTH_ROOTS_PLACEABLE_ON).add(PromenadeBlocks.BLACK_DYLIUM);
    }
}