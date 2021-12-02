package com.hugman.promenade.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "promenade")
@Config.Gui.Background("minecraft:textures/block/lime_concrete.png")
public class PromenadeConfig extends PartitioningSerializer.GlobalData {
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
		//TODO: turn this back on when biomes are there again
		@ConfigEntry.Gui.RequiresRestart
		public boolean pumpkin_pastures = false;
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.BoundedDiscrete(min = 1, max = 25)
		public int pumpkin_pastures_weight = 10;
		@ConfigEntry.Gui.RequiresRestart
		public boolean cherry_oak_forests = false;
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.BoundedDiscrete(min = 1, max = 25)
		public int cherry_oak_forests_weight = 10;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tall_nether_forests = false;
		@ConfigEntry.Gui.RequiresRestart
		public boolean nether_galleries = false;
		@ConfigEntry.Gui.RequiresRestart
		public boolean dark_amaranth_forests = false;
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.BoundedDiscrete(min = 1, max = 25)
		public int dark_amaranth_forests_weight = 10;
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
		@ConfigEntry.Gui.RequiresRestart
		public boolean sunken_skeletons = true;
	}
}
