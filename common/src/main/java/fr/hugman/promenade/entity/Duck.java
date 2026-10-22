package fr.hugman.promenade.entity;

import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.DuckVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.entity.MobSpawnType;
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
import net.minecraft.world.item.crafting.Ingredient;
import fr.hugman.promenade.entity.spawn.SpawnContext;
import fr.hugman.promenade.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class Duck extends Animal {
    private static final EntityDimensions BABY_DIMENSIONS = EntityDimensions.scalable(4 / 16f, 5 / 16f);
    private static final float EYE_HEIGHT = 9.75f / 16f;
    private static final float BABY_EYE_HEIGHT = 3.5f / 16f;

    private static final EntityDataAccessor<ResourceKey<DuckVariant>> VARIANT = SynchedEntityData.defineId(Duck.class, PromenadeTrackedData.DUCK_VARIANT);

    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    public float wingRotDelta = 1.0F;

    public Duck(EntityType<? extends Duck> type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData, @Nullable CompoundTag entityNbt) {
        DuckVariants.select(this.random, this.level().registryAccess(), SpawnContext.create(world, this.blockPosition())).ifPresent(this::setVariant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.isBaby() ? BABY_DIMENSIONS : super.getDimensions(pose);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return this.isBaby() ? BABY_EYE_HEIGHT : EYE_HEIGHT;
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
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0, Ingredient.of(PromenadeItemTags.DUCK_FOOD), false));
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
        return 0.98f;
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
    public Duck getBreedOffspring(ServerLevel serverWorld, AgeableMob entity) {
        Duck child = PromenadeEntityTypes.DUCK.create(this.level());
        if (child != null && entity instanceof Duck mama) {
            child.setVariant(this.random.nextFloat() < 0.5f ? mama.getVariant() : this.getVariant());
        }
        return child;
    }

    public Holder<DuckVariant> getVariant() {
        return VariantUtils.resolve(this.level().registryAccess(), this.entityData.get(VARIANT), DuckVariants.DEFAULT);
    }

    public void setVariant(Holder<DuckVariant> registryEntry) {
        registryEntry.unwrapKey().ifPresent(key -> this.entityData.set(VARIANT, key));
    }

    /*==========*/
    /*   DATA   */
    /*==========*/

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, DuckVariants.DEFAULT);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        VariantUtils.writeVariant(nbt, this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        VariantUtils.readVariant(nbt, this.level().registryAccess(), PromenadeRegistryKeys.DUCK_VARIANT).ifPresent(this::setVariant);
    }
}