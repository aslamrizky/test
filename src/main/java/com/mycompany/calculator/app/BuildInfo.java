package com.mycompany.calculator.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads build metadata (version, build number, build timestamp) that Maven bakes into
 * {@code version.properties} at build time via resource filtering. This lets the running
 * application report exactly which build of the jar is executing, which is handy once the
 * CI pipeline starts producing a new, auto-incremented version on every push.
 */
public final class BuildInfo {

    private static final String RESOURCE_NAME = "/version.properties";
    private static final String UNKNOWN = "unknown";

    private final String version;
    private final String buildNumber;
    private final String buildTimestamp;

    public BuildInfo(String version, String buildNumber, String buildTimestamp) {
        this.version = version;
        this.buildNumber = buildNumber;
        this.buildTimestamp = buildTimestamp;
    }

    /**
     * Reads {@code version.properties} from the classpath and falls back to placeholder
     * values when it cannot be found, which happens for example when the class is run
     * straight from an IDE without going through the Maven build first.
     *
     * @return the loaded build information
     */
    public static BuildInfo load() {
        Properties properties = new Properties();
        try (InputStream stream = BuildInfo.class.getResourceAsStream(RESOURCE_NAME)) {
            if (stream != null) {
                properties.load(stream);
            }
        } catch (IOException e) {
            // Falls through and uses the unknown defaults below; the app is still usable.
        }
        String version = properties.getProperty("app.version", UNKNOWN);
        String buildNumber = properties.getProperty("app.buildNumber", UNKNOWN);
        String buildTimestamp = properties.getProperty("app.buildTimestamp", UNKNOWN);
        return new BuildInfo(version, buildNumber, buildTimestamp);
    }

    public String getVersion() {
        return version;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public String getBuildTimestamp() {
        return buildTimestamp;
    }

    /**
     * @return a one-line banner suitable for printing on application startup
     */
    public String toBannerString() {
        return "Calculator App v" + version + " (build " + buildNumber + ", " + buildTimestamp + ")";
    }
}
