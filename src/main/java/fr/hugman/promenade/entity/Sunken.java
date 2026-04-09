package fr.hugman.promenade.entity;

import fr.hugman.promenade.component.PromenadeComponentTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import fr.hugman.promenade.entity.variant.SunkenVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.dolphin.Dolphin;
import net.minecraft.world.entity.animal.fish.Pufferfish;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import javax.annotation.Nullable;
import java.util.Optional;

public class Sunken extends AbstractSkeleton implements CrossbowAttackMob {
    private static final EntityDataAccessor<Holder<SunkenVariant>> VARIANT = SynchedEntityData.defineId(Sunken.class, PromenadeTrackedData.SUNKEN_VARIANT);
    private static final EntityDataAccessor<Boolean> CHARGING = SynchedEntityData.defineId(Sunken.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SWIMMING = SynchedEntityData.defineId(Sunken.class, EntityDataSerializers.BOOLEAN);

    private final static EntityDimensions SWIMMING_DIMENSIONS = EntityDimensions.fixed(0.6F, 0.6F);

    protected final WaterBoundPathNavigation waterNavigation;
    protected final GroundPathNavigation landNavigation;
    private final RangedCrossbowAttackGoal<Sunken> crossbowAttackGoal = new RangedCrossbowAttackGoal<>(this, 1.0D, 20.0F);
    private final RangedBowAttackGoal<AbstractSkeleton> bowAttackGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            Sunken.this.setAggressive(false);
        }

        public void start() {
            super.start();
            Sunken.this.setAggressive(true);
        }
    };
    boolean targetingUnderwater;

    public Sunken(EntityType<? extends Sunken> entityType, Level world) {
        super(entityType, world);
        this.reassessWeaponGoal();
        this.moveControl = new SunkenMoveControl(this);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.waterNavigation = new WaterBoundPathNavigation(this, world);
        this.landNavigation = new GroundPathNavigation(this, world);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
        SunkenVariants.select(this.random, this.registryAccess(), SpawnContext.create(world, this.blockPosition())).ifPresent(this::setVariant);
        this.lootTable = Optional.ofNullable(this.getVariant().value().lootTable());
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    public static boolean canSpawn(EntityType<Sunken> ignoredType, ServerLevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        boolean bl = world.getDifficulty() != Difficulty.PEACEFUL
                && isDarkEnoughToSpawn(world, pos, random)
                && (spawnReason == EntitySpawnReason.SPAWNER || world.getFluidState(pos).is(FluidTags.WATER));
        return pos.getY() < world.getSeaLevel() - 10 && bl;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return world.isUnobstructed(this);
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        if (this.isBreaststrokeSwimming()) return SWIMMING_DIMENSIONS;
        else return super.getDefaultDimensions(pose);
    }

    /*========*/
    /*   AI   */
    /*========*/

    public static AttributeSupplier.Builder createSunkenAttributes() {
        return createAttributes();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new RestrictSunGoal(this));
        this.goalSelector.addGoal(2, new RangedCrossbowAttackGoal(this, 1.0D, 4.5F));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Pufferfish.class, 8.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new Sunken.TargetAboveWaterGoal(this, 1.0D, this.level().getSeaLevel()));
        this.goalSelector.addGoal(7, new Sunken.LeaveWaterGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Dolphin.class, true));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float pullProgress) {
        if (this.isHolding(stack -> stack.getItem() instanceof CrossbowItem)) {
            this.performCrossbowAttack(this, 1.6F);
        } else {
            ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW));
            AbstractArrow persistentProjectileEntity = this.getArrow(this.getProjectile(itemStack), pullProgress, itemStack);
            double d = target.getX() - this.getX();
            double e = target.getY(0.3333333333333333D) - persistentProjectileEntity.getY();
            double f = target.getZ() - this.getZ();
            double g = Math.sqrt(d * d + f * f);
            persistentProjectileEntity.shoot(d, e + g * 0.20000000298023224D, f, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
            this.playSound(this.getShootSound(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.level().addFreshEntity(persistentProjectileEntity);
        }
    }

    @Override
    public void performCrossbowAttack(LivingEntity entity, float speed) {
        CrossbowAttackMob.super.performCrossbowAttack(entity, speed);
    }

    @Override
    protected AbstractArrow getArrow(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        AbstractArrow persistentProjectileEntity = super.getArrow(arrow, damageModifier, shotFrom);
        if (persistentProjectileEntity instanceof Arrow) {
            ((Arrow) persistentProjectileEntity).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 600));
        }

        return persistentProjectileEntity;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(random, difficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
    }

	@Override
	public boolean canUseNonMeleeWeapon(ItemStack stack) {
		var weapon = stack.getItem();
		return weapon instanceof BowItem || weapon instanceof CrossbowItem;
	}

	@Override
    public void travel(Vec3 movementInput) {
        if (this.canSimulateMovement() && this.isInWater()) {
            float speed = 0.075F;
            this.moveRelative(speed, movementInput);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public boolean isPushedByFluid() {
        return !this.isSwimming();
    }

    @Override
    public void updateSwimming() {
        if (!this.level().isClientSide()) {
            boolean b = this.level().isEmptyBlock(this.blockPosition().above(2));
            if (!b && this.canSimulateMovement() && this.isUnderWater()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
                this.setBreaststrokeSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
                this.setBreaststrokeSwimming(false);
            }
        }
    }

    @Override
    public void reassessWeaponGoal() {
        if (this.level() != null && !this.level().isClientSide()) {
            if (this.bowAttackGoal != null && this.crossbowAttackGoal != null && this.meleeAttackGoal != null) {
                this.goalSelector.removeGoal(this.bowAttackGoal);
                this.goalSelector.removeGoal(this.crossbowAttackGoal);
                this.goalSelector.removeGoal(this.meleeAttackGoal);
                ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.CROSSBOW));
                if (itemStack.is(Items.CROSSBOW)) {
                    this.goalSelector.addGoal(4, this.crossbowAttackGoal);
                } else if (itemStack.is(Items.BOW)) {
                    this.bowAttackGoal.setMinAttackInterval(this.level().getDifficulty() != Difficulty.HARD ? 40 : 20);
                    this.goalSelector.addGoal(4, this.bowAttackGoal);
                } else {
                    this.goalSelector.addGoal(4, this.meleeAttackGoal);
                }
            }
        }
    }

    public boolean isCharging() {
        return this.entityData.get(CHARGING);
    }

    @Override
    public void setChargingCrossbow(boolean charging) {
        this.entityData.set(CHARGING, charging);
    }

    public boolean isBreaststrokeSwimming() {
        if (this.getDeltaMovement().length() < 0.1F) return false;
        else return this.entityData.get(SWIMMING);
    }

    public void setBreaststrokeSwimming(boolean breaststrokeSwimming) {
        this.entityData.set(SWIMMING, breaststrokeSwimming);
    }

    @Override
    public boolean isVisuallySwimming() {
        return isBreaststrokeSwimming();
    }

    @Override
    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    protected boolean hasFinishedCurrentPath() {
        Path path = this.getNavigation().getPath();
        if (path != null) {
            BlockPos blockPos = path.getTarget();
            if (blockPos != null) {
                double d = this.distanceToSqr(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                return d < 4.0D;
            }
        }

        return false;
    }

    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isInWater();
        }
    }

    public void setTargetingUnderwater(boolean targetingUnderwater) {
        this.targetingUnderwater = targetingUnderwater;
    }

    /*============*/
    /*   SOUNDS   */
    /*============*/

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isEyeInFluid(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_AMBIENT : SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return this.isEyeInFluid(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_HURT : SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return this.isEyeInFluid(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_DEATH : SoundEvents.SKELETON_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return this.isEyeInFluid(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_STEP : SoundEvents.SKELETON_STEP;
    }

    protected SoundEvent getShootSound() {
        return this.isEyeInFluid(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_SHOOT : (this.isHolding(stack -> stack.getItem() instanceof CrossbowItem) ? SoundEvents.CROSSBOW_SHOOT : SoundEvents.SKELETON_SHOOT);
    }

    /*==============*/
    /*   VARIANTS   */
    /*==============*/

    public Holder<SunkenVariant> getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(Holder<SunkenVariant> variant) {
        this.entityData.set(VARIANT, variant);
    }

    /*============*/
    /*   STATES   */
    /*============*/

    public State getState() {
        if (this.isHolding(Items.CROSSBOW) && this.isCharging()) {
            return State.CROSSBOW_CHARGE;
        } else if (this.isHolding(CrossbowItem::isCharged)) {
            return State.CROSSBOW_HOLD;
        } else if (this.isBreaststrokeSwimming()) {
            return State.SWIMMING;
        } else if (this.isHolding(Items.BOW)) {
            return State.BOW_HOLD;
        }
        return State.NEUTRAL;
    }

    public enum State {
        CROSSBOW_HOLD,
        CROSSBOW_CHARGE,
        BOW_HOLD,
        SWIMMING,
        NEUTRAL
    }

    /*==========*/
    /*   DATA   */
    /*==========*/

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, this.registryAccess().lookupOrThrow(PromenadeRegistryKeys.SUNKEN_VARIANT).getOrThrow(SunkenVariants.DEFAULT));
        builder.define(CHARGING, false);
        builder.define(SWIMMING, false);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
		VariantUtils.writeVariant(view, this.getVariant());

    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
		VariantUtils.readVariant(view, PromenadeRegistryKeys.SUNKEN_VARIANT).ifPresent(this::setVariant);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T> T get(DataComponentType<? extends T> type) {
        return type == PromenadeComponentTypes.SUNKEN_VARIANT ? castComponentValue((DataComponentType<T>) type, this.getVariant()) : super.get(type);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter from) {
        this.applyImplicitComponentIfPresent(from, PromenadeComponentTypes.SUNKEN_VARIANT);
        super.applyImplicitComponents(from);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        if (type == PromenadeComponentTypes.SUNKEN_VARIANT) {
            this.setVariant(castComponentValue(PromenadeComponentTypes.SUNKEN_VARIANT, value));
            return true;
        } else {
            return super.applyImplicitComponent(type, value);
        }
    }

    static class SunkenMoveControl extends MoveControl {
        private final Sunken sunken;

        public SunkenMoveControl(Sunken sunken) {
            super(sunken);
            this.sunken = sunken;
        }

        public void tick() {
            LivingEntity target = this.sunken.getTarget();
            if (this.sunken.isTargetingUnderwater() || this.sunken.isUnderWater()) {
                if (target != null) {
                    if (target.getY() > this.sunken.getY())
                        this.sunken.setDeltaMovement(this.sunken.getDeltaMovement().add(0.0D, 0.002D, 0.0D));
                    else this.sunken.setDeltaMovement(this.sunken.getDeltaMovement().add(0.0D, -0.002D, 0.0D));
                }

                if (this.operation == Operation.WAIT || this.sunken.getNavigation().isDone()) {
                    this.sunken.setSpeed(0.0F);
                }

                double d = this.wantedX - this.sunken.getX();
                double e = this.wantedY - this.sunken.getY();
                double f = this.wantedZ - this.sunken.getZ();
                double g = Math.sqrt(d * d + e * e + f * f);
                e /= g;
                float h = (float) (Mth.atan2(f, d) * 57.2957763671875D) - 90.0F;
                this.sunken.setYRot(this.rotlerp(this.sunken.getYRot(), h, 90.0F));
                this.sunken.yBodyRot = this.sunken.getYRot();
                float i = (float) (this.speedModifier * this.sunken.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float j = Mth.lerp(0.125F, this.sunken.getSpeed(), i);
                this.sunken.setSpeed(j);
                this.sunken.setDeltaMovement(this.sunken.getDeltaMovement().add((double) j * d * 0.005D, (double) j * e * 0.1D, (double) j * f * 0.005D));
            } else {
                if (!this.sunken.onGround()) {
                    this.sunken.setDeltaMovement(this.sunken.getDeltaMovement().add(0.0D, -0.008D, 0.0D));
                }
                super.tick();
            }
        }
    }

    static class LeaveWaterGoal extends MoveToBlockGoal {
        private final Sunken sunken;

        public LeaveWaterGoal(Sunken sunken, double speed) {
            super(sunken, speed, 8, 2);
            this.sunken = sunken;
        }

        public boolean canUse() {
            return super.canUse() && !this.sunken.level().isBrightOutside() && this.sunken.isInWater() && this.sunken.getY() >= (double) (this.sunken.level().getSeaLevel() - 3);
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }

        protected boolean isValidTarget(LevelReader world, BlockPos pos) {
            BlockPos blockPos = pos.above();
            return world.isEmptyBlock(blockPos) && world.isEmptyBlock(blockPos.above()) && world.getBlockState(pos).entityCanStandOn(world, pos, this.sunken);
        }

        public void start() {
            this.sunken.setTargetingUnderwater(false);
            this.sunken.navigation = this.sunken.landNavigation;
            super.start();
        }

        public void stop() {
            super.stop();
        }
    }

    private static class TargetAboveWaterGoal extends Goal {
        private final Sunken sunken;
        private final double speed;
        private final int minY;
        private boolean foundTarget;

        public TargetAboveWaterGoal(Sunken sunken, double speed, int minY) {
            this.sunken = sunken;
            this.speed = speed;
            this.minY = minY;
        }

        public boolean canUse() {
            return !this.sunken.level().isBrightOutside() && this.sunken.isInWater() && !this.sunken.isTargetingUnderwater() && this.sunken.getY() < (double) (this.minY - 2);
        }

        public boolean canContinueToUse() {
            return this.canUse() && !this.foundTarget;
        }

        public void tick() {
            if (this.sunken.getY() < (double) (this.minY - 1) && (this.sunken.getNavigation().isDone() || this.sunken.hasFinishedCurrentPath())) {
                Vec3 vec3d = DefaultRandomPos.getPosTowards(this.sunken, 4, 8, new Vec3(this.sunken.getX(), this.minY - 1, this.sunken.getZ()), 1.6D);
                if (vec3d == null) {
                    this.foundTarget = true;
                    return;
                }

                this.sunken.getNavigation().moveTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
            }

        }

        public void start() {
            this.sunken.setTargetingUnderwater(true);
            this.foundTarget = false;
        }

        public void stop() {
            this.sunken.setTargetingUnderwater(false);
        }
    }
}