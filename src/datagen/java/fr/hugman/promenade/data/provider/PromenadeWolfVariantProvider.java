package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadeWolfVariants;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.AssetInfo;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class PromenadeWolfVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeWolfVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        var wrapper = registries.getOrThrow(RegistryKeys.WOLF_VARIANT);
        entries.add(wrapper, PromenadeWolfVariants.SHIBA_INU);
    }

    @Override
    public String getName() {
        return "Wolf Variants";
    }

    public static void register(Registerable<WolfVariant> registry) {
        of(registry, PromenadeWolfVariants.SHIBA_INU, PromenadeBiomeTags.SAKURA_GROVES);
    }

    private static void of(Registerable<WolfVariant> registry, RegistryKey<WolfVariant> key, TagKey<Biome> biomeTag) {
        of(registry, key, DataProviderUtil.createSpawnConditions(registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag)));
    }

    private static void of(Registerable<WolfVariant> registry, RegistryKey<WolfVariant> key, SpawnConditionSelectors spawnConditions) {
        var baseId = key.getValue().withPrefixedPath("entity/wolf/");
        registry.register(key, new WolfVariant(
                new WolfVariant.WolfAssetInfo(
                        new AssetInfo(baseId.withSuffixedPath("/wild")),
                        new AssetInfo(baseId.withSuffixedPath("/tame")),
                        new AssetInfo(baseId.withSuffixedPath("/angry"))
                ),
                spawnConditions
        ));
    }
}