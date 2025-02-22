package fr.hugman.promenade.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.config.PromenadeConfig;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;

public class PromenadeBiomes {
    public static final RegistryKey<Biome> BLUSH_SAKURA_GROVE = of("blush_sakura_grove");
    public static final RegistryKey<Biome> COTTON_SAKURA_GROVE = of("cotton_sakura_grove");
    public static final RegistryKey<Biome> CARNELIAN_TREEWAY = of("carnelian_treeway");
    public static final RegistryKey<Biome> GLACARIAN_TAIGA = of("glacarian_taiga");

    public static final RegistryKey<Biome> DARK_AMARANTH_FOREST = of("dark_amaranth_forest");

    public static final MultiNoiseUtil.NoiseHypercube DEFAULT_DARK_AMARANTH_FOREST_HYPERCUBE = MultiNoiseUtil.createNoiseHypercube(0.15f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

    private static RegistryKey<Biome> of(String path) {
        return RegistryKey.of(RegistryKeys.BIOME, Promenade.id(path));
    }

    public static void appendWorldGen() {
        var biomeConfig = PromenadeConfig.get().biomes();

        // Sakura Groves
        if (biomeConfig.sakuraGrovesWeight() > 0) {
            double sakuraWeight = biomeConfig.sakuraGrovesWeight() / 100.0D;
            BiomePlacement.replaceOverworld(BiomeKeys.FOREST, PromenadeBiomes.BLUSH_SAKURA_GROVE, sakuraWeight);
            BiomePlacement.replaceOverworld(BiomeKeys.BIRCH_FOREST, PromenadeBiomes.COTTON_SAKURA_GROVE, sakuraWeight);
        }

        // Carnelian Treeway
        if (biomeConfig.carnelianTreewayWeight() > 0) {
            BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, PromenadeBiomes.CARNELIAN_TREEWAY, biomeConfig.carnelianTreewayWeight() / 100.0D);
        }

        // Glacarian Taiga
        if (biomeConfig.glacarianTaigaWeight() > 0) {
            double glacarianTaigaWeight = biomeConfig.glacarianTaigaWeight() / 100.0D;
            BiomePlacement.replaceOverworld(BiomeKeys.TAIGA, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_TAIGA, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_SLOPES, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(BiomeKeys.JAGGED_PEAKS, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
            BiomePlacement.replaceOverworld(BiomeKeys.GROVE, PromenadeBiomes.GLACARIAN_TAIGA, glacarianTaigaWeight);
        }

        // Auroral Cypress
        if (Promenade.CONFIG.biomes.auroral_cypress_weight <= 0) {
            return;
        }
        double auroralCypressWeight = Promenade.CONFIG.biomes.auroral_cypress_weight / 100.0D;
        BiomePlacement.replaceEnd(BiomeKeys.END_HIGHLANDS, PromenadeBiomeKeys.AURORAL_CYPRESS_FOREST, auroralCypressWeight);
        BiomePlacement.replaceEnd(BiomeKeys.END_MIDLANDS, PromenadeBiomeKeys.AURORAL_CYPRESS_FOREST, auroralCypressWeight);

        if (biomeConfig.darkAmaranthForestsNoise().isPresent()) {
            BiomePlacement.addNether(PromenadeBiomes.DARK_AMARANTH_FOREST, biomeConfig.darkAmaranthForestsNoise().get());

            SurfaceGeneration.addNetherSurfaceRules(Promenade.id("dark_amaranth_forest"),
                    MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR,
                            MaterialRules.sequence(
                                    MaterialRules.condition(MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(32), 0)), MaterialRules.condition(MaterialRules.hole(), VanillaSurfaceRules.block(Blocks.LAVA))),
                                    MaterialRules.condition(MaterialRules.biome(PromenadeBiomes.DARK_AMARANTH_FOREST),
                                            MaterialRules.condition(
                                                    MaterialRules.not(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHERRACK, 0.54)),
                                                    MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(31), 0), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_WART, 1.17), VanillaSurfaceRules.block(PromenadeBlocks.DARK_AMARANTH_WART_BLOCK)), VanillaSurfaceRules.block(PromenadeBlocks.DARK_AMARANTH_NYLIUM)))
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
        RegistryEntry<Biome> biome = entity.getWorld().getBiome(entity.getBlockPos());
        if (entity.getType().isIn(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES)) {
            // is immune
            return false;
        }
        if (!biome.isIn(PromenadeBiomeTags.CAN_FREEZE_DURING_SNOWFALL) || entity.isSpectator()) {
            // is not the correct biome
            // is spectator
            return false;
        }
        if (!entity.getWorld().isRaining()) {
            // is not snowing
            return false;
        }
        boolean exposedToSky = entity.getWorld().getLightLevel(LightType.SKY, entity.getBlockPos()) >= 5;
        boolean lightSourceNear = entity.getWorld().getLightLevel(LightType.BLOCK, entity.getBlockPos()) >= 5;
        if (lightSourceNear || !exposedToSky) {
            // is near a light source
            // is not exposed much to sky
            return false;
        }
        // wear any leather piece
        return !entity.getEquippedStack(EquipmentSlot.HEAD).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES) &&
                !entity.getEquippedStack(EquipmentSlot.CHEST).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES) &&
                !entity.getEquippedStack(EquipmentSlot.LEGS).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES) &&
                !entity.getEquippedStack(EquipmentSlot.FEET).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES);
    }
}
