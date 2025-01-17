package fr.hugman.promenade.data.generator;

import fr.hugman.promenade.entity.variant.PromenadeWolfVariants;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
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

    public static void register(Registerable<WolfVariant> registerable) {
        var biomes = registerable.getRegistryLookup(RegistryKeys.BIOME);

        of(registerable, PromenadeWolfVariants.SHIBA_INU, biomes.getOrThrow(PromenadeBiomeTags.SAKURA_GROVES));
    }

    private static void of(Registerable<WolfVariant> registry, RegistryKey<WolfVariant> key, RegistryEntryList<Biome> biomes) {
        var baseId = key.getValue().withPrefixedPath("entity/wolf/");
        registry.register(key, new WolfVariant(
                baseId.withSuffixedPath("/small_eyes"),
                baseId.withSuffixedPath("/large_eyes"),
                baseId.withSuffixedPath("/closed_eyes"),
                biomes
        ));
    }
}