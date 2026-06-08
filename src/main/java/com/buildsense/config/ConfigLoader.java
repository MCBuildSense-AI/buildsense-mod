package com.buildsense.config;

import com.buildsense.BuildSenseMod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Handles the lifecycle of buildsense.json:
 * - find config path
 * - create defaults if missing
 * - load existing config
 * - recover from broken config
 * - save validated config
 */
public final class ConfigLoader {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final String CONFIG_FILE_NAME = "buildsense.json";

    private ConfigLoader() {
        // Utility class. Do not instantiate.
    }

    public static Path getConfigPath() {
        return FabricLoader.getInstance()
                .getConfigDir()
                .resolve(CONFIG_FILE_NAME);
    }

    public static BuildSenseConfig loadOrCreate() {
        Path configPath = getConfigPath();

        try {
            Files.createDirectories(configPath.getParent());

            if (Files.notExists(configPath)) {
                BuildSenseConfig defaults = BuildSenseConfig.defaults();
                save(defaults);
                BuildSenseMod.LOGGER.info("Created default BuildSense config at {}", configPath);
                return defaults;
            }

            try (Reader reader = Files.newBufferedReader(configPath)) {
                BuildSenseConfig loaded = GSON.fromJson(reader, BuildSenseConfig.class);

                if (loaded == null) {
                    BuildSenseMod.LOGGER.warn("BuildSense config was empty. Recreating defaults.");
                    BuildSenseConfig defaults = BuildSenseConfig.defaults();
                    save(defaults);
                    return defaults;
                }

                BuildSenseConfig validated = loaded.validatedOrDefault();

                if (!validated.equals(loaded)) {
                    BuildSenseMod.LOGGER.warn("BuildSense config had invalid values. Saving corrected config.");
                    save(validated);
                }

                return validated;
            }
        } catch (Exception error) {
            BuildSenseMod.LOGGER.error(
                    "Failed to load BuildSense config. Recreating safe defaults. Problem: {}",
                    error.getMessage()
            );

            BuildSenseConfig defaults = BuildSenseConfig.defaults();

            try {
                save(defaults);
            } catch (IOException saveError) {
                BuildSenseMod.LOGGER.error(
                        "Failed to save default BuildSense config. Problem: {}",
                        saveError.getMessage()
                );
            }

            return defaults;
        }
    }

    public static void save(BuildSenseConfig config) throws IOException {
        Path configPath = getConfigPath();
        Files.createDirectories(configPath.getParent());

        BuildSenseConfig safeConfig = config == null
                ? BuildSenseConfig.defaults()
                : config.validatedOrDefault();

        try (Writer writer = Files.newBufferedWriter(configPath)) {
            GSON.toJson(safeConfig, writer);
        }
    }
}