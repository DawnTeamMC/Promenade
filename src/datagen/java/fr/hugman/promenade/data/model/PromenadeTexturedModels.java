/*
 * Copyright (c) 2020, 2021, 2022, 2023, 2024, 2025 Hugman
 *
 * This software is licensed under the PolyForm Shield License 1.0.0.
 * You may obtain a copy of the License at
 *
 *      https://polyformproject.org/licenses/shield/1.0.0
 *
 * You may use this software only for non-commercial purposes.
 * For commercial use, you must obtain a separate commercial license.
 */
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
