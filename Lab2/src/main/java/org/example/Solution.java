package org.example;

import org.example.model.Developer;
import org.example.model.Employee;
import org.example.model.Skill;

import java.util.List;

public class Solution {
    public static void solve(ScannerService scanner) {
        mainLoop:
        while (true) {
            System.out.println("""
                    Выберите пункт меню
                    1) Добавить разработчика
                    2) Добавить тестировщика //
                    3) Добавить менеджера //
                    4) Просмотреть сотрудников
                    5) Удалить запись
                    6) Редактировать проект //
                    7) Поиск по имени
                    8) Поиск по навыкам //
                    9) Фильтр по должности
                    10) Фильтр по стажу
                    11) Фильтр по проекту //
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
                    EmployeeService.add(new Developer(name, workExperience, List.of(Skill.PLACEHOLDER1, Skill.PLACEHOLDER2)));
                }
                case 4 -> {
                    List<Employee> employees = EmployeeService.getEmployees();
                    if (employees.isEmpty()) {
                        System.out.println("Нет сотрудников");
                    } else {
                        employees.forEach(System.out::println);
                    }
                }
                case 5 -> {
                    System.out.println("Введите пароль");
                    String password = scanner.scanString();
                    if (AuthService.checkPassword(password)) {
                        EmployeeService.remove(EmployeeService.select());
                    } else {
                        System.out.println("Неправильный пароль");
                    }
                }
                case 7 -> {
                    System.out.println("Введите имя");
                    String name = scanner.scanString();
                    EmployeeService.getByName(name).forEach(System.out::println);
                }
                case 9 -> {
                    EmployeeService.getEmployees().stream()
                            .filter(employee -> employee.getClass().equals(Developer.class))
                            .forEach(System.out::println);
                }
                case 10 -> {
                    System.out.println("Введите минимальный стаж");
                    int minWorkExperience = scanner.scanBorderInt(0, 100);
                    System.out.println("Введите максимальный стаж");
                    int maxWorkExperience = scanner.scanBorderInt(0, 100);
                    if (minWorkExperience <= maxWorkExperience) {
                        EmployeeService.getByWorkExperience(minWorkExperience, maxWorkExperience).forEach(System.out::println);
                    } else {
                        System.out.println("Минимальное значение не может быть больше максимального");
                    }
                }
                case 12 -> {
                    EmployeeService.sortByName();
                }
                case 13 -> {
                    EmployeeService.sortByRole();
                }
                case 14 -> {
                    for (var entry : EmployeeService.getGroupedByRole().entrySet()) {
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
                    EmployeeService.save();
                }
                case 16 -> {
                    EmployeeService.load();
                }
            }
        }
    }
}
