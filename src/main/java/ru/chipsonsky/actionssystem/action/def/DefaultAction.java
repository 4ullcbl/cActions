package ru.chipsonsky.actionssystem.action.def;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;
import ru.chipsonsky.actionssystem.action.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DefaultAction {
    private final List<Action> toRegister = new ArrayList<>();

    public DefaultAction(Plugin plugin) {
        logAction(plugin);
        broadcastAction();
        messageAction();
        actionBarAction();
        soundAction();
        particleAction();
        commandAction();
    }

    public void logAction(Plugin plugin) {
        toRegister.add(Action.registrator().create("log")
                .argument("text")
                .onExecute((arguments, context) -> plugin.getLogger().info(arguments.get("text", "empty")))
                .build());
    }

    public void broadcastAction() {
        toRegister.add(Action.registrator().create("broadcast")
                .argument("text")
                .onExecute(((arguments, context) -> Bukkit.broadcast(MiniMessage.miniMessage().deserialize(arguments.get("text", "empty")))))
                .build());
    }

    public void messageAction() {
        toRegister.add(Action.registrator().create("message")
                .argument("text")
                .onExecute(((arguments, context) -> context.player().sendRichMessage(arguments.get("text" , "empty"))))
                .build());
    }

    public void actionBarAction() {
        toRegister.add(Action.registrator().create("action-bar")
                .argument("text")
                .onExecute(((arguments, context) -> context.player().sendActionBar(MiniMessage.miniMessage().deserialize(arguments.get("text", "empty")))))
                .build());
    }

    public void commandAction() {
        toRegister.add(Action.registrator().create("command")
                .argument("command")
                .onExecute(((arguments, context) -> Bukkit.dispatchCommand(context.player(), arguments.get("command", ""))))
                .build());
    }

    public void particleAction() {
        toRegister.add(Action.registrator().create("particle")
                .arguments("type", "count", "xOffset", "yOffset", "zOffset", "extra")
                .onExecute(((arguments, context) -> {
                    final Particle type = arguments.getParseEnum("type", "FLAME", Particle.class);
                    final int count = arguments.getParse("count", int.class, "0");
                    final double xOffset = arguments.getParse("xOffset", double.class, "0.0");
                    final double yOffset = arguments.getParse("yOffset", double.class, "0.0");
                    final double zOffset = arguments.getParse("zOffset", double.class, "0.0");
                    final double extra = arguments.getParse("extra", double.class, "0.0");

                    context.getWorld().spawnParticle(type, context.getLocation(), count, xOffset, yOffset, zOffset, extra);
                }))
                .build());
    }

    public void soundAction() {
        toRegister.add(Action.registrator().create("sound")
                .arguments("type", "volume", "pitch")
                .onExecute(((arguments, context) -> {
                    final String type = arguments.get("type", "UI_BUTTON_CLICK");
                    final float volume = arguments.getParse("volume", float.class, "0.0");
                    final float pitch = arguments.getParse("pitch", float.class, "0.0");

                    context.getWorld().playSound(context.getLocation(), Sound.valueOf(type), volume, pitch);
                }))
                .build());
    }

    public List<Action> getToRegister() {
        return Collections.unmodifiableList(toRegister);
    }
}
