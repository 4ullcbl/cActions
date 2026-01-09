package ru.chipsonsky.actionssystem.commands.impl;

import org.bukkit.command.CommandSender;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;
import ru.chipsonsky.actionssystem.commands.api.TabCompleteArgumentExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionDevInfoArgument extends ArgumentExecutor implements TabCompleteArgumentExecutor {

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


    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 2) {
            final List<String> arguments = new ArrayList<>();
            ActionRegistry.ACTIONS.entrySet().forEach(entry -> arguments.add(entry.getKey()));
            return arguments;
        }

        return Collections.emptyList();
    }
}
