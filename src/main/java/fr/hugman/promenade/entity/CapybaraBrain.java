package fr.hugman.promenade.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.registry.content.AnimalContent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;

import java.util.function.Predicate;

public class CapybaraBrain {
	private static final UniformIntProvider WALK_TOWARD_ADULT_RANGE = UniformIntProvider.create(5, 16);

	private static final ImmutableList<SensorType<? extends Sensor<? super CapybaraEntity>>> SENSORS = ImmutableList.of(
			SensorType.NEAREST_LIVING_ENTITIES,
			SensorType.HURT_BY,
			PromenadeSensorTypes.CAPYBARA_TEMPTATIONS,
			SensorType.NEAREST_ADULT);

	private static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(
			MemoryModuleType.IS_PANICKING,
			MemoryModuleType.HURT_BY,
			MemoryModuleType.HURT_BY_ENTITY,
			MemoryModuleType.WALK_TARGET,
			MemoryModuleType.LOOK_TARGET,
			MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
			MemoryModuleType.PATH,
			MemoryModuleType.VISIBLE_MOBS,
			MemoryModuleType.TEMPTING_PLAYER,
			MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
			MemoryModuleType.GAZE_COOLDOWN_TICKS,
			MemoryModuleType.IS_TEMPTED,
			MemoryModuleType.BREED_TARGET,
			MemoryModuleType.NEAREST_VISIBLE_ADULT);

	protected static void method_45367(CapybaraEntity capybara, Random random) {
	}

	public static Brain.Profile<CapybaraEntity> createProfile() {
		return Brain.createProfile(MEMORY_MODULES, SENSORS);
	}

	protected static Brain<?> create(Brain<CapybaraEntity> brain) {
		CapybaraBrain.addCoreActivities(brain);
		CapybaraBrain.addIdleActivities(brain);
		brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
		brain.setDefaultActivity(Activity.IDLE);
		brain.resetPossibleActivities();
		return brain;
	}

	private static void addCoreActivities(Brain<CapybaraEntity> brain) {
		brain.setTaskList(Activity.CORE, 0, ImmutableList.of(
				new StayAboveWaterTask(0.8f),
				new WalkTask(1.0f),
				new LookAroundTask(45, 90),
				new WanderAroundTask(),
				new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
				new TemptationCooldownTask(MemoryModuleType.GAZE_COOLDOWN_TICKS)));
	}

	private static void addIdleActivities(Brain<CapybaraEntity> brain) {
		brain.setTaskList(Activity.IDLE, ImmutableList.of(
				Pair.of(0, FollowMobWithIntervalTask.follow(EntityType.PLAYER, 6.0f, UniformIntProvider.create(30, 60))),
				Pair.of(1, new BreedTask(AnimalContent.CAPYBARA, 1.0f)),
				Pair.of(2, new TemptTask(entity -> 1.5f)),
				Pair.of(3, TaskTriggerer.runIf(Predicate.not(CapybaraEntity::isStationary), WalkTowardClosestAdultTask.create(WALK_TOWARD_ADULT_RANGE, 1.5f))),
				Pair.of(4, new RandomLookAroundTask(UniformIntProvider.create(150, 250), 30.0f, -50.0f, 10.0f)),
				Pair.of(5, new RandomTask<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableList.of(
						Pair.of(TaskTriggerer.runIf(Predicate.not(CapybaraEntity::isStationary), StrollTask.create(1.0f)), 1),
						Pair.of(TaskTriggerer.runIf(Predicate.not(CapybaraEntity::isStationary), GoTowardsLookTargetTask.create(1.0f, 3)), 1),
						Pair.of(new SleepTask(20), 1),
						Pair.of(new FartTask(10), 1),
						Pair.of(new WaitTask(30, 60), 1)
				)))));
	}

	public static void updateActivities(CapybaraEntity capybara) {
		capybara.getBrain().resetPossibleActivities(ImmutableList.of(Activity.IDLE));
	}

	public static class WalkTask extends net.minecraft.entity.ai.brain.task.WalkTask {
		public WalkTask(float speed) {
			super(speed);
		}

		@Override
		protected void run(ServerWorld world, PathAwareEntity entity, long l) {
			if(entity instanceof CapybaraEntity capybara) {
				capybara.standUp();
			}
			super.run(world, entity, l);
		}
	}

	public static class SleepTask extends MultiTickTask<CapybaraEntity> {
		private final int lastPoseTickDelta;

		public SleepTask(int lastPoseSecondsDelta) {
			super(ImmutableMap.of());
			this.lastPoseTickDelta = lastPoseSecondsDelta * 20;
		}

		@Override
		protected boolean shouldRun(ServerWorld world, CapybaraEntity capybara) {
			return !capybara.isTouchingWater() && capybara.getLastStateTickDelta() >= (long) this.lastPoseTickDelta && !capybara.isLeashed() && capybara.isOnGround() && !capybara.hasPrimaryPassenger();
		}

		@Override
		protected void run(ServerWorld world, CapybaraEntity capybara, long l) {
			if(capybara.canStopSleeping()) {
				capybara.stopSleeping();
			}
			else if(capybara.canFallToSleep()) {
				capybara.fallToSleep();
			}
		}
	}

	public static class FartTask extends MultiTickTask<CapybaraEntity> {
		private final int lastPoseTickDelta;

		public FartTask(int lastPoseSecondsDelta) {
			super(ImmutableMap.of());
			this.lastPoseTickDelta = lastPoseSecondsDelta * 20;
		}

		@Override
		protected boolean shouldRun(ServerWorld world, CapybaraEntity capybara) {
			return capybara.canFart() && !capybara.isTouchingWater() && capybara.getLastStateTickDelta() >= (long) this.lastPoseTickDelta && !capybara.isLeashed() && capybara.isOnGround() && !capybara.hasPrimaryPassenger();
		}

		@Override
		protected void run(ServerWorld world, CapybaraEntity capybara, long l) {
			capybara.fart();
		}
	}
}
