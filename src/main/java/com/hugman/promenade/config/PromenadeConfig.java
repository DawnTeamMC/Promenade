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
	@ConfigEntry.Category("world_features")
	@ConfigEntry.Gui.TransitiveObject
	public WorldFeaturesCategory world_features = new WorldFeaturesCategory();
	@ConfigEntry.Category("animals")
	@ConfigEntry.Gui.TransitiveObject
	public AnimalsCategory animals = new AnimalsCategory();
	@ConfigEntry.Category("monsters")
	@ConfigEntry.Gui.TransitiveObject
	public MonstersCategory monsters = new MonstersCategory();

	@Config(name = "biomes")
	public static class BiomesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.BoundedDiscrete(min = 0, max = 25)
		public int pumpkin_pastures_weight = 10;
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.BoundedDiscrete(min = 0, max = 25)
		public int cherry_oak_forests_weight = 10;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tall_nether_forests = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean nether_galleries = true;
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.BoundedDiscrete(min = 0, max = 25)
		public int dark_amaranth_forests_weight = 10;
	}

	@Config(name = "world_features")
	public static class WorldFeaturesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean witch_huts = true;
		@ConfigEntry.Gui.RequiresRestart
		@ConfigEntry.Gui.Tooltip
		public boolean igneous_rock_patches = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean palms = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean blueberry_bushes = true;
	}

	@Config(name = "animals")
	public static class AnimalsCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean ducks = true;
	}

	@Config(name = "monsters")
	public static class MonstersCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean lush_creepers = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean sunken_skeletons = true;
	}
}
