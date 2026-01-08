package ru.chipsonsky.actionssystem.creator.impl;

import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.arguments.ActionArguments;
import ru.chipsonsky.actionssystem.action.context.ActionContext;
import ru.chipsonsky.actionssystem.action.registry.ActionRegistry;
import ru.chipsonsky.actionssystem.creator.api.ActionCreator;

import java.util.Collections;
import java.util.function.BiConsumer;

public class ActionCreatorImpl implements ActionCreator {
    @Override
    public void create(String name, BiConsumer<ActionArguments, ActionContext> execute) {
        ActionRegistry.ACTIONS.register(name, new Action(name, execute, Collections.emptySet()));
    }
}
