package ru.chipsonsky.actionssystem.commands.impl;

import org.bukkit.command.CommandSender;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;

public class ActionDevInfoArgument extends ArgumentExecutor {

    public ActionDevInfoArgument(String name) {
        super(name);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (args.length < 1) return;

        final String key = args[1];
        final Action action = ActionRegistry.ACTIONS.get(key);

        if (action == null) {
            sender.sendRichMessage("<red>Unknown action");
            return;
        }

        sender.sendRichMessage("<yellow>" + action.getName() + "<white>: {\n" + action + "\n}");
    }
}
