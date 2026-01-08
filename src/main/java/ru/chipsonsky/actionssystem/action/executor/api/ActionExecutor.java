package ru.chipsonsky.actionssystem.action.executor.api;

import ru.chipsonsky.actionssystem.action.context.ActionContext;

import java.util.List;

public interface ActionExecutor {
    void execute(List<String> actions, ActionContext context);


    void execute(String action, ActionContext context);
}
