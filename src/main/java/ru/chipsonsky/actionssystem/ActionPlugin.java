package ru.chipsonsky.actionssystem;

import org.bukkit.plugin.java.JavaPlugin;
import ru.chipsonsky.actionssystem.creator.api.ActionCreator;
import ru.chipsonsky.actionssystem.creator.impl.ActionCreatorImpl;

import java.util.Optional;

public class ActionPlugin extends JavaPlugin {

    private final ActionCreator creator = new ActionCreatorImpl();

    @Override
    public void onEnable() {
        creator.create("test", (arg, context) -> getLogger().info("test action"));

        Optional.ofNullable(getCommand("action"))
                .orElseThrow()
                .setExecutor(this);

    }
}
