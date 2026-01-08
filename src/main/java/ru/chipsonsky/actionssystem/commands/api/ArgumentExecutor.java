package ru.chipsonsky.actionssystem.commands.api;

import org.bukkit.command.CommandSender;

public abstract class ArgumentExecutor {
    protected final String name;
    protected final String[] rawInput;

    protected ArgumentExecutor(String name, String[] rawInput) {
        this.name = name;
        this.rawInput = rawInput;
    }

    protected abstract void onExecute(CommandSender sender);
}
