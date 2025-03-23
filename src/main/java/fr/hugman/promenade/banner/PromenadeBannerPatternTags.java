package fr.hugman.promenade.banner;

import fr.hugman.promenade.Promenade;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class PromenadeBannerPatternTags {
    public static final TagKey<BannerPattern> BOVINE_PATTERN_ITEM = of("pattern_item/bovine");

    private static TagKey<BannerPattern> of(String path) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, Promenade.id(path));
    }
}
