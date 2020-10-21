package com.hugman.wild_explorer.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

@Config(name = "wild_explorer")
@Config.Gui.Background("minecraft:textures/block/lime_concrete.png")
public class WEConfig extends PartitioningSerializer.GlobalData {
	@ConfigEntry.Category("world_generation")
	@ConfigEntry.Gui.TransitiveObject
	public WorldGenerationCategory worldGeneration = new WorldGenerationCategory();

	@Config(name = "world_generation")
	public static class WorldGenerationCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean pumpkinPastures = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean pinkCherryOakForest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean whiteCherryOakForest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tallCrimsonForest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tallWarpedForest = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean tritanopianGallery = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean achromatopsianGallery = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean protanopianGallery = true;
	}
}
