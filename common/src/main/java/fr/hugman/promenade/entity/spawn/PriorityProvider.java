package fr.hugman.promenade.entity.spawn;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Something that can be picked among others, depending on the priority of the conditions it matches.
 * Mirrors the class of the same name in newer versions of Minecraft.
 */
public interface PriorityProvider<Context, Condition extends PriorityProvider.SelectorCondition<Context>> {
    List<Selector<Context, Condition>> selectors();

    /**
     * @return the entries whose conditions match, among the ones with the highest priority
     */
    @SuppressWarnings("unchecked")
    static <C, T> Stream<T> select(Stream<T> entries, Function<T, PriorityProvider<C, ?>> extractor, C context) {
        List<UnpackedEntry<C, T>> unpackedEntries = new ArrayList<>();
        entries.forEach(entry -> {
            PriorityProvider<C, ?> provider = extractor.apply(entry);
            for (Selector<C, ?> selector : provider.selectors()) {
                unpackedEntries.add(new UnpackedEntry<>(
                        entry,
                        selector.priority(),
                        DataFixUtils.orElseGet((Optional<? extends SelectorCondition<C>>) selector.condition(), SelectorCondition::alwaysTrue)
                ));
            }
        });
        unpackedEntries.sort(UnpackedEntry.HIGHEST_PRIORITY_FIRST);
        Iterator<UnpackedEntry<C, T>> iterator = unpackedEntries.iterator();
        int highestMatchedPriority = Integer.MIN_VALUE;

        while (iterator.hasNext()) {
            UnpackedEntry<C, T> entry = iterator.next();
            if (entry.priority < highestMatchedPriority) {
                iterator.remove();
            } else if (entry.condition.test(context)) {
                highestMatchedPriority = entry.priority;
            } else {
                iterator.remove();
            }
        }

        return unpackedEntries.stream().map(UnpackedEntry::entry);
    }

    static <C, T> Optional<T> pick(Stream<T> entries, Function<T, PriorityProvider<C, ?>> extractor, RandomSource random, C context) {
        List<T> selected = select(entries, extractor, context).toList();
        return Util.getRandomSafe(selected, random);
    }

    static <Context, Condition extends SelectorCondition<Context>> List<Selector<Context, Condition>> single(Condition check, int priority) {
        return List.of(new Selector<>(check, priority));
    }

    static <Context, Condition extends SelectorCondition<Context>> List<Selector<Context, Condition>> alwaysTrue(int priority) {
        return List.of(new Selector<>(Optional.empty(), priority));
    }

    record Selector<Context, Condition extends SelectorCondition<Context>>(Optional<Condition> condition, int priority) {
        public Selector(Condition condition, int priority) {
            this(Optional.of(condition), priority);
        }

        public Selector(int priority) {
            this(Optional.empty(), priority);
        }

        public static <Context, Condition extends SelectorCondition<Context>> Codec<Selector<Context, Condition>> codec(Codec<Condition> conditionCodec) {
            return RecordCodecBuilder.create(instance -> instance.group(
                    conditionCodec.optionalFieldOf("condition").forGetter(Selector::condition),
                    Codec.INT.fieldOf("priority").forGetter(Selector::priority)
            ).apply(instance, Selector::new));
        }
    }

    @FunctionalInterface
    interface SelectorCondition<C> extends Predicate<C> {
        static <C> SelectorCondition<C> alwaysTrue() {
            return context -> true;
        }
    }

    record UnpackedEntry<C, T>(T entry, int priority, SelectorCondition<C> condition) {
        public static final Comparator<UnpackedEntry<?, ?>> HIGHEST_PRIORITY_FIRST = Comparator.<UnpackedEntry<?, ?>>comparingInt(UnpackedEntry::priority).reversed();
    }
}
