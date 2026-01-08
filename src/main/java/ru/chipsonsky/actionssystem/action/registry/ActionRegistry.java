package ru.chipsonsky.actionssystem.action.registry;

import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.registry.api.MapRegistry;
import ru.chipsonsky.actionssystem.action.registry.impl.MapRegistryImpl;

public final class ActionRegistry {

    public static final MapRegistry<String, Action> ACTIONS = new MapRegistryImpl<>();

    private ActionRegistry() {

    }
}
