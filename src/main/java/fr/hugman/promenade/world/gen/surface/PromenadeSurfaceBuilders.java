package fr.hugman.promenade.world.gen.surface;

import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.world.biome.PromenadeBiomeKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class PromenadeSurfaceBuilders {

    public static void init() {
        addEndRules();
    }

    public static void addEndRules() {
        MaterialRules.MaterialCondition isAuroralCypressForest = MaterialRules.biome(PromenadeBiomeKeys.AURORAL_CYPRESS_FOREST);
        MaterialRules.MaterialCondition isDarkAmaranthForest = MaterialRules.biome(PromenadeBiomeKeys.DARK_AMARANTH_FOREST, PromenadeBiomeKeys.TALL_DARK_AMARANTH_FOREST);
        SurfaceGeneration.addEndSurfaceRules(Promenade.id("end_surface"), MaterialRules.sequence(
                MaterialRules.condition(isAuroralCypressForest,
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_FLOOR,
                                stateRule(PromenadeBlocks.BLACK_DYLIUM)
                        )
                ),
                MaterialRules.condition(isDarkAmaranthForest,
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_FLOOR,
                                stateRule(PromenadeBlocks.BLACK_DYLIUM)
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
