package fr.hugman.promenade.entity;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;

import java.util.Collection;

public class LushCreeperEntity extends CreeperEntity {
    private static final int EXPLOSION_Y_LENGTH = 10;

    public LushCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean shouldRenderOverlay() {
        return false;
    }

    public static boolean canSpawn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return pos.getY() < 0 && HostileEntity.canSpawnInDark(type, world, spawnReason, pos, random);
    }

    @Override
    protected void explode() {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            boolean hasGeneratedMoss = false;
            if (this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                Registry<ConfiguredFeature<?, ?>> registry = serverWorld.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE);
                for (int i = 0; i < EXPLOSION_Y_LENGTH; i++) {
                    BlockPos pos = getBlockPos().down(i);
                    if (this.getWorld().getBlockState(pos).isSolidBlock(this.getWorld(), pos)) {
                        if (registry.get(this.getWorld().getRandom().nextBoolean() ? UndergroundConfiguredFeatures.MOSS_PATCH : UndergroundConfiguredFeatures.CLAY_POOL_WITH_DRIPLEAVES).generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), random, pos.up())) {
                            hasGeneratedMoss = true;
                        }
                        break;
                    }
                }
                for (int i = 0; i < EXPLOSION_Y_LENGTH; i++) {
                    BlockPos pos = getBlockPos().up(i);
                    if (this.getWorld().getBlockState(pos).isSolidBlock(this.getWorld(), pos)) {
                        if (registry.get(UndergroundConfiguredFeatures.MOSS_PATCH_CEILING).generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), random, pos.down())) {
                            hasGeneratedMoss = true;
                        }
                        break;
                    }
                }
            }
            if (hasGeneratedMoss) {
                this.dead = true;
                float f = this.shouldRenderOverlay() ? 2.0F : 1.0F;
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), f, World.ExplosionSourceType.MOB);
                this.discard();
                this.spawnEffectsCloud();
            } else {
                super.explode();
            }
        }
    }

    private void spawnEffectsCloud() {
        Collection<StatusEffectInstance> statusEffects = this.getStatusEffects();
        if (!statusEffects.isEmpty()) {
            AreaEffectCloudEntity aec = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
            aec.setRadius(2.5F);
            aec.setRadiusOnUse(-0.5F);
            aec.setWaitTime(10);
            aec.setDuration(aec.getDuration() / 2);
            aec.setRadiusGrowth(-aec.getRadius() / (float) aec.getDuration());

            for (StatusEffectInstance statusEffectInstance : statusEffects) {
                aec.addEffect(new StatusEffectInstance(statusEffectInstance));
            }
            this.getWorld().spawnEntity(aec);
        }
    }
}