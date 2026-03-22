package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.banner.PromenadeBannerPatternTags;
import fr.hugman.promenade.banner.PromenadeBannerPatterns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import java.util.concurrent.CompletableFuture;

public class PromenadeBannerPatternTagProvider extends FabricTagProvider<BannerPattern> {
    public PromenadeBannerPatternTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BANNER_PATTERN, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Promenade
        builder(PromenadeBannerPatternTags.BOVINE_PATTERN_ITEM).add(PromenadeBannerPatterns.BOVINE);
    }
}