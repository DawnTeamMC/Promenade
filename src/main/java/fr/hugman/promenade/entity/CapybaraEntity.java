package fr.hugman.promenade.entity;

import com.mojang.serialization.Dynamic;
import fr.hugman.promenade.content.AnimalContent;
import fr.hugman.promenade.entity.data.PromenadeTrackedData;
import fr.hugman.promenade.registry.PromenadeRegistries;
import fr.hugman.promenade.registry.tag.PromenadeTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
		if(this.world.isClient()) {
			this.updateAnimations();
		}
	}

	@Override
	protected void mobTick() {
		this.world.getProfiler().push("capybaraBrain");
		Brain<CapybaraEntity> brain = (Brain<CapybaraEntity>) this.getBrain();
		brain.tick((ServerWorld) this.world, this);
		this.world.getProfiler().pop();
		this.world.getProfiler().push("capybaraActivityUpdate");
		CapybaraBrain.updateActivities(this);
		this.world.getProfiler().pop();
		super.mobTick();
	}

	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
		CapybaraBrain.method_45367(this, world.getRandom());
		this.setVariant(CapybaraVariants.getRandom(this.random));
		this.dataTracker.set(LAST_POSE_TICK, world.toServerWorld().getTime() - FALL_OVER_LENGTH);
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
		return stack.isIn(PromenadeTags.Items.BREEDING_CAPYBARA);
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
		return this.isFarting() || this.isSleeping() || this.isChangingPose();
	}

	public boolean isPanicking() {
		return this.getBrain().isMemoryInState(MemoryModuleType.IS_PANICKING, MemoryModuleState.VALUE_PRESENT);
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

	/*================*/
	/*   ANIMATIONS   */
	/*================*/


	private static final IntProvider EAR_WIGGLE_COOLDOWN_PROVIDER = BiasedToBottomIntProvider.create(4, 64); // Minimum MUST be the length of the anim
	public final AnimationState walkingAnimationState = new AnimationState();
	public final AnimationState earWiggleAnimState = new AnimationState();
	public final AnimationState fallOverAnimState = new AnimationState();
	public final AnimationState sleepingAnimState = new AnimationState();
	public final AnimationState fartAnimState = new AnimationState();
	private int earWiggleCooldown = 0;

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size) {
		return size.height * 13 / 14;
	}

	private void updateAnimations() {
		if(this.earWiggleCooldown <= 0) {
			this.earWiggleCooldown = EAR_WIGGLE_COOLDOWN_PROVIDER.get(this.random);
			this.earWiggleAnimState.start(this.age);
		}
		else {
			--this.earWiggleCooldown;
		}

		switch(this.getPose()) {
			case STANDING: {
				this.walkingAnimationState.setRunning((this.onGround || this.hasPrimaryPassenger()) && this.getVelocity().horizontalLengthSquared() > 1.0E-6, this.age);
				this.sleepingAnimState.stop();
				this.fallOverAnimState.stop();
				this.fartAnimState.stop();
				break;
			}
			case SLEEPING: {
				this.walkingAnimationState.stop();
				if(this.shouldPlayFallOverAnimation()) {
					this.fallOverAnimState.startIfNotRunning(this.age);
					this.sleepingAnimState.stop();
				}
				else {
					this.fallOverAnimState.stop();
					this.sleepingAnimState.startIfNotRunning(this.age);
				}
				this.fartAnimState.stop();
				break;
			}
			case CROUCHING: {
				this.walkingAnimationState.stop();
				this.sleepingAnimState.stop();
				this.fallOverAnimState.stop();
				this.fartAnimState.startIfNotRunning(this.age);
				break;
			}
		}
		if(this.getPose() == EntityPose.STANDING) {
			this.walkingAnimationState.setRunning((this.onGround || this.hasPrimaryPassenger()) && this.getVelocity().horizontalLengthSquared() > 1.0E-6, this.age);
		}
		else {
			this.walkingAnimationState.setRunning(false, this.age);
		}
	}

	public boolean isSurprisedByFart() {
		return this.fartAnimState.isRunning() && this.fartAnimState.getTimeRunning() < (1.7F * 20L);
	}

	@Environment(EnvType.CLIENT)
	public boolean hasLargeEyes() {
		return isBaby() || isSurprisedByFart();
	}

	public void setLastPoseTick(long lastPoseTick) {
		this.dataTracker.set(LAST_POSE_TICK, lastPoseTick);
	}

	public long getLastPoseTickDelta() {
		return this.world.getTime() - this.dataTracker.get(LAST_POSE_TICK);
	}

	public void fart() {
		if(this.isInPose(EntityPose.CROUCHING)) {
			return;
		}
		this.playSound(AnimalContent.CAPYBARA_FART_SOUND, 1.0f, 1.0f);
		this.setPose(EntityPose.CROUCHING);
		this.setLastPoseTick(this.world.getTime());
	}

	public void startSleeping() {
		if(this.isInPose(EntityPose.SLEEPING)) {
			return;
		}
		this.setPose(EntityPose.SLEEPING);
		this.setLastPoseTick(this.world.getTime());
	}

	public void startStanding() {
		if(this.isInPose(EntityPose.STANDING)) {
			return;
		}
		this.setPose(EntityPose.STANDING);
		this.setLastPoseTick(this.world.getTime());
	}

	public void setStanding() {
		this.setPose(EntityPose.STANDING);
		this.setLastPoseTick(this.world.getTime() - STAND_UP_LENGTH);
	}

	private boolean shouldPlayFallOverAnimation() {
		return isSleeping() && this.getLastPoseTickDelta() < FALL_OVER_LENGTH;
	}

	public boolean isSleeping() {
		return this.getPose() == EntityPose.SLEEPING;
	}

	public boolean isFarting() {
		return this.getPose() == EntityPose.CROUCHING;
	}

	public boolean isChangingPose() {
		long l = this.getLastPoseTickDelta();
		return switch(this.getPose()) {
			case STANDING -> l < STAND_UP_LENGTH;
			case SLEEPING -> l < FALL_OVER_LENGTH;
			default -> false;
		};
	}

	/*============*/
	/*   SOUNDS   */
	/*============*/

	@Override
	public void playAmbientSound() {
		SoundEvent soundEvent = this.getAmbientSound();
		if(soundEvent != null) {
			// do not pitch up if the sound for babies as it is a custom one
			this.playSound(soundEvent, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
		}
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if(this.isBaby()) return AnimalContent.CAPYBARA_AMBIENT_BABY_SOUND;
		return AnimalContent.CAPYBARA_AMBIENT_SOUND;
	}

	@Override
	public int getMinAmbientSoundDelay() {
		return this.isBaby() ? 20 : super.getMinAmbientSoundDelay();
	}

	/*==============*/
	/*   VARIANTS   */
	/*==============*/

	private static final TrackedData<CapybaraVariant> VARIANT = DataTracker.registerData(CapybaraEntity.class, PromenadeTrackedData.CAPYBARA_VARIANT);
	private static final TrackedData<Float> FART_CHANCE = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.FLOAT);

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return AnimalContent.CAPYBARA.create(this.world);
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

	public boolean shouldFart() {
		return this.random.nextFloat() < this.getFartChance();
	}

	/*==========*/
	/*   DATA   */
	/*==========*/
	public static final TrackedData<Long> LAST_POSE_TICK = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.LONG);
	public static final long FALL_OVER_LENGTH = 39L;
	public static final long STAND_UP_LENGTH = 39L;
	public static final long FART_LENGTH = 77L;

	public static final String VARIANT_KEY = "Variant";
	public static final String FART_CHANCE_KEY = "FartChance";
	public static final String LAST_POSE_TICK_KEY = "LastPoseTick";
	public static final String FARTING_KEY = "IsFarting";
	public static final String SLEEPING_KEY = "IsSleeping";

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(VARIANT, CapybaraVariants.getDefault());
		this.dataTracker.startTracking(FART_CHANCE, 0.0f);
		this.dataTracker.startTracking(LAST_POSE_TICK, -FALL_OVER_LENGTH);
	}

	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putString(VARIANT_KEY, PromenadeRegistries.CAPYBARA_VARIANT.getId(this.getVariant()).toString());
		nbt.putFloat(FART_CHANCE_KEY, this.getFartChance());
		nbt.putLong(LAST_POSE_TICK_KEY, this.dataTracker.get(LAST_POSE_TICK));
		nbt.putBoolean(FARTING_KEY, this.getPose() == EntityPose.CROUCHING);
		nbt.putBoolean(SLEEPING_KEY, this.getPose() == EntityPose.SLEEPING);
	}

	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		CapybaraVariant variant = PromenadeRegistries.CAPYBARA_VARIANT.get(Identifier.tryParse(nbt.getString(VARIANT_KEY)));
		if(variant != null) {
			this.setVariant(variant);
		}
		if(nbt.getBoolean(FARTING_KEY)) {
			this.setPose(EntityPose.CROUCHING);
		}
		if(nbt.getBoolean(SLEEPING_KEY)) {
			this.setPose(EntityPose.SLEEPING);
		}
		this.setFartChance(nbt.getFloat(FART_CHANCE_KEY));
	}
}
