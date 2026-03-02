package org.example;

import org.example.model.Project;

public interface ProjectService {
    boolean add(Project project);

    boolean remove(Project project);

    Project remove(int index);

    void printProjectList();

    Project select();

    void addEmployeeToProject(EmployeeService employeeService);
}
