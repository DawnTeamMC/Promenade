package fr.hugman.promenade.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public final class CapybaraVariant {
    public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("small_eyes_texture").forGetter(capybara -> capybara.smallEyesTexture),
            Identifier.CODEC.fieldOf("large_eyes_texture").forGetter(capybara -> capybara.largeEyesTexture),
            Identifier.CODEC.fieldOf("closed_eyes_texture").forGetter(capybara -> capybara.closedEyesTexture),
            Codec.INT.fieldOf("spawn_weight").forGetter(CapybaraVariant::spawnWeight)
    ).apply(instance, CapybaraVariant::new));

    public static final Codec<RegistryEntry<CapybaraVariant>> ENTRY_CODEC = RegistryElementCodec.of(PromenadeRegistryKeys.CAPYBARA_VARIANT, CODEC);

    public static final PacketCodec<RegistryByteBuf, CapybaraVariant> PACKET_CODEC = PacketCodec.tuple(
            Identifier.PACKET_CODEC, (capybara -> capybara.smallEyesTexture),
            Identifier.PACKET_CODEC, (capybara -> capybara.largeEyesTexture),
            Identifier.PACKET_CODEC, (capybara -> capybara.closedEyesTexture),
            PacketCodecs.INTEGER, CapybaraVariant::spawnWeight,
            CapybaraVariant::new
    );
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<CapybaraVariant>> ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(PromenadeRegistryKeys.CAPYBARA_VARIANT, PACKET_CODEC);

    private final Identifier smallEyesTexture;
    private final Identifier largeEyesTexture;
    private final Identifier closedEyesTexture;
    private final int spawnWeight;

    private final Identifier smallEyesTexturePath;
    private final Identifier largeEyesTexturePath;
    private final Identifier closedEyesTexturePath;

    public CapybaraVariant(
            Identifier smallEyesTexture,
            Identifier largeEyesTexture,
            Identifier closedEyesTexture,
            int spawnWeight
    ) {
        this.smallEyesTexture = smallEyesTexture;
        this.largeEyesTexture = largeEyesTexture;
        this.closedEyesTexture = closedEyesTexture;
        this.spawnWeight = spawnWeight;

        this.smallEyesTexturePath = getTexturePath(smallEyesTexture);
        this.largeEyesTexturePath = getTexturePath(largeEyesTexture);
        this.closedEyesTexturePath = getTexturePath(closedEyesTexture);
    }

    public Identifier smallEyesTexture() {
        return smallEyesTexturePath;
    }

    public Identifier largeEyesTexture() {
        return largeEyesTexturePath;
    }

    public Identifier closedEyesTexture() {
        return closedEyesTexturePath;
    }

    public int spawnWeight() {
        return spawnWeight;
    }

    private static Identifier getTexturePath(Identifier id) {
        return id.withPath(oldPath -> "textures/" + oldPath + ".png");
    }
}
