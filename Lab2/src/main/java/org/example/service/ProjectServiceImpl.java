package org.example.service;

import org.example.model.Employee;
import org.example.model.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final List<Project> projects;

    public ProjectServiceImpl(List<Project> projects) {
        this.projects = projects;
    }

    public ProjectServiceImpl() {
        projects = new ArrayList<>();
    }

    @Override
    public List<Project> getElements() {
        return Collections.unmodifiableList(projects);
    }

    @Override
    public boolean add(Project project) {
        return projects.add(project);
    }

    @Override
    public boolean remove(Project project) {
        return projects.remove(project);
    }

    @Override
    public Project remove(int index) {
        return projects.remove(index);
    }

    @Override
    public void addEmployeeToProject(ScannerService scanner, EmployeeService employeeService) {
        if (projects.isEmpty()) {
            System.out.println("Нет проектов");
            return;
        }

        System.out.println("Выберите проект");
        Project project = select(scanner);

        System.out.println("Выберите сотрудника");
        Employee employee = employeeService.select(scanner);

        project.addEmployee(employee);
    }
}
