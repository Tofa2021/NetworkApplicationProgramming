package org.example;

import org.example.model.*;

import java.util.Arrays;
import java.util.List;

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
                    employeeService.add(new Developer(name, workExperience, List.of(Skill.PLACEHOLDER1, Skill.PLACEHOLDER2)));
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
                        employeeService.remove(employeeService.select());
                    } else {
                        System.out.println("Неправильный пароль");
                    }
                }
                case 6 -> {
                    projectService.addEmployeeToProject(employeeService);
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
                    projectService.select().printEmployees();
                }
                case 12 -> {
                    employeeService.sortByName();
                }
                case 13 -> {
                    employeeService.sortByRole();
                }
                case 14 -> {
                    for (var entry : employeeService.getGroupedByRole().entrySet()) {
                        List<Employee> employees = entry.getValue();
                        int employeesCount = employees.size();
                        int allWorkExperience = employees.stream()
                                .map(Employee::getWorkExperience)
                                .reduce(Integer::sum)
                                .get();
                        double averageRoleWorkExperience = (double) allWorkExperience / employeesCount;
                        System.out.println(entry.getKey() + " Количество сотрудников: " + employeesCount + " Средний стаж: " + averageRoleWorkExperience);
                    }
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
