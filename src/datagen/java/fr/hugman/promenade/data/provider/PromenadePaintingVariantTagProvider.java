package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadePaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.PaintingVariantTags;

import java.util.concurrent.CompletableFuture;

public class PromenadePaintingVariantTagProvider extends FabricTagProvider<PaintingVariant> {
    public PromenadePaintingVariantTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, RegistryKeys.PAINTING_VARIANT, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(PaintingVariantTags.PLACEABLE)
                .add(PromenadePaintingVariants.OPTIMISM, PromenadePaintingVariants.NURTURE);
    }
}