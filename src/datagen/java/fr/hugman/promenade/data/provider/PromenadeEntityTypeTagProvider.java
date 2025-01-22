package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.PromenadeEntityTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class PromenadeEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public PromenadeEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Vanilla
        getOrCreateTagBuilder(EntityTypeTags.SKELETONS).add(PromenadeEntityTypes.SUNKEN);
        getOrCreateTagBuilder(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES).add(PromenadeEntityTypes.SUNKEN);
        getOrCreateTagBuilder(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(PromenadeEntityTypes.DUCK);
        getOrCreateTagBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(PromenadeEntityTypes.DUCK);

        getOrCreateTagBuilder(EntityTypeTags.BOAT).add(PromenadeEntityTypes.SAKURA_BOAT, PromenadeEntityTypes.MAPLE_BOAT, PromenadeEntityTypes.PALM_BOAT);
    }
}