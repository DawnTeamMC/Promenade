package fr.hugman.promenade.data;

import fr.hugman.promenade.Promenade;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class PromenadeModels {
    public static final Model PILE = block("pile", TextureKey.ALL);

    private static Model make(TextureKey... requiredTextureKeys) {
        return new Model(Optional.empty(), Optional.empty(), requiredTextureKeys);
    }

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Promenade.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Promenade.id("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static Model openBundle(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Promenade.id("item/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Promenade.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
}
