package fr.hugman.promenade.entity;

import fr.hugman.dawn.entity.ai.goal.AnimalTemptGoal;
import fr.hugman.promenade.content.AnimalContent;
import fr.hugman.promenade.registry.tag.PromenadeTags;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CapybaraEntity extends AnimalEntity {
	private static final IntProvider EAR_WIGGLE_COOLDOWN_PROVIDER = BiasedToBottomIntProvider.create(4, 64); // Minimum MUST be the length of the anim

	public final AnimationState walkingAnimationState = new AnimationState();
	public final AnimationState earWiggleAnimState = new AnimationState();
	public final AnimationState fallOverAnimState = new AnimationState();
	public final AnimationState sleepingAnimState = new AnimationState();

	private int earWiggleCooldown = 0;

	public CapybaraEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void tick() {
		super.tick();
		if(this.world.isClient()) {
			this.updateAnimations();
		}
	}

	/*========*/
	/*   AI   */
	/*========*/

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4D));
		this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(4, new AnimalTemptGoal(this, 1.0D, false));
		this.goalSelector.add(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));
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

	/*================*/
	/*   ANIMATIONS   */
	/*================*/

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
		if(this.getPose() == EntityPose.STANDING) {
			this.walkingAnimationState.setRunning((this.onGround || this.hasPrimaryPassenger()) && this.getVelocity().horizontalLengthSquared() > 1.0E-6, this.age);
		}
	}

	public void setStanding() {
		this.setPose(EntityPose.STANDING);
		//this.setLastPoseTick(this.world.getTime() - 52L);
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
		return this.isBaby() ? AnimalContent.CAPYBARA_AMBIENT_BABY_SOUND : AnimalContent.CAPYBARA_AMBIENT_SOUND;
	}

	@Override
	public int getMinAmbientSoundDelay() {
		return this.isBaby() ? 20 : super.getMinAmbientSoundDelay();
	}

	/*==============*/
	/*   VARIANTS   */
	/*==============*/

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return AnimalContent.CAPYBARA.create(this.world);
	}
}
