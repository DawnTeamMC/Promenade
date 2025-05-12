package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.Promenade;
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

public record DuckVariant(
        AssetInfo texture,
        AssetInfo babyTexture,
        SpawnConditionSelectors spawnConditions
) implements VariantSelectorProvider<SpawnContext, SpawnCondition> {
    public static final AssetInfo DEFAULT_DUCKLING_ASSET = new AssetInfo(Promenade.id("entity/duck/duckling"));

    public static final Codec<DuckVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.MAP_CODEC.forGetter(DuckVariant::texture),
            AssetInfo.CODEC.optionalFieldOf("baby_texture", DEFAULT_DUCKLING_ASSET).forGetter(DuckVariant::babyTexture),
            SpawnConditionSelectors.CODEC.fieldOf("spawn_conditions").forGetter(DuckVariant::spawnConditions)
    ).apply(instance, DuckVariant::new));

    public static final Codec<DuckVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AssetInfo.MAP_CODEC.forGetter(DuckVariant::texture),
            AssetInfo.CODEC.optionalFieldOf("baby_texture", DEFAULT_DUCKLING_ASSET).forGetter(DuckVariant::babyTexture)
    ).apply(instance, DuckVariant::new));

    public static final Codec<RegistryEntry<DuckVariant>> ENTRY_CODEC = RegistryFixedCodec.of(PromenadeRegistryKeys.DUCK_VARIANT);
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<DuckVariant>> ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(PromenadeRegistryKeys.DUCK_VARIANT);

    public DuckVariant(AssetInfo texture, AssetInfo babyTexture) {
        this(texture, babyTexture, SpawnConditionSelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> getSelectors() {
        return this.spawnConditions.selectors();
    }
}
