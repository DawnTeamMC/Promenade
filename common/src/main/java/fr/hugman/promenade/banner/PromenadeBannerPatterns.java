package fr.hugman.promenade.banner;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class PromenadeBannerPatterns {
    public static final ResourceKey<BannerPattern> BOVINE = of("bovine");

    private static ResourceKey<BannerPattern> of(String path) {
        return ResourceKey.create(Registries.BANNER_PATTERN, Promenade.id(path));
    }
}
