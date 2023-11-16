package fr.hugman.promenade.gen.surface;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.content.DuskContent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class PromenadeSurfaceBuilders {
    private static final MaterialRules.MaterialRule CALCITE = stateRule(Blocks.CALCITE);

    public static void init() {
        MaterialRules.MaterialCondition belowWater = MaterialRules.waterWithStoneDepth(-6, -1);
        MaterialRules.MaterialCondition noiseCondition4 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.1, 0.1);

        MaterialRules.MaterialRule duskCypressRule = MaterialRules.condition(MaterialRules.surface(), MaterialRules.condition(belowWater,
                MaterialRules.condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                        MaterialRules.condition(MaterialRules.biome(DuskContent.DUSK_CYPRESS_FOREST),
                                MaterialRules.sequence(
                                        MaterialRules.condition(noiseCondition4, CALCITE)))))
        );

        ImmutableList.Builder <MaterialRules.MaterialRule> builder = ImmutableList.builder();
        builder.add(duskCypressRule);

        SurfaceGeneration.addOverworldSurfaceRules(Promenade.id("overworld_surfaces"),
                MaterialRules.sequence(builder.build().toArray(MaterialRules.MaterialRule[]::new)));
    }

    private static MaterialRules.MaterialRule stateRule(Block block) {
        return stateRule(block.getDefaultState());
    }

    private static MaterialRules.MaterialRule stateRule(BlockState state) {
        return MaterialRules.block(state);
    }
}
