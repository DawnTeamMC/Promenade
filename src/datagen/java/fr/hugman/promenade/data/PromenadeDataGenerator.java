package fr.hugman.promenade.data;

import com.google.common.reflect.Reflection;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.data.generator.*;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.Nullable;

public class PromenadeDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        Reflection.initialize(PromenadeBlockFamilies.class);

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Resource Pack
        pack.addProvider(PromenadeModelProvider::new);
        pack.addProvider(PromenadeRecipeGenerator::create);

        // Data Pack

        // - Variants
        pack.addProvider(PromenadeWolfVariantProvider::new);
        pack.addProvider(PromenadeCapybaraVariantProvider::new);
        pack.addProvider(PromenadeDuckVariantProvider::new);
        pack.addProvider(PromenadeSunkenVariantProvider::new);
        pack.addProvider(PromenadePaintingVariantProvider::new);

        // - World generation
        pack.addProvider(PromenadeConfiguredFeatureProvider::new);
        pack.addProvider(PromenadePlacedFeatureProvider::new);
        pack.addProvider(PromenadeBiomeProvider::new);

        // - Loot tables
        pack.addProvider(PromenadeBlockLootTableProvider::new);
        pack.addProvider(PromenadeEntityLootTableProvider::new);

        // - Tags
        var blockTagProvider = pack.addProvider(PromenadeBlockTagProvider::new);
        pack.addProvider((output, lookup) -> new PromenadeItemTagProvider(output, lookup, blockTagProvider));
        pack.addProvider(PromenadeBiomeTagProvider::new);
        pack.addProvider(PromenadePaintingVariantTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.WOLF_VARIANT, PromenadeWolfVariantProvider::register);
        registryBuilder.addRegistry(PromenadeRegistryKeys.CAPYBARA_VARIANT, PromenadeCapybaraVariantProvider::register);
        registryBuilder.addRegistry(PromenadeRegistryKeys.DUCK_VARIANT, PromenadeDuckVariantProvider::register);
        registryBuilder.addRegistry(PromenadeRegistryKeys.SUNKEN_VARIANT, PromenadeSunkenVariantProvider::register);
        registryBuilder.addRegistry(RegistryKeys.PAINTING_VARIANT, PromenadePaintingVariantProvider::register);

        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, PromenadeConfiguredFeatureProvider::register);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PromenadePlacedFeatureProvider::register);
        registryBuilder.addRegistry(RegistryKeys.BIOME, PromenadeBiomeProvider::register);
    }

    @Override
    @Nullable
    public String getEffectiveModId() {
        return Promenade.MOD_ID;
    }
}
