package model.ADTs;

import exceptions.ADTException;

import java.util.List;

public interface MyIStack<T> {
    void push(T e);
    T pop() throws ADTException;
    boolean isEmpty();
    List<T> getReversed();

    String toString();
}
