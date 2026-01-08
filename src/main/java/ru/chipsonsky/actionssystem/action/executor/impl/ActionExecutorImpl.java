package ru.chipsonsky.actionssystem.action.executor.impl;

import ru.chipsonsky.actionssystem.action.executor.api.ActionExecutor;

import java.util.Arrays;
import java.util.List;

public class ActionExecutorImpl implements ActionExecutor {

    @Override
    public void execute(List<String> actions) {

    }

    @Override
    public void execute(String... actions) {

    }

    @Override
    public void execute(String action) {
        final List<String> input = Arrays.stream(action.split(" ")).toList();

    }
}
