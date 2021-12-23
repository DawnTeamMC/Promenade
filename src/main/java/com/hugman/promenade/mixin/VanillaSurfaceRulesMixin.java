package com.hugman.promenade.mixin;

import com.hugman.promenade.init.BiomeKeys;
import com.hugman.promenade.init.GalleryBundle;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaSurfaceRules.class)
public class VanillaSurfaceRulesMixin {
	@Final @Shadow
	private static MaterialRules.MaterialRule LAVA, NETHERRACK, GRAVEL, SOUL_SAND, CRIMSON_NYLIUM, NETHER_WART_BLOCK, WARPED_NYLIUM, WARPED_WART_BLOCK;

	@Inject(method = "createNetherSurfaceRule", at = @At("RETURN"), cancellable = true)
	private static void promenade$appendNetherSurfaces(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
		MaterialRules.MaterialRule rules = cir.getReturnValue();
		MaterialRules.MaterialCondition materialCondition = MaterialRules.aboveY(YOffset.fixed(31), 0);
		MaterialRules.MaterialCondition materialCondition2 = MaterialRules.aboveY(YOffset.fixed(32), 0);
		MaterialRules.MaterialCondition materialCondition3 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(30), 0);
		MaterialRules.MaterialCondition materialCondition4 = MaterialRules.not(MaterialRules.aboveYWithStoneDepth(YOffset.fixed(35), 0));
		MaterialRules.MaterialCondition materialCondition6 = MaterialRules.hole();
		MaterialRules.MaterialCondition materialCondition7 = MaterialRules.noiseThreshold(NoiseParametersKeys.SOUL_SAND_LAYER, -0.012);
		MaterialRules.MaterialCondition materialCondition8 = MaterialRules.noiseThreshold(NoiseParametersKeys.GRAVEL_LAYER, -0.012);
		MaterialRules.MaterialCondition materialCondition10 = MaterialRules.noiseThreshold(NoiseParametersKeys.NETHERRACK, 0.54);
		MaterialRules.MaterialCondition materialCondition11 = MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_WART, 1.17);
		cir.setReturnValue(MaterialRules.sequence(rules,
				MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(materialCondition2), MaterialRules.condition(materialCondition6, LAVA)),

						MaterialRules.condition(MaterialRules.biome(BiomeKeys.TALL_WARPED_FOREST),
								MaterialRules.condition(MaterialRules.not(materialCondition10),
										MaterialRules.condition(materialCondition, MaterialRules.sequence(MaterialRules.condition(materialCondition11, WARPED_WART_BLOCK), WARPED_NYLIUM)))),

						MaterialRules.condition(MaterialRules.biome(BiomeKeys.TALL_CRIMSON_FOREST),
								MaterialRules.condition(MaterialRules.not(materialCondition10),
										MaterialRules.condition(materialCondition, MaterialRules.sequence(MaterialRules.condition(materialCondition11, NETHER_WART_BLOCK), CRIMSON_NYLIUM)))))),

				MaterialRules.condition(MaterialRules.biome(BiomeKeys.ACHROMATOPSIAN_GALLERY, BiomeKeys.PROTANOPIAN_GALLERY, BiomeKeys.TRITANOPIAN_GALLERY),
						MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(materialCondition7,
										MaterialRules.sequence(MaterialRules.condition(MaterialRules.not(materialCondition6),
												MaterialRules.condition(materialCondition3, MaterialRules.condition(materialCondition4, SOUL_SAND))), NETHERRACK))),
								MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.condition(materialCondition, MaterialRules.condition(materialCondition4, MaterialRules.condition(materialCondition8,
										MaterialRules.sequence(MaterialRules.condition(materialCondition2, GRAVEL), MaterialRules.condition(MaterialRules.not(materialCondition6), GRAVEL)))))))), NETHERRACK));
	}
}
