package model.ADTs;

import exceptions.ADTException;
import model.values.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MyIHeap {
    int getFreeValue();
    Map<Integer, Value> getContent();
    int add(Value value);
    void update(Integer position, Value value) throws ADTException;
    Value get(Integer position) throws ADTException;
    boolean containsKey(Integer position);
    void remove(Integer key) throws ADTException;
    Set<Integer> keySet();
    void setContent(Map<Integer, Value> newMap);
}
