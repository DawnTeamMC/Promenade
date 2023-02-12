package fr.hugman.promenade.registry.content;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.block.SignBlocks;
import fr.hugman.dawn.block.sapling.OakLikeSaplingGenerator;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.PromenadeFactory;
import fr.hugman.promenade.item.ItemGroupHelper;
import fr.hugman.promenade.village.TradeOfferUtils;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;

public class SakuraContent {
	private static final MapColor BARK_COLOR = MapColor.DARK_DULL_PINK;
	private static final MapColor WOOD_COLOR = MapColor.DULL_PINK;
	private static final MapColor PLANKS_COLOR = MapColor.DULL_RED;

	public static final Block STRIPPED_SAKURA_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block SAKURA_LOG = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR, BARK_COLOR).stripInto(STRIPPED_SAKURA_LOG));
	public static final Block STRIPPED_SAKURA_WOOD = new PillarBlock(DawnFactory.logSettings(false, WOOD_COLOR));
	public static final Block SAKURA_WOOD = new PillarBlock(DawnFactory.logSettings(false, BARK_COLOR).stripInto(STRIPPED_SAKURA_WOOD));

	public static final Block SAKURA_PLANKS = DawnFactory.planks(false, PLANKS_COLOR);
	public static final Block SAKURA_STAIRS = DawnFactory.stairs(SAKURA_PLANKS);
	public static final Block SAKURA_SLAB = DawnFactory.slab(SAKURA_PLANKS);
	public static final Block SAKURA_FENCE = DawnFactory.fence(false, SAKURA_PLANKS);
	public static final Block SAKURA_FENCE_GATE = DawnFactory.fenceGate(false, SAKURA_PLANKS);
	public static final Block SAKURA_DOOR = DawnFactory.woodenDoor(SAKURA_PLANKS);
	public static final Block SAKURA_TRAPDOOR = DawnFactory.woodenTrapdoor(SAKURA_PLANKS);
	public static final Block SAKURA_BUTTON = DawnFactory.woodenButton(false);
	public static final Block SAKURA_PRESSURE_PLATE = DawnFactory.pressurePlate(SAKURA_PLANKS);

	public static final SignBlocks SAKURA_SIGNS = DawnFactory.signs(Promenade.id("sakura"), SAKURA_PLANKS);
	public static final TerraformBoatType SAKURA_BOAT_TYPE = DawnFactory.boat(Promenade.id("sakura"), Items.OAK_PLANKS); //TODO change when possible (PR #72 on TerraformersMC/Terraform)

	public static final Block BLUSH_SAKURA_SAPLING = DawnFactory.sapling(OakLikeSaplingGenerator.of(Promenade.id("sakura/blush")));
	public static final Block POTTED_BLUSH_SAKURA_SAPLING = DawnFactory.potted(BLUSH_SAKURA_SAPLING);
	public static final Block BLUSH_SAKURA_BLOSSOMS = DawnFactory.leaves(BlockSoundGroup.AZALEA_LEAVES);
	public static final Block BLUSH_SAKURA_BLOSSOM_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);
	public static final DefaultParticleType BLUSH_SAKURA_BLOSSOM = FabricParticleTypes.simple();

	public static final Block COTTON_SAKURA_SAPLING = DawnFactory.sapling(OakLikeSaplingGenerator.of(Promenade.id("sakura/cotton")));
	public static final Block POTTED_COTTON_SAKURA_SAPLING = DawnFactory.potted(COTTON_SAKURA_SAPLING);
	public static final Block COTTON_SAKURA_BLOSSOMS = DawnFactory.leaves(BlockSoundGroup.AZALEA_LEAVES);
	public static final Block COTTON_SAKURA_BLOSSOM_PILE = PromenadeFactory.leafPile(BlockSoundGroup.AZALEA_LEAVES);
	public static final DefaultParticleType COTTON_SAKURA_BLOSSOM = FabricParticleTypes.simple();

	public static final RegistryKey<Biome> BLUSH_SAKURA_GROVE = DawnFactory.biome(Promenade.id("blush_sakura_grove"));
	public static final RegistryKey<Biome> COTTON_SAKURA_GROVE = DawnFactory.biome(Promenade.id("cotton_sakura_grove"));

	public static void register(Registrar r) {
		r.add(("sakura_log"), SAKURA_LOG);
		r.add(("stripped_sakura_log"), STRIPPED_SAKURA_LOG);
		r.add(("sakura_wood"), SAKURA_WOOD);
		r.add(("stripped_sakura_wood"), STRIPPED_SAKURA_WOOD);

		r.add(("sakura_planks"), SAKURA_PLANKS);
		r.add(("sakura_stairs"), SAKURA_STAIRS);
		r.add(("sakura_slab"), SAKURA_SLAB);
		r.add(("sakura_fence"), SAKURA_FENCE);
		r.add(("sakura_fence_gate"), SAKURA_FENCE_GATE);
		r.add(("sakura_door"), SAKURA_DOOR);
		r.add(("sakura_trapdoor"), SAKURA_TRAPDOOR);
		r.add(("sakura_button"), SAKURA_BUTTON);
		r.add(("sakura_pressure_plate"), SAKURA_PRESSURE_PLATE);

		r.add(("sakura"), SAKURA_SIGNS);
		r.add(("sakura"), SAKURA_BOAT_TYPE);

		r.add(("blush_sakura_sapling"), BLUSH_SAKURA_SAPLING);
		r.add(("potted_blush_sakura_sapling"), POTTED_BLUSH_SAKURA_SAPLING);
		r.add(("blush_sakura_blossoms"), BLUSH_SAKURA_BLOSSOMS);
		r.add(("blush_sakura_blossom_pile"), BLUSH_SAKURA_BLOSSOM_PILE);
		r.add(("blush_sakura_blossom"), BLUSH_SAKURA_BLOSSOM);

		r.add(("cotton_sakura_sapling"), COTTON_SAKURA_SAPLING);
		r.add(("potted_cotton_sakura_sapling"), POTTED_COTTON_SAKURA_SAPLING);
		r.add(("cotton_sakura_blossoms"), COTTON_SAKURA_BLOSSOMS);
		r.add(("cotton_sakura_blossom_pile"), COTTON_SAKURA_BLOSSOM_PILE);
		r.add(("cotton_sakura_blossom"), COTTON_SAKURA_BLOSSOM);

		if(Promenade.CONFIG.biomes.sakura_groves_weight > 0) {
			//TODO : add back to overworld generation when the Biome API supports that
			//OverworldBiomes.addContinentalBiome(BLUSH_SAKURA_GROVE.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.sakura_forests_weight / 10.0D)
			//OverworldBiomes.addContinentalBiome(PASTEL_SAKURA_GROVE.getRegistryKey(), OverworldClimate.COOL, BIOMES_CONFIG.sakura_forests_weight / 10.0D);
		}

		TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
			factories.add(TradeOfferUtils.sapling(BLUSH_SAKURA_SAPLING));
			factories.add(TradeOfferUtils.sapling(COTTON_SAKURA_SAPLING));
		});


		ItemGroupHelper.append(ItemGroups.BUILDING_BLOCKS, e -> e.addAfter(Blocks.BIRCH_BUTTON,
				SAKURA_LOG,
				SAKURA_WOOD,
				STRIPPED_SAKURA_LOG,
				STRIPPED_SAKURA_WOOD,
				SAKURA_PLANKS,
				SAKURA_STAIRS,
				SAKURA_SLAB,
				SAKURA_FENCE,
				SAKURA_FENCE_GATE,
				SAKURA_DOOR,
				SAKURA_TRAPDOOR,
				SAKURA_PRESSURE_PLATE,
				SAKURA_BUTTON));

		ItemGroupHelper.append(ItemGroups.NATURAL, e -> {
			e.addAfter(Blocks.BIRCH_LOG, SAKURA_LOG);
			e.addAfter(Blocks.BIRCH_LEAVES, BLUSH_SAKURA_BLOSSOMS, COTTON_SAKURA_BLOSSOMS);
			e.addAfter(Blocks.BIRCH_SAPLING, BLUSH_SAKURA_SAPLING, COTTON_SAKURA_SAPLING);
			e.addAfter(VanillaPilesContent.BIRCH_LEAF_PILE, BLUSH_SAKURA_BLOSSOM_PILE, COTTON_SAKURA_BLOSSOM_PILE);
		});
		ItemGroupHelper.append(ItemGroups.FUNCTIONAL, e -> e.addAfter(Blocks.BIRCH_HANGING_SIGN, SAKURA_SIGNS.sign(), SAKURA_SIGNS.hangingSign()));
		ItemGroupHelper.append(ItemGroups.TOOLS, e -> e.addAfter(Items.BIRCH_CHEST_BOAT, SAKURA_BOAT_TYPE.getItem(), SAKURA_BOAT_TYPE.getChestItem()));
	}
}
