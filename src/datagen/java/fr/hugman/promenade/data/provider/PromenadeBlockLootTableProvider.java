package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.block.BerryBushBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class PromenadeBlockLootTableProvider extends FabricBlockLootSubProvider {
    private static final float[] JUNGLE_SAPLING_DROP_CHANCE = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};
    protected static final float[] SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] LEAVES_STICK_DROP_CHANCE = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    public PromenadeBlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        final var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        dropSelf(PromenadeBlocks.ASPHALT);
        add(PromenadeBlocks.ASPHALT_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.ASPHALT_STAIRS);
        dropSelf(PromenadeBlocks.ASPHALT_WALL);
        dropSelf(PromenadeBlocks.POLISHED_ASPHALT);
        add(PromenadeBlocks.POLISHED_ASPHALT_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.POLISHED_ASPHALT_STAIRS);

        dropSelf(PromenadeBlocks.BLUNITE);
        add(PromenadeBlocks.BLUNITE_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.BLUNITE_STAIRS);
        dropSelf(PromenadeBlocks.BLUNITE_WALL);
        dropSelf(PromenadeBlocks.POLISHED_BLUNITE);
        add(PromenadeBlocks.POLISHED_BLUNITE_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.POLISHED_BLUNITE_STAIRS);

        add(PromenadeBlocks.OAK_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.SPRUCE_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.BIRCH_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.JUNGLE_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.ACACIA_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.CHERRY_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.DARK_OAK_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.PALE_OAK_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.MANGROVE_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.AZALEA_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);

        add(PromenadeBlocks.DANDELION_PILE, flowerPile(Items.DANDELION));
        add(PromenadeBlocks.POPPY_PILE, flowerPile(Items.POPPY));
        add(PromenadeBlocks.BLUE_ORCHID_PILE, flowerPile(Items.BLUE_ORCHID));
        add(PromenadeBlocks.ALLIUM_PILE, flowerPile(Items.ALLIUM));
        add(PromenadeBlocks.AZURE_BLUET_PILE, flowerPile(Items.AZURE_BLUET));
        add(PromenadeBlocks.RED_TULIP_PILE, flowerPile(Items.RED_TULIP));
        add(PromenadeBlocks.ORANGE_TULIP_PILE, flowerPile(Items.ORANGE_TULIP));
        add(PromenadeBlocks.WHITE_TULIP_PILE, flowerPile(Items.WHITE_TULIP));
        add(PromenadeBlocks.PINK_TULIP_PILE, flowerPile(Items.PINK_TULIP));
        add(PromenadeBlocks.OXEYE_DAISY_PILE, flowerPile(Items.OXEYE_DAISY));
        add(PromenadeBlocks.CORNFLOWER_PILE, flowerPile(Items.CORNFLOWER));
        add(PromenadeBlocks.LILY_OF_THE_VALLEY_PILE, flowerPile(Items.LILY_OF_THE_VALLEY));
        add(PromenadeBlocks.WITHER_ROSE_PILE, flowerPile(Items.WITHER_ROSE));

        add(PromenadeBlocks.SNOWY_OAK_LEAVES, block -> this.snowyFruitLeavesDrop(block, Blocks.OAK_SAPLING, Items.APPLE, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_SPRUCE_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.SPRUCE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_BIRCH_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.BIRCH_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_JUNGLE_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.JUNGLE_SAPLING, JUNGLE_SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_ACACIA_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.ACACIA_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_CHERRY_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.CHERRY_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_DARK_OAK_LEAVES, block -> this.snowyFruitLeavesDrop(block, Blocks.DARK_OAK_SAPLING, Items.APPLE, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_PALE_OAK_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.PALE_OAK_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_MANGROVE_LEAVES, this::snowyMangroveLeavesDrops);
        add(PromenadeBlocks.SNOWY_AZALEA_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.AZALEA, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_FLOWERING_AZALEA_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.FLOWERING_AZALEA, SAPLING_DROP_CHANCE));

        dropSelf(PromenadeBlocks.STRIPPED_SAKURA_LOG);
        dropSelf(PromenadeBlocks.SAKURA_LOG);
        dropSelf(PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        dropSelf(PromenadeBlocks.SAKURA_WOOD);
        dropSelf(PromenadeBlocks.SAKURA_PLANKS);
        dropSelf(PromenadeBlocks.SAKURA_STAIRS);
        add(PromenadeBlocks.SAKURA_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.SAKURA_FENCE);
        dropSelf(PromenadeBlocks.SAKURA_FENCE_GATE);
        add(PromenadeBlocks.SAKURA_DOOR, this::createDoorTable);
        dropSelf(PromenadeBlocks.SAKURA_TRAPDOOR);
        dropSelf(PromenadeBlocks.SAKURA_BUTTON);
        dropSelf(PromenadeBlocks.SAKURA_PRESSURE_PLATE);
        dropSelf(PromenadeBlocks.SAKURA_SIGN);
        dropSelf(PromenadeBlocks.SAKURA_HANGING_SIGN);
        dropSelf(PromenadeBlocks.SAKURA_SHELF);

        dropSelf(PromenadeBlocks.BLUSH_SAKURA_SAPLING);
        dropPottedContents(PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING);
        add(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, block -> this.createLeavesDrops(block, PromenadeBlocks.BLUSH_SAKURA_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_BLUSH_SAKURA_BLOSSOMS, block -> this.snowyLeavesDrops(block, PromenadeBlocks.BLUSH_SAKURA_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, this::createShearsOrSilkTouchOnlyDrop);

        dropSelf(PromenadeBlocks.COTTON_SAKURA_SAPLING);
        dropPottedContents(PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING);
        add(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, block -> this.createLeavesDrops(block, PromenadeBlocks.COTTON_SAKURA_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_COTTON_SAKURA_BLOSSOMS, block -> this.snowyLeavesDrops(block, PromenadeBlocks.COTTON_SAKURA_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, this::createShearsOrSilkTouchOnlyDrop);

        dropSelf(PromenadeBlocks.STRIPPED_MAPLE_LOG);
        dropSelf(PromenadeBlocks.MAPLE_LOG);
        dropSelf(PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        dropSelf(PromenadeBlocks.MAPLE_WOOD);
        dropSelf(PromenadeBlocks.MAPLE_PLANKS);
        dropSelf(PromenadeBlocks.MAPLE_STAIRS);
        add(PromenadeBlocks.MAPLE_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.MAPLE_FENCE);
        dropSelf(PromenadeBlocks.MAPLE_FENCE_GATE);
        add(PromenadeBlocks.MAPLE_DOOR, this::createDoorTable);
        dropSelf(PromenadeBlocks.MAPLE_TRAPDOOR);
        dropSelf(PromenadeBlocks.MAPLE_BUTTON);
        dropSelf(PromenadeBlocks.MAPLE_PRESSURE_PLATE);
        dropSelf(PromenadeBlocks.MAPLE_SIGN);
        dropSelf(PromenadeBlocks.MAPLE_HANGING_SIGN);
        dropSelf(PromenadeBlocks.MAPLE_SHELF);

        dropSelf(PromenadeBlocks.SAP_MAPLE_SAPLING);
        dropPottedContents(PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING);
        add(PromenadeBlocks.SAP_MAPLE_LEAVES, block -> this.createLeavesDrops(block, PromenadeBlocks.SAP_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_SAP_MAPLE_LEAVES, block -> this.snowyLeavesDrops(block, PromenadeBlocks.SAP_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.FALLEN_SAP_MAPLE_LEAVES, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);

        dropSelf(PromenadeBlocks.VERMILION_MAPLE_SAPLING);
        dropPottedContents(PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING);
        add(PromenadeBlocks.VERMILION_MAPLE_LEAVES, block -> this.createLeavesDrops(block, PromenadeBlocks.VERMILION_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_VERMILION_MAPLE_LEAVES, block -> this.snowyLeavesDrops(block, PromenadeBlocks.VERMILION_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.FALLEN_VERMILION_MAPLE_LEAVES, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);

        dropSelf(PromenadeBlocks.FULVOUS_MAPLE_SAPLING);
        dropPottedContents(PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING);
        add(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, block -> this.createLeavesDrops(block, PromenadeBlocks.FULVOUS_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_FULVOUS_MAPLE_LEAVES, block -> this.snowyLeavesDrops(block, PromenadeBlocks.FULVOUS_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.FALLEN_FULVOUS_MAPLE_LEAVES, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);

        dropSelf(PromenadeBlocks.MIKADO_MAPLE_SAPLING);
        dropPottedContents(PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING);
        add(PromenadeBlocks.MIKADO_MAPLE_LEAVES, block -> this.createLeavesDrops(block, PromenadeBlocks.MIKADO_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_MIKADO_MAPLE_LEAVES, block -> this.snowyLeavesDrops(block, PromenadeBlocks.MIKADO_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.FALLEN_MIKADO_MAPLE_LEAVES, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);

        dropSelf(PromenadeBlocks.STRIPPED_PALM_LOG);
        dropSelf(PromenadeBlocks.PALM_LOG);
        dropSelf(PromenadeBlocks.STRIPPED_PALM_WOOD);
        dropSelf(PromenadeBlocks.PALM_WOOD);
        dropSelf(PromenadeBlocks.PALM_PLANKS);
        dropSelf(PromenadeBlocks.PALM_STAIRS);
        add(PromenadeBlocks.PALM_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.PALM_FENCE);
        dropSelf(PromenadeBlocks.PALM_FENCE_GATE);
        add(PromenadeBlocks.PALM_DOOR, this::createDoorTable);
        dropSelf(PromenadeBlocks.PALM_TRAPDOOR);
        dropSelf(PromenadeBlocks.PALM_BUTTON);
        dropSelf(PromenadeBlocks.PALM_PRESSURE_PLATE);
        dropSelf(PromenadeBlocks.PALM_SIGN);
        dropSelf(PromenadeBlocks.PALM_HANGING_SIGN);
        dropSelf(PromenadeBlocks.PALM_SHELF);

        dropSelf(PromenadeBlocks.PALM_SAPLING);
        dropPottedContents(PromenadeBlocks.POTTED_PALM_SAPLING);
        add(PromenadeBlocks.PALM_LEAVES, block -> this.createLeavesDrops(block, PromenadeBlocks.PALM_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.SNOWY_PALM_LEAVES, block -> this.snowyLeavesDrops(block, PromenadeBlocks.PALM_SAPLING, SAPLING_DROP_CHANCE));
        add(PromenadeBlocks.PALM_HANGING_LEAVES, this::createShearsOrSilkTouchOnlyDrop);
        add(PromenadeBlocks.PALM_LEAF_PILE, this::createShearsOrSilkTouchOnlyDrop);

        add(PromenadeBlocks.DARK_AMARANTH_NYLIUM, block -> this.createSingleItemTableWithSilkTouch(block, Blocks.NETHERRACK));
        dropSelf(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_ROOTS);
        dropPottedContents(PromenadeBlocks.POTTED_DARK_AMARANTH_ROOTS);

        dropSelf(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_STEM);
        dropSelf(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_HYPHAE);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_PLANKS);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_STAIRS);
        add(PromenadeBlocks.DARK_AMARANTH_SLAB, this::createSlabItemTable);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_FENCE);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_FENCE_GATE);
        add(PromenadeBlocks.DARK_AMARANTH_DOOR, this::createDoorTable);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_TRAPDOOR);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_BUTTON);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_PRESSURE_PLATE);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_SIGN);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);
        dropSelf(PromenadeBlocks.DARK_AMARANTH_SHELF);

        dropSelf(PromenadeBlocks.DARK_AMARANTH_FUNGUS);
        dropPottedContents(PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS);

        dropSelf(PromenadeBlocks.SOUL_SHROOMLIGHT);

        addNetherVinesDropTable(PromenadeBlocks.COILED_VINES, PromenadeBlocks.COILED_VINES_PLANT);

        dropSelf(PromenadeBlocks.MOAI);

        //TODO: generify this fat block of code
        this.add(
                PromenadeBlocks.BLUEBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PromenadeBlocks.BLUEBERRY_BUSH)
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BerryBushBlock.AGE, 3)))
                                                .add(LootItem.lootTableItem(PromenadeItems.BLUEBERRIES))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PromenadeBlocks.BLUEBERRY_BUSH)
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BerryBushBlock.AGE, 2)))
                                                .add(LootItem.lootTableItem(PromenadeItems.BLUEBERRIES))
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );
    }

    public LootTable.Builder snowyLeavesDrops(Block leaves, Block sapling, float... saplingChance) {
        return this.createLeavesDrops(leaves, sapling, saplingChance)
                .withPool(
                        LootPool.lootPool()
                                .when(this.doesNotHaveShearsOrSilkTouch())
                                .add(
                                        this.applyExplosionCondition(leaves,
                                                LootItem.lootTableItem(Items.SNOWBALL)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        )
                                ));
    }

    public LootTable.Builder snowyMangroveLeavesDrops(Block leaves) {
        return this.createMangroveLeavesDrops(leaves)
                .withPool(
                        LootPool.lootPool()
                                .when(this.doesNotHaveShearsOrSilkTouch())
                                .add(
                                        this.applyExplosionCondition(leaves,
                                                LootItem.lootTableItem(Items.SNOWBALL).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        )
                                ));
    }

    public LootTable.Builder fruitLeavesDrops(Block leaves, Block sapling, Item fruit, float... saplingChance) {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(leaves, sapling, saplingChance)
                .withPool(LootPool.lootPool()
                        .when(this.doesNotHaveSilkTouch())
                        .add(
                                this.applyExplosionCondition(leaves, LootItem.lootTableItem(fruit))
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                        )
                );
    }

    public LootTable.Builder snowyFruitLeavesDrop(Block leaves, Block sapling, Item fruit, float... saplingChance) {
        return this.fruitLeavesDrops(leaves, sapling, fruit, saplingChance)
                .withPool(LootPool.lootPool()
                        .when(this.doesNotHaveShearsOrSilkTouch())
                        .add(
                                this.applyExplosionCondition(leaves,
                                        LootItem.lootTableItem(Items.SNOWBALL).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                )
                        )
                );
    }

    public Function<Block, LootTable.Builder> flowerPile(Item flower) {
        return block -> this.flowerPile(block, flower);
    }

    public LootTable.Builder flowerPile(Block pile, Item flower) {
        return this.createSilkTouchOrShearsDispatchTable(
                pile,
                this.applyExplosionDecay(pile, LootItem.lootTableItem(flower).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
        );
    }
}