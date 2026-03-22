package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadePaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import java.util.concurrent.CompletableFuture;

public class PromenadePaintingVariantTagProvider extends FabricTagProvider<PaintingVariant> {
    public PromenadePaintingVariantTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, Registries.PAINTING_VARIANT, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(PaintingVariantTags.PLACEABLE)
                .add(PromenadePaintingVariants.OPTIMISM, PromenadePaintingVariants.NURTURE);
    }
}