package model.ADTs;

import exceptions.ADTException;
import model.values.Value;

import java.util.*;

public class MyHeap implements MyIHeap {
    Map<Integer, Value> heap;
    int freeLocation;

    public MyHeap() {
        heap = new HashMap<>();
        freeLocation = 1;
    }

    public int newValue() {
        freeLocation += 1;
        while (freeLocation == 0 || heap.containsKey(freeLocation))
            freeLocation += 1;
        return freeLocation;
    }

    @Override
    public int getFreeValue() {

        synchronized (this) {
            return freeLocation;
        }
    }

    @Override
    public Map<Integer, Value> getContent() {
        synchronized (this) {
            return heap;
        }
    }

    @Override
    public void setContent(Map<Integer, Value> newMap) {
        synchronized (this) {
            this.heap = newMap;
        }
    }

    @Override
    public int add(Value value) {
        synchronized (this) {
            heap.put(freeLocation, value);
            Integer toReturn = freeLocation;
            freeLocation = newValue();
            return toReturn;
        }
    }

    @Override
    public void update(Integer position, Value value) throws ADTException {
        synchronized (this) {
            if (!heap.containsKey(position))
                throw new ADTException(String.format("%d is not present in the heap", position));
            heap.put(position, value);
        }
    }

    @Override
    public Value get(Integer position) throws ADTException {
        synchronized (this) {
            if (!heap.containsKey(position))
                throw new ADTException(String.format("%d is not present in the heap", position));
            return heap.get(position);
        }
    }

    @Override
    public boolean containsKey(Integer position) {
        synchronized (this) {
            return this.heap.containsKey(position);
        }
    }

    @Override
    public void remove(Integer key) throws ADTException {
        synchronized (this) {
            if (!containsKey(key))
                throw new ADTException(key + " is not defined.");
            freeLocation = key;
            this.heap.remove(key);
        }
    }

    @Override
    public Set<Integer> keySet() {
        synchronized (this) {
            return heap.keySet();
        }
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }
}
