package org.example;

import org.example.model.Developer;
import org.example.model.Manager;
import org.example.model.Tester;
import org.example.model.enums.Grade;
import org.example.model.enums.ProgrammingLanguages;
import org.example.model.enums.Skill;
import org.example.model.enums.TestTools;
import org.example.service.scanner.ScannerService;

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

    public double inputSalary() {
        System.out.println("Введите зарплату");
        return scanner.scanDouble();
    }

    public int inputWorkExperience() {
        System.out.println("Введите опыт работы");
        return scanner.scanBorderInt(0, 100);
    }

    public List<Skill> inputSkills() {
        System.out.println("Выберите навыки");
        return Skill.multiSelect(scanner);
    }

    public Grade inputGrade() {
        System.out.println("Выберите грейд");
        return Grade.select(scanner);
    }

    public List<ProgrammingLanguages> inputProgrammingLanguages() {
        System.out.println("Выберите языки программирования");
        return ProgrammingLanguages.multiSelect(scanner);
    }

    public Developer createDeveloper() {
        return new Developer(
                inputName(),
                inputSalary(),
                inputGrade(),
                inputWorkExperience(),
                inputSkills(),
                inputProgrammingLanguages()
        );
    }

    public List<TestTools> inputTestTools() {
        System.out.println("Выберите инструменты тестирования");
        return TestTools.multiSelect(scanner);
    }

    public Tester createTester() {
        return new Tester(
                inputName(),
                inputSalary(),
                inputGrade(),
                inputWorkExperience(),
                inputSkills(),
                inputTestTools()
        );
    }

    public int inputTeamSize() {
        System.out.println("Введите размер команды");
        return scanner.scanBorderInt(0, 20);
    }

    public Manager createManager() {
        return new Manager(
                inputName(),
                inputSalary(),
                inputGrade(),
                inputWorkExperience(),
                inputSkills(),
                inputTeamSize()
        );
    }
}
