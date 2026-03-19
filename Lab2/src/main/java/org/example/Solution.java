package org.example;

import org.example.model.*;
import org.example.service.AuthService;
import org.example.service.EmployeeService;
import org.example.service.ProjectService;
import org.example.service.ScannerService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Solution {
    public static void solve(
            ScannerService scanner,
            EmployeeService employeeService,
            ProjectService projectService,
            EmployeeFactory employeeFactory
    ) {
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
                    17) Перевести сотрудника на проект
                    18) Удалить из проекта
                    19) Просмотреть журнал переводов
                    20) Статистика по грейдам
                    0) Выход""");
            switch (scanner.scanInt()) {
                case 0 -> {
                    break mainLoop;
                }
                case 1 -> employeeService.add(employeeFactory.createDeveloper());

                case 2 -> employeeService.add(employeeFactory.createTester());

                case 3 -> employeeService.add(employeeFactory.createManager());

                case 4 -> employeeService.printDescribableList();

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
                    Project project = projectService.select();
                    List<Employee> employees = employeeService.getElements().stream()
                            .filter(employee -> employee.getProject() == null)
                            .toList();
                    if (employees.isEmpty()) {
                        System.out.println("Пусто");
                        return;
                    }
                    Employee employee = employeeService.select(employees);
                    projectService.addToProject(project, employee);
                }
                case 7 -> {
                    System.out.println("Введите имя");
                    String name = scanner.scanString();
                    Utils.printList(employeeService.getByName(name));
                }
                case 8 -> {
                    System.out.println("Выберите навыки");
                    List<Skill> skills = Skill.multiSelect(scanner);
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
                    Utils.printList(employeeService.getByRole(predicate));
                }
                case 10 -> {
                    System.out.println("Введите минимальный стаж");
                    int minWorkExperience = scanner.scanBorderInt(0, 100);

                    System.out.println("Введите максимальный стаж");
                    int maxWorkExperience = scanner.scanBorderInt(0, 100);

                    if (minWorkExperience <= maxWorkExperience) {
                        Utils.printList(employeeService.getByWorkExperience(minWorkExperience, maxWorkExperience));
                    } else {
                        System.out.println("Минимальное значение не может быть больше максимального");
                    }
                }
                case 11 -> {
                    System.out.println("Выберите проект");
                    Project project = projectService.select();
                    List<Employee> employees = employeeService.getByProject(project);
                    Utils.printList(employees);
                }
                case 12 -> employeeService.sortByName();

                case 13 -> employeeService.sortByRole();

                case 14 -> {
                    var groupedByRole = employeeService.getMappedByRole();
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
                case 15 -> employeeService.save();

                case 16 -> employeeService.load();

                case 17 -> {
                    Project newProject = projectService.select();
                    List<Employee> employees = employeeService.getElements().stream()
                            .filter(employee -> employee.getProject() != newProject)
                            .toList();
                    if (employees.isEmpty()) {
                        System.out.println("Пусто");
                        return;
                    }
                    Employee employee = employeeService.select(employees);
                    projectService.transfer(newProject, employee);
                }
                case 18 -> {
                    Project project = projectService.select();
                    List<Employee> projectEmployees = employeeService.getElements().stream()
                            .filter(e -> e.getProject() == project)
                            .toList();
                    if (projectEmployees.isEmpty()) {
                        System.out.println("Пусто");
                        return;
                    }
                    Employee employee = employeeService.select(projectEmployees);
                    projectService.removeFromProject(employee);
                }
                case 19 -> projectService.getProjectLogger().printLogs();

                case 20 -> {
                    var map = employeeService.getMappedByGrade();
                    for (var entry : map.entrySet()) {
                        System.out.println(entry.getKey().getName() + " " + entry.getValue().size());
                    }
                }
            }
        }
    }
}
