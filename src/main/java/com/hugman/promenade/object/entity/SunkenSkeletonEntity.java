package com.hugman.promenade.object.entity;

import com.hugman.promenade.content.MonsterContent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.PufferfishEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class SunkenSkeletonEntity extends AbstractSkeletonEntity implements CrossbowUser {
	private static final TrackedData<Integer> SUNKEN_SKELETON_TYPE = DataTracker.registerData(SunkenSkeletonEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Boolean> CHARGING = DataTracker.registerData(SunkenSkeletonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Boolean> SWIMMING = DataTracker.registerData(SunkenSkeletonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private final static EntityDimensions SWIMMING_DIMENSIONS = EntityDimensions.fixed(0.6F, 0.6F);
	protected final SwimNavigation waterNavigation = new SwimNavigation(this, world);
	protected final MobNavigation landNavigation = new MobNavigation(this, world);
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
	boolean targetingUnderwater;

	public SunkenSkeletonEntity(EntityType<? extends SunkenSkeletonEntity> entityType, World world) {
		super(entityType, world);
		this.updateAttackType();
		this.moveControl = new SunkenMoveControl(this);
		this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
	}

	public static boolean canSpawn(EntityType<SunkenSkeletonEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		boolean bl = world.getDifficulty() != Difficulty.PEACEFUL
				&& isSpawnDark(world, pos, random)
				&& (spawnReason == SpawnReason.SPAWNER || world.getFluidState(pos).isIn(FluidTags.WATER));
		return pos.getY() < world.getSeaLevel() - 10 && bl;
	}

	@Override
	public boolean canSpawn(WorldView world) {
		return world.doesNotIntersectEntities(this);
	}

	public static DefaultAttributeContainer.Builder createSunkenSkeletonAttributes() {
		return createAbstractSkeletonAttributes();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? MonsterContent.SUNKEN_SKELETON_AMBIENT_SOUND : SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isSubmergedIn(FluidTags.WATER) ? MonsterContent.SUNKEN_SKELETON_HURT_SOUND : SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? MonsterContent.SUNKEN_SKELETON_DEATH_SOUND : SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Override
	protected SoundEvent getStepSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? MonsterContent.SUNKEN_SKELETON_STEP_SOUND : SoundEvents.ENTITY_SKELETON_STEP;
	}

	protected SoundEvent getShootSound() {
		return this.isSubmergedIn(FluidTags.WATER) ? MonsterContent.SUNKEN_SKELETON_SHOOT_SOUND : (this.isHolding(stack -> stack.getItem() instanceof CrossbowItem) ? SoundEvents.ITEM_CROSSBOW_SHOOT : SoundEvents.ENTITY_SKELETON_SHOOT);
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

	@Override
	public void shoot(LivingEntity entity, LivingEntity target, ProjectileEntity projectile, float multishotSpray, float speed) {
		double d = target.getX() - entity.getX();
		double e = target.getZ() - entity.getZ();
		double f = Math.sqrt(d * d + e * e);
		double g = target.getBodyY(0.3333333333333333D) - projectile.getY() + f * 0.20000000298023224D;
		Vec3f vec3f = this.getProjectileLaunchVelocity(entity, new Vec3d(d, g, e), multishotSpray);
		projectile.setVelocity(vec3f.getX(), vec3f.getY(), vec3f.getZ(), speed, (float) (14 - entity.world.getDifficulty().getId() * 4));
		entity.playSound(getShootSound(), 1.0F, 1.0F / (entity.getRandom().nextFloat() * 0.4F + 0.8F));
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
		this.dataTracker.startTracking(SWIMMING, false);
	}

	@Override
	public boolean isPushedByFluids() {
		return !this.isSwimming();
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new MoveIntoWaterGoal(this));
		this.goalSelector.add(1, new AvoidSunlightGoal(this));
		this.goalSelector.add(2, new CrossbowAttackGoal(this, 1.0D, 4.5F));
		this.goalSelector.add(3, new FleeEntityGoal(this, PufferfishEntity.class, 8.0F, 1.0D, 1.2D));
		this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
		this.goalSelector.add(6, new SwimAroundGoal(this, 1.0D, 40));
		this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(7, new SunkenSkeletonEntity.TargetAboveWaterGoal(this, 1.0D, this.world.getSeaLevel()));
		this.goalSelector.add(7, new SunkenSkeletonEntity.LeaveWaterGoal(this, 1.0D));
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));

		this.targetSelector.add(1, new RevengeGoal(this));
		this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
		this.targetSelector.add(2, new ActiveTargetGoal(this, DolphinEntity.class, true));
	}

	@Override
	protected void initEquipment(Random random, LocalDifficulty difficulty) {
		super.initEquipment(random, difficulty);
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
	}

	@Override
	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		return weapon instanceof BowItem || weapon instanceof CrossbowItem;
	}

	@Override
	public void travel(Vec3d movementInput) {
		if(this.canMoveVoluntarily() && this.isTouchingWater()) {
			float speed = 0.075F;
			this.updateVelocity(speed, movementInput);
			this.move(MovementType.SELF, this.getVelocity());
			this.setVelocity(this.getVelocity().multiply(0.9D));
		}
		else {
			super.travel(movementInput);
		}
	}

	@Override
	public void updateSwimming() {
		if(!this.world.isClient) {
			boolean b = this.world.isAir(this.getBlockPos().up(2));
			if(!b && this.canMoveVoluntarily() && this.isSubmergedInWater()) {
				this.navigation = this.waterNavigation;
				this.setSwimming(true);
				this.setBreaststrokeSwimming(true);
			}
			else {
				this.navigation = this.landNavigation;
				this.setSwimming(false);
				this.setBreaststrokeSwimming(false);
			}
		}
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

	public boolean isBreaststrokeSwimming() {
		if(this.getVelocity().length() < 0.1F) return false;
		else return this.dataTracker.get(SWIMMING);
	}

	public void setBreaststrokeSwimming(boolean breaststrokeSwimming) {
		this.dataTracker.set(SWIMMING, breaststrokeSwimming);
	}

	@Override
	public boolean isInSwimmingPose() {
		return isBreaststrokeSwimming();
	}

	@Override
	public void postShoot() {
		this.despawnCounter = 0;
	}

	public State getState() {
		if(this.isHolding(Items.CROSSBOW) && this.isCharging()) {
			return State.CROSSBOW_CHARGE;
		}
		else if(this.isHolding(CrossbowItem::isCharged)) {
			return State.CROSSBOW_HOLD;
		}
		else if(this.isBreaststrokeSwimming()) {
			return State.SWIMMING;
		}
		else if(this.isHolding(Items.BOW)) {
			return State.BOW_HOLD;
		}
		return State.NEUTRAL;
	}

	protected boolean hasFinishedCurrentPath() {
		Path path = this.getNavigation().getCurrentPath();
		if(path != null) {
			BlockPos blockPos = path.getTarget();
			if(blockPos != null) {
				double d = this.squaredDistanceTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
				return d < 4.0D;
			}
		}

		return false;
	}

	@Override
	protected Box calculateBoundingBox() {
		if(this.isBreaststrokeSwimming()) return SWIMMING_DIMENSIONS.getBoxAt(this.getPos());
		else return super.calculateBoundingBox();
	}

	boolean isTargetingUnderwater() {
		if(this.targetingUnderwater) {
			return true;
		}
		else {
			LivingEntity livingEntity = this.getTarget();
			return livingEntity != null && livingEntity.isTouchingWater();
		}
	}

	public void setTargetingUnderwater(boolean targetingUnderwater) {
		this.targetingUnderwater = targetingUnderwater;
	}

	public enum State {
		CROSSBOW_HOLD,
		CROSSBOW_CHARGE,
		BOW_HOLD,
		SWIMMING,
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

	static class SunkenMoveControl extends MoveControl {
		private final SunkenSkeletonEntity sunkenSkeleton;

		public SunkenMoveControl(SunkenSkeletonEntity sunkenSkeleton) {
			super(sunkenSkeleton);
			this.sunkenSkeleton = sunkenSkeleton;
		}

		public void tick() {
			LivingEntity target = this.sunkenSkeleton.getTarget();
			if(this.sunkenSkeleton.isTargetingUnderwater() || this.sunkenSkeleton.isSubmergedInWater()) {
				if(target != null) {
					if(target.getY() > this.sunkenSkeleton.getY()) this.sunkenSkeleton.setVelocity(this.sunkenSkeleton.getVelocity().add(0.0D, 0.002D, 0.0D));
					else this.sunkenSkeleton.setVelocity(this.sunkenSkeleton.getVelocity().add(0.0D, -0.002D, 0.0D));
				}

				if(this.state == State.WAIT || this.sunkenSkeleton.getNavigation().isIdle()) {
					this.sunkenSkeleton.setMovementSpeed(0.0F);
				}

				double d = this.targetX - this.sunkenSkeleton.getX();
				double e = this.targetY - this.sunkenSkeleton.getY();
				double f = this.targetZ - this.sunkenSkeleton.getZ();
				double g = Math.sqrt(d * d + e * e + f * f);
				e /= g;
				float h = (float) (MathHelper.atan2(f, d) * 57.2957763671875D) - 90.0F;
				this.sunkenSkeleton.setYaw(this.wrapDegrees(this.sunkenSkeleton.getYaw(), h, 90.0F));
				this.sunkenSkeleton.bodyYaw = this.sunkenSkeleton.getYaw();
				float i = (float) (this.speed * this.sunkenSkeleton.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
				float j = MathHelper.lerp(0.125F, this.sunkenSkeleton.getMovementSpeed(), i);
				this.sunkenSkeleton.setMovementSpeed(j);
				this.sunkenSkeleton.setVelocity(this.sunkenSkeleton.getVelocity().add((double) j * d * 0.005D, (double) j * e * 0.1D, (double) j * f * 0.005D));
			}
			else {
				if(!this.sunkenSkeleton.onGround) {
					this.sunkenSkeleton.setVelocity(this.sunkenSkeleton.getVelocity().add(0.0D, -0.008D, 0.0D));
				}
				super.tick();
			}
		}
	}

	static class LeaveWaterGoal extends MoveToTargetPosGoal {
		private final SunkenSkeletonEntity sunkenSkeleton;

		public LeaveWaterGoal(SunkenSkeletonEntity sunkenSkeleton, double speed) {
			super(sunkenSkeleton, speed, 8, 2);
			this.sunkenSkeleton = sunkenSkeleton;
		}

		public boolean canStart() {
			return super.canStart() && !this.sunkenSkeleton.world.isDay() && this.sunkenSkeleton.isTouchingWater() && this.sunkenSkeleton.getY() >= (double) (this.sunkenSkeleton.world.getSeaLevel() - 3);
		}

		public boolean shouldContinue() {
			return super.shouldContinue();
		}

		protected boolean isTargetPos(WorldView world, BlockPos pos) {
			BlockPos blockPos = pos.up();
			return world.isAir(blockPos) && world.isAir(blockPos.up()) && world.getBlockState(pos).hasSolidTopSurface(world, pos, this.sunkenSkeleton);
		}

		public void start() {
			this.sunkenSkeleton.setTargetingUnderwater(false);
			this.sunkenSkeleton.navigation = this.sunkenSkeleton.landNavigation;
			super.start();
		}

		public void stop() {
			super.stop();
		}
	}


	private static class TargetAboveWaterGoal extends Goal {
		private final SunkenSkeletonEntity sunkenSkeleton;
		private final double speed;
		private final int minY;
		private boolean foundTarget;

		public TargetAboveWaterGoal(SunkenSkeletonEntity sunkenSkeleton, double speed, int minY) {
			this.sunkenSkeleton = sunkenSkeleton;
			this.speed = speed;
			this.minY = minY;
		}

		public boolean canStart() {
			return !this.sunkenSkeleton.world.isDay() && this.sunkenSkeleton.isTouchingWater() && !this.sunkenSkeleton.isTargetingUnderwater() && this.sunkenSkeleton.getY() < (double) (this.minY - 2);
		}

		public boolean shouldContinue() {
			return this.canStart() && !this.foundTarget;
		}

		public void tick() {
			if(this.sunkenSkeleton.getY() < (double) (this.minY - 1) && (this.sunkenSkeleton.getNavigation().isIdle() || this.sunkenSkeleton.hasFinishedCurrentPath())) {
				Vec3d vec3d = NoPenaltyTargeting.findTo(this.sunkenSkeleton, 4, 8, new Vec3d(this.sunkenSkeleton.getX(), this.minY - 1, this.sunkenSkeleton.getZ()), 1.6D);
				if(vec3d == null) {
					this.foundTarget = true;
					return;
				}

				this.sunkenSkeleton.getNavigation().startMovingTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
			}

		}

		public void start() {
			this.sunkenSkeleton.setTargetingUnderwater(true);
			this.foundTarget = false;
		}

		public void stop() {
			this.sunkenSkeleton.setTargetingUnderwater(false);
		}
	}
}