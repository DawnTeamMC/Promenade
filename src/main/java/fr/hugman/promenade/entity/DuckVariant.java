package fr.hugman.promenade.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.Promenade;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.Objects;
import java.util.Optional;

public final class DuckVariant {
    private static final Identifier DEFAULT_DUCKLING_TEXTURE = Promenade.id("entity/duck/duckling");

    public static final Codec<DuckVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("texture").forGetter(duck -> duck.texture),
            Identifier.CODEC.optionalFieldOf("baby_texture", DEFAULT_DUCKLING_TEXTURE).forGetter(duck -> duck.babyTexture),
            RegistryCodecs.entryList(RegistryKeys.BIOME).fieldOf("biomes").forGetter(duck -> duck.biomes)
    ).apply(instance, DuckVariant::new));

    private final Identifier texture;
    private final Identifier babyTexture;
    private final RegistryEntryList<Biome> biomes;

    private final Identifier texturePath;
    private final Identifier babyTexturePath;

    public DuckVariant(Identifier texture, Identifier babyTexture, RegistryEntryList<Biome> biomes) {
        this.texture = texture;
        this.babyTexture = babyTexture;
        this.biomes = biomes;

        this.texturePath = getTexturePath(texture);
        this.babyTexturePath = getTexturePath(babyTexture);
    }

    public Identifier texture() {
        return texturePath;
    }

    public Identifier babyTexture() {
        return babyTexturePath;
    }

    public RegistryEntryList<Biome> getBiomes() {
        return this.biomes;
    }

    private static Identifier getTexturePath(Identifier id) {
        return id.withPath(oldPath -> "textures/" + oldPath + ".png");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DuckVariant) obj;
        return Objects.equals(this.texture, that.texture) && Objects.equals(this.babyTexture, that.babyTexture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texture, babyTexture);
    }
}
