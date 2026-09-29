package fr.hugman.promenade.data.model;

import fr.hugman.promenade.Promenade;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;

import java.util.Optional;

public class PromenadeModels {
    public static final ModelTemplate PILE = block("pile", TextureSlot.ALL);
    public static final ModelTemplate FALLEN_LEAVES = block("fallen_leaves", TextureSlot.ALL);
    public static final ModelTemplate BOTTOM_SNOWY_LEAVES = block("bottom_snowy_leaves", TextureSlot.ALL, TextureSlot.TOP, TextureSlot.SIDE);

    private static ModelTemplate make(TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.empty(), Optional.empty(), requiredTextureKeys);
    }

    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Promenade.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static ModelTemplate item(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Promenade.id("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static ModelTemplate openBundle(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Promenade.id("item/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Promenade.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }
}
