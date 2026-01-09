package ru.chipsonsky.actionssystem;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.executor.impl.ActionExecutorImpl;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.ActionCommand;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;
import ru.chipsonsky.actionssystem.config.impl.YamlConfig;

import java.util.Optional;

public class ActionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final CustomConfig aliasesConf = new YamlConfig("aliases.yml", this);

        Optional.ofNullable(getCommand("action"))
                .orElseThrow()
                .setExecutor(new ActionCommand());

        ActionSystem.init(new ActionExecutorImpl(), this);
        ActionRegistry.normalizeActions(aliasesConf, getLogger());
    }
}
