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
package fr.hugman.promenade.client.render.entity.state;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import fr.hugman.promenade.entity.variant.SunkenVariant;

@Environment(EnvType.CLIENT)
public class SunkenEntityRenderState extends SkeletonEntityRenderState {
	@Nullable
	public SunkenVariant variant;
}
