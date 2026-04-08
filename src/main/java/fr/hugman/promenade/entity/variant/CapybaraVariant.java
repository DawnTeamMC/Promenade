package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.core.ClientAsset.ResourceTexture;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.entity.variant.PriorityProvider;
import net.minecraft.world.entity.variant.SpawnCondition;
import net.minecraft.world.entity.variant.SpawnContext;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

import java.util.List;

public record CapybaraVariant(
        AssetInfo adultInfo,
        AssetInfo babyInfo,
        SpawnPrioritySelectors spawnConditions
) implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.CODEC.fieldOf("assets").forGetter(CapybaraVariant::adultInfo),
            AssetInfo.CODEC.fieldOf("baby_assets").forGetter(CapybaraVariant::babyInfo),
            SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(CapybaraVariant::spawnConditions)
    ).apply(instance, CapybaraVariant::new));

    public static final Codec<CapybaraVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.CODEC.fieldOf("assets").forGetter(CapybaraVariant::adultInfo),
            AssetInfo.CODEC.fieldOf("baby_assets").forGetter(CapybaraVariant::babyInfo)
    ).apply(instance, CapybaraVariant::new));

    public static final Codec<Holder<CapybaraVariant>> ENTRY_CODEC = RegistryFixedCodec.create(PromenadeRegistryKeys.CAPYBARA_VARIANT);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<CapybaraVariant>> ENTRY_PACKET_CODEC = ByteBufCodecs.holderRegistry(PromenadeRegistryKeys.CAPYBARA_VARIANT);

    private CapybaraVariant(AssetInfo adultInfo, AssetInfo babyInfo) {
        this(adultInfo, babyInfo, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
    }

    public record AssetInfo(
            ResourceTexture normal,
            ResourceTexture surprised,
            ResourceTexture sleeping
    ) {
        public static final Codec<AssetInfo> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                ResourceTexture.CODEC.fieldOf("normal").forGetter(AssetInfo::normal),
                                ResourceTexture.CODEC.fieldOf("surprised").forGetter(AssetInfo::surprised),
                                ResourceTexture.CODEC.fieldOf("sleeping").forGetter(AssetInfo::sleeping)
                        )
                        .apply(instance, AssetInfo::new)
        );
    }
}
