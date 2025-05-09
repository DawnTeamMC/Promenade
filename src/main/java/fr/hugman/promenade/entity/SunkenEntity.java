package fr.hugman.promenade.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.variant.SunkenVariant;
import fr.hugman.promenade.entity.variant.SunkenVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
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
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;

import javax.annotation.Nullable;
import java.util.Optional;

public class SunkenEntity extends AbstractSkeletonEntity implements CrossbowUser, VariantHolder<RegistryEntry<SunkenVariant>> {
    private static final TrackedData<RegistryEntry<SunkenVariant>> VARIANT = DataTracker.registerData(SunkenEntity.class, PromenadeTrackedData.SUNKEN_VARIANT);
    private static final TrackedData<Boolean> CHARGING = DataTracker.registerData(SunkenEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> SWIMMING = DataTracker.registerData(SunkenEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public static final String VARIANT_KEY = "variant";
    public static final MapCodec<RegistryEntry<SunkenVariant>> VARIANT_MAP_CODEC = SunkenVariant.ENTRY_CODEC.fieldOf(VARIANT_KEY);
    public static final Codec<RegistryEntry<SunkenVariant>> VARIANT_ENTRY_CODEC = VARIANT_MAP_CODEC.codec();

    private final static EntityDimensions SWIMMING_DIMENSIONS = EntityDimensions.fixed(0.6F, 0.6F);

    protected final SwimNavigation waterNavigation;
    protected final MobNavigation landNavigation;
    private final CrossbowAttackGoal<SunkenEntity> crossbowAttackGoal = new CrossbowAttackGoal<>(this, 1.0D, 20.0F);
    private final BowAttackGoal<AbstractSkeletonEntity> bowAttackGoal = new BowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            SunkenEntity.this.setAttacking(false);
        }

        public void start() {
            super.start();
            SunkenEntity.this.setAttacking(true);
        }
    };
    boolean targetingUnderwater;

    public SunkenEntity(EntityType<? extends SunkenEntity> entityType, World world) {
        super(entityType, world);
        this.updateAttackType();
        this.moveControl = new SunkenMoveControl(this);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);
    }

    public static boolean canSpawn(EntityType<SunkenEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        boolean bl = world.getDifficulty() != Difficulty.PEACEFUL
                && isSpawnDark(world, pos, random)
                && (spawnReason == SpawnReason.SPAWNER || world.getFluidState(pos).isIn(FluidTags.WATER));
        return pos.getY() < world.getSeaLevel() - 10 && bl;
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return world.doesNotIntersectEntities(this);
    }

    public static DefaultAttributeContainer.Builder createSunkenAttributes() {
        return createAbstractSkeletonAttributes();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isSubmergedIn(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_AMBIENT : SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return this.isSubmergedIn(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_HURT : SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return this.isSubmergedIn(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_DEATH : SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return this.isSubmergedIn(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_STEP : SoundEvents.ENTITY_SKELETON_STEP;
    }

    protected SoundEvent getShootSound() {
        return this.isSubmergedIn(FluidTags.WATER) ? PromenadeSoundEvents.SUNKEN_SHOOT : (this.isHolding(stack -> stack.getItem() instanceof CrossbowItem) ? SoundEvents.ITEM_CROSSBOW_SHOOT : SoundEvents.ENTITY_SKELETON_SHOOT);
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        if (this.isHolding(stack -> stack.getItem() instanceof CrossbowItem)) {
            this.shoot(this, 1.6F);
        } else {
            ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
            PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(this.getProjectileType(itemStack), pullProgress, itemStack);
            double d = target.getX() - this.getX();
            double e = target.getBodyY(0.3333333333333333D) - persistentProjectileEntity.getY();
            double f = target.getZ() - this.getZ();
            double g = Math.sqrt(d * d + f * f);
            persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224D, f, 1.6F, (float) (14 - this.getWorld().getDifficulty().getId() * 4));
            this.playSound(this.getShootSound(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.getWorld().spawnEntity(persistentProjectileEntity);
        }
    }

    @Override
    public void shoot(LivingEntity entity, float speed) {
        CrossbowUser.super.shoot(entity, speed);
    }

    @Override
    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier, shotFrom);
        if (persistentProjectileEntity instanceof ArrowEntity) {
            ((ArrowEntity) persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600));
        }

        return persistentProjectileEntity;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.setVariant(SunkenVariants.getRandom(this.getRegistryManager(), random));
        this.lootTable = Optional.ofNullable(this.getVariant().value().lootTable());
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, this.getRegistryManager().getOrThrow(PromenadeRegistryKeys.SUNKEN_VARIANT).getOrThrow(SunkenVariants.DEFAULT));
        builder.add(CHARGING, false);
        builder.add(SWIMMING, false);
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
        this.goalSelector.add(7, new SunkenEntity.TargetAboveWaterGoal(this, 1.0D, this.getWorld().getSeaLevel()));
        this.goalSelector.add(7, new SunkenEntity.LeaveWaterGoal(this, 1.0D));
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
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            float speed = 0.075F;
            this.updateVelocity(speed, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9D));
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public void updateSwimming() {
        if (!this.getWorld().isClient) {
            boolean b = this.getWorld().isAir(this.getBlockPos().up(2));
            if (!b && this.canMoveVoluntarily() && this.isSubmergedInWater()) {
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
    public void updateAttackType() {
        if (this.getWorld() != null && !this.getWorld().isClient) {
            if (this.bowAttackGoal != null && this.crossbowAttackGoal != null && this.meleeAttackGoal != null) {
                this.goalSelector.remove(this.bowAttackGoal);
                this.goalSelector.remove(this.crossbowAttackGoal);
                this.goalSelector.remove(this.meleeAttackGoal);
                ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.CROSSBOW));
                if (itemStack.isOf(Items.CROSSBOW)) {
                    this.goalSelector.add(4, this.crossbowAttackGoal);
                } else if (itemStack.isOf(Items.BOW)) {
                    this.bowAttackGoal.setAttackInterval(this.getWorld().getDifficulty() != Difficulty.HARD ? 40 : 20);
                    this.goalSelector.add(4, this.bowAttackGoal);
                } else {
                    this.goalSelector.add(4, this.meleeAttackGoal);
                }
            }
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString(VARIANT_KEY, (this.getVariant().getKey().orElse(SunkenVariants.BUBBLE)).getValue().toString());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        VARIANT_ENTRY_CODEC.parse(this.getRegistryManager().getOps(NbtOps.INSTANCE), nbt).ifSuccess(this::setVariant);
    }

    @Override
    public RegistryEntry<SunkenVariant> getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    @Override
    public void setVariant(RegistryEntry<SunkenVariant> variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    public boolean isCharging() {
        return this.dataTracker.get(CHARGING);
    }

    @Override
    public void setCharging(boolean charging) {
        this.dataTracker.set(CHARGING, charging);
    }

    public boolean isBreaststrokeSwimming() {
        if (this.getVelocity().length() < 0.1F) return false;
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

    protected boolean hasFinishedCurrentPath() {
        Path path = this.getNavigation().getCurrentPath();
        if (path != null) {
            BlockPos blockPos = path.getTarget();
            if (blockPos != null) {
                double d = this.squaredDistanceTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                return d < 4.0D;
            }
        }

        return false;
    }

    @Override
    public EntityDimensions getBaseDimensions(EntityPose pose) {
        if (this.isBreaststrokeSwimming()) return SWIMMING_DIMENSIONS;
        else return super.getBaseDimensions(pose);
    }

    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }

    public void setTargetingUnderwater(boolean targetingUnderwater) {
        this.targetingUnderwater = targetingUnderwater;
    }

    public Identifier getTexture() {
        return this.getVariant().value().texture();
    }

    public enum State {
        CROSSBOW_HOLD,
        CROSSBOW_CHARGE,
        BOW_HOLD,
        SWIMMING,
        NEUTRAL
    }

    static class SunkenMoveControl extends MoveControl {
        private final SunkenEntity sunken;

        public SunkenMoveControl(SunkenEntity sunken) {
            super(sunken);
            this.sunken = sunken;
        }

        public void tick() {
            LivingEntity target = this.sunken.getTarget();
            if (this.sunken.isTargetingUnderwater() || this.sunken.isSubmergedInWater()) {
                if (target != null) {
                    if (target.getY() > this.sunken.getY())
                        this.sunken.setVelocity(this.sunken.getVelocity().add(0.0D, 0.002D, 0.0D));
                    else this.sunken.setVelocity(this.sunken.getVelocity().add(0.0D, -0.002D, 0.0D));
                }

                if (this.state == State.WAIT || this.sunken.getNavigation().isIdle()) {
                    this.sunken.setMovementSpeed(0.0F);
                }

                double d = this.targetX - this.sunken.getX();
                double e = this.targetY - this.sunken.getY();
                double f = this.targetZ - this.sunken.getZ();
                double g = Math.sqrt(d * d + e * e + f * f);
                e /= g;
                float h = (float) (MathHelper.atan2(f, d) * 57.2957763671875D) - 90.0F;
                this.sunken.setYaw(this.wrapDegrees(this.sunken.getYaw(), h, 90.0F));
                this.sunken.bodyYaw = this.sunken.getYaw();
                float i = (float) (this.speed * this.sunken.getAttributeValue(EntityAttributes.MOVEMENT_SPEED));
                float j = MathHelper.lerp(0.125F, this.sunken.getMovementSpeed(), i);
                this.sunken.setMovementSpeed(j);
                this.sunken.setVelocity(this.sunken.getVelocity().add((double) j * d * 0.005D, (double) j * e * 0.1D, (double) j * f * 0.005D));
            } else {
                if (!this.sunken.isOnGround()) {
                    this.sunken.setVelocity(this.sunken.getVelocity().add(0.0D, -0.008D, 0.0D));
                }
                super.tick();
            }
        }
    }

    static class LeaveWaterGoal extends MoveToTargetPosGoal {
        private final SunkenEntity sunken;

        public LeaveWaterGoal(SunkenEntity sunken, double speed) {
            super(sunken, speed, 8, 2);
            this.sunken = sunken;
        }

        public boolean canStart() {
            return super.canStart() && !this.sunken.getWorld().isDay() && this.sunken.isTouchingWater() && this.sunken.getY() >= (double) (this.sunken.getWorld().getSeaLevel() - 3);
        }

        public boolean shouldContinue() {
            return super.shouldContinue();
        }

        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            BlockPos blockPos = pos.up();
            return world.isAir(blockPos) && world.isAir(blockPos.up()) && world.getBlockState(pos).hasSolidTopSurface(world, pos, this.sunken);
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
        private final SunkenEntity sunken;
        private final double speed;
        private final int minY;
        private boolean foundTarget;

        public TargetAboveWaterGoal(SunkenEntity sunken, double speed, int minY) {
            this.sunken = sunken;
            this.speed = speed;
            this.minY = minY;
        }

        public boolean canStart() {
            return !this.sunken.getWorld().isDay() && this.sunken.isTouchingWater() && !this.sunken.isTargetingUnderwater() && this.sunken.getY() < (double) (this.minY - 2);
        }

        public boolean shouldContinue() {
            return this.canStart() && !this.foundTarget;
        }

        public void tick() {
            if (this.sunken.getY() < (double) (this.minY - 1) && (this.sunken.getNavigation().isIdle() || this.sunken.hasFinishedCurrentPath())) {
                Vec3d vec3d = NoPenaltyTargeting.findTo(this.sunken, 4, 8, new Vec3d(this.sunken.getX(), this.minY - 1, this.sunken.getZ()), 1.6D);
                if (vec3d == null) {
                    this.foundTarget = true;
                    return;
                }

                this.sunken.getNavigation().startMovingTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
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