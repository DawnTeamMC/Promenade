package fr.hugman.promenade.entity;

import fr.hugman.promenade.component.PromenadeComponentTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.DuckVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class DuckEntity extends Animal {
    private static final EntityDimensions BABY_BASE_DIMENSIONS = EntityDimensions.scalable(0.4F, 0.8F).scale(0.5F).withEyeHeight(0.78125F);

    private static final EntityDataAccessor<Holder<DuckVariant>> VARIANT = SynchedEntityData.defineId(DuckEntity.class, PromenadeTrackedData.DUCK_VARIANT);

    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    public float wingRotDelta = 1.0F;

    public DuckEntity(EntityType<? extends DuckEntity> type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @org.jetbrains.annotations.Nullable SpawnGroupData entityData) {
        DuckVariants.select(this.random, this.registryAccess(), SpawnContext.create(world, this.blockPosition())).ifPresent(this::setVariant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction positionUpdater) {
        super.positionRider(passenger, positionUpdater);
        if (passenger instanceof LivingEntity) {
            ((LivingEntity) passenger).yBodyRot = this.yBodyRot;
        }
    }

    /*========*/
    /*   AI   */
    /*========*/

    public static Builder createDuckAttributes() {
        return createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0, (stack) -> stack.is(PromenadeItemTags.DUCK_FOOD), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0, 10));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        boolean isAirBorne = !this.onGround() && !this.isInWater();
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation = (float) ((double) this.maxWingDeviation + (double) (!isAirBorne ? -1 : 4) * 0.3D);
        this.maxWingDeviation = Mth.clamp(this.maxWingDeviation, 0.0F, 1.0F);
        if (isAirBorne && this.wingRotDelta < 0.55F) {
            this.wingRotDelta = 0.55F;
        }
        this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);
        Vec3 vec3d = this.getDeltaMovement();
        if (isAirBorne && vec3d.y < 0.0D) {
            this.setDeltaMovement(vec3d.multiply(1.0D, 0.75D, 1.0D));
        }
        this.flapProgress += this.wingRotDelta * 2.0F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(PromenadeItemTags.DUCK_FOOD);
    }

    @Override
    protected float getWaterSlowDown() {
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
    public DuckEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob entity) {
        DuckEntity child = PromenadeEntityTypes.DUCK.create(this.level(), EntitySpawnReason.BREEDING);
        if (child != null && entity instanceof DuckEntity mama) {
            child.setVariant(this.random.nextFloat() < 0.5f ? mama.getVariant() : this.getVariant());
        }
        return child;
    }

    public Holder<DuckVariant> getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(Holder<DuckVariant> registryEntry) {
        this.entityData.set(VARIANT, registryEntry);
    }

    /*==========*/
    /*   DATA   */
    /*==========*/

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, VariantUtils.getDefaultOrAny(this.registryAccess(), DuckVariants.DEFAULT));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
		VariantUtils.writeVariant(view, this.getVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
		VariantUtils.readVariant(view, PromenadeRegistryKeys.DUCK_VARIANT).ifPresent(this::setVariant);
    }

    @Nullable
    @Override
    public <T> T get(DataComponentType<? extends T> type) {
        return type == PromenadeComponentTypes.DUCK_VARIANT ? castComponentValue((DataComponentType<T>) type, this.getVariant()) : super.get(type);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter from) {
        this.applyImplicitComponentIfPresent(from, PromenadeComponentTypes.DUCK_VARIANT);
        super.applyImplicitComponents(from);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        if (type == PromenadeComponentTypes.DUCK_VARIANT) {
            this.setVariant(castComponentValue(PromenadeComponentTypes.DUCK_VARIANT, value));
            return true;
        } else {
            return super.applyImplicitComponent(type, value);
        }
    }

}