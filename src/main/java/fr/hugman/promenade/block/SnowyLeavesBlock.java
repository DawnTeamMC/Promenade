package fr.hugman.promenade.block;

import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class SnowyLeavesBlock extends LeavesBlock {
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public SnowyLeavesBlock(float leafParticleChance, Properties settings) {
        super(leafParticleChance, settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BOTTOM, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BOTTOM);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) return null;
        BlockState stateBelow = context.getLevel().getBlockState(context.getClickedPos().below());
        return state.setValue(BOTTOM, !isSnow(stateBelow));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.randomTick(state, world, pos, random);

        if (world.getBrightness(LightLayer.BLOCK, pos) > 11 && !world.getBiome(pos).is(PromenadeBiomeTags.CAN_FREEZE_DURING_SNOWFALL)) {
            var normalLeaves = world.registryAccess().lookupOrThrow(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION).stream().filter(
                    entry -> entry.snowyBlock().value() == state.getBlock()
            ).findFirst().map(sbt -> sbt.baseBlock().value()).orElse(null);

            var newLeavesState = normalLeaves.defaultBlockState();
            // copy properties of snowy block
            for (Property property : state.getProperties()) {
                newLeavesState = newLeavesState.hasProperty(property) ? newLeavesState.setValue(property, state.getValue(property)) : newLeavesState;
            }
            world.setBlockAndUpdate(pos, newLeavesState);
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        BlockState stateBelow = world.getBlockState(pos.below());
        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random).setValue(BOTTOM, !isSnow(stateBelow));
    }

    public static boolean isSnow(BlockState state) {
        return state.getBlock() instanceof SnowyLeavesBlock;
    }
}
