package org.example.service;

import org.example.model.Employee;
import org.example.model.Grade;
import org.example.model.Project;
import org.example.model.Skill;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface EmployeeService extends DescribableService<Employee> {
    void sortByRole();

    void sortByName();

    Map<String, List<Employee>> getMappedByRole();

    List<Employee> getByRole(Predicate<Employee> predicate);

    List<Employee> getByName(String name);

    List<Employee> getByWorkExperience(int lowerBound, int upperBound);

    List<Employee> getBySkills(List<Skill> skills);

    default List<Employee> getByProject(Project project) {
        return getElements().stream()
                .filter(employee -> employee.getProject() == project)
                .toList();
    }

    Map<Grade, List<Employee>> getMappedByGrade();

    List<Employee> getFiltered(
            Predicate<Employee> rolePredicate,
            int minWorkExperience,
            int maxWorkExperience,
            List<Skill> skills
    );

    void createDeveloper();

    void createTester();

    void createManager();

    void load();

    void save();

    List<Employee> getWithoutProject();

    List<Employee> getExcludingProject(Project project);

    List<Employee> getNonNullExcludingProject(Project project);
}
