package org.example.service;

import org.example.model.Project;
import org.example.model.interfaces.Nameable;
import org.example.model.interfaces.ProjectAssignable;

import java.util.List;

public interface ProjectService<T extends ProjectAssignable & Nameable> extends Service<Project> {
    void addToProject(Project project, T participant);

    void removeFromProject(T participant);

    void transfer(Project newProject, T participant);

    void setAll(List<Project> projects);

    void create();

    List<T> getProjectAssignables(Project project);

    void printTransferLogs();

    void printProjectAndEmployees();
}
