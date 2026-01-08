package ru.chipsonsky.actionssystem;

import org.bukkit.plugin.java.JavaPlugin;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.ActionCommand;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;
import ru.chipsonsky.actionssystem.config.impl.YamlConfig;

import java.util.Optional;

public class ActionPlugin extends JavaPlugin {

    private CustomConfig aliasesConf;

    @Override
    public void onEnable() {
        aliasesConf = new YamlConfig("aliases.yml", this);
        final Action action = new Action("test", ((actionArguments, context) -> {
            getLogger().info("test");
        }));

        ActionRegistry.ACTIONS.register("test", action);

        Optional.ofNullable(getCommand("action"))
                .orElseThrow()
                .setExecutor(new ActionCommand());

        ActionRegistry.normalizeActions(aliasesConf);
    }
}
