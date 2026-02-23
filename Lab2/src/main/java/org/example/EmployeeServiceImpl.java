package org.example;

import org.example.model.Employee;
import org.example.model.Skill;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl() {
    }

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void add(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void remove(int index) {
        employees.remove(index);
    }

    public void remove(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Employee> getByWorkExperience(int lowerBound, int upperBound) {
        return employees.stream()
                .filter(employee -> {
                    int workExperience = employee.getWorkExperience();
                    return lowerBound <= workExperience && workExperience < upperBound;
                })
                .toList();
    }

    public List<Employee> getBySkills(List<Skill> skills) {
        return employees.stream()
                .filter(employee -> employee.haveAnySkill(skills))
                .toList();
    }

    public Employee select() {
        return Utils.select(SystemInService.INSTANCE, employees);
    }

    public void sortByName() {
        employees.sort(Comparator.comparing(Employee::getName));
    }

    public void sortByRole() {
        employees.sort(Comparator.comparing(employee -> employee.getClass().getSimpleName()));
    }

    public Map<String, List<Employee>> getGroupedByRole() {
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getClass().getSimpleName()));
    }

    public void save() {
        FileManager.save(employees);
    }

    public void load() {
        employees = FileManager.load();
    }

    public List<Employee> getByRole(Predicate<Employee> predicate) {
        return employees.stream()
                .filter(predicate)
                .toList();
    }

    public void printList() {
        Utils.printList(employees);
    }
}
