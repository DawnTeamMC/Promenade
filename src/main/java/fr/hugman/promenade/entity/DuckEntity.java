package fr.hugman.promenade.entity;

import fr.hugman.promenade.item.PromenadeItemTags;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.world.biome.PromenadeBiomeTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class DuckEntity extends AnimalEntity {
    private static final TrackedData<Integer> DUCK_TYPE = DataTracker.registerData(DuckEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.ofItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private static final EntityDimensions BABY_BASE_DIMENSIONS = EntityDimensions.changing(0.4F, 0.8F).scaled(0.5F).withEyeHeight(0.78125F);
    public static final String TYPE_KEY = "type";

    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;

    public DuckEntity(EntityType<? extends DuckEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    public static Builder createDuckAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DUCK_TYPE, 0);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @org.jetbrains.annotations.Nullable EntityData entityData) {
        RegistryEntry<Biome> biomeEntry = world.getBiome(this.getBlockPos());

        DuckEntity.Type type = DuckEntity.Type.fromBiome(biomeEntry);
        if (entityData instanceof DuckEntity.DuckData) {
            type = ((DuckEntity.DuckData) entityData).type;
        } else {
            entityData = new DuckEntity.DuckData(type);
        }
        this.setVariant(type);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getBaseDimensions(pose);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, TEMPTATION_ITEMS, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound compound) {
        super.writeCustomDataToNbt(compound);
        compound.putString(TYPE_KEY, this.getVariant().getName());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.setVariant(DuckEntity.Type.byName(compound.getString(TYPE_KEY)));
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        boolean isAirBorne = !this.isOnGround() && !this.isTouchingWater();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float) ((double) this.destPos + (double) (!isAirBorne ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
        if (isAirBorne && this.wingRotDelta < 0.55F) {
            this.wingRotDelta = 0.55F;
        }
        this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);
        Vec3d vec3d = this.getVelocity();
        if (isAirBorne && vec3d.y < 0.0D) {
            this.setVelocity(vec3d.multiply(1.0D, 0.75D, 1.0D));
        }
        this.wingRotation += this.wingRotDelta * 2.0F;
    }

    @Override
    public boolean canWalkOnFluid(FluidState fluidState) {
        return fluidState.isOf(Fluids.WATER);
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(this.getMovementSpeed(), movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9D));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public boolean handleFallDamage(float distance, float multiplier, DamageSource damageSource) {
        return false;
    }

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

    @Nullable
    @Override
    public DuckEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        DuckEntity child = PromenadeEntityTypes.DUCK.create(this.getWorld());
        if (child != null) {
            child.setVariant(this.random.nextFloat() < 0.5f ? ((DuckEntity) (mate)).getVariant() : this.getVariant());
        }
        return child;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(PromenadeItemTags.DUCK_FOOD);
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
        super.updatePassengerPosition(passenger, positionUpdater);
        if (passenger instanceof LivingEntity) {
            ((LivingEntity) passenger).bodyYaw = this.bodyYaw;
        }
    }

    public DuckEntity.Type getVariant() {
        return DuckEntity.Type.fromId(this.dataTracker.get(DUCK_TYPE));
    }

    private void setVariant(DuckEntity.Type type) {
        this.dataTracker.set(DUCK_TYPE, type.getIndex());
    }

    public enum Type {
        PEKIN(0, "pekin", PromenadeBiomeTags.PEKIN_DUCK_SPAWN),
        MALLARD(1, "mallard", PromenadeBiomeTags.MALLARD_DUCK_SPAWN);

        private static final DuckEntity.Type[] typeList = Arrays.stream(values()).sorted(Comparator.comparingInt(DuckEntity.Type::getIndex)).toArray(Type[]::new);
        private static final Map<String, DuckEntity.Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(DuckEntity.Type::getName, (type) -> type));
        private final int index;
        private final String name;
        private final TagKey<Biome> spawnBiomes;

        Type(int indexIn, String nameIn, TagKey<Biome> spawnBiomes) {
            this.index = indexIn;
            this.name = nameIn;
            this.spawnBiomes = spawnBiomes;
        }

        public static DuckEntity.Type byName(String name) {
            return TYPES_BY_NAME.getOrDefault(name, PEKIN);
        }

        public static DuckEntity.Type fromId(int index) {
            if (index < 0 || index > typeList.length) {
                index = 0;
            }
            return typeList[index];
        }

        public static DuckEntity.Type fromBiome(RegistryEntry<Biome> biome) {
            List<Type> shuffledList = Arrays.asList(typeList.clone());
            Collections.shuffle(shuffledList);
            for (DuckEntity.Type type : shuffledList) {
                if (biome.isIn(type.getSpawnBiomes())) {
                    return type;
                }
            }
            return PEKIN;
        }

        public String getName() {
            return this.name;
        }

        public TagKey<Biome> getSpawnBiomes() {
            return this.spawnBiomes;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static class DuckData extends PassiveEntity.PassiveData {
        public final DuckEntity.Type type;

        public DuckData(DuckEntity.Type typeIn) {
            super(false);
            this.type = typeIn;
        }
    }
}