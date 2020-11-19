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
	@ConfigEntry.Category("creatures")
	@ConfigEntry.Gui.TransitiveObject
	public CreaturesCategory creatures = new CreaturesCategory();

	@Config(name = "biomes")
	public static class BiomesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean pumpkin_pastures = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean pink_cherry_oak_forest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean white_cherry_oak_forest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tall_crimson_forest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tall_warped_forest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tritanopian_gallery = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean achromatopsian_gallery = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean protanopian_gallery = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean dark_amaranth_forest = true;
	}

	@Config(name = "features")
	public static class FeaturesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean blunite_patch = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean carbonite_patch = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean palm_tree = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean blueberry_bush = true;
	}

	@Config(name = "creatures")
	public static class CreaturesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean duck = true;
	}
}
