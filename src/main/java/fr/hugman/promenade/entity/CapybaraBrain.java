package fr.hugman.promenade.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.Map;
import java.util.function.Predicate;

public class CapybaraBrain {
    private static final UniformIntProvider WALK_TOWARD_ADULT_RANGE = UniformIntProvider.create(5, 16);

    private static final ImmutableList<SensorType<? extends Sensor<? super CapybaraEntity>>> SENSORS = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            PromenadeSensorTypes.CAPYBARA_TEMPTATIONS,
            SensorType.NEAREST_ADULT,
            SensorType.IS_IN_WATER
    );

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
            MemoryModuleType.NEAREST_VISIBLE_ADULT,
            PromenadeMemoryModuleTypes.FART_COOLDOWN
    );

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
                new StayAboveWaterTask<>(0.8f),
                new net.minecraft.entity.ai.brain.task.FleeTask<>(1.0F) {
                    protected void run(ServerWorld serverWorld, CapybaraEntity capybaraEntity, long l) {
                        capybaraEntity.startState(CapybaraEntity.State.IDLING);
                        // TODO: stop farting, sleeping...
                        super.run(serverWorld, capybaraEntity, l);
                    }
                },
                new UpdateLookControlTask(45, 90),
                new MoveToTargetTask(),
                new TickCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new TickCooldownTask(MemoryModuleType.GAZE_COOLDOWN_TICKS)));
    }

    private static void addIdleActivities(Brain<CapybaraEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(
                Pair.of(0, LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0f, UniformIntProvider.create(30, 60))),
                Pair.of(1, new BreedTask(PromenadeEntityTypes.CAPYBARA)),
                Pair.of(2, new TemptTask(entity -> 1.5f)),
                Pair.of(3, TaskTriggerer.runIf(Predicate.not(CapybaraEntity::isStationary), WalkTowardsClosestAdultTask.create(WALK_TOWARD_ADULT_RANGE, 1.5f))),
                Pair.of(4, new LookAroundTask(UniformIntProvider.create(150, 250), 30.0f, -50.0f, 10.0f)),
                Pair.of(5, new RandomTask<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT), ImmutableList.of(
                        Pair.of(TaskTriggerer.runIf(Predicate.not(CapybaraEntity::isStationary), StrollTask.create(1.0f)), 1),
                        Pair.of(TaskTriggerer.runIf(Predicate.not(CapybaraEntity::isStationary), GoToLookTargetTask.create(1.0f, 3)), 1),
                        Pair.of(new SleepTask(20), 1),
                        Pair.of(new FartTask(40, 80), 1),
                        Pair.of(new WaitTask(30, 60), 1)
                )))));
    }

    public static void updateActivities(CapybaraEntity capybara) {
        capybara.getBrain().resetPossibleActivities(ImmutableList.of(Activity.IDLE));
    }

    public static Predicate<ItemStack> getTemptItemPredicate() {
        return (stack) -> stack.isIn(PromenadeItemTags.CAPYBARA_FOOD);
    }

    public static class SleepTask extends MultiTickTask<CapybaraEntity> {
        private final int lastPoseTickDelta;

        public SleepTask(int lastPoseSecondsDelta) {
            super(ImmutableMap.of());
            this.lastPoseTickDelta = lastPoseSecondsDelta * 20;
        }

        @Override
        protected boolean shouldRun(ServerWorld world, CapybaraEntity capybara) {
            return !capybara.isTouchingWater() && capybara.getLastStateTickDelta() >= (long) this.lastPoseTickDelta && !capybara.isLeashed() && capybara.isOnGround() && !capybara.hasControllingPassenger();
        }

        @Override
        protected void run(ServerWorld world, CapybaraEntity capybara, long l) {
            if (capybara.canStopSleeping()) {
                capybara.startState(CapybaraEntity.State.WAKE_UP);
            } else if (capybara.canFallToSleep()) {
                capybara.startState(CapybaraEntity.State.FALL_TO_SLEEP);
            }
        }
    }

    static class FartTask extends MultiTickTask<CapybaraEntity> {
        FartTask(int minRunTime, int maxRunTime) {
            super(
                    Map.of(
                            MemoryModuleType.IS_PANICKING, MemoryModuleState.VALUE_ABSENT,
                            MemoryModuleType.IS_TEMPTED, MemoryModuleState.VALUE_ABSENT,
                            MemoryModuleType.IS_IN_WATER, MemoryModuleState.VALUE_ABSENT,
                            // TODO: add capybara falling sleeping check
                            // TODO: add capybara sleeping check
                            // TODO: add capybara waking up check
                            PromenadeMemoryModuleTypes.FART_COOLDOWN, MemoryModuleState.VALUE_ABSENT,
                            MemoryModuleType.BREED_TARGET, MemoryModuleState.VALUE_ABSENT
                    ),
                    minRunTime,
                    maxRunTime
            );
        }

        protected boolean shouldRun(ServerWorld serverWorld, CapybaraEntity capybara) {
            return !capybara.mightBeLeashed() && capybara.isOnGround() && !capybara.hasControllingPassenger();
        }

        protected boolean shouldKeepRunning(ServerWorld serverWorld, CapybaraEntity capybara, long l) {
            return true;
        }

        protected void run(ServerWorld serverWorld, CapybaraEntity capybara, long l) {
            capybara.startState(CapybaraEntity.State.FARTING);
        }

        protected void finishRunning(ServerWorld serverWorld, CapybaraEntity capybara, long l) {
            capybara.startState(CapybaraEntity.State.IDLING);
            capybara.getBrain().remember(PromenadeMemoryModuleTypes.FART_COOLDOWN, Unit.INSTANCE, 9600L);
        }
    }
}