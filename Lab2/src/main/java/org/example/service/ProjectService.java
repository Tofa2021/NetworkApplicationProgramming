package org.example.service;

import org.example.model.Nameable;
import org.example.model.Project;
import org.example.model.ProjectAssignable;

import java.util.List;

public interface ProjectService<T extends ProjectAssignable & Nameable> extends Service<Project> {
    void addToProject(Project project, T participant);

    void removeFromProject(T participant);

    void transfer(Project newProject, T participant);

    void create();

    List<T> getProjectAssignables(Project project);
}
