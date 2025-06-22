package fr.hugman.promenade.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.tag.PromenadeBiomeTags.*;
import static fr.hugman.promenade.world.biome.PromenadeBiomes.*;
import static net.minecraft.world.biome.BiomeKeys.*;

public class PromenadeBiomeTagProvider extends FabricTagProvider<Biome> {
    public PromenadeBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, RegistryKeys.BIOME, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Promenade
        builder(SAKURA_GROVES).add(BLUSH_SAKURA_GROVE, COTTON_SAKURA_GROVE);

        builder(HAS_PALMS)
                .add(DESERT)
                .addOptionalTag(ConventionalBiomeTags.IS_DESERT);
        builder(HAS_DARK_FOREST_WITCH_HUT)
                .add(DARK_FOREST, PALE_GARDEN)
                .addOptionalTag(ConventionalBiomeTags.IS_DARK_FOREST);

        builder(CAN_FREEZE_DURING_SNOWFALL).add(GLACARIAN_TAIGA);

        builder(SPAWNS_CAPYBARAS)
                .forceAddTag(BiomeTags.IS_RIVER)
                .add(MANGROVE_SWAMP, SWAMP)
                .addOptionalTag(ConventionalBiomeTags.IS_RIVER)
                .addOptionalTag(ConventionalBiomeTags.IS_SWAMP);
        builder(SPAWNS_SUNKEN).add(WARM_OCEAN, LUKEWARM_OCEAN, DEEP_LUKEWARM_OCEAN);
        builder(SPAWNS_MALLARD_DUCKS)
                .forceAddTag(BiomeTags.IS_OCEAN)
                .forceAddTag(BiomeTags.IS_RIVER)
                .addOptionalTag(ConventionalBiomeTags.IS_OCEAN)
                .addOptionalTag(ConventionalBiomeTags.IS_RIVER);
        builder(SPAWNS_PEKIN_DUCKS)
                .add(PLAINS)
                .forceAddTag(BiomeTags.IS_FOREST)
                .addOptionalTag(ConventionalBiomeTags.IS_OCEAN);

        // Vanilla
        builder(BiomeTags.IS_FOREST).addTag(SAKURA_GROVES).add(CARNELIAN_TREEWAY);
        builder(BiomeTags.IS_NETHER).add(DARK_AMARANTH_FOREST);
        builder(BiomeTags.IS_OVERWORLD).addTag(SAKURA_GROVES).add(CARNELIAN_TREEWAY, GLACARIAN_TAIGA);
        builder(BiomeTags.IS_TAIGA).add(GLACARIAN_TAIGA);

        builder(BiomeTags.STRONGHOLD_BIASED_TO).addTag(SAKURA_GROVES).add(CARNELIAN_TREEWAY, GLACARIAN_TAIGA);
        builder(BiomeTags.TRIAL_CHAMBERS_HAS_STRUCTURE).addTag(SAKURA_GROVES).add(CARNELIAN_TREEWAY, GLACARIAN_TAIGA);
        builder(BiomeTags.BASTION_REMNANT_HAS_STRUCTURE).add(DARK_AMARANTH_FOREST);

        builder(BiomeTags.SPAWNS_COLD_VARIANT_FROGS).add(GLACARIAN_TAIGA);
        builder(BiomeTags.SPAWNS_SNOW_FOXES).add(GLACARIAN_TAIGA);
        builder(BiomeTags.SPAWNS_WHITE_RABBITS).add(GLACARIAN_TAIGA);
        builder(BiomeTags.SNOW_GOLEM_MELTS).add(DARK_AMARANTH_FOREST);
    }
}