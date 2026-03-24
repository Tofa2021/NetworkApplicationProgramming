package org.example.service;

import org.apache.logging.log4j.Logger;
import org.example.model.Nameable;
import org.example.model.Project;
import org.example.model.ProjectAssignable;

public interface ProjectService extends Service<Project> {
    Logger getLogger();

    default <T extends ProjectAssignable & Nameable> void addToProject(Project project, T participant) {
        project.addParticipant(participant);
        participant.setProject(project);
        getLogger().info("{} added to project {}", participant.getName(), project.getName());
    }

    default <T extends ProjectAssignable & Nameable> void removeFromProject(T participant) {
        Project project = participant.getProject();
        project.removeParticipant(participant);
        participant.setProject(null);
        getLogger().info("{} removed from project {}", participant.getName(), project.getName());
    }

    default <T extends ProjectAssignable & Nameable> void transfer(Project newProject, T participant) {
        Project oldProject = participant.getProject();
        if (oldProject == null) {
            getLogger().warn("{} cannot be transferred oldProject = null", participant.getName());
            return;
        }
        removeFromProject(participant);
        addToProject(newProject, participant);
        getLogger().info("{} transferred from project {} to project {}", participant.getName(), oldProject.getName(), newProject.getName());
    }
}
