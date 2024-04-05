package fr.hugman.promenade.block;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class BerryBushBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<BerryBushBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    RegistryKey.createCodec(RegistryKeys.ITEM).fieldOf("berry").forGetter(block -> block.berry),
                    Codec.BOOL.fieldOf("is_spiny").forGetter(block -> block.isSpiny),
                    createSettingsCodec())
            .apply(instance, BerryBushBlock::new));

    private static final float MIN_MOVEMENT_FOR_DAMAGE = 0.003f;
    public static final int MAX_AGE = 3;
    public static final IntProperty AGE = Properties.AGE_3;
    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    private final RegistryKey<Item> berry;
    private final boolean isSpiny;

    public BerryBushBlock(RegistryKey<Item> berry, boolean isSpiny, AbstractBlock.Settings settings) {
        super(settings);
        this.berry = berry;
        this.isSpiny = isSpiny;
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    public static AbstractBlock.Settings createSettings() {
        return AbstractBlock.Settings.create()
                .mapColor(MapColor.DARK_GREEN)
                .ticksRandomly()
                .noCollision()
                .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)
                .pistonBehavior(PistonBehavior.DESTROY)
                .burnable(60, 100);
    }

    public static BerryBushBlock of(RegistryKey<Item> berry, boolean isSpiny) {
        return new BerryBushBlock(berry, isSpiny, createSettings());
    }

    @Override
    public MapCodec<BerryBushBlock> getCodec() {
        return CODEC;
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(DataFixUtils.orElse(world.getRegistryManager().get(RegistryKeys.ITEM).getOrEmpty(this.berry), this));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(AGE) == 0) {
            return SMALL_SHAPE;
        }
        if (state.get(AGE) < MAX_AGE) {
            return LARGE_SHAPE;
        }
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = state.get(AGE);
        if (age < MAX_AGE && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
            BlockState blockState = state.with(AGE, age + 1);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getType() == EntityType.FOX || entity.getType() == EntityType.BEE) {
            return;
        }
        entity.slowMovement(state, new Vec3d(0.8f, 0.75, 0.8f));
        if (this.isSpiny) {
            if (!(world.isClient || state.get(AGE) <= 0 || entity.lastRenderX == entity.getX() && entity.lastRenderZ == entity.getZ())) {
                double d = Math.abs(entity.getX() - entity.lastRenderX);
                double e = Math.abs(entity.getZ() - entity.lastRenderZ);
                if (d >= MIN_MOVEMENT_FOR_DAMAGE || e >= MIN_MOVEMENT_FOR_DAMAGE) {
                    entity.damage(world.getDamageSources().sweetBerryBush(), 1.0f);
                }
            }
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(AGE) != MAX_AGE && stack.isOf(Items.BONE_MEAL)) {
            return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int age = state.get(AGE);
        if (age > 1) {
            int j = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(DataFixUtils.orElse(world.getRegistryManager().get(RegistryKeys.ITEM).getOrEmpty(this.berry), this), j + (age == MAX_AGE ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            BlockState blockState = state.with(AGE, 1);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < 3;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(MAX_AGE, state.get(AGE) + 1);
        world.setBlockState(pos, state.with(AGE, i), Block.NOTIFY_LISTENERS);
    }
}