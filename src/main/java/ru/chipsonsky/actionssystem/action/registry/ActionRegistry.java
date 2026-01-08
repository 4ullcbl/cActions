package ru.chipsonsky.actionssystem.action.registry;

import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.registry.api.MapRegistry;
import ru.chipsonsky.actionssystem.action.registry.impl.MapRegistryImpl;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public final class ActionRegistry {

    public static final MapRegistry<String, Action> ACTIONS = new MapRegistryImpl<>();
    private static boolean normalized = false;

    private ActionRegistry() {
    }

    public static void normalizeActions(CustomConfig aliasesConf, Logger logger) {
        if (normalized) return;
        final AtomicInteger integer = new AtomicInteger(0);
        ACTIONS.forEach((name, action) -> {

            final List<String> aliasesList = aliasesConf.getConf().getStringList(name);
            if (aliasesList.isEmpty()) return;

            action.setAliases(Set.copyOf(aliasesList));
            integer.getAndIncrement();
        });

        logger.info("Init " + integer + " aliases for actions");

        normalized = true;
    }
}
