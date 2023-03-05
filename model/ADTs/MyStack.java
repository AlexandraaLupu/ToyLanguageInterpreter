package model.ADTs;

import exceptions.ADTException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;

    public MyStack(){
        stack = new Stack<>();
    }

    @Override
    public void push(T e) {
        stack.push(e);
    }

    @Override
    public T pop() throws ADTException {
        T e = stack.pop();
        if (e == null)
            throw new ADTException("Stack is empty");
        return e;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<T> getReversed() {
        List<T> list = Arrays.asList((T[])stack.toArray());
        Collections.reverse(list);
        return list;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
