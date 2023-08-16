package org.soldier.yuananticheating;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class TitleNameConfig {
    private static TitleNameConfig instance;

    private Main plugin;

    private File configFile;

    private FileConfiguration config;

    private TitleNameConfig(Main plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configFile);
        saveDefaultConfig();
    }

    public static void initialize(Main plugin) {
        instance = new TitleNameConfig(plugin);
    }

    public static TitleNameConfig getInstance() {
        return instance;
    }

    public void reloadConfig() {
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configFile);
    }

    public String getWindowTitle() {
        return this.config.getString("windowTitle");
    }

    private void saveDefaultConfig() {
        if (!this.configFile.exists())
            this.plugin.saveResource("config.yml", false);
    }

    public void saveConfig() {
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
