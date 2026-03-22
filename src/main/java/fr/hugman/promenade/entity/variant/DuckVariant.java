package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import java.util.List;
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

public record DuckVariant(
        ResourceTexture texture,
        ResourceTexture babyTexture,
        SpawnPrioritySelectors spawnConditions
) implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final ResourceTexture DEFAULT_DUCKLING_ASSET = new ResourceTexture(Promenade.id("entity/duck/duckling"));

    public static final Codec<DuckVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(DuckVariant::texture),
            ResourceTexture.CODEC.optionalFieldOf("baby_texture", DEFAULT_DUCKLING_ASSET).forGetter(DuckVariant::babyTexture),
            SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(DuckVariant::spawnConditions)
    ).apply(instance, DuckVariant::new));

    public static final Codec<DuckVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(DuckVariant::texture),
            ResourceTexture.CODEC.optionalFieldOf("baby_texture", DEFAULT_DUCKLING_ASSET).forGetter(DuckVariant::babyTexture)
    ).apply(instance, DuckVariant::new));

    public static final Codec<Holder<DuckVariant>> ENTRY_CODEC = RegistryFixedCodec.create(PromenadeRegistryKeys.DUCK_VARIANT);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<DuckVariant>> ENTRY_PACKET_CODEC = ByteBufCodecs.holderRegistry(PromenadeRegistryKeys.DUCK_VARIANT);

    public DuckVariant(ResourceTexture texture, ResourceTexture babyTexture) {
        this(texture, babyTexture, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
    }
}
