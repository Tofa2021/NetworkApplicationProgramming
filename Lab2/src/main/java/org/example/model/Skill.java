package org.example.model;

import org.example.Nameable;
import org.example.Utils;
import org.example.service.ScannerService;

import java.util.List;

public enum Skill implements Nameable {
    PLACEHOLDER1("PLACEHOLDER1"),
    PLACEHOLDER2("PLACEHOLDER2"),
    PLACEHOLDER3("PLACEHOLDER3"),
    ;

    private final String name;

    Skill(String name) {
        this.name = name;
    }

    public static Skill select(ScannerService scanner) {
        return Utils.select(scanner, List.of(values()));
    }

    public static List<Skill> multiSelect(ScannerService scanner, int count) {
        return Utils.multiSelect(scanner, List.of(values()), count);
    }

    public static List<Skill> multiSelect(ScannerService scanner) {
        return Utils.multiSelect(scanner, List.of(values()));
    }

    @Override
    public String getName() {
        return name;
    }
}
