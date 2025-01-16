package fr.hugman.promenade.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.Promenade;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public record PromenadeConfig(
        BiomesConfig biomes,
        WorldFeaturesConfig worldFeatures,
        AnimalsConfig animals,
        MonstersConfig monsters
) {
    private static final Path PATH = Paths.get("config/promenade.json");

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Codec<PromenadeConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BiomesConfig.CODEC.fieldOf("biomes").forGetter(PromenadeConfig::biomes),
                    WorldFeaturesConfig.CODEC.fieldOf("world_features").forGetter(PromenadeConfig::worldFeatures),
                    AnimalsConfig.CODEC.fieldOf("animals").forGetter(PromenadeConfig::animals),
                    MonstersConfig.CODEC.fieldOf("monsters").forGetter(PromenadeConfig::monsters)
            ).apply(instance, PromenadeConfig::new)
    );

    private static PromenadeConfig instance;

    private PromenadeConfig() {
        this(
                new BiomesConfig(20, 20, 10, 20),
                new WorldFeaturesConfig(true, true, true),
                new AnimalsConfig(10, 10),
                new MonstersConfig(15, 10)
        );
    }

    public record BiomesConfig(
            int carnelianTreewayWeight,
            int sakuraGrovesWeight,
            int glacarianTaigaWeight,
            int darkAmaranthForestsWeight
    ) {

        private static final Codec<BiomesConfig> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.optionalFieldOf("carnelian_treeway_weight", 20).forGetter(BiomesConfig::carnelianTreewayWeight),
                        Codec.INT.optionalFieldOf("sakura_groves_weight", 20).forGetter(BiomesConfig::sakuraGrovesWeight),
                        Codec.INT.optionalFieldOf("glacarian_taiga_weight", 10).forGetter(BiomesConfig::glacarianTaigaWeight),
                        Codec.INT.optionalFieldOf("dark_amaranth_forests_weight", 20).forGetter(BiomesConfig::darkAmaranthForestsWeight)
                ).apply(instance, BiomesConfig::new)
        );
    }

    public record WorldFeaturesConfig(
            boolean igneousRockPatches,
            boolean blueberryBushes,
            boolean palms
    ) {
        private static final Codec<WorldFeaturesConfig> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.BOOL.optionalFieldOf("igneous_rock_patches", true).forGetter(WorldFeaturesConfig::igneousRockPatches),
                        Codec.BOOL.optionalFieldOf("blueberry_bushes", true).forGetter(WorldFeaturesConfig::blueberryBushes),
                        Codec.BOOL.optionalFieldOf("palms", true).forGetter(WorldFeaturesConfig::palms)
                ).apply(instance, WorldFeaturesConfig::new)
        );
    }

    public record AnimalsConfig(
            int capybarasWeight,
            int ducksWeight
    ) {
        private static final Codec<AnimalsConfig> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.optionalFieldOf("capybaras_weight", 10).forGetter(AnimalsConfig::capybarasWeight),
                        Codec.INT.optionalFieldOf("ducks_weight", 10).forGetter(AnimalsConfig::ducksWeight)
                ).apply(instance, AnimalsConfig::new)
        );
    }

    public record MonstersConfig(
            int lushCreepersWeight,
            int sunkensWeight
    ) {
        private static final Codec<MonstersConfig> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.optionalFieldOf("lush_creepers_weight", 15).forGetter(MonstersConfig::lushCreepersWeight),
                        Codec.INT.optionalFieldOf("sunkens_weight", 10).forGetter(MonstersConfig::sunkensWeight)
                ).apply(instance, MonstersConfig::new)
        );
    }

    @NotNull
    public static PromenadeConfig get() {
        if (instance == null) {
            instance = initializeConfig();
        }
        return instance;
    }

    private static PromenadeConfig initializeConfig() {
        if (Files.exists(PATH)) {
            return loadConfig();
        } else {
            return createDefaultConfig();
        }
    }

    private static PromenadeConfig loadConfig() {
        try (var input = Files.newInputStream(PATH)) {
            var json = JsonParser.parseReader(new InputStreamReader(input));
            var result = CODEC.decode(JsonOps.INSTANCE, json).map(Pair::getFirst);
            return result.result().orElseGet(PromenadeConfig::new);
        } catch (IOException e) {
            Promenade.LOGGER.warn("Failed to load Promenade config", e);
            return new PromenadeConfig();
        }
    }

    private static PromenadeConfig createDefaultConfig() {
        var config = new PromenadeConfig();
        try (var output = Files.newOutputStream(PATH)) {
            var result = CODEC.encodeStart(JsonOps.INSTANCE, config).result();
            if (result.isPresent()) {
                var json = result.get();
                IOUtils.write(GSON.toJson(json), output, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            Promenade.LOGGER.warn("Failed to create default Promenade config", e);
        }
        return config;
    }
}
