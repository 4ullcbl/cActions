package ru.chipsonsky.actionssystem.action;

/*
actions:
  - "[broadcast] Hello from action!"
  - "[command] kill @e"
  - "[particle] FLAME, 1, 0, 0, 0, 0q"
 */

import lombok.Getter;
import lombok.Setter;
import ru.chipsonsky.actionssystem.action.arguments.ActionArguments;
import ru.chipsonsky.actionssystem.action.context.ActionContext;

import java.util.*;
import java.util.function.BiConsumer;

@Getter
public class Action {
    private final String name;
    private final List<String> arguments = new ArrayList<>();
    @Setter
    private Set<String> aliases;
    @Setter
    protected BiConsumer<ActionArguments, ActionContext> onExecute;
    @Setter
    protected boolean cancelled = false;

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

    public boolean addAlias(String alias) {
        return this.aliases.add(alias);
    }

    public void argument(String argument) {
        arguments.add(argument);
    }

    public static Builder registrator() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "<yellow>action name: <white>" + name +
                "\n<yellow>aliases: <white>" + aliases +
                "\n<yellow>arguments: <white>" + arguments
                ;
    }

    public static class Builder {
        private String name;
        private final List<String> arguments = new ArrayList<>();
        private final Set<String> aliases = new HashSet<>();
        private BiConsumer<ActionArguments, ActionContext> onExecute;

        public Builder create(String name) {
            this.name = name;
            return this;
        }

        public Builder argument(String arg) {
            this.arguments.add(arg);
            return this;
        }

        public Builder arguments(String... args) {
            this.arguments.addAll(Arrays.asList(args));
            return this;
        }

        public Builder alias(String alias) {
            this.aliases.add(alias);
            return this;
        }

        public Builder onExecute(BiConsumer<ActionArguments, ActionContext> consumer) {
            this.onExecute = consumer;
            return this;
        }

        public Action build() {
            final Action action = new Action(this.name, this.onExecute, this.aliases);
            for (String argument : arguments) {
                action.argument(argument);
            }

            return action;
        }
    }
}
