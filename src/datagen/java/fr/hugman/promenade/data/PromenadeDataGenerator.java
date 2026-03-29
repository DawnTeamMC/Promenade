package fr.hugman.promenade.data;

import com.google.common.reflect.Reflection;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.data.provider.*;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.Nullable;

public class PromenadeDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        Reflection.initialize(PromenadeBlockFamilies.class);

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Resource Pack
        pack.addProvider(PromenadeModelProvider::new);
        pack.addProvider(PromenadeSoundsProvider::new);
        pack.addProvider(PromenadeEnglishLangProvider::new);

        // Data Pack

        // - Modded stuff
        pack.addProvider(PromenadeSnowyBlockTransformationProvider::new);

        // - Variants
        pack.addProvider(PromenadeWolfVariantProvider::new);
        pack.addProvider(PromenadeCapybaraVariantProvider::new);
        pack.addProvider(PromenadeDuckVariantProvider::new);
        pack.addProvider(PromenadeSunkenVariantProvider::new);
        pack.addProvider(PromenadePaintingVariantProvider::new);

        // - World generation
        pack.addProvider(PromenadeTemplatePoolProvider::new);
        pack.addProvider(PromenadeStructureProvider::new);
        pack.addProvider(PromenadeStructureSetProvider::new);

        pack.addProvider(PromenadeConfiguredFeatureProvider::new);
        pack.addProvider(PromenadePlacedFeatureProvider::new);
        pack.addProvider(PromenadeBiomeProvider::new);

        // - Loot tables
        pack.addProvider(PromenadeBlockLootTableProvider::new);
        pack.addProvider(PromenadeEntityLootTableProvider::new);
        pack.addProvider(PromenadeChestLootTableProvider::new);

        // - Banner Patterns
        pack.addProvider(PromenadeBannerPatternProvider::new);

        // - Villager Trades
        pack.addProvider(PromenadeVillagerTradeProvider::new);

        // - Tags
        var blockTagProvider = pack.addProvider(PromenadeBlockTagProvider::new);
        pack.addProvider((output, lookup) -> new PromenadeItemTagProvider(output, lookup, blockTagProvider));
        pack.addProvider(PromenadeBiomeTagProvider::new);
        pack.addProvider(PromenadeEntityTypeTagProvider::new);
        pack.addProvider(PromenadeBannerPatternTagProvider::new);
        pack.addProvider(PromenadePaintingVariantTagProvider::new);
        pack.addProvider(PromenadeVillagerTradeTagsProvider::new);

        // - Recipes
        pack.addProvider(PromenadeRecipeGenerator::create);

        // - Advancements
        pack.addProvider(PromenadeAdvancementProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(PromenadeRegistryKeys.SNOWY_BLOCK_TRANSFORMATION, PromenadeSnowyBlockTransformationProvider::register);

        registryBuilder.add(Registries.WOLF_VARIANT, PromenadeWolfVariantProvider::register);
        registryBuilder.add(PromenadeRegistryKeys.CAPYBARA_VARIANT, PromenadeCapybaraVariantProvider::register);
        registryBuilder.add(PromenadeRegistryKeys.DUCK_VARIANT, PromenadeDuckVariantProvider::register);
        registryBuilder.add(PromenadeRegistryKeys.SUNKEN_VARIANT, PromenadeSunkenVariantProvider::register);
        registryBuilder.add(Registries.PAINTING_VARIANT, PromenadePaintingVariantProvider::register);

        registryBuilder.add(Registries.TEMPLATE_POOL, PromenadeTemplatePoolProvider::register);
        registryBuilder.add(Registries.STRUCTURE, PromenadeStructureProvider::register);
        registryBuilder.add(Registries.STRUCTURE_SET, PromenadeStructureSetProvider::register);

        registryBuilder.add(Registries.CONFIGURED_FEATURE, PromenadeConfiguredFeatureProvider::register);
        registryBuilder.add(Registries.PLACED_FEATURE, PromenadePlacedFeatureProvider::register);
        registryBuilder.add(Registries.BIOME, PromenadeBiomeProvider::register);

        registryBuilder.add(Registries.BANNER_PATTERN, PromenadeBannerPatternProvider::register);

        registryBuilder.add(Registries.VILLAGER_TRADE, PromenadeVillagerTradeProvider::register);
    }

    @Override
    @Nullable
    public String getEffectiveModId() {
        return Promenade.MOD_ID;
    }
}
