package org.example.service;

import org.example.Utils;
import org.example.model.Nameable;

import java.util.List;

public interface Service<T extends Nameable> {
    ScannerService getScannerService();

    List<T> getElements();

    boolean add(T element);

    T remove(int index);

    boolean remove(T element);

    void removeSelected();

    default T select() {
        return Utils.select(getScannerService(), getElements());
    }

    default T select(List<T> elements) {
        return Utils.select(getScannerService(), elements);
    }

    default void printList() {
        Utils.printList(getElements());
    }
}
