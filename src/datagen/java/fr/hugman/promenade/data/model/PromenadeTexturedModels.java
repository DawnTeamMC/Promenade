package fr.hugman.promenade.data.model;

import net.minecraft.block.Block;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.data.TexturedModel;

public class PromenadeTexturedModels {
    public static final TexturedModel.Factory PILE = TexturedModel.makeFactory(TextureMap::all, PromenadeModels.PILE);
    public static final TexturedModel.Factory SURFACE_BLOCK = TexturedModel.makeFactory(TextureMap::all, PromenadeModels.SURFACE_BLOCK);

    public static TexturedModel.Factory pile(Block block) {
        return TexturedModel.makeFactory(b -> TextureMap.all(block), PromenadeModels.PILE);
    }

    public static TexturedModel.Factory surfaceBlock(Block block) {
        return TexturedModel.makeFactory(b -> TextureMap.all(block), PromenadeModels.SURFACE_BLOCK);
    }

    public static TexturedModel.Factory surfaceBlock(Block block, String suffix) {
        return TexturedModel.makeFactory(b -> TextureMap.all(TextureMap.getSubId(block, suffix)), PromenadeModels.SURFACE_BLOCK);
    }
}
