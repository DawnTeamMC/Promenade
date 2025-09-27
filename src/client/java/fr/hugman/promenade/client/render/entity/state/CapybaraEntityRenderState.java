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

import fr.hugman.promenade.entity.variant.CapybaraVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class CapybaraEntityRenderState extends LivingEntityRenderState {
	public final AnimationState earWiggleAnimState = new AnimationState();
	public final AnimationState fallToSleepAnimState = new AnimationState();
	public final AnimationState sleepingAnimState = new AnimationState();
	public final AnimationState wakeUpAnimState = new AnimationState();
	public final AnimationState fartAnimState = new AnimationState();

	@Nullable
	public CapybaraVariant variant;
	public boolean closedEyes = false;
	public boolean largeEyes = false;
	public float earWiggleSpeed = 1.0f;

	public boolean canAngleHead = true;
}
