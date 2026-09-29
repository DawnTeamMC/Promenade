package fr.hugman.promenade.banner;

import fr.hugman.promenade.Promenade;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;

public class PromenadeBannerPatternTags {
    public static final TagKey<BannerPattern> BOVINE_PATTERN_ITEM = of("pattern_item/bovine");

    private static TagKey<BannerPattern> of(String path) {
        return TagKey.create(Registries.BANNER_PATTERN, Promenade.id(path));
    }
}
