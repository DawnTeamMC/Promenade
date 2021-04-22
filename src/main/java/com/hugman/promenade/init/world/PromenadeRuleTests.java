package com.hugman.promenade.init.world;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;

public class PromenadeRuleTests {
	public static final RuleTest ANDESITE_RULE = new BlockMatchRuleTest(Blocks.ANDESITE);
	public static final RuleTest DIORITE_RULE = new BlockMatchRuleTest(Blocks.DIORITE);
	public static final RuleTest GRANITE_RULE = new BlockMatchRuleTest(Blocks.GRANITE);
	public static final RuleTest TUFF_RULE = new BlockMatchRuleTest(Blocks.TUFF);
}
