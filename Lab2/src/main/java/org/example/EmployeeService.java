package org.example;

import org.example.model.Employee;
import org.example.model.Skill;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {
    private static List<Employee> employees = new ArrayList<>();

    public static void add(Employee employee) {
        employees.add(employee);
    }

    public static void add(List<Employee> employees) {
        EmployeeService.employees.addAll(employees);
    }

    public static void remove(int index) {
        employees.remove(index);
    }

    public static void remove(Employee employee) {
        employees.remove(employee);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static List<Employee> getByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList());
    }

    public static List<Employee> getByWorkExperience(int lowerBound, int upperBound) {
        return employees.stream()
                .filter(employee -> {
                    int workExperience = employee.getWorkExperience();
                    return lowerBound <= workExperience && workExperience < upperBound;
                })
                .toList();
    }

    public static List<Employee> getBySkills(List<Skill> skills) {
        return employees.stream()
                .filter(employee -> employee.haveAnySkill(skills))
                .toList();
    }

    public static Employee select() {
        return Utils.select(SystemInService.INSTANCE, employees);
    }

    public static void sortByName() {
        employees.sort(Comparator.comparing(Employee::getName));
    }

    public static void sortByRole() {
        employees.sort(Comparator.comparing(employee -> employee.getClass().getSimpleName()));
    }

    public static Map<String, List<Employee>> getGroupedByRole() {
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getClass().getSimpleName()));
    }

    public static void save() {
        FileManager.save(employees);
    }

    public static void load() {
        employees = FileManager.load();
    }
}
