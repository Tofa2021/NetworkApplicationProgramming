package org.example.model;

import java.util.List;

public class Developer extends Employee {
    public Developer(String name, int workExperience, List<Skill> skills) {
        super(name, workExperience, skills);
    }
}
