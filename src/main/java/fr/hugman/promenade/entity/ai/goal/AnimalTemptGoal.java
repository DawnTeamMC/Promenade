package fr.hugman.promenade.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

//TODO: move to Dawn
public class AnimalTemptGoal extends Goal {
	private static final TargetPredicate TEMPTING_ENTITY_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility();
	private final TargetPredicate predicate;
	protected final AnimalEntity entity;
	private final double speed;
	private double lastPlayerX;
	private double lastPlayerY;
	private double lastPlayerZ;
	private double lastPlayerPitch;
	private double lastPlayerYaw;
	@Nullable
	protected PlayerEntity closestPlayer;
	private int cooldown;
	private boolean active;
	private final boolean canBeScared;

	public AnimalTemptGoal(AnimalEntity entity, double speed, boolean canBeScared) {
		this.entity = entity;
		this.speed = speed;
		this.canBeScared = canBeScared;
		this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
		this.predicate = TEMPTING_ENTITY_PREDICATE.copy().setPredicate(this::isTemptedBy);
	}

	public boolean canStart() {
		if(this.cooldown > 0) {
			--this.cooldown;
			return false;
		}
		else {
			this.closestPlayer = this.entity.world.getClosestPlayer(this.predicate, this.entity);
			return this.closestPlayer != null;
		}
	}

	private boolean isTemptedBy(LivingEntity entity) {
		return this.entity.isBreedingItem(entity.getMainHandStack()) || this.entity.isBreedingItem(entity.getOffHandStack());
	}

	public boolean shouldContinue() {
		if(this.canBeScared()) {
			if(this.entity.squaredDistanceTo(this.closestPlayer) < 36.0) {
				if(this.closestPlayer.squaredDistanceTo(this.lastPlayerX, this.lastPlayerY, this.lastPlayerZ) > 0.01D) {
					return false;
				}

				if(Math.abs((double) this.closestPlayer.getPitch() - this.lastPlayerPitch) > 5.0 || Math.abs((double) this.closestPlayer.getYaw() - this.lastPlayerYaw) > 5.0) {
					return false;
				}
			}
			else {
				this.lastPlayerX = this.closestPlayer.getX();
				this.lastPlayerY = this.closestPlayer.getY();
				this.lastPlayerZ = this.closestPlayer.getZ();
			}

			this.lastPlayerPitch = (double) this.closestPlayer.getPitch();
			this.lastPlayerYaw = (double) this.closestPlayer.getYaw();
		}

		return this.canStart();
	}

	protected boolean canBeScared() {
		return this.canBeScared;
	}

	public void start() {
		this.lastPlayerX = this.closestPlayer.getX();
		this.lastPlayerY = this.closestPlayer.getY();
		this.lastPlayerZ = this.closestPlayer.getZ();
		this.active = true;
	}

	public void stop() {
		this.closestPlayer = null;
		this.entity.getNavigation().stop();
		this.cooldown = toGoalTicks(100);
		this.active = false;
	}

	public void tick() {
		this.entity.getLookControl().lookAt(this.closestPlayer, (float) (this.entity.getMaxHeadRotation() + 20), (float) this.entity.getMaxLookPitchChange());
		if(this.entity.squaredDistanceTo(this.closestPlayer) < 6.25D) {
			this.entity.getNavigation().stop();
		}
		else {
			this.entity.getNavigation().startMovingTo(this.closestPlayer, this.speed);
		}

	}

	public boolean isActive() {
		return this.active;
	}
}
