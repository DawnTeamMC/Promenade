package com.hugman.promenade.object.world.gen.feature.structure.generator;

import com.google.common.collect.ImmutableList;
import com.hugman.dawn.api.util.DefaultBlockGetter;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.PromenadeBlocks;
import com.hugman.promenade.init.data.PromenadeLootTables;
import com.hugman.promenade.init.data.PromenadeTags;
import com.hugman.promenade.init.world.PromenadeStructurePieces;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

import java.util.List;
import java.util.Random;

public class WitchHutGenerator {
	public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, BlockMirror mirror, List<StructurePiece> pieces) {
		pieces.add(new MainPiece(manager, pos, Promenade.MOD_DATA.id("witch_hut"), rotation, mirror));
	}

	public static class MainPiece extends SimpleStructurePiece {
		private final BlockRotation rotation;
		private final Identifier template;
		private final BlockMirror mirror;

		public MainPiece(StructureManager manager, CompoundTag tag) {
			super(PromenadeStructurePieces.WITCH_HUT_PIECE, tag);
			this.template = new Identifier(tag.getString("Template"));
			this.rotation = BlockRotation.valueOf(tag.getString("Rot"));
			this.mirror = BlockMirror.valueOf(tag.getString("Mirror"));
			this.initializeStructureData(manager);
		}

		public MainPiece(StructureManager manager, BlockPos pos, Identifier template, BlockRotation rotation, BlockMirror mirror) {
			super(PromenadeStructurePieces.WITCH_HUT_PIECE, 0);
			this.pos = pos;
			this.rotation = rotation;
			this.template = template;
			this.mirror = mirror;

			this.initializeStructureData(manager);
		}

		private void initializeStructureData(StructureManager manager) {
			Random random = new Random();
			Structure structure = manager.getStructureOrBlank(this.template);
			StructurePlacementData placementData = (new StructurePlacementData())
					.setRotation(this.rotation)
					.setMirror(this.mirror)
					.addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS)
					.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))
					.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new TagMatchRuleTest(PromenadeTags.Blocks.POTTED_MUSHROOMS), AlwaysTrueRuleTest.INSTANCE, PromenadeTags.Blocks.POTTED_MUSHROOMS.getRandom(random).getDefaultState()))));
			if(random.nextBoolean()) {
				placementData.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new BlockMatchRuleTest(PromenadeBlocks.POLISHED_CARBONITE.getBlock(DefaultBlockGetter.CUBE)), AlwaysTrueRuleTest.INSTANCE, Blocks.POLISHED_ANDESITE.getDefaultState()))));
				placementData.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new BlockMatchRuleTest(PromenadeBlocks.CARBONITE.getBlock(DefaultBlockGetter.WALL)), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_WALL.getDefaultState()))));
			}
			if(random.nextFloat() < 0.2f) {
				placementData.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new BlockMatchRuleTest(Blocks.LANTERN), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_LANTERN.getDefaultState().with(LanternBlock.HANGING, true)))));
			}
			this.setStructureData(structure, this.pos, placementData);
		}

		protected void toNbt(CompoundTag tag) {
			super.toNbt(tag);
			tag.putString("Template", this.template.toString());
			tag.putString("Rot", this.rotation.name());
			tag.putString("Mirror", this.mirror.name());
		}

		@Override
		protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random, BlockBox boundingBox) {
			if(metadata.startsWith("Barrel")) {
				BlockEntity blockEntity = serverWorldAccess.getBlockEntity(pos.down());
				if(blockEntity instanceof LootableContainerBlockEntity) {
					((LootableContainerBlockEntity) blockEntity).setLootTable(PromenadeLootTables.WITCH_HUT_CHEST, random.nextLong());
				}
			}
			else if(metadata.startsWith("Witch")) {
				WitchEntity witchEntity = EntityType.WITCH.create(serverWorldAccess.toServerWorld());
				witchEntity.setPersistent();
				witchEntity.refreshPositionAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
				witchEntity.initialize(serverWorldAccess, serverWorldAccess.getLocalDifficulty(pos), SpawnReason.STRUCTURE, null, null);
				serverWorldAccess.spawnEntityAndPassengers(witchEntity);
			}
		}
	}
}
