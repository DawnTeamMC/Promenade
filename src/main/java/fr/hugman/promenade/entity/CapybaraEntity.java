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
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CapybaraEntity extends AnimalEntity {
	private static final IntProvider IDLE_ANIM_COOLDOWN = BiasedToBottomIntProvider.create(4, 10);

	public final AnimationState walkingAnimationState = new AnimationState();
	public final AnimationState idleAnimState = new AnimationState();
	private int idleAnimCooldown = 0;

	public CapybaraEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

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
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2);
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size) {
		return size.height * 13 / 14;
	}

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return AnimalContent.CAPYBARA.create(this.world);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.isIn(PromenadeTags.Items.BREEDING_CAPYBARA);
	}

	@Override
	public void tick() {
		super.tick();
		if(this.world.isClient()) {
			this.updateAnimations();
		}
	}

	public void setStanding() {
		this.setPose(EntityPose.STANDING);
		//this.setLastPoseTick(this.world.getTime() - 52L);
	}

	private void updateAnimations() {
		if(this.idleAnimCooldown <= 0) {
			this.idleAnimCooldown = IDLE_ANIM_COOLDOWN.get(this.random);
			this.idleAnimState.start(this.age);
		}
		else {
			--this.idleAnimCooldown;
		}
		if(this.getPose() == EntityPose.STANDING) {
			this.walkingAnimationState.setRunning((this.onGround || this.hasPrimaryPassenger()) && this.getVelocity().horizontalLengthSquared() > 1.0E-6, this.age);
		}
	}
}
