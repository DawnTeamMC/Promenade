package fr.hugman.promenade.data.model;

import net.minecraft.block.Block;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.data.TexturedModel;

public class PromenadeTexturedModels {
    public static final TexturedModel.Factory PILE = TexturedModel.makeFactory(TextureMap::all, PromenadeModels.PILE);
    public static final TexturedModel.Factory FALLEN_LEAVES = TexturedModel.makeFactory(TextureMap::all, PromenadeModels.FALLEN_LEAVES);

    public static TexturedModel.Factory pile(Block block) {
        return TexturedModel.makeFactory(b -> TextureMap.all(block), PromenadeModels.PILE);
    }

    public static TexturedModel.Factory fallenLeaves(Block block) {
        return TexturedModel.makeFactory(b -> TextureMap.all(block), PromenadeModels.FALLEN_LEAVES);
    }
}
