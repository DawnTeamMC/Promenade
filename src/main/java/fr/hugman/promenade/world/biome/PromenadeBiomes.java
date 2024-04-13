package fr.hugman.promenade.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.block.PromenadeBlocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;

public class PromenadeBiomes {
    public static void appendWorldGen() {
        // Sakura Groves
        if (Promenade.CONFIG.biomes.sakura_groves_weight <= 0) {
            return;
        }
        double sakuraWeight = Promenade.CONFIG.biomes.sakura_groves_weight / 100.0D;
        BiomePlacement.replaceOverworld(BiomeKeys.FOREST, PromenadeBiomeKeys.BLUSH_SAKURA_GROVE, sakuraWeight);
        BiomePlacement.replaceOverworld(BiomeKeys.BIRCH_FOREST, PromenadeBiomeKeys.COTTON_SAKURA_GROVE, sakuraWeight);

        // Carnelian Treeway
        if (Promenade.CONFIG.biomes.carnelian_treeway_weight <= 0) {
            return;
        }
        BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, PromenadeBiomeKeys.CARNELIAN_TREEWAY, Promenade.CONFIG.biomes.carnelian_treeway_weight / 100.0D);

        // Glacarian Taiga
        if (Promenade.CONFIG.biomes.glacarian_taiga_weight <= 0) {
            return;
        }
        double glacarianTaigaWeight = Promenade.CONFIG.biomes.glacarian_taiga_weight / 100.0D;
        BiomePlacement.replaceOverworld(BiomeKeys.TAIGA, PromenadeBiomeKeys.GLACARIAN_TAIGA, glacarianTaigaWeight);
        BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_TAIGA, PromenadeBiomeKeys.GLACARIAN_TAIGA, glacarianTaigaWeight);
        BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_SLOPES, PromenadeBiomeKeys.GLACARIAN_TAIGA, glacarianTaigaWeight);
        BiomePlacement.replaceOverworld(BiomeKeys.JAGGED_PEAKS, PromenadeBiomeKeys.GLACARIAN_TAIGA, glacarianTaigaWeight);
        BiomePlacement.replaceOverworld(BiomeKeys.GROVE, PromenadeBiomeKeys.GLACARIAN_TAIGA, glacarianTaigaWeight);

        // Dusk Cypress
        if (Promenade.CONFIG.biomes.dusk_cypress_weight <= 0) {
            return;
        }
        double weight = Promenade.CONFIG.biomes.dusk_cypress_weight / 100.0D;
        BiomePlacement.replaceOverworld(BiomeKeys.PLAINS, PromenadeBiomeKeys.DUSK_CYPRESS_FOREST, weight);

        // Dark Amaranth
        if (Promenade.CONFIG.biomes.dark_amaranth_forests_weight <= 0) {
            return;
        }
        double darkAmaranthForestWeight = Promenade.CONFIG.biomes.dark_amaranth_forests_weight / 100.0D;
        BiomePlacement.replaceEnd(BiomeKeys.END_HIGHLANDS, PromenadeBiomeKeys.TALL_DARK_AMARANTH_FOREST, darkAmaranthForestWeight);
        BiomePlacement.replaceEnd(BiomeKeys.END_MIDLANDS, PromenadeBiomeKeys.DARK_AMARANTH_FOREST, darkAmaranthForestWeight);

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
