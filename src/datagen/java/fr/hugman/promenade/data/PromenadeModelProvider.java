package fr.hugman.promenade.data;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class PromenadeModelProvider extends FabricModelProvider {
    private static final int SAP_MAPLE_COLOR = 10931465;
    private static final int PALM_COLOR = 8237614;

    public PromenadeModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        PromenadeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels).forEach(family -> gen.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));

        //TODO: re-use vanilla leaves textures for this...
        gen.registerTintedBlockAndItem(PromenadeBlocks.OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.OAK_LEAVES), -12012264);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.SPRUCE_LEAVES), -10380959);
        gen.registerTintedBlockAndItem(PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.BIRCH_LEAVES), -8345771);
        gen.registerTintedBlockAndItem(PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.JUNGLE_LEAVES), -12012264);
        gen.registerTintedBlockAndItem(PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.ACACIA_LEAVES), -12012264);
        gen.registerSingleton(PromenadeBlocks.CHERRY_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.CHERRY_LEAVES));
        gen.registerTintedBlockAndItem(PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.DARK_OAK_LEAVES), -12012264);
        gen.registerSingleton(PromenadeBlocks.PALE_OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.PALE_OAK_LEAVES));
        gen.registerTintedBlockAndItem(PromenadeBlocks.MANGROVE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.MANGROVE_LEAVES), -7158200);
        gen.registerSingleton(PromenadeBlocks.AZALEA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.AZALEA_LEAVES));
        gen.registerSingleton(PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.FLOWERING_AZALEA_LEAVES));

        gen.registerSingleton(PromenadeBlocks.DANDELION_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.POPPY_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.BLUE_ORCHID_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.ALLIUM_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.AZURE_BLUET_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.RED_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.ORANGE_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.WHITE_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.PINK_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.OXEYE_DAISY_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.CORNFLOWER_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.LILY_OF_THE_VALLEY_PILE, PromenadeTexturedModels.PILE);
        gen.registerSingleton(PromenadeBlocks.WITHER_ROSE_PILE, PromenadeTexturedModels.PILE);

        //TODO: snowy leaves

        gen.registerLog(PromenadeBlocks.SAKURA_LOG).uvLockedLog(PromenadeBlocks.SAKURA_LOG).wood(PromenadeBlocks.SAKURA_WOOD);
        gen.registerLog(PromenadeBlocks.STRIPPED_SAKURA_LOG).uvLockedLog(PromenadeBlocks.STRIPPED_SAKURA_LOG).wood(PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_SAKURA_LOG, PromenadeBlocks.SAKURA_HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.BLUSH_SAKURA_SAPLING, PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.COTTON_SAKURA_SAPLING, PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerSingleton(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS));
        gen.registerSingleton(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS));

        gen.registerLog(PromenadeBlocks.MAPLE_LOG).uvLockedLog(PromenadeBlocks.MAPLE_LOG).wood(PromenadeBlocks.MAPLE_WOOD);
        //TODO: stripped maple log side
        gen.registerLog(PromenadeBlocks.STRIPPED_MAPLE_LOG).uvLockedLog(PromenadeBlocks.STRIPPED_MAPLE_LOG).wood(PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_MAPLE_LOG, PromenadeBlocks.MAPLE_HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.SAP_MAPLE_SAPLING, PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.VERMILION_MAPLE_SAPLING, PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.FULVOUS_MAPLE_SAPLING, PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.MIKADO_MAPLE_SAPLING, PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SAP_MAPLE_LEAVES, TexturedModel.LEAVES, SAP_MAPLE_COLOR);
        gen.registerSingleton(PromenadeBlocks.VERMILION_MAPLE_LEAVES, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.MIKADO_MAPLE_LEAVES, TexturedModel.LEAVES);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.SAP_MAPLE_LEAVES), SAP_MAPLE_COLOR);
        gen.registerSingleton(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.VERMILION_MAPLE_LEAVES));
        gen.registerSingleton(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.FULVOUS_MAPLE_LEAVES));
        gen.registerSingleton(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.MIKADO_MAPLE_LEAVES));

        gen.registerLog(PromenadeBlocks.PALM_LOG).uvLockedLog(PromenadeBlocks.PALM_LOG).wood(PromenadeBlocks.PALM_WOOD);
        gen.registerLog(PromenadeBlocks.STRIPPED_PALM_LOG).uvLockedLog(PromenadeBlocks.STRIPPED_PALM_LOG).wood(PromenadeBlocks.STRIPPED_PALM_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_PALM_LOG, PromenadeBlocks.PALM_HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.PALM_SAPLING, PromenadeBlocks.POTTED_PALM_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerTintedBlockAndItem(PromenadeBlocks.PALM_LEAVES, TexturedModel.LEAVES, PALM_COLOR);
        gen.registerTintedBlockAndItem(PromenadeBlocks.PALM_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.PALM_LEAVES), PALM_COLOR);

        gen.registerSimpleCubeAll(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        gen.registerLog(PromenadeBlocks.DARK_AMARANTH_STEM).uvLockedLog(PromenadeBlocks.DARK_AMARANTH_STEM).wood(PromenadeBlocks.DARK_AMARANTH_HYPHAE);
        gen.registerLog(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).uvLockedLog(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).wood(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM, PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.DARK_AMARANTH_FUNGUS, PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS, BlockStateModelGenerator.CrossType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        gen.register(PromenadeItems.SAKURA_BOAT, Models.GENERATED);
        gen.register(PromenadeItems.SAKURA_CHEST_BOAT, Models.GENERATED);
        gen.register(PromenadeItems.MAPLE_BOAT, Models.GENERATED);
        gen.register(PromenadeItems.MAPLE_CHEST_BOAT, Models.GENERATED);
        gen.register(PromenadeItems.MAPLE_SYRUP_BOTTLE, Models.GENERATED);
        gen.register(PromenadeItems.PALM_BOAT, Models.GENERATED);
        gen.register(PromenadeItems.PALM_CHEST_BOAT, Models.GENERATED);
        gen.register(PromenadeItems.BLUEBERRIES, Models.GENERATED);
        gen.register(PromenadeItems.BANANA, Models.GENERATED);
        gen.register(PromenadeItems.APRICOT, Models.GENERATED);
        gen.register(PromenadeItems.MANGO, Models.GENERATED);
        gen.register(PromenadeItems.DUCK, Models.GENERATED);
        gen.register(PromenadeItems.COOKED_DUCK, Models.GENERATED);
        //TODO: review colors
        gen.registerSpawnEgg(PromenadeItems.CAPYBARA_SPAWN_EGG, 0xa0704e, 0x433930);
        gen.registerSpawnEgg(PromenadeItems.DUCK_SPAWN_EGG, 10592673, 15904341);
        gen.registerSpawnEgg(PromenadeItems.LUSH_CREEPER_SPAWN_EGG, 4347181, 4262661);
        gen.registerSpawnEgg(PromenadeItems.SUNKEN_SPAWN_EGG, 12233882, 6191682);
    }
}
