package com.buildsense.config;

/**
 * Main BuildSense configuration object.
 *
 * This is intentionally small for alpha:
 * - provider: which AI mode to use later
 * - endpoint/model/apiKey: future AI provider settings
 * - timeoutSeconds: future HTTP timeout
 * - maxArea: alpha scan limit
 */
public record BuildSenseConfig(
        Provider provider,
        String endpoint,
        String model,
        String apiKey,
        int timeoutSeconds,
        int maxArea
) {
    public enum Provider {
        MOCK,
        OLLAMA,
        OPENAI_COMPATIBLE,
        SHARED_BACKEND
    }

    public static BuildSenseConfig defaults() {
        return new BuildSenseConfig(
                Provider.MOCK,
                "http://localhost:11434",
                "llama3.1",
                "",
                20,
                32
        );
    }

    public BuildSenseConfig sanitizedForDisplay() {
        return new BuildSenseConfig(
                provider,
                endpoint,
                model,
                apiKey == null || apiKey.isBlank() ? "" : "[REDACTED]",
                timeoutSeconds,
                maxArea
        );
    }

    public BuildSenseConfig validatedOrDefault() {
        BuildSenseConfig defaults = defaults();

        Provider safeProvider = provider == null ? defaults.provider() : provider;
        String safeEndpoint = endpoint == null || endpoint.isBlank() ? defaults.endpoint() : endpoint;
        String safeModel = model == null || model.isBlank() ? defaults.model() : model;
        String safeApiKey = apiKey == null ? "" : apiKey;

        int safeTimeout = timeoutSeconds <= 0 || timeoutSeconds > 120
                ? defaults.timeoutSeconds()
                : timeoutSeconds;

        int safeMaxArea = maxArea <= 0 || maxArea > 64
                ? defaults.maxArea()
                : maxArea;

        return new BuildSenseConfig(
                safeProvider,
                safeEndpoint,
                safeModel,
                safeApiKey,
                safeTimeout,
                safeMaxArea
        );
    }
}