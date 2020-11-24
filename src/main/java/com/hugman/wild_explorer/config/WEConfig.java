package com.hugman.wild_explorer.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

@Config(name = "wild_explorer")
@Config.Gui.Background("minecraft:textures/block/lime_concrete.png")
public class WEConfig extends PartitioningSerializer.GlobalData {
	@ConfigEntry.Category("biomes")
	@ConfigEntry.Gui.TransitiveObject
	public BiomesCategory biomes = new BiomesCategory();
	@ConfigEntry.Category("features")
	@ConfigEntry.Gui.TransitiveObject
	public FeaturesCategory features = new FeaturesCategory();
	@ConfigEntry.Category("structures")
	@ConfigEntry.Gui.TransitiveObject
	public StructuresCategory structures = new StructuresCategory();
	@ConfigEntry.Category("creatures")
	@ConfigEntry.Gui.TransitiveObject
	public CreaturesCategory creatures = new CreaturesCategory();

	@Config(name = "biomes")
	public static class BiomesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean pumpkin_pastures = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean cherry_oak_forests = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tall_nether_forests = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean nether_galleries = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean dark_amaranth_forests = true;
	}

	@Config(name = "features")
	public static class FeaturesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.Gui.Tooltip
		public boolean igneous_rock_patches = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean palm_trees = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean blueberry_bushes = true;
	}

	@Config(name = "structures")
	public static class StructuresCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean witch_huts = true;
	}

	@Config(name = "creatures")
	public static class CreaturesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean ducks = true;
	}
}
