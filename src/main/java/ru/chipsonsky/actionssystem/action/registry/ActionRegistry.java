package ru.chipsonsky.actionssystem.action.registry;

import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.registry.api.MapRegistry;
import ru.chipsonsky.actionssystem.action.registry.impl.MapRegistryImpl;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;

import java.util.List;
import java.util.Set;

public final class ActionRegistry {

    public static final MapRegistry<String, Action> ACTIONS = new MapRegistryImpl<>();
    private static boolean normalized = false;

    private ActionRegistry() {
    }

    public static void normalizeActions(CustomConfig aliasesConf) {
        if (normalized) return;

        ACTIONS.forEach((name, action) -> {

            final List<String> aliasesList = aliasesConf.getConf().getStringList(name);
            if (aliasesList.isEmpty()) return;

            action.setAliases(Set.copyOf(aliasesList));
        });

        normalized = true;
    }
}
