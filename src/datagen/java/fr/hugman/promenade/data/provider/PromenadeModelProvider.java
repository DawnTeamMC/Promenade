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
import net.minecraft.client.render.model.json.ModelVariantOperator;
import net.minecraft.client.render.model.json.MultipartModelConditionBuilder;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.AxisRotation;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.FoliageColors;

import java.util.function.Function;

public class PromenadeModelProvider extends FabricModelProvider {
    public static final ModelVariantOperator NO_OP = variant -> variant;
    public static final ModelVariantOperator UV_LOCK = ModelVariantOperator.UV_LOCK.withValue(true);
    public static final ModelVariantOperator ROTATE_X_90 = ModelVariantOperator.ROTATION_X.withValue(AxisRotation.R90);
    public static final ModelVariantOperator ROTATE_X_180 = ModelVariantOperator.ROTATION_X.withValue(AxisRotation.R180);
    public static final ModelVariantOperator ROTATE_X_270 = ModelVariantOperator.ROTATION_X.withValue(AxisRotation.R270);
    public static final ModelVariantOperator ROTATE_Y_90 = ModelVariantOperator.ROTATION_Y.withValue(AxisRotation.R90);
    public static final ModelVariantOperator ROTATE_Y_180 = ModelVariantOperator.ROTATION_Y.withValue(AxisRotation.R180);
    public static final ModelVariantOperator ROTATE_Y_270 = ModelVariantOperator.ROTATION_Y.withValue(AxisRotation.R270);

    private static final Function<MultipartModelConditionBuilder, MultipartModelConditionBuilder> LEAF_LITTER_MODEL_1_CONDITION_FUNCTION = builder -> builder.put(Properties.SEGMENT_AMOUNT, 1);
    private static final Function<MultipartModelConditionBuilder, MultipartModelConditionBuilder> LEAF_LITTER_MODEL_2_CONDITION_FUNCTION = builder -> builder.put(Properties.SEGMENT_AMOUNT, 2, 3);
    private static final Function<MultipartModelConditionBuilder, MultipartModelConditionBuilder> LEAF_LITTER_MODEL_3_CONDITION_FUNCTION = builder -> builder.put(Properties.SEGMENT_AMOUNT, 3);
    private static final Function<MultipartModelConditionBuilder, MultipartModelConditionBuilder> LEAF_LITTER_MODEL_4_CONDITION_FUNCTION = builder -> builder.put(Properties.SEGMENT_AMOUNT, 4);

    private static final BlockStateVariantMap<ModelVariantOperator> NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS = BlockStateVariantMap.operations(
                    Properties.HORIZONTAL_FACING
            )
            .register(Direction.EAST, ROTATE_Y_90)
            .register(Direction.SOUTH, ROTATE_Y_180)
            .register(Direction.WEST, ROTATE_Y_270)
            .register(Direction.NORTH, NO_OP);
    private static final BlockStateVariantMap<ModelVariantOperator> UP_DEFAULT_ROTATION_OPERATIONS = BlockStateVariantMap.operations(Properties.FACING)
            .register(Direction.DOWN, ROTATE_X_180)
            .register(Direction.UP, NO_OP)
            .register(Direction.NORTH, ROTATE_X_90)
            .register(Direction.SOUTH, ROTATE_X_90.then(ROTATE_Y_180))
            .register(Direction.WEST, ROTATE_X_90.then(ROTATE_Y_270))
            .register(Direction.EAST, ROTATE_X_90.then(ROTATE_Y_90));

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

        gen.createLogTexturePool(PromenadeBlocks.SAKURA_LOG).uvLockedLog(PromenadeBlocks.SAKURA_LOG).wood(PromenadeBlocks.SAKURA_WOOD);
        gen.createLogTexturePool(PromenadeBlocks.STRIPPED_SAKURA_LOG).uvLockedLog(PromenadeBlocks.STRIPPED_SAKURA_LOG).wood(PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_SAKURA_LOG, PromenadeBlocks.SAKURA_HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.BLUSH_SAKURA_SAPLING, PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.COTTON_SAKURA_SAPLING, PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerSingleton(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS, PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, "snowy_sakura_blossoms");
        gen.registerSingleton(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS, PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, "snowy_sakura_blossoms");
        gen.registerSingleton(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS));
        gen.registerSingleton(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS));

        gen.createLogTexturePool(PromenadeBlocks.MAPLE_LOG).log(PromenadeBlocks.MAPLE_LOG).wood(PromenadeBlocks.MAPLE_WOOD);
        registerDripLog(gen, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        gen.createLogTexturePool(PromenadeBlocks.STRIPPED_MAPLE_LOG).wood(PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_MAPLE_LOG, PromenadeBlocks.MAPLE_HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.SAP_MAPLE_SAPLING, PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.VERMILION_MAPLE_SAPLING, PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.FULVOUS_MAPLE_SAPLING, PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.MIKADO_MAPLE_SAPLING, PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SAP_MAPLE_LEAVES, TexturedModel.LEAVES, PromenadeFoliageColors.SAP_MAPLE);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAVES, "snowy_maple_leaves", PromenadeFoliageColors.SAP_MAPLE);
        gen.registerSingleton(PromenadeBlocks.VERMILION_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES, PromenadeBlocks.VERMILION_MAPLE_LEAVES, "snowy_maple_leaves");
        gen.registerSingleton(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES, PromenadeBlocks.FULVOUS_MAPLE_LEAVES, "snowy_maple_leaves");
        gen.registerSingleton(PromenadeBlocks.MIKADO_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES, PromenadeBlocks.MIKADO_MAPLE_LEAVES, "snowy_maple_leaves");
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES, PromenadeFoliageColors.SAP_MAPLE);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES);
        gen.registerTintedBlockAndItem(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.SAP_MAPLE_LEAVES), PromenadeFoliageColors.SAP_MAPLE);
        gen.registerSingleton(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.VERMILION_MAPLE_LEAVES));
        gen.registerSingleton(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.FULVOUS_MAPLE_LEAVES));
        gen.registerSingleton(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.MIKADO_MAPLE_LEAVES));

        gen.createLogTexturePool(PromenadeBlocks.PALM_LOG).log(PromenadeBlocks.PALM_LOG).wood(PromenadeBlocks.PALM_WOOD);
        gen.createLogTexturePool(PromenadeBlocks.STRIPPED_PALM_LOG).log(PromenadeBlocks.STRIPPED_PALM_LOG).wood(PromenadeBlocks.STRIPPED_PALM_WOOD);
        gen.registerHangingSign(PromenadeBlocks.STRIPPED_PALM_LOG, PromenadeBlocks.PALM_HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        gen.registerFlowerPotPlantAndItem(PromenadeBlocks.PALM_SAPLING, PromenadeBlocks.POTTED_PALM_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        gen.registerTintedBlockAndItem(PromenadeBlocks.PALM_LEAVES, TexturedModel.LEAVES, PromenadeFoliageColors.PALM);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_LEAVES, PromenadeFoliageColors.PALM);
        gen.registerTintedBlockAndItem(PromenadeBlocks.PALM_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.PALM_LEAVES), PromenadeFoliageColors.PALM);
        gen.registerTintableCrossBlockState(PromenadeBlocks.PALM_HANGING_LEAVES, BlockStateModelGenerator.CrossType.TINTED);
        this.registerTintedItem(gen, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeFoliageColors.PALM);

        gen.registerNetherrackBottomCustomTop(PromenadeBlocks.DARK_AMARANTH_NYLIUM);
        gen.registerSimpleCubeAll(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        gen.registerRoots(PromenadeBlocks.DARK_AMARANTH_ROOTS, PromenadeBlocks.POTTED_DARK_AMARANTH_ROOTS);
        gen.createLogTexturePool(PromenadeBlocks.DARK_AMARANTH_STEM).stem(PromenadeBlocks.DARK_AMARANTH_STEM).wood(PromenadeBlocks.DARK_AMARANTH_HYPHAE);
        gen.createLogTexturePool(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).stem(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).wood(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
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

        var verticalModel = BlockStateModelGenerator.createWeightedVariant(Models.CUBE_COLUMN.upload(block, textureMap, gen.modelCollector));
        var horizontalModel = BlockStateModelGenerator.createWeightedVariant(Models.CUBE_COLUMN_HORIZONTAL.upload(block, textureMap, gen.modelCollector));

        var verticalModelDrip = BlockStateModelGenerator.createWeightedVariant(Models.CUBE_COLUMN.upload(block, "_drip", textureMapDrip, gen.modelCollector));
        var horizontalModelDrip = BlockStateModelGenerator.createWeightedVariant(Models.CUBE_COLUMN_HORIZONTAL.upload(block, "_drip", textureMapDrip, gen.modelCollector));

        gen.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block).with(
                BlockStateVariantMap.models(Properties.AXIS, PromenadeBlockProperties.DRIP)
                        .register(Direction.Axis.Y, false, verticalModel)
                        .register(Direction.Axis.Z, false, horizontalModel.apply(ROTATE_X_90))
                        .register(Direction.Axis.X, false, horizontalModel.apply(ROTATE_X_90).apply(ROTATE_Y_90))
                        .register(Direction.Axis.Y, true, verticalModelDrip)
                        .register(Direction.Axis.Z, true, horizontalModelDrip.apply(ROTATE_X_90))
                        .register(Direction.Axis.X, true, horizontalModelDrip.apply(ROTATE_X_90).apply(ROTATE_Y_90))
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
        gen.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(PromenadeBlocks.BLUEBERRY_BUSH)
                .with(
                        BlockStateVariantMap.models(Properties.AGE_3)
                                .generate(stage -> BlockStateModelGenerator.createWeightedVariant(gen.createSubModel(PromenadeBlocks.BLUEBERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross)))
                )
        );
    }

    private void registerMoai(BlockStateModelGenerator gen) {
        // maybe check if we can't generate the models too?
        gen.blockStateCollector
                .accept(
                        VariantsBlockModelDefinitionCreator.of(PromenadeBlocks.MOAI)
                                .with(BlockStateVariantMap.models(MoaiBlock.TYPE)
                                        .register(MoaiType.SINGLE, BlockStateModelGenerator.createWeightedVariant(ModelIds.getBlockModelId(PromenadeBlocks.MOAI)))
                                        .register(MoaiType.TOP, BlockStateModelGenerator.createWeightedVariant(ModelIds.getBlockSubModelId(PromenadeBlocks.MOAI, "_top")))
                                        .register(MoaiType.BOTTOM, BlockStateModelGenerator.createWeightedVariant(ModelIds.getBlockSubModelId(PromenadeBlocks.MOAI, "_bottom")))
                                )
                                .coordinate(NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS)
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
                VariantsBlockModelDefinitionCreator.of(snowyLeaves)
                        .with(BlockStateVariantMap.models(SnowyLeavesBlock.BOTTOM)
                                .register(true, BlockStateModelGenerator.createWeightedVariant(bottomModel))
                                .register(false, BlockStateModelGenerator.createWeightedVariant(gen.createSubModel(snowyLeaves, "", Models.CUBE_ALL, TextureMap::all)))
                        )
        );
    }

    private void registerSnowyLeaves(BlockStateModelGenerator gen, Block snowyLeaves, Block normalLeaves, String textureName) {
        registerSnowyLeaves(gen, snowyLeaves, normalLeaves, textureName, 0);
    }

    private void registerSnowyLeaves(BlockStateModelGenerator gen, Block snowyLeaves, Block normalLeaves, String textureName, int tint) {
        var textureId = Identifier.of(Registries.BLOCK.getId(snowyLeaves).getNamespace(), "block/" + textureName);
        var bottomModel = gen.createSubModel(snowyLeaves, "_bottom", PromenadeModels.BOTTOM_SNOWY_LEAVES, block -> PromenadeTextureMaps.snowyLeaves(textureId, normalLeaves));
        if (tint != 0) {
            gen.registerTintedItemModel(snowyLeaves, bottomModel, ItemModels.constantTintSource(tint));
        } else {
            gen.registerItemModel(snowyLeaves.asItem(), bottomModel);
        }
        gen.blockStateCollector.accept(
                VariantsBlockModelDefinitionCreator.of(snowyLeaves)
                        .with(BlockStateVariantMap.models(SnowyLeavesBlock.BOTTOM)
                                .register(true, BlockStateModelGenerator.createWeightedVariant(bottomModel))
                                .register(false, BlockStateModelGenerator.createWeightedVariant(gen.createSubModel(snowyLeaves, "", Models.CUBE_ALL, identifier -> TextureMap.all(textureId))))
                        )
        );

    }

    private void registerFallenLeaves(BlockStateModelGenerator gen, Block fallenLeaves) {
        registerFallenLeaves(gen, fallenLeaves, 0);
    }

    public final void registerFallenLeaves(BlockStateModelGenerator gen, Block fallenLeaves, int tintSource) {
        if (tintSource != 0) {
            Identifier identifier = gen.uploadBlockItemModel(fallenLeaves.asItem(), fallenLeaves);
            gen.registerTintedItemModel(fallenLeaves, identifier, ItemModels.constantTintSource(tintSource));
        } else {
            gen.registerItemModel(fallenLeaves);
        }
        var weightedVariant = BlockStateModelGenerator.createWeightedVariant(TexturedModel.TEMPLATE_LEAF_LITTER_1.upload(fallenLeaves, gen.modelCollector));
        var weightedVariant2 = BlockStateModelGenerator.createWeightedVariant(TexturedModel.TEMPLATE_LEAF_LITTER_2.upload(fallenLeaves, gen.modelCollector));
        var weightedVariant3 = BlockStateModelGenerator.createWeightedVariant(TexturedModel.TEMPLATE_LEAF_LITTER_3.upload(fallenLeaves, gen.modelCollector));
        var weightedVariant4 = BlockStateModelGenerator.createWeightedVariant(TexturedModel.TEMPLATE_LEAF_LITTER_4.upload(fallenLeaves, gen.modelCollector));
        gen.registerSegmentedBlock(fallenLeaves,
                weightedVariant, LEAF_LITTER_MODEL_1_CONDITION_FUNCTION,
                weightedVariant2, LEAF_LITTER_MODEL_2_CONDITION_FUNCTION,
                weightedVariant3, LEAF_LITTER_MODEL_3_CONDITION_FUNCTION,
                weightedVariant4, LEAF_LITTER_MODEL_4_CONDITION_FUNCTION
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
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(tintType.getCrossModel().upload(block, crossTexture, gen.modelCollector));
        gen.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(block, weightedVariant)
                        .coordinate(UP_DEFAULT_ROTATION_OPERATIONS)
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
        gen.register(PromenadeItems.CAPYBARA_SPAWN_EGG, Models.GENERATED);
        gen.register(PromenadeItems.DUCK_SPAWN_EGG, Models.GENERATED);
        gen.register(PromenadeItems.LUSH_CREEPER_SPAWN_EGG, Models.GENERATED);
        gen.register(PromenadeItems.SUNKEN_SPAWN_EGG, Models.GENERATED);
    }
}
