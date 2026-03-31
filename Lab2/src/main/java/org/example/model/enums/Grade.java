package org.example.model.enums;

import org.example.Utils;
import org.example.model.interfaces.Nameable;
import org.example.service.scanner.ScannerService;

import java.util.List;

public enum Grade implements Nameable {
    TRAINEE("Стажер"),
    JUNIOR("Джуниор"),
    MIDDLE("Мидл"),
    SENIOR("Сеньор"),
    LEAD("Лид"),
    ;

    private final String name;

    Grade(String name) {
        this.name = name;
    }

    public static Grade select(ScannerService scanner) {
        return Utils.select(scanner, List.of(values()));
    }

    @Override
    public String getName() {
        return name;
    }
}
