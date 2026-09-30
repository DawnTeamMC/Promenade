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
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

/**
 * Generates the block states and models. Tints are registered in code on 1.21.1 (see {@code PromenadeBlockColors}).
 */
public class PromenadeModelProvider extends FabricModelProvider {
    private static final PropertyDispatch UP_DEFAULT_ROTATION_OPERATIONS = PropertyDispatch.property(BlockStateProperties.FACING)
            .select(Direction.DOWN, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
            .select(Direction.UP, Variant.variant())
            .select(Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
            .select(Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
            .select(Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
            .select(Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));

    public PromenadeModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators gen) {
        PromenadeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModel).forEach(family -> gen.family(family.getBaseBlock()).generateFor(family));

        gen.createTrivialBlock(PromenadeBlocks.OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.OAK_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.SPRUCE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.SPRUCE_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.BIRCH_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.BIRCH_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.JUNGLE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.JUNGLE_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.ACACIA_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.ACACIA_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.CHERRY_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.CHERRY_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.DARK_OAK_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.DARK_OAK_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.MANGROVE_LEAF_PILE, PromenadeTexturedModels.pile(Blocks.MANGROVE_LEAVES));
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

        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_OAK_LEAVES, Blocks.OAK_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_SPRUCE_LEAVES, Blocks.SPRUCE_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_BIRCH_LEAVES, Blocks.BIRCH_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_JUNGLE_LEAVES, Blocks.JUNGLE_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_ACACIA_LEAVES, Blocks.ACACIA_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_CHERRY_LEAVES, Blocks.CHERRY_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, Blocks.DARK_OAK_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_MANGROVE_LEAVES, Blocks.MANGROVE_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_AZALEA_LEAVES, Blocks.AZALEA_LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES);

        gen.woodProvider(PromenadeBlocks.SAKURA_LOG).logUVLocked(PromenadeBlocks.SAKURA_LOG).wood(PromenadeBlocks.SAKURA_WOOD);
        gen.woodProvider(PromenadeBlocks.STRIPPED_SAKURA_LOG).logUVLocked(PromenadeBlocks.STRIPPED_SAKURA_LOG).wood(PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_SAKURA_LOG, PromenadeBlocks.SAKURA_HANGING_SIGN, PromenadeBlocks.SAKURA_WALL_HANGING_SIGN);
        gen.createPlant(PromenadeBlocks.BLUSH_SAKURA_SAPLING, PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(PromenadeBlocks.COTTON_SAKURA_SAPLING, PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createTrivialBlock(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS, PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, "snowy_sakura_blossoms");
        gen.createTrivialBlock(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS, PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, "snowy_sakura_blossoms");
        gen.createTrivialBlock(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS));
        gen.createTrivialBlock(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS));

        gen.woodProvider(PromenadeBlocks.MAPLE_LOG).log(PromenadeBlocks.MAPLE_LOG).wood(PromenadeBlocks.MAPLE_WOOD);
        this.registerDripLog(gen, PromenadeBlocks.STRIPPED_MAPLE_LOG);
        gen.woodProvider(PromenadeBlocks.STRIPPED_MAPLE_LOG).wood(PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_MAPLE_LOG, PromenadeBlocks.MAPLE_HANGING_SIGN, PromenadeBlocks.MAPLE_WALL_HANGING_SIGN);
        gen.createPlant(PromenadeBlocks.SAP_MAPLE_SAPLING, PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(PromenadeBlocks.VERMILION_MAPLE_SAPLING, PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(PromenadeBlocks.FULVOUS_MAPLE_SAPLING, PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(PromenadeBlocks.MIKADO_MAPLE_SAPLING, PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createTrivialBlock(PromenadeBlocks.SAP_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, PromenadeBlocks.SAP_MAPLE_LEAVES, "snowy_maple_leaves");
        gen.createTrivialBlock(PromenadeBlocks.VERMILION_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES, PromenadeBlocks.VERMILION_MAPLE_LEAVES, "snowy_maple_leaves");
        gen.createTrivialBlock(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES, PromenadeBlocks.FULVOUS_MAPLE_LEAVES, "snowy_maple_leaves");
        gen.createTrivialBlock(PromenadeBlocks.MIKADO_MAPLE_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES, PromenadeBlocks.MIKADO_MAPLE_LEAVES, "snowy_maple_leaves");
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES);
        this.registerFallenLeaves(gen, PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES);
        gen.createTrivialBlock(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.SAP_MAPLE_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.VERMILION_MAPLE_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.FULVOUS_MAPLE_LEAVES));
        gen.createTrivialBlock(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.MIKADO_MAPLE_LEAVES));

        gen.woodProvider(PromenadeBlocks.PALM_LOG).log(PromenadeBlocks.PALM_LOG).wood(PromenadeBlocks.PALM_WOOD);
        gen.woodProvider(PromenadeBlocks.STRIPPED_PALM_LOG).log(PromenadeBlocks.STRIPPED_PALM_LOG).wood(PromenadeBlocks.STRIPPED_PALM_WOOD);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_PALM_LOG, PromenadeBlocks.PALM_HANGING_SIGN, PromenadeBlocks.PALM_WALL_HANGING_SIGN);
        gen.createPlant(PromenadeBlocks.PALM_SAPLING, PromenadeBlocks.POTTED_PALM_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createTrivialBlock(PromenadeBlocks.PALM_LEAVES, TexturedModel.LEAVES);
        this.registerSnowyLeaves(gen, PromenadeBlocks.SNOWY_PALM_LEAVES, PromenadeBlocks.PALM_LEAVES);
        gen.createTrivialBlock(PromenadeBlocks.PALM_LEAF_PILE, PromenadeTexturedModels.pile(PromenadeBlocks.PALM_LEAVES));
        gen.createCrossBlock(PromenadeBlocks.PALM_HANGING_LEAVES, BlockModelGenerators.TintState.TINTED);
        gen.createSimpleFlatItemModel(PromenadeBlocks.PALM_HANGING_LEAVES);

        gen.createNyliumBlock(PromenadeBlocks.DARK_AMARANTH_NYLIUM);
        gen.createTrivialCube(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        gen.createNetherRoots(PromenadeBlocks.DARK_AMARANTH_ROOTS, PromenadeBlocks.POTTED_DARK_AMARANTH_ROOTS);
        gen.woodProvider(PromenadeBlocks.DARK_AMARANTH_STEM).log(PromenadeBlocks.DARK_AMARANTH_STEM).wood(PromenadeBlocks.DARK_AMARANTH_HYPHAE);
        gen.woodProvider(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).log(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM).wood(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
        gen.createHangingSign(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM, PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN, PromenadeBlocks.DARK_AMARANTH_WALL_HANGING_SIGN);
        gen.createPlant(PromenadeBlocks.DARK_AMARANTH_FUNGUS, PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS, BlockModelGenerators.TintState.NOT_TINTED);

        gen.createTrivialCube(PromenadeBlocks.SOUL_SHROOMLIGHT);

        this.registerFacingPlantPart(gen, PromenadeBlocks.COILED_VINES, PromenadeBlocks.COILED_VINES_PLANT, BlockModelGenerators.TintState.NOT_TINTED);
        gen.createSimpleFlatItemModel(PromenadeBlocks.COILED_VINES, "_plant");

        this.registerMoai(gen);

        this.registerBlueberryBush(gen);

        // 1.21.1 spawn eggs are tinted templates
        for (Item spawnEgg : new Item[]{PromenadeItems.CAPYBARA_SPAWN_EGG, PromenadeItems.DUCK_SPAWN_EGG, PromenadeItems.LUSH_CREEPER_SPAWN_EGG, PromenadeItems.SUNKEN_SPAWN_EGG}) {
            gen.delegateItemModel(spawnEgg, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        }
    }

    public final void registerDripLog(BlockModelGenerators gen, Block block) {
        var textureMap = new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block)).put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block));
        var textureMapDrip = new TextureMapping().put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_drip")).put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block));

        var verticalModel = ModelTemplates.CUBE_COLUMN.create(block, textureMap, gen.modelOutput);
        var horizontalModel = ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block, textureMap, gen.modelOutput);

        var verticalModelDrip = ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_drip", textureMapDrip, gen.modelOutput);
        var horizontalModelDrip = ModelTemplates.CUBE_COLUMN_HORIZONTAL.createWithSuffix(block, "_drip", textureMapDrip, gen.modelOutput);

        gen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(
                PropertyDispatch.properties(BlockStateProperties.AXIS, PromenadeBlockProperties.DRIP)
                        .select(Direction.Axis.Y, false, model(verticalModel))
                        .select(Direction.Axis.Z, false, model(horizontalModel).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.X, false, model(horizontalModel).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.Y, true, model(verticalModelDrip))
                        .select(Direction.Axis.Z, true, model(horizontalModelDrip).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                        .select(Direction.Axis.X, true, model(horizontalModelDrip).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
        ));
    }

    public final void registerBlueberryBush(BlockModelGenerators gen) {
        gen.createSimpleFlatItemModel(PromenadeItems.BLUEBERRIES);
        gen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(PromenadeBlocks.BLUEBERRY_BUSH)
                .with(
                        PropertyDispatch.property(BlockStateProperties.AGE_3)
                                .generate(stage -> model(gen.createSuffixedVariant(PromenadeBlocks.BLUEBERRY_BUSH, "_stage" + stage, ModelTemplates.CROSS, TextureMapping::cross)))
                )
        );
    }

    private void registerMoai(BlockModelGenerators gen) {
        // the models are made by hand
        gen.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(PromenadeBlocks.MOAI)
                                .with(PropertyDispatch.property(MoaiBlock.TYPE)
                                        .select(MoaiType.SINGLE, model(ModelLocationUtils.getModelLocation(PromenadeBlocks.MOAI)))
                                        .select(MoaiType.TOP, model(ModelLocationUtils.getModelLocation(PromenadeBlocks.MOAI, "_top")))
                                        .select(MoaiType.BOTTOM, model(ModelLocationUtils.getModelLocation(PromenadeBlocks.MOAI, "_bottom")))
                                )
                                .with(BlockModelGenerators.createHorizontalFacingDispatch())
                );
    }

    private void registerSnowyLeaves(BlockModelGenerators gen, Block snowyLeaves, Block normalLeaves) {
        var bottomModel = gen.createSuffixedVariant(snowyLeaves, "_bottom", PromenadeModels.BOTTOM_SNOWY_LEAVES, block -> PromenadeTextureMaps.snowyLeaves(snowyLeaves, normalLeaves));
        var model = gen.createSuffixedVariant(snowyLeaves, "", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        this.registerSnowyLeaves(gen, snowyLeaves, bottomModel, model);
    }

    private void registerSnowyLeaves(BlockModelGenerators gen, Block snowyLeaves, Block normalLeaves, String textureName) {
        var textureId = ResourceLocation.fromNamespaceAndPath(BuiltInRegistries.BLOCK.getKey(snowyLeaves).getNamespace(), "block/" + textureName);
        var bottomModel = gen.createSuffixedVariant(snowyLeaves, "_bottom", PromenadeModels.BOTTOM_SNOWY_LEAVES, block -> PromenadeTextureMaps.snowyLeaves(textureId, normalLeaves));
        var model = gen.createSuffixedVariant(snowyLeaves, "", ModelTemplates.CUBE_ALL, identifier -> TextureMapping.cube(textureId));
        this.registerSnowyLeaves(gen, snowyLeaves, bottomModel, model);
    }

    private void registerSnowyLeaves(BlockModelGenerators gen, Block snowyLeaves, ResourceLocation bottomModel, ResourceLocation model) {
        gen.delegateItemModel(snowyLeaves, bottomModel);
        gen.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(snowyLeaves)
                        .with(PropertyDispatch.property(SnowyLeavesBlock.BOTTOM)
                                .select(true, model(bottomModel))
                                .select(false, model(model))
                        )
        );
    }

    /**
     * Fallen leaves are flat piles on 1.21.1, which has no leaf litter.
     */
    public final void registerFallenLeaves(BlockModelGenerators gen, Block fallenLeaves) {
        gen.createTrivialBlock(fallenLeaves, PromenadeTexturedModels.FALLEN_LEAVES);
        gen.createSimpleFlatItemModel(fallenLeaves);
    }

    public final void registerFacingPlantPart(BlockModelGenerators gen, Block plant, Block plantStem, BlockModelGenerators.TintState tintType) {
        this.registerFacingTintableCrossBlockState(gen, plant, tintType);
        this.registerFacingTintableCrossBlockState(gen, plantStem, tintType);
    }

    public final void registerFacingTintableCrossBlockState(BlockModelGenerators gen, Block block, BlockModelGenerators.TintState tintType) {
        ResourceLocation model = tintType.getCross().create(block, TextureMapping.cross(block), gen.modelOutput);
        gen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, model(model)).with(UP_DEFAULT_ROTATION_OPERATIONS));
    }

    private static Variant model(ResourceLocation model) {
        return Variant.variant().with(VariantProperties.MODEL, model);
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
    }
}
