package com.hugman.promenade.object.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.Random;

public class SunkenSkeletonEntity extends AbstractSkeletonEntity {
	public SunkenSkeletonEntity(EntityType<? extends SunkenSkeletonEntity> entityType, World world) {
		super(entityType, world);
	}

	public static boolean canSpawn(EntityType<SunkenSkeletonEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		BlockPos blockPos = pos;
		do {
			blockPos = blockPos.up();
		} while(world.getBlockState(blockPos).isOf(Blocks.POWDER_SNOW));

		return canSpawnInDark(type, world, spawnReason, pos, random) && (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(blockPos.down()));
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}
	
	SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SKELETON_STEP;
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
		PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier);
		if (persistentProjectileEntity instanceof ArrowEntity) {
			((ArrowEntity)persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600));
		}

		return persistentProjectileEntity;
	}
}