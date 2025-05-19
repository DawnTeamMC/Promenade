package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class UntintedParticleSnowyExtendedLeavesBlock extends ExtendedLeavesBlock {
    public static final MapCodec<UntintedParticleSnowyExtendedLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.rangedInclusiveFloat(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter(untintedParticleLeavesBlock -> untintedParticleLeavesBlock.leafParticleChance),
            ParticleTypes.TYPE_CODEC.fieldOf("leaf_particle").forGetter(untintedParticleLeavesBlock -> untintedParticleLeavesBlock.leafParticleEffect),
            createSettingsCodec()
    ).apply(instance, UntintedParticleSnowyExtendedLeavesBlock::new));
    public static final BooleanProperty BOTTOM = Properties.BOTTOM;

    protected final ParticleEffect leafParticleEffect;

    public UntintedParticleSnowyExtendedLeavesBlock(float leafParticleChance, ParticleEffect leafParticleEffect, Settings settings) {
        super(leafParticleChance, settings);
        this.setDefaultState(this.getDefaultState().with(BOTTOM, false));
        this.leafParticleEffect = leafParticleEffect;
    }

    @Override
    public MapCodec<? extends UntintedParticleSnowyExtendedLeavesBlock> getCodec() {
        return CODEC;
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
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        BlockState stateBelow = world.getBlockState(pos.down());
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random).with(BOTTOM, !isSnow(stateBelow));
    }

    public static boolean isSnow(BlockState state) {
        return state.getBlock() instanceof UntintedParticleSnowyExtendedLeavesBlock;
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        ParticleUtil.spawnParticle(world, pos, random, this.leafParticleEffect);
    }
}
