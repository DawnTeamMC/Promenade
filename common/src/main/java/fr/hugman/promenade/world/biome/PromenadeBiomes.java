package fr.hugman.promenade.world.biome;

import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;

public class PromenadeBiomes {
    public static final ResourceKey<Biome> BLUSH_SAKURA_GROVE = of("blush_sakura_grove");
    public static final ResourceKey<Biome> COTTON_SAKURA_GROVE = of("cotton_sakura_grove");
    public static final ResourceKey<Biome> CARNELIAN_TREEWAY = of("carnelian_treeway");
    public static final ResourceKey<Biome> GLACARIAN_TAIGA = of("glacarian_taiga");

    public static final ResourceKey<Biome> DARK_AMARANTH_FOREST = of("dark_amaranth_forest");

    private static ResourceKey<Biome> of(String path) {
        return ResourceKey.create(Registries.BIOME, Promenade.id(path));
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
