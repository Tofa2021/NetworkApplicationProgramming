package org.example.service;

import org.example.EmployeeFactory;
import org.example.model.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final ScannerService scannerService;
    private final StorageService<Employee> storageService;
    private final SecurityService securityService;
    private final EmployeeFactory employeeFactory;
    private final Map<Payable, Double> salaries = new HashMap<>();
    private List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl(ScannerService scannerService,
                               StorageService<Employee> storageService, SecurityService securityService,
                               EmployeeFactory employeeFactory
    ) {
        this.scannerService = scannerService;
        this.storageService = storageService;
        this.securityService = securityService;
        this.employeeFactory = employeeFactory;
    }

    public EmployeeServiceImpl(
            ScannerService scannerService,
            StorageService<Employee> storageService, SecurityService securityService,
            EmployeeFactory employeeFactory,
            List<Employee> employees
    ) {
        this.scannerService = scannerService;
        this.storageService = storageService;
        this.securityService = securityService;
        this.employeeFactory = employeeFactory;
        this.employees = employees;
    }

    @Override
    public boolean add(Employee employee) {
        return employees.add(employee);
    }

    @Override
    public boolean remove(Employee employee) {
        return employees.remove(employee);
    }

    @Override
    public List<Employee> getByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getByWorkExperience(int lowerBound, int upperBound) {
        return employees.stream()
                .filter(employee -> {
                    int workExperience = employee.getWorkExperience();
                    return lowerBound <= workExperience && workExperience < upperBound;
                })
                .toList();
    }

    @Override
    public List<Employee> getBySkills(List<Skill> skills) {
        return employees.stream()
                .filter(employee -> employee.haveOneOfSkills(skills))
                .toList();
    }

    @Override
    public Map<Grade, List<Employee>> getMappedByGrade() {
        return employees.stream().collect(Collectors.groupingBy(Employee::getGrade));
    }

    @Override
    public List<Employee> getFiltered(Predicate<Employee> rolePredicate, int minWorkExperience, int maxWorkExperience, List<Skill> skills) {
        return getByRole(rolePredicate).stream()
                .filter(getByWorkExperience(minWorkExperience, maxWorkExperience)::contains)
                .filter(getBySkills(skills)::contains)
                .toList();
    }

    @Override
    public void createDeveloper() {
        employees.add(employeeFactory.createDeveloper());
    }

    @Override
    public void createTester() {
        employees.add(employeeFactory.createTester());
    }

    @Override
    public void createManager() {
        employees.add(employeeFactory.createManager());
    }

    @Override
    public ScannerService getScannerService() {
        return scannerService;
    }

    @Override
    public List<Employee> getElements() {
        return Collections.unmodifiableList(employees);
    }

    @Override
    public void sortByName() {
        employees.sort(Comparator.comparing(Employee::getName));
    }

    @Override
    public void sortByRole() {
        employees.sort(Comparator.comparing(employee -> employee.getClass().getSimpleName()));
    }

    @Override
    public Map<String, List<Employee>> getMappedByRole() {
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getClass().getSimpleName()));
    }

    @Override
    public void save() {
        storageService.save(employees);
    }

    @Override
    public List<Employee> getWithoutProject() {
        return employees.stream()
                .filter(employee -> employee.getProject() == null)
                .toList();
    }

    @Override
    public List<Employee> getExcludingProject(Project project) {
        return employees.stream()
                .filter(employee -> employee.getProject() != project)
                .toList();
    }

    @Override
    public List<Employee> getWithProjectExcludingProject(Project project) {
        return employees.stream()
                .filter(employee -> employee.getProject() != project && employee.getProject() != null)
                .toList();
    }

    @Override
    public void update() {
        if (!securityService.checkPassword()) {
            System.out.println("Неверный пароль");
            return;
        }

        System.out.println("Выберите сотрудника для редактирования");
        Employee employee = select();
        System.out.println(employee.getDescription());
        System.out.println("""
                Выберите, что редактировать
                1) Имя
                2) Стаж
                3) Навыки
                4) Грейд
                5) Зарплата""");

        if (employee instanceof Developer) {
            System.out.println("6) Языки программирования");
        } else if (employee instanceof Tester) {
            System.out.println("6) Инструменты тестирования");
        } else if (employee instanceof Manager) {
            System.out.println("6) Количество подчиненных");
        }

        switch (scannerService.scanInt()) {
            case 1 -> employee.setName(employeeFactory.inputName());
            case 2 -> employee.setWorkExperience(employeeFactory.inputWorkExperience());
            case 3 -> employee.setSkills(employeeFactory.inputSkills());
            case 4 -> employee.setGrade(employeeFactory.inputGrade());
            case 5 -> employee.setSalary(employeeFactory.inputSalary());
            case 6 -> {
                if (employee instanceof Developer developer) {
                    developer.setProgrammingLanguages(employeeFactory.inputProgrammingLanguages());
                } else if (employee instanceof Tester tester) {
                    tester.setTestTools(employeeFactory.inputTestTools());
                } else if (employee instanceof Manager manager) {
                    manager.setTeamSize(employeeFactory.inputTeamSize());
                } else {
                    System.out.println("Невозможный пункт меню");
                }
            }
            default -> System.out.println("Невозможный пункт меню");
        }
    }

    @Override
    public void removeSelected() {
        if (!securityService.checkPassword()) {
            System.out.println("Неверный пароль");
            return;
        }

        if (employees.isEmpty()) {
            System.out.println("Пусто");
            return;
        }

        remove(select());
    }

    @Override
    public void load() {
        employees = storageService.load();
    }

    @Override
    public List<Employee> getByRole(Predicate<Employee> predicate) {
        return employees.stream()
                .filter(predicate)
                .toList();
    }
}
