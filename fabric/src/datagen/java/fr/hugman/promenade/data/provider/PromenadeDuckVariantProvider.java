package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.DuckVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.ClientAsset.ResourceTexture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

//TODO: a generic class for other devs
public class PromenadeDuckVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeDuckVariantProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(PromenadeRegistryKeys.DUCK_VARIANT));
    }

    @Override
    public String getName() {
        return "Duck Variants";
    }

    public static void register(BootstrapContext<DuckVariant> registerable) {
        of(registerable, DuckVariants.PEKIN, PromenadeBiomeTags.SPAWNS_PEKIN_DUCKS);
        of(registerable, DuckVariants.MALLARD, PromenadeBiomeTags.SPAWNS_MALLARD_DUCKS);
    }

    private static void of(BootstrapContext<DuckVariant> registry, ResourceKey<DuckVariant> key, TagKey<Biome> biomeTag) {
        of(registry, key, DataProviderUtil.createSpawnConditions(registry.lookup(Registries.BIOME).getOrThrow(biomeTag)));
    }

    private static void of(BootstrapContext<DuckVariant> registry, ResourceKey<DuckVariant> key, SpawnPrioritySelectors spawnConditions) {
        registry.register(key, new DuckVariant(new ResourceTexture(key.identifier().withPrefix("entity/duck/")), DuckVariant.DEFAULT_DUCKLING_ASSET, spawnConditions));
    }
}