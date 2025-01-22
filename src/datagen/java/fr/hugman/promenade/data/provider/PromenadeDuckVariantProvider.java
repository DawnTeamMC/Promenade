package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.DuckVariant;
import fr.hugman.promenade.entity.variant.DuckVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

//TODO: a generic class for other devs
public class PromenadeDuckVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeDuckVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(PromenadeRegistryKeys.DUCK_VARIANT));
    }

    @Override
    public String getName() {
        return "Duck Variants";
    }

    public static void register(Registerable<DuckVariant> registerable) {
        final var biomes = registerable.getRegistryLookup(RegistryKeys.BIOME);

        of(registerable, DuckVariants.PEKIN, biomes.getOrThrow(PromenadeBiomeTags.SPAWNS_PEKIN_DUCKS));
        of(registerable, DuckVariants.MALLARD, biomes.getOrThrow(PromenadeBiomeTags.SPAWNS_MALLARD_DUCKS));
    }

    private static void of(Registerable<DuckVariant> registry, RegistryKey<DuckVariant> key, RegistryEntryList<Biome> biomes) {
        registry.register(key, new DuckVariant(key.getValue().withPrefixedPath("entity/duck/"), DuckVariant.DEFAULT_DUCKLING_TEXTURE, biomes));
    }
}