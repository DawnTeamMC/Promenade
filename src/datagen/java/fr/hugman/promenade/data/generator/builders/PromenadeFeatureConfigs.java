package fr.hugman.promenade.data.generator.builders;

import com.google.common.collect.ImmutableList;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.gen.tree.foliage.PalmFoliagePlacer;
import fr.hugman.promenade.world.gen.tree.trunk.LeapingTrunkPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.MegaPineFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class PromenadeFeatureConfigs {
    public static final BlockPredicate FUNGUS_REPLACEABLE = BlockPredicate.matchingBlocks(
            Blocks.OAK_SAPLING,
            Blocks.SPRUCE_SAPLING,
            Blocks.BIRCH_SAPLING,
            Blocks.JUNGLE_SAPLING,
            Blocks.ACACIA_SAPLING,
            Blocks.CHERRY_SAPLING,
            Blocks.DARK_OAK_SAPLING,
            Blocks.PALE_OAK_SAPLING,
            Blocks.MANGROVE_PROPAGULE,
            Blocks.DANDELION,
            Blocks.TORCHFLOWER,
            Blocks.POPPY,
            Blocks.BLUE_ORCHID,
            Blocks.ALLIUM,
            Blocks.AZURE_BLUET,
            Blocks.RED_TULIP,
            Blocks.ORANGE_TULIP,
            Blocks.WHITE_TULIP,
            Blocks.PINK_TULIP,
            Blocks.OXEYE_DAISY,
            Blocks.CORNFLOWER,
            Blocks.WITHER_ROSE,
            Blocks.LILY_OF_THE_VALLEY,
            Blocks.BROWN_MUSHROOM,
            Blocks.RED_MUSHROOM,
            Blocks.WHEAT,
            Blocks.SUGAR_CANE,
            Blocks.ATTACHED_PUMPKIN_STEM,
            Blocks.ATTACHED_MELON_STEM,
            Blocks.PUMPKIN_STEM,
            Blocks.MELON_STEM,
            Blocks.LILY_PAD,
            Blocks.NETHER_WART,
            Blocks.COCOA,
            Blocks.CARROTS,
            Blocks.POTATOES,
            Blocks.CHORUS_PLANT,
            Blocks.CHORUS_FLOWER,
            Blocks.TORCHFLOWER_CROP,
            Blocks.PITCHER_CROP,
            Blocks.BEETROOTS,
            Blocks.SWEET_BERRY_BUSH,
            Blocks.WARPED_FUNGUS,
            Blocks.CRIMSON_FUNGUS,
            Blocks.WEEPING_VINES,
            Blocks.WEEPING_VINES_PLANT,
            Blocks.TWISTING_VINES,
            Blocks.TWISTING_VINES_PLANT,
            Blocks.CAVE_VINES,
            Blocks.CAVE_VINES_PLANT,
            Blocks.SPORE_BLOSSOM,
            Blocks.AZALEA,
            Blocks.FLOWERING_AZALEA,
            Blocks.MOSS_CARPET,
            Blocks.PINK_PETALS,
            Blocks.BIG_DRIPLEAF,
            Blocks.BIG_DRIPLEAF_STEM,
            Blocks.SMALL_DRIPLEAF
    );


    public static TreeFeatureConfig.Builder palm() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(PromenadeBlocks.PALM_LOG),
                new LeapingTrunkPlacer(6, 5, 2, BiasedToBottomIntProvider.create(3, 10), UniformIntProvider.create(-1, 0), 0.45f, 2),
                BlockStateProvider.of(PromenadeBlocks.PALM_LEAVES),
                new PalmFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1, 0, 1)
        )
                .decorators(ImmutableList.of(new AttachedToLeavesTreeDecorator(0.8f, 1, 0, BlockStateProvider.of(PromenadeBlocks.PALM_HANGING_LEAVES), 2, List.of(Direction.DOWN))))
                .dirtProvider(BlockStateProvider.of(Blocks.SAND))
                .forceDirt()
                .ignoreVines();
    }

    public static TreeFeatureConfig.Builder sakura(Block leaves, boolean fancy) {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(PromenadeBlocks.SAKURA_LOG),
                fancy ? new ForkingTrunkPlacer(5, 3, 2) :
                        new ForkingTrunkPlacer(3, 2, 1),
                BlockStateProvider.of(leaves),
                new AcaciaFoliagePlacer(UniformIntProvider.create(1, 2), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines();
    }

    public static TreeFeatureConfig.Builder maple(Block leaves, boolean fancy) {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(PromenadeBlocks.MAPLE_LOG),
                fancy ? new StraightTrunkPlacer(8, 5, 2) :
                        new StraightTrunkPlacer(5, 4, 0),
                BlockStateProvider.of(leaves),
                new LargeOakFoliagePlacer(BiasedToBottomIntProvider.create(2, 3), ConstantIntProvider.create(1), 4),
                fancy ? new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)) :
                        new TwoLayersFeatureSize(1, 0, 1, OptionalInt.empty())
        ).ignoreVines();
    }

    public static TreeFeatureConfig.Builder snowyMegaSpruce(Block leaves) {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.SPRUCE_LOG),
                new GiantTrunkPlacer(13, 2, 14),
                BlockStateProvider.of(leaves),
                new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(13, 17)),
                new TwoLayersFeatureSize(1, 1, 2)
        );
    }

    public static HugeFungusFeatureConfig hugeDarkAmaranthFungus(boolean planted) {
        return new HugeFungusFeatureConfig(
                PromenadeBlocks.DARK_AMARANTH_NYLIUM.getDefaultState(),
                PromenadeBlocks.DARK_AMARANTH_STEM.getDefaultState(),
                PromenadeBlocks.DARK_AMARANTH_WART_BLOCK.getDefaultState(),
                Blocks.COBWEB.getDefaultState(),
                PromenadeFeatureConfigs.FUNGUS_REPLACEABLE,
                planted
        );
    }

}
