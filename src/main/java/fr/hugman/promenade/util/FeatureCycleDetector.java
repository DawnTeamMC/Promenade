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

public final class FeatureCycleDetector
{
    /**
     * A modified version of {@link PlacedFeatureIndexer#collectIndexedFeatures} with several improvements, in order to properly report errors.
     * Added comments, removed vanilla's slow as heck "try this again by removing biomes until it doesn't break" detector and replace
     * with one that is able to track the detected cycle during the DFS.
     * <p>
     * Note that vanilla, for some insane reason, decided to code this to a generic {@code T} parameter, instead of {@code RegistryEntry<Biome>}.
     * This makes it annoyingly difficult to fit into vanilla code, so we just assume that nobody would use this cursed bit of code,
     * unless they are using it to sort biomes.
     *
     * @throws IllegalStateException if a feature was detected.
     */
    public static <T> List<PlacedFeatureIndexer.IndexedFeatures> buildFeaturesPerStep(
            List<RegistryEntry<Biome>> allBiomes,
            Function<RegistryEntry<Biome>, List<RegistryEntryList<PlacedFeature>>> biomeFeatures
    )
    {
        // Maps to establish identity among features and biomes
        // We assign features and biomes ID numbers based on ==, and then create wrapper objects which respect equals() identity
        final Reference2IntMap<PlacedFeature> featureToIntIdMap = new Reference2IntOpenHashMap<>();
        final Reference2IntMap<Biome> biomeToIntIdMap = new Reference2IntOpenHashMap<>();
        final MutableInt nextFeatureId = new MutableInt(0);
        final MutableInt nextBiomeId = new MutableInt(0);

        // Sort by step, then by index
        final Comparator<FeatureData> compareByStepThenByIndex = Comparator.comparingInt(FeatureData::step).thenComparingInt(FeatureData::featureId);
        final Map<FeatureData, Set<FeatureData>> nodesToChildren = new TreeMap<>(compareByStepThenByIndex);

        int maxSteps = 0;

        // Trace of where FeatureData's are found, in reference to biomes, indexes, and steps
        final Map<FeatureData, Map<BiomeData, IntSet>> nodesToTracebacks = new HashMap<>();

        for (var biomeRegistryEntry : allBiomes)
        {
            // Loop through all biomes
            // For each biome, we ultimately compute the maxSteps - the maximum number of generation steps of any biome
            // We then take the features in the biome, and, treating (step, index) as an absolute ordering, add them to a flat list of FeatureData
            // We compute integer IDs for each placed feature as we go through them.
            // At the end, once we've computed this list, we then have that F1, F2, ... Fn, where Fj must come before Fi if j < i
            // So, we represent that as a graph F1 -> F2 -> ... -> Fn

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

                    // Track traceback biomes
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

        // Loop through every known node in the graph (the graph may not be connected, since all we know is it's a series of strings)
        // F1,1 -> F1,2 -> ... F1,n1
        // ...
        // Fm,1 -> Fm,2 -> ... Fm,nm
        // Where some Fi,j may be the same (which would create connected components)
        for (FeatureData node : nodesToChildren.keySet())
        {
            if (!inProgress.isEmpty())
            {
                throw new IllegalStateException("You somehow broke the universe; DFS bork (iteration finished with non-empty in-progress vertex set");
            }

            // In vanilla, the DFS returns true to indicate a cycle exists
            // The rest of this if branch is then just code to try and narrow down the error in an extremely unhelpful way
            if (!nonCyclicalNodes.contains(node) && depthFirstSearch(nodesToChildren, nonCyclicalNodes, inProgress, sortedFeatureData::add, featureCycle::add, node))
            {
                if (featureCycle.size() <= 1)
                {
                    throw new IllegalStateException("There was a feature cycle that involved 0 or 1 feature??");
                }

                // Trim the cycle - the last feature should occur somewhere in the cycle. This is done before reversing, so last = index 0
                final FeatureData loop = featureCycle.getFirst();
                for (int i = 1; i < featureCycle.size(); i++)
                {
                    if (featureCycle.get(i).equals(loop))
                    {
                        // Reset the cycle by trimming off all excess after this index
                        featureCycle = featureCycle.subList(0, i + 1);
                        break;
                    }
                }

                // Reverse the cycle, since we add to it in reverse order
                Collections.reverse(featureCycle);

                // At this point, we have enough information to throw a custom exception, with the biomes and features involved
                throw new IllegalStateException(buildErrorMessage(nodesToTracebacks, featureCycle));
            }
        }

        Collections.reverse(sortedFeatureData);

        // This is the intended result: A list just of features to be applied at each given generation step
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
        // Called initially with:
        // nonCyclicalNodes = { ... } does not contain start
        // reachable = {}
        // start = ?

        // Returning true must (somehow) indicate the presence of a cycle
        // nonCyclicalNodes = the nodes that we've already seen, and verified that they have no cycles
        // reachable = an empty set (when this is first called), representing all edges that are reachable.
        if (nonCyclicalNodes.contains(start))
        {
            // The DFS has already seen the current node, so return false
            return false;
        }
        else if (pathSet.contains(start))
        {
            // If we have navigated back to an element that we haven't seen (and so verified that no cycles from that node exist)
            // But, we know it's in the path set, then there's a cycle!
            // We then push these through the consumer in reverse order, as any true return value will bubble up
            onCyclicalNodeFound.accept(start);
            return true;
        }
        else
        {
            pathSet.add(start); // This node is now in the path set (we consider it as a node that may have a cycle, and want to mark if we can reach back to it)
            for (FeatureData next : edges.getOrDefault(start, ImmutableSet.of()))
            {
                if (depthFirstSearch(edges, nonCyclicalNodes, pathSet, onNonCyclicalNodeFound, onCyclicalNodeFound, next))
                {
                    // A cycle was found, so we exit here.
                    // We append the cycle node found in verse order.
                    onCyclicalNodeFound.accept(start);
                    return true;
                }
            }

            // At this point: we cannot find a way back into the path set, considering 'start' as a stepping stone node. (Since above, recursively, we've DFS'd all the way that we can go from this node.)
            // As a result, we can
            // 1. mark this as not in the path set
            // 2. mark this node as already seen, and valid
            // And then return false (which exits this recursive call of the DFS)
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
                // Check if the features have a relative ordering from prev -> current, in that biome
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

    /**
     * @param featureId An integer ID mapping for the feature, NOT the index of the feature within the step
     * @param step The step index the feature was found in.
     * @param feature The placed feature itself
     */
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

    /**
     * @param biomeId An integer ID mapping for the biome
     * @param biome The biome itself
     */
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