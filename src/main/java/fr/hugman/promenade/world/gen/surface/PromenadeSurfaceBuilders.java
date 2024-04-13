package fr.hugman.promenade.world.gen.surface;

import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeBiomeKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class PromenadeSurfaceBuilders {
    private static final MaterialRules.MaterialRule CALCITE = stateRule(Blocks.CALCITE);

    public static void init() {
        addOverworldRules();
        addEndRules();
    }

    public static void addOverworldRules() {
        MaterialRules.MaterialCondition belowWater = MaterialRules.waterWithStoneDepth(-6, -1);
        MaterialRules.MaterialCondition noiseCondition4 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.1, 0.1);

        MaterialRules.MaterialRule auroralCypressRule = MaterialRules.condition(MaterialRules.surface(), MaterialRules.condition(belowWater,
                MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                        MaterialRules.condition(MaterialRules.biome(PromenadeBiomeKeys.AURORAL_CYPRESS_FOREST),
                                MaterialRules.sequence(
                                        MaterialRules.condition(noiseCondition4, CALCITE)))))
        );

        SurfaceGeneration.addOverworldSurfaceRules(Promenade.id("overworld_surfaces"),
                MaterialRules.sequence(auroralCypressRule));
    }

    public static void addEndRules() {
        MaterialRules.MaterialCondition isDarkAmaranthForest = MaterialRules.biome(PromenadeBiomeKeys.DARK_AMARANTH_FOREST, PromenadeBiomeKeys.TALL_DARK_AMARANTH_FOREST);
        SurfaceGeneration.addEndSurfaceRules(Promenade.id("end_surface"), MaterialRules.sequence(
                MaterialRules.condition(isDarkAmaranthForest,
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_FLOOR,
                                VanillaSurfaceRules.block(PromenadeBlocks.BLACK_DYLIUM)
                        )
                ))
        );
    }

    private static MaterialRules.MaterialRule stateRule(Block block) {
        return stateRule(block.getDefaultState());
    }

    private static MaterialRules.MaterialRule stateRule(BlockState state) {
        return MaterialRules.block(state);
    }
}
