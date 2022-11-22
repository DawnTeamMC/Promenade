package fr.hugman.promenade.mixin;

import fr.hugman.promenade.content.AmaranthContent;
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
		MaterialRules.MaterialCondition isDarkAmaranthForest = MaterialRules.biome(AmaranthContent.DARK_AMARANTH_FOREST, AmaranthContent.TALL_DARK_AMARANTH_FOREST);

		cir.setReturnValue(MaterialRules.sequence(
				MaterialRules.condition(isDarkAmaranthForest,
						MaterialRules.sequence(
								MaterialRules.condition(
										MaterialRules.STONE_DEPTH_FLOOR,
										VanillaSurfaceRules.block(AmaranthContent.BLACK_DYLIUM)
								)
						)
				),
				previousRules
		));
	}
}