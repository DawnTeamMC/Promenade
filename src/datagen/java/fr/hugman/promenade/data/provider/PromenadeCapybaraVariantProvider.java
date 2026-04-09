package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.spawn.ChanceSpawnCondition;
import fr.hugman.promenade.entity.variant.CapybaraVariant;
import fr.hugman.promenade.entity.variant.CapybaraVariants;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.ClientAsset.ResourceTexture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

import java.util.concurrent.CompletableFuture;

//TODO: a generic class for other devs
public class PromenadeCapybaraVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeCapybaraVariantProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(PromenadeRegistryKeys.CAPYBARA_VARIANT));
    }

    @Override
    public String getName() {
        return "Capybara Variants";
    }

    public static void register(BootstrapContext<CapybaraVariant> registerable) {
        of(registerable, CapybaraVariants.BROWN, SpawnPrioritySelectors.fallback(0));
        of(registerable, CapybaraVariants.ALBINO, 1 / 50f);
    }

    private static void of(BootstrapContext<CapybaraVariant> registry, ResourceKey<CapybaraVariant> key, float rarity) {
        of(registry, key, SpawnPrioritySelectors.single(new ChanceSpawnCondition(rarity), 0));
    }

    private static void of(BootstrapContext<CapybaraVariant> registry, ResourceKey<CapybaraVariant> key, SpawnPrioritySelectors spawnConditions) {
        var baseId = key.identifier().withPrefix("entity/capybara/");
        registry.register(key, new CapybaraVariant(
                new CapybaraVariant.AssetInfo(
                        new ResourceTexture(baseId.withSuffix("/normal")),
                        new ResourceTexture(baseId.withSuffix("/surprised")),
                        new ResourceTexture(baseId.withSuffix("/sleeping"))
                ),
                new CapybaraVariant.AssetInfo(
                        new ResourceTexture(baseId.withSuffix("/normal_baby")),
                        new ResourceTexture(baseId.withSuffix("/normal_baby")),
                        new ResourceTexture(baseId.withSuffix("/sleeping_baby"))
                ),
                spawnConditions
        ));
    }
}