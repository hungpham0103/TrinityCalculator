package edu.trinity;

import java.util.LinkedList;
import java.util.EmptyStackException;
public class MyStack<T> {
    private final LinkedList<T> stack = new LinkedList<>();
    public void push(T hello) {
        stack.addFirst(hello);
    }

    public T peek() {
        if (stack.isEmpty())
            throw new EmptyStackException();
        return stack.getFirst();
    }

    public T pop() {
        if (stack.isEmpty())
            throw new EmptyStackException();
        return stack.remove();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
