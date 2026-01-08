package ru.chipsonsky.actionssystem.action.registry.impl;

import ru.chipsonsky.actionssystem.action.registry.api.MapRegistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapRegistryImpl<K, V> implements MapRegistry<K, V> {
    private final Map<K, V> items = new HashMap<>();

    @Override
    public void register(K key, V value) {
        items.put(key, value);
    }

    @Override
    public void unregister(K key) {
        items.remove(key);
    }

    @Override
    public void forEach(BiConsumer<K, V> action) {
        items.forEach(action);
    }

    @Override
    public boolean contains(K key) {
        return items.containsKey(key);
    }

    @Override
    public V get(K key) {
        return items.get(key);
    }

    @Override
    public V getOrDefault(K key, V value) {
        return items.getOrDefault(key, value);
    }

    @Override
    public Map<K, V> getAll() {
        return Collections.unmodifiableMap(items);
    }
}
