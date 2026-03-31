package org.example.model.enums;

import org.example.Utils;
import org.example.model.interfaces.Nameable;
import org.example.service.scanner.ScannerService;

import java.util.List;

public enum Skill implements Nameable {
    COMMUNICATION("Коммуникабельность"),
    TEAMWORK("Работа в команде"),
    PROBLEM_SOLVING("Решение проблем"),
    CRITICAL_THINKING("Критическое мышление"),
    TIME_MANAGEMENT("Тайм-менеджмент"),
    LEADERSHIP("Лидерство"),
    MENTORING("Наставничество"),
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
