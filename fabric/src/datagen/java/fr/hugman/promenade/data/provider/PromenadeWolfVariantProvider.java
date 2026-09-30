package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadeWolfVariants;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class PromenadeWolfVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeWolfVariantProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        var wrapper = registries.lookupOrThrow(Registries.WOLF_VARIANT);
        entries.add(wrapper, PromenadeWolfVariants.SHIBA_INU);
    }

    @Override
    public String getName() {
        return "Wolf Variants";
    }

    public static void register(BootstrapContext<WolfVariant> registry) {
        of(registry, PromenadeWolfVariants.SHIBA_INU, PromenadeBiomeTags.SAKURA_GROVES);
    }

    /**
     * 1.21.1 wolf variants spawn in their biomes, and have no baby textures.
     */
    private static void of(BootstrapContext<WolfVariant> registry, ResourceKey<WolfVariant> key, TagKey<Biome> biomeTag) {
        var baseId = key.location().withPrefix("entity/wolf/");
        registry.register(key, new WolfVariant(
                baseId.withSuffix("/wild"),
                baseId.withSuffix("/tame"),
                baseId.withSuffix("/angry"),
                registry.lookup(Registries.BIOME).getOrThrow(biomeTag)
        ));
    }
}
