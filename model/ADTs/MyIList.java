package model.ADTs;

import java.util.List;

public interface MyIList<T> {
    void add(T elem);

    boolean isEmpty();

    List<T> getList();

    String toString();
}
