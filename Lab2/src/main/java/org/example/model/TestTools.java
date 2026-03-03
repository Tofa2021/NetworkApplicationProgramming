package org.example.model;

import org.example.Utils;
import org.example.service.ScannerService;

import java.util.List;

public enum TestTools implements Nameable {
    POSTMAN("Postman"),
    SELENIUM("Selenium"),
    SOAPUI("SoapUI"),
    DBEAVER("DBeaver"),
    ;

    private final String name;

    TestTools(String name) {
        this.name = name;
    }

    public static TestTools select(ScannerService scanner) {
        return Utils.select(scanner, List.of(values()));
    }

    public static List<TestTools> multiSelect(ScannerService scanner, int count) {
        return Utils.multiSelect(scanner, List.of(values()), count);
    }

    public static List<TestTools> multiSelect(ScannerService scanner) {
        return Utils.multiSelect(scanner, List.of(values()));
    }

    @Override
    public String getName() {
        return name;
    }
}
