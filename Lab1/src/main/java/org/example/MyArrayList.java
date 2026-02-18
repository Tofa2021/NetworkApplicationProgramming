package org.example;

import java.util.AbstractList;
import java.util.Arrays;

public class MyArrayList<T> extends AbstractList<T> {
    private Object[] elementData = new Object[10];
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T element) {
        if (size == elementData.length) {
            grow();
        }

        elementData[size] = element;
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        return (T) elementData[index];
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, size * 2);
    }
}
