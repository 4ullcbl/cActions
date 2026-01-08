package ru.chipsonsky.actionssystem.action.registry.api;


import java.util.Map;

public interface MapRegistry<K, V> {

    void register(K key, V value);

    void unregister(K key);

    boolean contains(K key);

    V get(K key);

    V getOrDefault(K key, V value);

    Map<K, V> getAll();
}
