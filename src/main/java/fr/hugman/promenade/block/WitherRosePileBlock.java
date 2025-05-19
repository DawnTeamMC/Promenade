package fr.hugman.promenade.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class WitherRosePileBlock extends PileBlock {
    public static final MapCodec<WitherRosePileBlock> CODEC = createCodec(WitherRosePileBlock::new);

    public WitherRosePileBlock(Settings builder) {
        super(builder);
    }

    @Override
    protected MapCodec<WitherRosePileBlock> getCodec() {
        return CODEC;
    }

    @Override
    public boolean canPlantOnTop(BlockState state, BlockView world, BlockPos pos) {
        return super.canPlantOnTop(state, world, pos) || state.getBlock() == Blocks.SOUL_SAND;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
        for (int i = 0; i < 5; ++i) {
            if (rand.nextBoolean()) {
                world.addParticleClient(ParticleTypes.SMOKE, (double) pos.getX() + (double) (rand.nextInt(17) / 16), (double) pos.getY() + (0.5D - (double) rand.nextFloat()), (double) pos.getZ() + (double) (rand.nextInt(17) / 16), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        if (world instanceof ServerWorld serverWorld
                && world.getDifficulty() != Difficulty.PEACEFUL
                && entity instanceof LivingEntity livingEntity
                && !livingEntity.isInvulnerableTo(serverWorld, world.getDamageSources().wither())) {
            livingEntity.addStatusEffect(this.getContactEffect());
        }
        super.onEntityCollision(state, world, pos, entity, handler);
    }

    public StatusEffectInstance getContactEffect() {
        return new StatusEffectInstance(StatusEffects.WITHER, 40);
    }
}
