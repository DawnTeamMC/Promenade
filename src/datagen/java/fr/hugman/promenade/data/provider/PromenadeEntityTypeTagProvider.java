package fr.hugman.promenade.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.entity.PromenadeEntityTypes.*;
import static fr.hugman.promenade.tag.PromenadeEntityTypeTags.*;

public class PromenadeEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
	public PromenadeEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		// Vanilla
		valueLookupBuilder(EntityTypeTags.SKELETONS).add(SUNKEN);
		valueLookupBuilder(EntityTypeTags.BOAT).add(SAKURA_BOAT, MAPLE_BOAT, PALM_BOAT);
		valueLookupBuilder(EntityTypeTags.AQUATIC).add(SUNKEN, CAPYBARA);

		valueLookupBuilder(EntityTypeTags.BURN_IN_DAYLIGHT).add(SUNKEN);

		valueLookupBuilder(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(SUNKEN);
		valueLookupBuilder(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(DUCK);
		valueLookupBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(DUCK);
		valueLookupBuilder(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(DUCK);
		valueLookupBuilder(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(CAPYBARA);
		valueLookupBuilder(EntityTypeTags.CANDIDATE_FOR_IRON_GOLEM_GIFT).add(CAPYBARA);
		valueLookupBuilder(EntityTypeTags.FOLLOWABLE_FRIENDLY_MOBS).add(DUCK, CAPYBARA);

		// Conventional
		valueLookupBuilder(ANIMALS).add(DUCK, CAPYBARA);
		valueLookupBuilder(MONSTERS).add(LUSH_CREEPER);

		valueLookupBuilder(BIRDS).add(DUCK);
		valueLookupBuilder(RODENTS).add(CAPYBARA);
		valueLookupBuilder(CREEPERS).add(LUSH_CREEPER);
	}
}