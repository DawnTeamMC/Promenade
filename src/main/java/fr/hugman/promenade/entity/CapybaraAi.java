package fr.hugman.promenade.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.entity.ai.brain.PromenadeMemoryModuleTypes;
import fr.hugman.promenade.tag.PromenadeItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class CapybaraAi {
    private static final UniformInt WALK_TOWARD_ADULT_RANGE = UniformInt.of(5, 16);

    // How long a capybara enjoys the water before heading back to land
    private static final UniformInt SWIM_TIME = UniformInt.of(20 * 20, 60 * 20);
    // How long a capybara waits after a swim before voluntarily going back to the water
    private static final UniformInt SWIM_COOLDOWN = UniformInt.of(120 * 20, 240 * 20);
    // How long a capybara waits before looking for water again when none was found
    private static final UniformInt SWIM_RETRY_COOLDOWN = UniformInt.of(10 * 20, 20 * 20);

    protected static List<ActivityData<Capybara>> getActivities() {
        return List.of(initCoreActivity(), initIdleActivity(), initSwimActivity());
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
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityTypes.PLAYER, 6.0f, UniformInt.of(30, 60))),
                Pair.of(1, new AnimalMakeLove(PromenadeEntityTypes.CAPYBARA)),
                Pair.of(2, new FollowTemptation(_ -> 1.5f)),
                Pair.of(3, BehaviorBuilder.triggerIf(Predicate.not(Capybara::isStationary), BabyFollowAdult.create(WALK_TOWARD_ADULT_RANGE, 1.5f))),
                Pair.of(4, new RandomLookAround(UniformInt.of(150, 250), 30.0f, 0.0f, 10.0f)),
                Pair.of(5, new RunOne<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableList.of(
                        Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Capybara::isStationary), RandomStroll.stroll(1.0f)), 1),
                        Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Capybara::isStationary), SetWalkTargetFromLookTarget.create(1.0f, 3)), 1),
                        Pair.of(new SleepOrWakeUp(20), 1),
                        Pair.of(new Fart(10), 1),
                        Pair.of(new GoSwimming(10, 1.0f), 1),
                        Pair.of(new DoNothing(30, 60), 1)
                )))),
                ImmutableSet.of(Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT))
        );
    }

    private static ActivityData<Capybara> initSwimActivity() {
        return ActivityData.create(
                Activity.SWIM,
                ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityTypes.PLAYER, 6.0f, UniformInt.of(30, 60))),
                Pair.of(1, new FollowTemptation(_ -> 1.5f)),
                Pair.of(2, BabyFollowAdult.create(WALK_TOWARD_ADULT_RANGE, 1.5f)),
                Pair.of(3, leaveWater(12, 1.0f)),
                Pair.of(4, new RunOne<>(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), ImmutableList.of(
                        Pair.of(swimAround(1.0f), 3),
                        Pair.of(SetWalkTargetFromLookTarget.create(1.0f, 3), 1),
                        Pair.of(new DoNothing(30, 60), 1)
                )))),
                ImmutableSet.of(Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_PRESENT))
        );
    }

    public static void updateActivities(Capybara capybara) {
        Brain<?> brain = capybara.getBrain();
        boolean wasSwimming = brain.isActive(Activity.SWIM);
        brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.SWIM, Activity.IDLE));
        if (!wasSwimming && brain.isActive(Activity.SWIM)) {
            onEnterWater(capybara);
        }
    }

    private static void onEnterWater(Capybara capybara) {
        if (!capybara.isStanding()) {
            capybara.forceDefaultState();
        }
        // A capybara that did not choose to go swimming (pushed, fell, followed its mother...)
        // still enjoys a swim, unless it just had one, in which case it heads straight back to land
        Brain<?> brain = capybara.getBrain();
        if (!brain.hasMemoryValue(PromenadeMemoryModuleTypes.SWIM_COOLDOWN)) {
            startSwimCycle(capybara);
        }
    }

    private static void startSwimCycle(Capybara capybara) {
        Brain<?> brain = capybara.getBrain();
        brain.setMemoryWithExpiry(PromenadeMemoryModuleTypes.SWIM_TIME, Unit.INSTANCE, SWIM_TIME.sample(capybara.getRandom()));
        brain.setMemoryWithExpiry(PromenadeMemoryModuleTypes.SWIM_COOLDOWN, Unit.INSTANCE, SWIM_COOLDOWN.sample(capybara.getRandom()));
    }

    public static Predicate<ItemStack> getTemptItemPredicate() {
        return (stack) -> stack.is(PromenadeItemTags.CAPYBARA_FOOD);
    }

    /**
     * Wanders around at the surface of the water.
     */
    private static OneShot<Capybara> swimAround(float speedModifier) {
        return BehaviorBuilder.create(i -> i.group(i.absent(MemoryModuleType.WALK_TARGET)).apply(i, walkTarget -> (_, capybara, _) -> {
            if (!capybara.isInWater()) {
                return false;
            }
            Optional<Vec3> target = Optional.ofNullable(getWaterSurfacePos(capybara, 8, 3));
            walkTarget.setOrErase(target.map(pos -> new WalkTarget(pos, speedModifier, 0)));
            return true;
        }));
    }

    /**
     * Heads back to land once the capybara has had enough of swimming.
     * If no land can be found nearby, keeps swimming around until it finds some.
     */
    private static OneShot<Capybara> leaveWater(int range, float speedModifier) {
        return BehaviorBuilder.create(i -> i.group(
                i.absent(MemoryModuleType.WALK_TARGET),
                i.present(MemoryModuleType.IS_IN_WATER),
                i.absent(PromenadeMemoryModuleTypes.SWIM_TIME),
                i.registered(MemoryModuleType.LOOK_TARGET)
        ).apply(i, (walkTarget, _, _, lookTarget) -> (level, capybara, _) -> {
            Vec3 target = findLand(level, capybara, range);
            if (target == null) {
                target = LandRandomPos.getPos(capybara, range + 4, 7);
            }
            if (target == null) {
                target = getWaterSurfacePos(capybara, range, 3);
            }
            if (target == null) {
                return false;
            }
            lookTarget.set(new BlockPosTracker(target));
            walkTarget.set(new WalkTarget(target, speedModifier, 0));
            return true;
        }));
    }

    @Nullable
    private static Vec3 findLand(ServerLevel level, Capybara capybara, int range) {
        BlockPos origin = capybara.blockPosition();
        BlockPos.MutableBlockPos belowPos = new BlockPos.MutableBlockPos();
        for (BlockPos pos : BlockPos.withinBoxByManhattanDistance(origin, range, 4, range)) {
            if (pos.getX() == origin.getX() && pos.getZ() == origin.getZ()) continue;
            if (!level.getFluidState(pos).isEmpty()) continue;
            if (!level.getBlockState(pos).getCollisionShape(level, pos).isEmpty()) continue;
            if (!level.getBlockState(pos.above()).getCollisionShape(level, pos.above()).isEmpty()) continue;
            belowPos.setWithOffset(pos, Direction.DOWN);
            if (!level.getFluidState(belowPos).isEmpty()) continue;
            if (level.getBlockState(belowPos).isFaceSturdy(level, belowPos, Direction.UP)) {
                return Vec3.atBottomCenterOf(pos);
            }
        }
        return null;
    }

    /**
     * Finds the closest water block where a capybara can float, preferring spots that are not on the shore.
     */
    @Nullable
    private static BlockPos findWaterSurface(ServerLevel level, Capybara capybara, int range) {
        BlockPos origin = capybara.blockPosition();
        BlockPos shore = null;
        for (BlockPos pos : BlockPos.withinBoxByManhattanDistance(origin, range, 3, range)) {
            if (!isWaterSurface(level, pos)) continue;
            if (Direction.Plane.HORIZONTAL.stream().allMatch(direction -> level.getFluidState(pos.relative(direction)).is(FluidTags.WATER))) {
                return pos.immutable();
            }
            if (shore == null) {
                shore = pos.immutable();
            }
        }
        return shore;
    }

    /**
     * Picks a random position at the surface of the water, so that the capybara stays afloat.
     */
    @Nullable
    private static Vec3 getWaterSurfacePos(Capybara capybara, int horizontalRange, int verticalRange) {
        Level level = capybara.level();
        RandomSource random = capybara.getRandom();
        BlockPos origin = capybara.blockPosition();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int attempt = 0; attempt < 10; attempt++) {
            int x = origin.getX() + random.nextInt(2 * horizontalRange + 1) - horizontalRange;
            int z = origin.getZ() + random.nextInt(2 * horizontalRange + 1) - horizontalRange;
            for (int y = origin.getY() + verticalRange; y >= origin.getY() - verticalRange; y--) {
                if (isWaterSurface(level, pos.set(x, y, z))) {
                    return Vec3.atBottomCenterOf(pos);
                }
            }
        }
        return null;
    }

    private static boolean isWaterSurface(Level level, BlockPos pos) {
        return level.getFluidState(pos).is(FluidTags.WATER) && level.getBlockState(pos.above()).isAir();
    }

    /**
     * From time to time, walks to some nearby water to go for a swim.
     */
    public static class GoSwimming extends Behavior<Capybara> {
        private final int range;
        private final float speedModifier;

        public GoSwimming(int range, float speedModifier) {
            super(ImmutableMap.of(
                    MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED,
                    MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.TEMPTING_PLAYER, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT,
                    PromenadeMemoryModuleTypes.SWIM_COOLDOWN, MemoryStatus.VALUE_ABSENT,
                    PromenadeMemoryModuleTypes.SWIM_TIME, MemoryStatus.REGISTERED
            ));
            this.range = range;
            this.speedModifier = speedModifier;
        }

        @Override
        protected boolean checkExtraStartConditions(ServerLevel serverLevel, Capybara capybara) {
            return !capybara.isStationary() &&
                    !capybara.isLeashed() &&
                    capybara.onGround() &&
                    !capybara.hasControllingPassenger();
        }

        @Override
        protected void start(ServerLevel serverLevel, Capybara capybara, long l) {
            BlockPos water = findWaterSurface(serverLevel, capybara, this.range);
            if (water == null) {
                capybara.getBrain().setMemoryWithExpiry(PromenadeMemoryModuleTypes.SWIM_COOLDOWN, Unit.INSTANCE, SWIM_RETRY_COOLDOWN.sample(capybara.getRandom()));
                return;
            }
            startSwimCycle(capybara);
            capybara.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, new BlockPosTracker(water));
            capybara.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(water, this.speedModifier, 0));
        }
    }

    public static class SleepOrWakeUp extends Behavior<Capybara> {
        private final int lastPoseTickDelta;

        public SleepOrWakeUp(int lastPoseSecondsDelta) {
            super(ImmutableMap.of(
                    MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT,
                    MemoryModuleType.TEMPTING_PLAYER, MemoryStatus.VALUE_ABSENT,
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
                    MemoryModuleType.TEMPTING_PLAYER, MemoryStatus.VALUE_ABSENT,
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