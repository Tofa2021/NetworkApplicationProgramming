package org.example.model;

import org.example.Nameable;
import org.example.Utils;
import org.example.service.ScannerService;

import java.util.List;

public interface SelectableEnum<T extends Nameable> extends Selectable<T> {

    @Override
    default T select(ScannerService scanner) {
        return Utils.select(scanner, List.of(values()));
    }

    @Override
    default List<T> multiSelect(ScannerService scanner, int count) {
        return Utils.multiSelect(scanner, List.of(values()), count);
    }

    T[] values();
}
