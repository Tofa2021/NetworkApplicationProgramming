package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project implements Nameable {
    private final String name;
    private final List<ProjectAssignable> participants;

    public Project(String name) {
        this.name = name;
        participants = new ArrayList<>();
    }

    public List<ProjectAssignable> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    @Override
    public String getName() {
        return name;
    }

    public void addParticipant(ProjectAssignable participant) {
        participants.add(participant);
    }

    public boolean removeParticipant(ProjectAssignable participant) {
        return participants.remove(participant);
    }
}
