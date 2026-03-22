package fr.hugman.promenade.data.model;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

public class PromenadeTextureMaps {
    public static TextureMapping snowyLeaves(Block snowyLeaves, Block baseLeaves) {
        return new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(baseLeaves))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(snowyLeaves))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(snowyLeaves, "_bottom"));
    }


    public static TextureMapping snowyLeaves(Identifier snowyLeavesTexture, Block baseLeaves) {

        return new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(baseLeaves))
                .put(TextureSlot.TOP, snowyLeavesTexture)
                .put(TextureSlot.SIDE, snowyLeavesTexture.withSuffix("_bottom"));
    }
}
