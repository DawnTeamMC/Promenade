package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class WitherRosePileBlock extends PileBlock {
    public static final MapCodec<WitherRosePileBlock> CODEC = simpleCodec(WitherRosePileBlock::new);

    public WitherRosePileBlock(Properties builder) {
        super(builder);
    }

    @Override
    protected MapCodec<WitherRosePileBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return super.mayPlaceOn(state, world, pos) || state.getBlock() == Blocks.SOUL_SAND;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
        for (int i = 0; i < 5; ++i) {
            if (rand.nextBoolean()) {
                world.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + (double) (rand.nextInt(17) / 16), (double) pos.getY() + (0.5D - (double) rand.nextFloat()), (double) pos.getZ() + (double) (rand.nextInt(17) / 16), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
	protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl) {
        if (world instanceof ServerLevel serverWorld
                && world.getDifficulty() != Difficulty.PEACEFUL
                && entity instanceof LivingEntity livingEntity
                && !livingEntity.isInvulnerableTo(serverWorld, world.damageSources().wither())) {
            livingEntity.addEffect(this.getContactEffect());
        }
		super.entityInside(state, world, pos, entity, handler, bl);
    }

    public MobEffectInstance getContactEffect() {
        return new MobEffectInstance(MobEffects.WITHER, 40);
    }
}
