package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadePaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class PromenadePaintingVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadePaintingVariantProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        final var wrapper = registries.lookupOrThrow(Registries.PAINTING_VARIANT);
        entries.add(wrapper, PromenadePaintingVariants.OPTIMISM);
        entries.add(wrapper, PromenadePaintingVariants.NURTURE);
    }

    @Override
    public String getName() {
        return "Painting Variants";
    }

    public static void register(BootstrapContext<PaintingVariant> registerable) {
        of(registerable, PromenadePaintingVariants.OPTIMISM, 2, 2);
        of(registerable, PromenadePaintingVariants.NURTURE, 2, 2);
    }

    /**
     * 1.21.1 paintings take their title and author from translations (see the English language provider).
     */
    private static void of(BootstrapContext<PaintingVariant> registry, ResourceKey<PaintingVariant> key, int width, int height) {
        registry.register(key, new PaintingVariant(width, height, key.location()));
    }
}