package org.example.service;

import org.example.model.Project;
import org.example.model.ProjectAssignable;
import org.example.model.ProjectTransferLog;

public interface ProjectService extends Service<Project> {
    ProjectTransferLogger getProjectLogger();

    default void addToProject(Project project, ProjectAssignable participant) {
        Project oldProject = participant.getProject();
        project.addParticipant(participant);
        participant.setProject(project);
        getProjectLogger().addLog(new ProjectTransferLog(oldProject, project, participant));
    }

    default void removeFromProject(ProjectAssignable participant) {
        Project project = participant.getProject();
        project.removeParticipant(participant);
        participant.setProject(null);
        getProjectLogger().addLog(new ProjectTransferLog(project, null, participant));
    }

    default void transfer(Project newProject, ProjectAssignable participant) {
        Project oldProject = participant.getProject();
        oldProject.removeParticipant(participant);
        participant.setProject(newProject);
        newProject.addParticipant(participant);
        getProjectLogger().addLog(new ProjectTransferLog(oldProject, newProject, participant));
    }
}
