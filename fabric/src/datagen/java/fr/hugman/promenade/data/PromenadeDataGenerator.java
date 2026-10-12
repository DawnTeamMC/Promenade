package fr.hugman.promenade.data;

import com.google.common.reflect.Reflection;
import com.terraformersmc.biolith.impl.data.BiomePlacementMarshaller;
import fr.hugman.promenade.Promenade;
import fr.hugman.promenade.data.provider.*;
import fr.hugman.promenade.pack.PromenadeDataPacks;
import fr.hugman.promenade.registry.PromenadeRegistryKeys;
import fr.hugman.promenade.world.biome.PromenadeBiomes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static fr.hugman.promenade.data.provider.PromenadeBiolithBiomePlacementProvider.*;

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
        // - Loader-specific
        pack.addProvider(PromenadeNeoForgeTransformablesProvider::new);

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
        pack.addProvider(PromenadeBiolithBiomePlacementProvider::new);
        pack.addProvider(PromenadeBiolithSurfaceProvider::new);

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

        generateDataPacks(fabricDataGenerator);
    }

    /**
     * Generates the {@linkplain PromenadeDataPacks optional data packs}.
     * <p>
     * Disabling packs override the biome's placement file. Replacing packs add a file of their own instead, so that they
     * can be combined with a disabling pack to only generate the biome in place of the vanilla one.
     */
    private static void generateDataPacks(FabricDataGenerator generator) {
        dataPack(generator, PromenadeDataPacks.NO_SAKURA_GROVES, placements -> placements.accept(SAKURA_GROVES, NONE));
        dataPack(generator, PromenadeDataPacks.NO_CARNELIAN_TREEWAY, placements -> placements.accept(CARNELIAN_TREEWAY, NONE));
        dataPack(generator, PromenadeDataPacks.NO_GLACARIAN_TAIGA, placements -> placements.accept(GLACARIAN_TAIGA, NONE));
        dataPack(generator, PromenadeDataPacks.NO_DARK_AMARANTH_FOREST, placements -> placements.accept(DARK_AMARANTH_FOREST, NONE));

        // Both groves take half of the cherry groves (a full proportion each leaves no room for the vanilla biome)
        dataPack(generator, PromenadeDataPacks.SAKURA_GROVES_REPLACE_CHERRY_GROVES, placements -> placements.accept(PromenadeDataPacks.SAKURA_GROVES_REPLACE_CHERRY_GROVES, replacements(List.of(
                overworldReplacement(Biomes.CHERRY_GROVE, PromenadeBiomes.BLUSH_SAKURA_GROVE, 1.0D),
                overworldReplacement(Biomes.CHERRY_GROVE, PromenadeBiomes.COTTON_SAKURA_GROVE, 1.0D)
        ))));
        dataPack(generator, PromenadeDataPacks.CARNELIAN_TREEWAY_REPLACES_DAPPLED_FOREST, placements -> placements.accept(PromenadeDataPacks.CARNELIAN_TREEWAY_REPLACES_DAPPLED_FOREST, replacements(List.of(
                overworldReplacement(Biomes.DAPPLED_FOREST, PromenadeBiomes.CARNELIAN_TREEWAY, 1.0D)
        ))));
    }

    private static void dataPack(FabricDataGenerator generator, Identifier id, Consumer<BiConsumer<Identifier, BiomePlacementMarshaller>> placements) {
        FabricDataGenerator.Pack pack = generator.createBuiltinResourcePack(id);
        pack.addProvider((FabricPackOutput output) -> PackMetadataGenerator.forFeaturePack(output, Component.translatable(PromenadeDataPacks.descriptionKey(id))));
        pack.addProvider((output, registries) -> new PromenadeBiolithBiomePlacementProvider(output, registries, placements));
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

        registryBuilder.add(Registries.FEATURE, PromenadeConfiguredFeatureProvider::register);
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
