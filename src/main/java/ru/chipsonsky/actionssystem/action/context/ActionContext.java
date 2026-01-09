package ru.chipsonsky.actionssystem.action.context;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

public record ActionContext(Player player) {

    public Location getLocation() {
        return player.getLocation();
    }

    public World getWorld() {
        return player.getWorld();
    }

    public Server getServer() {
        return player.getServer();
    }
}
