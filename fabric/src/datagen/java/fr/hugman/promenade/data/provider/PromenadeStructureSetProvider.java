package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.world.gen.structure.PromenadeStructureKeys;
import fr.hugman.promenade.world.gen.structure.PromenadeStructureSetKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.concurrent.CompletableFuture;

public class PromenadeStructureSetProvider extends FabricDynamicRegistryProvider {
    public PromenadeStructureSetProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE_SET));
    }

    @Override
    public String getName() {
        return "Structure Sets";
    }

    public static void register(BootstrapContext<StructureSet> registerable) {
        var structures = registerable.lookup(Registries.STRUCTURE);

        registerable.register(
                PromenadeStructureSetKeys.WITCH_HUTS,
                new StructureSet(
                        structures.getOrThrow(PromenadeStructureKeys.DARK_FOREST_WITCH_HUT),
                        new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357620)
                )
        );
    }
}