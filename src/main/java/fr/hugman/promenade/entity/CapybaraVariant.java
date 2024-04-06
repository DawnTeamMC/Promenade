package fr.hugman.promenade.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

import java.util.Objects;

public final class CapybaraVariant {
    public static final Codec<CapybaraVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("small_eyes_texture").forGetter(capybara -> capybara.smallEyesTexture),
            Identifier.CODEC.fieldOf("large_eyes_texture").forGetter(capybara -> capybara.largeEyesTexture),
            Identifier.CODEC.fieldOf("closed_eyes_texture").forGetter(capybara -> capybara.closedEyesTexture),
            Codec.INT.fieldOf("spawn_weight").forGetter(CapybaraVariant::spawnWeight)
    ).apply(instance, CapybaraVariant::new));

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
        this.smallEyesTexturePath = getTexturePath(smallEyesTexture);
        this.largeEyesTexture = largeEyesTexture;
        this.largeEyesTexturePath = getTexturePath(largeEyesTexture);
        this.closedEyesTexture = closedEyesTexture;
        this.closedEyesTexturePath = getTexturePath(closedEyesTexture);
        this.spawnWeight = spawnWeight;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CapybaraVariant) obj;
        return this.spawnWeight == that.spawnWeight &&
                Objects.equals(this.smallEyesTexture, that.smallEyesTexture) &&
                Objects.equals(this.largeEyesTexture, that.largeEyesTexture) &&
                Objects.equals(this.closedEyesTexture, that.closedEyesTexture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spawnWeight, smallEyesTexture, largeEyesTexture, closedEyesTexture);
    }
}
