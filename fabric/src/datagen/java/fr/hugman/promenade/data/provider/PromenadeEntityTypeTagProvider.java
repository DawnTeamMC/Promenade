package fr.hugman.promenade.data.provider;



import net.minecraft.world.entity.EntityType;
import net.minecraft.tags.TagKey;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

import static fr.hugman.promenade.tag.PromenadeEntityTypeTags.*;
import static fr.hugman.promenade.references.PromenadeEntityTypeIds.*;


public class PromenadeEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public PromenadeEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Vanilla
        builder(EntityTypeTags.SKELETONS).add(SUNKEN);
        builder(EntityTypeTags.AQUATIC).add(SUNKEN, CAPYBARA);


        builder(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(SUNKEN);
        builder(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(DUCK);
        builder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(DUCK);
        builder(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(DUCK);
        builder(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH).add(CAPYBARA);

        // Conventional
        builder(ANIMALS).add(DUCK, CAPYBARA);
        builder(MONSTERS).add(LUSH_CREEPER);

        builder(BIRDS).add(DUCK);
        builder(RODENTS).add(CAPYBARA);
        builder(CREEPERS).add(LUSH_CREEPER);
    }

    private PromenadeTagBuilder<EntityType<?>> builder(TagKey<EntityType<?>> tag) {
        return new PromenadeTagBuilder<EntityType<?>>(this.getOrCreateTagBuilder(tag), null);
    }
}
