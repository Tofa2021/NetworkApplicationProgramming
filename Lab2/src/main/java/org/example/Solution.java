package org.example;

import org.example.model.*;
import org.example.model.enums.Skill;
import org.example.service.EmployeeService;
import org.example.service.GlobalStorageService;
import org.example.service.ProjectService;
import org.example.service.ProjectServiceImpl;
import org.example.service.scanner.ScannerService;
import org.example.service.storage.CompanyData;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Solution {
    private final ScannerService scannerService;
    private final EmployeeService employeeService;
    private final ProjectService<Employee> projectService;
    private final GlobalStorageService globalStorageService;

    public Solution(
            ScannerService scannerService,
            EmployeeService employeeService,
            ProjectService<Employee> projectService,
            GlobalStorageService globalStorageService
    ) {
        this.scannerService = scannerService;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.globalStorageService = globalStorageService;
    }

    private void handleEmployees() {
        System.out.println("""
                1) Добавить разработчика
                2) Добавить тестировщика
                3) Добавить менеджера
                4) Просмотреть сотрудников
                5) Редактировать сотрудника
                6) Удалить сотрудника
                7) Поиск по имени
                8) Поиск по навыкам
                9) Фильтр по должности
                10) Фильтр по стажу
                11) Фильтр по проекту
                12) Фильтр должность ∧ стаж ∧ навыки
                13) Сортировка по имени
                14) Сортировка по должности
                15) Статистика по ролям
                16) Статистика по грейдам
                17) Назад
                """);
        switch (scannerService.scanInt()) {
            case 1 -> employeeService.createDeveloper();

            case 2 -> employeeService.createTester();

            case 3 -> employeeService.createManager();

            case 4 -> employeeService.printDescribableList();

            case 5 -> employeeService.update();

            case 6 -> employeeService.removeSelected();

            case 7 -> {
                System.out.println("Введите имя");
                String name = scannerService.scanString();
                Utils.printList(employeeService.getByName(name));
            }

            case 8 -> {
                System.out.println("Выберите навыки");
                List<Skill> skills = Skill.multiSelect(scannerService);
                Utils.printList(employeeService.getBySkills(skills));
            }

            case 9 -> {
                System.out.println("""
                        Выберите должность
                        1) Разработчик
                        2) Тестировщик
                        3) Менеджер""");
                Predicate<Employee> predicate = switch (scannerService.scanBorderInt(0, 3)) {
                    case 1 -> Developer.getIsInstancePredicate();
                    case 2 -> Tester.getIsInstancePredicate();
                    case 3 -> Manager.getIsInstancePredicate();
                    default -> throw new NoSuchElementException();
                };
                Utils.printList(employeeService.getByRole(predicate));
            }

            case 10 -> {
                System.out.println("Введите минимальный стаж");
                int minWorkExperience = scannerService.scanBorderInt(0, 100);

                System.out.println("Введите максимальный стаж");
                int maxWorkExperience = scannerService.scanBorderInt(0, 100);

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

            case 12 -> {
                System.out.println("""
                        Выберите должность
                        1) Разработчик
                        2) Тестировщик
                        3) Менеджер""");
                Predicate<Employee> predicate = switch (scannerService.scanBorderInt(0, 3)) {
                    case 1 -> Developer.getIsInstancePredicate();
                    case 2 -> Tester.getIsInstancePredicate();
                    case 3 -> Manager.getIsInstancePredicate();
                    default -> throw new NoSuchElementException();
                };

                System.out.println("Введите минимальный стаж");
                int minWorkExperience = scannerService.scanBorderInt(0, 100);

                System.out.println("Введите максимальный стаж");
                int maxWorkExperience = scannerService.scanBorderInt(0, 100);
                if (minWorkExperience > maxWorkExperience) {
                    System.out.println("Минимальное значение не может быть больше максимального");
                    return;
                }

                System.out.println("Выберите навыки");
                List<Skill> skills = Skill.multiSelect(scannerService);

                Utils.printDescribableList(employeeService.getFiltered(predicate, minWorkExperience, maxWorkExperience, skills));
            }

            case 13 -> employeeService.sortByName();

            case 14 -> employeeService.sortByRole();

            case 15 -> {
                var groupedByRole = employeeService.getMappedByRole();
                if (groupedByRole.isEmpty()) {
                    System.out.println("Нет сотрудников для статистики");
                    return;
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

            case 16 -> {
                var map = employeeService.getMappedByGrade();
                for (var entry : map.entrySet()) {
                    System.out.println(entry.getKey().getName() + " " + entry.getValue().size());
                }
            }

//            case 17 -> employeeService.save();
//
//            case 18 -> employeeService.load();

            case 17 -> {
            }

            default -> System.out.println("Невозможный пункт меню");
        }
    }

    private void handleProjects() {
        System.out.println("""
                1) Добавить проект
                2) Просмотреть проекты
                3) Редактировать проект
                4) Удалить проект
                5) Назад
                """);
        switch (scannerService.scanInt()) {
            case 1 -> projectService.create();

            case 2 -> projectService.printList();

            case 3 -> projectService.update();

            case 4 -> projectService.removeSelected();

            case 5 -> {
            }

            default -> System.out.println("Невозможный пункт меню");
        }
    }

    private void handleProjectAssignable() {
        System.out.println("""
                1) Добавить сотрудника в проект
                2) Перевести сотрудника на проект
                3) Удалить из проекта
                4) Просмотреть журнал переводов
                5) Посмотреть проекты и сотрудников в нем
                6) Сохранить данные
                7) Загрузить данные
                8) Назад
                """);
        switch (scannerService.scanInt()) {
            case 1 -> {
                System.out.println("Выберите проект в который добавить сотрудника");
                Project project = projectService.select();
                System.out.println("Выберите сотрудника");
                Employee employee = employeeService.selectWithoutProject();
                if (employee == null) {
                    return;
                }
                projectService.addToProject(project, employee);
            }

            case 2 -> {
                System.out.println("Выберите проект в который перевести сотрудника");
                Project project = projectService.select();
                System.out.println("Выберите сотрудника");
                Employee employee = employeeService.selectWithProjectExcludingProject(project);
                projectService.transfer(project, employee);
            }

            case 3 -> {
                System.out.println("Выберите проект");
                Project project = projectService.select();
                System.out.println("Выберите сотрудника");
                Employee employee = employeeService.select(projectService.getProjectAssignables(project));
                projectService.removeFromProject(employee);
            }

            case 4 -> projectService.printTransferLogs();

            case 5 -> projectService.printProjectAndEmployees();

            case 6 ->
                    globalStorageService.save(new CompanyData(employeeService.getElements(), projectService.getElements()));

            case 7 -> {
                CompanyData data = globalStorageService.load();
                employeeService.setAll(data.employees());

                projectService.setAll(data.projects());

                if (projectService instanceof ProjectServiceImpl) {
                    ((ProjectServiceImpl) projectService).restoreProjectAssignments(employeeService.getElements());
                }
            }

            case 8 -> {
            }

            default -> System.out.println("Невозможный пункт меню");
        }
    }


    public void solve() {
        while (true) {
            System.out.println("""
                    
                    Выберите пункт меню
                    1) Управление сотрудниками
                    2) Управление проектами
                    3) Работа с проектами и сотрудниками
                    0) Выход""");
            switch (scannerService.scanInt()) {
                case 0 -> {
                    return;
                }

                case 1 -> handleEmployees();

                case 2 -> handleProjects();

                case 3 -> handleProjectAssignable();

                default -> System.out.println("Невозможный пункт меню");
            }
        }
    }
}
