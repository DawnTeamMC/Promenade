package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.SunkenVariant;
import fr.hugman.promenade.entity.variant.SunkenVariants;
import fr.hugman.promenade.loot.PromenadeLootTables;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.ClientAsset.ResourceTexture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.level.storage.loot.LootTable;
import java.util.concurrent.CompletableFuture;

//TODO: a generic class for other devs
public class PromenadeSunkenVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeSunkenVariantProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(PromenadeRegistryKeys.SUNKEN_VARIANT));
    }

    @Override
    public String getName() {
        return "Sunken Variants";
    }

    public static void register(BootstrapContext<SunkenVariant> registry) {
        of(registry, SunkenVariants.TUBE, PromenadeLootTables.TUBE_SUNKEN);
        of(registry, SunkenVariants.BRAIN, PromenadeLootTables.BRAIN_SUNKEN);
        of(registry, SunkenVariants.BUBBLE, PromenadeLootTables.BUBBLE_SUNKEN);
        of(registry, SunkenVariants.FIRE, PromenadeLootTables.FIRE_SUNKEN);
        of(registry, SunkenVariants.HORN, PromenadeLootTables.HORN_SUNKEN);
    }

    private static void of(BootstrapContext<SunkenVariant> registry, ResourceKey<SunkenVariant> key, ResourceKey<LootTable> lootTable) {
        registry.register(key, new SunkenVariant(new ResourceTexture(key.identifier().withPrefix("entity/sunken/")), lootTable, SpawnPrioritySelectors.fallback(0)));
    }
}