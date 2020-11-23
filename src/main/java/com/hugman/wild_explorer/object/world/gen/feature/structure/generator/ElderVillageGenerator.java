package com.hugman.wild_explorer.object.world.gen.feature.structure.generator;

import com.hugman.wild_explorer.init.world.WEStructurePieces;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

import java.util.List;
import java.util.Random;

public class ElderVillageGenerator {
	private static final Identifier IGLOO_TOP = new Identifier("igloo/top");

	public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieces) {
		pieces.add(new MyPiece(manager, pos, IGLOO_TOP, rotation));
	}

	public static class MyPiece extends SimpleStructurePiece {
		private final BlockRotation rotation;
		private final Identifier template;

		public MyPiece(StructureManager structureManager, CompoundTag compoundTag) {
			super(WEStructurePieces.ELDER_VILLAGE_PIECE, compoundTag);
			this.template = new Identifier(compoundTag.getString("Template"));
			this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));
			this.initializeStructureData(structureManager);
		}

		public MyPiece(StructureManager structureManager, BlockPos pos, Identifier template, BlockRotation rotation) {
			super(WEStructurePieces.ELDER_VILLAGE_PIECE, 0);
			this.pos = pos;
			this.rotation = rotation;
			this.template = template;

			this.initializeStructureData(structureManager);
		}

		private void initializeStructureData(StructureManager structureManager) {
			Structure structure = structureManager.getStructureOrBlank(this.template);
			StructurePlacementData placementData = (new StructurePlacementData())
					.setRotation(this.rotation)
					.setMirror(BlockMirror.NONE)
					.addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
			this.setStructureData(structure, this.pos, placementData);
		}

		protected void toNbt(CompoundTag tag) {
			super.toNbt(tag);
			tag.putString("Template", this.template.toString());
			tag.putString("Rot", this.rotation.name());
		}

		@Override
		protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random, BlockBox boundingBox) {
		}
	}
}
