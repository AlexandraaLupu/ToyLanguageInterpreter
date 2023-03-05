package model.ADTs;

import exceptions.ADTException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<K, V> {
    void put(K key, V value);

    boolean isDefined(K key);

    void update(K key, V value) throws ADTException;

    V lookUp(K key) throws ADTException;

    String toString();

    void remove(K key)  throws ADTException;

    Set<K> getKeys();

    public Collection<V> getValues();
    public Map<K, V> getContent();

    public MyIDictionary<K, V> deepCopy() throws ADTException;
}
