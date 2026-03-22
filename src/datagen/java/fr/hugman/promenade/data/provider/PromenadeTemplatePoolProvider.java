package fr.hugman.promenade.data.provider;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.world.gen.structure.PromenadeStructurePoolsKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import java.util.concurrent.CompletableFuture;

public class PromenadeTemplatePoolProvider extends FabricDynamicRegistryProvider {
    public PromenadeTemplatePoolProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.TEMPLATE_POOL));
    }

    @Override
    public String getName() {
        return "Template Pools";
    }

    public static void register(BootstrapContext<StructureTemplatePool> registerable) {
        var pools = registerable.lookup(Registries.TEMPLATE_POOL);
        var empty = pools.getOrThrow(Pools.EMPTY);

        registerable.register(PromenadeStructurePoolsKeys.DARK_FOREST_WITCH_HUTS, new StructureTemplatePool(empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single(Promenade.id("witch_hut").toString()), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));

        registerable.register(PromenadeStructurePoolsKeys.WITCH_HUT_INTERIORS, new StructureTemplatePool(empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single(Promenade.id("witch_hut/interior/normal").toString()), 5),
                        Pair.of(StructurePoolElement.single(Promenade.id("witch_hut/interior/hatred").toString()), 1),
                        Pair.of(StructurePoolElement.single(Promenade.id("witch_hut/interior/afraid").toString()), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));
    }
}