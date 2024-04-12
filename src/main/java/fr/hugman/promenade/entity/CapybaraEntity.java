package fr.hugman.promenade.entity;

import com.mojang.serialization.Dynamic;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.item.PromenadeItemTags;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import io.netty.buffer.ByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.control.BodyControl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.floatprovider.TrapezoidFloatProvider;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.IntFunction;

public class CapybaraEntity extends AnimalEntity implements VariantHolder<RegistryEntry<CapybaraVariant>> {
    private static final FloatProvider FART_CHANCE_PROVIDER = TrapezoidFloatProvider.create(0.1F, 0.55F, 0.2F);
    private static final EntityDimensions BABY_BASE_DIMENSIONS = EntityDimensions.changing(0.7f, 0.875f).scaled(0.5F).withEyeHeight(0.5F);

    public CapybaraEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.getNavigation().setCanSwim(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            this.updateAnimations();
        }
        this.updateState();
    }

    @Override
    protected void mobTick() {
        this.getWorld().getProfiler().push("capybaraBrain");
        Brain<CapybaraEntity> brain = (Brain<CapybaraEntity>) this.getBrain();
        brain.tick((ServerWorld) this.getWorld(), this);
        this.getWorld().getProfiler().pop();
        this.getWorld().getProfiler().push("capybaraActivityUpdate");
        CapybaraBrain.updateActivities(this);
        this.getWorld().getProfiler().pop();
        super.mobTick();
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        RegistryEntry<CapybaraVariant> variant;
        if (entityData instanceof Data capybaraData) {
            variant = capybaraData.variant;
        } else {
            variant = CapybaraVariants.getRandom(this.getRegistryManager(), this.random);
            entityData = new Data(variant);
        }

        this.setVariant(variant);
        this.dataTracker.set(LAST_STATE_TICK, world.toServerWorld().getTime() - WAKE_UP_LENGTH);
        this.dataTracker.set(FART_CHANCE, FART_CHANCE_PROVIDER.get(this.random));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    /*========*/
    /*   AI   */
    /*========*/

    protected Brain.Profile<CapybaraEntity> createBrainProfile() {
        return CapybaraBrain.createProfile();
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return CapybaraBrain.create(this.createBrainProfile().deserialize(dynamic));
    }

    public static DefaultAttributeContainer.Builder createCapybaraAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(PromenadeItemTags.CAPYBARA_FOOD);
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (!this.isAlive()) {
            return;
        }
        if (this.isStationary() && this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.0, 1.0, 0.0));
            movementInput = movementInput.multiply(0.0, 1.0, 0.0);
        }
        super.travel(movementInput);
    }

    public boolean isStationary() {
        return this.isFarting() || this.isAsleep() || this.isWakingUp() || this.isFallingToSleep();
    }

    public boolean isPanicking() {
        return this.getBrain().isMemoryInState(MemoryModuleType.IS_PANICKING, MemoryModuleState.VALUE_PRESENT);
    }

    @Override
    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getBaseDimensions(pose);
    }

    @Override
    protected BodyControl createBodyControl() {
        return new CapybaraBodyControl(this);
    }

    class CapybaraBodyControl extends BodyControl {

        public CapybaraBodyControl(CapybaraEntity capybara) {
            super(capybara);
        }

        @Override
        public void tick() {
            if (!CapybaraEntity.this.isStationary()) {
                super.tick();
            }
        }
    }



    /*============*/
    /*   STATES   */
    /*============*/

    // These values depend on the animations
    public static final long FALL_TO_SLEEP_LENGTH = (long) (SharedConstants.TICKS_PER_SECOND * 1.9167667f);
    public static final long WAKE_UP_LENGTH = (long) (SharedConstants.TICKS_PER_SECOND * 5.791677f);
    public static final long FART_LENGTH = (long) (SharedConstants.TICKS_PER_SECOND * 3.8343335f);

    private void updateState() {
        if (this.isFarting() && this.getLastStateTickDelta() > FART_LENGTH) {
            this.standUp();
        }
        if (this.isFallingToSleep() && this.getLastStateTickDelta() > FALL_TO_SLEEP_LENGTH) {
            if (this.canSleep()) {
                this.sleep();
            } else {
                if (this.canStopSleeping()) {
                    this.stopSleeping();
                } else {
                    this.standUp();
                }
            }
        }
        if (this.isWakingUp() && this.getLastStateTickDelta() > WAKE_UP_LENGTH) {
            this.standUp();
        }
    }

    // FALL TO SLEEP

    public boolean isFallingToSleep() {
        return this.getState() == State.FALL_TO_SLEEP;
    }

    public boolean canFallToSleep() {
        return this.isStanding() && !this.isPanicking() && !this.isFarting();
    }

    public void fallToSleep() {
        this.setState(State.FALL_TO_SLEEP);
        this.setLastStateTick(this.getWorld().getTime());
    }

    // SLEEP

    public boolean isAsleep() {
        return this.getState() == State.SLEEPING;
    }

    public boolean canSleep() {
        return isFallingToSleep();
    }

    public void sleep() {
        this.setState(State.SLEEPING);
        this.setLastStateTick(this.getWorld().getTime());
    }

    // WAKE UP

    private boolean isWakingUp() {
        return this.getState() == State.WAKE_UP;
    }

    public boolean canStopSleeping() {
        return this.isAsleep() && this.getWorld().isDay();
    }

    public void stopSleeping() {
        this.setState(State.WAKE_UP);
        this.setLastStateTick(this.getWorld().getTime());
    }

    // STAND

    public boolean isStanding() {
        return this.getState() == State.STANDING;
    }

    public void standUp() {
        this.setState(State.STANDING);
        this.setLastStateTick(this.getWorld().getTime() - WAKE_UP_LENGTH);
    }

    // FART

    public boolean isFarting() {
        return this.getState() == State.FARTING;
    }

    public boolean canFart() {
        return !this.isFarting() && !this.isPanicking() && !this.isAsleep() && this.random.nextFloat() < this.getFartChance();
    }

    public void fart() {
        this.playSound(PromenadeSoundEvents.CAPYBARA_FART, getSoundVolume(), getSoundPitch());
        this.setState(State.FARTING);
        this.setLastStateTick(this.getWorld().getTime());
    }



    /*================*/
    /*   ANIMATIONS   */
    /*================*/

    private static final int EAR_WIGGLE_LENGHT = (int) (0.2f * SharedConstants.TICKS_PER_SECOND);
    private static final IntProvider EAR_WIGGLE_COOLDOWN_PROVIDER = BiasedToBottomIntProvider.create(EAR_WIGGLE_LENGHT, 64); // Minimum MUST be the length of the anim
    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState earWiggleAnimState = new AnimationState();
    public final AnimationState fallToSleepAnimState = new AnimationState();
    public final AnimationState sleepingAnimState = new AnimationState();
    public final AnimationState wakeUpAnimState = new AnimationState();
    public final AnimationState fartAnimState = new AnimationState();
    private int earWiggleCooldown = 0;

    @Environment(EnvType.CLIENT)
    private void updateAnimations() {
        if (this.earWiggleCooldown <= 0) {
            this.earWiggleCooldown = MathHelper.clamp(EAR_WIGGLE_COOLDOWN_PROVIDER.get(this.random), (int) (EAR_WIGGLE_LENGHT / this.getEarWiggleSpeed()), Integer.MAX_VALUE);
            this.earWiggleAnimState.start(this.age);
        } else {
            --this.earWiggleCooldown;

        }

        switch (this.getState()) {
            case STANDING -> {
                this.walkingAnimationState.setRunning((this.isOnGround() || this.hasControllingPassenger()) && this.getVelocity().horizontalLengthSquared() > 1.0E-6, this.age);
                this.fallToSleepAnimState.stop();
                this.sleepingAnimState.stop();
                this.wakeUpAnimState.stop();
                this.fartAnimState.stop();
            }
            case FALL_TO_SLEEP -> {
                this.walkingAnimationState.stop();
                this.fallToSleepAnimState.startIfNotRunning(this.age);
                this.sleepingAnimState.stop();
                this.wakeUpAnimState.stop();
                this.fartAnimState.stop();
            }
            case SLEEPING -> {
                this.walkingAnimationState.stop();
                this.fallToSleepAnimState.stop();
                this.sleepingAnimState.startIfNotRunning(this.age);
                this.wakeUpAnimState.stop();
                this.fartAnimState.stop();
            }
            case WAKE_UP -> {
                this.walkingAnimationState.stop();
                this.fallToSleepAnimState.stop();
                this.sleepingAnimState.stop();
                this.wakeUpAnimState.startIfNotRunning(this.age);
                this.fartAnimState.stop();
            }
            case FARTING -> {
                this.walkingAnimationState.stop();
                this.fallToSleepAnimState.stop();
                this.sleepingAnimState.stop();
                this.wakeUpAnimState.stop();
                this.fartAnimState.startIfNotRunning(this.age);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public boolean hasLargeEyes() {
        boolean surprisedByFart = this.isFarting() && this.getLastStateTickDelta() < 40L;
        return isBaby() || surprisedByFart;
    }

    @Environment(EnvType.CLIENT)
    public boolean hasClosedEyes() {
        boolean aboutToStartToSleep = this.isFallingToSleep() && this.getLastStateTickDelta() > 31L;
        boolean shakingHead = this.isFarting() && (40L < this.getLastStateTickDelta() && this.getLastStateTickDelta() < 65L);
        boolean wakingUp = this.isWakingUp() && (this.getLastStateTickDelta() < 5L || (18L < this.getLastStateTickDelta() && this.getLastStateTickDelta() < 34L));

        return aboutToStartToSleep || this.isAsleep() || shakingHead || wakingUp;
    }

    @Environment(EnvType.CLIENT)
    public boolean canAngleHead() {
        return !this.isAsleep() && !this.isFallingToSleep() && !this.isWakingUp();
    }

    @Environment(EnvType.CLIENT)
    public float getEarWiggleSpeed() {
        return this.isAsleep() ? 0.3f : 1.0f;
    }


    /*============*/
    /*   SOUNDS   */
    /*============*/

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isAsleep()) return null; //TODO: Sleeping sound
        if (this.isBaby()) return PromenadeSoundEvents.CAPYBARA_AMBIENT_BABY;
        return PromenadeSoundEvents.CAPYBARA_AMBIENT;
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return this.isBaby() ? 20 : super.getMinAmbientSoundDelay();
    }

    @Override
    public void playAmbientSound() {
        SoundEvent soundEvent = this.getAmbientSound();
        if (soundEvent != null) {
            // Do not pitch up if the sound for babies as it is already pitched up
            this.playSound(soundEvent, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        }
    }



    /*==============*/
    /*   VARIANTS   */
    /*==============*/

    private static final TrackedData<RegistryEntry<CapybaraVariant>> VARIANT = DataTracker.registerData(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_VARIANT);
    private static final TrackedData<Float> FART_CHANCE = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.FLOAT);

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        var capy = PromenadeEntityTypes.CAPYBARA.create(this.getWorld());
        if (capy != null && entity instanceof CapybaraEntity capyParent) {
            if (this.random.nextBoolean()) {
                capy.setVariant(this.getVariant());
            } else {
                capy.setVariant(capyParent.getVariant());
            }
        }
        return capy;
    }

    @Override
    public RegistryEntry<CapybaraVariant> getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    @Override
    public void setVariant(RegistryEntry<CapybaraVariant> variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    public float getFartChance() {
        return this.dataTracker.get(FART_CHANCE);
    }

    public void setFartChance(float frequency) {
        this.dataTracker.set(FART_CHANCE, frequency);
    }



    /*==========*/
    /*   DATA   */
    /*==========*/

    public static final TrackedData<Long> LAST_STATE_TICK = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.LONG);
    protected static final TrackedData<State> STATE = DataTracker.registerData(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_STATE);

    public static final String VARIANT_KEY = "variant";
    public static final String FART_CHANCE_KEY = "fart_chance";
    public static final String LAST_STATE_TICK_KEY = "last_state_tick";
    public static final String FARTING_KEY = "is_farting";
    public static final String SLEEPING_KEY = "is_sleeping";

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, this.getRegistryManager().get(PromenadeRegistryKeys.CAPYBARA_VARIANT).entryOf(CapybaraVariants.BROWN));
        builder.add(FART_CHANCE, 0.0f);
        builder.add(STATE, State.STANDING);
        builder.add(LAST_STATE_TICK, -WAKE_UP_LENGTH);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString(VARIANT_KEY, (this.getVariant().getKey().orElse(CapybaraVariants.BROWN)).getValue().toString());
        nbt.putFloat(FART_CHANCE_KEY, this.getFartChance());
        nbt.putLong(LAST_STATE_TICK_KEY, this.dataTracker.get(LAST_STATE_TICK));
        nbt.putBoolean(FARTING_KEY, this.isFarting());
        nbt.putBoolean(SLEEPING_KEY, this.isAsleep());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        Optional.ofNullable(Identifier.tryParse(nbt.getString(VARIANT_KEY)))
                .map(variantId -> RegistryKey.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, variantId))
                .flatMap(variantKey -> this.getRegistryManager().get(PromenadeRegistryKeys.CAPYBARA_VARIANT).getEntry(variantKey))
                .ifPresent(this::setVariant);

        if (nbt.getBoolean(FARTING_KEY)) {
            this.setState(State.FARTING);
        }
        if (nbt.getBoolean(SLEEPING_KEY)) {
            this.setState(State.SLEEPING);
        }
        this.setFartChance(nbt.getFloat(FART_CHANCE_KEY));
    }

    public void setLastStateTick(long t) {
        this.dataTracker.set(LAST_STATE_TICK, t);
    }

    public long getLastStateTickDelta() {
        return this.getWorld().getTime() - this.dataTracker.get(LAST_STATE_TICK);
    }

    public State getState() {
        return this.dataTracker.get(STATE);
    }

    public void setState(State state) {
        this.dataTracker.set(STATE, state);
    }

    public enum State implements StringIdentifiable {
        STANDING("standing", 0),
        FARTING("farting", 1),
        SLEEPING("sleeping", 2),
        FALL_TO_SLEEP("fall_to_sleep", 3),
        WAKE_UP("wake_up", 4);

        private final String name;
        private final int index;

        private static final StringIdentifiable.EnumCodec<State> CODEC = StringIdentifiable.createCodec(State::values);
        private static final IntFunction<State> INDEX_TO_VALUE = ValueLists.createIdToValueFunction(State::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO);
        public static final PacketCodec<ByteBuf, State> PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, State::getIndex);

        State(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public static State fromName(String name) {
            return CODEC.byId(name, STANDING);
        }

        public String asString() {
            return this.name;
        }

        private int getIndex() {
            return this.index;
        }
    }

    public static class Data extends PassiveEntity.PassiveData {
        public final RegistryEntry<CapybaraVariant> variant;

        public Data(RegistryEntry<CapybaraVariant> variant) {
            super(false);
            this.variant = variant;
        }
    }


    /*==============*/
    /*   TEXTURES   */
    /*==============*/

    public Identifier getTexture() {
        var variant = this.getVariant().value();
        if (this.hasClosedEyes()) {
            return variant.closedEyesTexture();
        }
        return this.hasLargeEyes() ? variant.largeEyesTexture() : variant.smallEyesTexture();
    }
}
