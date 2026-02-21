package org.example;

import java.util.Iterator;
import java.util.List;

public class MockScannerService implements ScannerService {
    private final Iterator<String> iterator;

    public MockScannerService(Iterator<String> iterator) {
        this.iterator = iterator;
    }

    public MockScannerService(String... strings) {
        this.iterator = List.of(strings).iterator();
    }

    @Override
    public String scanString() {
        return iterator.next();
    }
}
