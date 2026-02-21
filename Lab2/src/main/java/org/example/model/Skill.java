package org.example.model;

import org.example.Nameable;

public enum Skill implements Nameable {
    PLACEHOLDER1("PLACEHOLDER1"),
    PLACEHOLDER2("PLACEHOLDER2"),
    PLACEHOLDER3("PLACEHOLDER3"),
    ;

    private final String name;

    Skill(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
