package model.ADTs;

import exceptions.ADTException;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MyBarrierTable implements MyIBarrierTable{
    private HashMap<Integer, Pair<Integer, List<Integer>>> barrierTable;
    private int freeLocation = 0;

    public MyBarrierTable() {
        this.barrierTable = new HashMap<>();
    }

    @Override
    public void put(int key, Pair<Integer, List<Integer>> value) throws ADTException {
        synchronized (this) {
            if (!barrierTable.containsKey(key)) {
                barrierTable.put(key, value);
            } else {
                throw new ADTException("barrierTable already contains the key " + key);
            }
        }
    }

    @Override
    public Pair<Integer, List<Integer>> get(int key) throws ADTException {
        synchronized (this) {
            if (barrierTable.containsKey(key)) {
                return barrierTable.get(key);
            } else throw new ADTException("barrierTable does not contain " + key);
        }
    }

    @Override
    public boolean containsKey(int key) {
        synchronized (this) {
            return barrierTable.containsKey(key);
        }
    }

    @Override
    public int getFreeAddress() {
        synchronized (this) {
            freeLocation++;
            return freeLocation;
        }
    }

    @Override
    public void setFreeAddress(int freeAddress) {
        synchronized (this) {
            this.freeLocation = freeAddress;
        }
    }

    @Override
    public void update(int key, Pair<Integer, List<Integer>> value) throws ADTException {
        synchronized (this) {
            if (barrierTable.containsKey(key)) {
                barrierTable.replace(key, value);
            }
        }
    }

    @Override
    public HashMap<Integer, Pair<Integer, List<Integer>>> getBarrierTable() {
        synchronized (this) {
            return barrierTable;
        }
    }

    @Override
    public Set<Integer> keySet() {
        return barrierTable.keySet();
    }

    @Override
    public String toString() {
        return barrierTable.toString();
    }
}
