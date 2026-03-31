package org.example.model.log;

import org.example.model.Project;
import org.example.model.interfaces.Describable;
import org.example.model.interfaces.Nameable;
import org.example.model.interfaces.ProjectAssignable;

public record TransferLog<T extends ProjectAssignable & Nameable>(
        T participant,
        Project oldProject,
        Project newProject) implements Describable {
    @Override
    public String getDescription() {
        String participantString = participant.getName();
        String oldProjectString = oldProject == null ? "без проекта" : oldProject.getName();
        String newProjectString = newProject == null ? "без проекта" : newProject.getName();

        return participantString + " с " + oldProjectString + " на " + newProjectString;
    }
}
