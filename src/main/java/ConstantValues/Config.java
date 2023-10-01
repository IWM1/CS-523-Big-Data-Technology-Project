package ConstantValues;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private static Map<String, Object> config = new HashMap<>();
    public Config() {
        inti();
    }

    private  static void inti() {
        try (InputStream input = new FileInputStream("src/main/java/config.yaml")) {
            Yaml yaml = new Yaml();
            config = yaml.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getValueFromNestedMap(String key) {
        inti();
        String[] keys = key.split("\\.");
        Map<String, Object> currentMap = config;

        for (int i = 0; i < keys.length - 1; i++) {
            currentMap = (Map<String, Object>) currentMap.get(keys[i]);
            if (currentMap == null) {
                return null; // Key not found
            }
        }
        return currentMap.get(keys[keys.length - 1]).toString();
    }
}
