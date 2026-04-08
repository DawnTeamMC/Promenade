package fr.hugman.promenade.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class PromenadeBiomes {
    public static final ResourceKey<Biome> BLUSH_SAKURA_GROVE = of("blush_sakura_grove");
    public static final ResourceKey<Biome> COTTON_SAKURA_GROVE = of("cotton_sakura_grove");
    public static final ResourceKey<Biome> CARNELIAN_TREEWAY = of("carnelian_treeway");
    public static final ResourceKey<Biome> GLACARIAN_TAIGA = of("glacarian_taiga");

    public static final ResourceKey<Biome> DARK_AMARANTH_FOREST = of("dark_amaranth_forest");

    public static final Climate.ParameterPoint DEFAULT_DARK_AMARANTH_FOREST_HYPERCUBE = Climate.parameters(0.15f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

    private static ResourceKey<Biome> of(String path) {
        return ResourceKey.create(Registries.BIOME, Promenade.id(path));
    }

    public static void appendWorldGen() {

        var biomeConfig = PromenadeConfig.get().biomes();

        // Sakura Groves
        if (biomeConfig.sakuraGrovesWeight() > 0) {
            double sakuraWeight = biomeConfig.sakuraGrovesWeight() / 100.0D;
            BiomePlacement.replaceOverworld(Biomes.FOREST, PromenadeBiomes.BLUSH_SAKURA_GROVE, sakuraWeight);
            BiomePlacement.replaceOverworld(Biomes.BIRCH_FOREST, PromenadeBiomes.COTTON_SAKURA_GROVE, sakuraWeight);
        }

        // Carnelian Treeway
        if (biomeConfig.carnelianTreewayWeight() > 0) {
            BiomePlacement.replaceOverworld(Biomes.PLAINS, PromenadeBiomes.CARNELIAN_TREEWAY, biomeConfig.carnelianTreewayWeight() / 100.0D);
        }

        // Glacarian Taiga
        if (biomeConfig.glacarianTaigaWeight() > 0) {
            double glacarianTaigaWeight = biomeConfig.glacarianTaigaWeight() / 100.0D;
            BiomePlacement.replaceOverworld(Biomes.TAIGA, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(Biomes.SNOWY_TAIGA, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(Biomes.SNOWY_SLOPES, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(Biomes.JAGGED_PEAKS, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(Biomes.GROVE, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
        }


        if (biomeConfig.darkAmaranthForestsNoise().isPresent()) {
            BiomePlacement.addNether(PromenadeBiomes.DARK_AMARANTH_FOREST, biomeConfig.darkAmaranthForestsNoise().get());

            SurfaceGeneration.addNetherSurfaceRules(Promenade.id("dark_amaranth_forest"),
                    SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                            SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0)), SurfaceRules.ifTrue(SurfaceRules.hole(), SurfaceRuleData.makeStateRule(Blocks.LAVA))),
                                    SurfaceRules.ifTrue(SurfaceRules.isBiome(PromenadeBiomes.DARK_AMARANTH_FOREST),
                                            SurfaceRules.ifTrue(
                                                    SurfaceRules.not(SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.54)),
                                                    SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17), SurfaceRuleData.makeStateRule(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK)), SurfaceRuleData.makeStateRule(PromenadeBlocks.DARK_AMARANTH_NYLIUM)))
                                            )
                                    )
                            )
                    ));
        }
    }

    /**
     * Check if the entity can freeze from the biome and weather.
     *
     * @param entity The entity to check.
     * @return true if the entity can freeze, false otherwise.
     */
    public static boolean canFreezeFromBiomeAndWeather(LivingEntity entity) {
        Holder<Biome> biome = entity.level().getBiome(entity.blockPosition());
        if (entity.is(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES)) {
            // is immune
            return false;
        }
        if (!biome.is(PromenadeBiomeTags.CAN_FREEZE_DURING_SNOWFALL) || entity.isSpectator()) {
            // is not the correct biome
            // is spectator
            return false;
        }
        if (!entity.level().isRaining()) {
            // is not snowing
            return false;
        }
        boolean exposedToSky = entity.level().getBrightness(LightLayer.SKY, entity.blockPosition()) >= 5;
        boolean lightSourceNear = entity.level().getBrightness(LightLayer.BLOCK, entity.blockPosition()) >= 5;
        if (lightSourceNear || !exposedToSky) {
            // is near a light source
            // is not exposed much to sky
            return false;
        }
        // wear any leather piece
        return !entity.getItemBySlot(EquipmentSlot.HEAD).is(ItemTags.FREEZE_IMMUNE_WEARABLES) &&
                !entity.getItemBySlot(EquipmentSlot.CHEST).is(ItemTags.FREEZE_IMMUNE_WEARABLES) &&
                !entity.getItemBySlot(EquipmentSlot.LEGS).is(ItemTags.FREEZE_IMMUNE_WEARABLES) &&
                !entity.getItemBySlot(EquipmentSlot.FEET).is(ItemTags.FREEZE_IMMUNE_WEARABLES);
    }
}
