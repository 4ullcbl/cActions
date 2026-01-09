package ru.chipsonsky.actionssystem;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import ru.chipsonsky.actionssystem.action.def.DefaultAction;
import ru.chipsonsky.actionssystem.action.executor.api.ActionExecutor;

public final class ActionSystem {

    @Getter
    private static ActionExecutor executor;

    private static boolean init = false;

    static void init(ActionExecutor executor, Plugin plugin) {

        if (init) return;
        final DefaultAction actions = new DefaultAction();
        actions.registerAll(plugin);

        ActionSystem.executor = executor;
        init = true;
    }

}
