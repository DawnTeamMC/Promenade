package fr.hugman.promenade.entity;

import com.mojang.serialization.Dynamic;
import fr.hugman.promenade.component.PromenadeComponentTypes;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.CapybaraVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.sound.PromenadeSoundEvents;
import fr.hugman.promenade.tag.PromenadeItemTags;
import io.netty.buffer.ByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
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
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Unit;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.floatprovider.TrapezoidFloatProvider;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

public class CapybaraEntity extends AnimalEntity {
    private static final FloatProvider FART_CHANCE_PROVIDER = TrapezoidFloatProvider.create(0.1F, 0.55F, 0.2F);
    private static final EntityDimensions BABY_BASE_DIMENSIONS = EntityDimensions.changing(0.7f, 0.875f).scaled(0.5F).withEyeHeight(0.5F);

    protected static final TrackedData<State> STATE = DataTracker.registerData(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_STATE);
    public static final TrackedData<Long> LAST_STATE_TICK = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.LONG);

    public static final String FART_CHANCE_KEY = "fart_chance";
    public static final String LAST_STATE_TICK_KEY = "last_state_tick";
    public static final String STATE_KEY = "state_key";

    private static final TrackedData<RegistryEntry<CapybaraVariant>> VARIANT = DataTracker.registerData(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_VARIANT);
    private static final TrackedData<Float> FART_CHANCE = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.FLOAT);

    private static final int EAR_WIGGLE_LENGHT = (int) (0.2f * SharedConstants.TICKS_PER_SECOND);
    private static final IntProvider EAR_WIGGLE_COOLDOWN_PROVIDER = BiasedToBottomIntProvider.create(EAR_WIGGLE_LENGHT, 64); // Minimum MUST be the length of the anim
    public final AnimationState earWiggleAnimState = new AnimationState();
    public final AnimationState fallToSleepAnimState = new AnimationState();
    public final AnimationState sleepingAnimState = new AnimationState();
    public final AnimationState wakeUpAnimState = new AnimationState();
    public final AnimationState fartAnimState = new AnimationState();

    public int earWiggleCooldown = 0;

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
        this.tickState();
    }

    @Override
    protected void mobTick(ServerWorld world) {
        Profiler profiler = Profilers.get();
        profiler.push("capybaraBrain");
        Brain<CapybaraEntity> brain = (Brain<CapybaraEntity>) this.getBrain();
        brain.tick((ServerWorld) this.getWorld(), this);
        profiler.pop();
        profiler.push("capybaraActivityUpdate");
        CapybaraBrain.updateActivities(this);
        profiler.pop();
        super.mobTick(world);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        CapybaraVariants.select(this.random, this.getRegistryManager(), SpawnContext.of(world, this.getBlockPos())).ifPresent(this::setVariant);
        this.dataTracker.set(LAST_STATE_TICK, world.toServerWorld().getTime() - WAKE_UP_LENGTH);
        this.dataTracker.set(FART_CHANCE, FART_CHANCE_PROVIDER.get(this.random));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    /*========*/
    /*   AI   */
    /*========*/

    public static DefaultAttributeContainer.Builder createCapybaraAttributes() {
        return createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2);
    }

    protected Brain.Profile<CapybaraEntity> createBrainProfile() {
        return CapybaraBrain.createProfile();
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return CapybaraBrain.create(this.createBrainProfile().deserialize(dynamic));
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

    public void forceDefaultState() {
        this.updateState(State.IDLING);
    }

    public void updateState(State state) {
        this.setState(state);
        this.setLastStateTick(this.getWorld().getTime());
    }

    private void tickState() {
        if (this.isFarting() && this.getLastStateTickDelta() > this.getFartLength()) {
            finishFarting();
        }
        if (this.isFallingToSleep() && this.getLastStateTickDelta() > this.getFallToSleepLength()) {
            finishFallingToSleep();
        }
        if (this.isWakingUp() && this.getLastStateTickDelta() > this.getWakeUpLength()) {
            finishWakingUp();
        }
    }

    // FALL TO SLEEP

    public boolean isFallingToSleep() {
        return this.getState() == State.FALL_TO_SLEEP;
    }

    public boolean canFallToSleep() {
        return this.isStanding() && !this.isPanicking() && !this.isFarting();
    }

    public long getFallToSleepLength() {
        return FALL_TO_SLEEP_LENGTH;
    }

    public void startFallingToSleep() {
        this.updateState(State.FALL_TO_SLEEP);
    }

    public void finishFallingToSleep() {
        this.updateState(State.SLEEPING);
    }

    // SLEEP

    public boolean isAsleep() {
        return this.getState() == State.SLEEPING;
    }

    // WAKE UP

    private boolean isWakingUp() {
        return this.getState() == State.WAKE_UP;
    }

    public boolean canWakeUp() {
        return this.isAsleep() && this.getWorld().isDay();
    }

    public long getWakeUpLength() {
        return WAKE_UP_LENGTH;
    }

    public void startWakingUp() {
        this.updateState(State.WAKE_UP);
    }

    public void finishWakingUp() {
        this.updateState(State.IDLING);
    }

    // STAND

    public boolean isStanding() {
        return this.getState() == State.IDLING;
    }

    // FART

    public void fart() {
        this.updateState(State.FARTING);
        this.getBrain().remember(PromenadeMemoryModuleTypes.FART_COOLDOWN, Unit.INSTANCE, createFartCooldown());

        this.playSound(PromenadeSoundEvents.CAPYBARA_FART, getSoundVolume(), getSoundPitch());
        this.emitGameEvent(GameEvent.ENTITY_ACTION);
    }

    public boolean isFarting() {
        return this.getState() == State.FARTING;
    }

    public long getFartLength() {
        return FART_LENGTH;
    }

    public long createFartCooldown() {
        return 120 * 20L;
    }

    public void finishFarting() {
        this.updateState(State.IDLING);
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
        IDLING("idling", 0),
        FARTING("farting", 1),
        SLEEPING("sleeping", 2),
        FALL_TO_SLEEP("fall_to_sleep", 3),
        WAKE_UP("wake_up", 4);

        private final String name;
        private final int index;

        private static final EnumCodec<State> CODEC = StringIdentifiable.createCodec(State::values);
        private static final IntFunction<State> INDEX_TO_VALUE = ValueLists.createIndexToValueFunction(State::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO);
        public static final PacketCodec<ByteBuf, State> PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, State::getIndex);

        State(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public static State fromName(String name) {
            return CODEC.byId(name, IDLING);
        }

        public String asString() {
            return this.name;
        }

        private int getIndex() {
            return this.index;
        }
    }

    /*================*/
    /*   ANIMATIONS   */
    /*================*/

    @Environment(EnvType.CLIENT)
    private void updateAnimations() {
        if (this.earWiggleCooldown <= 0) {
            this.earWiggleCooldown = MathHelper.clamp(EAR_WIGGLE_COOLDOWN_PROVIDER.get(this.random), (int) (EAR_WIGGLE_LENGHT / this.getEarWiggleSpeed()), Integer.MAX_VALUE);
            this.earWiggleAnimState.start(this.age);
        } else {
            this.earWiggleCooldown--;
        }
    }

    private void stopAnimations() {
        this.fallToSleepAnimState.stop();
        this.sleepingAnimState.stop();
        this.wakeUpAnimState.stop();
        this.fartAnimState.stop();
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (STATE.equals(data)) {
            var state = this.getState();
            this.stopAnimations();
            AnimationState animstate = (switch (state) {
                case FALL_TO_SLEEP -> this.fallToSleepAnimState;
                case SLEEPING -> this.sleepingAnimState;
                case WAKE_UP -> this.wakeUpAnimState;
                case FARTING -> this.fartAnimState;
                case null, default -> null;
            });
            if (animstate != null) animstate.startIfNotRunning(this.age);
        }

        super.onTrackedDataSet(data);
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
        return !this.isFarting() && !this.isAsleep() && !this.isFallingToSleep() && !this.isWakingUp();
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
        if (this.isFallingToSleep() || this.isAsleep() || this.isWakingUp() || this.isFarting())
            return null; //TODO: Sleeping sound
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

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        var capyBaby = PromenadeEntityTypes.CAPYBARA.create(this.getWorld(), SpawnReason.BREEDING);
        if (capyBaby != null && entity instanceof CapybaraEntity capyMama) {
            capyBaby.setVariant(this.random.nextBoolean() ? this.getVariant() : capyMama.getVariant());
        }
        return capyBaby;
    }

    public RegistryEntry<CapybaraVariant> getVariant() {
        return this.dataTracker.get(VARIANT);
    }

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

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, Variants.getOrDefaultOrThrow(this.getRegistryManager(), CapybaraVariants.DEFAULT));
        builder.add(FART_CHANCE, 0.0f);
        builder.add(STATE, State.IDLING);
        builder.add(LAST_STATE_TICK, -WAKE_UP_LENGTH);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        Variants.writeVariantToNbt(nbt, this.getVariant());
        nbt.putFloat(FART_CHANCE_KEY, this.getFartChance());
        nbt.putString(STATE_KEY, this.getState().asString());
        nbt.putLong(LAST_STATE_TICK_KEY, this.dataTracker.get(LAST_STATE_TICK));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        Variants.readVariantFromNbt(nbt, this.getRegistryManager(), PromenadeRegistryKeys.CAPYBARA_VARIANT).ifPresent(this::setVariant);

        nbt.getString(LAST_STATE_TICK_KEY).ifPresent(s -> this.setState(State.fromName(s))); //TODO: default?
        nbt.getFloat(FART_CHANCE_KEY).ifPresent(this::setFartChance); //TODO: default?
    }

    @Nullable
    @Override
    public <T> T get(ComponentType<? extends T> type) {
        return type == PromenadeComponentTypes.CAPYBARA_VARIANT ? castComponentValue((ComponentType<T>)type, this.getVariant()) : super.get(type);
    }

    @Override
    protected void copyComponentsFrom(ComponentsAccess from) {
        this.copyComponentFrom(from, PromenadeComponentTypes.CAPYBARA_VARIANT);
        super.copyComponentsFrom(from);
    }

    @Override
    protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
        if (type == PromenadeComponentTypes.CAPYBARA_VARIANT) {
            this.setVariant(castComponentValue(PromenadeComponentTypes.CAPYBARA_VARIANT, value));
            return true;
        } else {
            return super.setApplicableComponent(type, value);
        }
    }
}
