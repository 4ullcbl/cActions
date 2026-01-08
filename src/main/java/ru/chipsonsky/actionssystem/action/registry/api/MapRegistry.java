package ru.chipsonsky.actionssystem.action.registry.api;


import java.util.Map;
import java.util.function.BiConsumer;

public interface MapRegistry<K, V> {

    void register(K key, V value);

    void unregister(K key);

    void forEach(BiConsumer<K, V> action);

    boolean contains(K key);

    V get(K key);

    V getOrDefault(K key, V value);

    Map<K, V> getAll();
}
