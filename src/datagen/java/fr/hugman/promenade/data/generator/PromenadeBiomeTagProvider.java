package fr.hugman.promenade.data.generator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.tag.PromenadeBiomeTags.*;
import static net.minecraft.world.biome.BiomeKeys.*;

public class PromenadeBiomeTagProvider extends FabricTagProvider<Biome> {
    public PromenadeBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, RegistryKeys.BIOME, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Promenade tags

        getOrCreateTagBuilder(SAKURA_GROVES)
                //.add(BLUSH_SAKURA_GROVE, COTTON_SAKURA_GROVE) FIXME
        ;

        getOrCreateTagBuilder(HAS_PALMS)
                .add(DESERT)
                .addOptionalTag(ConventionalBiomeTags.IS_DESERT);
        getOrCreateTagBuilder(HAS_DARK_FOREST_WITCH_HUT)
                .add(DARK_FOREST, PALE_GARDEN)
                .addOptionalTag(ConventionalBiomeTags.IS_DARK_FOREST);

        getOrCreateTagBuilder(CAN_FREEZE_DURING_SNOWFALL)
                //.add(GLACARIAN_TAIGA) FIXME
        ;

        getOrCreateTagBuilder(SPAWNS_CAPYBARAS)
                .forceAddTag(BiomeTags.IS_RIVER)
                .add(MANGROVE_SWAMP, SWAMP)
                .addOptionalTag(ConventionalBiomeTags.IS_RIVER)
                .addOptionalTag(ConventionalBiomeTags.IS_SWAMP);
        getOrCreateTagBuilder(SPAWNS_SUNKEN).add(WARM_OCEAN, LUKEWARM_OCEAN, DEEP_LUKEWARM_OCEAN);
        getOrCreateTagBuilder(SPAWNS_MALLARD_DUCKS)
                .forceAddTag(BiomeTags.IS_OCEAN)
                .forceAddTag(BiomeTags.IS_RIVER)
                .addOptionalTag(ConventionalBiomeTags.IS_OCEAN)
                .addOptionalTag(ConventionalBiomeTags.IS_RIVER);
        getOrCreateTagBuilder(SPAWNS_PEKIN_DUCKS)
                .add(PLAINS)
                .forceAddTag(BiomeTags.IS_FOREST)
                .addOptionalTag(ConventionalBiomeTags.IS_OCEAN);

        // Vanilla tags

        /*
        FIXME
        getOrCreateTagBuilder(BiomeTags.IS_FOREST).addTag(SAKURA_GROVES).add(CARNELIAN_TREEWAY);
        getOrCreateTagBuilder(BiomeTags.IS_NETHER).add(DARK_AMARANTH_FOREST);
        getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD).addTag(SAKURA_GROVES).add(CARNELIAN_TREEWAY, GLACARIAN_TAIGA);
        getOrCreateTagBuilder(BiomeTags.IS_TAIGA).add(GLACARIAN_TAIGA);
        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO).addTag(SAKURA_GROVES).add(CARNELIAN_TREEWAY, GLACARIAN_TAIGA);
         */
    }
}