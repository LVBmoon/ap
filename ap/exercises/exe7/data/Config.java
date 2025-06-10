package ap.exercises.exe7.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    private static final String CONFIG_FILE = "config.txt";
    private String storageType;

    public Config() {
        this.storageType = "tabsplit"; // Default to tabsplit
        loadConfig();
    }

    private void loadConfig() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("storage=")) {
                    String type = line.substring("storage=".length()).trim();
                    if (type.equals("tabsplit") || type.equals("json") || type.equals("sqlite")) {
                        this.storageType = type;
                    } else {
                        System.err.println("Invalid storage type in config: " + type + ". Using default (tabsplit).");
                    }
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading config file: " + e.getMessage() + ". Using default (tabsplit).");
        }
    }

    public String getStorageType() {
        return storageType;
    }
}