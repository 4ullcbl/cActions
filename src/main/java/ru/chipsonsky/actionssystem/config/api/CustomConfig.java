package ru.chipsonsky.actionssystem.config.api;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public interface CustomConfig {
    File getFile();

    FileConfiguration getConf();
}
