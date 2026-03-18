package org.example.model;

import java.util.List;
import java.util.function.Predicate;

public class Developer extends Employee {
    private final List<ProgrammingLanguages> programmingLanguages;

    public Developer(String name, double salary, Grade grade, int workExperience, List<Skill> skills, List<ProgrammingLanguages> programmingLanguages) {
        super(name, salary, grade, workExperience, skills);
        this.programmingLanguages = programmingLanguages;
    }

    public static Predicate<Employee> getIsInstancePredicate() {
        return (employee) -> employee instanceof Developer;
    }

    @Override
    public String getDescription() {
        String languagesString = programmingLanguages.isEmpty() ?
                "" :
                " Языки программирования: " + String.join(
                        " ",
                        programmingLanguages.stream()
                                .map(ProgrammingLanguages::getName)
                                .toList()
                );

        return super.getDescription() + languagesString;
    }
}
