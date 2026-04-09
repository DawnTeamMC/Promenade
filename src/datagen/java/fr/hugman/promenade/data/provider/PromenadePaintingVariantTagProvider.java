package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadePaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class PromenadePaintingVariantTagProvider extends FabricTagsProvider<PaintingVariant> {
    public PromenadePaintingVariantTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, Registries.PAINTING_VARIANT, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(PaintingVariantTags.PLACEABLE)
                .add(PromenadePaintingVariants.OPTIMISM, PromenadePaintingVariants.NURTURE);
    }
}