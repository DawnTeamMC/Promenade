package fr.hugman.promenade.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.tag.PromenadeEntityTypeTags.*;
import static fr.hugman.promenade.references.PromenadeEntityTypeIds.*;


public class PromenadeEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public PromenadeEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Vanilla
        builder(EntityTypeTags.SKELETONS).add(SUNKEN);
        builder(EntityTypeTags.BOAT).add(SAKURA_BOAT, MAPLE_BOAT, PALM_BOAT);
        builder(EntityTypeTags.AQUATIC).add(SUNKEN, CAPYBARA);

        builder(EntityTypeTags.BURN_IN_DAYLIGHT).add(SUNKEN);

        builder(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(SUNKEN);
        builder(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(DUCK);
        builder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(DUCK);
        builder(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(DUCK);
        builder(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(CAPYBARA);
        builder(EntityTypeTags.CANDIDATE_FOR_IRON_GOLEM_GIFT).add(CAPYBARA);
        builder(EntityTypeTags.FOLLOWABLE_FRIENDLY_MOBS).add(DUCK, CAPYBARA);

        // Conventional
        builder(ANIMALS).add(DUCK, CAPYBARA);
        builder(MONSTERS).add(LUSH_CREEPER);

        builder(BIRDS).add(DUCK);
        builder(RODENTS).add(CAPYBARA);
        builder(CREEPERS).add(LUSH_CREEPER);
    }
}