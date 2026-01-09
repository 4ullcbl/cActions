package ru.chipsonsky.actionssystem.action.def;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;
import ru.chipsonsky.actionssystem.action.Action;

public final class DefaultAction {
    private boolean register = false;

    public void registerAll(Plugin plugin) {
        if (register) return;

        logAction(plugin);
        broadcastAction();
        messageAction();
        commandAction();
        actionBarAction();
        particleAction();
        soundAction();
        register = true;
    }

    public void logAction(Plugin plugin) {
        Action.registrator().create("log")
                .argument("text")
                .onExecute((arguments, context) -> plugin.getLogger().info(arguments.get("text", "empty")))
                .register();
    }

    public void broadcastAction() {
        Action.registrator().create("broadcast")
                .argument("text")
                .onExecute(((arguments, context) -> Bukkit.broadcast(MiniMessage.miniMessage().deserialize(arguments.get("text", "empty")))))
                .register();
    }

    public void messageAction() {
        Action.registrator().create("message")
                .argument("text")
                .onExecute(((arguments, context) -> context.player().sendRichMessage(arguments.get("text" , "empty"))))
                .register();
    }

    public void actionBarAction() {
        Action.registrator().create("action-bar")
                .argument("text")
                .onExecute(((arguments, context) -> context.player().sendActionBar(MiniMessage.miniMessage().deserialize(arguments.get("text", "empty")))))
                .register();
    }

    public void commandAction() {
        Action.registrator().create("command")
                .argument("command")
                .onExecute(((arguments, context) -> Bukkit.dispatchCommand(context.player(), arguments.get("command", ""))))
                .register();
    }

    public void particleAction() {
        Action.registrator().create("particle")
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
                .register();
    }

    public void soundAction() {
        Action.registrator().create("sound")
                .arguments("type", "volume", "pitch")
                .onExecute(((arguments, context) -> {
                    final String type = arguments.get("type", "UI_BUTTON_CLICK");
                    final float volume = arguments.getParse("volume", float.class, "0.0");
                    final float pitch = arguments.getParse("pitch", float.class, "0.0");

                    context.getWorld().playSound(context.getLocation(), Sound.valueOf(type), volume, pitch);
                }))
                .register();
    }
}
