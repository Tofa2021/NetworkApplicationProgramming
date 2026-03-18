package org.example.model;

public class ProjectTransferLog<T extends ProjectAssignable & Nameable> implements Nameable, Describable {
    private final Project oldProject;
    private final Project newProject;
    private final T participant;

    public ProjectTransferLog(Project oldProject, Project newProject, T participant) {
        this.oldProject = oldProject;
        this.newProject = newProject;
        this.participant = participant;
    }

    @Override
    public String getName() {
        return "Перевод";
    }

    @Override
    public String getDescription() {
        String oldProjectString = getNotNullProjectString(oldProject);
        String newProjectString = getNotNullProjectString(newProject);

        return participant.getName() + " c " + oldProjectString + " на " + newProjectString;
    }

    private String getNotNullProjectString(Project Project) {
        return Project == null ? "без проекта" : Project.getName();
    }
}
