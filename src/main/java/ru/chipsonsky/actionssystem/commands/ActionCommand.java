package ru.chipsonsky.actionssystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.chipsonsky.actionssystem.commands.api.ArgumentExecutor;
import ru.chipsonsky.actionssystem.commands.impl.ActionExecutorArgument;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActionCommand implements CommandExecutor {

    private final Map<String, ArgumentExecutor> arguments = new HashMap<>();

    public ActionCommand() {
        arguments.put("execute", new ActionExecutorArgument("executor"));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player))
            return false;

        if (args.length == 0) {
            player.sendRawMessage("<red> arguments is required");
            return true;
        }
        System.out.println(Arrays.toString(args));

        final ArgumentExecutor argument = arguments.get(args[0]);
        if (argument == null) return false;

        argument.onExecute(player, args);

        return true;
    }
}
