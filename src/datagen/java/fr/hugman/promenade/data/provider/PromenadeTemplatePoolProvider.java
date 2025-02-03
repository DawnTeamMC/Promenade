package fr.hugman.promenade.data.provider;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.world.gen.structure.PromenadeStructurePoolsKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;

import java.util.concurrent.CompletableFuture;

public class PromenadeTemplatePoolProvider extends FabricDynamicRegistryProvider {
    public PromenadeTemplatePoolProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.TEMPLATE_POOL));
    }

    @Override
    public String getName() {
        return "Template Pools";
    }

    public static void register(Registerable<StructurePool> registerable) {
        var pools = registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
        var empty = pools.getOrThrow(StructurePools.EMPTY);

        registerable.register(PromenadeStructurePoolsKeys.DARK_FOREST_WITCH_HUTS, new StructurePool(empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.ofSingle(Promenade.id("witch_hut").toString()), 1)
                ),
                StructurePool.Projection.RIGID
        ));

        registerable.register(PromenadeStructurePoolsKeys.WITCH_HUT_INTERIORS, new StructurePool(empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.ofSingle(Promenade.id("witch_hut/interior/normal").toString()), 5),
                        Pair.of(StructurePoolElement.ofSingle(Promenade.id("witch_hut/interior/hatred").toString()), 1),
                        Pair.of(StructurePoolElement.ofSingle(Promenade.id("witch_hut/interior/afraid").toString()), 1)
                ),
                StructurePool.Projection.RIGID
        ));
    }

    private static void of(Registerable<StructurePool> registry, String key, StructurePool pool) {
        registry.register(RegistryKey.of(RegistryKeys.TEMPLATE_POOL, Promenade.id(key)), pool);
    }
}