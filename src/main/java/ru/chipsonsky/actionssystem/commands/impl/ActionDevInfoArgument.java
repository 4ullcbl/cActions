package ru.chipsonsky.actionssystem.commands.impl;

import org.bukkit.command.CommandSender;
import ru.chipsonsky.actionssystem.ActionPlugin;
import ru.chipsonsky.actionssystem.action.Action;
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
        final Action action = ActionPlugin.getActionAPI().getACTIONS().get(key);

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
            ActionPlugin.getActionAPI().allActions().forEach((name, a) -> arguments.add(name));
            return arguments;
        }

        return Collections.emptyList();
    }
}
