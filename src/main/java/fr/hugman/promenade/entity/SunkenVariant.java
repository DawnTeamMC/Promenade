package fr.hugman.promenade.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Objects;

public final class SunkenVariant {
    public static final Codec<SunkenVariant> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("texture").forGetter(sunken -> sunken.texture),
            RegistryKey.createCodec(RegistryKeys.LOOT_TABLE).fieldOf("loot_table").forGetter(sunken -> sunken.lootTable)
    ).apply(instance, SunkenVariant::new));

    private final Identifier texture;
    private final RegistryKey<LootTable> lootTable;

    private final Identifier texturePath;

    public SunkenVariant(Identifier texture, RegistryKey<LootTable> lootTable) {
        this.texture = texture;
        this.lootTable = lootTable;

        this.texturePath = getTexturePath(texture);
    }

    public Identifier texture() {
        return texturePath;
    }

    public RegistryKey<LootTable> lootTable() {
        return lootTable;
    }

    private static Identifier getTexturePath(Identifier id) {
        return id.withPath(oldPath -> "textures/" + oldPath + ".png");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SunkenVariant) obj;
        return Objects.equals(this.texture, that.texture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texture);
    }
}
