package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.tag.PromenadeBiomeTags;
import fr.hugman.promenade.world.gen.structure.PromenadeStructureKeys;
import fr.hugman.promenade.world.gen.structure.PromenadeStructurePoolsKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import java.util.concurrent.CompletableFuture;

public class PromenadeStructureProvider extends FabricDynamicRegistryProvider {
    public PromenadeStructureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE));
    }

    @Override
    public String getName() {
        return "Structures";
    }

    public static void register(BootstrapContext<Structure> registerable) {
        var biomes = registerable.lookup(Registries.BIOME);
        var templatePools = registerable.lookup(Registries.TEMPLATE_POOL);

        registerable.register(
                PromenadeStructureKeys.DARK_FOREST_WITCH_HUT,
                new JigsawStructure(
                        new Structure.StructureSettings.Builder(biomes.getOrThrow(PromenadeBiomeTags.HAS_DARK_FOREST_WITCH_HUTS))
                                .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
                                .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                                .build(),
                        templatePools.getOrThrow(PromenadeStructurePoolsKeys.DARK_FOREST_WITCH_HUTS),
                        1,
                        ConstantHeight.of(VerticalAnchor.absolute(0)),
                        false,
                        Heightmap.Types.WORLD_SURFACE_WG
                )
        );
    }
}