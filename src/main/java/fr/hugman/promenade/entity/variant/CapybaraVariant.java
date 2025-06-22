package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.util.AssetInfo;

import java.util.List;

public record CapybaraVariant(
        AssetInfo smallEyesTexture,
        AssetInfo largeEyesTexture,
        AssetInfo closedEyesTexture,
        SpawnConditionSelectors spawnConditions
) implements VariantSelectorProvider<SpawnContext, SpawnCondition> {
    public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.CODEC.fieldOf("small_eyes_texture").forGetter(CapybaraVariant::smallEyesTexture),
            AssetInfo.CODEC.fieldOf("large_eyes_texture").forGetter(CapybaraVariant::largeEyesTexture),
            AssetInfo.CODEC.fieldOf("closed_eyes_texture").forGetter(CapybaraVariant::closedEyesTexture),
            SpawnConditionSelectors.CODEC.fieldOf("spawn_conditions").forGetter(CapybaraVariant::spawnConditions)
    ).apply(instance, CapybaraVariant::new));

    public static final Codec<CapybaraVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.CODEC.fieldOf("small_eyes_texture").forGetter(CapybaraVariant::smallEyesTexture),
            AssetInfo.CODEC.fieldOf("large_eyes_texture").forGetter(CapybaraVariant::largeEyesTexture),
            AssetInfo.CODEC.fieldOf("closed_eyes_texture").forGetter(CapybaraVariant::closedEyesTexture)
    ).apply(instance, CapybaraVariant::new));

    public static final Codec<RegistryEntry<CapybaraVariant>> ENTRY_CODEC = RegistryFixedCodec.of(PromenadeRegistryKeys.CAPYBARA_VARIANT);
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<CapybaraVariant>> ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(PromenadeRegistryKeys.CAPYBARA_VARIANT);

    private CapybaraVariant(AssetInfo smallEyesTexture, AssetInfo largeEyesTexture, AssetInfo closedEyesTexture) {
        this(smallEyesTexture, largeEyesTexture, closedEyesTexture, SpawnConditionSelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> getSelectors() {
        return this.spawnConditions.selectors();
    }
}
