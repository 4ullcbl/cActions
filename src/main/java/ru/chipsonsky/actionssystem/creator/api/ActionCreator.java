package ru.chipsonsky.actionssystem.creator.api;

import ru.chipsonsky.actionssystem.action.arguments.ActionArguments;
import ru.chipsonsky.actionssystem.action.context.ActionContext;

import java.util.List;
import java.util.function.BiConsumer;

public interface ActionCreator {
    void create(String name, BiConsumer<ActionArguments, ActionContext> execute);

    void create(String name, BiConsumer<ActionArguments, ActionContext> executor, List<String> aliases);
}
