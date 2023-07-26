package sba.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class Initializer {
    private static final String CONFIGURATION_FILE = "application.properties";

    public static void initialize() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        // read from application.properties file in resources folder
        // -- MODIFY CONFIG FILE ON DIFFERENT COMPUTERS
        // DON'T COMMIT REAL PASSWORD IN THE PROPERTIES FILE
        try (InputStream resourceStream = loader.getResourceAsStream(CONFIGURATION_FILE)) {
            props.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            System.setProperty(key, value);
        }
    }
}
