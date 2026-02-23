package org.example.model;

import java.util.List;
import java.util.function.Predicate;

public class Manager extends Employee {
    public Manager(String name, int workExperience, List<Skill> skills) {
        super(name, workExperience, skills);
    }

    public static Predicate<Object> getIsInstancePredicate() {
        return (obj) -> obj instanceof Manager;
    }
}
