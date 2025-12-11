package fr.hugman.promenade.block;

import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public abstract class SnowyLeavesBlock extends LeavesBlock {
    public static final BooleanProperty BOTTOM = Properties.BOTTOM;

    public SnowyLeavesBlock(float leafParticleChance, Settings settings) {
        super(leafParticleChance, settings);
        this.setDefaultState(this.getDefaultState().with(BOTTOM, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BOTTOM);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = super.getPlacementState(context);
        if (state == null) return null;
        BlockState stateBelow = context.getWorld().getBlockState(context.getBlockPos().down());
        return state.with(BOTTOM, !isSnow(stateBelow));
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        if (world.getLightLevel(LightType.BLOCK, pos) > 11 && !world.getBiome(pos).isIn(PromenadeBiomeTags.CAN_FREEZE_DURING_SNOWFALL)) {
            var normalLeaves = world.getRegistryManager().getOrThrow(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION).stream().filter(
                    entry -> entry.snowyBlock().value() == state.getBlock()
            ).findFirst().map(sbt -> sbt.baseBlock().value()).orElse(null);

            var newLeavesState = normalLeaves.getDefaultState();
            // copy properties of snowy block
            for (Property property : state.getProperties()) {
                newLeavesState = newLeavesState.contains(property) ? newLeavesState.with(property, state.get(property)) : newLeavesState;
            }
            world.setBlockState(pos, newLeavesState);
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        BlockState stateBelow = world.getBlockState(pos.down());
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random).with(BOTTOM, !isSnow(stateBelow));
    }

    public static boolean isSnow(BlockState state) {
        return state.getBlock() instanceof SnowyLeavesBlock;
    }
}
