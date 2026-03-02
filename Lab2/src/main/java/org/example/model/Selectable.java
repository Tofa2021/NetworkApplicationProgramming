package org.example.model;

import org.example.Nameable;
import org.example.service.ScannerService;

import java.util.List;

public interface Selectable<T extends Nameable> {
    T select(ScannerService scanner);

    List<T> multiSelect(ScannerService scanner, int count);
}
