package ru.chipsonsky.actionssystem.commands.impl;

import org.bukkit.command.CommandSender;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;

import java.util.concurrent.atomic.AtomicInteger;

public class ActionListArgument extends ArgumentExecutor {

    public ActionListArgument(String name) {
        super(name);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        final AtomicInteger counter = new AtomicInteger(1);

        ActionRegistry.ACTIONS.forEach((name, action) -> {
            sender.sendMessage(counter + ". " + name + " := " + action.getAliases());
            counter.getAndIncrement();
        });
    }
}
