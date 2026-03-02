package org.example;

import org.example.model.*;
import org.example.service.AuthService;
import org.example.service.EmployeeService;
import org.example.service.ProjectService;
import org.example.service.ScannerService;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Solution {
    public static void solve(ScannerService scanner, EmployeeService employeeService, ProjectService projectService) {
        mainLoop:
        while (true) {
            System.out.println("""
                    Выберите пункт меню
                    1) Добавить разработчика
                    2) Добавить тестировщика
                    3) Добавить менеджера
                    4) Просмотреть сотрудников
                    5) Удалить запись
                    6) Добавить сотрудника в проект
                    7) Поиск по имени
                    8) Поиск по навыкам
                    9) Фильтр по должности
                    10) Фильтр по стажу
                    11) Фильтр по проекту
                    12) Сортировка по имени
                    13) Сортировка по должности
                    14) Статистика по ролям
                    15) Сохранить базу сотрудников
                    16) Загрузить базу сотрудников
                    0) Выход""");
            switch (scanner.scanInt()) {
                case 0 -> {
                    break mainLoop;
                }
                case 1 -> {
                    System.out.println("Введите имя сотрудника");
                    String name = scanner.scanString();

                    System.out.println("Введите опыт работы");
                    int workExperience = scanner.scanBorderInt(0, 100);

                    System.out.println("Выберите сколько языков знает разработчик");
                    int count = scanner.scanBorderInt(0, ProgrammingLanguages.values().length);

                    System.out.println("Выберите языки программирования");
                    List<ProgrammingLanguages> programmingLanguages = ProgrammingLanguages.GO.multiSelect(scanner, count);

                    employeeService.add(
                            new Developer(
                                    name,
                                    workExperience,
                                    List.of(Skill.PLACEHOLDER1, Skill.PLACEHOLDER2),
                                    programmingLanguages
                            )
                    );
                }
                case 2 -> {
                    System.out.println("Введите имя сотрудника");
                    String name = scanner.scanString();
                    System.out.println("Введите опыт работы");
                    int workExperience = scanner.scanBorderInt(0, 100);
                    employeeService.add(new Tester(name, workExperience, List.of(Skill.PLACEHOLDER1, Skill.PLACEHOLDER2)));
                }
                case 3 -> {
                    System.out.println("Введите имя сотрудника");
                    String name = scanner.scanString();
                    System.out.println("Введите опыт работы");
                    int workExperience = scanner.scanBorderInt(0, 100);
                    employeeService.add(new Manager(name, workExperience, List.of(Skill.PLACEHOLDER1, Skill.PLACEHOLDER2)));
                }
                case 4 -> {
                    employeeService.printList();
                }
                case 5 -> {
                    System.out.println("Введите пароль");
                    String password = scanner.scanString();
                    if (AuthService.checkPassword(password)) {
                        employeeService.remove(employeeService.select(scanner));
                    } else {
                        System.out.println("Неправильный пароль");
                    }
                }
                case 6 -> {
                    projectService.addEmployeeToProject(scanner, employeeService);
                }
                case 7 -> {
                    System.out.println("Введите имя");
                    String name = scanner.scanString();
                    employeeService.getByName(name).forEach(System.out::println);
                }
                case 8 -> {
                    System.out.println("Введите количество навыков");
                    int count = scanner.scanBorderInt(0, Skill.values().length);
                    System.out.println("Выберите навыки");
                    List<Skill> skills = Utils.multiSelect(scanner, Arrays.stream(Skill.values()).toList(), count);
                    Utils.printList(employeeService.getBySkills(skills));
                }
                case 9 -> {
                    System.out.println("""
                            Выберите должность
                            1) Разработчик
                            2) Тестировщик
                            3) Менеджер""");
                    Predicate<Employee> predicate = switch (scanner.scanBorderInt(0, 3)) {
                        case 1 -> Developer.getIsInstancePredicate();
                        case 2 -> Tester.getIsInstancePredicate();
                        case 3 -> Manager.getIsInstancePredicate();
                        default -> throw new NoSuchElementException();
                    };
                    employeeService.getByRole(predicate);
                }
                case 10 -> {
                    System.out.println("Введите минимальный стаж");
                    int minWorkExperience = scanner.scanBorderInt(0, 100);
                    System.out.println("Введите максимальный стаж");
                    int maxWorkExperience = scanner.scanBorderInt(0, 100);
                    if (minWorkExperience <= maxWorkExperience) {
                        employeeService.getByWorkExperience(minWorkExperience, maxWorkExperience).forEach(System.out::println);
                    } else {
                        System.out.println("Минимальное значение не может быть больше максимального");
                    }
                }
                case 11 -> {
                    System.out.println("Выберите проект");
                    projectService.select(scanner).printEmployees();
                }
                case 12 -> {
                    employeeService.sortByName();
                }
                case 13 -> {
                    employeeService.sortByRole();
                }
                case 14 -> {
                    var groupedByRole = employeeService.getGroupedByRole();
                    if (groupedByRole.isEmpty()) {
                        System.out.println("Нет сотрудников для статистики");
                        continue;
                    }

                    groupedByRole.forEach((role, employees) -> {
                        int count = employees.size();
                        double averageWorkExperience = employees.stream()
                                .mapToInt(Employee::getWorkExperience)
                                .average()
                                .orElse(0);

                        System.out.printf("%s Количество сотрудников: %d Средний стаж: %.2f%n",
                                role, count, averageWorkExperience);
                    });
                }
                case 15 -> {
                    employeeService.save();
                }
                case 16 -> {
                    employeeService.load();
                }
            }
        }
    }
}
