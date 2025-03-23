package fr.hugman.promenade.data.model;

import net.minecraft.block.Block;
import net.minecraft.client.data.TextureKey;
import net.minecraft.client.data.TextureMap;
import net.minecraft.util.Identifier;

public class PromenadeTextureMaps {
    public static TextureMap snowyLeaves(Block snowyLeaves, Block baseLeaves) {
        return new TextureMap()
                .put(TextureKey.ALL, TextureMap.getId(baseLeaves))
                .put(TextureKey.TOP, TextureMap.getId(snowyLeaves))
                .put(TextureKey.SIDE, TextureMap.getSubId(snowyLeaves, "_bottom"));
    }


    public static TextureMap snowyLeaves(Identifier snowyLeavesTexture, Block baseLeaves) {

        return new TextureMap()
                .put(TextureKey.ALL, TextureMap.getId(baseLeaves))
                .put(TextureKey.TOP, snowyLeavesTexture)
                .put(TextureKey.SIDE, snowyLeavesTexture.withSuffixedPath("_bottom"));
    }
}
