package ru.chipsonsky.actionssystem.action;

/*
actions:
  - "[BROADCAST] Hello from action!"
  - "[COMMAND] kill @e"
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.chipsonsky.actionssystem.action.arguments.ActionArguments;
import ru.chipsonsky.actionssystem.action.context.ActionContext;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

@Builder
@Getter
public class Action {
    private final String name;
    private final Set<String> aliases;
    @Setter protected BiConsumer<ActionArguments, ActionContext> onExecute;
    @Setter protected boolean cancelled = false;

    public Action(String name, BiConsumer<ActionArguments, ActionContext> onExecute) {
        this.name = name.toLowerCase();
        this.onExecute = onExecute;
        this.aliases = new HashSet<>();
        this.aliases.add(name);
    }

    public Action(String name, BiConsumer<ActionArguments, ActionContext> onExecute, Set<String> aliases) {
        this.name = name.toLowerCase();
        this.onExecute = onExecute;
        this.aliases = aliases;
        this.aliases.add(name);
    }
}
