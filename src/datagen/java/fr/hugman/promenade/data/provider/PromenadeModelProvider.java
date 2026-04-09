package fr.hugman.promenade.data.provider;

import com.mojang.math.Quadrant;
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
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Function;

public class PromenadeModelProvider extends FabricModelProvider {
    public static final VariantMutator NO_OP = variant -> variant;
    public static final VariantMutator UV_LOCK = VariantMutator.UV_LOCK.withValue(true);
    public static final VariantMutator ROTATE_X_90 = VariantMutator.X_ROT.withValue(Quadrant.R90);
    public static final VariantMutator ROTATE_X_180 = VariantMutator.X_ROT.withValue(Quadrant.R180);
    public static final VariantMutator ROTATE_X_270 = VariantMutator.X_ROT.withValue(Quadrant.R270);
    public static final VariantMutator ROTATE_Y_90 = VariantMutator.Y_ROT.withValue(Quadrant.R90);
    public static final VariantMutator ROTATE_Y_180 = VariantMutator.Y_ROT.withValue(Quadrant.R180);
    public static final VariantMutator ROTATE_Y_270 = VariantMutator.Y_ROT.withValue(Quadrant.R270);

    private static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_1_CONDITION_FUNCTION = builder -> builder.term(BlockStateProperties.SEGMENT_AMOUNT, 1);
    private static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_2_CONDITION_FUNCTION = builder -> builder.term(BlockStateProperties.SEGMENT_AMOUNT, 2, 3);
    private static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_3_CONDITION_FUNCTION = builder -> builder.term(BlockStateProperties.SEGMENT_AMOUNT, 3);
    private static final Function<ConditionBuilder, ConditionBuilder> LEAF_LITTER_MODEL_4_CONDITION_FUNCTION = builder -> builder.term(BlockStateProperties.SEGMENT_AMOUNT, 4);

    private static final PropertyDispatch<VariantMutator> NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS = PropertyDispatch.modify(
                    BlockStateProperties.HORIZONTAL_FACING
            )
            .select(Direction.EAST, ROTATE_Y_90)
            .select(Direction.SOUTH, ROTATE_Y_180)
            .select(Direction.WEST, ROTATE_Y_270)
            .select(Direction.NORTH, NO_OP);
    private static final PropertyDispatch<VariantMutator> UP_DEFAULT_ROTATION_OPERATIONS = PropertyDispatch.modify(BlockStateProperties.FACING)
            .select(Direction.DOWN, ROTATE_X_180)
            .select(Direction.UP, NO_OP)
            .select(Direction.NORTH, ROTATE_X_90)
            .select(Direction.SOUTH, ROTATE_X_90.then(ROTATE_Y_180))
            .select(Direction.WEST, ROTATE_X_90.then(ROTATE_Y_270))
            .select(Direction.EAST, ROTATE_X_90.then(ROTATE_Y_90));

    public PromenadeModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators gen) {
        PromenadeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModel).forEach(family -> gen.family(family.getBaseBlock()).generateFor(family));

        gen.createTintedLeaves(PromenadeBlocks.OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.OAK_LEAVES), FoliageColor.FOLIAGE_DEFAULT);
        gen.createTintedLeaves(PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.SPRUCE_LEAVES), FoliageColor.FOLIAGE_EVERGREEN);
        gen.createTintedLeaves(PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.BIRCH_LEAVES), FoliageColor.FOLIAGE_BIRCH);
        gen.createTintedLeaves(PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.JUNGLE_LEAVES), FoliageColor.FOLIAGE_DEFAULT);
        gen.createTintedLeaves(PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.ACACIA_LEAVES), FoliageColor.FOLIAGE_DEFAULT);
        gen.createTrivialBlock(PromenadeBlocks.CHERRY_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.CHERRY_LEAVES));
        gen.createTintedLeaves(PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.DARK_OAK_LEAVES), FoliageColor.FOLIAGE_DEFAULT);
        gen.createTrivialBlock(PromenadeBlocks.PALE_OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.PALE_OAK_LEAVES));
        gen.createTintedLeaves(PromenadeBlocks.MANGROVE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.MANGROVE_LEAVES), FoliageColor.FOLIAGE_MANGROVE);
        gen.createTrivialBlock(PromenadeBlocks.AZALEA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.AZALEA_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.FLOWERING_AZALEA_LEAVES));

        gen.createTrivialBlock(PromenadeBlocks.DANDELION_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.POPPY_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.BLUE_ORCHID_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.ALLIUM_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.AZURE_BLUET_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.RED_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.ORANGE_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.WHITE_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.PINK_TULIP_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.OXEYE_DAISY_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.CORNFLOWER_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.LILY_OF_THE_VALLEY_PILE, PromenadeTexturedModels.PILE);
        gen.createTrivialBlock(PromenadeBlocks.WITHER_ROSE_PILE, PromenadeTexturedModels.PILE);

        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_OAK_LEAVES, Blocks.OAK_LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_SPRUCE_LEAVES, Blocks.SPRUCE_LEAVES, FoliageColor.FOLIAGE_EVERGREEN);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_BIRCH_LEAVES, Blocks.BIRCH_LEAVES, FoliageColor.FOLIAGE_BIRCH);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_JUNGLE_LEAVES, Blocks.JUNGLE_LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_ACACIA_LEAVES, Blocks.ACACIA_LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_CHERRY_LEAVES, Blocks.CHERRY_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, Blocks.DARK_OAK_LEAVES, FoliageColor.FOLIAGE_DEFAULT);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_PALE_OAK_LEAVES, Blocks.PALE_OAK_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_MANGROVE_LEAVES, Blocks.MANGROVE_LEAVES, FoliageColor.FOLIAGE_MANGROVE);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_AZALEA_LEAVES, Blocks.AZALEA_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES);

        gen.woodProvider(PromenadeBlocks.SAKURA_LOG).logUVLocked(PromenadeBlocks.SAKURA_LOG).wood(PromenadeBlocks.SAKURA_WOOD);
        gen.woodProvider(PromenadeBlocks.STRIPPED_SAKURA_LOG).logUVLocked(PromenadeBlocks.STRIPPED_SAKURA_LOG).wood(PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_SAKURA_LOG, PromenadeBlocks.SAKURA_HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        gen.createShelf(PromenadeBlocks.SAKURA_SHELF, PromenadeBlocks.STRIPPED_SAKURA_LOG);
        gen.createPlantWithDefaultItem(PromenadeBlocks.BLUSH_SAKURA_SAPLING, PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(PromenadeBlocks.COTTON_SAKURA_SAPLING, PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createTrivialBlock(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS, PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, "snowy_sakura_blossoms");
        gen.createTrivialBlock(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS, PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, "snowy_sakura_blossoms");
        gen.createTrivialBlock(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS));
        gen.createTrivialBlock(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS));

        gen.woodProvider(PromenadeBlocks.MAPLE_LOG).log(PromenadeBlocks.MAPLE_LOG).wood(PromenadeBlocks.MAPLE_WOOD);
        registerDripLog(gen, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        gen.woodProvider(PromenadeBlocks.STRIPPED_MAPLE_LOG).wood(PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_MAPLE_LOG, PromenadeBlocks.MAPLE_HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        gen.createShelf(PromenadeBlocks.MAPLE_SHELF, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        gen.createPlantWithDefaultItem(PromenadeBlocks.SAP_MAPLE_SAPLING, PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(PromenadeBlocks.VERMILION_MAPLE_SAPLING, PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(PromenadeBlocks.FULVOUS_MAPLE_SAPLING, PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createPlantWithDefaultItem(PromenadeBlocks.MIKADO_MAPLE_SAPLING, PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createTintedLeaves(PromenadeBlocks.SAP_MAPLE_LEAVES, TexturedModel.LEAVES, PromenadeFoliageColors.SAP_MAPLE);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAVES, "snowy_maple_leaves", PromenadeFoliageColors.SAP_MAPLE);
        gen.createTrivialBlock(PromenadeBlocks.VERMILION_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES, PromenadeBlocks.VERMILION_MAPLE_LEAVES, "snowy_maple_leaves");
        gen.createTrivialBlock(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES, PromenadeBlocks.FULVOUS_MAPLE_LEAVES, "snowy_maple_leaves");
        gen.createTrivialBlock(PromenadeBlocks.MIKADO_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES, PromenadeBlocks.MIKADO_MAPLE_LEAVES, "snowy_maple_leaves");
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES, PromenadeFoliageColors.SAP_MAPLE);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES);
        gen.createTintedLeaves(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.SAP_MAPLE_LEAVES), PromenadeFoliageColors.SAP_MAPLE);
        gen.createTrivialBlock(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.VERMILION_MAPLE_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.FULVOUS_MAPLE_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.MIKADO_MAPLE_LEAVES));

        gen.woodProvider(PromenadeBlocks.PALM_LOG).log(PromenadeBlocks.PALM_LOG).wood(PromenadeBlocks.PALM_WOOD);
        gen.woodProvider(PromenadeBlocks.STRIPPED_PALM_LOG).log(PromenadeBlocks.STRIPPED_PALM_LOG).wood(PromenadeBlocks.STRIPPED_PALM_WOOD);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_PALM_LOG, PromenadeBlocks.PALM_HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        gen.createShelf(PromenadeBlocks.PALM_SHELF, PromenadeBlocks.STRIPPED_PALM_LOG);
        gen.createPlantWithDefaultItem(PromenadeBlocks.PALM_SAPLING, PromenadeBlocks.POTTED_PALM_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.createTintedLeaves(PromenadeBlocks.PALM_LEAVES, TexturedModel.LEAVES, PromenadeFoliageColors.PALM);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_LEAVES, PromenadeFoliageColors.PALM);
        gen.createTintedLeaves(PromenadeBlocks.PALM_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.PALM_LEAVES), PromenadeFoliageColors.PALM);
        gen.createCrossBlock(PromenadeBlocks.PALM_HANGING_LEAVES, BlockModelGenerators.PlantType.TINTED);
        this.registerTintedItem(gen, PromenadeBlocks.PALM_HANGING_LEAVES, PromenadeFoliageColors.PALM);

        gen.createNyliumBlock(PromenadeBlocks.DARK_AMARANTH_NYLIUM);
        gen.createTrivialCube(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        gen.createNetherRoots(PromenadeBlocks.DARK_AMARANTH_ROOTS, PromenadeBlocks.POTTED_DARK_AMARANTH_ROOTS);
        gen.woodProvider(PromenadeBlocks.DARK_AMARANTH_STEM).log(PromenadeBlocks.DARK_AMARANTH_STEM).wood(PromenadeBlocks.DARK_AMARANTH_HYPHAE);
        gen.woodProvider(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).log(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).wood(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM, PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);
        gen.createShelf(PromenadeBlocks.DARK_AMARANTH_SHELF, PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        gen.createPlantWithDefaultItem(PromenadeBlocks.DARK_AMARANTH_FUNGUS, PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS, BlockModelGenerators.PlantType.NOT_TINTED);

        gen.createTrivialCube(PromenadeBlocks.SOUL_SHROOMLIGHT);

        this.registerFacingPlantPart(gen, PromenadeBlocks.COILED_VINES, PromenadeBlocks.COILED_VINES_PLANT, BlockModelGenerators.PlantType.NOT_TINTED);
        gen.registerSimpleFlatItemModel(PromenadeBlocks.COILED_VINES, "_plant");

        this.registerMoai(gen);

        this.registerBlueberryBush(gen);
    }

    public final void registerDripLog(BlockModelGenerators gen, Block block) {
        var textureMap = new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block)).put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block));
        var textureMapDrip = new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_drip")).put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block));

        var verticalModel = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_COLUMN.create(block, textureMap, gen.modelOutput));
        var horizontalModel = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block, textureMap, gen.modelOutput));

        var verticalModelDrip = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_drip", textureMapDrip, gen.modelOutput));
        var horizontalModelDrip = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_COLUMN_HORIZONTAL.createWithSuffix(block, "_drip", textureMapDrip, gen.modelOutput));

        gen.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(
                PropertyDispatch.initial(BlockStateProperties.AXIS, PromenadeBlockProperties.DRIP)
                        .select(Direction.Axis.Y, false, verticalModel)
                        .select(Direction.Axis.Z, false, horizontalModel.with(ROTATE_X_90))
                        .select(Direction.Axis.X, false, horizontalModel.with(ROTATE_X_90).with(ROTATE_Y_90))
                        .select(Direction.Axis.Y, true, verticalModelDrip)
                        .select(Direction.Axis.Z, true, horizontalModelDrip.with(ROTATE_X_90))
                        .select(Direction.Axis.X, true, horizontalModelDrip.with(ROTATE_X_90).with(ROTATE_Y_90))
        ));
    }

    public final void registerTintedItem(BlockModelGenerators gen, Block block, int constant) {
        registerTintedItem(gen, block, ItemModelUtils.constantTint(constant));
    }

    public final void registerTintedItem(BlockModelGenerators gen, Block block, ItemTintSource tint) {
        Identifier identifier = gen.createFlatItemModelWithBlockTexture(block.asItem(), block);
        gen.registerSimpleTintedItemModel(block, identifier, tint);
    }

    public final void registerBlueberryBush(BlockModelGenerators gen) {
        gen.registerSimpleFlatItemModel(PromenadeItems.BLUEBERRIES);
        gen.blockStateOutput.accept(MultiVariantGenerator.dispatch(PromenadeBlocks.BLUEBERRY_BUSH)
                .with(
                        PropertyDispatch.initial(BlockStateProperties.AGE_3)
                                .generate(stage -> BlockModelGenerators.plainVariant(gen.createSuffixedVariant(PromenadeBlocks.BLUEBERRY_BUSH, "_stage" + stage, ModelTemplates.CROSS, TextureMapping::cross)))
                )
        );
    }

    private void registerMoai(BlockModelGenerators gen) {
        // maybe check if we can't generate the models too?
        gen.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(PromenadeBlocks.MOAI)
                                .with(PropertyDispatch.initial(MoaiBlock.TYPE)
                                        .select(MoaiType.SINGLE, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(PromenadeBlocks.MOAI)))
                                        .select(MoaiType.TOP, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(PromenadeBlocks.MOAI, "_top")))
                                        .select(MoaiType.BOTTOM, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(PromenadeBlocks.MOAI, "_bottom")))
                                )
                                .with(NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS)
                );
    }

    private void registerSnowyLeaves(BlockModelGenerators gen, Block snowyLeaves, Block normalLeaves) {
        registerSnowyLeaves(gen, snowyLeaves, normalLeaves, 0);
    }

    private void registerSnowyLeaves(BlockModelGenerators gen, Block snowyLeaves, Block normalLeaves, int tint) {
        var bottomModel = gen.createSuffixedVariant(snowyLeaves, "_bottom", PromenadeModels.BOTTOM_SNOWY_LEAVES, block -> PromenadeTextureMaps.snowyLeaves(snowyLeaves, normalLeaves));
        if (tint != 0) {
            gen.registerSimpleTintedItemModel(snowyLeaves, bottomModel, ItemModelUtils.constantTint(tint));
        } else {
            gen.registerSimpleItemModel(snowyLeaves.asItem(), bottomModel);
        }
        gen.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(snowyLeaves)
                        .with(PropertyDispatch.initial(SnowyLeavesBlock.BOTTOM)
                                .select(true, BlockModelGenerators.plainVariant(bottomModel))
                                .select(false, BlockModelGenerators.plainVariant(gen.createSuffixedVariant(snowyLeaves, "", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                        )
        );
    }

    private void registerSnowyLeaves(BlockModelGenerators gen, Block snowyLeaves, Block normalLeaves, String textureName) {
        registerSnowyLeaves(gen, snowyLeaves, normalLeaves, textureName, 0);
    }

    private void registerSnowyLeaves(BlockModelGenerators gen, Block snowyLeaves, Block normalLeaves, String textureName, int tint) {
        var textureId = Identifier.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(snowyLeaves).getNamespace(), "block/" + textureName);
        var bottomModel = gen.createSuffixedVariant(snowyLeaves, "_bottom", PromenadeModels.BOTTOM_SNOWY_LEAVES, block -> PromenadeTextureMaps.snowyLeaves(textureId, normalLeaves));
        if (tint != 0) {
            gen.registerSimpleTintedItemModel(snowyLeaves, bottomModel, ItemModelUtils.constantTint(tint));
        } else {
            gen.registerSimpleItemModel(snowyLeaves.asItem(), bottomModel);
        }
        gen.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(snowyLeaves)
                        .with(PropertyDispatch.initial(SnowyLeavesBlock.BOTTOM)
                                .select(true, BlockModelGenerators.plainVariant(bottomModel))
                                .select(false, BlockModelGenerators.plainVariant(gen.createSuffixedVariant(snowyLeaves, "", ModelTemplates.CUBE_ALL, identifier -> TextureMapping.cube(new Material(textureId)))))
                        )
        );

    }

    private void registerFallenLeaves(BlockModelGenerators gen, Block fallenLeaves) {
        registerFallenLeaves(gen, fallenLeaves, 0);
    }

    public final void registerFallenLeaves(BlockModelGenerators gen, Block fallenLeaves, int tintSource) {
        if (tintSource != 0) {
            Identifier identifier = gen.createFlatItemModelWithBlockTexture(fallenLeaves.asItem(), fallenLeaves);
            gen.registerSimpleTintedItemModel(fallenLeaves, identifier, ItemModelUtils.constantTint(tintSource));
        } else {
            gen.registerSimpleFlatItemModel(fallenLeaves);
        }
        var weightedVariant = BlockModelGenerators.plainVariant(TexturedModel.LEAF_LITTER_1.create(fallenLeaves, gen.modelOutput));
        var weightedVariant2 = BlockModelGenerators.plainVariant(TexturedModel.LEAF_LITTER_2.create(fallenLeaves, gen.modelOutput));
        var weightedVariant3 = BlockModelGenerators.plainVariant(TexturedModel.LEAF_LITTER_3.create(fallenLeaves, gen.modelOutput));
        var weightedVariant4 = BlockModelGenerators.plainVariant(TexturedModel.LEAF_LITTER_4.create(fallenLeaves, gen.modelOutput));
        gen.createSegmentedBlock(fallenLeaves,
                weightedVariant, LEAF_LITTER_MODEL_1_CONDITION_FUNCTION,
                weightedVariant2, LEAF_LITTER_MODEL_2_CONDITION_FUNCTION,
                weightedVariant3, LEAF_LITTER_MODEL_3_CONDITION_FUNCTION,
                weightedVariant4, LEAF_LITTER_MODEL_4_CONDITION_FUNCTION
        );
    }

    public final void registerFacingPlantPart(BlockModelGenerators gen, Block plant, Block plantStem, BlockModelGenerators.PlantType tintType) {
        this.registerFacingTintableCrossBlockState(gen, plant, tintType);
        this.registerFacingTintableCrossBlockState(gen, plantStem, tintType);
    }

    public final void registerFacingTintableCrossBlockState(BlockModelGenerators gen, Block block, BlockModelGenerators.PlantType tintType) {
        TextureMapping textureMap = tintType.getTextureMapping(block);
        this.registerFacingTintableCrossBlockState(gen, block, tintType, textureMap);
    }

    public final void registerFacingTintableCrossBlockState(BlockModelGenerators gen, Block block, BlockModelGenerators.PlantType tintType, TextureMapping crossTexture) {
        MultiVariant weightedVariant = BlockModelGenerators.plainVariant(tintType.getCross().create(block, crossTexture, gen.modelOutput));
        gen.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(block, weightedVariant)
                        .with(UP_DEFAULT_ROTATION_OPERATIONS)
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {
        gen.generateFlatItem(PromenadeItems.SAKURA_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.SAKURA_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.MAPLE_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.MAPLE_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.MAPLE_SYRUP_BOTTLE, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.PALM_BOAT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.PALM_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(PromenadeItems.BANANA, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.APRICOT, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.MANGO, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.DUCK, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.COOKED_DUCK, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(PromenadeItems.BOVINE_BANNER_PATTERN, ModelTemplates.FLAT_ITEM);

        gen.generateFlatItem(PromenadeItems.CAPYBARA_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.DUCK_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.LUSH_CREEPER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(PromenadeItems.SUNKEN_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
    }
}
