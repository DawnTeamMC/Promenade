package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
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

public class NyliumBlock extends Block implements Fertilizable {
    public static final MapCodec<NyliumBlock> CODEC = createCodec(NyliumBlock::new);

    @Override
    public MapCodec<NyliumBlock> getCodec() {
        return CODEC;
    }

    public NyliumBlock(Settings settings) {
        super(settings);
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        int i = ChunkLightProvider.getRealisticOpacity(state, blockState, Direction.UP, blockState.getOpacity());
        return i < 15;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!stayAlive(state, world, pos)) {
            //TODO: make this configurable
            world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        Registry<ConfiguredFeature<?, ?>> registry = world.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE);
        //TODO: make this configurable
        this.generate(registry, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION, world, chunkGenerator, random, blockPos);
    }

    private void generate(
            Registry<ConfiguredFeature<?, ?>> registry,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            ServerWorld world,
            ChunkGenerator chunkGenerator,
            Random random,
            BlockPos pos
    ) {
        registry.getOptional(key).ifPresent(entry -> entry.value().generate(world, chunkGenerator, random, pos));
    }

    @Override
    public FertilizableType getFertilizableType() {
        return FertilizableType.NEIGHBOR_SPREADER;
    }
}
