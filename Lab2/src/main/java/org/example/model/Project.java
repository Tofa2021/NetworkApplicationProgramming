package org.example.model;

public class Project implements Nameable {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
