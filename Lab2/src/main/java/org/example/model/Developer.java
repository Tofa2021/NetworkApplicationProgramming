package org.example.model;

import java.util.List;
import java.util.function.Predicate;

public class Developer extends Employee {
    public Developer(String name, int workExperience, List<Skill> skills) {
        super(name, workExperience, skills);
    }

    public static Predicate<Object> getIsInstancePredicate() {
        return (obj) -> obj instanceof Developer;
    }
}
