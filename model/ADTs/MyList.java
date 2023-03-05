package model.ADTs;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        synchronized (this) {
            list.add(elem);
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return list.isEmpty();
        }
    }

    @Override
    public List<T> getList() {
        synchronized (this) {
            return list;
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
