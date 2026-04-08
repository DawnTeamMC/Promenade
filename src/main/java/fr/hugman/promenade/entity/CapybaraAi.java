package fr.hugman.promenade.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CapybaraAi {
    private static final UniformInt WALK_TOWARD_ADULT_RANGE = UniformInt.of(5, 16);

    protected static List<ActivityData<Capybara>> getActivities() {
        return List.of(initCoreActivity(), initIdleActivity());
    }

    private static ActivityData<Capybara> initCoreActivity() {
        return ActivityData.create(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new Swim<>(0.8f),
                        new AnimalPanic<>(1.0F) {
                            private void run(ServerLevel serverWorld, Capybara capybara, long l) {
                                capybara.forceDefaultState();
                                super.start(serverWorld, capybara, l);
                            }
                        },
                        new LookAtTargetSink(45, 90),
                        new MoveToTargetSink(),
                        new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                        new CountDownCooldownTicks(MemoryModuleType.GAZE_COOLDOWN_TICKS)
                )
        );
    }


    private static ActivityData<Capybara> initIdleActivity() {
        return ActivityData.create(
                Activity.IDLE,
                ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(30, 60))),
                Pair.of(1, new AnimalMakeLove(PromenadeEntityTypes.CAPYBARA)),
                Pair.of(2, new FollowTemptation(_ -> 1.5f)),
                Pair.of(3, BehaviorBuilder.triggerIf(Predicate.not(Capybara::isStationary), BabyFollowAdult.create(WALK_TOWARD_ADULT_RANGE, 1.5f))),
                Pair.of(4, new RandomLookAround(UniformInt.of(150, 250), 30.0f, 0.0f, 10.0f)),
                Pair.of(5, new RunOne<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableList.of(
                        Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Capybara::isStationary), RandomStroll.stroll(1.0f)), 1),
                        Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Capybara::isStationary), SetWalkTargetFromLookTarget.create(1.0f, 3)), 1),
                        Pair.of(new SleepOrWakeUp(20), 1),
                        Pair.of(new Fart(10), 1),
                        Pair.of(new DoNothing(30, 60), 1)
                ))))
        );
    }

    public static void updateActivities(Capybara capybara) {
        capybara.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.IDLE));
    }

    public static Predicate<ItemStack> getTemptItemPredicate() {
        return (stack) -> stack.is(PromenadeItemTags.CAPYBARA_FOOD);
    }

    public static class SleepOrWakeUp extends Behavior<Capybara> {
        private final int lastPoseTickDelta;

        public SleepOrWakeUp(int lastPoseSecondsDelta) {
            super(ImmutableMap.of(
                    MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.IS_TEMPTED, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT));
            this.lastPoseTickDelta = lastPoseSecondsDelta * 20;
        }

        @Override
        protected boolean checkExtraStartConditions(ServerLevel serverLevel, Capybara capybara) {
            return capybara.getLastStateTickDelta() >= this.lastPoseTickDelta &&
                    !capybara.isLeashed() &&
                    capybara.onGround() &&
                    !capybara.hasControllingPassenger();
        }

        @Override
        protected void start(ServerLevel serverLevel, Capybara capybara, long l) {
            if (capybara.canFallToSleep()) {
                capybara.startFallingToSleep();
            } else if (capybara.canWakeUp()) {
                capybara.startWakingUp();
            }
        }
    }

    static class Fart extends Behavior<Capybara> {
        private final int lastPoseTickDelta;

        Fart(int lastPoseSecondsDelta) {
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
        protected boolean checkExtraStartConditions(ServerLevel serverLevel, Capybara capybara) {
            return capybara.getLastStateTickDelta() >= this.lastPoseTickDelta &&
                    !capybara.mayBeLeashed() &&
                    capybara.onGround() &&
                    !capybara.hasControllingPassenger();
        }

        @Override
        protected void start(ServerLevel serverLevel, Capybara capybara, long l) {
            capybara.fart();
        }

        @Override
        protected boolean canStillUse(ServerLevel serverLevel, Capybara livingEntity, long l) {
            return true;
        }
    }
}