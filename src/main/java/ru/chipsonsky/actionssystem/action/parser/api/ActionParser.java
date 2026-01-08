package ru.chipsonsky.actionssystem.action.parser.api;

import ru.chipsonsky.actionssystem.action.Action;

import javax.annotation.Nullable;

public interface ActionParser {
    @Nullable Action parse(String action);
}
