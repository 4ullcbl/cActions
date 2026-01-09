package ru.chipsonsky.actionssystem;

import lombok.Getter;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import ru.chipsonsky.actionssystem.api.AbstractActionAPI;
import ru.chipsonsky.actionssystem.api.ActionAPI;
import ru.chipsonsky.actionssystem.commands.ActionCommand;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;
import ru.chipsonsky.actionssystem.config.impl.YamlConfig;

import java.util.Optional;

public class ActionPlugin extends JavaPlugin {

    @Getter
    private static AbstractActionAPI actionAPI;

    @Override
    public void onEnable() {
        final CustomConfig aliasesConf = new YamlConfig("aliases.yml", this);
        actionAPI = new AbstractActionAPI(this){};

        getServer().getServicesManager().register(ActionAPI.class, actionAPI, this, ServicePriority.Normal);

        Optional.ofNullable(getCommand("action"))
                .orElseThrow()
                .setExecutor(new ActionCommand());


        actionAPI.normalizeActions(aliasesConf);
    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregisterAll(this);
    }

}
