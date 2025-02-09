package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.MoaiBlock;
import fr.hugman.promenade.block.MoaiType;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.block.SnowyLeavesBlock;
import fr.hugman.promenade.block.property.PromenadeBlockProperties;
import fr.hugman.promenade.data.PromenadeBlockFamilies;
import fr.hugman.promenade.data.model.PromenadeModels;
import fr.hugman.promenade.data.model.PromenadeTextureMaps;
import fr.hugman.promenade.data.model.PromenadeTexturedModels;
import fr.hugman.promenade.item.PromenadeItems;
import fr.hugman.promenade.world.biome.PromenadeFoliageColors;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.FoliageColors;

public class PromenadeModelProvider extends FabricModelProvider {
    public PromenadeModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        PromenadeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels).forEach(family -> gen.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));

        gen.registerTintedBlockAndItem(PromenadeBlocks.OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.OAK_LEAVES), FoliageColors.DEFAULT);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.SPRUCE_LEAVES), FoliageColors.SPRUCE);
        gen.registerTintedBlockAndItem(PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.BIRCH_LEAVES), FoliageColors.BIRCH);
        gen.registerTintedBlockAndItem(PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.JUNGLE_LEAVES), FoliageColors.DEFAULT);
        gen.registerTintedBlockAndItem(PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.ACACIA_LEAVES), FoliageColors.DEFAULT);
        gen.registerSingleton(PromenadeBlocks.CHERRY_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.CHERRY_LEAVES));
        gen.registerTintedBlockAndItem(PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.DARK_OAK_LEAVES), FoliageColors.DEFAULT);
        gen.registerSingleton(PromenadeBlocks.PALE_OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.PALE_OAK_LEAVES));
        gen.registerTintedBlockAndItem(PromenadeBlocks.MANGROVE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.MANGROVE_LEAVES), FoliageColors.MANGROVE);
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

        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_OAK_LEAVES, Blocks.OAK_LEAVES, FoliageColors.DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_SPRUCE_LEAVES, Blocks.SPRUCE_LEAVES, FoliageColors.SPRUCE);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_BIRCH_LEAVES, Blocks.BIRCH_LEAVES, FoliageColors.BIRCH);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_JUNGLE_LEAVES, Blocks.JUNGLE_LEAVES, FoliageColors.DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_ACACIA_LEAVES, Blocks.ACACIA_LEAVES, FoliageColors.DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_CHERRY_LEAVES, Blocks.CHERRY_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, Blocks.DARK_OAK_LEAVES, FoliageColors.DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_PALE_OAK_LEAVES, Blocks.PALE_OAK_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_MANGROVE_LEAVES, Blocks.MANGROVE_LEAVES, FoliageColors.MANGROVE);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_AZALEA_LEAVES, Blocks.AZALEA_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES);

        gen.registerLog(PromenadeBlocks.SAKURA_LOG).uvLockedLog(PromenadeBlocks.SAKURA_LOG).wood(PromenadeBlocks.SAKURA_WOOD);
        gen.registerLog(PromenadeBlocks.STRIPPED_SAKURA_LOG).uvLockedLog(PromenadeBlocks.STRIPPED_SAKURA_LOG).wood(PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_SAKURA_LOG, PromenadeBlocks.SAKURA_HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.BLUSH_SAKURA_SAPLING, PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.COTTON_SAKURA_SAPLING, PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerSingleton(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS));
        gen.registerSingleton(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS));

        gen.registerLog(PromenadeBlocks.MAPLE_LOG).log(PromenadeBlocks.MAPLE_LOG).wood(PromenadeBlocks.MAPLE_WOOD);
        registerDripLog(gen, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        gen.registerLog(PromenadeBlocks.STRIPPED_MAPLE_LOG).wood(PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_MAPLE_LOG, PromenadeBlocks.MAPLE_HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.SAP_MAPLE_SAPLING, PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.VERMILION_MAPLE_SAPLING, PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.FULVOUS_MAPLE_SAPLING, PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.MIKADO_MAPLE_SAPLING, PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SAP_MAPLE_LEAVES, TexturedModel.LEAVES, PromenadeFoliageColors.SAP_MAPLE);
        gen.registerSingleton(PromenadeBlocks.VERMILION_MAPLE_LEAVES, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, TexturedModel.LEAVES);
        gen.registerSingleton(PromenadeBlocks.MIKADO_MAPLE_LEAVES, TexturedModel.LEAVES);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.SAP_MAPLE_LEAVES), PromenadeFoliageColors.SAP_MAPLE);
        gen.registerSingleton(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.VERMILION_MAPLE_LEAVES));
        gen.registerSingleton(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.FULVOUS_MAPLE_LEAVES));
        gen.registerSingleton(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.MIKADO_MAPLE_LEAVES));

        gen.registerLog(PromenadeBlocks.PALM_LOG).log(PromenadeBlocks.PALM_LOG).wood(PromenadeBlocks.PALM_WOOD);
        gen.registerLog(PromenadeBlocks.STRIPPED_PALM_LOG).log(PromenadeBlocks.STRIPPED_PALM_LOG).wood(PromenadeBlocks.STRIPPED_PALM_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_PALM_LOG, PromenadeBlocks.PALM_HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.PALM_SAPLING, PromenadeBlocks.POTTED_PALM_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerTintedBlockAndItem(PromenadeBlocks.PALM_LEAVES, TexturedModel.LEAVES, PromenadeFoliageColors.PALM);
        gen.registerTintedBlockAndItem(PromenadeBlocks.PALM_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.PALM_LEAVES), PromenadeFoliageColors.PALM);
        gen.registerTintableCrossBlockState(PromenadeBlocks.PALM_HANGING_LEAVES, BlockStateModelGenerator.CrossType.TINTED);
        this.registerTintedItem(gen, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeFoliageColors.PALM);

        gen.registerNetherrackBottomCustomTop(PromenadeBlocks.DARK_AMARANTH_NYLIUM);
        gen.registerSimpleCubeAll(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        gen.registerRoots(PromenadeBlocks.DARK_AMARANTH_ROOTS, PromenadeBlocks.POTTED_DARK_AMARANTH_ROOTS);
        gen.registerLog(PromenadeBlocks.DARK_AMARANTH_STEM).stem(PromenadeBlocks.DARK_AMARANTH_STEM).wood(PromenadeBlocks.DARK_AMARANTH_HYPHAE);
        gen.registerLog(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).stem(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).wood(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM, PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.DARK_AMARANTH_FUNGUS, PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS, BlockStateModelGenerator.CrossType.NOT_TINTED);

        gen.registerSimpleCubeAll(PromenadeBlocks.SOUL_SHROOMLIGHT);

        this.registerFacingPlantPart(gen, PromenadeBlocks.COILED_VINES, PromenadeBlocks.COILED_VINES_PLANT, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerItemModel(PromenadeBlocks.COILED_VINES, "_plant");

        this.registerMoai(gen);

        this.registerBlueberryBush(gen);
    }

    public final void registerDripLog(BlockStateModelGenerator gen, Block block) {
        var textureMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.END, TextureMap.getSubId(block, "_top")).put(TextureKey.PARTICLE, TextureMap.getId(block));
        var textureMapDrip = new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_drip")).put(TextureKey.END, TextureMap.getSubId(block, "_top")).put(TextureKey.PARTICLE, TextureMap.getId(block));

        Identifier verticalModelId = Models.CUBE_COLUMN.upload(block, textureMap, gen.modelCollector);
        Identifier horizontalModelId = Models.CUBE_COLUMN_HORIZONTAL.upload(block, textureMap, gen.modelCollector);

        Identifier verticalModelIdDrip = Models.CUBE_COLUMN.upload(block, "_drip", textureMapDrip, gen.modelCollector);
        Identifier horizontalModelIdDrip = Models.CUBE_COLUMN_HORIZONTAL.upload(block, "_drip", textureMapDrip, gen.modelCollector);


        gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(
                BlockStateVariantMap.create(Properties.AXIS, PromenadeBlockProperties.DRIP)
                        .register(Direction.Axis.Y, false, BlockStateVariant.create().put(VariantSettings.MODEL, verticalModelId))
                        .register(Direction.Axis.Z, false, BlockStateVariant.create().put(VariantSettings.MODEL, horizontalModelId).put(VariantSettings.X, VariantSettings.Rotation.R90))
                        .register(
                                Direction.Axis.X, false,
                                BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, horizontalModelId)
                                        .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                        )
                        .register(Direction.Axis.Y, true, BlockStateVariant.create().put(VariantSettings.MODEL, verticalModelIdDrip))
                        .register(Direction.Axis.Z, true, BlockStateVariant.create().put(VariantSettings.MODEL, horizontalModelIdDrip).put(VariantSettings.X, VariantSettings.Rotation.R90))
                        .register(
                                Direction.Axis.X, true,
                                BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, horizontalModelIdDrip)
                                        .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                        .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                        )
        ));
    }

    public final void registerTintedItem(BlockStateModelGenerator gen, Block block, int constant) {
        registerTintedItem(gen, block, ItemModels.constantTintSource(constant));
    }

    public final void registerTintedItem(BlockStateModelGenerator gen, Block block, TintSource tint) {
        Identifier identifier = gen.uploadBlockItemModel(block.asItem(), block);
        gen.registerTintedItemModel(block, identifier, tint);
    }

    public final void registerBlueberryBush(BlockStateModelGenerator gen) {
        gen.registerItemModel(PromenadeItems.BLUEBERRIES);
        gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(PromenadeBlocks.BLUEBERRY_BUSH)
                .coordinate(
                        BlockStateVariantMap.create(Properties.AGE_3).register(
                                stage -> BlockStateVariant.create().put(VariantSettings.MODEL, gen.createSubModel(PromenadeBlocks.BLUEBERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross))
                        )
                )
        );
    }

    private void registerMoai(BlockStateModelGenerator gen) {
        // maybe check if we can't generate the models too?
        gen.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(PromenadeBlocks.MOAI)
                                .coordinate(
                                        BlockStateVariantMap.create(MoaiBlock.TYPE)
                                                .register(MoaiType.SINGLE, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(PromenadeBlocks.MOAI)))
                                                .register(MoaiType.TOP, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(PromenadeBlocks.MOAI, "_top")))
                                                .register(MoaiType.BOTTOM, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(PromenadeBlocks.MOAI, "_bottom")))
                                )
                                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                );
    }

    private void registerSnowyLeaves(BlockStateModelGenerator gen, Block snowyLeaves, Block normalLeaves) {
        registerSnowyLeaves(gen, snowyLeaves, normalLeaves, 0);
    }


    private void registerSnowyLeaves(BlockStateModelGenerator gen, Block snowyLeaves, Block normalLeaves, int tint) {
        var bottomModel = gen.createSubModel(snowyLeaves, "_bottom", PromenadeModels.BOTTOM_SNOWY_LEAVES, block -> PromenadeTextureMaps.snowyLeaves(snowyLeaves, normalLeaves));
        if (tint != 0) {
            gen.registerTintedItemModel(snowyLeaves, bottomModel, ItemModels.constantTintSource(tint));
        } else {
            gen.registerItemModel(snowyLeaves.asItem(), bottomModel);
        }
        gen.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(snowyLeaves).coordinate(
                        BlockStateVariantMap.create(SnowyLeavesBlock.BOTTOM)
                                .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, bottomModel))
                                .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, gen.createSubModel(snowyLeaves, "", Models.CUBE_ALL, TextureMap::all)))
                )
        );
    }

    public final void registerFacingPlantPart(BlockStateModelGenerator gen, Block plant, Block plantStem, BlockStateModelGenerator.CrossType tintType) {
        this.registerFacingTintableCrossBlockState(gen, plant, tintType);
        this.registerFacingTintableCrossBlockState(gen, plantStem, tintType);
    }

    public final void registerFacingTintableCrossBlockState(BlockStateModelGenerator gen, Block block, BlockStateModelGenerator.CrossType tintType) {
        TextureMap textureMap = tintType.getTextureMap(block);
        this.registerFacingTintableCrossBlockState(gen, block, tintType, textureMap);
    }

    public final void registerFacingTintableCrossBlockState(BlockStateModelGenerator gen, Block block, BlockStateModelGenerator.CrossType tintType, TextureMap crossTexture) {
        Identifier identifier = tintType.getCrossModel().upload(block, crossTexture, gen.modelCollector);
        gen.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(block, identifier)
                        .coordinate(gen.createUpDefaultFacingVariantMap())
        );
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

        gen.register(PromenadeItems.BANANA, Models.GENERATED);
        gen.register(PromenadeItems.APRICOT, Models.GENERATED);
        gen.register(PromenadeItems.MANGO, Models.GENERATED);
        gen.register(PromenadeItems.DUCK, Models.GENERATED);
        gen.register(PromenadeItems.COOKED_DUCK, Models.GENERATED);

        gen.register(PromenadeItems.BOVINE_BANNER_PATTERN, Models.GENERATED);

        //TODO: review colors
        gen.registerSpawnEgg(PromenadeItems.CAPYBARA_SPAWN_EGG, 0xa0704e, 0x433930);
        gen.registerSpawnEgg(PromenadeItems.DUCK_SPAWN_EGG, 10592673, 15904341);
        gen.registerSpawnEgg(PromenadeItems.LUSH_CREEPER_SPAWN_EGG, 4347181, 4262661);
        gen.registerSpawnEgg(PromenadeItems.SUNKEN_SPAWN_EGG, 12233882, 6191682);
    }
}
