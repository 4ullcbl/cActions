package ru.chipsonsky.actionssystem.action.caller.impl;

import lombok.Getter;
import org.bukkit.command.ConsoleCommandSender;
import ru.chipsonsky.actionssystem.action.caller.api.ActionCaller;

@Getter
public class ConsoleActionCaller extends ActionCaller {
    private final ConsoleCommandSender caller;

    public ConsoleActionCaller(ConsoleCommandSender caller) {
        this.caller = caller;
    }
}
