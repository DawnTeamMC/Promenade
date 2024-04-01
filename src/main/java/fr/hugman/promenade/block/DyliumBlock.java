package fr.hugman.promenade.block;

import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.block.BoneMealSpreadable;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.content.AmaranthContent;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NyliumBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

//TODO: make generic
public class DyliumBlock extends NyliumBlock implements BoneMealSpreadable {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BONEMEAL_VEGETATION = DawnFactory.configuredFeature(Promenade.id("dark_amaranth_forest_vegetation/bonemeal"));

    public DyliumBlock(Settings settings) {
        super(settings);
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            world.setBlockState(pos, Blocks.END_STONE.getDefaultState());
        }
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        ConfiguredFeature<?, ?> configuredFeature = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).get(BONEMEAL_VEGETATION);
        return configuredFeature != null && super.canGrow(world, random, pos, state);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockState blockState = world.getBlockState(pos);
        BlockPos blockPos = pos.up();
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        if (blockState.isOf(AmaranthContent.BLACK_DYLIUM)) {
            ConfiguredFeature<?, ?> configuredFeature = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).get(BONEMEAL_VEGETATION);
            if (configuredFeature != null) configuredFeature.generate(world, chunkGenerator, random, blockPos);
        }
    }

    @Override
    public boolean canSpreadAt(BlockView world, BlockPos pos) {
        return world.getBlockState(pos).isIn(PromenadeBlockTags.CAN_SPREAD_BLACK_DYLIUM) && world.getBlockState(pos.up()).isAir();
    }
}
