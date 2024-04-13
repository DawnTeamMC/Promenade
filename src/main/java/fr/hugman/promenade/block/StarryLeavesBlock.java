package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * Leaves block that can obtain stars on it at night.
 *
 * @author Hugman
 */
public class StarryLeavesBlock extends LeavesBlock {
    public static final MapCodec<StarryLeavesBlock> CODEC = createCodec(StarryLeavesBlock::new);
    public static final BooleanProperty HAS_STARS = PromenadeBlockProperties.HAS_STARS;

    public StarryLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HAS_STARS, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_STARS);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (world.isNight()) {
            if (random.nextInt(100) == 0) {
                var newState = state.with(HAS_STARS, true);
                world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(newState));
                // TODO: play sound
            }
        } else {
            if (state.get(HAS_STARS)) {
                var newState = state.with(HAS_STARS, false);
                world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(newState));
                // TODO: play sound
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (!state.get(HAS_STARS)) {
            return;
        }
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        var dir = Direction.random(random);
        if (!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), dir)) {
            //TODO: change particle
            //TODO: fix particle offset
            var vel = new Vec3d(MathHelper.nextDouble(random, -0.5, 0.5), MathHelper.nextDouble(random, -0.5, 0.5), MathHelper.nextDouble(random, -0.5, 0.5));
            ParticleUtil.spawnParticle(world, pos, dir, ParticleTypes.CRIT, vel, 0.1D);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (state.get(HAS_STARS)) {
            //TODO: change item
            //TODO: don't drop in the middle of the block
            dropStack(world, pos, new ItemStack(Items.FIREWORK_STAR, 3));
            //TODO: change sound
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            BlockState newState = state.with(HAS_STARS, false);
            world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hit);
    }
}
