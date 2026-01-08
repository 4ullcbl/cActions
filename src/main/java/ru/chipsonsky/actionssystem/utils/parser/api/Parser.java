package ru.chipsonsky.actionssystem.utils.parser.api;

public interface Parser<T> {
    <V> V parse(T object, Class<V> type);

    <V> V parse(T object, Class<V> type, V def);
}

