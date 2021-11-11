package com.hugman.promenade.object.entity;

import com.hugman.promenade.init.PromenadeEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.CrossbowAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * TODO: add underwater walk
 */
public class SunkenSkeletonEntity extends AbstractSkeletonEntity implements CrossbowUser {
	private static final TrackedData<Integer> SUNKEN_SKELETON_TYPE = DataTracker.registerData(DuckEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private final CrossbowAttackGoal<SunkenSkeletonEntity> bowAttackGoal = new CrossbowAttackGoal(this, 1.0D, 20, 15.0F);

	public SunkenSkeletonEntity(EntityType<? extends SunkenSkeletonEntity> entityType, World world) {
		super(entityType, world);
	}

	public static boolean canSpawn(EntityType<SunkenSkeletonEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		BlockPos blockPos = pos;

		do {
			blockPos = blockPos.up();
		} while(world.getBlockState(blockPos).isOf(Blocks.WATER));

		return canSpawnInDark(type, world, spawnReason, pos, random) && (spawnReason == SpawnReason.SPAWNER || world.isSkyVisible(blockPos.down()));
	}

	public static DefaultAttributeContainer.Builder createSunkenSkeletonAttributes() {
		return createAbstractSkeletonAttributes();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? PromenadeEntities.SUNKEN_SKELETON_AMBIENT_SOUND.getSound() : SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isSubmergedIn(FluidTags.WATER) ? PromenadeEntities.SUNKEN_SKELETON_HURT_SOUND.getSound() : SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? PromenadeEntities.SUNKEN_SKELETON_DEATH_SOUND.getSound() : SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Override
	protected SoundEvent getStepSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? PromenadeEntities.SUNKEN_SKELETON_STEP_SOUND.getSound() : SoundEvents.ENTITY_SKELETON_STEP;
	}

	protected SoundEvent getShootSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? PromenadeEntities.SUNKEN_SKELETON_SHOOT_SOUND.getSound() : SoundEvents.ENTITY_SKELETON_SHOOT;
	}

	/**
	 * TODO: add crossbow behavior see {@link net.minecraft.entity.mob.PillagerEntity}
	 */
	@Override
	public void attack(LivingEntity target, float pullProgress) {
		ItemStack itemStack = this.getArrowType(this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW)));
		PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack, pullProgress);
		double d = target.getX() - this.getX();
		double e = target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY();
		double f = target.getZ() - this.getZ();
		double g = Math.sqrt(d * d + f * f);
		persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224D, f, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
		this.playSound(this.getShootSound(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(persistentProjectileEntity);
	}

	protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
		PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier);
		if(persistentProjectileEntity instanceof ArrowEntity) {
			((ArrowEntity) persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600));
		}

		return persistentProjectileEntity;
	}

	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
		this.setVariant(Type.fromId(world.getRandom().nextInt(Type.values().length)));
		return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(SUNKEN_SKELETON_TYPE, 0);
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound compound) {
		super.writeCustomDataToNbt(compound);
		compound.putString("Type", this.getVariant().getName());
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound compound) {
		super.readCustomDataFromNbt(compound);
		this.setVariant(SunkenSkeletonEntity.Type.byName(compound.getString("Type")));
	}

	public SunkenSkeletonEntity.Type getVariant() {
		return SunkenSkeletonEntity.Type.fromId(this.dataTracker.get(SUNKEN_SKELETON_TYPE));
	}

	private void setVariant(SunkenSkeletonEntity.Type type) {
		this.dataTracker.set(SUNKEN_SKELETON_TYPE, type.getIndex());
	}

	@Override
	public void setCharging(boolean charging) {

	}

	@Override
	public void shoot(LivingEntity target, ItemStack crossbow, ProjectileEntity projectile, float multiShotSpray) {

	}

	@Override
	public void postShoot() {

	}

	public enum Type {
		BUBBLE(0, "bubble"),
		FIRE(1, "fire"),
		HORN(2, "horn");

		private static final SunkenSkeletonEntity.Type[] typeList = Arrays.stream(values()).sorted(Comparator.comparing(SunkenSkeletonEntity.Type::getIndex)).toArray(SunkenSkeletonEntity.Type[]::new);
		private static final Map<String, SunkenSkeletonEntity.Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(SunkenSkeletonEntity.Type::getName, (type) -> type));
		private final int index;
		private final String name;

		Type(int indexIn, String nameIn) {
			this.index = indexIn;
			this.name = nameIn;
		}

		public static SunkenSkeletonEntity.Type byName(String name) {
			return TYPES_BY_NAME.getOrDefault(name, BUBBLE);
		}

		public static SunkenSkeletonEntity.Type fromId(int index) {
			if(index < 0 || index > typeList.length) {
				index = 0;
			}
			return typeList[index];
		}

		public String getName() {
			return this.name;
		}

		public int getIndex() {
			return this.index;
		}
	}
}