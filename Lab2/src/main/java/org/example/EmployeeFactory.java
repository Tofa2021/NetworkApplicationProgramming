package org.example;

import org.example.model.*;
import org.example.service.ScannerService;

import java.util.List;

public class EmployeeFactory {
    private final ScannerService scanner;

    public EmployeeFactory(ScannerService scanner) {
        this.scanner = scanner;
    }

    public String inputName() {
        System.out.println("Введите имя сотрудника");
        return scanner.scanNonEmptyString();
    }

    public int inputWorkExperience() {
        System.out.println("Введите опыт работы");
        return scanner.scanBorderInt(0, 100);
    }

    public List<Skill> inputSkills() {
        System.out.println("Выберите навыки");
        return Skill.multiSelect(scanner);
    }

    public List<ProgrammingLanguages> inputProgrammingLanguages() {
        System.out.println("Выберите языки программирования");
        return ProgrammingLanguages.multiSelect(scanner);
    }

    public Developer inputDeveloper() {
        return new Developer(
                inputName(),
                inputWorkExperience(),
                inputSkills(),
                inputProgrammingLanguages()
        );
    }

    public List<TestTools> inputTestTools() {
        System.out.println("Выберите инструменты тестирования");
        return TestTools.multiSelect(scanner);
    }

    public Tester inputTester() {
        return new Tester(
                inputName(),
                inputWorkExperience(),
                inputSkills(),
                inputTestTools()
        );
    }

    public int inputTeamSize() {
        System.out.println("Введите размер команды");
        return scanner.scanBorderInt(0, 20);
    }

    public Manager inputManager() {
        return new Manager(
                inputName(),
                inputWorkExperience(),
                inputSkills(),
                inputTeamSize()
        );
    }
}
