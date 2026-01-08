package ru.chipsonsky.actionssystem.commands.api;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface TabCompleteArgumentExecutor {
    List<String> complete(CommandSender sender, String[] args);
}
