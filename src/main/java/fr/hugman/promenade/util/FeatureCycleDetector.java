package fr.hugman.promenade.util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.Hash;
import org.apache.commons.lang3.mutable.MutableInt;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.*;

/**
 * Utility class for debugging feature cycles. Imported from cyanide.
 * TODO: remove this class before release
 */
public final class FeatureCycleDetector
{
    public static <T> List<PlacedFeatureIndexer.IndexedFeatures> buildFeaturesPerStep(
            List<RegistryEntry<Biome>> allBiomes,
            Function<RegistryEntry<Biome>, List<RegistryEntryList<PlacedFeature>>> biomeFeatures
    )
    {
        final Reference2IntMap<PlacedFeature> featureToIntIdMap = new Reference2IntOpenHashMap<>();
        final Reference2IntMap<Biome> biomeToIntIdMap = new Reference2IntOpenHashMap<>();
        final MutableInt nextFeatureId = new MutableInt(0);
        final MutableInt nextBiomeId = new MutableInt(0);

        final Comparator<FeatureData> compareByStepThenByIndex = Comparator.comparingInt(FeatureData::step).thenComparingInt(FeatureData::featureId);
        final Map<FeatureData, Set<FeatureData>> nodesToChildren = new TreeMap<>(compareByStepThenByIndex);

        int maxSteps = 0;

        final Map<FeatureData, Map<BiomeData, IntSet>> nodesToTracebacks = new HashMap<>();

        for (var biomeRegistryEntry : allBiomes)
        {
            final Biome biome = biomeRegistryEntry.value();
            final List<FeatureData> flatDataList = new ArrayList<>();
            final List<RegistryEntryList<PlacedFeature>> features = biomeFeatures.apply(biomeRegistryEntry);

            maxSteps = Math.max(maxSteps, features.size());

            for (int stepIndex = 0; stepIndex < features.size(); ++stepIndex)
            {
                int biomeIndex = 0;
                for (RegistryEntry<PlacedFeature> featureRegistryEntry : features.get(stepIndex))
                {
                    final PlacedFeature feature = featureRegistryEntry.value();
                    final FeatureData featureIdentity = new FeatureData(idFor(feature, featureToIntIdMap, nextFeatureId), stepIndex, feature, featureRegistryEntry);
                    flatDataList.add(featureIdentity);

                    final BiomeData biomeIdentity = new BiomeData(idFor(biome, biomeToIntIdMap, nextBiomeId), biome, biomeRegistryEntry);

                    nodesToTracebacks
                        .computeIfAbsent(featureIdentity, key -> new HashMap<>(1))
                        .computeIfAbsent(biomeIdentity, key -> new IntOpenHashSet())
                        .add(biomeIndex);
                    biomeIndex++;
                }
            }

            for (int featureIndex = 0; featureIndex < flatDataList.size(); ++featureIndex)
            {
                final Set<FeatureData> children = nodesToChildren.computeIfAbsent(flatDataList.get(featureIndex), key -> new TreeSet<>(compareByStepThenByIndex));
                if (featureIndex < flatDataList.size() - 1)
                {
                    children.add(flatDataList.get(featureIndex + 1));
                }
            }
        }

        final Set<FeatureData> nonCyclicalNodes = new TreeSet<>(compareByStepThenByIndex);
        final Set<FeatureData> inProgress = new TreeSet<>(compareByStepThenByIndex);
        final List<FeatureData> sortedFeatureData = new ArrayList<>();
        List<FeatureData> featureCycle = new ArrayList<>();

        for (FeatureData node : nodesToChildren.keySet())
        {
            if (!inProgress.isEmpty())
            {
                throw new IllegalStateException("You somehow broke the universe; DFS bork (iteration finished with non-empty in-progress vertex set");
            }

            if (!nonCyclicalNodes.contains(node) && depthFirstSearch(nodesToChildren, nonCyclicalNodes, inProgress, sortedFeatureData::add, featureCycle::add, node))
            {
                if (featureCycle.size() <= 1)
                {
                    throw new IllegalStateException("There was a feature cycle that involved 0 or 1 feature??");
                }

                final FeatureData loop = featureCycle.getFirst();
                for (int i = 1; i < featureCycle.size(); i++)
                {
                    if (featureCycle.get(i).equals(loop))
                    {
                        featureCycle = featureCycle.subList(0, i + 1);
                        break;
                    }
                }

                Collections.reverse(featureCycle);

                throw new IllegalStateException(buildErrorMessage(nodesToTracebacks, featureCycle));
            }
        }

        Collections.reverse(sortedFeatureData);

        final ImmutableList.Builder<PlacedFeatureIndexer.IndexedFeatures> featuresPerStepData = ImmutableList.builder();

        for (int stepIndex = 0; stepIndex < maxSteps; ++stepIndex)
        {
            final int finalStepIndex = stepIndex;
            final List<PlacedFeature> featuresAtStep = sortedFeatureData.stream()
                .filter(arg -> arg.step() == finalStepIndex)
                .map(FeatureData::feature)
                .toList();

            final int totalIndex = featuresAtStep.size();
            final Object2IntMap<PlacedFeature> featureToIndexMapping = new Object2IntOpenCustomHashMap<>(totalIndex, new Hash.Strategy<>() {
                @Override
                public int hashCode(PlacedFeature o)
                {
                    return System.identityHashCode(o);
                }

                @Override
                public boolean equals(PlacedFeature a, PlacedFeature b)
                {
                    return a == b;
                }
            });
            for (int index = 0; index < totalIndex; ++index)
            {
                featureToIndexMapping.put(featuresAtStep.get(index), index);
            }

            featuresPerStepData.add(new PlacedFeatureIndexer.IndexedFeatures(featuresAtStep, featureToIndexMapping));
        }

        return featuresPerStepData.build();
    }

    private static boolean depthFirstSearch(Map<FeatureData, Set<FeatureData>> edges, Set<FeatureData> nonCyclicalNodes, Set<FeatureData> pathSet, Consumer<FeatureData> onNonCyclicalNodeFound, Consumer<FeatureData> onCyclicalNodeFound, FeatureData start)
    {
        if (nonCyclicalNodes.contains(start))
        {
            return false;
        }
        else if (pathSet.contains(start))
        {
            onCyclicalNodeFound.accept(start);
            return true;
        }
        else
        {
            pathSet.add(start);
            for (FeatureData next : edges.getOrDefault(start, ImmutableSet.of()))
            {
                if (depthFirstSearch(edges, nonCyclicalNodes, pathSet, onNonCyclicalNodeFound, onCyclicalNodeFound, next))
                {
                    onCyclicalNodeFound.accept(start);
                    return true;
                }
            }

            pathSet.remove(start);
            nonCyclicalNodes.add(start);
            onNonCyclicalNodeFound.accept(start);
            return false;
        }
    }

    private static <T> int idFor(T object, Reference2IntMap<T> objectToIntIdMap, MutableInt nextId)
    {
        return objectToIntIdMap.computeIfAbsent(object, key -> nextId.getAndIncrement());
    }

    private static String buildErrorMessage(Map<FeatureData, Map<BiomeData, IntSet>> tracebacks, List<FeatureData> cycle)
    {
        final StringBuilder error = new StringBuilder("""
                A feature cycle was found.

                Cycle:
                """);

        final ListIterator<FeatureData> iterator = cycle.listIterator();
        final FeatureData start = iterator.next();
        Map<BiomeData, IntSet> prevTracebacks = tracebacks.get(start);
        error.append("At step ")
            .append(start.step)
            .append('\n')
            .append("Feature '")
            .append(start.name())
            .append("'\n");

        while (iterator.hasNext())
        {
            final FeatureData current = iterator.next();
            final Map<BiomeData, IntSet> currentTracebacks = tracebacks.get(current);
            int found = 0;
            for (BiomeData biome : Sets.intersection(prevTracebacks.keySet(), currentTracebacks.keySet()))
            {
                final int prevTb = prevTracebacks.get(biome).intStream().min().orElseThrow();
                final int currTb = currentTracebacks.get(biome).intStream().max().orElseThrow();
                if (prevTb < currTb)
                {
                    if (found == 0)
                    {
                        error.append("  must be before '")
                            .append(current.name())
                            .append("' (defined in '")
                            .append(biome.name())
                            .append("' at index ")
                            .append(prevTb)
                            .append(", ")
                            .append(currTb);
                    }
                    found++;
                }
            }
            if (found > 1)
            {
                error.append(" and ")
                    .append(found - 1)
                    .append(" others)\n");
            }
            else if (found > 0)
            {
                error.append(")\n");
            }

            prevTracebacks = currentTracebacks;
        }
        return error.toString();
    }

    record FeatureData(int featureId, int step, PlacedFeature feature, RegistryEntry<PlacedFeature> source)
    {
        String name()
        {
            return source.getKeyOrValue().map(
                e -> e.getValue().toString(),
                e -> "[Inline feature: " + feature + "]"
            );
        }
    }

    record BiomeData(int biomeId, Biome biome, RegistryEntry<Biome> source)
    {
        String name()
        {
            return source.getKeyOrValue().map(
                e -> e.getValue().toString(),
                e -> "[Inline biome: " + biome + "]"
            );
        }
    }
}