package org.example.model;

import org.example.Nameable;
import org.example.Utils;
import org.example.service.ScannerService;

import java.util.List;

public enum ProgrammingLanguages implements Nameable, SelectableEnum<ProgrammingLanguages> {
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

    @Override
    public String getName() {
        return name;
    }

    public ProgrammingLanguages select(ScannerService scanner) {
        return Utils.select(scanner, List.of(values()));
    }
}
