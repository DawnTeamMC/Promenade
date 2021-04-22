package com.hugman.promenade.mixin;

import com.google.common.collect.ImmutableList;
import com.hugman.promenade.init.PromenadeBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {
	@Shadow
	@Final
	@Mutable
	public static ImmutableList<OreFeatureConfig.Target> IRON_ORE_TARGETS;

	@Inject(
			method = "<clinit>",
			at = @At(
					value = "FIELD",
					target = "Lnet/minecraft/world/gen/feature/ConfiguredFeatures;IRON_ORE_TARGETS:Lcom/google/common/collect/ImmutableList;",
					opcode = Opcodes.PUTSTATIC,
					ordinal = 0,
					shift = At.Shift.AFTER)
	)
	private static void addIronOreTargets(CallbackInfo ci) {
		RuleTest ANDESITE_RULE = new BlockMatchRuleTest(Blocks.ANDESITE);
		RuleTest DIORITE_RULE = new BlockMatchRuleTest(Blocks.DIORITE);
		RuleTest GRANITE_RULE = new BlockMatchRuleTest(Blocks.GRANITE);
		RuleTest TUFF_RULE = new BlockMatchRuleTest(Blocks.TUFF);
		IRON_ORE_TARGETS = ImmutableList.<OreFeatureConfig.Target>builder()
				.add(OreFeatureConfig.create(ANDESITE_RULE, PromenadeBlocks.WHITE_MUSHROOM_BLOCK.getDefaultState()))
				.add(OreFeatureConfig.create(DIORITE_RULE, PromenadeBlocks.WHITE_MUSHROOM_BLOCK.getDefaultState()))
				.add(OreFeatureConfig.create(GRANITE_RULE, PromenadeBlocks.WHITE_MUSHROOM_BLOCK.getDefaultState()))
				.add(OreFeatureConfig.create(TUFF_RULE, PromenadeBlocks.WHITE_MUSHROOM_BLOCK.getDefaultState()))
				.addAll(IRON_ORE_TARGETS)
				.build();
	}
}