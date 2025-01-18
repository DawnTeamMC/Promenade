package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.block.BerryBushBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.item.PromenadeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class PromenadeBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final float[] JUNGLE_SAPLING_DROP_CHANCE = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};
    protected static final float[] SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public PromenadeBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> enchantments = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(PromenadeBlocks.ASPHALT);
        addDrop(PromenadeBlocks.ASPHALT_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.ASPHALT_STAIRS);
        addDrop(PromenadeBlocks.ASPHALT_WALL);
        addDrop(PromenadeBlocks.POLISHED_ASPHALT);
        addDrop(PromenadeBlocks.POLISHED_ASPHALT_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.POLISHED_ASPHALT_STAIRS);

        addDrop(PromenadeBlocks.BLUNITE);
        addDrop(PromenadeBlocks.BLUNITE_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.BLUNITE_STAIRS);
        addDrop(PromenadeBlocks.BLUNITE_WALL);
        addDrop(PromenadeBlocks.POLISHED_BLUNITE);
        addDrop(PromenadeBlocks.POLISHED_BLUNITE_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.POLISHED_BLUNITE_STAIRS);

        addDrop(PromenadeBlocks.OAK_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.SPRUCE_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.BIRCH_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.JUNGLE_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.ACACIA_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.CHERRY_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.DARK_OAK_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.PALE_OAK_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.MANGROVE_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.AZALEA_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.FLOWERING_AZALEA_LEAF_PILE, this::dropsWithShears);

        addDrop(PromenadeBlocks.DANDELION_PILE, flowerPile(Items.DANDELION));
        addDrop(PromenadeBlocks.POPPY_PILE, flowerPile(Items.POPPY));
        addDrop(PromenadeBlocks.BLUE_ORCHID_PILE, flowerPile(Items.BLUE_ORCHID));
        addDrop(PromenadeBlocks.ALLIUM_PILE, flowerPile(Items.ALLIUM));
        addDrop(PromenadeBlocks.AZURE_BLUET_PILE, flowerPile(Items.AZURE_BLUET));
        addDrop(PromenadeBlocks.RED_TULIP_PILE, flowerPile(Items.RED_TULIP));
        addDrop(PromenadeBlocks.ORANGE_TULIP_PILE, flowerPile(Items.ORANGE_TULIP));
        addDrop(PromenadeBlocks.WHITE_TULIP_PILE, flowerPile(Items.WHITE_TULIP));
        addDrop(PromenadeBlocks.PINK_TULIP_PILE, flowerPile(Items.PINK_TULIP));
        addDrop(PromenadeBlocks.OXEYE_DAISY_PILE, flowerPile(Items.OXEYE_DAISY));
        addDrop(PromenadeBlocks.CORNFLOWER_PILE, flowerPile(Items.CORNFLOWER));
        addDrop(PromenadeBlocks.LILY_OF_THE_VALLEY_PILE, flowerPile(Items.LILY_OF_THE_VALLEY));
        addDrop(PromenadeBlocks.WITHER_ROSE_PILE, flowerPile(Items.WITHER_ROSE));

        addDrop(PromenadeBlocks.SNOWY_SPRUCE_LEAVES, block -> this.snowyLeavesDrops(block, Blocks.SPRUCE_SAPLING, SAPLING_DROP_CHANCE));

        addDrop(PromenadeBlocks.STRIPPED_SAKURA_LOG);
        addDrop(PromenadeBlocks.SAKURA_LOG);
        addDrop(PromenadeBlocks.STRIPPED_SAKURA_WOOD);
        addDrop(PromenadeBlocks.SAKURA_WOOD);
        addDrop(PromenadeBlocks.SAKURA_PLANKS);
        addDrop(PromenadeBlocks.SAKURA_STAIRS);
        addDrop(PromenadeBlocks.SAKURA_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.SAKURA_FENCE);
        addDrop(PromenadeBlocks.SAKURA_FENCE_GATE);
        addDrop(PromenadeBlocks.SAKURA_DOOR, this::doorDrops);
        addDrop(PromenadeBlocks.SAKURA_TRAPDOOR);
        addDrop(PromenadeBlocks.SAKURA_BUTTON);
        addDrop(PromenadeBlocks.SAKURA_PRESSURE_PLATE);
        addDrop(PromenadeBlocks.SAKURA_SIGN);
        addDrop(PromenadeBlocks.SAKURA_HANGING_SIGN);

        addDrop(PromenadeBlocks.BLUSH_SAKURA_SAPLING);
        addPottedPlantDrops(PromenadeBlocks.POTTED_BLUSH_SAKURA_SAPLING);
        addDrop(PromenadeBlocks.BLUSH_SAKURA_BLOSSOMS, block -> this.leavesDrops(block, PromenadeBlocks.BLUSH_SAKURA_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(PromenadeBlocks.BLUSH_SAKURA_BLOSSOM_PILE, this::dropsWithShears);

        addDrop(PromenadeBlocks.COTTON_SAKURA_SAPLING);
        addPottedPlantDrops(PromenadeBlocks.POTTED_COTTON_SAKURA_SAPLING);
        addDrop(PromenadeBlocks.COTTON_SAKURA_BLOSSOMS, block -> this.leavesDrops(block, PromenadeBlocks.COTTON_SAKURA_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(PromenadeBlocks.COTTON_SAKURA_BLOSSOM_PILE, this::dropsWithShears);

        addDrop(PromenadeBlocks.STRIPPED_MAPLE_LOG);
        addDrop(PromenadeBlocks.MAPLE_LOG);
        addDrop(PromenadeBlocks.STRIPPED_MAPLE_WOOD);
        addDrop(PromenadeBlocks.MAPLE_WOOD);
        addDrop(PromenadeBlocks.MAPLE_PLANKS);
        addDrop(PromenadeBlocks.MAPLE_STAIRS);
        addDrop(PromenadeBlocks.MAPLE_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.MAPLE_FENCE);
        addDrop(PromenadeBlocks.MAPLE_FENCE_GATE);
        addDrop(PromenadeBlocks.MAPLE_DOOR, this::doorDrops);
        addDrop(PromenadeBlocks.MAPLE_TRAPDOOR);
        addDrop(PromenadeBlocks.MAPLE_BUTTON);
        addDrop(PromenadeBlocks.MAPLE_PRESSURE_PLATE);
        addDrop(PromenadeBlocks.MAPLE_SIGN);
        addDrop(PromenadeBlocks.MAPLE_HANGING_SIGN);

        addDrop(PromenadeBlocks.SAP_MAPLE_SAPLING);
        addPottedPlantDrops(PromenadeBlocks.POTTED_SAP_MAPLE_SAPLING);
        addDrop(PromenadeBlocks.SAP_MAPLE_LEAVES, block -> this.leavesDrops(block, PromenadeBlocks.SAP_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(PromenadeBlocks.SAP_MAPLE_LEAF_PILE, this::dropsWithShears);

        addDrop(PromenadeBlocks.VERMILION_MAPLE_SAPLING);
        addPottedPlantDrops(PromenadeBlocks.POTTED_VERMILION_MAPLE_SAPLING);
        addDrop(PromenadeBlocks.VERMILION_MAPLE_LEAVES, block -> this.leavesDrops(block, PromenadeBlocks.VERMILION_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(PromenadeBlocks.VERMILION_MAPLE_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.VERMILION_CARPETED_GRASS_BLOCK);

        addDrop(PromenadeBlocks.FULVOUS_MAPLE_SAPLING);
        addPottedPlantDrops(PromenadeBlocks.POTTED_FULVOUS_MAPLE_SAPLING);
        addDrop(PromenadeBlocks.FULVOUS_MAPLE_LEAVES, block -> this.leavesDrops(block, PromenadeBlocks.FULVOUS_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(PromenadeBlocks.FULVOUS_MAPLE_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.FULVOUS_CARPETED_GRASS_BLOCK);

        addDrop(PromenadeBlocks.MIKADO_MAPLE_SAPLING);
        addPottedPlantDrops(PromenadeBlocks.POTTED_MIKADO_MAPLE_SAPLING);
        addDrop(PromenadeBlocks.MIKADO_MAPLE_LEAVES, block -> this.leavesDrops(block, PromenadeBlocks.MIKADO_MAPLE_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(PromenadeBlocks.MIKADO_MAPLE_LEAF_PILE, this::dropsWithShears);
        addDrop(PromenadeBlocks.MIKADO_CARPETED_GRASS_BLOCK);

        addDrop(PromenadeBlocks.STRIPPED_PALM_LOG);
        addDrop(PromenadeBlocks.PALM_LOG);
        addDrop(PromenadeBlocks.STRIPPED_PALM_WOOD);
        addDrop(PromenadeBlocks.PALM_WOOD);
        addDrop(PromenadeBlocks.PALM_PLANKS);
        addDrop(PromenadeBlocks.PALM_STAIRS);
        addDrop(PromenadeBlocks.PALM_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.PALM_FENCE);
        addDrop(PromenadeBlocks.PALM_FENCE_GATE);
        addDrop(PromenadeBlocks.PALM_DOOR, this::doorDrops);
        addDrop(PromenadeBlocks.PALM_TRAPDOOR);
        addDrop(PromenadeBlocks.PALM_BUTTON);
        addDrop(PromenadeBlocks.PALM_PRESSURE_PLATE);
        addDrop(PromenadeBlocks.PALM_SIGN);
        addDrop(PromenadeBlocks.PALM_HANGING_SIGN);

        addDrop(PromenadeBlocks.PALM_SAPLING);
        addPottedPlantDrops(PromenadeBlocks.POTTED_PALM_SAPLING);
        addDrop(PromenadeBlocks.PALM_LEAVES, block -> this.leavesDrops(block, PromenadeBlocks.PALM_SAPLING, SAPLING_DROP_CHANCE));
        //TODO: Palm Hanging Leaves
        addDrop(PromenadeBlocks.PALM_LEAF_PILE, this::dropsWithShears);

        addDrop(PromenadeBlocks.DARK_AMARANTH_NYLIUM, block -> this.drops(block, Blocks.END_STONE));
        addDrop(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK);
        addDrop(PromenadeBlocks.DARK_AMARANTH_ROOTS);
        addPottedPlantDrops(PromenadeBlocks.POTTED_DARK_AMARANTH_ROOTS);

        addDrop(PromenadeBlocks.STRIPPED_DARK_AMARANTH_STEM);
        addDrop(PromenadeBlocks.DARK_AMARANTH_STEM);
        addDrop(PromenadeBlocks.STRIPPED_DARK_AMARANTH_HYPHAE);
        addDrop(PromenadeBlocks.DARK_AMARANTH_HYPHAE);
        addDrop(PromenadeBlocks.DARK_AMARANTH_PLANKS);
        addDrop(PromenadeBlocks.DARK_AMARANTH_STAIRS);
        addDrop(PromenadeBlocks.DARK_AMARANTH_SLAB, this::slabDrops);
        addDrop(PromenadeBlocks.DARK_AMARANTH_FENCE);
        addDrop(PromenadeBlocks.DARK_AMARANTH_FENCE_GATE);
        addDrop(PromenadeBlocks.DARK_AMARANTH_DOOR, this::doorDrops);
        addDrop(PromenadeBlocks.DARK_AMARANTH_TRAPDOOR);
        addDrop(PromenadeBlocks.DARK_AMARANTH_BUTTON);
        addDrop(PromenadeBlocks.DARK_AMARANTH_PRESSURE_PLATE);
        addDrop(PromenadeBlocks.DARK_AMARANTH_SIGN);
        addDrop(PromenadeBlocks.DARK_AMARANTH_HANGING_SIGN);

        addDrop(PromenadeBlocks.DARK_AMARANTH_FUNGUS);
        addPottedPlantDrops(PromenadeBlocks.POTTED_DARK_AMARANTH_FUNGUS);

        addDrop(PromenadeBlocks.MOAI);

        //TODO: generify this fat block of code
        this.addDrop(
                PromenadeBlocks.BLUEBERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.builder()
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(PromenadeBlocks.BLUEBERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BerryBushBlock.AGE, 3))
                                                )
                                                .with(ItemEntry.builder(PromenadeItems.BLUEBERRIES))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(PromenadeBlocks.BLUEBERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BerryBushBlock.AGE, 2))
                                                )
                                                .with(ItemEntry.builder(PromenadeItems.BLUEBERRIES))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );

        this.lootTables.forEach((id, lootTable) -> lootTable.randomSequenceId(id.getValue()));
    }


    public LootTable.Builder snowyLeavesDrops(Block leaves, Block sapling, float... saplingChance) {
        return this.leavesDrops(leaves, sapling, saplingChance)
                .pool(
                        LootPool.builder()
                                .conditionally(this.createWithoutShearsOrSilkTouchCondition())
                                .with(
                                        this.addSurvivesExplosionCondition(leaves,
                                                ItemEntry.builder(Items.SNOWBALL)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F)))
                                        )
                                ));
    }

    public LootTable.Builder fruitLeavesDrops(Block leaves, Block sapling, Item fruit, float... saplingChance) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance)
                .pool(LootPool.builder()
                        .conditionally(this.createWithoutSilkTouchCondition())
                        .with(
                                this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(fruit))
                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                        )
                );
    }

    public Function<Block, LootTable.Builder> flowerPile(Item flower) {
        return block -> this.flowerPile(block, flower);
    }

    public LootTable.Builder flowerPile(Block pile, Item flower) {
        return this.dropsWithSilkTouchOrShears(
                pile,
                this.applyExplosionDecay(pile, ItemEntry.builder(flower).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))))
        );
    }
}