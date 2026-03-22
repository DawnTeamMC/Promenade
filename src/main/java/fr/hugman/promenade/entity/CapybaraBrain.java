package fr.hugman.promenade.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.entity.ai.brain.sensor.PromenadeSensorTypes;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.ItemStack;
import java.util.Map;
import java.util.function.Predicate;

public class CapybaraBrain {
    private static final UniformInt WALK_TOWARD_ADULT_RANGE = UniformInt.of(5, 16);

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
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.GAZE_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.NEAREST_VISIBLE_ADULT,
            PromenadeMemoryModuleTypes.FART_COOLDOWN
    );

    public static Brain.Provider<CapybaraEntity> createProfile() {
        return Brain.provider(MEMORY_MODULES, SENSORS);
    }

    protected static Brain<?> create(Brain<CapybaraEntity> brain) {
        CapybaraBrain.addCoreActivities(brain);
        CapybaraBrain.addIdleActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void addCoreActivities(Brain<CapybaraEntity> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim<>(0.8f),
                new AnimalPanic<>(1.0F) {
                    private void run(ServerLevel serverWorld, CapybaraEntity capybaraEntity, long l) {
                        capybaraEntity.forceDefaultState();
                        super.start(serverWorld, capybaraEntity, l);
                    }
                },
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new CountDownCooldownTicks(MemoryModuleType.GAZE_COOLDOWN_TICKS)));
    }

    private static void addIdleActivities(Brain<CapybaraEntity> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(30, 60))),
                Pair.of(1, new AnimalMakeLove(PromenadeEntityTypes.CAPYBARA)),
                Pair.of(2, new FollowTemptation(entity -> 1.5f)),
                Pair.of(3, BehaviorBuilder.triggerIf(Predicate.not(CapybaraEntity::isStationary), BabyFollowAdult.create(WALK_TOWARD_ADULT_RANGE, 1.5f))),
                Pair.of(4, new RandomLookAround(UniformInt.of(150, 250), 30.0f, 0.0f, 10.0f)),
                Pair.of(5, new RunOne<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableList.of(
                        Pair.of(BehaviorBuilder.triggerIf(Predicate.not(CapybaraEntity::isStationary), RandomStroll.stroll(1.0f)), 1),
                        Pair.of(BehaviorBuilder.triggerIf(Predicate.not(CapybaraEntity::isStationary), SetWalkTargetFromLookTarget.create(1.0f, 3)), 1),
                        Pair.of(new SleepOrWakeUpTask(20), 1),
                        Pair.of(new FartTask(10), 1),
                        Pair.of(new DoNothing(30, 60), 1)
                )))));
    }

    public static void updateActivities(CapybaraEntity capybara) {
        capybara.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.IDLE));
    }

    public static Predicate<ItemStack> getTemptItemPredicate() {
        return (stack) -> stack.is(PromenadeItemTags.CAPYBARA_FOOD);
    }

    public static class SleepOrWakeUpTask extends Behavior<CapybaraEntity> {
        private final int lastPoseTickDelta;

        public SleepOrWakeUpTask(int lastPoseSecondsDelta) {
            super(ImmutableMap.of(
                    MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.IS_TEMPTED, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT));
            this.lastPoseTickDelta = lastPoseSecondsDelta * 20;
        }

        @Override
        protected boolean checkExtraStartConditions(ServerLevel serverLevel, CapybaraEntity capybara) {
            return capybara.getLastStateTickDelta() >= this.lastPoseTickDelta &&
                    !capybara.isLeashed() &&
                    capybara.onGround() &&
                    !capybara.hasControllingPassenger();
        }

        @Override
        protected void start(ServerLevel serverLevel, CapybaraEntity capybara, long l) {
            if (capybara.canFallToSleep()) {
                capybara.startFallingToSleep();
            } else if (capybara.canWakeUp()) {
                capybara.startWakingUp();
            }
        }
    }

    static class FartTask extends Behavior<CapybaraEntity> {
        private final int lastPoseTickDelta;

        FartTask(int lastPoseSecondsDelta) {
            super(Map.of(
                    MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.IS_TEMPTED, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT,
                    PromenadeMemoryModuleTypes.FART_COOLDOWN, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT
            ));
            this.lastPoseTickDelta = lastPoseSecondsDelta * 20;
        }

        @Override
        protected boolean checkExtraStartConditions(ServerLevel serverLevel, CapybaraEntity capybara) {
            return capybara.getLastStateTickDelta() >= this.lastPoseTickDelta &&
                    !capybara.mayBeLeashed() &&
                    capybara.onGround() &&
                    !capybara.hasControllingPassenger();
        }

        @Override
        protected void start(ServerLevel serverLevel, CapybaraEntity capybara, long l) {
            capybara.fart();
        }

        @Override
        protected boolean canStillUse(ServerLevel serverLevel, CapybaraEntity livingEntity, long l) {
            return true;
        }
    }
}