package fr.hugman.promenade.entity;

import fr.hugman.promenade.component.PromenadeComponentTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.DuckVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DuckEntity extends AnimalEntity {
    private static final EntityDimensions BABY_BASE_DIMENSIONS = EntityDimensions.changing(0.4F, 0.8F).scaled(0.5F).withEyeHeight(0.78125F);

    private static final TrackedData<RegistryEntry<DuckVariant>> VARIANT = DataTracker.registerData(DuckEntity.class, PromenadeTrackedData.DUCK_VARIANT);

    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    public float wingRotDelta = 1.0F;

    public DuckEntity(EntityType<? extends DuckEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @org.jetbrains.annotations.Nullable EntityData entityData) {
        DuckVariants.select(this.random, this.getRegistryManager(), SpawnContext.of(world, this.getBlockPos())).ifPresent(this::setVariant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getBaseDimensions(pose);
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
        super.updatePassengerPosition(passenger, positionUpdater);
        if (passenger instanceof LivingEntity) {
            ((LivingEntity) passenger).bodyYaw = this.bodyYaw;
        }
    }

    /*========*/
    /*   AI   */
    /*========*/

    public static Builder createDuckAttributes() {
        return createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 4.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, (stack) -> stack.isIn(PromenadeItemTags.DUCK_FOOD), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(5, new SwimAroundGoal(this, 1.0, 10));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        boolean isAirBorne = !this.isOnGround() && !this.isTouchingWater();
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation = (float) ((double) this.maxWingDeviation + (double) (!isAirBorne ? -1 : 4) * 0.3D);
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0F, 1.0F);
        if (isAirBorne && this.wingRotDelta < 0.55F) {
            this.wingRotDelta = 0.55F;
        }
        this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);
        Vec3d vec3d = this.getVelocity();
        if (isAirBorne && vec3d.y < 0.0D) {
            this.setVelocity(vec3d.multiply(1.0D, 0.75D, 1.0D));
        }
        this.flapProgress += this.wingRotDelta * 2.0F;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(PromenadeItemTags.DUCK_FOOD);
    }

    @Override
    protected float getBaseWaterMovementSpeedMultiplier() {
        return 0.9f;
    }

    /*============*/
    /*   SOUNDS   */
    /*============*/

    @Override
    protected SoundEvent getAmbientSound() {
        return PromenadeSoundEvents.DUCK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return PromenadeSoundEvents.DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PromenadeSoundEvents.DUCK_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(PromenadeSoundEvents.DUCK_STEP, 0.15F, 1.0F);
    }

    /*==============*/
    /*   VARIANTS   */
    /*==============*/

    @Nullable
    @Override
    public DuckEntity createChild(ServerWorld serverWorld, PassiveEntity entity) {
        DuckEntity child = PromenadeEntityTypes.DUCK.create(this.getWorld(), SpawnReason.BREEDING);
        if (child != null && entity instanceof DuckEntity mama) {
            child.setVariant(this.random.nextFloat() < 0.5f ? mama.getVariant() : this.getVariant());
        }
        return child;
    }

    public RegistryEntry<DuckVariant> getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    public void setVariant(RegistryEntry<DuckVariant> registryEntry) {
        this.dataTracker.set(VARIANT, registryEntry);
    }

    /*==========*/
    /*   DATA   */
    /*==========*/

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, Variants.getOrDefaultOrThrow(this.getRegistryManager(), DuckVariants.DEFAULT));
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        Variants.writeVariantToNbt(view, this.getVariant());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        Variants.readVariantFromNbt(view, PromenadeRegistryKeys.DUCK_VARIANT).ifPresent(this::setVariant);
    }

    @Nullable
    @Override
    public <T> T get(ComponentType<? extends T> type) {
        return type == PromenadeComponentTypes.DUCK_VARIANT ? castComponentValue((ComponentType<T>) type, this.getVariant()) : super.get(type);
    }

    @Override
    protected void copyComponentsFrom(ComponentsAccess from) {
        this.copyComponentFrom(from, PromenadeComponentTypes.DUCK_VARIANT);
        super.copyComponentsFrom(from);
    }

    @Override
    protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
        if (type == PromenadeComponentTypes.DUCK_VARIANT) {
            this.setVariant(castComponentValue(PromenadeComponentTypes.DUCK_VARIANT, value));
            return true;
        } else {
            return super.setApplicableComponent(type, value);
        }
    }

}