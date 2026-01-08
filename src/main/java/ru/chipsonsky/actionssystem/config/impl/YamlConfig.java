package ru.chipsonsky.actionssystem.config.impl;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;

import java.io.File;
import java.io.IOException;

public class YamlConfig implements CustomConfig {
    private final String name;
    private final File file;
    private final FileConfiguration config;
    private final Plugin plugin;

    public YamlConfig(String name, Plugin plugin) {
        this.plugin = plugin;
        this.name = name.endsWith(".yml") ? name : name + ".yml";

        this.file = new File(this.plugin.getDataFolder(), this.name);
        if (!this.file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            this.file.mkdirs();
            plugin.saveResource(this.name, false);
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void reload() {
        if (!this.file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            this.file.mkdirs();
            this.plugin.saveResource(this.name, true);
        }
        try {
            this.config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public FileConfiguration getConf() {
        return this.config;
    }
}
