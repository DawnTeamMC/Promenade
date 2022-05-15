package com.hugman.promenade.object.world.gen.feature.structure;

import com.google.common.collect.ImmutableList;
import com.hugman.promenade.Promenade;
import com.hugman.promenade.init.OreBundle;
import com.hugman.promenade.init.WitchHutBundle;
import com.hugman.promenade.init.data.PromenadeLootTables;
import com.hugman.promenade.init.data.PromenadeTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.Tag;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Optional;
import java.util.Random;

public class WitchHutGenerator {
	public static void addPieces(StructureManager manager, BlockPos pos, StructurePiecesHolder holder, Random random) {
		BlockRotation rotation = BlockRotation.random(random);
		BlockMirror mirror = random.nextFloat() < 0.5F ? BlockMirror.NONE : BlockMirror.FRONT_BACK;
		holder.addPiece(new MainPiece(manager, Promenade.MOD_DATA.id("witch_hut"), pos, rotation, mirror, random));
	}

	public static class MainPiece extends SimpleStructurePiece {
		public MainPiece(StructureManager manager, NbtCompound nbt) {
			super(WitchHutBundle.WITCH_HUT_PIECE, nbt, manager, (identifier) -> createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")), BlockMirror.valueOf(nbt.getString("Mirror")), new Random()));
		}

		public MainPiece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation, BlockMirror mirror, Random random) {
			super(WitchHutBundle.WITCH_HUT_PIECE, 0, manager, identifier, identifier.toString(), createPlacementData(rotation, mirror, random), pos);
		}

		private static StructurePlacementData createPlacementData(BlockRotation rotation, BlockMirror mirror, Random random) {
			Optional<RegistryEntryList.Named<Block>> optional = Registry.BLOCK.getEntryList(PromenadeTags.Blocks.POTTED_MUSHROOMS);
			Block block = random.nextBoolean() ? Blocks.POTTED_BROWN_MUSHROOM : Blocks.POTTED_RED_MUSHROOM;
			if(optional.isPresent()) {
				Optional<RegistryEntry<Block>> optional1 = optional.get().getRandom(random);
				if(optional1.isPresent()) {
					block = optional1.get().value();
				}
			}
			StructurePlacementData placementData = (new StructurePlacementData())
					.setRotation(rotation)
					.setMirror(mirror)
					.addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS)
					.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))
					.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new TagMatchRuleTest(PromenadeTags.Blocks.POTTED_MUSHROOMS), AlwaysTrueRuleTest.INSTANCE, block.getDefaultState()))));
			if(random.nextBoolean()) {
				placementData.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new BlockMatchRuleTest(OreBundle.POLISHED_CARBONITE.getBlock()), AlwaysTrueRuleTest.INSTANCE, Blocks.POLISHED_ANDESITE.getDefaultState()))));
				placementData.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new BlockMatchRuleTest(OreBundle.CARBONITE.getBlock()), AlwaysTrueRuleTest.INSTANCE, Blocks.ANDESITE_WALL.getDefaultState()))));
			}
			if(random.nextFloat() < 0.2f) {
				placementData.addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new BlockMatchRuleTest(Blocks.LANTERN), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_LANTERN.getDefaultState().with(LanternBlock.HANGING, true)))));
			}
			return placementData;
		}

		@Override
		protected void writeNbt(StructureContext context, NbtCompound nbt) {
			super.writeNbt(context, nbt);
			nbt.putString("Rot", this.placementData.getRotation().name());
			nbt.putString("Mirror", this.placementData.getMirror().name());
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
				if(witchEntity != null) {
					witchEntity.setPersistent();
					witchEntity.refreshPositionAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
					witchEntity.initialize(serverWorldAccess, serverWorldAccess.getLocalDifficulty(pos), SpawnReason.STRUCTURE, null, null);
				}
				serverWorldAccess.spawnEntityAndPassengers(witchEntity);
			}
		}

		@Override
		public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivotPos) {
			BlockPos pos2 = this.pos;
			int i = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, this.pos.getX(), this.pos.getZ());
			this.pos = this.pos.add(0, i - 90 - 1, 0);
			super.generate(world, structureAccessor, chunkGenerator, random, chunkBox, chunkPos, pivotPos);
			this.pos = pos2;
		}
	}
}
