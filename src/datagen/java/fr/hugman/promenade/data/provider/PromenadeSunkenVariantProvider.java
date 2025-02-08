package fr.hugman.promenade.data.provider;

import fr.hugman.promenade.entity.variant.SunkenVariant;
import fr.hugman.promenade.entity.variant.SunkenVariants;
import fr.hugman.promenade.loot.PromenadeLootTables;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

//TODO: a generic class for other devs
public class PromenadeSunkenVariantProvider extends FabricDynamicRegistryProvider {
    public PromenadeSunkenVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getOrThrow(PromenadeRegistryKeys.SUNKEN_VARIANT));
    }

    @Override
    public String getName() {
        return "Sunken Variants";
    }

    public static void register(Registerable<SunkenVariant> registerable) {
        of(registerable, SunkenVariants.TUBE, PromenadeLootTables.TUBE_SUNKEN);
        of(registerable, SunkenVariants.BRAIN, PromenadeLootTables.BRAIN_SUNKEN);
        of(registerable, SunkenVariants.BUBBLE, PromenadeLootTables.BUBBLE_SUNKEN);
        of(registerable, SunkenVariants.FIRE, PromenadeLootTables.FIRE_SUNKEN);
        of(registerable, SunkenVariants.HORN, PromenadeLootTables.HORN_SUNKEN);
    }

    private static void of(Registerable<SunkenVariant> registry, RegistryKey<SunkenVariant> key, RegistryKey<LootTable> lootTable) {
        registry.register(key, new SunkenVariant(key.getValue().withPrefixedPath("entity/sunken/"), lootTable));
    }
}