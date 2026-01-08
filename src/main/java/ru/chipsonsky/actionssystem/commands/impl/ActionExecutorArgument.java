package ru.chipsonsky.actionssystem.commands.impl;

import org.bukkit.command.CommandSender;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.context.ActionContext;
import ru.chipsonsky.actionssystem.action.executor.api.ActionExecutor;
import ru.chipsonsky.actionssystem.action.executor.impl.ActionExecutorImpl;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;
import ru.chipsonsky.actionssystem.commands.api.TabCompleteArgumentExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionExecutorArgument extends ArgumentExecutor implements TabCompleteArgumentExecutor {

    public ActionExecutorArgument(String name) {
        super(name);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        final StringBuilder actionStr = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            actionStr.append(" ").append(args[i]);
        }

        final ActionExecutor executor = new ActionExecutorImpl();
        executor.execute(actionStr.toString(), new ActionContext(){});

    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 2) {
            final List<String> arguments = new ArrayList<>();
            ActionRegistry.ACTIONS.entrySet().forEach(entry -> arguments.add("[" + entry.getKey() + "]"));
            return arguments;
        }

        if (args.length > 2) {
            final Action action = ActionRegistry.ACTIONS.get(args[1].substring(1, args[1].length() - 1));

            if (action == null) return Collections.emptyList();

            try {
                return Collections.singletonList(action.getArguments().get(args.length - 3));
            } catch (Exception ignored) {
            }
        }

        return Collections.emptyList();
    }
}
