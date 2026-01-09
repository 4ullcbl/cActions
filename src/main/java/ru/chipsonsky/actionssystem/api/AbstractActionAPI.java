package ru.chipsonsky.actionssystem.api;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;
import ru.chipsonsky.actionssystem.action.Action;
import ru.chipsonsky.actionssystem.action.context.ActionContext;
import ru.chipsonsky.actionssystem.action.def.DefaultAction;
import ru.chipsonsky.actionssystem.action.executor.api.ActionExecutor;
import ru.chipsonsky.actionssystem.action.executor.impl.ActionExecutorImpl;
import ru.chipsonsky.actionssystem.action.parser.api.ActionParser;
import ru.chipsonsky.actionssystem.action.parser.impl.ActionParserImpl;
import ru.chipsonsky.actionssystem.action.registry.api.MapRegistry;
import ru.chipsonsky.actionssystem.action.registry.impl.MapRegistryImpl;
import ru.chipsonsky.actionssystem.config.api.CustomConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractActionAPI implements ActionAPI {
    @Getter
    private final MapRegistry<String, Action> ACTIONS = new MapRegistryImpl<>();

    protected static ActionParser actionParser = new ActionParserImpl();
    protected static ActionExecutor actionExecutor = new ActionExecutorImpl();
    private static boolean normalized = false;

    private final Plugin plugin;

    public AbstractActionAPI(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerAction(Action action, Plugin plugin) {
        ACTIONS.register(action.getName(), action);
        this.plugin.getLogger().info("Register action: " + action.getName() + ". Plugin owner: " + plugin.getName());
    }

    @Override
    public @Nullable Action parseAction(String actionString) {
        return actionParser.parse(actionString);
    }

    @Override
    public Map<String, Action> allActions() {
        return ACTIONS.getAll();
    }

    @Override
    public void executeAction(String action, ActionContext context) {
        actionExecutor.execute(action, context);
    }

    public void normalizeActions(CustomConfig aliasesConf) {
        if (normalized) return;

        initDefaults(plugin);

        int counter = 0;

        for (Map.Entry<String, Action> entry : ACTIONS.entrySet()) {
            final List<String> aliasesList = aliasesConf.getConf().getStringList(entry.getKey());
            if (aliasesList.isEmpty()) continue;

            entry.getValue().setAliases(Set.copyOf(aliasesList));
            counter++;
        }

        plugin.getLogger().info("Init " + counter + " aliases for actions");

        normalized = true;
    }

    private void initDefaults(Plugin plugin) {

        if (normalized) return;

        final DefaultAction actions = new DefaultAction(plugin);
        for (Action a : actions.getToRegister()) {
            registerAction(a, plugin);
        }

    }

}
