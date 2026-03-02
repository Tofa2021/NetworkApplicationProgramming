package org.example.model;

import java.util.List;
import java.util.function.Predicate;

public class Tester extends Employee {
    private final List<String> testTools;

    public Tester(String name, int workExperience, List<Skill> skills, List<String> testTools) {
        super(name, workExperience, skills);
        this.testTools = testTools;
    }

    public static Predicate<Employee> getIsInstancePredicate() {
        return (employee) -> employee instanceof Tester;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Инструменты: " + String.join(" ", testTools);
    }
}
