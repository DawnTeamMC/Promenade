package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.banner.PromenadeBannerPatternTags;
import fr.hugman.promenade.banner.PromenadeBannerPatterns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class PromenadeBannerPatternTagProvider extends FabricTagProvider<BannerPattern> {
    public PromenadeBannerPatternTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BANNER_PATTERN, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Promenade
        getOrCreateTagBuilder(PromenadeBannerPatternTags.BOVINE_PATTERN_ITEM).add(PromenadeBannerPatterns.BOVINE);
    }
}