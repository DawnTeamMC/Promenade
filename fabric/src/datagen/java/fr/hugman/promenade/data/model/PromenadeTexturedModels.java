package fr.hugman.promenade.data.model;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

public class PromenadeTexturedModels {
    public static final TexturedModel.Provider PILE = TexturedModel.createDefault(TextureMapping::cube, PromenadeModels.PILE);
    public static final TexturedModel.Provider FALLEN_LEAVES = TexturedModel.createDefault(TextureMapping::cube, PromenadeModels.FALLEN_LEAVES);

    public static TexturedModel.Provider pile(Block block) {
        return TexturedModel.createDefault(b -> TextureMapping.cube(block), PromenadeModels.PILE);
    }

    public static TexturedModel.Provider fallenLeaves(Block block) {
        return TexturedModel.createDefault(b -> TextureMapping.cube(block), PromenadeModels.FALLEN_LEAVES);
    }
}
