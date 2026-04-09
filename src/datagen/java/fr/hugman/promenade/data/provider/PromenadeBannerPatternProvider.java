package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.banner.PromenadeBannerPatterns;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.concurrent.CompletableFuture;

public class PromenadeBannerPatternProvider extends FabricDynamicRegistryProvider {
    public PromenadeBannerPatternProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.BANNER_PATTERN));
    }

    @Override
    public String getName() {
        return "Banner Patterns";
    }

    public static void register(BootstrapContext<BannerPattern> registerable) {
        of(registerable, PromenadeBannerPatterns.BOVINE);
    }

    public static void of(BootstrapContext<BannerPattern> registerable, ResourceKey<BannerPattern> key) {
        registerable.register(key, new BannerPattern(key.identifier(), "block.promenade.banner." + key.identifier().getPath()));
    }

}