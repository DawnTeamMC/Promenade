package fr.hugman.promenade.data.model;

import net.minecraft.block.Block;
import net.minecraft.client.data.TextureKey;
import net.minecraft.client.data.TextureMap;

public class PromenadeTextureMaps {
    public static TextureMap snowyLeaves(Block snowyLeaves, Block baseLeaves) {
        return new TextureMap()
                .put(TextureKey.ALL, TextureMap.getId(baseLeaves))
                .put(TextureKey.TOP, TextureMap.getId(snowyLeaves))
                .put(TextureKey.SIDE, TextureMap.getSubId(snowyLeaves, "_bottom"));
    }
}
