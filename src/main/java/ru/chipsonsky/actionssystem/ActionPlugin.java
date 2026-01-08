package ru.chipsonsky.actionssystem;

import org.bukkit.plugin.java.JavaPlugin;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;
import ru.chipsonsky.actionssystem.config.impl.YamlConfig;
import ru.chipsonsky.actionssystem.creator.api.ActionCreator;
import ru.chipsonsky.actionssystem.creator.impl.ActionCreatorImpl;

import java.util.Optional;

public class ActionPlugin extends JavaPlugin {
    private final ActionCreator creator = new ActionCreatorImpl();

    private CustomConfig aliasesConf;

    @Override
    public void onEnable() {
        aliasesConf = new YamlConfig("aliases.yml", this);

        creator.create("test", (arg, context) -> getLogger().info("test action"));

        Optional.ofNullable(getCommand("action"))
                .orElseThrow()
                .setExecutor(this);

        ActionRegistry.normalizeActions(aliasesConf);
    }
}
