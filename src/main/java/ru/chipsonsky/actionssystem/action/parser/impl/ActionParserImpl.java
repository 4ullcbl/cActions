package ru.chipsonsky.actionssystem.action.parser.impl;

import org.jetbrains.annotations.Nullable;
import ru.chipsonsky.actionssystem.ActionPlugin;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.parser.api.ActionParser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ActionParserImpl implements ActionParser {
    public static final String START_REGEX = " ";
    public static final String BETWEEN_REGEX = ", ";

    @Override
    public @Nullable Action parse(String actionStr) {
        final List<String> input = Arrays.stream(actionStr.trim().split(START_REGEX)).toList();
        final String actionName = input.get(0).substring(1, input.get(0).length() - 1);

        Action action = ActionPlugin.getActionAPI().getACTIONS().get(actionName);
        if (action == null)
            action = parseByAliases(actionName);

        return action;
    }

    private @Nullable Action parseByAliases(String actionName) {
        for (Map.Entry<String, Action> item : ActionPlugin.getActionAPI().getACTIONS().entrySet()) {
            if (item.getValue().getAliases().contains(actionName)) {
                return item.getValue();
            }
        }
        return null;
    }
}
