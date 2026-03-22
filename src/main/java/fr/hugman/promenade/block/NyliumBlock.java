package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.world.gen.feature.PromenadeConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.lighting.LightEngine;

public class NyliumBlock extends Block implements BonemealableBlock {
    public static final MapCodec<NyliumBlock> CODEC = simpleCodec(NyliumBlock::new);

    @Override
    public MapCodec<NyliumBlock> codec() {
        return CODEC;
    }

    public NyliumBlock(Properties settings) {
        super(settings);
    }

    private static boolean stayAlive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.above();
        BlockState blockState = world.getBlockState(blockPos);
        int i = LightEngine.getLightBlockInto(state, blockState, Direction.UP, blockState.getLightBlock());
        return i < 15;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!stayAlive(state, world, pos)) {
            //TODO: make this configurable
            world.setBlockAndUpdate(pos, Blocks.NETHERRACK.defaultBlockState());
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.above();
        ChunkGenerator chunkGenerator = world.getChunkSource().getGenerator();
        Registry<ConfiguredFeature<?, ?>> registry = world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        //TODO: make this configurable
        this.generate(registry, PromenadeConfiguredFeatures.DARK_AMARANTH_FOREST_BONEMEAL_VEGETATION, world, chunkGenerator, random, blockPos);
    }

    private void generate(
            Registry<ConfiguredFeature<?, ?>> registry,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            ServerLevel world,
            ChunkGenerator chunkGenerator,
            RandomSource random,
            BlockPos pos
    ) {
        registry.get(key).ifPresent(entry -> entry.value().place(world, chunkGenerator, random, pos));
    }

    @Override
    public Type getType() {
        return Type.NEIGHBOR_SPREADER;
    }
}
