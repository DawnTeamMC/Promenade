package fr.hugman.promenade.entity;

import com.mojang.serialization.Dynamic;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.content.AnimalContent;
import fr.hugman.promenade.registry.tag.PromenadeItemTags;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CapybaraEntity extends AnimalEntity implements VariantHolder<CapybaraVariant> {
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

	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
		CapybaraBrain.method_45367(this, world.getRandom());
		this.setVariant(CapybaraVariants.getRandom(this.random));
		this.dataTracker.set(LAST_STATE_TICK, world.toServerWorld().getTime() - WAKE_UP_LENGTH);
		this.dataTracker.set(FART_CHANCE, (this.random.nextFloat() * 0.5F) + 0.15F);
		return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
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
		return stack.isIn(PromenadeItemTags.BREEDING_CAPYBARA);
	}

	@Override
	public void travel(Vec3d movementInput) {
		if(!this.isAlive()) {
			return;
		}
		if(this.isStationary() && this.isOnGround()) {
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
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size) {
		return size.height * 13 / 14;
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
			if(!CapybaraEntity.this.isStationary()) {
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
		if(this.isFarting() && this.getLastStateTickDelta() > FART_LENGTH) {
			this.standUp();
		}
		if(this.isFallingToSleep() && this.getLastStateTickDelta() > FALL_TO_SLEEP_LENGTH) {
			if(this.canSleep()) {
				this.sleep();
			}
			else {
				if(this.canStopSleeping()) {
					this.stopSleeping();
				}
				else {
					this.standUp();
				}
			}
		}
		if(this.isWakingUp() && this.getLastStateTickDelta() > WAKE_UP_LENGTH) {
			this.standUp();
		}
	}

	// FALL TO SLEEP

	public boolean isFallingToSleep() {
		return this.getState() == CapybaraState.FALL_TO_SLEEP;
	}

	public boolean canFallToSleep() {
		return this.isStanding() && !this.isPanicking() && !this.isFarting();
	}

	public void fallToSleep() {
		this.setState(CapybaraState.FALL_TO_SLEEP);
		this.setLastStateTick(this.getWorld().getTime());
	}

	// SLEEP

	public boolean isAsleep() {
		return this.getState() == CapybaraState.SLEEPING;
	}

	public boolean canSleep() {
		return isFallingToSleep();
	}

	public void sleep() {
		this.setState(CapybaraState.SLEEPING);
		this.setLastStateTick(this.getWorld().getTime());
	}

	// WAKE UP

	private boolean isWakingUp() {
		return this.getState() == CapybaraState.WAKE_UP;
	}

	public boolean canStopSleeping() {
		return this.isAsleep() && this.getWorld().isDay();
	}

	public void stopSleeping() {
		this.setState(CapybaraState.WAKE_UP);
		this.setLastStateTick(this.getWorld().getTime());
	}

	// STAND

	public boolean isStanding() {
		return this.getState() == CapybaraState.STANDING;
	}

	public void standUp() {
		this.setState(CapybaraState.STANDING);
		this.setLastStateTick(this.getWorld().getTime() - WAKE_UP_LENGTH);
	}

	// FART

	public boolean isFarting() {
		return this.getState() == CapybaraState.FARTING;
	}

	public boolean canFart() {
		return !this.isFarting() && !this.isPanicking() && !this.isAsleep() && this.random.nextFloat() < this.getFartChance();
	}

	public void fart() {
		this.playSound(AnimalContent.CAPYBARA_FART_SOUND, getSoundVolume(), getSoundPitch());
		this.setState(CapybaraState.FARTING);
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
		if(this.earWiggleCooldown <= 0) {
			this.earWiggleCooldown = MathHelper.clamp(EAR_WIGGLE_COOLDOWN_PROVIDER.get(this.random), (int) (EAR_WIGGLE_LENGHT / this.getEarWiggleSpeed()), Integer.MAX_VALUE);
			this.earWiggleAnimState.start(this.age);
		}
		else {
			--this.earWiggleCooldown;
		}

		switch(this.getState()) {
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
		if(this.isAsleep()) return null; //TODO: Sleeping sound
		if(this.isBaby()) return AnimalContent.CAPYBARA_AMBIENT_BABY_SOUND;
		return AnimalContent.CAPYBARA_AMBIENT_SOUND;
	}

	@Override
	public int getMinAmbientSoundDelay() {
		return this.isBaby() ? 20 : super.getMinAmbientSoundDelay();
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundEvent = this.getAmbientSound();
		if(soundEvent != null) {
			// Do not pitch up if the sound for babies as it is already pitched up
			this.playSound(soundEvent, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
		}
	}



	/*==============*/
	/*   VARIANTS   */
	/*==============*/

	private static final TrackedData<CapybaraVariant> VARIANT = DataTracker.registerData(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_VARIANT);
	private static final TrackedData<Float> FART_CHANCE = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.FLOAT);

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return AnimalContent.CAPYBARA.create(this.getWorld());
	}

	@Override
	public CapybaraVariant getVariant() {
		return this.dataTracker.get(VARIANT);
	}

	@Override
	public void setVariant(CapybaraVariant variant) {
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
	protected static final TrackedData<CapybaraState> STATE = DataTracker.registerData(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_STATE);

	public static final String VARIANT_KEY = "Variant";
	public static final String FART_CHANCE_KEY = "FartChance";
	public static final String LAST_STATE_TICK_KEY = "LastStateTick";
	public static final String FARTING_KEY = "IsFarting";
	public static final String SLEEPING_KEY = "IsSleeping";

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(VARIANT, CapybaraVariants.getDefault());
		this.dataTracker.startTracking(FART_CHANCE, 0.0f);
		this.dataTracker.startTracking(STATE, CapybaraState.STANDING);
		this.dataTracker.startTracking(LAST_STATE_TICK, -WAKE_UP_LENGTH);
	}

	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putString(VARIANT_KEY, PromenadeRegistries.CAPYBARA_VARIANT.getId(this.getVariant()).toString());
		nbt.putFloat(FART_CHANCE_KEY, this.getFartChance());
		nbt.putLong(LAST_STATE_TICK_KEY, this.dataTracker.get(LAST_STATE_TICK));
		nbt.putBoolean(FARTING_KEY, this.isFarting());
		nbt.putBoolean(SLEEPING_KEY, this.isAsleep());
	}

	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		CapybaraVariant variant = PromenadeRegistries.CAPYBARA_VARIANT.get(Identifier.tryParse(nbt.getString(VARIANT_KEY)));
		if(variant != null) {
			this.setVariant(variant);
		}
		if(nbt.getBoolean(FARTING_KEY)) {
			this.setState(CapybaraState.FARTING);
		}
		if(nbt.getBoolean(SLEEPING_KEY)) {
			this.setState(CapybaraState.SLEEPING);
		}
		this.setFartChance(nbt.getFloat(FART_CHANCE_KEY));
	}

	public void setLastStateTick(long t) {
		this.dataTracker.set(LAST_STATE_TICK, t);
	}

	public long getLastStateTickDelta() {
		return this.getWorld().getTime() - this.dataTracker.get(LAST_STATE_TICK);
	}

	public CapybaraState getState() {
		return this.dataTracker.get(STATE);
	}

	public void setState(CapybaraState state) {
		this.dataTracker.set(STATE, state);
	}
}
