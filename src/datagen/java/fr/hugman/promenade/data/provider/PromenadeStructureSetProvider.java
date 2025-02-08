package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.world.gen.structure.PromenadeStructureKeys;
import fr.hugman.promenade.world.gen.structure.PromenadeStructureSetKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;

import java.util.concurrent.CompletableFuture;

public class PromenadeStructureSetProvider extends FabricDynamicRegistryProvider {
    public PromenadeStructureSetProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.STRUCTURE_SET));
    }

    @Override
    public String getName() {
        return "Structure Sets";
    }

    public static void register(Registerable<StructureSet> registerable) {
        var structures = registerable.getRegistryLookup(RegistryKeys.STRUCTURE);

        registerable.register(
                PromenadeStructureSetKeys.WITCH_HUTS,
                new StructureSet(
                        structures.getOrThrow(PromenadeStructureKeys.DARK_FOREST_WITCH_HUT),
                        new RandomSpreadStructurePlacement(32, 8, SpreadType.LINEAR, 14357620)
                )
        );
    }
}