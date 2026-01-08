package ru.chipsonsky.actionssystem.commands.api;

import org.bukkit.command.CommandSender;

public abstract class ArgumentExecutor {
    private final String name;

    protected ArgumentExecutor(String name) {
        this.name = name;
    }

    public abstract void onExecute(CommandSender sender, String[] args);
}
