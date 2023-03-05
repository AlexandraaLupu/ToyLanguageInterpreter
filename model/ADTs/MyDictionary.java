package model.ADTs;

import exceptions.ADTException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private HashMap<K, V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        synchronized (this) {
            dictionary.put(key, value);
        }
    }

    @Override
    public boolean isDefined(K key) {
        synchronized (this) {
            return dictionary.containsKey(key);
        }
    }

    @Override
    public void update(K key, V value) throws ADTException {
        synchronized (this) {
            if (!isDefined(key))
                throw new ADTException(key + " is not defined");
            dictionary.put(key, value);
        }
    }

    @Override
    public V lookUp(K key) throws ADTException {
        synchronized (this) {
            if (!isDefined(key))
                throw new ADTException(key + " is not defined");
            return dictionary.get(key);
        }
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    @Override
    public void remove(K key) throws ADTException {
        synchronized (this) {
            if (dictionary.containsKey(key))
                dictionary.remove(key);
            else throw new ADTException("The key " + key.toString() + " is not in the dictionary");
        }
    }

    @Override
    public Set<K> getKeys() {
        synchronized (this) {
            return dictionary.keySet();
        }
    }

    @Override
    public Collection<V> getValues() {
        synchronized (this) {
            return this.dictionary.values();
        }
    }

    @Override
    public Map<K, V> getContent() {
        synchronized (this) {
            return dictionary;
        }

    }

    @Override
    public MyIDictionary<K, V> deepCopy() throws ADTException {
        MyIDictionary<K, V> toReturn = new MyDictionary<>();
        for (K key: getKeys())
            toReturn.put(key, lookUp(key));
        return toReturn;
    }
}
