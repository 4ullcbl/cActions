package ru.chipsonsky.actionssystem.action.executor.impl;

import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.arguments.ActionArguments;
import ru.chipsonsky.actionssystem.action.context.ActionContext;
import ru.chipsonsky.actionssystem.action.executor.api.ActionExecutor;
import ru.chipsonsky.actionssystem.action.parser.impl.ActionParserImpl;

import java.util.Arrays;
import java.util.List;

import static ru.chipsonsky.actionssystem.action.parser.impl.ActionParserImpl.BETWEEN_REGEX;
import static ru.chipsonsky.actionssystem.action.parser.impl.ActionParserImpl.START_REGEX;

public class ActionExecutorImpl implements ActionExecutor {

    @Override
    public void execute(List<String> actions, ActionContext context) {
        actions.forEach(a -> execute(a, context));
    }

    @Override
    public void execute(String actionStr, ActionContext context) {
        final Action action = new ActionParserImpl().parse(actionStr);
        if (action == null) return;

        final ActionArguments arguments = new ActionArguments(actionStr);

        final List<String> input = Arrays.stream(actionStr.trim().split(START_REGEX)).toList();
        final String[] argsWithoutName = String.join(" ", input.subList(1, input.size())).split(BETWEEN_REGEX);

        for (int i = 0; i < argsWithoutName.length; i++) {
            if (action.getArguments().size() <= i)
                break;

            arguments.put(action.getArguments().get(i), argsWithoutName[i]);
        }

        try {
            action.getOnExecute().accept(arguments, context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// [action] arg0, arg1, arg2, arg3