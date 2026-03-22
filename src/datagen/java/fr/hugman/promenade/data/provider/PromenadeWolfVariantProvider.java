package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.PromenadeWolfVariants;
import fr.hugman.promenade.tag.PromenadeBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.ClientAsset.ResourceTexture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.wolf.WolfVariant;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.level.biome.Biome;
import java.util.concurrent.CompletableFuture;

public class PromenadeWolfVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeWolfVariantProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        var wrapper = registries.lookupOrThrow(Registries.WOLF_VARIANT);
        entries.add(wrapper, PromenadeWolfVariants.SHIBA_INU);
    }

    @Override
    public String getName() {
        return "Wolf Variants";
    }

    public static void register(BootstrapContext<WolfVariant> registry) {
        of(registry, PromenadeWolfVariants.SHIBA_INU, PromenadeBiomeTags.SAKURA_GROVES);
    }

    private static void of(BootstrapContext<WolfVariant> registry, ResourceKey<WolfVariant> key, TagKey<Biome> biomeTag) {
        of(registry, key, DataProviderUtil.createSpawnConditions(registry.lookup(Registries.BIOME).getOrThrow(biomeTag)));
    }

    private static void of(BootstrapContext<WolfVariant> registry, ResourceKey<WolfVariant> key, SpawnPrioritySelectors spawnConditions) {
        var baseId = key.identifier().withPrefix("entity/wolf/");
        registry.register(key, new WolfVariant(
                new WolfVariant.AssetInfo(
                        new ResourceTexture(baseId.withSuffix("/wild")),
                        new ResourceTexture(baseId.withSuffix("/tame")),
                        new ResourceTexture(baseId.withSuffix("/angry"))
                ),
                spawnConditions
        ));
    }
}