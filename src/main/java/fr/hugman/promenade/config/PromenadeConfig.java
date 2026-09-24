package fr.hugman.promenade.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.promenade.Promenade;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The mod's configuration, stored in {@code config/promenade.json}.
 * <p>
 * Every value is read once, while the mod initializes. Saving a new configuration only writes it to disk: the game
 * must be restarted for it to take effect.
 */
public record PromenadeConfig(
        WorldFeaturesConfig worldFeatures,
        AnimalsConfig animals,
        MonstersConfig monsters
) {
    public static final PromenadeConfig DEFAULT = new PromenadeConfig(
            WorldFeaturesConfig.DEFAULT,
            AnimalsConfig.DEFAULT,
            MonstersConfig.DEFAULT
    );

    private static final String FILE_NAME = Promenade.MOD_ID + ".json";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Codec<PromenadeConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    WorldFeaturesConfig.CODEC.fieldOf("world_features").orElse(WorldFeaturesConfig.DEFAULT).forGetter(PromenadeConfig::worldFeatures),
                    AnimalsConfig.CODEC.fieldOf("animals").orElse(AnimalsConfig.DEFAULT).forGetter(PromenadeConfig::animals),
                    MonstersConfig.CODEC.fieldOf("monsters").orElse(MonstersConfig.DEFAULT).forGetter(PromenadeConfig::monsters)
            ).apply(instance, PromenadeConfig::new)
    );

    private static Path path;
    private static PromenadeConfig active;
    private static PromenadeConfig saved;

    public record WorldFeaturesConfig(
            boolean igneousRockPatches,
            boolean blueberryBushes,
            boolean palms
    ) {
        public static final WorldFeaturesConfig DEFAULT = new WorldFeaturesConfig(true, true, true);

        private static final Codec<WorldFeaturesConfig> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.BOOL.fieldOf("igneous_rock_patches").orElse(DEFAULT.igneousRockPatches()).forGetter(WorldFeaturesConfig::igneousRockPatches),
                        Codec.BOOL.fieldOf("blueberry_bushes").orElse(DEFAULT.blueberryBushes()).forGetter(WorldFeaturesConfig::blueberryBushes),
                        Codec.BOOL.fieldOf("palms").orElse(DEFAULT.palms()).forGetter(WorldFeaturesConfig::palms)
                ).apply(instance, WorldFeaturesConfig::new)
        );
    }

    public record AnimalsConfig(
            int capybarasWeight,
            int ducksWeight
    ) {
        public static final AnimalsConfig DEFAULT = new AnimalsConfig(10, 10);

        private static final Codec<AnimalsConfig> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        weightField("capybaras_weight", DEFAULT.capybarasWeight()).forGetter(AnimalsConfig::capybarasWeight),
                        weightField("ducks_weight", DEFAULT.ducksWeight()).forGetter(AnimalsConfig::ducksWeight)
                ).apply(instance, AnimalsConfig::new)
        );
    }

    public record MonstersConfig(
            int lushCreepersWeight,
            int sunkensWeight
    ) {
        public static final MonstersConfig DEFAULT = new MonstersConfig(15, 10);

        private static final Codec<MonstersConfig> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        weightField("lush_creepers_weight", DEFAULT.lushCreepersWeight()).forGetter(MonstersConfig::lushCreepersWeight),
                        weightField("sunkens_weight", DEFAULT.sunkensWeight()).forGetter(MonstersConfig::sunkensWeight)
                ).apply(instance, MonstersConfig::new)
        );
    }

    private static MapCodec<Integer> weightField(String name, int defaultValue) {
        return Codec.INT.fieldOf(name).orElse(defaultValue);
    }

    /**
     * Loads the configuration from the given directory, creating the file if it does not exist yet.
     * Must be called before {@link #get()}.
     *
     * @param configDir the directory in which the configuration file is stored
     */
    public static void load(Path configDir) {
        path = configDir.resolve(FILE_NAME);
        var loaded = read();
        active = loaded == null ? DEFAULT : loaded;
        saved = active;
        if (loaded != null) {
            // Adds missing values and removes unknown ones
            write(active);
        }
    }

    /**
     * @return the configuration the game is currently running with
     */
    @NotNull
    public static PromenadeConfig get() {
        if (active == null) {
            throw new IllegalStateException("Promenade config has not been loaded yet");
        }
        return active;
    }

    /**
     * @return the last configuration saved to disk, which may differ from the {@linkplain #get() active} one until the
     * game restarts
     */
    @NotNull
    public static PromenadeConfig getSaved() {
        return saved == null ? get() : saved;
    }

    /**
     * Saves a new configuration to disk. It will only be applied once the game restarts.
     *
     * @param config the configuration to save
     */
    public static void save(PromenadeConfig config) {
        saved = config;
        write(config);
    }

    /**
     * @return {@code true} if the saved configuration differs from the active one
     */
    public static boolean requiresRestart() {
        return !getSaved().equals(get());
    }

    /**
     * @return the configuration read from disk, or {@code null} if the file exists but could not be read
     */
    private static PromenadeConfig read() {
        if (!Files.exists(path)) {
            return DEFAULT;
        }
        try (var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            var json = JsonParser.parseReader(reader);
            return CODEC.parse(JsonOps.INSTANCE, json)
                    .resultOrPartial(error -> Promenade.LOGGER.warn("Invalid values in Promenade config, using defaults where needed: {}", error))
                    .orElse(DEFAULT);
        } catch (IOException | JsonParseException e) {
            Promenade.LOGGER.warn("Failed to read Promenade config, using default values", e);
            return null;
        }
    }

    private static void write(PromenadeConfig config) {
        var json = CODEC.encodeStart(JsonOps.INSTANCE, config).getOrThrow();
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(json), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Promenade.LOGGER.warn("Failed to write Promenade config", e);
        }
    }
}
