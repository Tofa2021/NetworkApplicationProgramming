package org.example.model;

import org.example.Utils;
import org.example.service.ScannerService;

import java.util.List;

public enum ProgrammingLanguages implements Nameable {
    PYTHON("Python"),
    JAVA("Java"),
    C_SHARP("С#"),
    C_PLUS_PLUS("C++"),
    GO("GO"),
    ;

    private final String name;

    ProgrammingLanguages(String name) {
        this.name = name;
    }

    public static ProgrammingLanguages select(ScannerService scanner) {
        return Utils.select(scanner, List.of(values()));
    }

    public static List<ProgrammingLanguages> multiSelect(ScannerService scanner, int count) {
        return Utils.multiSelect(scanner, List.of(values()), count);
    }

    public static List<ProgrammingLanguages> multiSelect(ScannerService scanner) {
        return Utils.multiSelect(scanner, List.of(values()));
    }

    @Override
    public String getName() {
        return name;
    }
}
