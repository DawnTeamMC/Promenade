package com.hugman.wild_explorer.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.pack.block.*;
import com.hugman.dawn.api.object.block.RootsBlock;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.BlockSettings;
import com.hugman.wild_explorer.init.world.WEConfiguredFeatures;
import com.hugman.wild_explorer.object.block.BlueberryBushBlock;
import com.hugman.wild_explorer.object.block.DyliumBlock;
import com.hugman.wild_explorer.object.block.sapling_generator.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;

public class WEBlockPack extends WEPack {
	public static final PottedPlantPack AUTUMN_OAK_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("autumn_oak_sapling", new SaplingBlock(new AutumnOakSaplingGenerator(), BlockSettings.SAPLING))));
	public static final LeavesPack AUTUMN_OAK_LEAVES = register(new LeavesPack.Builder("autumn_oak"));
	public static final PottedPlantPack AUTUMN_BIRCH_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("autumn_birch_sapling", new SaplingBlock(new AutumnBirchSaplingGenerator(), BlockSettings.SAPLING))));
	public static final LeavesPack AUTUMN_BIRCH_LEAVES = register(new LeavesPack.Builder("autumn_birch"));

	public static final WoodPack CHERRY_OAK_WOOD = register(new WoodPack.Builder("cherry_oak", MaterialColor.field_25702, MaterialColor.field_25703, MaterialColor.field_25707, false));
	public static final PottedPlantPack PINK_CHERRY_OAK_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("pink_cherry_oak_sapling", new SaplingBlock(new PinkCherryOakSaplingGenerator(), BlockSettings.SAPLING))));
	public static final PottedPlantPack WHITE_CHERRY_OAK_SAPLING = register(new PottedPlantPack.Builder(new BlockCreator.Builder("white_cherry_oak_sapling", new SaplingBlock(new WhiteCherryOakSaplingGenerator(), BlockSettings.SAPLING))));
	public static final LeavesPack PINK_CHERRY_OAK_LEAVES = register(new LeavesPack.Builder("pink_cherry_oak"));
	public static final LeavesPack WHITE_CHERRY_OAK_LEAVES = register(new LeavesPack.Builder("white_cherry_oak"));

	public static final NormalWoodPack PALM_WOOD = register(new NormalWoodPack.Builder("palm", new PalmSaplingGenerator(), MaterialColor.ORANGE, MaterialColor.CYAN_TERRACOTTA));

	public static final Block AMARANTH_DYLIUM = register(new BlockCreator.Builder("amaranth_dylium", new DyliumBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.field_25702)
																																		.requiresTool()
																																		.strength(3.0F, 9.0F)
																																		.sounds(BlockSoundGroup.NYLIUM)
																																		.ticksRandomly())).itemGroup(ItemGroup.BUILDING_BLOCKS));
	public static final Block AMARANTH_WART_BLOCK = register(new BlockCreator.Builder("amaranth_wart_block", new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC, MaterialColor.field_25708).breakByTool(FabricToolTags.HOES).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK))));
	public static final Block AMARANTH_ROOTS = register(new BlockCreator.Builder("amaranth_roots", new RootsBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MaterialColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.ROOTS))).itemGroup(ItemGroup.DECORATIONS)
																																																													   .render(BlockCreator.Render.CUTOUT));
	public static final NetherWoodPack DARK_AMARANTH_WOOD = register(new NetherWoodPack.Builder("dark_amaranth", () -> WEConfiguredFeatures.AMARANTH_FUNGI_PLANTED, MaterialColor.LIGHT_GRAY, MaterialColor.field_25707));

	public static final Block BLUEBERRY_BUSH = register(new BlockCreator.Builder("blueberry_bush", new BlueberryBushBlock(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH))).flammability(60, 100)
																																																										 .render(BlockCreator.Render.CUTOUT)
																																																										 .noItem());
}
