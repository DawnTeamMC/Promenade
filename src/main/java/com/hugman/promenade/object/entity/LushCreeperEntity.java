package com.hugman.promenade.object.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.UndergroundConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;

import java.util.Random;

public class LushCreeperEntity extends CreeperEntity {
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
		if (world instanceof ServerWorld serverWorld) {
			boolean hasGeneratedMoss = false;
			if(this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
				for(int i = 0; i < 10; i++) {
					BlockPos pos = getBlockPos().down(i);
					if(this.world.getBlockState(pos).getMaterial().isSolid()) {
						if((this.world.getRandom().nextBoolean() ? UndergroundConfiguredFeatures.MOSS_PATCH : UndergroundConfiguredFeatures.CLAY_POOL_WITH_DRIPLEAVES).generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), random, pos.up())) {
							hasGeneratedMoss = true;
						}
						break;
					}
				}
				for(int i = 0; i < 10; i++) {
					BlockPos pos = getBlockPos().up(i);
					if(this.world.getBlockState(pos).getMaterial().isSolid()) {
						if(UndergroundConfiguredFeatures.MOSS_PATCH_CEILING.generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), random, pos.down())) {
							hasGeneratedMoss = true;
						}
						break;
					}
				}
			}
			if(hasGeneratedMoss) {
				this.dead = true;
				this.discard();
				this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 3.0f, Explosion.DestructionType.NONE);
			}
			else {
				super.explode();
			}
		}
	}
}