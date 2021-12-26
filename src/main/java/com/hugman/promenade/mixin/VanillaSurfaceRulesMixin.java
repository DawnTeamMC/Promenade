package com.hugman.promenade.mixin;

import com.hugman.promenade.init.AmaranthBundle;
import com.hugman.promenade.util.BiomeUtil;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
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
		MaterialRules.MaterialRule rules = cir.getReturnValue();
		MaterialRules.MaterialCondition surface = MaterialRules.aboveY(YOffset.fixed(31), 0);
		MaterialRules.MaterialCondition darkAmaranthForest = MaterialRules.biome(BiomeUtil.DARK_AMARANTH_FOREST_KEY, BiomeUtil.TALL_DARK_AMARANTH_FOREST_KEY);

		cir.setReturnValue(MaterialRules.sequence(
				MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR,
						MaterialRules.sequence(
								MaterialRules.condition(
										darkAmaranthForest,
										MaterialRules.block(AmaranthBundle.BLACK_DYLIUM.getDefaultState())
								)
						)
				),
				rules
		));
	}
}
