package org.example.service;

import org.example.Utils;
import org.example.model.Describable;
import org.example.model.Nameable;

public interface DescribableService<T extends Nameable & Describable> extends Service<T> {
    default void printDescribableList() {
        Utils.printDescribableList(getElements());
    }
}
