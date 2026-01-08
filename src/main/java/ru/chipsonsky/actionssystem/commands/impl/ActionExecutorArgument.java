package ru.chipsonsky.actionssystem.commands.impl;

import org.bukkit.command.CommandSender;
import ru.chipsonsky.actionssystem.action.context.ActionContext;
import ru.chipsonsky.actionssystem.action.executor.api.ActionExecutor;
import ru.chipsonsky.actionssystem.action.executor.impl.ActionExecutorImpl;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;

public class ActionExecutorArgument extends ArgumentExecutor {

    public ActionExecutorArgument(String name) {
        super(name);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        final StringBuilder actionStr = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            actionStr.append(" ").append(args[i]);
        }

        System.out.println(actionStr);

        final ActionExecutor executor = new ActionExecutorImpl();
        executor.execute(actionStr.toString(), new ActionContext(){});

    }
}
