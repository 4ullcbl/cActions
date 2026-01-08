// Javadoc created by deepseek(sorry)

package ru.chipsonsky.actionssystem.action.arguments;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.chipsonsky.actionssystem.utils.parser.api.Parser;
import ru.chipsonsky.actionssystem.utils.parser.impl.StringParser;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for managing and accessing action arguments.
 * Provides methods for retrieving arguments in various data types.
 */
public class ActionArguments {

    protected final Map<String, String> inputKeyValueMap = new HashMap<>();
    protected final String rawInput;
    private final Parser<String> parser;

    /**
     * Constructs an ActionArguments instance from raw input string.
     *
     * @param rawInput The raw string input containing arguments
     */
    public ActionArguments(String rawInput) {
        this.rawInput = rawInput;
        parser = new StringParser();
    }

    /**
     * Adds or updates an argument in the arguments map.
     *
     * @param key The argument key
     * @param val The argument value as a string
     */
    public void put(String key, String val) {
        inputKeyValueMap.put(key, val);
    }

    /**
     * Retrieves a string value from arguments.
     * Returns default value if the key is not found.
     *
     * @param key The argument key to retrieve
     * @param def The default value to return if key is not found
     * @return The argument value or default value
     * @throws NullPointerException if key or default value is null
     */
    public String get(@NotNull String key, @NotNull String def) {
        return inputKeyValueMap.getOrDefault(key, def);
    }

    /**
     * Retrieves and parses an argument value to specified type.
     * Useful for primitive types and their wrappers.
     *
     * @param <T> The return type
     * @param key The argument key to retrieve
     * @param primitiveClass The class of the target type
     * @param def The default value as string (used if parsing fails)
     * @return The parsed argument value or default value
     * @throws NullPointerException if key, primitiveClass, or default value is null
     */
    public <T> T getParse(@NotNull String key, @NotNull Class<T> primitiveClass, @NotNull String def) {
        try {
            return parser.parse(get(key, ""), primitiveClass);
        } catch (Exception e) {
            return parser.parse(def, primitiveClass);
        }
    }

    /**
     * Retrieves an argument value as an enum constant.
     * Returns default value if parsing fails.
     *
     * @param <T> The enum type
     * @param key The argument key to retrieve
     * @param def The default value as string
     * @param enumClass The enum class
     * @return The enum constant or default value
     * @throws NullPointerException if key, default value, or enumClass is null
     * @throws IllegalArgumentException if default value doesn't match any enum constant
     */
    public <T extends Enum<T>> T getParseEnum(@NotNull String key, @NotNull String def, Class<T> enumClass) {
        try {
            return Enum.valueOf(enumClass, get(key, ""));
        } catch (IllegalArgumentException e) {
            return Enum.valueOf(enumClass, def);
        }
    }

    /**
     * Retrieves a Player object by name from arguments.
     *
     * @param key The argument key containing player name
     * @return The Player object or null if player is not found
     * @throws NullPointerException if key is null
     */
    public Player getPlayer(@NotNull String key) {
        return Bukkit.getPlayer(get(key, ""));
    }

    /**
     * Retrieves a string argument and performs regex replacement.
     *
     * @param key The argument key to retrieve
     * @param def The default value
     * @param regex The regular expression to match
     * @param replacement The replacement string
     * @return The resulting string after replacement
     * @throws NullPointerException if key, default value, regex, or replacement is null
     */
    public String getReplace(@NotNull String key, @NotNull String def, String regex, String replacement) {
        return get(key, def).replaceAll(regex, replacement);
    }
}
