# cActions
Simple text-actions system for bukkit plugins


Execute action
```java
ActionSystem.getExecutor().execute(actionStr.toString(), new ActionContext(player));
```

Create Action
```java
// this action logging text
Action.registrator().create("log")
    .argument("text")
    .onExecute(((actionArguments, context) -> getLogger().info(actionArguments.get("text", "null"))))
    .register();

public void soundAction() {
    Action.registrator().create("sound")
            .arguments("type", "volume", "pitch")
            .onExecute(((arguments, context) -> {
                final Sound type = arguments.getParseEnum("type", "UI_BUTTON_CLICK", Sound.class);
                final float volume = arguments.getParse("volume", float.class, "0.0");
                final float pitch = arguments.getParse("pitch", float.class, "0.0");

                context.getWorld().playSound(context.getLocation(), type, volume, pitch);
            }))
            .register();
}
```

