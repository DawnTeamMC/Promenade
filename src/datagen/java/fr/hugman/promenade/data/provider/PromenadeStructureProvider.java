package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.tag.PromenadeBiomeTags;
import fr.hugman.promenade.world.gen.structure.PromenadeStructureKeys;
import fr.hugman.promenade.world.gen.structure.PromenadeStructurePoolsKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;

import java.util.concurrent.CompletableFuture;

public class PromenadeStructureProvider extends FabricDynamicRegistryProvider {
    public PromenadeStructureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.STRUCTURE));
    }

    @Override
    public String getName() {
        return "Structures";
    }

    public static void register(Registerable<Structure> registerable) {
        var biomes = registerable.getRegistryLookup(RegistryKeys.BIOME);
        var templatePools = registerable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        registerable.register(
                PromenadeStructureKeys.DARK_FOREST_WITCH_HUT,
                new JigsawStructure(
                        new Structure.Config.Builder(biomes.getOrThrow(PromenadeBiomeTags.HAS_DARK_FOREST_WITCH_HUT))
                                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                                .terrainAdaptation(StructureTerrainAdaptation.BEARD_THIN)
                                .build(),
                        templatePools.getOrThrow(PromenadeStructurePoolsKeys.DARK_FOREST_WITCH_HUTS),
                        1,
                        ConstantHeightProvider.create(YOffset.fixed(0)),
                        false,
                        Heightmap.Type.WORLD_SURFACE_WG
                )
        );
    }
}