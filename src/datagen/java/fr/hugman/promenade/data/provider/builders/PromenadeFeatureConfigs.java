package fr.hugman.promenade.data.provider.builders;

import com.google.common.collect.ImmutableList;
import fr.hugman.promenade.block.MapleLogBlock;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.gen.tree.foliage.MapleFoliagePlacer;
import fr.hugman.promenade.world.gen.tree.foliage.PalmFoliagePlacer;
import fr.hugman.promenade.world.gen.tree.trunk.BranchingStraightTrunkPlacer;
import fr.hugman.promenade.world.gen.tree.trunk.LeapingTrunkPlacer;
import java.util.List;
import java.util.OptionalInt;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;

public class PromenadeFeatureConfigs {
    //TODO: should be a tag
    public static final BlockPredicate FUNGUS_REPLACEABLE = BlockPredicate.matchesBlocks(
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


    public static TreeConfiguration.TreeConfigurationBuilder palm() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PromenadeBlocks.PALM_LOG),
                new LeapingTrunkPlacer(6, 5, 2, BiasedToBottomInt.of(3, 10), UniformInt.of(-1, 0), 0.45f, 2),
                BlockStateProvider.simple(PromenadeBlocks.PALM_LEAVES),
                new PalmFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)
        )
                .decorators(ImmutableList.of(new AttachedToLeavesDecorator(0.8f, 1, 0, BlockStateProvider.simple(PromenadeBlocks.PALM_HANGING_LEAVES), 2, List.of(Direction.DOWN))))
                .dirt(BlockStateProvider.simple(Blocks.SAND))
                .forceDirt()
                .ignoreVines();
    }

    public static TreeConfiguration.TreeConfigurationBuilder sakura(Block leaves, boolean fancy) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PromenadeBlocks.SAKURA_LOG),
                fancy ? new ForkingTrunkPlacer(5, 3, 2) :
                        new ForkingTrunkPlacer(3, 2, 1),
                BlockStateProvider.simple(leaves),
                new AcaciaFoliagePlacer(UniformInt.of(1, 2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines();
    }

    public static TreeConfiguration.TreeConfigurationBuilder maple(Block leaves, boolean fancy) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PromenadeBlocks.MAPLE_LOG.defaultBlockState().setValue(MapleLogBlock.NATURAL, true)),
                fancy ? new BranchingStraightTrunkPlacer(17, 5, 3) :
                        new BranchingStraightTrunkPlacer(13, 4, 2),
                BlockStateProvider.simple(leaves),
                new MapleFoliagePlacer(BiasedToBottomInt.of(3, 4), UniformInt.of(5, 6), BiasedToBottomInt.of(17, 20)),
                fancy ? new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)) :
                        new TwoLayersFeatureSize(1, 0, 1, OptionalInt.empty())
        )
                .ignoreVines();
    }

    public static TreeConfiguration.TreeConfigurationBuilder snowyMegaSpruce(Block leaves) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                new GiantTrunkPlacer(13, 2, 14),
                BlockStateProvider.simple(leaves),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)),
                new TwoLayersFeatureSize(1, 1, 2)
        );
    }

    public static HugeFungusConfiguration hugeDarkAmaranthFungus(boolean planted) {
        return new HugeFungusConfiguration(
                PromenadeBlocks.DARK_AMARANTH_NYLIUM.defaultBlockState(),
                PromenadeBlocks.DARK_AMARANTH_STEM.defaultBlockState(),
                PromenadeBlocks.DARK_AMARANTH_WART_BLOCK.defaultBlockState(),
                PromenadeBlocks.SOUL_SHROOMLIGHT.defaultBlockState(),
                PromenadeFeatureConfigs.FUNGUS_REPLACEABLE,
                planted
        );
    }

}
