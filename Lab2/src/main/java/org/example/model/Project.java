package org.example.model;

import org.example.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project implements Nameable {
    private final String name;
    private final List<Employee> employees;

    public Project(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Project(String name) {
        this.name = name;
        employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    @Override
    public String getName() {
        return name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }

    public Employee removeEmployee(int index) {
        return employees.remove(index);
    }

    public void printEmployees() {
        Utils.printList(employees);
    }
}
