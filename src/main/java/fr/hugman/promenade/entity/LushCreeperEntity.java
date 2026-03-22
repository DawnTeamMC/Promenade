package fr.hugman.promenade.entity;

import java.util.Collection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class LushCreeperEntity extends Creeper {
    private static final int EXPLOSION_Y_LENGTH = 10;

    public LushCreeperEntity(EntityType<? extends Creeper> entityType, Level world) {
        super(entityType, world);
    }

    public static boolean canSpawn(EntityType<? extends Monster> type, ServerLevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return pos.getY() < 0 && Monster.checkMonsterSpawnRules(type, world, spawnReason, pos, random);
    }

    @Override
    protected void explodeCreeper() {
        if (this.level() instanceof ServerLevel serverWorld) {
            boolean hasGeneratedMoss = false;
			if (serverWorld.getGameRules().get(GameRules.MOB_GRIEFING)) {
                Registry<ConfiguredFeature<?, ?>> registry = serverWorld.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
                for (int i = 0; i < EXPLOSION_Y_LENGTH; i++) {
                    BlockPos pos = blockPosition().below(i);
                    if (this.level().getBlockState(pos).isRedstoneConductor(this.level(), pos)) {
                        if (registry.getValue(this.level().getRandom().nextBoolean() ? CaveFeatures.MOSS_PATCH : CaveFeatures.CLAY_POOL_WITH_DRIPLEAVES).place(serverWorld, serverWorld.getChunkSource().getGenerator(), random, pos.above())) {
                            hasGeneratedMoss = true;
                        }
                        break;
                    }
                }
                for (int i = 0; i < EXPLOSION_Y_LENGTH; i++) {
                    BlockPos pos = blockPosition().above(i);
                    if (this.level().getBlockState(pos).isRedstoneConductor(this.level(), pos)) {
                        if (registry.getValue(CaveFeatures.MOSS_PATCH_CEILING).place(serverWorld, serverWorld.getChunkSource().getGenerator(), random, pos.below())) {
                            hasGeneratedMoss = true;
                        }
                        break;
                    }
                }
            }
            if (hasGeneratedMoss) {
                this.dead = true;
                float f = this.isPowered() ? 2.0F : 1.0F;
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), f, Level.ExplosionInteraction.MOB);
                this.discard();
                this.spawnLingeringCloud();
            } else {
                super.explodeCreeper();
            }
        }
    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> statusEffects = this.getActiveEffects();
        if (!statusEffects.isEmpty()) {
            AreaEffectCloud aec = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            aec.setRadius(2.5F);
            aec.setRadiusOnUse(-0.5F);
            aec.setWaitTime(10);
            aec.setDuration(aec.getDuration() / 2);
            aec.setRadiusPerTick(-aec.getRadius() / (float) aec.getDuration());

            for (MobEffectInstance statusEffectInstance : statusEffects) {
                aec.addEffect(new MobEffectInstance(statusEffectInstance));
            }
            this.level().addFreshEntity(aec);
        }
    }
}