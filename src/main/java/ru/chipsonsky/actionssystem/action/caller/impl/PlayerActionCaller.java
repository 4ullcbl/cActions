package ru.chipsonsky.actionssystem.action.caller.impl;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.chipsonsky.actionssystem.action.caller.api.ActionCaller;

@Getter
public class PlayerActionCaller extends ActionCaller {
    private final Player player;

    public PlayerActionCaller(Player player) {
        this.player = player;
    }
}
