package ru.chipsonsky.actionssystem.utils.parser.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.chipsonsky.actionssystem.utils.parser.api.Parser;

import java.util.Map;
import java.util.function.Function;

public class StringParser implements Parser<String> {

    private static final Map<Class<?>, Function<String, ?>> PARSERS = Map.of(
            float.class, Float::parseFloat,
            double.class, Double::parseDouble,
            int.class, Integer::parseInt,
            boolean.class, Boolean::parseBoolean,
            short.class, Short::parseShort,
            long.class, Long::parseLong,
            byte.class, Byte::parseByte
    );

    @Override
    public @Nullable <V> V parse(@NotNull String object, @NotNull Class<V> type) {
        return parse(object, type, null);
    }

    @Override
    public <V> V parse(@NotNull String object, @NotNull Class<V> type, V def) {
        if (object.isEmpty())
            return def;

        if (!PARSERS.containsKey(type))
            throw new RuntimeException("Unsupported type: " + type.getName());

        try {
            //noinspection unchecked
            return (V) PARSERS.get(type).apply(object.trim());
        } catch (Exception e) {
            return def;
        }
    }
}
