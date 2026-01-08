package ru.chipsonsky.actionssystem.commands.impl;

import org.bukkit.command.CommandSender;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;

public class ActionListExecutor extends ArgumentExecutor {

    public ActionListExecutor(String name) {
        super(name);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        ActionRegistry.ACTIONS.forEach((name, action) -> {
            sender.sendMessage(name + " := " + action.getAliases());
        });
    }
}
