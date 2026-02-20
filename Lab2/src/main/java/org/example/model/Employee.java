package org.example.model;

import org.example.Nameable;

import java.io.Serializable;

public abstract class Employee implements Serializable, Nameable {
    private final String name;
    private final int workExperience;

    public Employee(String name, int workExperience) {
        this.name = name;
        this.workExperience = workExperience;
    }

    public String getName() {
        return name;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", workExperience=" + workExperience +
                '}';
    }
}
