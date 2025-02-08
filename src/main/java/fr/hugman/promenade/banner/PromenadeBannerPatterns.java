package fr.hugman.promenade.banner;

import fr.hugman.promenade.Promenade;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class PromenadeBannerPatterns {
    public static final RegistryKey<BannerPattern> BOVINE = of("bovine");

    private static RegistryKey<BannerPattern> of(String path) {
        return RegistryKey.of(RegistryKeys.BANNER_PATTERN, Promenade.id(path));
    }
}
