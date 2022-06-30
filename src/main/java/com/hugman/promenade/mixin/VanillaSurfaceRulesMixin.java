package com.hugman.promenade.mixin;

import com.hugman.promenade.init.AmaranthBundle;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaSurfaceRules.class)
public class VanillaSurfaceRulesMixin {
	@Inject(method = "getEndStoneRule", at = @At("RETURN"), cancellable = true)
	private static void promenade$appendEndSurfaces(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
		MaterialRules.MaterialRule previousRules = cir.getReturnValue();
		MaterialRules.MaterialCondition isSurface = MaterialRules.aboveY(YOffset.fixed(31), 0);
		MaterialRules.MaterialCondition isDarkAmaranthForest = MaterialRules.biome(AmaranthBundle.DARK_AMARANTH_FOREST, AmaranthBundle.TALL_DARK_AMARANTH_FOREST);

		cir.setReturnValue(MaterialRules.sequence(
				MaterialRules.condition(isDarkAmaranthForest,
						MaterialRules.sequence(
								MaterialRules.condition(
										MaterialRules.STONE_DEPTH_FLOOR,
										MaterialRules.block(AmaranthBundle.BLACK_DYLIUM.getDefaultState())
								)
						)
				),
				previousRules
		));
	}
}