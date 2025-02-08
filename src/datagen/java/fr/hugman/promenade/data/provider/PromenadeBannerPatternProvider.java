package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.banner.PromenadeBannerPatterns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class PromenadeBannerPatternProvider extends FabricDynamicRegistryProvider {
    public PromenadeBannerPatternProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.BANNER_PATTERN));
    }

    @Override
    public String getName() {
        return "Banner Patterns";
    }

    public static void register(Registerable<BannerPattern> registerable) {
        of(registerable, PromenadeBannerPatterns.BOVINE);
    }

    public static void of(Registerable<BannerPattern> registerable, RegistryKey<BannerPattern> key) {
        registerable.register(key, new BannerPattern(key.getValue(), "block.promenade.banner." + key.getValue().getPath()));
    }

}