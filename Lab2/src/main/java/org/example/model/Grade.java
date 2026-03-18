package org.example.model;

import org.example.Utils;
import org.example.service.ScannerService;

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
