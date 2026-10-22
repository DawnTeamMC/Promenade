package fr.hugman.promenade.banner;

import fr.hugman.promenade.platform.PromenadePlatform;
import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

/**
 * Banner patterns are not data-driven in 1.20.1: they are registered with the rest of the content.
 */
public class PromenadeBannerPatterns {
    public static final ResourceKey<BannerPattern> BOVINE = register("bovine");

    private static ResourceKey<BannerPattern> register(String path) {
        var key = ResourceKey.create(Registries.BANNER_PATTERN, Promenade.id(path));
        // Saved in banners by this name, which must be unique among all patterns
        PromenadePlatform.INSTANCE.register(BuiltInRegistries.BANNER_PATTERN, key, new BannerPattern(key.location().toString()));
        return key;
    }
}
