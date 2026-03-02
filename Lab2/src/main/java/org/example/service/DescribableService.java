package org.example.service;

import org.example.Nameable;
import org.example.Utils;
import org.example.model.Describable;

public interface DescribableService<T extends Nameable & Describable> extends Service<T> {
    default void printDescribableList() {
        Utils.printDescribableList(getElements());
    }
}
