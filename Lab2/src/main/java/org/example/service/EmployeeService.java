package org.example.service;

import org.example.model.Employee;
import org.example.model.Skill;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface EmployeeService extends DescribableService<Employee> {
    void sortByRole();

    Map<String, List<Employee>> getGroupedByRole();

    void save();

    void load();

    List<Employee> getByRole(Predicate<Employee> predicate);

    void sortByName();

    List<Employee> getByName(String name);

    List<Employee> getByWorkExperience(int lowerBound, int upperBound);

    List<Employee> getBySkills(List<Skill> skills);
}
