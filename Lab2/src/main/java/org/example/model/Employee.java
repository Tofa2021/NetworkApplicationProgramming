package org.example.model;

import org.example.Nameable;

import java.io.Serializable;
import java.util.List;

public abstract class Employee implements Serializable, Nameable {
    private final String name;
    private final int workExperience;
    private final List<Skill> skills;

    public Employee(String name, int workExperience, List<Skill> skills) {
        this.name = name;
        this.workExperience = workExperience;
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public boolean haveAnySkill(List<Skill> skills) {
        for (Skill skill : skills) {
            return skills.contains(skill);
        }
        return false;
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
