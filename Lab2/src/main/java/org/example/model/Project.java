package org.example.model;

import org.example.model.interfaces.Nameable;

import java.io.Serializable;

public class Project implements Nameable, Serializable {
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
