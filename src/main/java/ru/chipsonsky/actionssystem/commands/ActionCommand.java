package ru.chipsonsky.actionssystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;
import ru.chipsonsky.actionssystem.commands.api.TabCompleteArgumentExecutor;
import ru.chipsonsky.actionssystem.commands.impl.ActionDevInfoArgument;
import ru.chipsonsky.actionssystem.commands.impl.ActionExecutorArgument;
import ru.chipsonsky.actionssystem.commands.impl.ActionListArgument;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionCommand implements TabExecutor {

    private final Map<String, ArgumentExecutor> arguments = new HashMap<>();

    public ActionCommand() {
        arguments.put("execute", new ActionExecutorArgument("executor"));
        arguments.put("list", new ActionListArgument("list"));
        arguments.put("devinfo", new ActionDevInfoArgument("devinfo"));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player))
            return false;

        if (args.length == 0) {
            player.sendRawMessage("<red> arguments is required");
            return true;
        }

        final ArgumentExecutor argument = arguments.get(args[0]);
        if (argument == null) return false;

        argument.onExecute(player, args);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.copyOf(arguments.keySet());
        }

        if (args.length > 1) {
            final ArgumentExecutor executor = arguments.get(args[0]);
            if (!(executor instanceof TabCompleteArgumentExecutor tab)) return Collections.emptyList();

            return tab.complete(sender, args);
        }

        return Collections.emptyList();
    }
}
