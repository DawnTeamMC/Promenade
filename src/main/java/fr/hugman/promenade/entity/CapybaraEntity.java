package fr.hugman.promenade.entity;

import fr.hugman.promenade.component.PromenadeComponentTypes;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
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
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.IntFunction;

public class CapybaraEntity extends Animal {
    private static final FloatProvider FART_CHANCE_PROVIDER = TrapezoidFloat.of(0.1F, 0.55F, 0.2F);
    private static final EntityDimensions BABY_DIMENSIONS = EntityDimensions.scalable(0.49f, 0.45f).withEyeHeight(0.40F);

    protected static final EntityDataAccessor<State> STATE = SynchedEntityData.defineId(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_STATE);
    public static final EntityDataAccessor<Long> LAST_STATE_TICK = SynchedEntityData.defineId(CapybaraEntity.class, EntityDataSerializers.LONG);

    public static final String FART_CHANCE_KEY = "fart_chance";
    public static final String LAST_STATE_TICK_KEY = "last_state_tick";
    public static final String STATE_KEY = "state_key";

    private static final EntityDataAccessor<Holder<CapybaraVariant>> VARIANT = SynchedEntityData.defineId(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_VARIANT);
    private static final EntityDataAccessor<Float> FART_CHANCE = SynchedEntityData.defineId(CapybaraEntity.class, EntityDataSerializers.FLOAT);

    private static final Brain.Provider<CapybaraEntity> BRAIN_PROVIDER = Brain.provider(
            List.of(
                    SensorType.NEAREST_LIVING_ENTITIES,
                    SensorType.HURT_BY,
                    PromenadeSensorTypes.CAPYBARA_TEMPTATIONS,
                    SensorType.NEAREST_ADULT,
                    SensorType.IS_IN_WATER
            ), _ -> CapybaraBrain.getActivities()
    );

    private static final int EAR_WIGGLE_LENGHT = (int) (0.2f * SharedConstants.TICKS_PER_SECOND);
    private static final IntProvider EAR_WIGGLE_COOLDOWN_PROVIDER = BiasedToBottomInt.of(EAR_WIGGLE_LENGHT, 64); // Minimum MUST be the length of the anim
    public final AnimationState earWiggleAnimState = new AnimationState();
    public final AnimationState fallToSleepAnimState = new AnimationState();
    public final AnimationState sleepingAnimState = new AnimationState();
    public final AnimationState wakeUpAnimState = new AnimationState();
    public final AnimationState fartAnimState = new AnimationState();

    public int earWiggleCooldown = 0;

    public CapybaraEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
        this.getNavigation().setCanFloat(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.updateAnimations();
        }
        this.tickState();
    }

    @Override
    protected void customServerAiStep(ServerLevel world) {
        ProfilerFiller profiler = Profiler.get();
        profiler.push("capybaraBrain");
        Brain<CapybaraEntity> brain = (Brain<CapybaraEntity>) this.getBrain();
        brain.tick((ServerLevel) this.level(), this);
        profiler.pop();
        profiler.push("capybaraActivityUpdate");
        CapybaraBrain.updateActivities(this);
        profiler.pop();
        super.customServerAiStep(world);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
        CapybaraVariants.select(this.random, this.registryAccess(), SpawnContext.create(world, this.blockPosition())).ifPresent(this::setVariant);
        this.entityData.set(LAST_STATE_TICK, world.getLevel().getGameTime() - WAKE_UP_LENGTH);
        this.entityData.set(FART_CHANCE, FART_CHANCE_PROVIDER.sample(this.random));
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    /*========*/
    /*   AI   */
    /*========*/

    public static AttributeSupplier.Builder createCapybaraAttributes() {
        return createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected Brain<? extends LivingEntity> makeBrain(Brain.Packed packedBrain) {
        return BRAIN_PROVIDER.makeBrain(this, packedBrain);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(PromenadeItemTags.CAPYBARA_FOOD);
    }

    @Override
    public void travel(Vec3 movementInput) {
        if (!this.isAlive()) {
            return;
        }
        if (this.isStationary() && this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.0, 1.0, 0.0));
            movementInput = movementInput.multiply(0.0, 1.0, 0.0);
        }
        super.travel(movementInput);
    }

    public boolean isStationary() {
        return this.isFarting() || this.isAsleep() || this.isWakingUp() || this.isFallingToSleep();
    }

    public boolean isPanicking() {
        return this.getBrain().checkMemory(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_PRESENT);
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pose) {
        return this.isBaby() ? BABY_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    protected BodyRotationControl createBodyControl() {
        return new CapybaraBodyControl(this);
    }

    class CapybaraBodyControl extends BodyRotationControl {

        public CapybaraBodyControl(CapybaraEntity capybara) {
            super(capybara);
        }

        @Override
        public void clientTick() {
            if (!CapybaraEntity.this.isStationary()) {
                super.clientTick();
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
        this.setLastStateTick(this.level().getGameTime());
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
        return this.isAsleep() && this.level().isBrightOutside();
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
        this.getBrain().setMemoryWithExpiry(PromenadeMemoryModuleTypes.FART_COOLDOWN, Unit.INSTANCE, createFartCooldown());

        this.playSound(PromenadeSoundEvents.CAPYBARA_FART, getSoundVolume(), getVoicePitch());
        this.gameEvent(GameEvent.ENTITY_ACTION);
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
        this.entityData.set(LAST_STATE_TICK, t);
    }

    public long getLastStateTickDelta() {
        return this.level().getGameTime() - this.entityData.get(LAST_STATE_TICK);
    }

    public State getState() {
        return this.entityData.get(STATE);
    }

    public void setState(State state) {
        this.entityData.set(STATE, state);
    }

    public enum State implements StringRepresentable {
        IDLING("idling", 0),
        FARTING("farting", 1),
        SLEEPING("sleeping", 2),
        FALL_TO_SLEEP("fall_to_sleep", 3),
        WAKE_UP("wake_up", 4);

        private final String name;
        private final int index;

        private static final EnumCodec<State> CODEC = StringRepresentable.fromEnum(State::values);
        private static final IntFunction<State> INDEX_TO_VALUE = ByIdMap.continuous(State::getIndex, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final StreamCodec<ByteBuf, State> PACKET_CODEC = ByteBufCodecs.idMapper(INDEX_TO_VALUE, State::getIndex);

        State(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public static State fromName(String name) {
            return CODEC.byName(name, IDLING);
        }

        public String getSerializedName() {
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
            this.earWiggleCooldown = Mth.clamp(EAR_WIGGLE_COOLDOWN_PROVIDER.sample(this.random), (int) (EAR_WIGGLE_LENGHT / this.getEarWiggleSpeed()), Integer.MAX_VALUE);
            this.earWiggleAnimState.start(this.tickCount);
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
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
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
            if (animstate != null) animstate.startIfStopped(this.tickCount);
        }

        super.onSyncedDataUpdated(data);
    }

    @Environment(EnvType.CLIENT)
    public boolean isSurprised() {
        boolean surprisedByFart = this.isFarting() && this.getLastStateTickDelta() < 40L;
        return isBaby() || surprisedByFart;
    }

    @Environment(EnvType.CLIENT)
    public boolean isVisuallySleeping() {
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
    public int getAmbientSoundInterval() {
        return this.isBaby() ? 20 : super.getAmbientSoundInterval();
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
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        var capyBaby = PromenadeEntityTypes.CAPYBARA.create(this.level(), EntitySpawnReason.BREEDING);
        if (capyBaby != null && entity instanceof CapybaraEntity capyMama) {
            capyBaby.setVariant(this.random.nextBoolean() ? this.getVariant() : capyMama.getVariant());
        }
        return capyBaby;
    }

    public Holder<CapybaraVariant> getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(Holder<CapybaraVariant> variant) {
        this.entityData.set(VARIANT, variant);
    }

    public float getFartChance() {
        return this.entityData.get(FART_CHANCE);
    }

    public void setFartChance(float frequency) {
        this.entityData.set(FART_CHANCE, frequency);
    }



    /*==========*/
    /*   DATA   */
    /*==========*/

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, VariantUtils.getDefaultOrAny(this.registryAccess(), CapybaraVariants.DEFAULT));
        builder.define(FART_CHANCE, 0.0f);
        builder.define(STATE, State.IDLING);
        builder.define(LAST_STATE_TICK, -WAKE_UP_LENGTH);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
		VariantUtils.writeVariant(view, this.getVariant());

        view.putFloat(FART_CHANCE_KEY, this.getFartChance());
        view.putString(STATE_KEY, this.getState().getSerializedName());
        view.putLong(LAST_STATE_TICK_KEY, this.entityData.get(LAST_STATE_TICK));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
		VariantUtils.readVariant(view, PromenadeRegistryKeys.CAPYBARA_VARIANT).ifPresent(this::setVariant);

        view.getString(STATE_KEY).ifPresent(s -> this.setState(State.fromName(s)));
        view.getLong(LAST_STATE_TICK_KEY).ifPresent(this::setLastStateTick);
        this.setFartChance(view.getFloatOr(FART_CHANCE_KEY, FART_CHANCE_PROVIDER.min()));
    }

    @Nullable
    @Override
    public <T> T get(DataComponentType<? extends T> type) {
        return type == PromenadeComponentTypes.CAPYBARA_VARIANT ? castComponentValue((DataComponentType<T>)type, this.getVariant()) : super.get(type);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter from) {
        this.applyImplicitComponentIfPresent(from, PromenadeComponentTypes.CAPYBARA_VARIANT);
        super.applyImplicitComponents(from);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        if (type == PromenadeComponentTypes.CAPYBARA_VARIANT) {
            this.setVariant(castComponentValue(PromenadeComponentTypes.CAPYBARA_VARIANT, value));
            return true;
        } else {
            return super.applyImplicitComponent(type, value);
        }
    }
}
