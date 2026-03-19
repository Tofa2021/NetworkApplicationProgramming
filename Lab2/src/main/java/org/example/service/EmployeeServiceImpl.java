package org.example.service;

import org.example.FileManager;
import org.example.model.Employee;
import org.example.model.Grade;
import org.example.model.Skill;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final ScannerService scannerService;
    private List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    public EmployeeServiceImpl(ScannerService scannerService, List<Employee> employees) {
        this.scannerService = scannerService;
        this.employees = employees;
    }

    @Override
    public boolean add(Employee employee) {
        return employees.add(employee);
    }

    @Override
    public Employee remove(int index) {
        return employees.remove(index);
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
        FileManager.save(employees);
    }

    @Override
    public void load() {
        employees = FileManager.load();
    }

    @Override
    public List<Employee> getByRole(Predicate<Employee> predicate) {
        return employees.stream()
                .filter(predicate)
                .toList();
    }
}
