package fr.hugman.promenade.data.model;

import net.minecraft.block.Block;
import net.minecraft.client.data.*;

public class PromenadeTexturedModels {
    public static final TexturedModel.Factory PILE = TexturedModel.makeFactory(TextureMap::all, PromenadeModels.PILE);

    public static TexturedModel.Factory pile(Block block) {
        return TexturedModel.makeFactory(b -> TextureMap.all(block), PromenadeModels.PILE);
    }
}
