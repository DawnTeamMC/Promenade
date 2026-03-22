package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public record CapybaraVariant(
        ResourceTexture smallEyesTexture,
        ResourceTexture largeEyesTexture,
        ResourceTexture closedEyesTexture,
        SpawnPrioritySelectors spawnConditions
) implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceTexture.CODEC.fieldOf("small_eyes_texture").forGetter(CapybaraVariant::smallEyesTexture),
            ResourceTexture.CODEC.fieldOf("large_eyes_texture").forGetter(CapybaraVariant::largeEyesTexture),
            ResourceTexture.CODEC.fieldOf("closed_eyes_texture").forGetter(CapybaraVariant::closedEyesTexture),
            SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(CapybaraVariant::spawnConditions)
    ).apply(instance, CapybaraVariant::new));

    public static final Codec<CapybaraVariant> NETWORK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceTexture.CODEC.fieldOf("small_eyes_texture").forGetter(CapybaraVariant::smallEyesTexture),
            ResourceTexture.CODEC.fieldOf("large_eyes_texture").forGetter(CapybaraVariant::largeEyesTexture),
            ResourceTexture.CODEC.fieldOf("closed_eyes_texture").forGetter(CapybaraVariant::closedEyesTexture)
    ).apply(instance, CapybaraVariant::new));

    public static final Codec<Holder<CapybaraVariant>> ENTRY_CODEC = RegistryFixedCodec.create(PromenadeRegistryKeys.CAPYBARA_VARIANT);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<CapybaraVariant>> ENTRY_PACKET_CODEC = ByteBufCodecs.holderRegistry(PromenadeRegistryKeys.CAPYBARA_VARIANT);

    private CapybaraVariant(ResourceTexture smallEyesTexture, ResourceTexture largeEyesTexture, ResourceTexture closedEyesTexture) {
        this(smallEyesTexture, largeEyesTexture, closedEyesTexture, SpawnPrioritySelectors.EMPTY);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> selectors() {
        return this.spawnConditions.selectors();
    }
}
