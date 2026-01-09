# cActions
Простая система текстовых действий, можно использывать в конфигах, для более настраивамой работы плагинов
Основной синтаксис: ```[имя-действия] 1 аргумент, 2 аргумент```
Аргументы раздяляются запятой с пробелом, что позволяет аргументу иметь пробелы

настройка проекта
```java
public final class CActionsExample extends JavaPlugin {

    private ActionAPI actionAPI; // основной обьект для работы а API

    @Override
    public void onEnable() {

        actionAPI = getServer().getServicesManager().load(ActionAPI.class);

        if (actionAPI == null) {
            getLogger().info("cActions not found. Disabling plugin");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // создание акшиона
        actionAPI.registerAction(Action
                .registrator() // .builder()
                .create("action-name") // имя акшиона
                .arguments("1arg", "2arg") // аргументы по порядку
                .onExecute(((arguments, context) -> { // BiConsumer
                    context.player().sendMessage(arguments.get("1arg", "default"));
                    context.player().sendMessage(arguments.get("2arg", default"));
                })
                .build(), this);
    }
```
Исполнение акшиона


Конфиг или другое место с действиями
```yaml
actions:
  - "[message] ты прыгнул"
  - "[particle] FLAME, 10, 0, 0, 0, 0.2"
```

```java
public final class JumpListener implements Listener {

    // получаем через констурктор апи и конфиг(который выше)
    private final FileConfiguration config;
    private final ActionAPI actionAPI;
    
    public JumpListener(FileConfiguration config, ActionAPI actionAPI) {
        this.config = config;
        this.actionAPI = actionAPI;
    }
    
    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        // слушаем ивент прыжка и выполняем действие
        actionAPI.execute(config.getStringList("actions"), new ActionContext(event.getPlayer()));
    }
}
```
