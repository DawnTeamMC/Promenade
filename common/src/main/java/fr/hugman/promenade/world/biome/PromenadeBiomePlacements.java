package fr.hugman.promenade.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.util.stream.Stream;

/**
 * Places the mod's biomes and their surfaces in the world.
 * Biolith 1.0 (for 1.20.1) is not data-driven: this is what newer versions generate as Biolith data files.
 */
public class PromenadeBiomePlacements {
    public static void register() {
        // Sakura Groves
        BiomePlacement.replaceOverworld(Biomes.FOREST, PromenadeBiomes.BLUSH_SAKURA_GROVE, 0.2D);
        BiomePlacement.replaceOverworld(Biomes.BIRCH_FOREST, PromenadeBiomes.COTTON_SAKURA_GROVE, 0.2D);
        // Carnelian Treeway
        BiomePlacement.replaceOverworld(Biomes.PLAINS, PromenadeBiomes.CARNELIAN_TREEWAY, 0.2D);
        // Glacarian Taiga
        Stream.of(Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.SNOWY_SLOPES, Biomes.JAGGED_PEAKS, Biomes.GROVE)
                .forEach(target -> BiomePlacement.replaceOverworld(target, PromenadeBiomes.GLACARIAN_TAIGA, 0.1D));

        // Dark Amaranth Forest
        BiomePlacement.addNether(PromenadeBiomes.DARK_AMARANTH_FOREST, Climate.parameters(0.15F, -0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        SurfaceGeneration.addNetherSurfaceRules(Promenade.id("rules/nether"), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0)), SurfaceRules.ifTrue(SurfaceRules.hole(), SurfaceRules.state(Blocks.LAVA.defaultBlockState()))),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(PromenadeBiomes.DARK_AMARANTH_FOREST),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.54)),
                                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17), SurfaceRules.state(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK.defaultBlockState())), SurfaceRules.state(PromenadeBlocks.DARK_AMARANTH_NYLIUM.defaultBlockState())))
                                )
                        )
                )
        ));
    }
}
