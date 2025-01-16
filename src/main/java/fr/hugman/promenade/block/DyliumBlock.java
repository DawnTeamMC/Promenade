package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatureKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

//TODO: make generic
//TODO: implement grower Fertilizable on endstone
public class DyliumBlock extends Block implements Fertilizable {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BONEMEAL_VEGETATION = PromenadeConfiguredFeatureKeys.DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION;

    public static final MapCodec<DyliumBlock> CODEC = createCodec(DyliumBlock::new);

    @Override
    public MapCodec<DyliumBlock> getCodec() {
        return CODEC;
    }

    public DyliumBlock(Settings settings) {
        super(settings);
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        int i = ChunkLightProvider.getRealisticOpacity(state, blockState, Direction.UP, blockState.getOpacity());
        return i < 15;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            world.setBlockState(pos, Blocks.END_STONE.getDefaultState());
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        ConfiguredFeature<?, ?> configuredFeature = world.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE).get(BONEMEAL_VEGETATION);
        return configuredFeature != null;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockState blockState = world.getBlockState(pos);
        BlockPos blockPos = pos.up();
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        if (blockState.isOf(PromenadeBlocks.BLACK_DYLIUM)) {
            this.generate(world.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE), BONEMEAL_VEGETATION, world, chunkGenerator, random, blockPos);
        }
    }


    private void generate(
            Registry<ConfiguredFeature<?, ?>> registry,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            ServerWorld world,
            ChunkGenerator chunkGenerator,
            Random random,
            BlockPos pos
    ) {
        registry.getOptional(key).ifPresent(entry -> ((ConfiguredFeature) entry.value()).generate(world, chunkGenerator, random, pos));
    }

    @Override
    public Fertilizable.FertilizableType getFertilizableType() {
        return Fertilizable.FertilizableType.NEIGHBOR_SPREADER;
    }
}
