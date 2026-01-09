package ru.chipsonsky.actionssystem.api;

import org.bukkit.plugin.Plugin;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.context.ActionContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public interface ActionAPI {

    /**
     * Builder the action
     * @param action - Action to be registered
     */
    void registerAction(Action action, Plugin plugin);

    /**
     * Parses an action string into an object
     * @param actionString - string of action
     * @return action object if the actionString is correct
     */
    @Nullable
    Action parseAction(String actionString);

    /**
     * @return action name -> action object
     */
    Map<String, Action> allActions();

    void executeAction(String action, ActionContext context);

    default void executeAction(List<String> action, ActionContext context) {
        for (String a : action) {
            executeAction(a, context);
        }
    }
}
