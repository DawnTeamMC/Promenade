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
package fr.hugman.promenade.mixin;

import fr.hugman.promenade.block.PromenadeBlocks;
import fr.hugman.promenade.entity.damage.PromenadeFallLocations;
import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.FallLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FallLocation.class)
public class FallLocationMixin {
	@Inject(method="fromBlockState", at=@At("HEAD"), cancellable=true)
	private static void promenade$fromBlockState(BlockState state, CallbackInfoReturnable<FallLocation> cir) {
		if (state.isOf(PromenadeBlocks.COILED_VINES) || state.isOf(PromenadeBlocks.COILED_VINES_PLANT)) {
			cir.setReturnValue(PromenadeFallLocations.COILED_VINES);
		}
	}
}
