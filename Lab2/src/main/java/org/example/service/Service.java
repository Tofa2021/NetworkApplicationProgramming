package org.example.service;

import org.example.Utils;
import org.example.model.Nameable;

import java.util.List;

public interface Service<T extends Nameable> {
    List<T> getElements();

    boolean add(T element);

    T remove(int index);

    boolean remove(T element);

    default T select(ScannerService scanner) {
        return Utils.select(scanner, getElements());
    }

    default void printList() {
        Utils.printList(getElements());
    }
}
