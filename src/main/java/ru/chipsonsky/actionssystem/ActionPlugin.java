package ru.chipsonsky.actionssystem;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.ActionCommand;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;
import ru.chipsonsky.actionssystem.config.impl.YamlConfig;

import java.util.Optional;

public class ActionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final CustomConfig aliasesConf = new YamlConfig("aliases.yml", this);

        Action.registrator().create("log")
                .argument("text")
                .onExecute(((actionArguments, context) -> getLogger().info(actionArguments.get("text", "null"))))
                .register();

        Action.registrator().create("broadcast")
                .argument("text")
                .onExecute(((actionArguments, context) -> Bukkit.broadcast(MiniMessage.miniMessage().deserialize(actionArguments.get("text", "null")))))
                .register();

        Optional.ofNullable(getCommand("action"))
                .orElseThrow()
                .setExecutor(new ActionCommand());

        ActionRegistry.normalizeActions(aliasesConf, getLogger());
    }
}
