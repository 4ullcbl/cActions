# cActions
Простая система текстовых действий, можно использывать в конфигах, для более настраивамой работы плагинов


Выполнение действия
```java
final String actionString = "[command] say hello";  // полученно с конфига или из комманды

ActionSystem.getExecutor().execute(actionString, new ActionContext(player));
// действие срабатывает 
```

Создание действий
```java
import ru.chipsonsky.actionssystem.action.Action;

// создаем действие для логгирывания
Action.registrator().create("log")
    .argument("text") // 1 аргумент - text лога
    .onExecute(((actionArguments, context) -> getLogger().info(actionArguments.get("text", "null")))) // получаем логгер и логгируем текст(который получаем из аргументов, null - деф значение если аргумент = null)
    .register();

// более сложный пример
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

