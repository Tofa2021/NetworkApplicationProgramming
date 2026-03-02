package org.example.service;

import org.example.model.Project;

public interface ProjectService extends Service<Project> {
    void addEmployeeToProject(ScannerService scanner, EmployeeService employeeService);
}
