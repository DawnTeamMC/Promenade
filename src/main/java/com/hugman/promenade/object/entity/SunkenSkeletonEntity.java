package com.hugman.promenade.object.entity;

import com.hugman.promenade.init.PromenadeEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.ai.goal.CrossbowAttackGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
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
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class SunkenSkeletonEntity extends AbstractSkeletonEntity implements CrossbowUser {
	private static final TrackedData<Integer> SUNKEN_SKELETON_TYPE = DataTracker.registerData(SunkenSkeletonEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Boolean> CHARGING = DataTracker.registerData(SunkenSkeletonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private final CrossbowAttackGoal<SunkenSkeletonEntity> crossbowAttackGoal = new CrossbowAttackGoal<>(this, 1.0D, 20.0F);
	private final BowAttackGoal<AbstractSkeletonEntity> bowAttackGoal = new BowAttackGoal<>(this, 1.0D, 20, 15.0F);
	private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2D, false) {
		public void stop() {
			super.stop();
			SunkenSkeletonEntity.this.setAttacking(false);
		}

		public void start() {
			super.start();
			SunkenSkeletonEntity.this.setAttacking(true);
		}
	};

	public SunkenSkeletonEntity(EntityType<? extends SunkenSkeletonEntity> entityType, World world) {
		super(entityType, world);
		this.updateAttackType();
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
		return this.isSubmergedIn(FluidTags.WATER) ? PromenadeEntities.SUNKEN_SKELETON_SHOOT_SOUND.getSound() : (this.isHolding(stack -> stack.getItem() instanceof CrossbowItem) ? SoundEvents.ITEM_CROSSBOW_SHOOT : SoundEvents.ENTITY_SKELETON_SHOOT);
	}

	@Override
	public void attack(LivingEntity target, float pullProgress) {
		if(this.isHolding(stack -> stack.getItem() instanceof CrossbowItem)) {
			this.shoot(this, 1.6F);
		}
		else {
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
	}

	@Override
	public void shoot(LivingEntity target, ItemStack crossbow, ProjectileEntity projectile, float multiShotSpray) {
		this.shoot(this, target, projectile, multiShotSpray, 1.6F);
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
		this.dataTracker.startTracking(CHARGING, false);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(3, new CrossbowAttackGoal(this, 1.5D, 4.5F));
	}

	@Override
	protected void initEquipment(LocalDifficulty difficulty) {
		super.initEquipment(difficulty);
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
	}

	@Override
	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		return weapon instanceof BowItem || weapon instanceof CrossbowItem;
	}

	@Override
	public void updateAttackType() {
		if(this.world != null && !this.world.isClient) {
			if(this.bowAttackGoal != null && this.crossbowAttackGoal != null && this.meleeAttackGoal != null) {
				this.goalSelector.remove(this.bowAttackGoal);
				this.goalSelector.remove(this.crossbowAttackGoal);
				this.goalSelector.remove(this.meleeAttackGoal);
				ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.CROSSBOW));
				if(itemStack.isOf(Items.CROSSBOW)) {
					this.goalSelector.add(4, this.crossbowAttackGoal);
				}
				else if(itemStack.isOf(Items.BOW)) {
					this.bowAttackGoal.setAttackInterval(this.world.getDifficulty() != Difficulty.HARD ? 40 : 20);
					this.goalSelector.add(4, this.bowAttackGoal);
				}
				else {
					this.goalSelector.add(4, this.meleeAttackGoal);
				}
			}
		}
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

	public boolean isCharging() {
		return this.dataTracker.get(CHARGING);
	}

	@Override
	public void setCharging(boolean charging) {
		this.dataTracker.set(CHARGING, charging);
	}

	@Override
	public void postShoot() {
		this.despawnCounter = 0;
	}

	public State getState() {
		if(this.isHolding(Items.CROSSBOW)) {
			return this.isCharging() ? State.CROSSBOW_CHARGE : State.CROSSBOW_HOLD;
		}
		else if(this.isHolding(Items.BOW)) {
			return State.BOW;
		}
		else {
			return State.NEUTRAL;
		}
	}

	public enum State {
		CROSSBOW_HOLD,
		CROSSBOW_CHARGE,
		BOW,
		NEUTRAL
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