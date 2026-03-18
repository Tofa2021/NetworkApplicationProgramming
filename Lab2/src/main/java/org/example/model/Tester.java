package org.example.model;

import java.util.List;
import java.util.function.Predicate;

public class Tester extends Employee {
    private final List<TestTools> testTools;

    public Tester(String name, double salary, Grade grade, int workExperience, List<Skill> skills, List<TestTools> testTools) {
        super(name, salary, grade, workExperience, skills);
        this.testTools = testTools;
    }

    public static Predicate<Employee> getIsInstancePredicate() {
        return (employee) -> employee instanceof Tester;
    }

    @Override
    public String getDescription() {
        String testToolsString = testTools.isEmpty() ?
                "" :
                " Инструменты: " + String.join(
                        " ",
                        testTools.stream()
                                .map(TestTools::getName)
                                .toList()
                );

        return super.getDescription() + testToolsString;
    }
}
