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

import fr.hugman.promenade.Promenade;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.TextureKey;

import java.util.Optional;

public class PromenadeModels {
	public static final Model PILE = block("pile", TextureKey.ALL);
	public static final Model FALLEN_LEAVES = block("fallen_leaves", TextureKey.ALL);
	public static final Model BOTTOM_SNOWY_LEAVES = block("bottom_snowy_leaves", TextureKey.ALL, TextureKey.TOP, TextureKey.SIDE);

	private static Model make(TextureKey... requiredTextureKeys) {
		return new Model(Optional.empty(), Optional.empty(), requiredTextureKeys);
	}

	private static Model block(String parent, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Promenade.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
	}

	private static Model item(String parent, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Promenade.id("item/" + parent)), Optional.empty(), requiredTextureKeys);
	}

	private static Model openBundle(String parent, String variant, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Promenade.id("item/" + parent)), Optional.of(variant), requiredTextureKeys);
	}

	private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
		return new Model(Optional.of(Promenade.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
	}
}
