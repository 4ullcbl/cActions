package ru.chipsonsky.actionssystem.action.executor.api;

import java.util.List;

public interface ActionExecutor {
    void execute(List<String> actions);

    void execute(String... actions);

    void execute(String action);
}
