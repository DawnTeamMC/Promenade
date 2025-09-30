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
