package org.example.service.storage;

import org.example.model.Employee;
import org.example.model.Project;

import java.io.Serializable;
import java.util.List;

public record CompanyData(List<Employee> employees, List<Project> projects) implements Serializable {
}