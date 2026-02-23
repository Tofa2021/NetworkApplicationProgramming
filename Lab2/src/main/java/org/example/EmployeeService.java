package org.example;

import org.example.model.Employee;
import org.example.model.Skill;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface EmployeeService {
    void sortByRole();

    Map<String, List<Employee>> getGroupedByRole();

    void save();

    void load();

    List<Employee> getByRole(Predicate<Employee> predicate);

    Employee select();

    void sortByName();

    void printList();

    void add(Employee employee);

    void add(List<Employee> employees);

    void remove(int index);

    void remove(Employee employee);

    List<Employee> getByName(String name);

    List<Employee> getByWorkExperience(int lowerBound, int upperBound);

    List<Employee> getBySkills(List<Skill> skills);
}
